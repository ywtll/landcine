package JFrameJava.userHomepage;


import Dao.DButil;
import Dao.Dao_Member;
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

    // 邮箱id
    static String emailIDImage;
    JFrame firstFile;
    JFrame jf = this;


    Color darkColor;
    Color lightColor;

    static {
        DButil.getConnection();
    }

    static final int JFRAME_X = 200;
    static final int JFRAME_Y = 100;
    static final int JFRAME_WIDTH = 1200;
    static final int JFRAME_HEIGHT = 800;

    public UserHomepage(String emailIDImage, JFrame firstFile) {
        this.firstFile = firstFile;
        setTitle("User Homepage");
        UserHomepage.emailIDImage = emailIDImage;
        setLayout(null);
        setBounds(JFRAME_X, JFRAME_Y, JFRAME_WIDTH, JFRAME_HEIGHT);

        init();


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * 主题
     */
    private Color bgColor() {
        darkColor = new Color(0,0,0);
        lightColor = new Color(255,255,255);

        return lightColor;
    }

    /**
     * init
     */
    private void init() {
        // 导航区域
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(null);
        rowPanel.setBounds(0,0,JFRAME_WIDTH, JFRAME_HEIGHT/10);
        rowPanelPrint(rowPanel);
        add(rowPanel);


        // 头像区域
        JPanel iconPanel = new JPanel();
        iconPanel.setBounds(0,JFRAME_HEIGHT/10,JFRAME_WIDTH/4, JFRAME_HEIGHT/4);
        setLayout(null);
        iconPanelPrint(iconPanel);
        add(iconPanel);


        // 内容
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(null);
        contentPanel.setBounds(JFRAME_WIDTH/4,JFRAME_HEIGHT/10,JFRAME_WIDTH - JFRAME_WIDTH/4, JFRAME_HEIGHT - JFRAME_HEIGHT/10);
        add(contentPanel);


        // 列表区域
        JPanel listPanel = new JPanel();
        rowPanel.setLayout(null);
        listPanel.setBounds(0,JFRAME_HEIGHT/10 + JFRAME_HEIGHT/4,JFRAME_WIDTH/4, JFRAME_HEIGHT - JFRAME_HEIGHT/4- JFRAME_HEIGHT/10);
        listPanelPrint(listPanel,contentPanel);
        add(listPanel);


        rowPanel.setBackground(bgColor());
        iconPanel.setBackground(bgColor());
        contentPanel.setBackground(bgColor());
        listPanel.setBackground(bgColor());
    }

    /**
     * 列表区域
     * @param lp
     * @param cp
     */
    private void listPanelPrint(JPanel lp, JPanel cp) {
        lp.setLayout(null);

        MenuButton information = new MenuButton(new ImageIcon("film/userHomeImg/information.png"), "个人信息", 0, 0);
        MenuButton history = new MenuButton(new ImageIcon("film/userHomeImg/history.png"), "历史记录", 0, 0);
        MenuButton wallet = new MenuButton(new ImageIcon("film/userHomeImg/wallet.png"), "    钱包", 0, 0);
        MenuButton hotline = new MenuButton(new ImageIcon("film/userHomeImg/hotline.png"), "人工热线", 0, 0);

        information.setBounds(0,0,lp.getWidth(),lp.getHeight()/8);
        history.setBounds(0,lp.getHeight()/8,lp.getWidth(),lp.getHeight()/8);
        wallet.setBounds(0,lp.getHeight()/8*2,lp.getWidth(),lp.getHeight()/8);
        hotline.setBounds(0,lp.getHeight()/8*3,lp.getWidth(),lp.getHeight()/8);

        lp.add(information);
        lp.add(history);
        lp.add(wallet);
        lp.add(hotline);

        // 个人信息
        information.addActionListener(e -> {
            cp.removeAll();
            new InformationPanel(cp,emailIDImage,jf);
            cp.repaint();
        });

        // 历史记录
        history.addActionListener(e -> {
            cp.removeAll();
            new HistoryPanel(cp,emailIDImage);
            cp.repaint();
        });

        // 钱包
        wallet.addActionListener(e -> {
            cp.removeAll();
            new WalletPanel(cp,emailIDImage);
            cp.repaint();
        });

        // 人工
        hotline.addActionListener(e -> {
            cp.removeAll();
            new HotlinePanel(cp,emailIDImage);
            cp.repaint();
        });


        lp.setBackground(bgColor());
        cp.setBackground(bgColor());

    }

    /**
     * 头像区域
     * @param ip
     */
    private void iconPanelPrint(JPanel ip) {
        ip.setLayout(null);
        List<DZ_Member> members = Dao_Member.Member_Query(emailIDImage);
        ImageIcon imgIcon = new ImageIcon(members.get(0).getU_Icon());
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(ip.getHeight(),ip.getHeight(),Image.SCALE_DEFAULT));
        JLabel icon = new JLabel(imgIcon);
        icon.setBounds(ip.getWidth()/2 - 30, 50,50,50);

        JLabel name = new JLabel(members.get(0).getU_Name());
        name.setFont(new Font("微软雅黑", Font.BOLD,20));
        name.setBounds(ip.getWidth()/2 - 30, 100,60,30);

        ip.add(icon);
        ip.add(name);

    }

    /**
     * 导航区域
     * @param rp
     */
    private void rowPanelPrint(JPanel rp) {
        rp.setLayout(null);
        // logo
        ImageIcon imgIcon = new ImageIcon("film/loginimg/logo.png");
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(rp.getHeight(),rp.getHeight(),Image.SCALE_DEFAULT));
        JLabel logo = new JLabel(imgIcon);
        logo.setBounds(rp.getHeight()/2,0,rp.getHeight(),rp.getHeight());
        rp.add(logo);

        // 分类
        JPanel type = new JPanel();
        type.setBounds(rp.getHeight() + rp.getHeight(),rp.getHeight()/4,rp.getWidth()/8, rp.getHeight());
        JLabel frontPage = new JLabel("首页");
        frontPage.setFont(new Font("微软雅黑", Font.BOLD, type.getHeight()/3));
        type.add(frontPage);

        type.setBackground(bgColor());

        rp.add(type);

        type.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                firstFile.setVisible(true);
                jf.dispose();
            }
        });




        // 搜索框
        SearchTextField search = new SearchTextField(new ImageIcon("film/img/search1.png"),rp.getWidth()/3,rp.getHeight()/2,bgColor());
        search.setBounds(rp.getHeight() + rp.getHeight() + rp.getWidth()/8,rp.getHeight()/4, rp.getWidth()/3,rp.getHeight()/2);
        rp.add(search);


        // 语音识别模块




        // 图片转文字模块

        JButton ocr = new JButton(new ImageIcon("film/Walletimg/iconText.png"));
        ocr.setBackground(bgColor());
        ocr.setBounds( rp.getHeight()*2 +  rp.getWidth()/3*2,rp.getHeight()/4 ,rp.getHeight()/2,rp.getHeight()/2);
        rp.add(ocr);


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
        ImageIcon imgLogo  = new ImageIcon(dz_members.get(0).getU_Icon());
        imgLogo.setImage(imgLogo.getImage().getScaledInstance(rp.getHeight(),rp.getHeight(),Image.SCALE_DEFAULT));
        JLabel icon = new JLabel(imgLogo);
        icon.setBounds(rp.getWidth() - rp.getHeight() -rp.getHeight()/2,0,rp.getHeight(),rp.getHeight());
        rp.add(icon);

    }


    public static void main(String[] args) {
        new UserHomepage("981493409@qq.com", null);
    }
}
