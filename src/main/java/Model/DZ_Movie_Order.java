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
public class DZ_Movie_Order {
    private int D_Id;
    private int D_User_Id;
    private int D_Movie_Id;
    private String D_Order_Time;
    private double D_Price;
    private int D_Confirm;
    private int D_Seat;

    public DZ_Movie_Order(int d_User_Id, int d_Movie_Id, String d_Order_Time, double d_Price, int d_Confirm, int  D_Seat) {
        this.D_User_Id = d_User_Id;
        this.D_Movie_Id = d_Movie_Id;
        this.D_Order_Time = d_Order_Time;
        this.D_Price = d_Price;
        this.D_Confirm = d_Confirm;
        this.D_Seat = D_Seat;
    }
}
