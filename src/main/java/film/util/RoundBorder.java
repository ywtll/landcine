package film.util;


import javax.swing.border.Border;
import java.awt.*;

public class RoundBorder implements Border {
    public Insets getBorderInsets(Component c) {
        return new Insets(0,0,0,0);
    }
    public boolean isBorderOpaque() {
        return false;
    }
    public void paintBorder(Component c, Graphics g, int x, int y,
                            int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();

        //使用黑色在组件的外边缘绘制一个圆角矩形
        g2d.setColor(Color.BLACK);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawRoundRect(0, 0, c.getWidth()-1, c.getHeight()-1, 50, 50);
        g2d.dispose();
    }
}

