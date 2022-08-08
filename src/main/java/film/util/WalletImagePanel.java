package film.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WalletImagePanel extends RoundPanel {
    private Color enterColor = new Color(210,210,210);
    private Color exitColor = new Color(255, 255, 255);

    public Color getEnterColor() {
        return enterColor;
    }

    public void setEnterColor(Color enterColor) {
        this.enterColor = enterColor;
    }

    public Color getExitColor() {
        return exitColor;
    }

    public void setExitColor(Color exitColor) {
        this.exitColor = exitColor;
    }

    public WalletImagePanel(ImageIcon icon , JLabel content, int arcWidth, int arcHeight, int width, int height) {
        super(arcWidth,arcHeight);
        this.setLayout(null);
//        this.setBackground(Color.red);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(enterColor);
            }
            public void mouseExited(MouseEvent e) {
                setBackground(exitColor);
            }
        });

        // 文字
//        JLabel moneyContent = new JLabel();
//        JLabel historyContent = new JLabel();
//        JLabel couponContent = new JLabel();
//
//        moneyContent.setText("" + "元");
//        historyContent.setText("历史记录");
//        couponContent.setText("" + "张");
//
//        moneyContent.setBounds(0,20,100,50);
//        historyContent.setBounds(0,20,100,50);
//        couponContent.setBounds(0,20,100,50);
//
//        moneyContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
//        historyContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
//        couponContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
//
//        add(moneyContent);
//        add(historyContent);
//        add(couponContent);


        this.add(content);


        // 图片
        icon.setImage(icon.getImage().getScaledInstance(width,height,Image.SCALE_DEFAULT));

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds( 0,0,width,height);
        this.add(imageLabel);






    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setBounds(100, 100, 800, 600);
        jf.setLayout(null);

//        //        内容面板
//        JPanel contentp=new JPanel();
//        contentp.setPreferredSize(new Dimension(970,410));
//        contentp.setBounds(0,0,970,410);
//        contentp.setLayout(null);
//        contentp.setBackground(Color.black);

        //        内容面板上的图片
        ImageIcon icon1 = new ImageIcon("film/Walletimg/Qmoney.png");
        ImageIcon icon2 = new ImageIcon("film/Walletimg/Qhistory.png");


        JLabel moneyContent = new JLabel();
        JLabel historyContent = new JLabel();
        JLabel couponContent = new JLabel();

        moneyContent.setText("" + "元");
        historyContent.setText("历史记录");
        couponContent.setText("" + "张");

        moneyContent.setBounds(0,20,100,50);
        historyContent.setBounds(0,20,100,50);
        couponContent.setBounds(0,20,100,50);

        moneyContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
        historyContent.setFont(new Font("微软雅黑", Font.BOLD, 50));
        couponContent.setFont(new Font("微软雅黑", Font.BOLD, 50));


        WalletImagePanel p = new WalletImagePanel(icon1, moneyContent,20, 20, 600, 200);
        WalletImagePanel p2 = new WalletImagePanel(icon2,  historyContent,20, 20, 600, 200);

        p.setBounds(0, 0, 600, 200);
        p2.setBounds(0, 250, 600, 200);


        jf.add(p);
        jf.add(p2);






        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
    }



}
