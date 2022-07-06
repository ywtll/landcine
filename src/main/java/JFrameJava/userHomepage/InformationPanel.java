package JFrameJava.userHomepage;

import Dao.Dao_Member;
import Model.DZ_Member;
import film.util.BrowserMain;
import film.util.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class InformationPanel extends JPanel {

    // 邮箱id
     String emailIDImage;
     private JFrame firstFile;
    public InformationPanel(JPanel cp, String emailIDImage, JFrame firstFile) {
        cp.setLayout(null);
        this.emailIDImage = emailIDImage;
        this.firstFile = firstFile;

        JPanel up = new JPanel();
        JPanel down = new JPanel();

        up.setBounds(0,0,cp.getWidth(), cp.getHeight()/3);
        down.setBounds(0, cp.getHeight()/3, cp.getWidth(), cp.getHeight()/3*2);

        upPanel(up, down);

        cp.add(up);
        cp.add(down);
    }

    private void upPanel(JPanel up, JPanel down) {
        up.setLayout(null);

        List<DZ_Member> member = Dao_Member.Member_Query(emailIDImage);
        // 更换头像
        ImageIcon imgIcon = new ImageIcon(member.get(0).getU_Icon());
        imgIcon.setImage(imgIcon.getImage().getScaledInstance(up.getHeight(),up.getHeight(),Image.SCALE_DEFAULT));
        JLabel icon = new JLabel(imgIcon);
        icon.setBounds(up.getWidth()/6, up.getHeight()/4, up.getHeight()/4*2, up.getHeight()/8*3);

        MenuButton reAvatar = new MenuButton(null,"更换头像",0,0, new Color(236, 225, 225, 255), new Color(232, 201, 201, 238), new Color(236, 216, 216, 238), new Color(232, 201, 201, 238));
        reAvatar.setBounds(up.getWidth()/6, up.getHeight()/8*3 + up.getHeight()/4, up.getHeight()/4*2, up.getHeight()/8);
        reAvatar.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        reAvatar.setForeground(new Color(0, 0, 0));



        up.add(icon);
        up.add(reAvatar);



        // 昵称名字
        JLabel nameTitle = new JLabel("昵称：");
        JLabel name = new JLabel(member.get(0).getU_Name());
        MenuButton reName = new MenuButton(null,"修改", 0, 0,new Color(255,255,255, 0),new Color(255,255,255, 0),new Color(255,255,255, 0),new Color(255,255,255, 0));
        // 标题
        nameTitle.setBounds(up.getWidth()/6*2, up.getHeight()/4, 120, 30);
        nameTitle.setFont(new Font(null, Font.BOLD,18));
        nameTitle.setForeground(new Color(136, 143, 153));
        // 用户名
        name.setBounds(up.getWidth()/6*2+100, up.getHeight()/4, 80, 30);
        name.setFont(new Font(null, Font.BOLD,18));
        name.setForeground(new Color(31, 31, 31));
        // 修改
        reName.setBounds(up.getWidth()/6*2+180, up.getHeight()/4, 60, 30);
        reName.setFont(new Font(null, Font.BOLD,18));
        reName.setForeground(new Color(77, 220, 179, 229));


        up.add(nameTitle);
        up.add(name);
        up.add(reName);



        // 会员类型
        JLabel memberTitle = new JLabel("会员类型：");
        JLabel memberLabel = new JLabel("超级会员");
        MenuButton reMember = new MenuButton(null,"升级会员", 0, 0,new Color(255,255,255, 0),new Color(255,255,255, 0),new Color(255,255,255, 0),new Color(255,255,255, 0));
        // 会员标题
        memberTitle.setBounds(up.getWidth()/6*2, up.getHeight()/4+50, 120, 30);
        memberTitle.setFont(new Font(null, Font.BOLD,18));
        memberTitle.setForeground(new Color(136, 143, 153));
        // 会员类型
        memberLabel.setBounds(up.getWidth()/6*2+100, up.getHeight()/4+50, 80, 30);
        memberLabel.setFont(new Font(null, Font.BOLD,18));
        memberLabel.setForeground(new Color(31, 31, 31));
        // 升级会员
        reMember.setBounds(up.getWidth()/6*2+180, up.getHeight()/4+50, 100, 30);
        reMember.setFont(new Font(null, Font.BOLD,18));
        reMember.setForeground(new Color(245, 154, 35, 255));


        up.add(memberTitle);
        up.add(memberLabel);
        up.add(reMember);


        // ID
        JLabel idTitle = new JLabel("ID:");
        JLabel id = new JLabel("123456");
        // 标题
        idTitle.setBounds(up.getWidth()/6*2, up.getHeight()/4+100, 120, 30);
        idTitle.setFont(new Font(null, Font.BOLD,18));
        idTitle.setForeground(new Color(136, 143, 153));
        // ID
        id.setBounds(up.getWidth()/6*2+100, up.getHeight()/4+100, 80, 30);
        id.setFont(new Font(null, Font.BOLD,18));
        id.setForeground(new Color(31, 31, 31));


        up.add(idTitle);
        up.add(id);


        reAvatar.addActionListener(e -> {
            down.removeAll();
            reAvatarControls(down);
            down.repaint();
        });

        reName.addActionListener(e -> {
            down.removeAll();
            reNameControls(down);
            down.repaint();
        });

        reMember.addActionListener(e -> {
            down.removeAll();
            reMemberControls(down);
            down.repaint();
        });

    }

    private void reAvatarControls(JPanel down) {
        down.setLayout(null);

        // 更换头像
        List<DZ_Member> members = Dao_Member.Member_Query(emailIDImage);
        ImageIcon imgIcon = new ImageIcon(members.get(0).getU_Icon());

        // 更换头像
        String path = members.get(0).getU_Icon();

        imgIcon.setImage(imgIcon.getImage().getScaledInstance(down.getHeight(),down.getHeight(),Image.SCALE_DEFAULT));
        JLabel icon = new JLabel(imgIcon);
        icon.setBounds(down.getWidth()/7, 0, down.getWidth()/2 - down.getWidth()/7, down.getWidth()/2 - down.getWidth()/7);

        down.add(icon);

        // 确定按钮
        MenuButton confirmButton = new MenuButton(null,"确定",30,30,new Color(215, 234, 223, 255),new Color(29, 225, 107, 255),new Color(60, 162, 105, 255),new Color(29, 225, 107, 255));
        confirmButton.setBounds(down.getWidth()/7*5, down.getHeight()/5/2, down.getWidth()/10*3/2, down.getHeight()/5/2);

        down.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Dao_Member.Member_Update(emailIDImage,path);
            }
        });

        down.add(confirmButton);

    }

    private void reMemberControls(JPanel down) {
        down.setLayout(null);

        MenuButton becomeMember = new MenuButton(null,"成为会员",20,20,new Color(255, 255, 255),new Color(222, 133, 17),new Color(255, 143, 0),new Color(222, 133, 17));
        becomeMember.setBounds(down.getWidth()/3,down.getHeight()/20,down.getWidth()/3,down.getHeight()/3);
        becomeMember.setFont(new Font("微软雅黑", Font.BOLD, 20));

        down.add(becomeMember);


        MenuButton more = new MenuButton(null,"了解更多",0,0,new Color(13, 39, 204, 234),new Color(0,0,0,0),new Color(0,0,0,0),new Color(0,0,0,0));
        more.setBounds(down.getWidth()/100*39,down.getHeight()/20+down.getHeight()/3,200,50);
        down.add(more);


        more.addActionListener(e -> {
            BrowserMain.openForm("https://zhidao.baidu.com/question/1436549356974827979.html", "成为会员的好处");
        });
    }

    private void reNameControls(JPanel down) {
        down.setLayout(null);

        JLabel title = new JLabel("请输入新的昵称：");
        title.setBounds(down.getWidth() / 4, down.getHeight() / 10, down.getWidth() / 2, 100);
        title.setFont(new Font("微软雅黑", Font.BOLD, 30));

        down.add(title);


        JTextField content = new JTextField();
        content.setBounds(down.getWidth() / 3, down.getHeight() / 10 + 100, down.getWidth() / 2, 75);
        content.setFont(new Font("微软雅黑", Font.BOLD, 30));

        down.add(content);

        // 确定按钮
        MenuButton confirmButton = new MenuButton(null, "确定", 30, 30, new Color(215, 234, 223, 255), new Color(29, 225, 107, 255), new Color(60, 162, 105, 255), new Color(29, 225, 107, 255));
        confirmButton.setBounds(down.getWidth() / 10 * 3, down.getHeight() / 10 * 6, down.getWidth() / 10 * 3 / 2, down.getHeight() / 5 / 2);

        confirmButton.addActionListener(e -> {
            String text=content.getText();
            int i = Dao_Member.Member_Update2(text,emailIDImage);
            System.out.println(i);
            if(i==1){
                JOptionPane.showMessageDialog(null,"修改成功");
                // 更新窗体
                firstFile.removeAll();
firstFileControls(firstFile);
                firstFile.repaint();

            }else{
                JOptionPane.showMessageDialog(null,"修改失败");
            }
        });

        down.add(confirmButton);
    }

    private void firstFileControls(JFrame firstFile) {
        firstFile.setLayout(null);

        // 头像
        List<DZ_Member> members = Dao_Member.Member_Query(emailIDImage);
        ImageIcon imgIcon = new ImageIcon(members.get(0).getU_Icon());



    }


}
