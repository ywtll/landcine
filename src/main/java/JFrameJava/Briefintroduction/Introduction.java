package JFrameJava.Briefintroduction;

import Dao.Dao_Member;
import Dao.Dao_Movie_Information;
import Dao.Dao_Movie_Order;
import Model.DZ_Member;
import Model.DZ_Movie_Information;
import film.util.MenuButton;
import film.util.RoundBorder;
import film.util.RoundRectButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * @author  zym
 */
public class Introduction extends JDialog {

    String emailIDImage;

    static final int jj_x=100;

    static final int jj_y=100;

    static final int jj_w=800;

    static final int jj_h=600;

    JDialog jd = this;

    public Introduction(JFrame jf,Object id, String emailIDImage) {
        super(jf, "订票信息", true);
        this.emailIDImage = emailIDImage;

        getContentPane().setBackground(Color.white);

        setLayout(null);
        setBounds( jj_x,jj_y,jj_w,jj_h);


        init(id,emailIDImage);
        setVisible(true);
    }

    private void init(Object id,String  em) {

        List<DZ_Movie_Information> objects = Dao_Movie_Information.Movie_Information_Query(id);
        List<DZ_Member> U = Dao_Member.Member_Query(em);
        System.out.println(objects);
        /**获取图片*/
        ImageIcon image = new ImageIcon(objects.get(0).getY_Movie_Cover());
        image.setImage(image.getImage().getScaledInstance(jj_w/2, jj_h/2,Image.SCALE_DEFAULT ));
        JLabel profile=new JLabel(image);

        profile.setBounds(20,20,jj_w/2,jj_h/2);
        add(profile);
        /**获取电影名称*/
        JLabel label=new JLabel("《"+ objects.get(0).getY_Name()+"》");
        label.setBounds(jj_w-300,jj_h-580,1000,50);
        label.setFont(new Font("黑体",Font.PLAIN,50));
        add(label);
        /**获取导演*/
        JLabel label2=new JLabel("导演："+ objects.get(0).getY_Director());
        label2.setBounds(jj_w-300,jj_h-490,1000,20);
        label2.setFont(new Font("黑体",Font.PLAIN,20));
        add(label2);
        /**获取主演*/
        JLabel label3=new JLabel("主演："+ objects.get(0).getY_Protagonist());
        label3.setBounds(jj_w-300,jj_h-440,1000,20);
        label3.setFont(new Font("黑体",Font.PLAIN,20));
        add(label3);
        /**电影类型*/
        JLabel label4=new JLabel("电影类型："+ objects.get(0).getY_Category());
        label4.setBounds(jj_w-300,jj_h-390,1000,20);
        label4.setFont(new Font("黑体",Font.PLAIN,20));
        add(label4);
        /**上映时间*/
        JLabel label5=new JLabel("上映时间："+ objects.get(0).getY_Show_Time());
        label5.setBounds(jj_w-300,jj_h-340,1000,20);
        label5.setFont(new Font("黑体",Font.PLAIN,20));
        add(label5);
        /**上映时间*/
        JTextArea jta = new JTextArea();
        //自动换行
        jta.setLineWrap(true);
        //模仿JLabel 禁止编辑文字
        jta.setEditable(false);
        //设置背景色和 窗体的背景色一样
        jta.setBackground(Color.WHITE);
        //设置文字
        jta.setText("剧情简介："+objects.get(0).getY_Synopsis());
        jta.setBounds(jj_w-300,jj_h-290,250,600);
        jta.setFont(new Font("黑体",Font.PLAIN,20));
        add(jta);
        /**价格*/
        JLabel label7=new JLabel("价格："+ objects.get(0).getY_Price());
        label7.setBounds(profile.getX()+profile.getWidth()-100-profile.getWidth()/2,profile.getY()+profile.getHeight()+20,200,20);
        label7.setFont(new Font("黑体",Font.PLAIN,20));
        add(label7);
        /**优惠*/
        JLabel label8=new JLabel("优惠："+ objects.get(0).getY_Msdata()+"折");
        label8.setBounds(profile.getX()+profile.getWidth()-100-profile.getWidth()/2+120,profile.getY()+profile.getHeight()+20,200,20);
        label8.setFont(new Font("黑体",Font.PLAIN,20));
        label8.setForeground(Color.red);
        add(label8);
        /**订票*/
        MenuButton tp=new MenuButton(null,"订票",10,5,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        tp.setBounds(label7.getX()-80,label8.getY()+40,120,30);
        tp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //使用Date创建日期对象
         Date date=new Date();
                /**
                 * 创建格式化时间日期类
                 *构造入参String类型就是我们想要转换成的时间形式
                 */
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Dao_Movie_Order.Movie_Order_Insert(U.get(0).getU_Id(),objects.get(0).getY_Id(),format.format(date),objects.get(0).getY_Price(),1);
//                new Seat(jd,id);
            }
        });
        add(tp);
        /**取消*/
        MenuButton qx=new MenuButton(null,"取消",10,5,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        qx.setBounds(label8.getX()+30,label8.getY()+40,120,30);
        qx.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(qx);
    }

    public static void main(String[] args) {
        Introduction introduction=new Introduction(null,1,"981493409@qq.com");
    }
}
