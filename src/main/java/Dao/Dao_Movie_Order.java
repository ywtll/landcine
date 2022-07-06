package Dao;

import Model.DZ_Movie_Order;

import java.util.List;
import java.util.Vector;

/**
 * @author 712f
 */
public class Dao_Movie_Order {
    public static int Movie_Order_Insert(Object...args){
        String sql = "INSERT INTO `dizhu`.`dz_movie_order`(`D_User_Id`, `D_Movie_Id`, `D_Order_Time`, `D_Price`, `D_Confirm`) VALUES (?,?,?,?,?)";
        int result = DButil.update(sql,args);
        return result;
    }

    public static int Movie_Order_Update(Object...args){
        String sql = "UPDATE `dizhu`.`dz_movie_order` SET `D_User_Id` = ?, `D_Movie_Id` = ?, `D_Order_Time` = ?, `D_Price` = ?, `D_Confirm` = ? WHERE `D_Id` = ?";
        int result = DButil.update(sql,args);
        return result;
    }

    public static int Movie_Order_Delete(int id){
        String sql = "DELETE FROM `dizhu`.`dz_movie_order` WHERE `A_Id`=?";
        int result = DButil.update(sql,id);
        return result;
    }

    public static Vector<Object> Movie_Order_Query() {
        String sql = "SELECT * FROM `dizhu`.`dz_movie_order`";
        return DButil.query(sql);
    }

    public static List<DZ_Movie_Order> Movie_Order_Query(int Movie_Id) {
        String sql = "SELECT dz_movie_order.D_Seat FROM dz_movie_order WHERE dz_movie_order.D_Movie_Id = ?";
        return DButil.query(sql, DZ_Movie_Order.class, Movie_Id);
    }

    public static void main(String[] args) {

    }

}
