package film.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchTextField extends RoundPanel {

    Color bgColor;

    JTextField jf=new JTextField();
    public SearchTextField(ImageIcon icon, int width, int height, Color bgColor) {
        super(height, height);
        if (bgColor.equals(new Color(255,255,255))) {
            this.bgColor = new Color(214, 224, 210);
        } else {
            this.bgColor = new Color(51, 50, 50);
        }
        this.setSize(width,height);
        this.setLayout(null);
        this.setBackground(this.bgColor);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(10, 5, height-10, height-10);
        imageLabel.setBackground(this.bgColor);
        jf.setBounds(height,0,width-height-10,height);
        jf.setBorder(BorderFactory.createEmptyBorder());
        jf.setBackground(this.bgColor);
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

    public String getJf() {
        return jf.getText();
    }

    public void setJf(String text){
        jf.setText(text);
    }
}
