package JFrameJava.userHomepage;

import Dao.Dao_Member;
import Dao.TheTheme;
import Model.DZ_Member;
import film.util.WalletImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WalletPanel extends JPanel {

    JPanel cp;
    String emailIDImage;


    public WalletPanel(JPanel cp, String emailIDImage) {
        this.cp = cp;
        this.emailIDImage = emailIDImage;

        // 初始化
        init();
        // 初始化主题
        theme();
    }


    /**
     * 余额
     */
    JLabel moneyContent;
    ImageIcon money;
    WalletImagePanel p1;
    /**
     * 历史记录
     */
    JLabel historyContent;
    ImageIcon history;
    WalletImagePanel p2;
    /**
     * 优惠券
     */
    JLabel couponContent;
    ImageIcon coupon;
    WalletImagePanel p3;


    List<DZ_Member> dz_members;
    private void init() {
        //        内容面板上的图片
        money = new ImageIcon("film/Walletimg/Qmoney.png");
        history = new ImageIcon("film/Walletimg/Qhistory.png");
        coupon = new ImageIcon("film/Walletimg/Qcoupon.png");

        moneyContent = new JLabel();
        historyContent = new JLabel();
        couponContent = new JLabel();

        //获取用户信息
        dz_members = Dao_Member.Member_Query(emailIDImage);
        moneyContent.setText(dz_members.get(0).getU_Price() + "元");
        historyContent.setText("历史记录");
        couponContent.setText(dz_members.get(0).getU_Coupons() + "张");


        moneyContent.setBounds(400,55,200,50);
        historyContent.setBounds(300,75,200,50);
        couponContent.setBounds(400,75,100,50);

        moneyContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
        moneyContent.setForeground(new Color(244, 179, 187));
        historyContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
        historyContent.setForeground(new Color(192, 247, 105));
        couponContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
        couponContent.setForeground(new Color(205, 237, 253));

        p1 = new WalletImagePanel(money, moneyContent , 20, 20, cp.getWidth()/4*3, cp.getHeight()/4);
        p2 = new WalletImagePanel(history, historyContent, 20, 20, cp.getWidth()/4*3, cp.getHeight()/4);
        p3 = new WalletImagePanel(coupon, couponContent ,  20, 20, cp.getWidth()/4*3, cp.getHeight()/4);

        p1.setBounds(cp.getWidth()/8, cp.getHeight()/8*1, cp.getWidth()/4*3, cp.getHeight()/4);
        p2.setBounds(cp.getWidth()/8, cp.getHeight()/8*3, cp.getWidth()/4*3, cp.getHeight()/4);
        p3.setBounds(cp.getWidth()/8, cp.getHeight()/8*5,cp.getWidth()/4*3, cp.getHeight()/4);

        cp.add(p1);
        cp.add(p2);
        cp.add(p3);
    }

    private void theme() {
        TheTheme theme = new TheTheme();
        p1.setBackground(theme.getColorBg());
        p2.setBackground(theme.getColorBg());
        p3.setBackground(theme.getColorBg());
        p1.setEnterColor(theme.getColorBg());
        p2.setEnterColor(theme.getColorBg());
        p3.setEnterColor(theme.getColorBg());
        p1.setExitColor(theme.getColorBg());
        p2.setExitColor(theme.getColorBg());
        p3.setExitColor(theme.getColorBg());
    }
}
