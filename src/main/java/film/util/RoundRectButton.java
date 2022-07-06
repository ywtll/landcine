package film.util;


import javax.swing.*;
import java.awt.*;

public class  RoundRectButton extends JButton
{
    private int arcWidth;
    private int arcHeight;

    private Color isArmed = new Color(217,217,217);

    /**
     * @author 712f
     * @param s
     * @param arcWidth
     * @param arcHeight
     */
    public RoundRectButton(String s,int arcWidth,int arcHeight,Color c)
    {
        super(s);
        this.arcWidth=arcWidth;
        this.arcHeight=arcHeight;
        this.isArmed = c;
        setMargin(new Insets(0,0,0,0));//去除文字与按钮的边沿
        setBorder(new RoundBorder());//圆角矩形边界
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);//取消原先画矩形的设置
        //setBorderPainted(false);//会导致按钮没有明显边界
        setFocusPainted(false);//去除文字周围的虚线框
    }


    public RoundRectButton(String s,int arcWidth,int arcHeight)
    {
        super(s);
        this.arcWidth=arcWidth;
        this.arcHeight=arcHeight;
        setMargin(new Insets(0,0,0,0));//去除文字与按钮的边沿
        setBorder(new RoundBorder());//圆角矩形边界
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setContentAreaFilled(false);//取消原先画矩形的设置
        //setBorderPainted(false);//会导致按钮没有明显边界
        setFocusPainted(false);//去除文字周围的虚线框
    }



    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        if (getModel().isArmed()) {
            g2d.setColor(isArmed);//按下后按钮变成绿色
        } else {
            g2d.setColor(getBackground());
        }
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1,arcWidth,arcHeight);//填充圆角矩形边界
        g2d.dispose();
        // 这个调用会画一个标签和焦点矩形。
        super.paintComponent(g);
    }



}

