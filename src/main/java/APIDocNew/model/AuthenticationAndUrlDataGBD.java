package APIDocNew.model;

import lombok.Data;

import static APIDocNew.ApiDocNewApplication.Constants.TEST;

/**
 * Данные для аутентификации и ГБД
 *
 */
@Data
public class AuthenticationAndUrlDataGBD {
    /*@Value("${gbd.testUrl}")
    String testUrl;
    @Value("${gbd.testUserId}")
    String testUserId;
    @Value("${gbd.testLogin}")
    String testLogin;
    @Value("${gbd.testPassword}")
    String testPassword;
    @Value("${gbd.prodUrl}")
    String prodUrl;
    @Value("${gbd.prodUserId}")
    String prodUserId;
    @Value("${gbd.prodLogin}")
    String prodLogin;
    @Value("${gbd.prodPassword}")
    String prodPassword;*/

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
        //System.out.println("GBD "+testUrl);
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
