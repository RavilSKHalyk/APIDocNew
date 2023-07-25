package APIDocNew.authentificationUtil;

import APIDocNew.model.AuthenticationAndUrlDataGBD;
import APIDocNew.model.KeyDataRequest;
import APIDocNew.model.Keys;
import APIDocNew.soap.RequestGBD;

public class KeysGBD {
    AuthenticationAndUrlDataGBD authenticationAndUrlDataGBD = new AuthenticationAndUrlDataGBD();

    /**
     * Возвращает ключи для запросов в ГБД, где требуется согласие клиента
     *
     */
    public Keys getKeysFromGBD (KeyDataRequest keyDataRequest) throws Exception {
        String xmlText = new RequestGBD().getKeysFromGBD(keyDataRequest);


        String PublicKey = "";
        String Token = "";


        Keys keys = new Keys();
        keys.setPublicKey(PublicKey);
        keys.setToken(Token);
        return keys;
    }
}
