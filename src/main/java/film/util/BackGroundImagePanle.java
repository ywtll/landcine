package film.util;

import javax.swing.*;
import java.awt.*;

public class BackGroundImagePanle extends JPanel {
    /**
     * 背景图片面板
     * 可以在背景上设置图片
     */

    private ImageIcon icon;

    /*public ImageIcon getIcon() {
        return icon;
    }*/
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    private int x = 0;
    private int y = 0;
    public BackGroundImagePanle(String url) {
        // TODO Auto-generated constructor stub
        icon = new ImageIcon(this.getClass().getResource(url));
    }
//    把图片画在背景上
    public void paintComponent(Graphics g) {
        g.drawImage(icon.getImage(), x, y, getSize().width,getSize().height, this);// 图片会自动缩放
    }
//示例代码
    public static void main(String[] args) {
        BackGroundImagePanle backGroundImagePanle = new BackGroundImagePanle("/film/img/fcwm.jpg");
        JFrame ddd = new JFrame("ddd");
        JButton jButton=new JButton("你好得的对方是否");
        backGroundImagePanle.add(jButton);
        ddd.setLocationRelativeTo(null);
        ddd.setBounds(200,200,500,600);
        ddd.add(backGroundImagePanle);
        ddd.setVisible(true);
    }
}
