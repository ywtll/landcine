package film.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImagePanel extends RoundPanel {


    private ImageIcon icon;
    Font titleFont = new Font("宋体", Font.BOLD, 20);

    public ImagePanel(ImageIcon icon,int arcWidth,int arcHeight){
        super(arcWidth,arcHeight);
        this.setLayout(null);
//        this.setBackground(Color.red);

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(210,210,210));
            }
            public void mouseExited(MouseEvent e) {
                setBackground(Color.white);
            }
        });
        JLabel imgLabel=new JLabel(icon);
        imgLabel.setBounds(0,0,220,300);
        //this.setSize(220,350);
        this.add(imgLabel);
        JLabel title=new JLabel("齐天大圣");
        title.setFont(titleFont);
        title.setBounds(10,310,200,20);
        this.add(imgLabel);
        this.add(title);

    }

    public static void main(String[] args) {
        JFrame jFrame=new JFrame();
        jFrame.setLayout(null);
        jFrame.setBounds(100,100,500,600);
        ImageIcon icon1=new ImageIcon("film/img/qtds.png");

        ImagePanel p=new ImagePanel(icon1,20,20);
        ImagePanel p2=new ImagePanel(icon1,20,20);


        p.setLocation(10,10);

        p2.setLocation(240,10);
        jFrame.add(p);
        jFrame.add(p2);
        jFrame.setVisible(true);
    }


}
