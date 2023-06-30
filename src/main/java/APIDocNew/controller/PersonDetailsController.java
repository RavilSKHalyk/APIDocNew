package APIDocNew.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/PersonDetailsController")
public class PersonDetailsController {
    @GetMapping("/{iin}")
    public String getPersonDetails (@Parameter(description = "ИИН 12 цифр", example = "12345678901") @PathVariable String iin) throws Exception {
        String xmlText = "";
        return "";
    }
}
