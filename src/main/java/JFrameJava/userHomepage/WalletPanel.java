package JFrameJava.userHomepage;

import Dao.Dao_Member;
import Model.DZ_Member;
import film.util.WalletImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WalletPanel extends JPanel {

    public WalletPanel(JPanel cp, String emailIDImage) {
        //        内容面板上的图片
        ImageIcon money = new ImageIcon("film/Walletimg/Qmoney.png");
        ImageIcon history = new ImageIcon("film/Walletimg/Qhistory.png");
        ImageIcon coupon = new ImageIcon("film/Walletimg/Qcoupon.png");

        JLabel moneyContent = new JLabel();
        JLabel historyContent = new JLabel();
        JLabel couponContent = new JLabel();

        //获取用户信息
        List<DZ_Member> dz_members = Dao_Member.Member_Query(emailIDImage);
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

        WalletImagePanel p1 = new WalletImagePanel(money, moneyContent , 20, 20, cp.getWidth()/4*3, cp.getHeight()/4);
        WalletImagePanel p2 = new WalletImagePanel(history, historyContent, 20, 20, cp.getWidth()/4*3, cp.getHeight()/4);
        WalletImagePanel p3 = new WalletImagePanel(coupon, couponContent ,  20, 20, cp.getWidth()/4*3, cp.getHeight()/4);

        p1.setBounds(cp.getWidth()/8, cp.getHeight()/8*1, cp.getWidth()/4*3, cp.getHeight()/4);
        p2.setBounds(cp.getWidth()/8, cp.getHeight()/8*3, cp.getWidth()/4*3, cp.getHeight()/4);
        p3.setBounds(cp.getWidth()/8, cp.getHeight()/8*5,cp.getWidth()/4*3, cp.getHeight()/4);

        cp.add(p1);
        cp.add(p2);
        cp.add(p3);
    }
}
