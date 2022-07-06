package film.util;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyOptionPane extends JComponent implements Accessible {

    private JDialog createDialog(JFrame jf) {
        JDialog dialog = new JDialog(jf);
        dialog.setLayout(null);
        dialog.setModal(true);
        dialog.setSize(new Dimension(400,100));
        dialog.setLocation(800,200);
        dialog.getRootPane().setOpaque(false);
        dialog.getContentPane().setBackground(new Color(255,255,255,0));
        //dialog.getLayeredPane().setBackground(new Color(255,255,255,0));
        //dialog.setBorder(BorderFactory.createEmptyBorder());
        dialog.setUndecorated(true);
        dialog.setBackground(new Color(255,255,255,0));
        JLabel la=new JLabel("你确认要删除这条记录吗");
        la.setForeground(new Color(140, 211, 236));
        la.setFont(new Font("宋体",Font.BOLD,20));
        la.setBounds(20,20,250,30);
        dialog.add(la);
        return dialog;
    }

    public static int  showMessage(JFrame jf){

        int[] answer = new int[1];

        JDialog dialog = new MyOptionPane().createDialog(jf);
        JButton ok=new JButton("确认");
        JButton cancel=new JButton("取消");
        ok.setBounds(50,50,60,40);
        cancel.setBounds(120,50,60,40);
        dialog.add(ok);
        dialog.add(cancel);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        dialog.setVisible(true);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer[0] =1;
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer[0] =2;
            }
        });

        return answer[0];

    }

    public static void main(String[] args) {
        JFrame jf=new JFrame();
        jf.setLayout(null);
//        jf.setUndecorated(true);
        jf.setBounds(300,300,400,400);
        JButton jbt=new JButton("弹框");
        jbt.setBounds(30,30,60,40);
        jf.add(jbt);
        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(showMessage(jf)==1){
                    System.out.println("用户选择了确认");
                };
            }
        });
        jf.setVisible(true);
    }
}