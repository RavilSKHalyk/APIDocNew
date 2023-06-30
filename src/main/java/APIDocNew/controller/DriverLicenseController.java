package APIDocNew.controller;

import APIDocNew.model.DriverLicense;
import APIDocNew.xmlUtil.MyXMLParser;
import APIDocNew.xmlUtil.soapRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driverLicense")
public class DriverLicenseController {
    @GetMapping("/{iin}")
    public DriverLicense driverLicense(@Parameter(description = "ИИН 12 цифр", example = "12345678901") @PathVariable String iin) throws Exception {
        DriverLicense driverLicense = new DriverLicense();
        String xmlText = new soapRequest().getDriverInfo(iin);
        MyXMLParser myXMLParser = new MyXMLParser();
        driverLicense.setFirstName(myXMLParser.getElementFromXMLByName(xmlText,"firstName"));
        driverLicense.setLastName(myXMLParser.getElementFromXMLByName(xmlText,"lastName"));
        driverLicense.setMiddleName(myXMLParser.getElementFromXMLByName(xmlText,"middleName"));
        driverLicense.setDateOfBirth(myXMLParser.getElementFromXMLByName(xmlText,"dateOfBirth"));
        driverLicense.setDateOperate(myXMLParser.getElementFromXMLByName(xmlText,"dateOperate"));
        driverLicense.setDrivingDocDateEnd(myXMLParser.getElementFromXMLByName(xmlText,"drivingDocDateEnd"));
        driverLicense.setSerial(myXMLParser.getElementFromXMLByName(xmlText,"serial"));
        driverLicense.setNum(myXMLParser.getElementFromXMLByName(xmlText,"num"));
        driverLicense.setCategoryA(myXMLParser.getElementFromXMLByName(xmlText,"categoryA"));
        driverLicense.setCategoryB(myXMLParser.getElementFromXMLByName(xmlText,"categoryB"));
        driverLicense.setCategoryC(myXMLParser.getElementFromXMLByName(xmlText,"categoryC"));
        driverLicense.setCategoryD(myXMLParser.getElementFromXMLByName(xmlText,"categoryD"));
        driverLicense.setCategoryE(myXMLParser.getElementFromXMLByName(xmlText,"categoryE"));
        driverLicense.setCategoryF(myXMLParser.getElementFromXMLByName(xmlText,"categoryF"));
        driverLicense.setIin(myXMLParser.getElementFromXMLByName(xmlText,"iin"));
        driverLicense.setPlaceBirth(myXMLParser.getElementFromXMLByName(xmlText,"placeBirth"));
        return driverLicense;
    }
}