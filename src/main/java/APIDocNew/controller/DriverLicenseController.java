package APIDocNew.controller;

import APIDocNew.model.DriverLicense;
import APIDocNew.soap.RequestGBD;
import APIDocNew.util.MyUtil;
import APIDocNew.util.MyXMLParser;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driverLicense")
public class DriverLicenseController {

    @GetMapping("/{iin}")
    public DriverLicense driverLicense(@Parameter(description = "ИИН 12 цифр", example = "880912300763") @PathVariable String iin) throws Exception {

        //проверяем ИИН
        if (!new MyUtil().isNumber(iin)){
            throw new Exception("ИИН содержит не числовые символы");
        }
        if (iin.length()!=12){
            throw new Exception("не правильное количество символов ИИН");
        }
        // проверяем есть ли в хранилище данные


        //если нет запрашиваем в ГБД
        DriverLicense driverLicense = new DriverLicense();
        String xmlText = new RequestGBD().getDriverInfo(iin);
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

        // сохраняем актуальные данные если надо


        return driverLicense;
    }

    /**
     * обработчик ошибок
     * @param exception исключение
     * @return код + текст ошибки
     */
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        return ResponseEntity
                .status(500)
                .body(new ErrorMessage(exception.getMessage()));
    }*/
}