package APIDocNew.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import static APIDocNew.ApiDocNewApplication.Constants.TEST;

/**
 * Данные для аутентификации и ГБД
 *
 */
@Data
public class AuthenticationAndUrlDataGBD {
    @Value("${gbd.test.url}")
    private String testUrl;
    @Value("${gbd.test.userId}")
    private String testUserId;
    @Value("${gbd.test.login}")
    private String testLogin;
    @Value("${gbd.test.password}")
    private String testPassword;
    @Value("${gbd.prod.url}")
    private String prodUrl;
    @Value("${gbd.prod.userId}")
    private String prodUserId;
    @Value("${gbd.prod.login}")
    private String prodLogin;
    @Value("${gbd.prod.password}")
    private String prodPassword;

    private String Url;
    private String UserID;
    private String Login;
    private String Password;

    /**
     *
     * TEST - true - тестовый сервер
     *        false - продакшн
     *
     */
    public AuthenticationAndUrlDataGBD() {
        System.out.println("GBD "+testUrl);
        if (TEST){
            this.Url = "https://testscbws.mkb.kz/gbdServices/";
            this.UserID = "KIS.SSL.KIPIKOVR";
            this.Login = "KIS.SSL.KIPIKOVR";
            this.Password = "D8ggA45f";
        }else{
            this.Url = "https://scbws.mkb.kz/gbdServices/";
            this.UserID = "KIS.SSL.KIPIKOVR";
            this.Login = "KIS.SSL.KIPIKOVR";
            this.Password = "F67Yn88M";
        }
    }
}
