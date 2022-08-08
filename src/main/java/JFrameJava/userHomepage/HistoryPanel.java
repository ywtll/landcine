package JFrameJava.userHomepage;

import Dao.Dao_Movie_Information;
import Model.DZ_Movie_Information;
import film.util.HistoyImagePanel;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class HistoryPanel extends JPanel {
    public HistoryPanel(JPanel cp, String emailIDImage) {
        setLayout(null);
        init(cp);
    }

    private static void init(JPanel cp) {
        //        内容面板
        JPanel contentp = new JPanel();
        contentp.setPreferredSize(new Dimension(cp.getWidth(), cp.getHeight()));
        contentp.setLayout(null);


        Vector<DZ_Movie_Information> all = Dao_Movie_Information.getAll();
        System.out.println(all.size());
        for (int i = 0; i < all.size(); i++) {
            String name = all.get(i).getY_Name();
            String time = all.get(i).getY_Show_Time();
            ImageIcon icon = new ImageIcon(all.get(i).getY_Movie_Cover());
            String detailed = all.get(i).getY_Protagonist();


            HistoyImagePanel p = new HistoyImagePanel(icon, name, time, detailed, 20, 20, cp.getWidth() / 2, cp.getWidth() / 2 / 3);


            p.setBounds(cp.getWidth() / 4, i * cp.getWidth() / 2 / 3 + i * 20, cp.getWidth() / 2, cp.getWidth() / 2 / 3);
            contentp.add(p);
        }

        contentp.setBounds(0, 0, cp.getWidth(), cp.getHeight());


        //        内容面板上的滚动条
        JScrollPane jsp = new JScrollPane(contentp);
        jsp.setBounds(0, 0, cp.getWidth(), cp.getHeight());


        cp.add(jsp);
    }
}
