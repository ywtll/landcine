package Dao;

import Model.DZ_Member;

/**
 * ”√ªß±Ì
 * @author 712f
 */
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dao_Member {
    public static int Member_Insert(Object...args){
        String sql = "INSERT INTO `dizhu`.`dz_member`(`U_Name`, `U_Phone`, `U_Password`, `U_Mail`, `U_EndLogin`, `U_Icon`, `U_Vip`, `U_Price`, `U_Coupons`, `U_Level`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        int index = DButil.update(sql,args);
        return index;
    }


    public static int Member_Update(Object...args){
        String sql = "UPDATE `dizhu`.`dz_member` SET `U_Name`=?,`U_Phone`=?,`U_Password`=?,`U_Mail`=?,`U_EndLogin`=?,`U_Icon`=?,`U_Vip`=?,`U_Price`=?,`U_Coupons`=?,`U_Level`=? WHERE `U_Id`=?";
        int index = DButil.update(sql,args);
        return index;
    }

    public static int Member_Update2(Object... args) {
        String sql = "UPDATE dz_member SET U_Name=? WHERE U_Mail = ?";
        int index = DButil.update(sql, args);
        return index;
    }

    public static int Member_Update(String mail,String icon){
        String sql = "UPDATE `dizhu`.`dz_member` SET `U_Icon` = ? WHERE `U_Mail` = ?";
        int index = DButil.update(sql,mail,icon);
        return index;
    }

    public static int Member_Delete(Object...args){
        String sql = "DELETE FROM `dizhu`.`dz_member` WHERE `U_Id`=?";
        int index = DButil.update(sql,args);
        return index;
    }

    public static Vector<Object> Member_Query(){
        String sql = "SELECT * FROM `dizhu`.`dz_member`";
        return DButil.query(sql);
    }
    /**
     * @author ZYM
     * @return
     */
    public static List<DZ_Member> Member_Query(String Mail){
        System.out.println("U_Mail==="+Mail);
        try {
            String sql = "SELECT * FROM dz_member WHERE U_Mail=?";
            return DButil.query(sql,DZ_Member.class,Mail);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @author ZYM
     * @return
     */
    public static List<DZ_Member> Member_Query(Object id){
        System.out.println("U_id==="+id);
        try {
            String sql = "SELECT * FROM dz_member WHERE U_id=?";
            return DButil.query(sql,DZ_Member.class,id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * @author ZYM
     * @return
     */
    public static boolean isEmail(String string) {
        if (string == null) {
            return false;
        }
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author ZYM
     * @return
     */
    public static boolean ismm(String string) {
        if (string == null) {
            return false;
        }
        String pw_pattern = "^(?![A-Za-z]+$)(?![A-Z0-9]+$)(?![a-z0-9]+$)(?![a-z\\W]+$)(?![A-Z\\W]+$)(?![0-9\\W]+$)[a-zA-Z0-9\\W]{6,8}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(pw_pattern);
        m = p.matcher(string);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

}
