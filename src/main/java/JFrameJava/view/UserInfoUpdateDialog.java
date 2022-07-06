package JFrameJava.view;

import javax.swing.*;

public class UserInfoUpdateDialog extends JDialog {


    private String name;
    private int phone;
    private int vip;
    private int level;
    private double price;

    public  UserInfoUpdateDialog(JFrame jf,String title,String name,int phone,int vip,int level,double price){
        super(jf,title);
        this.name = name;
        this.phone = phone;
        this.vip = vip;
        this.level = level;
        this.price = price;

        init();
    }

    public void init(){
        setLayout(null);
        setBounds(200,200,500,400);
        setResizable(false);
        setVisible(true);

        System.out.println(name+phone+vip+level+price);
    }



}
