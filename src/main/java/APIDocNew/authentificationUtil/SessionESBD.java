package APIDocNew.authentificationUtil;

import APIDocNew.model.AuthenticationAndUrlDataESBD;
import APIDocNew.util.MyXMLParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SessionESBD {
    AuthenticationAndUrlDataESBD authenticationAndUrlDataESBD = new AuthenticationAndUrlDataESBD();

    public String getSessionID () throws Exception {
        String request = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                         "  <soap:Body>\n" +
                         "      <AuthenticateUser xmlns=\"https://icweb/IICWebService\">\n" +
                         "          <aName>"+ authenticationAndUrlDataESBD.getLogin() +"</aName>\n" +
                         "          <aPassword>"+ authenticationAndUrlDataESBD.getPassword() +"</aPassword>\n" +
                         "      </AuthenticateUser>\n" +
                         "  </soap:Body>\n" +
                         "</soap:Envelope>";

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
        String sessionID = new MyXMLParser().getElementFromXMLByName(respond,"SessionID");
        //System.out.println("sessionID = "+ sessionID);
        return sessionID;
    }
}
