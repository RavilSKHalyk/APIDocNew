package APIDocNew.controller;

import APIDocNew.model.Udostoverenie;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

@RestController
@RequestMapping("/udv")
public class UDVController {
    @GetMapping("/{iin}")
    public Udostoverenie udostoverenie(@Parameter(description = "ИИН 12 цифр", example = "12345678901") @PathVariable String iin) throws Exception {

        Calendar calendar = new GregorianCalendar(2023, 6 , 13);

        if (iin.length()!=12){
            throw new Exception("не правильное количество символов ИИН");
        }
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
