package film.util;

import film.util.RoundRectButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuButton extends RoundRectButton {

    /**
     * 菜单按钮
     * 第一个参数是图片，第二个是按钮的文字，第三第四个是圆角的角度
     */

    Font bfont = new Font("宋体", Font.BOLD, 20);
    public MenuButton(ImageIcon icon,String s, int arcWidth, int arcHeight) {
        super(s, arcWidth, arcHeight);

        setIcon(icon);

        setBorder(BorderFactory.createEmptyBorder());
        setSize(220,40);
        //setBounds(20,160,220,40);
        setFont(bfont);
        setForeground(new Color(191,191,191));
        setBackground(new Color(57,61,73));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //loginbt.setForeground(Color.black);
                setBackground(new Color(26,140,168));
            }
            public void mouseExited(MouseEvent e) {
                //loginbt.setForeground(new Color(96,96,96));
                setBackground(new Color(57,61,73));
            }
        });
    }


    /**
     * @author 712f
     * @implNote 可以可以调节颜色
     * @param icon
     * @param s
     * @param arcWidth
     * @param arcHeight
     * @param font
     * @param bgcolor
     * @param enter
     * @param exit
     */
    public MenuButton(ImageIcon icon, String s, int arcWidth, int arcHeight, Color fore, Color bgcolor, Color enter, Color exit) {
        super(s, arcWidth, arcHeight);

        setIcon(icon);

        setBorder(BorderFactory.createEmptyBorder());
        setSize(220,40);
        //setBounds(20,160,220,40);
        setFont(bfont);
        setForeground(fore);
        setBackground(bgcolor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //loginbt.setForeground(Color.black);
                setBackground(enter);
            }
            public void mouseExited(MouseEvent e) {
                //loginbt.setForeground(new Color(96,96,96));
                setBackground(exit);
            }
        });
    }

    /**
     * @author 712f
     * @param icon
     * @param s
     * @param arcWidth
     * @param arcHeight
     * @param fore
     * @param bgcolor
     * @param enter
     * @param exit
     * @param isArmed
     */
    public MenuButton(ImageIcon icon, String s, int arcWidth, int arcHeight, Color fore, Color bgcolor, Color enter, Color exit, Color isArmed) {
        super(s, arcWidth, arcHeight, isArmed);

        setIcon(icon);

        setBorder(BorderFactory.createEmptyBorder());
        setSize(220,40);
        //setBounds(20,160,220,40);
        setFont(bfont);
        setForeground(fore);
        setBackground(bgcolor);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                //loginbt.setForeground(Color.black);
                setBackground(enter);
            }
            public void mouseExited(MouseEvent e) {
                //loginbt.setForeground(new Color(96,96,96));
                setBackground(exit);
            }
        });
    }

}
