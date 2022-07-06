package film.util;

import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

import javax.swing.*;
import java.awt.*;

/**
// * @author lijunming
 * @author 712f
 * @date 2018/7/22 下午6:00
 */
public class BrowserMain extends JPanel {

    private JPanel webBrowserPanel;

    private JWebBrowser webBrowser;

    public BrowserMain(String url) {
        super(new BorderLayout());
        webBrowserPanel = new JPanel(new BorderLayout());
        webBrowser = new JWebBrowser();
        webBrowser.navigate(url);
        webBrowser.setButtonBarVisible(false);
        webBrowser.setMenuBarVisible(false);
        webBrowser.setBarsVisible(false);
        webBrowser.setStatusBarVisible(false);
        webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
        add(webBrowserPanel, BorderLayout.CENTER);
//        //执行Js代码
//        webBrowser.executeJavascript("alert('hello swing')");
    }


    /**
     * 在swing里内嵌浏览器
     * @param url  要访问的url
     * @param title    窗体的标题
     */
    public  static void  openForm(String url,String title){
        UIUtils.setPreferredLookAndFeel();
        NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame(title);
                //设置窗体关闭的时候不关闭应用程序
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new BrowserMain(url), BorderLayout.CENTER);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setLocationByPlatform(true);
                //让窗体可见
                frame.setVisible(true);
                //重置窗体大小
                frame.setResizable(true);
                // 设置窗体的宽度、高度
                frame.setSize(1400, 700);
                // 设置窗体居中显示
                frame.setLocationRelativeTo(frame.getOwner());
            }
        });
        NativeInterface.runEventPump();
    }

    public static void main(String[] args) {
//        openForm("https://www.baidu.com/","hello swing");
        openForm("https://www.4399.com/flash/zmhj.htm","hello swing");
    }
}