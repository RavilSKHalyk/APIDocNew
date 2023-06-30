package APIDocNew.controller;

import APIDocNew.model.Udostoverenie;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/udv")
public class UDVController {
    @GetMapping("/{iin}")
    public Udostoverenie udostoverenie(@Parameter(description = "ИИН 12 цифр", example = "12345678901") @PathVariable String iin) throws Exception {

        Calendar calendar = new GregorianCalendar(2023, 6 , 13);

        Udostoverenie udv = new Udostoverenie();
        udv.setIin(iin);
        udv.setNumber("777777");
        udv.setSurname("Shaikhudinov");
        udv.setName("Ravil");
        udv.setPatronymic("Ravilevich");
        udv.setDateOfBirth(calendar);
        udv.setDateOperate(calendar);
        udv.setDocDateEnd(calendar);
        return udv;
    }

    @GetMapping("/{iin}/scan")
    public String udostoverenieScan(@Parameter(description = "ИИН 12 цифр", example = "482385300452") @PathVariable String iin){
        return "Скан документа";
    }
}
