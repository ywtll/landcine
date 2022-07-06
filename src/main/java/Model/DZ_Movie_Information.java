package Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @author 712f
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DZ_Movie_Information {

    private int Y_Id;
    private String Y_Name;
    private String Y_Director;
    private String Y_Protagonist;
    private String Y_Category;
    private String Y_Synopsis;
    private String Y_Show_Time;
    private String Y_Movie_Cover;
    private String Y_Price;
    private String  Y_Msdata;

    private  String Y_Language;

    public DZ_Movie_Information(String y_Name, String y_Director, String y_Protagonist, String y_Category, String y_Synopsis, String y_Show_Time, String y_Movie_Cover, String y_Price, String y_Msdata, String y_Language) {
        Y_Name = y_Name;
        Y_Director = y_Director;
        Y_Protagonist = y_Protagonist;
        Y_Category = y_Category;
        Y_Synopsis = y_Synopsis;
        Y_Show_Time = y_Show_Time;
        Y_Movie_Cover = y_Movie_Cover;
        Y_Price = y_Price;
        Y_Msdata = y_Msdata;
        Y_Language = y_Language;
    }

    /*
     * @author MOON
     */
    public DZ_Movie_Information(int y_Id, String y_Name, String y_Movie_Cover) {
        Y_Id = y_Id;
        Y_Name = y_Name;
        Y_Movie_Cover = y_Movie_Cover;
    }

}
