package film.util;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class MyJDialog extends JDialog {

    private int arcWidth;
    private int arcHeight;

    public MyJDialog(Frame owner, int arcWidth, int arcHeight){
        super(owner);
        this.arcWidth=arcWidth;
        this.arcHeight=arcHeight;

    }


}
