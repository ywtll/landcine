package JFrameJava.view;

import Dao.DButil;
import film.util.MenuButton;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;

/**
 * @author zlf
 */
@SuppressWarnings({"all"})
public class Admin {

    static {
        DButil.getConnection();
    }

    JFrame jf = new JFrame("后台管理");
    Color buttonTextColor = new  Color(242,242,242);//设置按钮文本的颜色
    Color buttonbBackground = new Color(35,38,46);//设置按钮背景颜色
    Font font = new Font("隶属",Font.BOLD,19);  //设置按钮字体大小

    JPanel leftManagePanel = new JPanel(); //左边的按钮

    JPanel left = new JPanel();//设置左边的区域

    JPanel majorPanel = new JPanel();//设置中间的区域
    //全部组装起来
    public void initAll(){
        jf.setLayout(null);
        jf.setBounds(100,50,1324,750);


        topLeftMange();
        topMange();

        new AdminMovie(jf,leftManagePanel,majorPanel);
        middlePanel();


        jf.setResizable(false);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    //设置左上角的地方
    public void topLeftMange(){
        JPanel leftTop = new JPanel();
        leftTop.setLayout(null);
        leftTop.setBounds(0,0,182,48);
        leftTop.setBackground(Color.BLACK);
        JLabel titleLebel = new JLabel("地主影院管理系统");
        titleLebel.setBounds(10,10,170,30);
        titleLebel.setFont(new Font("⾪书",Font.BOLD,18));
        titleLebel.setForeground(Color.white);
        leftTop.add(titleLebel);
        jf.add(leftTop);
    }

    //设置top的电影管理，用户管理，售票管理等
    public void topMange(){
        JPanel top = new JPanel();
        top.setLayout(null);
        top.setBounds(182,0,1324,48);
        top.setBackground(new Color(35,38,46));
        jf.add(top);
        //设置组件上的按钮

        MenuButton movieManage = new MenuButton(null,"电影管理",10,10);
        MenuButton userManage = new MenuButton(null,"用户管理",10,10);
        MenuButton ticketManage = new MenuButton(null,"售票管理",10,10);

        movieManage.setBounds(200,0,160,48);
        userManage.setBounds(370,0,160,48);
        ticketManage.setBounds(540,0,160,48);

        movieManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftManagePanel.removeAll();
                new AdminMovie(jf,leftManagePanel,majorPanel);
                leftManagePanel.repaint();
            }
        });

        userManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftManagePanel.removeAll();
                new AdminUser(jf,leftManagePanel,majorPanel);
                leftManagePanel.repaint();
            }
        });

        ticketManage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftManagePanel.removeAll();
                new AdminTicket(jf,leftManagePanel,majorPanel);
                leftManagePanel.repaint();
            }
        });

        top.add(movieManage);
        top.add(userManage);
        top.add(ticketManage);
    }

    //设置头像
    public void imageIcon(){

    }

    //中间的区域
    public void middlePanel(){

        majorPanel.setLayout(null);
        majorPanel.setBounds(182,48,1142,702);


        JLabel titleLabel = new JLabel("欢迎来到地主后台系统！");
        titleLabel.setBounds(350,300,450,60);
        titleLabel.setFont(new Font("黑体",Font.BOLD,30));
        majorPanel.add(titleLabel);

        jf.add(majorPanel);
    }


    public static void main(String[] args) {
        new Admin().initAll();
    }

}
