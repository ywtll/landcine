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
public class DZ_Administrator {
    private int A_Id;
    private String A_Password;

    public DZ_Administrator(String a_Password) {
        A_Password = a_Password;
    }
}
