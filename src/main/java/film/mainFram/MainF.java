package film.mainFram;

import film.bean.FilmInfo;
import film.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MainF extends JFrame {

    final JPanel lep=new JPanel();
    JPanel rightp=new JPanel();
    Font lfont = new Font("宋体", Font.BOLD, 25);


    public MainF(){
        this.setSize(1280,800);
        this.setBackground(Color.white);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);

    }
    public void init(){
        lep.setLayout(null);
        lep.setPreferredSize(new Dimension(260,800));
        lep.setBackground(Color.black);

        JLabel profile=new JLabel(new ImageIcon("film/img/mof4.png"));
        profile.setBounds(65,25,110,105);

        Panel ltpanel = new Panel();
        ltpanel.setLayout(null);
//        ltpanel.setPreferredSize(new Dimension(260,300));
        ltpanel.setBounds(0,0,260,800);
//        ltpanel.setBackground(Color.CYAN);
        ltpanel.add(profile);


        ImageIcon micon1 = new ImageIcon("film/img/dianz.png");

        MenuButton loginbt=new MenuButton(micon1," 每日精选",20,20);
        loginbt.setLocation(20,160);

        ImageIcon micon2 = new ImageIcon("film/img/vip.png");

        MenuButton vipbt=new MenuButton(micon2," 会员管理",20,20);
        vipbt.setLocation(20,280);

        ImageIcon micon3 = new ImageIcon("film/img/diany.png");

        MenuButton filmbt=new MenuButton(micon3," 电影管理",20,20);
        filmbt.setLocation(20,220);

        ImageIcon micon4 = new ImageIcon("film/img/piao.png");

        MenuButton piaobt=new MenuButton(micon4," 票务管理",20,20);
        piaobt.setLocation(20,340);


        ImageIcon micon5 = new ImageIcon("film/img/guanyu.png");

        MenuButton guanyubt=new MenuButton(micon5," 关于我们",20,20);
        guanyubt.setLocation(20,400);


        ltpanel.add(vipbt);
        ltpanel.add(filmbt);
        ltpanel.add(loginbt);
        ltpanel.add(piaobt);
        ltpanel.add(guanyubt);
        lep.add(ltpanel);


//        lep.add(ltpanel);
//        顶上的工具条
        Panel righttop = new Panel();

        righttop.setPreferredSize(new Dimension(1020,60));
        righttop.setBackground(Color.black);
        righttop.setLayout(null);

        ImageIcon searchicon=new ImageIcon("film/img/search1.png");
        SearchTextField search = new SearchTextField(searchicon,400,40,new Color(31,31,31));
        search.setLocation(200,10);
        righttop.add(search);

//        退出按钮
        MenuButton exit=new MenuButton(new ImageIcon("film/img/exit.png"),"",40,40);
        exit.setBounds(930,10,60,40);
        exit.setBackground(new Color(31,31,31));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        righttop.add(exit);



        //righttop.setLocation(0,0);

        ArrayList<FilmInfo> filmList=new ArrayList<>();
        String[] titles={"阿凡达","头号玩家","复仇者联盟","终结者","钢铁侠","哥斯拉大战金刚"};
        for (int i = 1; i < 7; i++) {
            ImageIcon icon1=new ImageIcon("film/filmImage/"+i+".jpeg");
            FilmInfo film1 = new FilmInfo(titles[i-1], titles[i-1], icon1, "ni hao", 10, 10);
            filmList.add(film1);
        }
//        幻灯片面板
        JPanel bp=new JPanel();

        bp.setLayout(null);
        bp.setBackground(Color.BLACK);
        BannerPanel bannerPanel=new BannerPanel(filmList,690,330);
//        开始播放幻灯片
        bannerPanel.start();
//        幻灯片标题面板
        JPanel titlePanel = bannerPanel.addTitlePanel();
        bp.add(bannerPanel);
        bp.add(titlePanel);


//        内容面板
        JPanel contentp=new JPanel();
        contentp.setPreferredSize(new Dimension(970,410));
        contentp.setLayout(null);
        contentp.setBackground(Color.black);

//        今日热点面板
        JPanel todayp=new JPanel();
        todayp.setLayout(null);
        todayp.setBounds(0,0,970,40);
        todayp.setBackground(Color.BLACK);
        JLabel tol=new JLabel("今日热门");
        tol.setFont(lfont);
        tol.setForeground(Color.white);
        tol.setBounds(30,10,200,30);
        todayp.add(tol);


        JPanel filmp=new JPanel();
        filmp.setBounds(0,40,970,370);
        filmp.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
        filmp.setBackground(Color.black);
        for (int i = 0; i < 4; i++) {
            ImageIcon icon1=new ImageIcon("film/img/qtds.png");
            ImagePanel p=new ImagePanel(icon1,20,20);
            p.setPreferredSize(new Dimension(220,350));
            filmp.add(p);
        }
        contentp.add(todayp);
        contentp.add(filmp);


        rightp.setLayout(new BorderLayout());

        rightp.add(righttop,BorderLayout.NORTH);

        rightp.add(bp);
        rightp.add(contentp,BorderLayout.SOUTH);

        rightp.setBackground(Color.black);

        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lep, rightp);
        content.setBorder(BorderFactory.createEmptyBorder());
        //打开"一触即展"特性
        content.setOneTouchExpandable(true);
        //设置分隔条的大小
        content.setDividerSize(0);
        //设置分割面板根据组件的大小调整最佳布局
        content.resetToPreferredSizes();
        this.add(content);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new MainF().init();
    }

}
