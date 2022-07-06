package film.util;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class RoundPanel extends JPanel {

    ImageIcon icon;

    private static final long serialVersionUID = 1L;

    protected int arcWidth;
    protected int arcHeight;


    public RoundPanel (int arcWidth,int arcHeight) {
        super();
        this.arcWidth=arcWidth;
        this.arcHeight=arcHeight;
//        setOpaque(true);
//        setSize(300, 40);
//        setBackground(Color.white);
    }

    @Override
    public void paint(Graphics g) {

        int fieldX = 0;
        int fieldY = 0;
        int fieldWeight = getSize().width;
        int fieldHeight = getSize().height;
        RoundRectangle2D rect = new RoundRectangle2D.Double(fieldX, fieldY, fieldWeight, fieldHeight, arcWidth, arcHeight);
        g.setClip(rect);
        super.paint(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("圆角面板");
        frame.setLayout(null);
        frame.setBounds(500, 300, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RoundPanel panel = new RoundPanel (10,10);
        panel.setLayout(null);
        JLabel nameLabel = new JLabel(new ImageIcon("film/img/search.png"));
        nameLabel.setForeground(Color.lightGray);
        JTextField jf=new JTextField();
        jf.setBounds(50,0,220,40);
        jf.setBorder(BorderFactory.createEmptyBorder());
        jf.setBackground(Color.white);
        nameLabel.setBounds(10, 5, 30, 30);
//        nameLabel.setAlignmentY(0.1f);
        panel.add(nameLabel);
        panel.add(jf);
        panel.setLocation(100, 100);
        frame.add(panel);
        frame.setVisible(true);
    }
}