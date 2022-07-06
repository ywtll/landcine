package film.util;

import Dao.DButil;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

/**
 * @author zlf
 */

@SuppressWarnings({"all"})
public class SetModeData {

    private String sql;

    public SetModeData(String sql){
        this.sql = sql;
    }

    public SetModeData(){

    }


    /*public void queryData(DefaultTableModel dtm,String n){
        dtm.getDataVector().clear();
        if (n == null || n.length() == 0){
            sql = "select `Y_Id`,`Y_Name`,`Y_Category`,`Y_Protagonist`,`Y_Director`,`Y_Language`,`Y_Synopsis`  from dz_MOVIE_information  ";
        }else {
            sql = "select `Y_Id`,`Y_Name`,`Y_Category`,`Y_Protagonist`,`Y_Director`,`Y_Language`,`Y_Synopsis`  from dz_MOVIE_information where `Y_Name` like  '"+n+"'";
        }
        Vector<Object> query = DButil.query(sql);
        for (Object info : query) {
            dtm.addRow((Vector) info);
        }
        dtm.fireTableDataChanged();
    }*/


    public void queryMovieData(DefaultTableModel dtm,String n){
        dtm.getDataVector().clear();
        if (n == null || n.length() == 0){
            sql = "SELECT `Y_Id`,`Y_Name`,`Y_Category`,`Y_Director`,`Y_Protagonist`,`Y_Language`,`Y_Synopsis`,`Y_Price`,`Y_Movie_Cover` FROM `dizhu`.`dz_movie_information`";
        }else if (n != null){
            sql = "SELECT `Y_Id`,`Y_Name`,`Y_Category`,`Y_Director`,`Y_Protagonist`,`Y_Language`,`Y_Synopsis`,`Y_Price`,`Y_Movie_Cover` FROM `dizhu`.`dz_movie_information` where `Y_Name` like '%"+n+"'";
        }
        Vector<Object> query = DButil.query(sql);
        for (Object info : query) {
            dtm.addRow((Vector) info);
        }
        dtm.fireTableDataChanged();
    }

    public void queryUserData(DefaultTableModel dtm,String n){
        dtm.getDataVector().clear();
        String sql;
        if (n == null){
            sql = "SELECT `U_Id`,`U_Name`,`U_Phone`,`U_Vip`,`U_Level`,`U_Price`,`U_EndLogin` from `dizhu`.`dz_member`";
        }else {
            sql = "SELECT `U_Id`,`U_Name`,`U_Phone`,`U_Vip`,`U_Level`,`U_Price`,`U_EndLogin` from `dizhu`.`dz_member` where `U_Name` like '%"+n+"'";
        }
        Vector<Object> query = DButil.query(sql);
        for (Object info : query) {
            dtm.addRow((Vector) info);
        }
        dtm.fireTableDataChanged();
    }
}
