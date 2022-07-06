package Dao;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Scanner;
import java.util.UUID;

public class SendEmail {


    /**
     * 发送验证码的功能
     */

    // 发件人邮箱
    static final String SENDER_EMAIL = "ywt712@qq.com";
    // 16位验证码密码
    static final String SENDER_PASSWORD = "andrnsqwmipgddih";



    public void sendEmail(String to, String title, String message) {
        //创建一个配置文件并保存
        Properties properties = new Properties();

        properties.setProperty("mail.host","smtp.qq.com");

        properties.setProperty("mail.transport.protocol","smtp");

        properties.setProperty("mail.smtp.auth","true");


        //QQ存在一个特性设置SSL加密
        MailSSLSocketFactory sf = null;
        try {
            sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

        //创建一个session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、16位密码
                return new PasswordAuthentication(SENDER_EMAIL,SENDER_PASSWORD);
            }
        });

        //开启debug模式
        session.setDebug(true);

        //获取连接对象
        Transport transport = null;
        try {
            transport = session.getTransport();

            //连接服务器
            transport.connect("smtp.qq.com",SENDER_EMAIL,SENDER_PASSWORD);

            //创建邮件对象
            MimeMessage mimeMessage = new MimeMessage(session);

            //邮件发送人
            mimeMessage.setFrom(new InternetAddress(SENDER_EMAIL));

            //邮件接收人
            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(to));

            //邮件标题
            mimeMessage.setSubject(title);

            //邮件内容
            mimeMessage.setContent(message,"text/html;charset=UTF-8");

            //发送邮件
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            try {
                transport.close();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 获取uuid 取后六位全都大小
        String uuid = UUID.randomUUID().toString().replace("-","").substring(0,6).toUpperCase();
        String message = "欢迎来到地组影院，您的验证码为：{" + uuid + "}，请在30分钟内使用。";
        new SendEmail().sendEmail("ywt712@qq.com","地组影院",message);

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入验证码：");
        String code = scanner.nextLine();
        while (true) {
            if (code.equals(uuid)) {
                System.out.println("验证码正确");
                break;
            } else {
                System.out.println("验证码错误");
                System.out.println("请输入验证码：");
                code = scanner.nextLine();
            }
        }
    }
}
