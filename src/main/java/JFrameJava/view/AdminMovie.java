package JFrameJava.view;
import Dao.DButil;
import Dao.Dao_Movie_Information;
import film.util.DropShadowPanel;
import film.util.MenuButton;
import film.util.SetModeData;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * @author zlf
 */
@SuppressWarnings({"all"})
public class AdminMovie {


    SetModeData setModeData = new SetModeData();
    DefaultTableModel queryDtm = new DefaultTableModel();

    //电影封面地址
    String imgPath;

    JFrame jf = new JFrame();
    JPanel jPanel = new JPanel();
    JPanel majorPanel = new JPanel();


    public AdminMovie(JFrame jf ,JPanel jPanel,JPanel majorPanel){
        this.jPanel = jPanel;
        this.jf = jf;
        this.majorPanel = majorPanel;
        adminMovie(jf,jPanel);
    }


    public void adminMovie(JFrame jf,JPanel jPanel){
        jPanel.setLayout(null);
        jPanel.setBounds(0,48,182,702);
        jPanel.setBackground(new Color(57,61,73));


        MenuButton movieAdd = new MenuButton(null,"电影增加",10,10);
        MenuButton movieQuery = new MenuButton(null,"电影查询",10,10);
        MenuButton movieAdjust = new MenuButton(null,"电影修改",10,10);

        movieAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                movieAddPanel();
                majorPanel.repaint();
            }
        });

        movieQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                movieQueryPanel();
                majorPanel.repaint();
            }
        });

        movieAdjust.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                majorPanel.removeAll();
                movieUpdatePanel();
                majorPanel.repaint();
            }
        });


        movieAdd.setBounds(0,250,182,46);
        movieQuery.setBounds(0,295,182,46);
        movieAdjust.setBounds(0,340,182,46);

        jPanel.add(movieAdd);
        jPanel.add(movieQuery);
        jPanel.add(movieAdjust);

        jf.add(jPanel);
    }

    //电影增加模块
    public void movieAddPanel(){

        //设置左边区域
        DropShadowPanel leftPanel = new DropShadowPanel(20);
        leftPanel.setLayout(null);
        leftPanel.setBounds(30,20,408,530);
        leftPanel.setBackground(Color.MAGENTA);

        Font labelFont = new Font("黑体",Font.BOLD,18);  //设置label的字体格式

        JLabel nameLabel = new JLabel("电影名称");
        JLabel typeLabel = new JLabel("电影类型");
        JLabel performerLabel = new JLabel("主演");
        JLabel directorLabel = new JLabel("导演");
        JLabel languageLabel = new JLabel("语言");
        JLabel priceLabel = new JLabel("价格");
        JLabel introductionLabel = new JLabel("简介");

        JTextField nameText = new JTextField();
        JComboBox typeBox = new JComboBox();
        typeBox.addItem("---请选择电影类型---");
        typeBox.addItem("恐怖电影");
        typeBox.addItem("戏剧");
        typeBox.addItem("科教");
        JTextField performerText = new JTextField();
        JTextField directorText = new JTextField();
        JTextField languageText = new JTextField();
        JTextField priceText = new JTextField();
        JTextArea introductionArea = new JTextArea(20,30);
        introductionArea.setLineWrap(true);

        nameLabel.setFont(labelFont);
        typeLabel.setFont(labelFont);
        performerLabel.setFont(labelFont);
        directorLabel.setFont(labelFont);
        languageLabel.setFont(labelFont);
        priceLabel.setFont(labelFont);
        introductionLabel.setFont(labelFont);



        nameLabel.setBounds(40,30,100,40);
        typeLabel.setBounds(40,90,100,40);
        performerLabel.setBounds(40,150,100,40);
        directorLabel.setBounds(40,210,100,40);
        languageLabel.setBounds(40,270,100,40);
        priceLabel.setBounds(40,330,100,40);
        introductionLabel.setBounds(40,390,100,40);

        nameText.setBounds(150,30,200,35);
        typeBox.setBounds(150,90,200,35);
        performerText.setBounds(150,150,200,35);
        directorText.setBounds(150,210,200,35);
        languageText.setBounds(150,270,200,35);
        priceText.setBounds(150,330,200,35);
        introductionArea.setBounds(150,390,200,80);



        leftPanel.add(nameLabel);leftPanel.add(typeLabel);leftPanel.add(performerLabel);leftPanel.add(directorLabel);leftPanel.add(languageLabel);leftPanel.add(priceLabel);leftPanel.add(introductionLabel);
        leftPanel.add(nameText);leftPanel.add(typeBox);leftPanel.add(performerText);leftPanel.add(directorText);leftPanel.add(languageText);leftPanel.add(priceText);leftPanel.add(introductionArea);

        majorPanel.add(leftPanel);

        //设置右边区域
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(Color.ORANGE);
        rightPanel.setBounds(580,30,329,409);

        JLabel imgLabel = new JLabel(new ImageIcon(""));
        imgLabel.setBounds(0,0,329,409);
        rightPanel.add(imgLabel);


        majorPanel.add(rightPanel);

        //添加保存、取消、插入按钮
        MenuButton saveButton = new MenuButton(null,"保存",10,10);
        MenuButton cancelButton = new MenuButton(null,"取消",10,10);
        MenuButton addImageButton = new MenuButton(null,"插入图片",10,10);


        saveButton.setBackground(new Color(2,167,240));
        saveButton.setForeground(Color.white);
        cancelButton.setForeground(Color.white);
        addImageButton.setForeground(Color.white);

        saveButton.setBounds(200,580,160,40);
        cancelButton.setBounds(600,580,160,40);
        addImageButton.setBounds(660,470,180,40);

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                saveButton.setBackground(new Color(2,167,240));
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                Object type = typeBox.getSelectedItem();
                String performer = performerText.getText();
                String director = directorText.getText();
                String language = languageText.getText();
                String price = priceText.getText();
                String introduction = introductionArea.getText();
                String Msdata = "1";   //优惠金额，默认为1
                //System.out.println(imgPath);
                //System.out.println(name+type+performer+director+language+price+introduction+imgPath);

                String sql = "insert into dz_movie_information (Y_name,Y_Category,Y_Protagonist,Y_Director,Y_language,Y_Price,Y_Synopsis,Y_Movie_Cover,Y_Msdata) values(?,?,?,?,?,?,?,?,?)";
                if (name.length() <= 0 || performer.length() <= 0 || director.length() <= 0 || language.length() <= 0 || introduction.length() <= 0){
                    JOptionPane.showMessageDialog(null,"请添加完整信息！！");
                }else if (typeBox.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null,"请选择电影类型！");
                }
                else {
                    int index = new Dao_Movie_Information().Movie_Information_Insert(name, type, performer, director, language, price,introduction,imgPath,Msdata);
                    if (index >0){
                        JOptionPane.showMessageDialog(null,"添加成功！");
                        nameText.setText("");
                        performerText.setText("");
                        typeBox.setSelectedIndex(0);
                        directorText.setText("");
                        languageText.setText("");
                        introductionArea.setText("");
                    }
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameText.setText("");
                performerText.setText("");
                typeBox.setSelectedIndex(0);
                directorText.setText("");
                languageText.setText("");
                introductionArea.setText("");
                imgLabel.setIcon(null);
            }
        });

        //插入电影封面
        addImageButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //文件打开对话框
                JFileChooser imgFileChoose = new JFileChooser();
                int chose = imgFileChoose.showOpenDialog(null);
                File imgFile = imgFileChoose.getSelectedFile();
                imgPath = imgFile.toString();
                String substring = imgPath.substring(imgPath.lastIndexOf("."));
                if (!substring.equals(".png") && !substring.equals(".jpg") && !substring.equals(".jpeg")){
                    JOptionPane.showMessageDialog(null,"只允许png格式图片！");
                }else {
                    try {
                        BufferedImage img = ImageIO.read(imgFile);
                        imgLabel.setIcon(new ImageIcon(img));
                        System.out.println("图片路径为："+imgPath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        majorPanel.add(saveButton);
        majorPanel.add(cancelButton);
        majorPanel.add(addImageButton);


    }

    //电影查询模块
    public void movieQueryPanel(){


        /**
         *  按钮查询功能
         */

        JLabel nameLabel = new JLabel("电影名");
        JTextField nameText = new JTextField();
        MenuButton nameQuery = new MenuButton(null, "搜索", 5, 5);
        MenuButton allQuery = new MenuButton(null, "查找所有电影", 5, 5);

        nameLabel.setFont(new Font("黑体",Font.BOLD,30));

        nameLabel.setBounds(20,30,120,40);
        nameText.setBounds(160,30,200,40);
        nameQuery.setBounds(380,30,100,40);
        allQuery.setBounds(800,30,180,40);

        //按钮事件
        nameQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = nameText.getText();
                if (query.length() <= 0){
                    JOptionPane.showMessageDialog(null,"请写入要搜索的内容");
                }else {
                    setModeData.queryMovieData(queryDtm,query);
                }
            }
        });


        allQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModeData.queryMovieData(queryDtm,null);
            }
        });

        majorPanel.add(nameLabel);
        majorPanel.add(nameText);
        majorPanel.add(nameQuery);
        majorPanel.add(allQuery);

        /**
         *  表格-电影信息
         */
        String[] heads = {"电影id","电影名称","类型","导演","主演","语言","介绍","价格","图片路径"};
        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(20,100,1100,300);

        queryDtm.setColumnIdentifiers(heads);

        JTable movieTable = new JTable(queryDtm){     //设置表格不可编辑
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        movieTable.setFont(new Font("黑体",0,16));  //设置表格字体
        movieTable.setRowHeight(25);  //设置表格行高
        JTableHeader head = movieTable.getTableHeader();
        head.setFont(new Font("",Font.BOLD,20));
        head.setPreferredSize(new Dimension(head.getWidth(),32));
        jsp.setBorder(new TitledBorder(null,"电影信息",TitledBorder.CENTER,TitledBorder.TOP,new Font("黑体",Font.BOLD,18),Color.GRAY));

        jsp.setViewportView(movieTable);

        majorPanel.add(jsp);

        //删除按钮
        MenuButton deleteButton = new MenuButton(null, "删除", 5, 5);
        deleteButton.setBounds(300,450,120,40);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int in = movieTable.getSelectedRow();
                if (in < 0){
                    JOptionPane.showMessageDialog(null,"请选择要删除的内容！！");
                    return;
                }
                Object id = movieTable.getValueAt(in, 0);
                String sql = "delete from `dizhu`.`dz_movie_information` where `Y_Id` = ?";
                int index = DButil.update(sql, id);
                if (index >0){
                    JOptionPane.showMessageDialog(null,"删除成功！");
                    setModeData.queryMovieData(queryDtm,null);
                }
            }
        });

        majorPanel.add(deleteButton);

    }

    //电影修改&更新模块
    public void movieUpdatePanel(){

        //查找电影部分
        JLabel idLabel = new JLabel("电影ID");
        JLabel nameLabel = new JLabel("电影名");

        nameLabel.setFont(new Font("",1,24));

        JTextField idText = new JTextField();
        JTextField nameText = new JTextField();

        nameText.setBackground(Color.cyan);
        nameText.setBorder(null);
        nameText.setFont(new Font("",0,20));

        MenuButton nameQuery = new MenuButton(null,"搜索",10,10);
        MenuButton allQuery = new MenuButton(null,"查找所有电影",10,10);


        nameQuery.setFont(new Font("黑体",0,24));
        allQuery.setFont(new Font("黑体",0,24));


        nameQuery.setForeground(Color.white);
        allQuery.setForeground(Color.white);

        nameLabel.setBounds(20,20,120,45);

        nameText.setBounds(120,20,200,45);

        nameQuery.setBounds(320,20,100,45);
        allQuery.setBounds(860,20,160,45);

        majorPanel.add(idLabel);
        majorPanel.add(nameLabel);

        majorPanel.add(idText);
        majorPanel.add(nameText);


        majorPanel.add(nameQuery);
        majorPanel.add(allQuery);

        nameQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameText.getText();
                if (name.length() <= 0){
                    JOptionPane.showMessageDialog(null,"请输入电影名称！");
                }else {
                    setModeData.queryMovieData(queryDtm,name);
                }
            }
        });

        allQuery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setModeData.queryMovieData(queryDtm,null);
            }
        });


        //JScrollPane2
        String[] heads = {"电影id","电影名称","类型","导演","主演","语言","介绍","价格","图片路径"};
        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(20,80,1100,200);


        queryDtm.setColumnIdentifiers(heads);
        JTable movieTable = new JTable(queryDtm){    //设置表格不可进行编辑
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        movieTable.setFont(new Font("黑体",0,16));
        movieTable.setRowHeight(25); //设置表格行高
        JTableHeader head = movieTable.getTableHeader();
        head.setPreferredSize(new Dimension(head.getWidth(),32));
        head.setFont(new Font("",Font.BOLD,20));
        jsp.setBorder(new TitledBorder(null,"电影基本信息",TitledBorder.CENTER,TitledBorder.TOP,new Font("黑体",Font.BOLD,18),Color.GRAY));

        jsp.setViewportView(movieTable);
        majorPanel.add(jsp);

        //面板3，负责显示要修改的内容
        JScrollPane jScrollPane3 = new JScrollPane();
        jScrollPane3.setBounds(20,300,1100,120);
        JLabel jLabel1 = new JLabel("电影id");
        JLabel jLabel2 = new JLabel("名字");
        JLabel jLabel3 = new JLabel("种类");
        JLabel jLabel4 = new JLabel("导演");
        JLabel jLabel5 = new JLabel("主演");
        JLabel jLabel6 = new JLabel("语言");
        JLabel jLabel7 = new JLabel("价格");
        JLabel jLabel8 = new JLabel("图片路径");

        jLabel1.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));
        jLabel2.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));
        jLabel3.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));
        jLabel4.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));
        jLabel5.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));
        jLabel6.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));
        jLabel7.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));
        jLabel8.setIcon(new ImageIcon("E:/JavaYm/untitled/src/main/java/JFrameJava/img/user.png"));


        jLabel1.setBounds(20,5,80,35);
        jLabel2.setBounds(260,5,80,35);
        jLabel3.setBounds(460,5,80,35);
        jLabel4.setBounds(660,5,80,35);
        jLabel5.setBounds(20,65,80,35);
        jLabel6.setBounds(260,65,80,35);
        jLabel7.setBounds(500,65,50,35);
        jLabel8.setBounds(700,65,100,35);

        JTextField jTextField1 = new JTextField();
        JTextField jTextField2 = new JTextField();
        JTextField jTextField3 = new JTextField();
        JTextField jTextField4 = new JTextField();
        JTextField jTextField5 = new JTextField();
        JTextField jTextField6 = new JTextField();
        JTextField jTextField7 = new JTextField();
        JTextField jTextField8 = new JTextField();



        jTextField1.setBorder(null);jTextField2.setBorder(null);jTextField3.setBorder(null);
        jTextField4.setBorder(null);jTextField5.setBorder(null);jTextField6.setBorder(null);
        jTextField7.setBorder(null);jTextField8.setBorder(null);
        jTextField1.setBackground(Color.LIGHT_GRAY);jTextField2.setBackground(Color.LIGHT_GRAY);
        jTextField3.setBackground(Color.LIGHT_GRAY);jTextField4.setBackground(Color.LIGHT_GRAY);
        jTextField5.setBackground(Color.LIGHT_GRAY);jTextField6.setBackground(Color.LIGHT_GRAY);
        jTextField7.setBackground(Color.LIGHT_GRAY);jTextField8.setBackground(Color.LIGHT_GRAY);




        jTextField1.setBounds(100,5,80,35);
        jTextField2.setBounds(320,5,120,35);
        jTextField3.setBounds(520,5,120,35);
        jTextField4.setBounds(720,5,100,35);
        jTextField5.setBounds(100,65,100,35);
        jTextField6.setBounds(360,65,100,35);
        jTextField7.setBounds(560,65,100,35);
        jTextField8.setBounds(800,65,240,35);

        jScrollPane3.add(jTextField1);jScrollPane3.add(jTextField2);jScrollPane3.add(jTextField3);
        jScrollPane3.add(jTextField4);jScrollPane3.add(jTextField5);jScrollPane3.add(jTextField6);
        jScrollPane3.add(jTextField7);jScrollPane3.add(jTextField8);

        jScrollPane3.add(jLabel1);jScrollPane3.add(jLabel2);jScrollPane3.add(jLabel3);
        jScrollPane3.add(jLabel4);jScrollPane3.add(jLabel5);jScrollPane3.add(jLabel6);
        jScrollPane3.add(jLabel7);jScrollPane3.add(jLabel8);


        majorPanel.add(jScrollPane3);

        //JScrollPane4
        JTextArea introductionArea = new JTextArea();
        introductionArea.setFont(new Font("",0,26));
        introductionArea.setLineWrap(true);
        JScrollPane jScrollPane4 = new JScrollPane(introductionArea);
        //jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane4.setBounds(30,430,800,180);
        majorPanel.add(jScrollPane4);


        MenuButton updateButton = new MenuButton(null,"确认更新",5,5);
        updateButton.setBounds(850,500,140,40);
        updateButton.setBackground(new Color(0,100,200));
        updateButton.setForeground(Color.white);
        majorPanel.add(updateButton);

        //添加表格监听
        movieTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jTextField1.setEditable(true);
                int in = movieTable.getSelectedRow();
                jTextField1.setText( movieTable.getValueAt(in,0).toString());
                jTextField2.setText( movieTable.getValueAt(in,1).toString());
                jTextField3.setText( movieTable.getValueAt(in,2).toString());
                jTextField4.setText( movieTable.getValueAt(in,3).toString());
                jTextField5.setText( movieTable.getValueAt(in,4).toString());
                jTextField6.setText( movieTable.getValueAt(in,5).toString());
                jTextField7.setText( movieTable.getValueAt(in,7).toString());
                jTextField8.setText(movieTable.getValueAt(in,8).toString());
                introductionArea.setText((String) movieTable.getValueAt(in,6));
                jTextField1.setEditable(false);
            }
        });

        //更新按钮监听事件
        updateButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                updateButton.setBackground(new Color(0,100,200));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateButton.setBackground(new Color(0,100,200));
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int in = movieTable.getSelectedRow();
                int id = (Integer.parseInt(jTextField1.getText()));
                String name = jTextField2.getText();
                String category = jTextField3.getText();
                String director = jTextField4.getText();
                String protagonist = jTextField5.getText();
                String language = jTextField6.getText();
                String price = jTextField7.getText();
                String path = jTextField8.getText();
                String introduction = introductionArea.getText();
                int i = Dao_Movie_Information.Movie_Information_Update(id,name,category,director,protagonist,language,introduction,price,path);
                if (i >= 0){
                    JOptionPane.showMessageDialog(null,"更新成功！！");
                    setModeData.queryMovieData(queryDtm,null);
                }

            }
        });
    }

}
