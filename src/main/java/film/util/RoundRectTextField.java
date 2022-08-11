package film.util;

import javax.swing.*;
import java.awt.*;

public class RoundRectTextField extends RoundPanel {

    private JTextField jf;

    public RoundRectTextField(ImageIcon icon,int width,int height,Color bgColor) {
        super(height,height);
        this.setSize(width,height);
        this.setLayout(null);
        this.setBackground(bgColor);
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(10, 5, height-10, height-10);
        imageLabel.setBackground(bgColor);
        jf=new JTextField();
        jf.setBounds(height,0,width-height-10,height);
        jf.setBorder(BorderFactory.createEmptyBorder());
        jf.setBackground(bgColor);
        this.add(imageLabel);
        this.add(jf);
    }

    public RoundRectTextField(int width,int height,Color bgColor) {
        super(height/2,height/2);
        this.setBackground(bgColor);
        this.setSize(width,height);
        this.setLayout(null);

        JTextField jf=new JTextField();
        jf.setBounds(height/2,0,width-height,height);
        jf.setBackground(bgColor);
        jf.setBorder(BorderFactory.createEmptyBorder());
        this.add(jf);
    }

    public String getFieldText(){
        return jf.getText();
    }

    public void setText(String text) {
        jf.setText(text);
    }
}
