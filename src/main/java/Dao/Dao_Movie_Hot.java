package Dao;

import Dao.DButil;
import Model.DZ_Movie_Hot;
import Model.DZ_Movie_Information;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class Dao_Movie_Hot {
    public static Vector<DZ_Movie_Hot> getAll(){
        Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        Vector<DZ_Movie_Hot> movie_List=new Vector<>();
        try {
            conn = DButil.getConnection();
            String sql="SELECT * FROM `dizhu`.`dz_movie_hot` ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()){
                int R_Id=rs.getInt("R_Id");
                String R_Name=rs.getString("R_Name");
                String R_Movie_cover=rs.getString("R_Movie_cover");
                DZ_Movie_Hot movie_information=new DZ_Movie_Hot(R_Id,R_Name,R_Movie_cover);
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
}
