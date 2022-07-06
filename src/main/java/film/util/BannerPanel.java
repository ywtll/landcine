package film.util;

import film.bean.FilmInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BannerPanel extends JPanel {
    /**
     * 幻灯片面板
     */

    ArrayList<FilmInfo> filmInfos=new ArrayList<>();
    private int index=0;

    private  Timer timer= new Timer();

    private JLabel jLabel=null;

    private Font bf=new Font("宋体", Font.BOLD, 18);

    private BannerPanel that=this;
    public BannerPanel(ArrayList<FilmInfo> films,int width,int heigth){
        setSize(width,heigth);
        this.filmInfos=films;
        setLayout(null);
        setImage();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                timer.cancel();
            }
            public void mouseExited(MouseEvent e){
                reStartTimer();
            }

        });
    }
//    重启定时器
    public void reStartTimer(){
        TimerTask tt1= new TimerTask(){
            @Override
            public void run() {
                that.setImage();
            }
        };
        Timer timer1 = new Timer();

        timer1.schedule(tt1,1000,5000);
        timer=timer1;
        tt=tt1;
    }

    //设置图片的方法
    public void setImage(){
        if(index>=filmInfos.size()){
            index=0;
        }
        FilmInfo film = filmInfos.get(index);
        //ImageIcon icon=new ImageIcon(this.getClass().getResource("//film//filmImage//hmx.jpg"));
        jLabel=new JLabel(film.getImage());

        jLabel.setBounds(0,0,690,330);
        removeAll();
        repaint();
        add(jLabel);
        revalidate();
        index++;
    }
    //设置图片的方法
    public void setImage(ImageIcon image){
        JLabel jLabel=new JLabel(image);
        jLabel.setBounds(0,0,690,330);
        removeAll();
        repaint();
        add(jLabel);
        revalidate();
    }
    public JPanel addTitlePanel() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(690,0,280,330);
        titlePanel.setLayout(null);
        titlePanel.setBackground(new Color(191,191,191,60));
        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                timer.cancel();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                reStartTimer();
            }
        });

        int index=0;
        for(FilmInfo f:filmInfos){
            index++;
            String title = f.getTitle();
            TitleJbutton  tb=new TitleJbutton(title);
            tb.setBackground(new Color(191,191,191,200));
            tb.setBorder(BorderFactory.createEmptyBorder());
            tb.setForeground(Color.white);
            tb.setFont(bf);
            tb.setBounds(40,40*index,200,30);
            tb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    timer.cancel();
                    tb.setForeground(new Color(255, 77, 52));
                    that.setImage(f.getImage());
                }

                public void mouseExited(MouseEvent e){
                    reStartTimer();
                    tb.setForeground(Color.white);
                }
            });
            titlePanel.add(tb);
        }
        return titlePanel;
    }

    TimerTask tt= new TimerTask(){
        @Override
        public void run() {
            that.setImage();
        }
    };

    public void start(){
        timer.schedule(tt,1000,5000);
    }


    public static void main(String[] args) {
        JFrame jf=new JFrame("滚动面板");
        ImageIcon icon1=new ImageIcon("film/filmImage/1.jpeg");
        ImageIcon icon2=new ImageIcon("film/filmImage/2.jpeg");
        ImageIcon icon3=new ImageIcon("film/filmImage/3.jpeg");

        FilmInfo film1 = new FilmInfo("ni hao", "ni hao", icon1, "ni hao", 10, 10);
        FilmInfo film2 = new FilmInfo("ni hao", "ni hao", icon2, "ni hao", 10, 10);
        FilmInfo film3 = new FilmInfo("ni hao", "ni hao", icon3, "ni hao", 10, 10);

        ArrayList<FilmInfo> flist=new ArrayList<>();
        flist.add(film1);
        flist.add(film2);
        flist.add(film3);

        BannerPanel bPanel = new BannerPanel(flist, 658, 970);
        bPanel.setLocation(0,300);
        JPanel titlePanel = bPanel.addTitlePanel();
        jf.setLayout(null);
        jf.setBounds(100,100,658,1270);

        jf.add(bPanel);
        jf.add(titlePanel);

        jf.setVisible(true);
//        jf.setDefaultCloseOperation();



        bPanel.timer.schedule(bPanel.tt,1000,5000);

    }

}
