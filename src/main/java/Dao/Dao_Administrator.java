package Dao;

import Model.DZ_Administrator;

import java.util.Vector;

/**
 * @author 712f
 */
public class Dao_Administrator {

    public static int Administrator_Insert(Object...args){
        String sql = "INSERT INTO `dizhu`.`dz_administrator`(`A_Id`, `A_Password`) VALUES (?,?)";
        int result = DButil.update(sql,args);
        return result;
    }

    public static int Administrator_Update(Object...args){
        String sql = "UPDATE `dizhu`.`dz_administrator` SET `A_Password`=? WHERE `A_Id`=?";
        int result = DButil.update(sql,args);
        return result;
    }

    public static int Administrator_Delete(int id){
        String sql = "DELETE FROM `dizhu`.`dz_administrator` WHERE `A_Id`=?";
        int result = DButil.update(sql,id);
        return result;
    }

    public static Vector<Object> Administrator_Query() {
        String sql = "SELECT * FROM `dizhu`.`dz_administrator`";
        return DButil.query(sql);
    }

    public static void main(String[] args) {
        System.out.println(Administrator_Insert(new DZ_Administrator(5,"1243")));
    }
}
