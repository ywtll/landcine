package JFrameJava.first;

import Dao.Dao_Member;
import Dao.Dao_Movie_Hot;
import Dao.Dao_Movie_Information;
import Dao.baiduOcr.FileChooserOCR2;
import Dao.baiduOcr.ScreenShotTest;
import JFrameJava.Briefintroduction.Introduction;
import JFrameJava.bean.FilmInfo;
import JFrameJava.login.Login;
import JFrameJava.userHomepage.UserHomepage;
import Model.DZ_Member;
import Model.DZ_Movie_Hot;
import Model.DZ_Movie_Information;
import film.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    static final int JFRAME_HEIGHT = 820;
    FirstFile firstFile = this;

    static Login login;

    ImageIcon imgLogo;


    Color darkColor;
    Color lightColor;

    Font lfont = new Font("宋体", Font.BOLD, 25);

    public FirstFile(){
        setTitle("首页");
        setLayout(null);
        setBounds(JFRAME_X, JFRAME_Y, JFRAME_WIDTH, JFRAME_HEIGHT);

        init();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    List<DZ_Member> dz_members;
    JPanel rowPanel;

    JPanel slideshow;


    private void init() {
        dz_members = Dao_Member.Member_Query(userName);
        rowPanel = new JPanel();
        rowPanel.setLayout(null);
        rowPanel.setBounds(0,0,JFRAME_WIDTH, JFRAME_HEIGHT/10);
        rowPanel.setBackground(bgColor());
        rowPanelPrint();
        add(rowPanel);




        //幻灯片面板
        slideshow=new JPanel();
        slideshow.setLayout(null);
        slideshow.setBackground(lightColor);
        slideshow.setBounds(0,80,1200,330);
        slideshowPrint();
        add(slideshow);




        //内容面板
        JPanel contentp=new JPanel();
        contentp.setPreferredSize(new Dimension(970,500));
        contentp.setLayout(null);
        contentp.setBackground(Color.CYAN);

        JPanel todayp=new JPanel();
        todayp.setLayout(null);
        todayp.setBounds(0,400,1200,40);
        todayp.setBackground(lightColor);
        JLabel tol=new JLabel("今日热门");
        tol.setFont(lfont);
        tol.setForeground(Color.black);
        tol.setBounds(30,10,200,30);

        tol.setBackground(bgColor());
        todayp.add(tol);

        JPanel filmp=new JPanel();
        filmp.setBounds(0,440,1200,360);
        filmp.setLayout(new FlowLayout(FlowLayout.LEFT,70,0));
        filmp.setBackground(lightColor);


        for (int i = 0; i < 4; i++) {
            ImageIcon icon1=new ImageIcon(hotall.get(i).getR_Movie_cover());
            HotPanel p=new HotPanel(icon1,10,12,hotall.get(i).getR_Name());
            p.setPreferredSize(new Dimension(210,350));
            int finalI = i;
            List<DZ_Movie_Information> dz_movie_informations = Dao_Movie_Information.Movie_Information_Query(finalI);
            p.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println(finalI);
                    new Introduction(null,dz_movie_informations.get(0).getY_Id(), FirstFile.login.getReturnStatus());
                }
            });

            filmp.setBackground(bgColor());
            filmp.add(p);
        }


        contentp.add(todayp);
        contentp.add(filmp);
        todayp.setBackground(bgColor());
        filmp.setBackground(bgColor());

        add(todayp);
        add(filmp);
    }

    Vector<DZ_Movie_Information> all;
    Vector<DZ_Movie_Hot> hotall;
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
        hotall = Dao_Movie_Hot.getAll();

        //获取电影id
        for (DZ_Movie_Information e : all) {
            idList.add(e.getY_Id());
        }

        //获取电影名字
        for (DZ_Movie_Information e : all) {
            nameList.add(e.getY_Name());
        }
        //获取电影封面地址
        for (DZ_Movie_Information e : all) {
            Y_Movie_CoverList.add(e.getY_Movie_Cover());
        }

        for (int i = 0; i <idList.size() ; i++) {
            String name = nameList.get(i);
            String movie_cover = Y_Movie_CoverList.get(i);
            DZ_Movie_Information film1 = new DZ_Movie_Information(i,name,movie_cover);
            filmList.add(film1);
        }


        bannerPanl=new BannerPanl(filmList,800,330);
        //开始播放幻灯片
        bannerPanl.start();
        //幻灯片标题面板
        titlePanel =bannerPanl.addTitlePanel(dz_members.get(0).getU_Id());

        bannerPanl.setBackground(bgColor());
        titlePanel.setBackground(bgColor());
        slideshow.setBackground(bgColor());


        slideshow.add(bannerPanl);
        slideshow.add(titlePanel);

    }

    /**
     * 导航
     */
    private void rowPanelPrint() {
        rowPanel.setLayout(null);
        rowPanel.setBackground(lightColor);
        // logo
        ImageIcon imgIcon = new ImageIcon("film/loginimg/logo.png");
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(rowPanel.getHeight(),rowPanel.getHeight(), Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(imgIcon);
        logo.setBounds(rowPanel.getHeight()/2,0,rowPanel.getHeight(),rowPanel.getHeight());
        rowPanel.add(logo);

        // 分类
        JPanel type = new JPanel();
        type.setBounds(rowPanel.getHeight() + rowPanel.getHeight(),rowPanel.getHeight()/4,rowPanel.getWidth()/8, rowPanel.getHeight());
        JLabel frontPage = new JLabel("首页");
        frontPage.setFont(new Font("微软雅黑", Font.BOLD, type.getHeight()/3));
        type.add(frontPage);

        type.setBackground(bgColor());
        rowPanel.add(type);


        // 搜索框
        SearchTextField search = new SearchTextField(new ImageIcon("film/img/search1.png"),rowPanel.getWidth()/3,rowPanel.getHeight()/2,bgColor());
        search.setBounds(rowPanel.getHeight() + rowPanel.getHeight() + rowPanel.getWidth()/8,rowPanel.getHeight()/4, rowPanel.getWidth()/3,rowPanel.getHeight()/2);
        rowPanel.add(search);


        // 语音识别模块




        // 图片转文字模块

        JButton ocr = new JButton(new ImageIcon("film/Walletimg/iconText.png"));
        ocr.setBackground(lightColor);
        ocr.setBounds( rowPanel.getHeight()*2 +  rowPanel.getWidth()/3*2,rowPanel.getHeight()/4 ,rowPanel.getHeight()/2,rowPanel.getHeight()/2);
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
        imgLogo.setImage(imgLogo.getImage().getScaledInstance(rowPanel.getHeight()/2,rowPanel.getHeight()/2,Image.SCALE_DEFAULT));
        JLabel icon = new JLabel(imgLogo);
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


        //搜索框功能
        ImageIcon imageIcon=new ImageIcon("film/img/search.png");
        MenuButton MenuButton=new MenuButton(imageIcon,"",50,50, Color.white,Color.lightGray,Color.lightGray,Color.lightGray);
        MenuButton.setBounds(search.getX(),search.getY(),search.getHeight(),search.getHeight());
        MenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = search.getJf();
                List<DZ_Movie_Information> dz_movie_informations = Dao_Movie_Information.Movie_Information_Query_name(name);
                if (dz_movie_informations.size()==0){
                    JOptionPane.showMessageDialog(firstFile,"未收入该影片");
                }else {
                    System.out.println(search);
                    new Introduction(firstFile,dz_movie_informations.get(0).getY_Id(), FirstFile.login.getReturnStatus());
                }
            }

        });
        add(MenuButton);

        //        内容面板
        JPanel contentp = new JPanel();
        contentp.setPreferredSize(new Dimension(100,100));
        contentp.setLayout(null);


    }
    /**
     * 主题
     */
    private Color bgColor() {
        darkColor = new Color(0,0,0);
        lightColor = new Color(255,255,255);

        return lightColor;
    }

    public static void main(String[] args) {
        new FirstFile();
    }


}
