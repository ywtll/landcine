package JFrameJava.Briefintroduction;

import Dao.Dao_Movie_Order;
import Model.DZ_Movie_Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Seat extends JDialog {

    JButton[] seat = new JButton[40];

    public Seat(JDialog jd, Object id) {
        super(jd,"座位表",true);
        setBounds(500,200,500,500);
        setLayout(null);
        init();

        setSeatVisible(id);

        setVisible(true);
    }

    private void init() {
        JLabel label=new JLabel("请选择座位");
        label.setBounds(180,10,200,30);
        label.setFont(new Font("黑体",Font.BOLD,20));
        add(label);

        int height=50;
        int width=7;

        int i =0;
        while (true) {
            if (i>=40) {
                break;
            }

            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 5; y++) {
                    seat[i] = new JButton();
                    seat[i].setBounds(x*60+width,y*60+height,50,50);

                    int finalI = i;
                    seat[i].addActionListener(e -> {
                        // 不可选择
                        seat[finalI].setVisible(false);
                    });
                    add(seat[i]);
                }
            }
            i++;
        }
    }

    // 设置座位可否可见
    public void setSeatVisible(Object id) {
        List<DZ_Movie_Order> dz_movie_orders = Dao_Movie_Order.Movie_Order_Query((Integer) id);
        for (DZ_Movie_Order order : dz_movie_orders) {
            seat[order.getD_Seat()].setVisible(false);
        }
    }




//    public static void main(String[] args) {
//        JFrame jf=new JFrame();
//        jf.setBounds(500,200,500,500);
//        jf.setLayout(null);
//
//        JButton jButton = new JButton();
//        jButton.setBounds(100,100,100,100);
//        jButton.addActionListener(e -> {
//            new Seat(jf);
//        });
//        jf.add(jButton);
//
//        jf.setVisible(true);
//
//    }
}
