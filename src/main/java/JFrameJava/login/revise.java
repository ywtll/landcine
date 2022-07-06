package JFrameJava.login;

import Dao.Dao_Member;
import film.util.MenuButton;
import film.util.RoundRectTextField;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class revise  extends JDialog {
    static final int revise_x=300;

    static final int revise_y=300;

    static final int revise_w=500;

    static final int revise_h=300;
    public revise(JDialog jDialog, String M){

        super(jDialog,"登录界面",true);
        setModal(true);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(revise_x,revise_y,revise_w,revise_h);
        init(M);
        setVisible(true);
    }

    private void   init(String M) {
        JLabel label=new JLabel("请输入密码");
        label.setBounds(100,50,100,30);
        label.setFont(new Font("黑体",Font.BOLD,15));
        add(label);
        JLabel label2=new JLabel("请确认密码");
        label2.setBounds(100,120,100,30);
        label2.setFont(new Font("黑体",Font.BOLD,15));
        add(label2);
        JLabel label3=new JLabel("         ");
        label3.setBounds(label.getX(),label.getY()+30,150,20);
        add(label3);
        JLabel label4=new JLabel("         ");
        label4.setBounds(label.getX(),label2.getY()+30,150,20);
        add(label4);
        /**眼睛图片宽*/
        JLabel zj=new JLabel(new ImageIcon("film/loginimg/zy.png"));
        zj.setBounds(250,-60,300,300);
        add(zj);
        JLabel by=new JLabel(new ImageIcon("film/loginimg/by.png"));
        by.setBounds(250,-60,300,300);
        by.setVisible(false);
        add(by);
        /**密码文本宽*/
        RoundRectTextField mm1=new RoundRectTextField(new ImageIcon(),150,30,Color.lightGray);
        mm1.setBounds(label.getX(),label.getY()+30,150,30);

        add(mm1);
        /**确认密码文本宽*/
        RoundRectTextField mm2=new RoundRectTextField(new ImageIcon(),150,30,Color.lightGray);
        mm2.setBounds(label.getX(),label2.getY()+30,150,30);
        add(mm2);
        /**确认按钮*/
        JLabel qs=new JLabel("请确认密码是否相同");
        qs.setBounds(mm2.getX(),mm2.getY()+30,500,30);
        qs.setFont(new Font("黑体",Font.BOLD,10));
        qs.setForeground(Color.red);
        qs.setVisible(false);
        add(qs);
        MenuButton qr=new MenuButton(null,"确认",15,15,new Color(255, 255, 255),new Color(59, 125, 239),new Color(39, 87, 168),new Color(59, 125, 239));
        qr.setBounds(label.getX(),mm2.getY()+60,80,30);
        qr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1=mm1.getFieldText();
                String text2=mm2.getFieldText();
                if (Dao_Member.ismm(text1)){
                    if (text1.equals(text2)){
                        Random random=new Random();
                        int r=random.nextInt(989)+100;
                        Dao_Member.Member_Insert("用户"+r,"",text1,M,"","film/img/profile.png",0,0,0,0);
                        System.out.println("新建成功");
                        setVisible(false);
                    }else {
                         qs.setText("请确认密码是否相同");
                        qs.setVisible(true);
                    }
                }else {
                    qs.setText("密码必须大写字母、小写字母、特殊符号、数字中的任意三项、长度为6-8位");
                    qs.setVisible(true);
                }

            }
        });
        add(qr);



        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }
        });




        label4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }
        });

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                zj.setVisible(true);
                by.setVisible(false);
            }
        });




        label3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                zj.setVisible(false);
                by.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new revise(null,null);
    }
}
