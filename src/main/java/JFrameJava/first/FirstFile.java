package JFrameJava.first;

import Dao.Dao_Member;
import Dao.Dao_Movie_Hot;
import Dao.Dao_Movie_Information;
import Dao.TheTheme;
import Dao.baiduOcr.FileChooserOCR2;
import Dao.baiduOcr.ScreenShotTest;
import JFrameJava.Briefintroduction.Introduction;
import JFrameJava.login.Login;
import JFrameJava.userHomepage.UserHomepage;
import Model.DZ_Member;
import Model.DZ_Movie_Hot;
import Model.DZ_Movie_Information;
import film.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author Moon
 */
public class FirstFile extends JFrame {

    /**
     * 是否调用OCR
     */
    static final boolean isMyOCR = false;

    String userName= "114514";
    static final int JFRAME_X = 200;
    static final int JFRAME_Y = 100;
    static final int JFRAME_WIDTH = 1200;
    static final int JFRAME_HEIGHT = 840;
    FirstFile firstFile = this;

    static Login login;

    public FirstFile(){
        setTitle("首页");
        setLayout(null);
        setBounds(JFRAME_X, JFRAME_Y, JFRAME_WIDTH, JFRAME_HEIGHT);

        // 初始化
        init();

        // 初始化主题
        theme();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    TheTheme theme = new TheTheme();
    /**
     * @implNote 初始化主题
     */
    private void theme() {
        // 导航栏
        rowPanel.setBackground(theme.getColorBg());

        // OCR
        ocr.setBackground(theme.getColorBg());

        // 搜索栏
        search.setColorBg(theme.getSearchColorImage());
        search.setColorFont(theme.getColorFont());

        content.setBackground(theme.getColorBg());


        bannerPanl.setBackground(theme.getColorBg());

        title.setForeground(theme.getColorFont());

    }


    List<DZ_Member> dz_members;
    JPanel rowPanel;
    JPanel slideshow;
    JPanel content;


    private void init() {
        // 导航栏
        dz_members = Dao_Member.Member_Query(userName);
        rowPanel = new JPanel();
        rowPanel.setLayout(null);
        rowPanel.setBounds(0,0,JFRAME_WIDTH, JFRAME_HEIGHT/10);
        rowPanelPrint();
        add(rowPanel);

        // 轮播图
        slideshow=new JPanel();
        slideshow.setLayout(null);
        slideshow.setBounds(0,JFRAME_HEIGHT/10,JFRAME_WIDTH,JFRAME_HEIGHT/2-JFRAME_HEIGHT/10);
        slideshowPrint();
        add(slideshow);


        // 内容面板
        content = new JPanel();
        content.setLayout(null);
        content.setBounds(0,JFRAME_HEIGHT/10+JFRAME_HEIGHT/2-JFRAME_HEIGHT/10,JFRAME_WIDTH,JFRAME_HEIGHT/2);
        contentPrint();
        add(content);


    }


    /**
     * 内容界面的标题文字
     */
    JLabel title;

    Vector<DZ_Movie_Hot> hotal = Dao_Movie_Hot.getAll();
    private void contentPrint() {
        // 内容标题文字
        title = new JLabel("猜你喜欢");
        title.setBounds(50,5,200,30);
        title.setFont(new Font("宋体", Font.BOLD, 25));
        content.add(title);

        // 内容图片
        for (int i = 0; i < 4; i++) {
            ImageIcon icon = new ImageIcon(hotal.get(i).getR_Movie_cover());
            HotPanel p = new HotPanel(icon, 10, 12, hotal.get(i).getR_Name());
            p.setPreferredSize(new Dimension(210,350));
            p.setBounds(70+i*280,40,210,350);
            p.setTitleColor(Color.BLUE,theme.getColorFont());
            p.setBackgroundColor(Color.LIGHT_GRAY,theme.getColorBg());


            int finalI = i;
            p.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    List<DZ_Movie_Information> dz_movie_informations = Dao_Movie_Information.Movie_Information_Query(finalI);
                    new Introduction(null,dz_movie_informations.get(0).getY_Id(), FirstFile.login.getReturnStatus());
                }
            });

            content.add(p);
        }
    }


    Vector<DZ_Movie_Information> all;
    List<Integer> idList = new ArrayList<>();
    List<String> nameList = new ArrayList<>();
    List<String> Y_Movie_CoverList = new ArrayList<>();
    ArrayList<DZ_Movie_Information> filmList=new ArrayList<>();
    BannerPanl bannerPanl;
    JPanel titlePanel;
    /**
     * 幻灯片面板
     */
    private void slideshowPrint() {
        all = Dao_Movie_Information.getAll();

        all.forEach(e -> {
            // 电影id
            idList.add(e.getY_Id());
            // 电影名字
            nameList.add(e.getY_Name());
            // 电影封面的地址
            Y_Movie_CoverList.add(e.getY_Movie_Cover());
        });

        for (int i = 0; i < idList.size(); i++) {
            String name = nameList.get(i);
            String url = Y_Movie_CoverList.get(i);
            DZ_Movie_Information dz_movie_information = new DZ_Movie_Information(i, name, url);
            filmList.add(dz_movie_information);
        }

//        bannerPanl = new BannerPanl(filmList, 800,380);
        bannerPanl = new BannerPanl(filmList, JFRAME_WIDTH,JFRAME_HEIGHT/2);
        // 开始播放幻灯片
        bannerPanl.start();
        // 幻灯片标题面板
        titlePanel = bannerPanl.addTitlePanel(dz_members.get(0).getU_Id());


        titlePanel.setBackground(new Color(255,255,255,100));

        slideshow.add(titlePanel);
        slideshow.add(bannerPanl);
    }


    ImageIcon imgIcon;
    JLabel logo;
    SearchTextField search;
    JButton ocr;
    ImageIcon imgLogo;
    JLabel icon;
    /**
     * 导航区域
     */
    private void rowPanelPrint() {
        rowPanel.setLayout(null);
        // logo
        imgIcon = new ImageIcon("film/loginimg/logo.png");
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(rowPanel.getHeight(),rowPanel.getHeight(),Image.SCALE_DEFAULT));
        logo = new JLabel(imgIcon);
        logo.setBounds(rowPanel.getHeight()/2,0,rowPanel.getHeight(),rowPanel.getHeight());
        rowPanel.add(logo);


        // 搜索框
        search = new SearchTextField(new ImageIcon("film/img/search1.png"),rowPanel.getWidth()/3,rowPanel.getHeight()/2);
        search.setBounds(rowPanel.getHeight() + rowPanel.getHeight() + rowPanel.getWidth()/8,rowPanel.getHeight()/4, rowPanel.getWidth()/3,rowPanel.getHeight()/2);
        rowPanel.add(search);


        // 语音识别模块




        // 图片转文字模块
        ocr = new JButton(new ImageIcon("film/Walletimg/iconText.png"));
        ocr.setBounds(rowPanel.getHeight()*2 +  rowPanel.getWidth()/3*2,rowPanel.getHeight()/4 ,rowPanel.getHeight()/2,rowPanel.getHeight()/2);
        rowPanel.add(ocr);


        ocr.addActionListener(e -> {
            if (isMyOCR) {
                FileChooserOCR2 OCR = new FileChooserOCR2();
                OCR.setVisible(false);
                new ScreenShotTest(OCR);
                Thread th = new Thread(
                        () -> {
                            while (true) {
                                if ("输出内容。。。".equals(OCR.getOcrText())) {
                                    System.out.println("正在识别中...");
                                } else {
                                    try {
                                        Thread.sleep(200);
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                    String ocrText = OCR.getOcrText();
                                    // 删除全部的/n和空格
                                    ocrText = ocrText.replaceAll("\\s*", "");
                                    search.setJf(ocrText);
                                    break;
                                }
                            }
                        }
                );
                th.start();
            }
        });


        // 头像
        List<DZ_Member> dz_members = Dao_Member.Member_Query(userName);
        imgLogo = new ImageIcon(dz_members.get(0).getU_Icon());
        imgLogo.setImage(imgLogo.getImage().getScaledInstance(rowPanel.getHeight(),rowPanel.getHeight(),Image.SCALE_DEFAULT));
        icon = new JLabel(imgLogo);
        icon.setBounds(rowPanel.getWidth() - rowPanel.getHeight() -rowPanel.getHeight()/2,0,rowPanel.getHeight(),rowPanel.getHeight());
        rowPanel.add(icon);


        // 隐形的按钮
        JButton button=new JButton("");
        button.setBounds(0,0,JFRAME_WIDTH,JFRAME_HEIGHT);
        button.setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
        button.setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
        button.setBorderPainted(false);//不打印边框
        button.setBorder(null);//除去边框
        button.setFocusPainted(false);//除去焦点的框
        button.setContentAreaFilled(false);//除去默认的背景填充
        add(button);

        button.addActionListener(e -> {
            if ((userName.equals("114514"))) {
                login=new Login(FirstFile.this.firstFile);
                String init = login.getReturnStatus();
                userName=init;

                List<DZ_Member> dz_members1 = Dao_Member.Member_Query(userName);

                imgLogo = new ImageIcon(dz_members1.get(0).getU_Icon());
                // 刷新头像
                icon.setIcon(imgLogo);

                button.setVisible(false);
            }
        });

        icon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                firstFile.setVisible(false);
                new UserHomepage(userName, firstFile);
            }
        });


//        //搜索框功能
//        ImageIcon imageIcon=new ImageIcon("film/img/search.png");
//        MenuButton MenuButton=new MenuButton(imageIcon,"",50,50, Color.white,Color.lightGray,Color.lightGray,Color.lightGray);
//        MenuButton.setBounds(search.getX(),search.getY(),search.getHeight(),search.getHeight());
//        MenuButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String name = search.getJf();
//                List<DZ_Movie_Information> dz_movie_informations = Dao_Movie_Information.Movie_Information_Query_name(name);
//                if (dz_movie_informations.size()==0){
//                    JOptionPane.showMessageDialog(firstFile,"未收入该影片");
//                }else {
//                    System.out.println(search);
//                    new Introduction(firstFile,dz_movie_informations.get(0).getY_Id(), FirstFile.login.getReturnStatus());
//                }
//            }
//
//        });
//
//        add(MenuButton);
    }

    public static void main(String[] args) {
        new FirstFile();
    }


}
