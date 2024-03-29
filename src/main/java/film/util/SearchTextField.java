package film.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchTextField extends RoundPanel {

    /**
     * landcine 0.1.0 bata后，已弃用
     */
    private Color colorBg;

    JTextField jf=new JTextField();

    /**
     * landcine 0.1.0 bata后，已弃用
     * @param icon
     * @param width
     * @param height
     * @param bgColor
     */
    public SearchTextField(ImageIcon icon, int width, int height, Color bgColor) {
        super(height, height);
        if (bgColor.equals(new Color(255,255,255))) {
            this.colorBg = new Color(214, 224, 210);
        } else {
            this.colorBg = new Color(51, 50, 50);
        }
        this.setSize(width,height);
        this.setLayout(null);
        this.setBackground(this.colorBg);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(10, 5, height-10, height-10);
        imageLabel.setBackground(this.colorBg);
        jf.setBounds(height,0,width-height-10,height);
        jf.setBorder(BorderFactory.createEmptyBorder());
        jf.setBackground(this.colorBg);
        jf.setText("输入你要查找的影片");
        jf.setFont(new Font("宋体", Font.BOLD, 18));
        if (bgColor.equals(new Color(255,255,255))) {
            jf.setForeground(new Color(0,0,0));
        } else {
            jf.setForeground(new Color(255,255,255));
        }

        jf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jf.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        this.add(imageLabel);
        this.add(jf);

    }

    JLabel imageLabel;
    /**
     * @apiNote 712f
     * @param icon
     * @param width
     * @param height
     */
    public SearchTextField(ImageIcon icon, int width, int height) {
        super(height, height);

        this.setSize(width,height);
        this.setLayout(null);
        /**
         * 背景
         */
//        this.setBackground(this.colorBg);
        this.setBackground(new Color(164, 255, 0));

        imageLabel = new JLabel(icon);
        imageLabel.setBounds(10, 5, height-10, height-10);
        /**
         * 未知
          */
//        imageLabel.setBackground(this.colorBorder);
//        imageLabel.setBackground(new Color(29, 0, 227, 55));

        jf.setBounds(height,0,width-height-10,height);
        jf.setBorder(BorderFactory.createEmptyBorder());
        jf.setBackground(new Color(164, 255, 0));
        jf.setForeground(new Color(168, 35, 35));

        jf.setText("输入你要查找的影片");
        jf.setFont(new Font("宋体", Font.BOLD, 18));

        jf.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                jf.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        this.add(imageLabel);
        this.add(jf);

    }

    public String getJf() {
        return jf.getText();
    }

    public void setJf(String text){
        jf.setText(text);
    }


    public void setColorFont(Color colorFont) {
        jf.setForeground(colorFont);
    }


    public void setColorBg(Color colorBg) {
        this.setBackground(colorBg);
        jf.setBackground(colorBg);
    }

}
