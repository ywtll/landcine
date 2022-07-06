package film.login;

import film.mainFram.MainFrame;
import film.util.RoundPanel;
import film.util.RoundRectButton;
import film.util.RoundRectTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;

public class LoginFrame extends JFrame {
    private int width = 500, length = 600;

    private  String[] picPaths={"film/img/ded.png","film/img/fcwm.jpg","film/img/ni.png"};
    private int picNum=0;

    private boolean flag=true;

    public LoginFrame(String name,int width, int length, String img) throws Exception {

        super(name);
        this.width = width;
        this.length = length;
        this.setSize(width, length);//设置窗口大小
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭方式
        this.setLocationRelativeTo(null);//设置窗口位置
        this.setBackground(img);//设置背景图片

    }

    public LoginFrame(String name,int width, int length){
        super(name);
        this.width = width;
        this.length = length;
        this.setSize(width, length);//设置窗口大小
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭方式
        this.setLocationRelativeTo(null);//设置窗口位置
    }

    public void setBackground(String picturePath) throws Exception {
        //zoomImage(picturePath,"res.png",width,length);//改变传入图片大小
        ImageIcon icon = new ImageIcon(picturePath);//加载图片
        JLabel label = new JLabel(icon);//将图片放入到label中


        JLayeredPane layeredPane = this.getLayeredPane();


        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置label大小
//        this.add(label);
//        layeredPane.remove(0);

        layeredPane.add(label, new Integer(Integer.MIN_VALUE));
        //添加Label到第二层
        layeredPane.updateUI();
        JPanel jp = (JPanel) this.getContentPane();//获取顶层容器
        jp.repaint();
        jp.setOpaque(false);//将顶层容器设置为透明
    }

//    设置背景图片
    public void setPic(){
        System.out.println("333333");
            if(picNum>=picPaths.length){
                picNum=0;
            }

            try {
                setBackground(picPaths[picNum]);
                picNum++;
            }catch (Exception e){
                System.err.println("读取文件出错"+e.getMessage());
            }
    }

    JLabel titlelabel=new JLabel("魔方电影售票系统");
//    JLabel clabel=new JLabel("登录账号");
//    JLabel plabel=new JLabel("密码");
//    JLabel tlabel=new JLabel("类型");
    Font font = new Font("宋体", Font.BOLD, 18);
    Font tfont = new Font("宋体", Font.BOLD, 40);
    Font bfont = new Font("宋体", Font.BOLD, 18);

    RoundRectButton lbt=new RoundRectButton("登录",20,20);


    RoundRectButton exitbt=new RoundRectButton("退出",20,20);

    RoundRectTextField cf=new RoundRectTextField(350,40,Color.white);
    RoundRectTextField pf=new RoundRectTextField(350,40,Color.white);

//    JComboBox<String> types=new JComboBox<>(new String[]{"普通用户","管理员"});

    //定义一个复选框，初始处于没有选中状态
//    JCheckBox userc = new JCheckBox("普通用户",true);
//    JCheckBox adminc = new JCheckBox("管理员",false);

    //定义一个单选按钮，初始处于选中的状态
    JRadioButton userc = new JRadioButton("普通用户",true);
    //定义一个单选按钮，初始处于选中状态
    JRadioButton adminc = new JRadioButton("管理员",false);

    //定义一个ButtonGroup，把male和female组合起来，实现单选
    ButtonGroup bg  = new ButtonGroup();


    public void init(){
        RoundPanel fullcp=new RoundPanel(20,20);
        fullcp.setBackground(new Color(255,255,255,230));
        fullcp.setLayout(new BorderLayout());

        JFrame loginf=this;



//        this.setBounds(200,200,500,600);
        JPanel topp=new JPanel();
        topp.setBackground(new Color(213,213,213,200));
        topp.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        titlelabel.setFont(tfont);
        titlelabel.setForeground(Color.gray);
        topp.add(titlelabel);
        fullcp.add(topp, BorderLayout.NORTH);


        JPanel copp=new JPanel();
        copp.setLayout(null);
        copp.setBackground(Color.white);
//        clabel.setForeground(Color.gray);
//        clabel.setFont(font);
//        clabel.setBounds(80,100,100,50);
        cf.setBounds(80,100,350,40);
//        BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.white,Color.white,Color.white);

//        cf.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Color.white,Color.black,Color.white,Color.white));
        //cf.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217),1));
//        cf.setBorder(BorderFactory.createEmptyBorder());
        cf.setBackground(new Color(245, 246, 247));

//        copp.add(clabel);
        copp.add(cf);
//        plabel.setForeground(Color.gray);
//        plabel.setFont(font);
//        plabel.setBounds(80,170,100,50);
        pf.setBounds(80,170,350,40);

        //pf.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217),1));
        pf.setBackground(new Color(245, 246, 247));
//        pf.setBorder(BorderFactory.createEmptyBorder());
//        copp.add(plabel);
        copp.add(pf);

//        tlabel.setForeground(Color.gray);
//        tlabel.setFont(font);
//        tlabel.setBounds(80,240,100,50);
//        types.setBounds(200,240,200,40);
//        types.setFont(font);
//        types.setBorder(BorderFactory.createLineBorder(new Color(217, 217, 217),1));
////        types.setBorder(BorderFactory.createEmptyBorder());
//        types.setBackground(new Color(245, 246, 247));
////        types.setBackground(Color.white);
//        copp.add(tlabel);
//        copp.add(types);
        userc.setBounds(100,240,100,30);
        userc.setFont(bfont);
        userc.setBackground(new Color(128, 183, 255,130));
        userc.setOpaque(false);
        adminc.setBounds(280,240,100,30);
        adminc.setFont(bfont);
        adminc.setOpaque(false);
        bg.add(userc);
        bg.add(adminc);
        copp.add(userc);
        copp.add(adminc);
//        设置透明
        copp.setOpaque(false);
        fullcp.add(copp);
        JPanel bp=new JPanel();
        bp.setLayout(new FlowLayout(FlowLayout.CENTER,50,40));
        lbt.setPreferredSize(new Dimension(80,40));

        lbt.setBorder(BorderFactory.createEmptyBorder());
        lbt.setOpaque(false);
        lbt.setForeground(Color.white);
        lbt.setFont(bfont);
        lbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginf.setVisible(false);
                new MainFrame("影片管理").init();
            }
        });
        exitbt.setPreferredSize(new Dimension(80,40));
        exitbt.setBorder(BorderFactory.createEmptyBorder());
        exitbt.setFont(bfont);
        exitbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                正常退出
                System.exit(0);
            }
        });
        bp.add(lbt);
        bp.add(exitbt);
        bp.setBackground(Color.white);

        bp.setOpaque(false);
        fullcp.add(bp,BorderLayout.SOUTH);
        fullcp.setBounds(400,100,500,550);
        this.setLayout(null);
        this.add(fullcp);
        Container contentPane = this.getContentPane();
        JPanel jcp=(JPanel)contentPane;

//        this.repaint();
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) throws Exception {
        LoginFrame loginf=new LoginFrame("login",1024, 768, "film/img/ded.png");
        loginf.init();
        loginf.setUndecorated(true);
        loginf.setVisible(true);
        TimerTask tt= new TimerTask(){
            @Override
            public void run() {
               loginf.setPic();
           }
        };
        java.util.Timer timer=new java.util.Timer();
        //timer.schedule(tt,1000,2000);
       // Component[] components = jcp.getComponents();

        int i = JOptionPane.showConfirmDialog(loginf, "你好的啊");
        System.out.println(i);

//        new LoginFrame("login",500, 600).init();
    }



}
