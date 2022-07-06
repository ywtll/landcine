package JFrameJava.view;



import Dao.DButil;
import film.util.DropShadowPanel;
import film.util.MenuButton;
import film.util.SetModeData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;


/**
 * @author zlf
 */

@SuppressWarnings({"all"})
public class AdminUser {

    JFrame jf = new JFrame();
    JPanel jPanel = new JPanel();
    JPanel majorPanel = new JPanel();


    JTable userTable = new JTable();  //用户表格
    SetModeData setModeData = new SetModeData();

    DefaultTableModel userDtm = new DefaultTableModel();

    public AdminUser(JFrame jf ,JPanel jPanel,JPanel majorPanel){
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
        MenuButton userInfo = new MenuButton(null,"用户信息",10,10);
        MenuButton userLevel = new MenuButton(null,"用户等级",10,10);
        MenuButton userUncultivated = new MenuButton(null,"暂未开放",10,10);

        userInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                userInfoPanel();
                majorPanel.repaint();
            }
        });

        userLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                userLevelPanel();
                majorPanel.repaint();
            }
        });

        userUncultivated.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                userUncultivatedPanel();
                majorPanel.repaint();
            }
        });


        userInfo.setBounds(0,250,182,46);
        userLevel.setBounds(0,295,182,46);
        userUncultivated.setBounds(0,340,182,46);

        jPanel.add(userInfo);
        jPanel.add(userLevel);
        jPanel.add(userUncultivated);

        jf.add(jPanel);
    }

    //用户信息界面
    public void userInfoPanel(){

        //Top区域，搜索
        JPanel topJpanel = new JPanel();
        topJpanel.setBackground(new Color(0,156,0));
        topJpanel.setBounds(40,20,8,70);

        JTextField inputText = new JTextField();
        inputText.setBounds(70,40,150,35);

        MenuButton queryButton = new MenuButton(null,"查询",5,5);
        MenuButton updateButton = new MenuButton(null,"信息更新",5,5);
        MenuButton deleteButton = new MenuButton(null,"删除用户",5,5);

        queryButton.setBounds(260,40,70,35);
        updateButton.setBounds(360,40,100,35);
        deleteButton.setBounds(480,40,100,35);


        queryButton.setBackground(new Color(48,168,157));
        queryButton.setForeground(Color.white);
        updateButton.setBackground(new Color(72,175,252));
        updateButton.setForeground(Color.white);
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.white);

        queryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                queryButton.setBackground(new Color(48,168,157));
            }
        });


        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                updateButton.setBackground(new Color(72,175,252));
            }
        });

        deleteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                deleteButton.setBackground(Color.RED);
            }
        });

        queryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = inputText.getText();
                if (query.length() <= 0){
                    JOptionPane.showMessageDialog(null,"请输入查询内容！！");
                    return;
                }
                setModeData.queryUserData(userDtm,query);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int in = userTable.getSelectedRow();
                if (in < 0){
                    JOptionPane.showMessageDialog(null,"请选择要更新信息的用户");
                    return;
                }
                String name = (String) userTable.getValueAt(in, 1);
                int phone = Integer.getInteger((String)userTable.getValueAt(in, 2));
                int vip = Integer.getInteger((String)userTable.getValueAt(in, 3));
                int level = Integer.getInteger((String)userTable.getValueAt(in, 4));
                double price = Double.parseDouble((String)(userTable.getValueAt(in, 5)));
                UserInfoUpdateDialog userInfoUpdateDialog = new UserInfoUpdateDialog(jf,"用户信息修改",name,phone,vip,level,price);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int in = userTable.getSelectedRow();
                if (in < 0){
                    JOptionPane.showMessageDialog(null,"请选择要删除用户");
                    return;
                }
                String sql = "delete from `dizhu`.`dz_member` where `U_Id` = ?";
                Object id = userTable.getValueAt(in, 0);
                int index = DButil.update(sql, id);
                if (index >= 0){
                    JOptionPane.showMessageDialog(null,"删除成功！");
                    setModeData.queryUserData(userDtm,null);
                }
            }
        });


        majorPanel.add(queryButton);
        majorPanel.add(updateButton);
        majorPanel.add(deleteButton);
        majorPanel.add(inputText);
        majorPanel.add(topJpanel);


        /**
         * 用户信息模块
         */
        String[] heads = {"id","用户名","用户手机号","vip等级","用户等级","用户余额","最后登录时间"};

        userDtm.setColumnIdentifiers(heads);

        userTable = new JTable(userDtm){   //设置表格不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        userTable.setRowHeight(25); //设置表格行高
        JTableHeader head = userTable.getTableHeader();
        head.setFont(new Font("",1,20));
        head.setPreferredSize(new Dimension(head.getWidth(),35));

        JScrollPane middleJsp = new JScrollPane(userTable);
        middleJsp.setBounds(15,140,1100,400);
        middleJsp.setBackground(Color.YELLOW);

        majorPanel.add(middleJsp);
    }

    //用户等级界面
    public void userLevelPanel(){
        JLabel label = new JLabel("404 Not Found");
        label.setBounds(400,300,400,80);
        label.setFont(new Font("黑体",Font.BOLD,40));
        majorPanel.add(label);
    }

    //未开发界面
    public void userUncultivatedPanel(){
        JLabel label = new JLabel("404 Not Found");
        label.setBounds(400,300,400,80);
        label.setFont(new Font("黑体",Font.BOLD,40));
        majorPanel.add(label);
    }


    public void setModeData(DefaultTableModel dtm){

    }


}