package JFrameJava.userHomepage;


import Dao.DButil;
import Dao.Dao_Member;
import Dao.TheTheme;
import Dao.baiduOcr.FileChooserOCR2;
import Dao.baiduOcr.ScreenShotTest;
import Model.DZ_Member;
import film.util.MenuButton;
import film.util.SearchTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * @author 712f
 */
public class UserHomepage extends JFrame {

    /**
     * 邮箱id
     */
    String emailIDImage;

    /**
     * 首页传过来的JFrame
     */
    JFrame firstFile;

    /**
     * 自身JFrame
     */
    JFrame jf = this;

    /**
     * 连接数据库
     * @implNote 防止第一次加载的卡顿
     */
    static {
        DButil.getConnection();
    }

    /**
     * 窗体的位置和大小
     */
    static final int JFRAME_X = 200;
    static final int JFRAME_Y = 100;
    static final int JFRAME_WIDTH = 1200;
    static final int JFRAME_HEIGHT = 800;

    public UserHomepage(String emailIDImage, JFrame firstFile) {
        this.firstFile = firstFile;
        setTitle("User Homepage");
        // 将首页的邮箱id传过来
        this.emailIDImage = emailIDImage;
        setLayout(null);
        setBounds(JFRAME_X, JFRAME_Y, JFRAME_WIDTH, JFRAME_HEIGHT);

        // 初始化
        init();

        // 初始化主题
        theme();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * @implNote 初始化主题
     */
    private void theme() {
        TheTheme theme = new TheTheme(1);

        // 导航区域
        rowPanel.setBackground(theme.getColorBg());
        // 头像区域
        iconPanel.setBackground(theme.getColorBg());
        // 内容区域
        contentPanel.setBackground(theme.getColorBg());
        // 列表区域
        listPanel.setBackground(theme.getColorBg());

        // OCR
        ocr.setBackground(theme.getColorBg());

        // 搜索栏
        search.setColorBg(theme.getColorBg());
        search.setColorFont(theme.getColorFont());
    }


    /**
     * 导航区域
     */
    JPanel rowPanel;
    /**
     * 头像区域
     */
    JPanel iconPanel;
    /**
     * 内容
     */
    JPanel contentPanel;
    /**
     * 列表区域
     */
    JPanel listPanel;
    /**
     * init
     */
    private void init() {
        // 导航区域
        rowPanel = new JPanel();
        rowPanel.setLayout(null);
        rowPanel.setBounds(0,0,JFRAME_WIDTH, JFRAME_HEIGHT/10);
        rowPanelPrint();
        add(rowPanel);


        // 头像区域
        iconPanel = new JPanel();
        iconPanel.setBounds(0,JFRAME_HEIGHT/10,JFRAME_WIDTH/4, JFRAME_HEIGHT/4);
        setLayout(null);
        iconPanelPrint();
        add(iconPanel);


        // 内容
        contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(JFRAME_WIDTH/4,JFRAME_HEIGHT/10,JFRAME_WIDTH - JFRAME_WIDTH/4, JFRAME_HEIGHT - JFRAME_HEIGHT/10);
        add(contentPanel);


        // 列表区域
        listPanel = new JPanel();
        rowPanel.setLayout(null);
        listPanel.setBounds(0,JFRAME_HEIGHT/10 + JFRAME_HEIGHT/4,JFRAME_WIDTH/4, JFRAME_HEIGHT - JFRAME_HEIGHT/4- JFRAME_HEIGHT/10);
        listPanelPrint();
        add(listPanel);
    }


    /**
     * 个人信息
     */
    MenuButton information;
    /**
     * 历史记录
     */
    MenuButton history;
    /**
     * 钱包
     */
    MenuButton wallet;
    /**
     * 人工热线
     */
    MenuButton hotline;
    /**
     * 列表区域
     */
    private void listPanelPrint() {
        listPanel.setLayout(null);

        information = new MenuButton(new ImageIcon("film/userHomeImg/information.png"), "个人信息", 0, 0);
        history = new MenuButton(new ImageIcon("film/userHomeImg/history.png"), "历史记录", 0, 0);
        wallet = new MenuButton(new ImageIcon("film/userHomeImg/wallet.png"), "    钱包", 0, 0);
        hotline = new MenuButton(new ImageIcon("film/userHomeImg/hotline.png"), "人工热线", 0, 0);

        // 设置位置和大小
        information.setBounds(0,0,listPanel.getWidth(),listPanel.getHeight()/8);
        history.setBounds(0,listPanel.getHeight()/8,listPanel.getWidth(),listPanel.getHeight()/8);
        wallet.setBounds(0,listPanel.getHeight()/8*2,listPanel.getWidth(),listPanel.getHeight()/8);
        hotline.setBounds(0,listPanel.getHeight()/8*3,listPanel.getWidth(),listPanel.getHeight()/8);

        listPanel.add(information);
        listPanel.add(history);
        listPanel.add(wallet);
        listPanel.add(hotline);

        //列表各个按钮的点击事件
        // 个人信息
        information.addActionListener(e -> {
            contentPanel.removeAll();
            new InformationPanel(contentPanel,emailIDImage,jf);
            contentPanel.repaint();
        });

        // 历史记录
        history.addActionListener(e -> {
            contentPanel.removeAll();
            new HistoryPanel(contentPanel,emailIDImage);
            contentPanel.repaint();
        });

        // 钱包
        wallet.addActionListener(e -> {
            contentPanel.removeAll();
            new WalletPanel(contentPanel,emailIDImage);
            contentPanel.repaint();
        });

        // 人工
        hotline.addActionListener(e -> {
            contentPanel.removeAll();
            new HotlinePanel(contentPanel,emailIDImage);
            contentPanel.repaint();
        });

    }



    /**
     * 头像区域
     */
    private void iconPanelPrint() {
        iconPanel.setLayout(null);
        List<DZ_Member> members = Dao_Member.Member_Query(emailIDImage);
        ImageIcon imgIcon = new ImageIcon(members.get(0).getU_Icon());
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(iconPanel.getHeight(),iconPanel.getHeight(),Image.SCALE_DEFAULT));
        JLabel icon = new JLabel(imgIcon);
        icon.setBounds(iconPanel.getWidth()/2 - 30, 50,50,50);

        JLabel name = new JLabel(members.get(0).getU_Name());
        name.setFont(new Font("微软雅黑", Font.BOLD,20));
        name.setBounds(iconPanel.getWidth()/2 - 30, 100,60,30);

        iconPanel.add(icon);
        iconPanel.add(name);

    }


    ImageIcon imgIcon;
    JLabel logo;
    JPanel type;
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
        ocr.setBounds( rowPanel.getHeight()*2 +  rowPanel.getWidth()/3*2,rowPanel.getHeight()/4 ,rowPanel.getHeight()/2,rowPanel.getHeight()/2);
        rowPanel.add(ocr);


        ocr.addActionListener(e -> {
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
                                System.out.println(ocrText);
                                search.setJf(ocrText);
                                break;
                            }
                        }
                    }
            );
            th.start();

        });




        // 头像
        List<DZ_Member> dz_members = Dao_Member.Member_Query(emailIDImage);
        imgLogo  = new ImageIcon(dz_members.get(0).getU_Icon());
        imgLogo.setImage(imgLogo.getImage().getScaledInstance(rowPanel.getHeight(),rowPanel.getHeight(),Image.SCALE_DEFAULT));
        icon = new JLabel(imgLogo);
        icon.setBounds(rowPanel.getWidth() - rowPanel.getHeight() -rowPanel.getHeight()/2,0,rowPanel.getHeight(),rowPanel.getHeight());
        rowPanel.add(icon);

    }


    public static void main(String[] args) {
        new UserHomepage("981493409@qq.com", null);
    }
}
