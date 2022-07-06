package film.util;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;

public class UIDesignBackground extends JFrame{
    private int width = 500, length = 600;

    /**
     * main用来做测试
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        UIDesignBackground ui = new UIDesignBackground("niu",500, 600, "film/img/guanyu.png");
        ui.setVisible(true);
    }

    /**
     * 构造器,构造一个含有img背景的jframe
     * @param name jframe标题
     * @param width 宽度
     * @param length 长度
     * @param img 图片路径
     * @throws Exception 异常处理
     */
    public UIDesignBackground(String name,int width, int length, String img) throws Exception {
        super(name);
        this.width = width;
        this.length = length;
        this.setSize(width, length);//设置窗口大小
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭方式
        this.setLocationRelativeTo(null);//设置窗口位置
        this.setBackground(img);//设置背景图片

    }

    /**
     * 基本参数设置
     * @throws HeadlessException
     */
    public UIDesignBackground() throws HeadlessException {
        this.setSize(width, length);//设置窗口大小
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//设置关闭方式
        this.setLocationRelativeTo(null);//设置窗口位置
    }

    /**
     * 改变图片大小,适应窗口大小, 再设置为背景图片
     * @param picturePath 图片路径
     */
    public void setBackground(String picturePath) throws Exception {
        //zoomImage(picturePath,"res.png",width,length);//改变传入图片大小
        ImageIcon icon = new ImageIcon(picturePath);//加载图片
        JLabel label = new JLabel(icon);//将图片放入到label中
        System.out.println(icon.getIconHeight());
        System.out.println(icon.getIconWidth());


        label.setBounds(0, 0, icon.getIconWidth(),icon.getIconHeight());//设置label大小
        this.add(label);
        this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));//添加Label到第二层
        JPanel jp = (JPanel) this.getContentPane();//获取顶层容器
        jp.setOpaque(false);//将顶层容器设置为透明
    }

    /**
     *改变图片大小使之适应panel大小
     * @param src 图片地址
     * @param dest 储存地址和图片名称
     * @param w 目标宽度
     * @param h 目标高度
     * @throws Exception
     */
    public void zoomImage(String src, String dest, int w, int h) throws Exception {
        double wr = 0, hr = 0;
        File srcFile = new File(src); //
        File destFile = new File(dest);//目标地址和图片名称
        BufferedImage bufImg = ImageIO.read(srcFile);//读取图片
        wr = w*1.0/bufImg.getWidth();//宽度比例
        hr = h*1.0/bufImg.getHeight();//长度比例

        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr,hr),null);
        Image Itemp = ato.filter(bufImg, null);//处理并得到图片
        ImageIO.write((BufferedImage)Itemp,dest.substring(dest.lastIndexOf(".")+1),destFile);//写入缩放后的图片
    }
}



