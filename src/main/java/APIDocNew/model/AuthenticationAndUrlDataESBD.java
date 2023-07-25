package APIDocNew.model;

import lombok.Data;

import static APIDocNew.ApiDocNewApplication.Constants.TEST;

/**
 * Данные для аутентификации ЕСБД
 *
 */
@Data
public class AuthenticationAndUrlDataESBD {

    /*@Value("${esbd.testUrl}")
    public String testUrl;
    @Value("${esbd.testLogin}")
    private String testLogin;
    @Value("${esbd.testPassword}")
    private String testPassword;
    @Value("${esbd.prodUrl}")
    private String prodUrl;
    @Value("${esbd.prodLogin}")
    private String prodLogin;
    @Value("${esbd.prodPassword}")
    private String prodPassword;*/

    private String Url;
    private String Login;
    private String Password;

    /**
     *
     * TEST - true - тестовый сервер
     *        false - продакшн
     *
     */
    public AuthenticationAndUrlDataESBD() {
        //System.out.println("ESBD "+ testUrl);
        if (TEST){
            this.Url = "http://10.186.30.74:8078/IICWebservice.asmx";
            this.Login = "KIS.SSL.KIPIKOVR";
            this.Password = "111111";
        }else{
            this.Url = "http://10.186.30.75:1108/iicwebservice.asmx";;
            this.Login = "KIS.SSL.KIPIKOVR";
            this.Password = "111111";
        }
    }
}
