package APIDocNew.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;

@Data
@Scope("prototype")
public class DriverLicense {
    private String firstName;
    private String lastName;
    private String middleName;
    private String dateOfBirth;
    private String dateOperate;
    private String drivingDocDateEnd;
    private String serial;
    private String num;
    private String categoryA;
    private String categoryB;
    private String categoryC;
    private String categoryD;
    private String categoryE;
    private String categoryF;
    private String iin;
    private String placeBirth;
}
