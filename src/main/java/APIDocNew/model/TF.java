package APIDocNew.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;

@Data
@Scope("prototype")
public class TF {
    private String tfid;
    private String tftypeid;
    private String vin;
    private String bodyNum;
    private String chassisNum;
    private String born;
    private String bornmonth;
    private String enginenumber;
    private String enginevolume;
    private String enginepower;
    private String righthanddrivebool;
    private String color;
    private String modelid;
    private String voitureModel;
    private String voitureMarkId;
    private String voitureMark;
    private String nPlaces;
    private String category;
    private String verifiedBool;
    private String dtVerified;
}
