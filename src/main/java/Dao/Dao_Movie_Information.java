package Dao;

import Model.DZ_Movie_Information;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

/**
 * @author 712f
 */
public class Dao_Movie_Information {
    public static int Movie_Information_Insert(Object...args){
        String sql = "INSERT INTO `dizhu`.`dz_movie_information`(`Y_Name`, `Y_Category`,`Y_Protagonist`,`Y_Director`, `Y_Language`,`Y_Price`,`Y_Synopsis`, `Y_Movie_Cover`,  `Y_Msdata`) VALUES (?,?,?,?,?,?,?,?,?)";
        int result = DButil.update(sql,args);
        return result;
    }

    public static int Movie_Information_Update(int id,Object...args){
        String sql = "UPDATE `dizhu`.`dz_movie_information` SET `Y_Name` = ?,  `Y_Category` = ?,`Y_Director` = ?,`Y_Protagonist` = ?, `Y_Language` = ?, `Y_Synopsis` = ?, `Y_Price` = ?, `Y_Movie_Cover` = ? WHERE `Y_Id` = "+id+"";
        int result = DButil.update(sql,args);
        return result;
    }

    public static int Movie_Information_Delete(int id){
        String sql = "DELETE FROM `dizhu`.`dz_movie_information` WHERE `A_Id`=?";
        int result = DButil.update(sql,id);
        return result;
    }

    public static Vector<Object> Movie_Information_Query() {
        String sql = "SELECT * FROM `dizhu`.`dz_movie_information`";
        return DButil.query(sql);
    }

    /**
     * @author ZYM
     */
    public static List<DZ_Movie_Information> Movie_Information_Query(Object id) {
        System.out.println("id==="+id);
        String sql = "SELECT * FROM dz_movie_information WHERE Y_Id=?";
        return DButil.query(sql, DZ_Movie_Information.class,id);
    }

    /**
     * @author zlf
     */
    public static Vector<Object> Movie_Information_Query(String n) {
        String sql = "SELECT `Y_Name`,`Y_Category`,`Y_Protagonist`,`Y_Director`,`Y_Language`,`Y_Synopsis`, FROM `dizhu`.`dz_movie_information` where Y_Name like '%"+n+"%'";
        return DButil.query(sql);
    }

    /**
     * @author MOON
     */
    public static Vector<DZ_Movie_Information> getAll(){
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        Vector<DZ_Movie_Information> movie_List=new Vector<>();
        try {
            conn = DButil.getConnection();
            String sql="SELECT * FROM `dizhu`.`dz_movie_information`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                int Y_Id=rs.getInt("Y_Id");
                String Y_Name=rs.getString("Y_Name");
                String Y_Category=rs.getString("Y_Category");
                String Y_Protagonist=rs.getString("Y_Protagonist");
                String Y_Director=rs.getString("Y_Director");
                String Y_Synopsis=rs.getString("Y_Synopsis");
                String Y_Moviecover=rs.getString("Y_Movie_Cover");
                String Y_Pirce=rs.getString("Y_Price");
                String Y_Msdata=rs.getString("Y_Msdata");
                String Y_ShowTimes=rs.getString("Y_Show_Time");
                String Y_Language=rs.getString("Y_Language");
                DZ_Movie_Information movie_information=new DZ_Movie_Information(Y_Name,Y_Director,Y_Protagonist,Y_Category,Y_Synopsis,Y_ShowTimes,Y_Moviecover,Y_Pirce,Y_Msdata,Y_Language);
                movie_List.add(movie_information);
            }
            return movie_List;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DButil.close(conn,pst,rs);
        }
        return movie_List;
    }


    /**
     * @author ZYM
     */
    public static List<DZ_Movie_Information> Movie_Information_Query_name(String n) {
        String sql = "SELECT * FROM dz_movie_information WHERE Y_Name LIKE ?";
        return DButil.query(sql, DZ_Movie_Information.class, n);
    }


        public static void main(String[] args) {
        Vector<DZ_Movie_Information> all = getAll();
        all.forEach(System.out::println);
    }
}
