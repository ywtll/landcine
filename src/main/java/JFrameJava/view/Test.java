package JFrameJava.view;

import film.util.DropShadowPanel;
import film.util.MenuButton;
import javafx.scene.layout.Border;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@SuppressWarnings({"all"})
public class Test {

    public void init(){

        JFrame jf = new JFrame();
        jf.setLayout(null);
        jf.setBounds(200,200,900,500);

        TextArea intruduction = new TextArea(20, 30);



        JScrollPane jScrollPane = new JScrollPane(intruduction);
        jScrollPane.setBounds(20,20,400,300);

        jf.add(jScrollPane);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new Test().init();
    }

}
