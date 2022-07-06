package film.mainFram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    JPanel topjp=new JPanel();
    JPanel lep=new JPanel();
    JPanel rightp=new JPanel();

    //创建工具条
    JToolBar jtb = new JToolBar();

    Action book = new AbstractAction("",new ImageIcon("film/img/book.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
//            jta.append("上一曲.\n");
        }
    };
    Action film15 = new AbstractAction("",new ImageIcon("film/img/15film.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
//            jta.append("上一曲.\n");
        }
    };
    Action filejies = new AbstractAction("",new ImageIcon("film/img/filejies.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
//            jta.append("上一曲.\n");
        }
    };

    Action filezhoub = new AbstractAction("",new ImageIcon("film/img/filezhoub.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
//            jta.append("上一曲.\n");
        }
    };
    Action filmp = new AbstractAction("",new ImageIcon("film/img/filmp.png")) {
        @Override
        public void actionPerformed(ActionEvent e) {
//            jta.append("上一曲.\n");
        }
    };



    public MainFrame(String title) {
        super(title);
    }

    public void init(){
        jtb.setBackground(Color.white);
        this.setBounds(200,200,1024,768);
        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, lep, rightp);
        //打开"一触即展"特性
        content.setOneTouchExpandable(true);
        //设置分隔条的大小
        content.setDividerSize(0);
        //设置分割面板根据组件的大小调整最佳布局
        content.resetToPreferredSizes();
        JButton bookBtn = new JButton(book);
        bookBtn.setBackground(Color.white);
//        bookBtn.setBorder(BorderFactory.createEmptyBorder());
        bookBtn.setBackground(new Color(242,242,242));
//        bookBtn.setOpaque(false);
        bookBtn.setPreferredSize(new Dimension(80,40));

        jtb.add(bookBtn);
        jtb.addSeparator();
        jtb.add(film15);
        jtb.addSeparator();
        jtb.add(filejies);
        jtb.addSeparator();
        jtb.add(filezhoub);
        jtb.addSeparator();
        jtb.add(filmp);

        //创建一个垂直的分割面板

//        topjp.add(jtb,FlowLayout.LEFT);

        JSplitPane maint = new JSplitPane(JSplitPane.VERTICAL_SPLIT,jtb,content);
        maint.setDividerSize(1);
        maint.setContinuousLayout(true);
        this.add(maint);

        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}
