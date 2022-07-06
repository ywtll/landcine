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
public class DZ_Member {
    private int U_Id;
    private String U_Name;
    private String U_Phone;
    private String U_Password;
    private String U_Mail;
    private String U_EndLogin;
    private String U_Icon;
    private int U_Vip;
    private String  U_Price;
    private int U_Coupons;
    private int U_Level;


    public DZ_Member(String u_Name, String u_Phone, String u_Password, String u_Mail, String u_EndLogin, String u_Icon, int u_Vip, String u_Price, int u_Coupons, int u_Level) {
        U_Name = u_Name;
        U_Phone = u_Phone;
        U_Password = u_Password;
        U_Mail = u_Mail;
        U_EndLogin = u_EndLogin;
        U_Icon = u_Icon;
        U_Vip = u_Vip;
        U_Price = u_Price;
        U_Coupons = u_Coupons;
        U_Level = u_Level;
    }
}
