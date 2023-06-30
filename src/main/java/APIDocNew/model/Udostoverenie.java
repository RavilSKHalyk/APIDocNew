package APIDocNew.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;

import java.util.Calendar;
@Data
@Scope("prototype")
public class Udostoverenie {
    //@JsonProperty("ИИН")
    private String iin;
    private String number;
    private String surname;
    private String name;
    private String patronymic;
    private Calendar dateOfBirth;
    private Calendar dateOperate;
    private Calendar docDateEnd;
}
