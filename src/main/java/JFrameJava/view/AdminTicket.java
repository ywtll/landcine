package JFrameJava.view;


import film.util.DropShadowPanel;
import film.util.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author zlf
 */
public class AdminTicket {

    JFrame jf = new JFrame();
    JPanel jPanel = new JPanel();
    JPanel majorPanel = new JPanel();

    public AdminTicket(JFrame jf ,JPanel jPanel,JPanel majorPanel){
        this.jPanel = jPanel;
        this.jf = jf;
        this.majorPanel = majorPanel;
        adminMoive(jf,jPanel);
    }


    public void adminMoive(JFrame jf,JPanel jPanel){
        jPanel.setLayout(null);
        jPanel.setBounds(0,48,182,702);
        jPanel.setBackground(new Color(57,61,73));

        //默认显示movie
        MenuButton ticketInfo = new MenuButton(null,"订单信息",10,10);
        MenuButton ticketUncultivated = new MenuButton(null,"暂未开放",10,10);
        MenuButton ticketUncultivated1 = new MenuButton(null,"暂未开放",10,10);

        ticketInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                ticketInfoPanel();
                majorPanel.repaint();
            }
        });

        ticketUncultivated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                ticketUncultivatedPanel();
                majorPanel.repaint();
            }
        });

        ticketUncultivated1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                ticketUncultivatedPanel();
                majorPanel.repaint();
            }
        });

        ticketInfo.setBounds(0,250,182,46);
        ticketUncultivated.setBounds(0,295,182,46);
        ticketUncultivated1.setBounds(0,340,182,46);

        jPanel.add(ticketInfo);
        jPanel.add(ticketUncultivated);
        jPanel.add(ticketUncultivated1);

        jf.add(jPanel);
    }

    public void ticketInfoPanel(){

        //Top区域，搜索
        DropShadowPanel topPanel = new DropShadowPanel(15);
        topPanel.setBounds(30,40,200,40);
        topPanel.setBackground(Color.GREEN);
        majorPanel.add(topPanel);

        //订单区域模块
        JScrollPane middleJsp = new JScrollPane();
        middleJsp.setBounds(30,150,900,400);
        middleJsp.setBackground(Color.YELLOW);
        majorPanel.add(middleJsp);
    }

    public void ticketUncultivatedPanel(){
        DropShadowPanel UncultivatedPanel = new DropShadowPanel(15);
        JLabel label = new JLabel("404 Not Found");
        label.setBounds(400,300,400,80);
        label.setFont(new Font("黑体",Font.BOLD,40));
        majorPanel.add(label);
    }



}
