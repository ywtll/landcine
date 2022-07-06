package Dao;

import film.util.DropShadowPanel;
import film.util.MenuButton;
import javafx.scene.layout.Border;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 路径选择
 */
@SuppressWarnings({"all"})
public class Test {

    public void init(){

        JFrame jf = new JFrame();
        jf.setLayout(null);
        jf.setBounds(200,200,900,500);

        JLabel imgJLabel = new JLabel(new ImageIcon("C:\\Users\\86187\\Desktop\\film\\1.png"));
        imgJLabel.setBounds(20,20,500,400);
        imgJLabel.setOpaque(true);

        //创建文件打开对话框
        JFileChooser jFileChooser = new JFileChooser();

        //创建按钮
        MenuButton insert = new MenuButton(null, "打开图片", 5, 5);
        insert.setBounds(600,50,100,35);
        jf.add(insert);

        jf.add(imgJLabel);
        jf.setVisible(true);
        jf.setResizable(false);

        //按钮监听事件
        insert.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = jFileChooser.showOpenDialog(null);
                File selectedFile = jFileChooser.getSelectedFile();
                System.out.println(selectedFile.toString());
                try {
                    BufferedImage img = ImageIO.read(selectedFile);
                    ImageIcon imageIcon = new ImageIcon(img);
                    imgJLabel.setIcon(imageIcon);
                    System.out.println("===============");
                    System.out.println(imgJLabel.toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

    public static void main(String[] args) {
        new Test().init();
    }

}
