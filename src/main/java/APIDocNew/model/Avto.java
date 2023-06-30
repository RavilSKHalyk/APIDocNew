package APIDocNew.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;

@Data
@Scope("prototype")
public class Avto {
    private String IdNumNikad;
    private String autoRegNum;
    private String autoRegNumOld;
    private String autoModel;
    private String autoYear;
    private String motorNum;
    private String chassisNum;
    private String BodyNum;
    private String autoVIN;
    private String autoColor;
    private String autoColorName;
    private String SrtsSerNum;
    private String autoCategory;
    private String motorPower1;
    private String motorPower2;
    private String motorVolume;
    private String autoTonnageMax;
    private String autoWeight;
    private String docPurchaseCode;
    private String docPurchaseNumDate;
    private String SRTSSerNumOld;
    private String SRTSDate;
    private String CountPlacesBus;
    private String LastName;
    private String FirstName;
    private String MiddleName;
    private String docSer;
    private String docType;
    private String docDate;
    private String docNum;
    private String drivingDocSer;
    private String drivingDocNum;
    private String areaCode;
    private String districtCode;
    private String cityCode;
    private String streetName;
    private String dom;
    private String apartment;
    private String contactPrefix;
    private String autoRegNumCode;
    private String isPersonApplicant;
    private String autoType;
    private String National;
    private String gender;
    private String RNN;
    private String IIN;
    private String OsobOtmetki;
    private String ownerKato;
    private String birthDate;
    private String status;
    private String statusDate;
    private String autoFirstRegDate;
}
