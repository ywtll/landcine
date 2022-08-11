package JFrameJava.first;

import Model.DZ_Movie_Hot;
import film.util.RoundPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class HotPanel extends RoundPanel {
    Font titleFont = new Font("宋体", Font.BOLD, 20);

    ArrayList<DZ_Movie_Hot> hotsInfo=new  ArrayList<>();
    JLabel title;

    private  int index=0;

    private Color enterColor=new Color(255,255,255);
    private Color exitColor=new Color(255,255,255);

    public HotPanel(ImageIcon icon, int arcWidth, int arcHeight,String name) {
        super(arcWidth, arcHeight);
        this.setLayout(null);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel imgLabel = new JLabel(icon);
        imgLabel.setBounds(0, 0, 220, 300);
        this.add(imgLabel);
        title = new JLabel(name);
        title.setFont(titleFont);
        title.setBounds(10, 310, 200, 20);

        this.add(imgLabel);
        this.add(title);
    }

    public void setTitleColor(Color enterColor, Color exitColor) {
        title.setForeground(exitColor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                title.setForeground(enterColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                title.setForeground(exitColor);
            }
        });
    }

    public void setBackgroundColor(Color enterColor, Color exitColor) {
        HotPanel h = this;
        this.setBackground(exitColor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                h.setBackground(enterColor);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                h.setBackground(exitColor);
            }
        });
    }


}

