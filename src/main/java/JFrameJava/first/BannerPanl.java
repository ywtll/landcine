package JFrameJava.first;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;

import Dao.Dao_Member;
import JFrameJava.Briefintroduction.Introduction;
import JFrameJava.login.Login;
import Model.DZ_Movie_Information;
import film.util.TitleJbutton;

public class BannerPanl extends JPanel {
    ArrayList<DZ_Movie_Information> filmInfos=new ArrayList<>();

    private  int index=0;

    private Timer timer=new Timer();

    private JLabel jLabel=null;
    private Font bf=new Font("宋体",Font.BOLD,18);

    private BannerPanl that=this;

    private JFrame jf ;

    public BannerPanl(ArrayList<DZ_Movie_Information> films, int width, int height) {
        this.jf=jf;

        setSize(width,height);

        this.filmInfos=films;
        setLayout(null);
        setImage();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                timer.cancel();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                reStartTime();
            }
        });
    }
    //重启定时器
    private void reStartTime() {
        TimerTask tt1=new TimerTask() {
            @Override
            public void run() {
                that.setImage();
            }
        };
        Timer timer1=new Timer();
        timer1.schedule(tt1,1000,5000);
        timer=timer1;
        tt=tt1;
    }

    //设置图片的方法
    private void setImage() {
        if(index>=filmInfos.size()){
            index=0;
        }
        DZ_Movie_Information film = filmInfos.get(index);
        ImageIcon icon=new ImageIcon(film.getY_Movie_Cover());
        jLabel=new JLabel(icon);
        jLabel.setBounds(0,0,800,330);
        removeAll();
        repaint();
        add(jLabel);
        revalidate();
        index++;
    }

    //设置图片的方法
    private void setImage(int id) {
        DZ_Movie_Information film = filmInfos.get(id);
        ImageIcon icon=new ImageIcon(film.getY_Movie_Cover());
        jLabel=new JLabel(icon);
        jLabel.setBounds(0,0,800,330);
        removeAll();
        repaint();
        add(jLabel);
        revalidate();
    }

    public JPanel addTitlePanel(int id) {
        JPanel titlePanel = new JPanel();
        titlePanel.setBounds(800,0,400,410);
        titlePanel.setLayout(null);
//        titlePanel.setBackground(new Color(215, 210, 210, 253));
        titlePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                timer.cancel();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                reStartTime();
            }
        });

        int index=0;
        for(DZ_Movie_Information f:filmInfos){
            index++;
            String title = f.getY_Name();

            // 按钮
            TitleJbutton tb=new TitleJbutton(title);
//            tb.setBackground(new Color(187, 183, 183, 173));
            // 按钮颜色
            tb.setBackground(new Color(155, 92, 92, 173));
            // 设置无边框
            tb.setBorder(BorderFactory.createEmptyBorder());
            // 字体颜色
            tb.setForeground(Color.white);
            tb.setFont(bf);
            tb.setBounds(100,45 * index,200,30);


            tb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    timer.cancel();
//                    tb.setForeground(new Color(231, 0, 0));
                    // 移动到上面的颜色
                    tb.setForeground(new Color(231, 0, 0));
                    that.setImage(f.getY_Id());
                }
                public void mouseExited(MouseEvent e){
//                    reStartTimer();
                    // 移出的颜色
                    tb.setForeground(Color.white);
                }
            });
            tb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Introduction(null,f.getY_Id(), FirstFile.login.getReturnStatus());
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
//    public static void main(String[] args) {
//        JFrame jf=new JFrame("滚动面板");
//
//        DZ_Movie_Information film1 = new DZ_Movie_Information();
//        DZ_Movie_Information film2 = new DZ_Movie_Information("ni hao", "ni hao", icon2, "ni hao", 10, 10);
//        DZ_Movie_Information film3 = new DZ_Movie_Information("ni hao", "ni hao", icon3, "ni hao", 10, 10);
//
//        ArrayList<DZ_Movie_Information> flist=new ArrayList<>();
//        flist.add(film1);
//        flist.add(film2);
//        flist.add(film3);
//
//        BannerPanl bannerPanl = new BannerPanl(flist, 658, 970);
//        bannerPanl.setLocation(0,300);
//        JPanel titlePanel = bannerPanl.addTitlePanel();
//        jf.setLayout(null);
//        jf.setBounds(100,100,658,1270);
//
//        jf.add(bannerPanl);
//        jf.add(titlePanel);
//
//        jf.setVisible(true);
////        jf.setDefaultCloseOperation();
//        bannerPanl.timer.schedule(bannerPanl.tt,1000,5000);
//    }


}
