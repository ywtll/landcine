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
    private  int index=0;

    public HotPanel(ImageIcon icon, int arcWidth, int arcHeight,String name) {
        super(arcWidth, arcHeight);
        this.setLayout(null);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(210, 210, 210));
            }

            public void mouseExited(MouseEvent e) {
                setBackground(new Color(245, 243, 243, 255));
            }
        });

        JLabel imgLabel = new JLabel(icon);
        imgLabel.setBounds(0, 0, 220, 300);
        //this.setSize(220,350);
        this.add(imgLabel);
        JLabel title = new JLabel(name);
        title.setFont(titleFont);
        title.setBounds(10, 310, 200, 20);
        this.add(imgLabel);
        this.add(title);
    }
}

