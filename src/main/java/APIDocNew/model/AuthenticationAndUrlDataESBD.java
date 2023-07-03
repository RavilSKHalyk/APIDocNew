package APIDocNew.model;

import lombok.Data;

import static APIDocNew.ApiDocNewApplication.Constants.TEST;

/**
 * Данные для аутентификации ЕСБД
 *
 */
@Data
public class AuthenticationAndUrlDataESBD {

    /*@Value("esbd.test.url")
    public String testUrl;
    @Value("esbd.test.login")
    private String testLogin;
    @Value("esbd.test.password")
    private String testPassword;
    @Value("esbd.prod.url")
    private String prodUrl;
    @Value("esbd.prod.login")
    private String prodLogin;
    @Value("esbd.prod.password")
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
