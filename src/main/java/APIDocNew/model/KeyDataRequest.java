package APIDocNew.model;

import lombok.Data;

@Data
public class KeyDataRequest {
    private String uin;
    private String company = "АО Страховая комания \"Халык\"";
    private String companyBin = "981040001082";
    private String companyResponsible = "База мобильных граждан";
    private String employeeName = "Иванов И.";
    private String accessName = "MCDB_SERVICE";
    private String personalDataName;
    private String expiresIn = "300000";
    private String omitSms = "false";
}
