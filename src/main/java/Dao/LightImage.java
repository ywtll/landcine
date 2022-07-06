package Dao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

/**
 * 手动实现改变图片的亮度 修改图片的rgb
 *
 */
public class LightImage extends JFrame
{
    private JPanel panel = null;
    private JSlider slider = null;
    private BufferedImage img = null;
    private int value = 0;

    public LightImage()//要与主类的名字一致，否则会提示错误
    {
        initComponent();
        this.setVisible(true);
    }

    private void initComponent()
    {
        this.setTitle("图片亮度调节");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(550, 450);
        this.setLocationRelativeTo(null);

        initImg(new File("film/Walletimg/Qmoney.png"));

        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };

        this.add(panel, BorderLayout.CENTER);

        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        panel.setLayout(new BorderLayout());
        slider = new JSlider();
        slider.setValue(50);
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(100);
        slider.setMinorTickSpacing(5);
        Dictionary<Integer, Component> labels = new Hashtable<Integer, Component>();
        for (int i = 0; i <= 100; i += 5)
        {
            labels.put(i, new JLabel("" + i));
        }
        slider.setLabelTable(labels);

        value = slider.getValue();

        slider.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent e)
            {
                if (slider.getValueIsAdjusting())
                {
                    int changeValue = slider.getValue() - value;
                    processImg(changeValue);
                    value = slider.getValue();
                }
            }
        });

        Box box = new Box(BoxLayout.X_AXIS);
        box.add(new JLabel("亮度调节："));
        box.add(slider);
        this.add(box, BorderLayout.NORTH);
    }

    private void initImg(File file)
    {
        try
        {
            img = ImageIO.read(file);
        } catch (IOException e)
        {
            JOptionPane.showMessageDialog(null, "图片不存在!");
        }
    }

    /**
     * 处理图片
     *
     */
    private void processImg(int changeValue)
    {
        for (int x = 0; x < img.getWidth(); x++)
        {
            for (int y = 0; y < img.getHeight(); y++)
            {
                // 获取到rgb的组合值
                int rgb = img.getRGB(x, y);
                Color color = new Color(rgb);
                int r = color.getRed() + changeValue;
                int g = color.getGreen() + changeValue;
                int b = color.getBlue() + changeValue;
                if (r > 255)
                {
                    r = 255;
                } else if (r < 0)
                {
                    r = 0;
                }

                if (g > 255)
                {
                    g = 255;
                } else if (g < 0)
                {
                    g = 0;
                }

                if (b > 255)
                {
                    b = 255;
                } else if (b < 0)
                {
                    b = 0;
                }

                color = new Color(r, g, b);
                img.setRGB(x, y, color.getRGB());
            }
        }
        panel.repaint();
    }

    public static void main(String[] args)
    {
        new LightImage();
    }

}