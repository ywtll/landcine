package JFrameJava.login;

import Dao.Dao_Member;
import Dao.SendEmail;
import Model.DZ_Member;
import film.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

/**
 * @author  zym
 */
public class Login extends JDialog {

    // 是否发送邮箱
    static final boolean is_SendEmail = false;

    static final int Login_x=450;

    static final int Login_y=300;

    static final int Login_w=600;

    static final int Login_h=400;
    private String  state;


    public Login() {
    }

    public Login(JFrame jf) {
        super(jf);
        setModal(true);
        setTitle("登录界面");
        JLabel profile=new JLabel(new ImageIcon("film/loginimg/logo.png"));
        getContentPane().setBackground(Color.white);
        profile.setBounds(20,20,250,250);
        add(profile);
        setLayout(null);
        setBounds(Login_x,Login_y,Login_w,Login_h);
        init();
        this.setUndecorated(true);
        setVisible(true);
    }

    RoundRectJPasswordField mm;
    public String[] init(){
        final String[] ME = {null};
        final String[] U_yzm = {null};
        JLabel label=new JLabel("请输入邮箱");
        label.setBounds(Login_w-290,Login_h-275,Login_w-350,Login_h-370);
        add(label);
        JLabel label2=new JLabel("请输入密码");
        label2.setBounds(Login_w-290,Login_h-225,Login_w-350,Login_h-370);
        add(label2);
        /**密码文本宽*/
        mm=new RoundRectJPasswordField(new ImageIcon(),Login_w-450,Login_h-370,Color.lightGray);
        mm.setBounds(Login_w-300,Login_h-200,Login_w-350,Login_h-370);

        add(mm);
        /**账号文本宽*/
        RoundRectTextField zh=new RoundRectTextField(new ImageIcon(),Login_w-450,Login_h-370,Color.lightGray);
        zh.setBounds(Login_w-300,Login_h-250,Login_w-350,Login_h-370);
        zh.setText("981493409@qq.com");
        add(zh);
        /**报错*/
        JLabel label4=new JLabel("密码或邮箱输入错误");
        label4.setBounds(330,225,Login_w-350,Login_h-370);
        label4.setVisible(false);
        add(label4);
        /**登录按钮*/
        MenuButton dl=new MenuButton(null,"登录",15,15,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        dl.setBounds(Login_w-300,Login_h-150,Login_w-500,Login_h-370);
        JDialog jd = this;
        dl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1=mm.getFieldText();
                String text2=zh.getFieldText();
                List<DZ_Member> dz_members = Dao_Member.Member_Query(text2);
                try {
                    if ( text1.toUpperCase().equals(U_yzm[0])&Dao_Member.isEmail(text2)& "请输入验证码".equals(label2.getText())){
                        if (dz_members.size()==1){
                            System.out.println("邮箱登陆成功");
                            state =text2;
                            setVisible(false);
                            return;
                        }
                        if (dz_members.size()==0){

                    new revise(jd,text2);
                            state =text2;
                            setVisible(false);
                            return ;
                        }
                    }
                    if ( dz_members.get(0).getU_Password().equals(text1)& "请输入密码".equals(label2.getText())){
                        System.out.println("密码登陆成功");
                        state =text2;
                        setVisible(false);
                        return;
                    }

                }catch (Exception q){
                    q.printStackTrace();
                }
                label4.setForeground(Color.red);
                label4.setVisible(true);
            }
        });
        add(dl);

        /**发送验证码按钮*/
        MenuButton yzm=new MenuButton(null,"验证码",15,15,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        yzm.setBounds(mm.getX()+120,mm.getY(),120,30);
        yzm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text2=zh.getFieldText();
                if (Dao_Member.isEmail(text2)){

                    // 发送邮箱
                    Thread th1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 获取uuid 取后六位全都大小
                            String uuid = UUID.randomUUID().toString().replace("-","").substring(0,6).toUpperCase();
                            String message = "欢迎来到地组影院，您的验证码为：{" + uuid + "}，请在30分钟内使用。";
                            if (is_SendEmail){
                                new SendEmail().sendEmail(text2,"地组影院",message);
                            }
                            System.out.println(message);
                            U_yzm[0] = uuid;
                        }
                    });
                    th1.start();

                    // 验证码倒计时
                    int time = 60;
                    MenuButton yzmq1=new MenuButton(null,String.valueOf(time),15,15,new Color(158, 161, 182),new Color(73, 75, 87),new Color(73, 75, 87),new Color(73, 75, 87),new Color(73, 75, 87));

                    Thread th2 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            yzm.setVisible(false);
                            yzmq1.setBounds(mm.getX()+120,mm.getY(),120,30);
                            for (int i = time ;i >= 0 ;i--) {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException interruptedException) {
                                    rootPane.isPaintingTile();
                                }
                                yzmq1.setText("" + i);
                                yzmq1.setVisible(true);

                                if (i==0){
                                    yzmq1.setVisible(false);
                                    yzm.setVisible(true);
                                }
                            }
                        }
                    });
                    th2.start();
                    add(yzmq1);

                }else {
                     label4.setText("密码或邮箱输入错误");
                     label4.setForeground(Color.red);
                    label4.setVisible(true);
                }
            }
        });
        add(yzm);

        /**密码登录按钮*/
        MenuButton Password=new MenuButton(null,"密码登录",15,15,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        Password.setBounds(Login_w-300,Login_h-300,Login_w-500,Login_h-370);
        Password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mm.setEcChar('*');
                yzm.setVisible(false);
                label4.setText("密码或账号输入错误");
                label4.setVisible(false);
                mm.setToolTipText(" ");
                zh.setToolTipText(" ");
                label.setText("请输入账号");
                label2.setText("请输入密码");
                mm.setBounds(Login_w-300,Login_h-200,Login_w-350,Login_h-370);

            }
        });
        add(Password);
        /**退出按钮*/
        MenuButton zc=new MenuButton(null,"退出",15,15,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        zc.setBounds(Login_w-150,Login_h-150,Login_w-500,Login_h-370);
        zc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        add(zc);
        /**短信登录按钮*/
        MenuButton Sms=new MenuButton(null,"邮箱登录",15,15,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        Sms.setBounds(Login_w-150,Login_h-300,Login_w-500,Login_h-370);
        Sms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mm.setEcChar('\0');
                yzm.setVisible(true);
                label4.setVisible(false);
                label4.setText("邮箱或验证码输入错误");
                mm.setText("");
                zh.setText("");
                label.setText("请输入邮箱");
                label2.setText("请输入验证码");
                mm.setBounds(Login_w-300,Login_h-200,Login_w-500,Login_h-370);
                yzm.setVisible(true);
            }
        });
        add(Sms);
        return ME;
    }

    public String getReturnStatus() {
        System.out.println(state);
        return state;
    }
}
