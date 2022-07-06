package film.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScearchImagePanel extends RoundPanel {

    public ScearchImagePanel(ImageIcon icon, String name, String introduce, int arcWidth, int arcHeight, int width, int height) {
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



        // 图片
        icon.setImage(icon.getImage().getScaledInstance(height/3*2,height,Image.SCALE_DEFAULT));

        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(0,0,height/3*2,height);
        this.add(imageLabel);

        // 名称
        JLabel nameLabel = new JLabel(name);
        nameLabel.setBounds(imageLabel.getX() + imageLabel.getWidth() + 20, height/10, width/2, height/3);
        nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, nameLabel.getHeight()));
        this.add(nameLabel);

        // 简介
        JLabel introduceLabel = new JLabel(introduce);
        introduceLabel.setBounds(imageLabel.getX() + imageLabel.getWidth() + 20, height/5 + height/3, width, height/3);
        introduceLabel.setFont(new Font("微软雅黑", Font.PLAIN,12));
        this.add(introduceLabel);



    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setBounds(100, 100, 800, 600);
        jf.setLayout(null);

//        ImageIcon icon1 = new ImageIcon("film/img/qtds.png");
//        HistoyImagePanel p = new HistoyImagePanel(icon1, "齐天大声gyu jkhkjh ghj", "2002-10-29", "这是一部非常好看的电影",20, 20, 600, 200);
//        HistoyImagePanel p2 = new HistoyImagePanel(icon1, "齐天大声gyu jkhkjh ghj", "2002-10-29", "这是一部非常好看的电影",20, 20, 600, 200);
//
//        p.setBounds(0, 0, 600, 200);
//
//        JScrollPane jsp = new JScrollPane();
//        jsp.setBounds(0, 0, 800, 600);
//
//        jsp.setViewportView(p);
//        jsp.setViewportView(p2);
//        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//
//        jf.add(jsp);
//
//        jf.add(p);


        //        内容面板
        JPanel contentp=new JPanel();
        contentp.setPreferredSize(new Dimension(970,410));
        contentp.setLayout(null);
        contentp.setBackground(Color.black);

        //        内容面板上的图片
        ImageIcon icon1 = new ImageIcon("film/img/qtds.png");
        ImageIcon icon2 = new ImageIcon("film/img/qtds.png");

        ScearchImagePanel p = new ScearchImagePanel(icon1, "齐天大声gyu jkhkjh ghj", "这是一部非常好看的电影",20, 20, 600, 200);
        ScearchImagePanel p2 = new ScearchImagePanel(icon2, "齐天大声gyu jkhkjh ghj", "这是一部非常好看的电影",20, 20, 600, 200);

        p.setBounds(0, 0, 600, 200);
        p2.setBounds(0, 250, 600, 200);


        contentp.add(p);
        contentp.add(p2);

        //        内容面板上的滚动条
        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(0, 0, 970, 410);
        jsp.setViewportView(contentp);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jf.add(jsp);




        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;



    }


}
