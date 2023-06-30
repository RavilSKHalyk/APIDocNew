package APIDocNew.xmlUtil;

import APIDocNew.model.AuthenticationAndUrlDataESBD;
import APIDocNew.model.AuthenticationAndUrlDataGBD;
import APIDocNew.model.KeyDataRequest;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Random;
public class soapRequest {
    AuthenticationAndUrlDataGBD authenticationAndUrlDataGBD = new AuthenticationAndUrlDataGBD();
    AuthenticationAndUrlDataESBD authenticationAndUrlDataESBD = new AuthenticationAndUrlDataESBD();

    public String getDriverInfo(String iin) throws Exception {
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:data=\"http://data.gbd.chdb.scb.kz/\">\n" +
                "   <soapenv:Header>\n" +
                "      <userId>" + authenticationAndUrlDataGBD.getUserID() + "</userId>\n" +
                "   </soapenv:Header>\n" +
                "   <soapenv:Body>\n" +
                "      <data:getZipDriverPortType>\n" +
                "         <!--Optional:-->\n" +
                "         <iin>" + iin + "</iin>\n" +
                "         <requestNumber>1</requestNumber>\n" +
                "         <!--Optional:-->\n" +
                "         <consentConfirmed>true</consentConfirmed>\n" +
                "      </data:getZipDriverPortType>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        URL url = new URL(authenticationAndUrlDataGBD.getUrl()+"ZipDriverPortTypeImplService");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        TrustModifier.relaxHostChecking(connection);
        String auth = authenticationAndUrlDataGBD.getLogin() + ":" + authenticationAndUrlDataGBD.getPassword();
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(auth.getBytes()));
        connection.setRequestProperty("Authorization", basicAuth);
        connection.setRequestProperty("Content-Length", String.valueOf(request.length()));
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setDoOutput(true);
        PrintWriter pw = new PrintWriter(connection.getOutputStream());
        pw.write(request);
        pw.flush();
        connection.connect();
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        String respond = rd.readLine();
        while ((line = rd.readLine()) != null)
            respond = line;
        //System.out.println(respond);
        return respond;
    }

    public String getAutoInfo(String iin) throws Exception {
        //System.out.println("URL в поиске авто по BBY \n "+authenticationAndUrlDataGBD.getUrl()+"AutoDetailsVinImplService");

        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:data=\"http://data.gbd.chdb.scb.kz/\">\n" +
                "    <soapenv:Header>\n" +
                "        <!--ID пользователя, от имени которого будет выполнена запрашиваемая операция-->\n" +
                "        <userId>" + authenticationAndUrlDataGBD.getUserID() + "</userId>\n" +
                "    </soapenv:Header>\n" +
                "    <soapenv:Body>\n" +
                "        <data:getAuto>\n" +
                "            <!--Optional:-->\n" +
                "            <iinBin>"+iin+"</iinBin>\n" +
                "        </data:getAuto>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";
        URL url = new URL(authenticationAndUrlDataGBD.getUrl()+"AutoDetailsVinImplService");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        TrustModifier.relaxHostChecking(connection);
        String auth = authenticationAndUrlDataGBD.getLogin() + ":" + authenticationAndUrlDataGBD.getPassword();
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(auth.getBytes()));
        connection.setRequestProperty("Authorization", basicAuth);
        connection.setRequestProperty("Content-Length", String.valueOf(request.length()));
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setDoOutput(true);
        PrintWriter pw = new PrintWriter(connection.getOutputStream());
        pw.write(request);
        pw.flush();
        connection.connect();
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        String respond = rd.readLine();
        while ((line = rd.readLine()) != null)
            respond = line;
        //System.out.println(respond);
        return respond;
    }

    public String getAutoInfoByNumber(String number, String sessionID) throws Exception {
        String request = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                "  <soap12:Body>\n" +
                "    <GetTFByNumber xmlns=\"https://icweb/IICWebService\">\n" +
                "      <aSessionID>"+sessionID+"</aSessionID>\n" +
                "      <aTF_NUMBER>"+number+"</aTF_NUMBER>\n" +
                "    </GetTFByNumber>\n" +
                "  </soap12:Body>\n" +
                "</soap12:Envelope>";

        URL url = new URL(authenticationAndUrlDataESBD.getUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Host", url.getHost());
        connection.setRequestProperty("Content-Length", String.valueOf(request.length()));
        connection.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setDoOutput(true);
        PrintWriter pw = new PrintWriter(connection.getOutputStream());
        pw.write(request);
        pw.flush();
        connection.connect();
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        String respond = rd.readLine();
        while ((line = rd.readLine()) != null) {
            respond = line;
        }

        return respond;
    }

    /**
     * Ключи согласия на сбор персональных данных для доступа в некоторые сервисы ГБД, в частности
     * сервис по получению данных о физ лице
     * @param keyDataRequest
     * @return
     */
    public String getKeysFromGBD(KeyDataRequest keyDataRequest) throws Exception {
        Random random = new Random();
        String request =
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:data=\"http://data.gbd.chdb.scb.kz/\">\n" +
                "    <soapenv:Header>\n" +
                "        <userId>"+authenticationAndUrlDataGBD.getUserID()+"</userId>\n" +
                "    </soapenv:Header>\n" +
                "    <soapenv:Body>\n" +
                "        <data:getPersonDataAccessControl>\n" +
                "            <requestNumber>" + random.nextInt(10000) + "</requestNumber>\n" +
                "            <uin>"+keyDataRequest.getUin()+"</uin>\n" +
                "            <company>"+keyDataRequest.getCompany()+"</company>\n" +
                "            <company_bin>"+keyDataRequest.getCompanyBin()+"</company_bin>\n" +
                "            <company_responsible>"+keyDataRequest.getCompanyResponsible()+"</company_responsible>\n" +
                "            <employee_name>"+keyDataRequest.getEmployeeName()+"</employee_name>\n" +
                "            <access_name>"+keyDataRequest.getAccessName()+"</access_name>\n" +
                "            <personal_data_name>"+keyDataRequest.getPersonalDataName()+"</personal_data_name>\n" +
                "            <expiresIn>"+keyDataRequest.getExpiresIn()+"</expiresIn>\n" +
                "            <omit-sms>"+keyDataRequest.getOmitSms()+"</omit-sms>\n" +
                "        </data:getPersonDataAccessControl>\n" +
                "    </soapenv:Body>\n" +
                "</soapenv:Envelope>";

        URL url = new URL(authenticationAndUrlDataGBD.getUrl());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        TrustModifier.relaxHostChecking(connection);
        String auth = "KIS.SSL.KIPIKOVR" + ":" + "D8ggA45f";
        String basicAuth = "Basic " + new String(Base64.getEncoder().encode(auth.getBytes()));
        connection.setRequestProperty("Authorization", basicAuth);
        connection.setRequestProperty("Content-Length", String.valueOf(request.length()));
        connection.setRequestProperty("Content-Type", "application/xml");
        connection.setRequestProperty("Connection", "Keep-Alive");
        connection.setDoOutput(true);
        PrintWriter pw = new PrintWriter(connection.getOutputStream());
        pw.write(request);
        pw.flush();
        connection.connect();
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String line;
        String respond = rd.readLine();
        while ((line = rd.readLine()) != null)
            respond = line;
        return respond;
    }
}