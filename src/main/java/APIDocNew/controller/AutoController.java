package APIDocNew.controller;

import APIDocNew.authentificationUtil.SessionESBD;
import APIDocNew.model.Avto;
import APIDocNew.model.TF;
import APIDocNew.model.xml.MySoapMessage;
import APIDocNew.xmlUtil.MyXMLParser;
import APIDocNew.xmlUtil.SoapParser;
import APIDocNew.xmlUtil.XmlUtil;
import APIDocNew.xmlUtil.soapRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;

@RestController
@RequestMapping("/autoController")
public class AutoController {
    @GetMapping("/iin/{iin}")
    public ArrayList<Avto> autoByIIN(@Parameter(description = "ИИН 12 цифр", example = "12345678901") @PathVariable String iin) throws Exception {
        // здесь может быть несколько машин
        ArrayList<Avto> cars = new ArrayList<Avto>();
        String xmlText = new soapRequest().getAutoInfo(iin);
        Document doc = XmlUtil.fromXML(xmlText);
        SoapParser parser = new SoapParser(doc);
        MySoapMessage soap = parser.parse();
        String body = XmlUtil.toXML(soap.getDocument(), false);
        //System.out.println(body);
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(body));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc2 = db.parse(is);
        NodeList nodes = doc2.getElementsByTagName("item");
        MyXMLParser myXMLParser = new MyXMLParser();
        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            if (element!=null){
                Avto avto = new Avto();
                avto.setIdNumNikad(myXMLParser.getElementFromElement(element, "IdNumNikad"));
                avto.setAutoRegNum(myXMLParser.getElementFromElement(element, "autoRegNum"));
                avto.setAutoRegNumOld(myXMLParser.getElementFromElement(element, "autoRegNumOld"));
                avto.setAutoModel(myXMLParser.getElementFromElement(element, "autoModel"));
                avto.setAutoYear(myXMLParser.getElementFromElement(element, "autoYear"));
                avto.setMotorNum(myXMLParser.getElementFromElement(element, "motorNum"));
                avto.setChassisNum(myXMLParser.getElementFromElement(element, "chassisNum"));
                avto.setBodyNum(myXMLParser.getElementFromElement(element, "BodyNum"));
                avto.setAutoVIN(myXMLParser.getElementFromElement(element, "autoVIN"));
                avto.setAutoColor(myXMLParser.getElementFromElement(element, "autoColor"));
                avto.setAutoColorName(myXMLParser.getElementFromElement(element, "autoColorName"));
                avto.setSrtsSerNum(myXMLParser.getElementFromElement(element, "SrtsSerNum"));
                avto.setAutoCategory(myXMLParser.getElementFromElement(element, "autoCategory"));
                avto.setMotorPower1(myXMLParser.getElementFromElement(element, "motorPower1"));
                avto.setMotorPower2(myXMLParser.getElementFromElement(element, "motorPower2"));
                avto.setMotorVolume(myXMLParser.getElementFromElement(element, "motorVolume"));
                avto.setAutoTonnageMax(myXMLParser.getElementFromElement(element, "autoTonnageMax"));
                avto.setAutoWeight(myXMLParser.getElementFromElement(element, "autoWeight"));
                avto.setDocPurchaseCode(myXMLParser.getElementFromElement(element, "docPurchaseCode"));
                avto.setDocPurchaseNumDate(myXMLParser.getElementFromElement(element, "docPurchaseNumDate"));
                avto.setSRTSSerNumOld(myXMLParser.getElementFromElement(element, "SRTSSerNumOld"));
                avto.setSRTSDate(myXMLParser.getElementFromElement(element, "SRTSDate"));
                avto.setCountPlacesBus(myXMLParser.getElementFromElement(element, "CountPlacesBus"));
                avto.setLastName(myXMLParser.getElementFromElement(element, "LastName"));
                avto.setFirstName(myXMLParser.getElementFromElement(element, "FirstName"));
                avto.setMiddleName(myXMLParser.getElementFromElement(element, "MiddleName"));
                avto.setDocSer(myXMLParser.getElementFromElement(element, "docSer"));
                avto.setDocType(myXMLParser.getElementFromElement(element, "docType"));
                avto.setDocDate(myXMLParser.getElementFromElement(element, "docDate"));
                avto.setDocNum(myXMLParser.getElementFromElement(element, "docNum"));
                avto.setDrivingDocSer(myXMLParser.getElementFromElement(element, "drivingDocSer"));
                avto.setDrivingDocNum(myXMLParser.getElementFromElement(element, "drivingDocNum"));
                avto.setAreaCode(myXMLParser.getElementFromElement(element, "areaCode"));
                avto.setDistrictCode(myXMLParser.getElementFromElement(element, "districtCode"));
                avto.setCityCode(myXMLParser.getElementFromElement(element, "cityCode"));
                avto.setStreetName(myXMLParser.getElementFromElement(element, "streetName"));
                avto.setDom(myXMLParser.getElementFromElement(element, "dom"));
                avto.setApartment(myXMLParser.getElementFromElement(element, "apartment"));
                avto.setContactPrefix(myXMLParser.getElementFromElement(element, "contactPrefix"));
                avto.setAutoRegNumCode(myXMLParser.getElementFromElement(element, "autoRegNumCode"));
                avto.setIsPersonApplicant(myXMLParser.getElementFromElement(element, "isPersonApplicant"));
                avto.setAutoType(myXMLParser.getElementFromElement(element, "autoType"));
                avto.setNational(myXMLParser.getElementFromElement(element, "National"));
                avto.setGender(myXMLParser.getElementFromElement(element, "gender"));
                avto.setRNN(myXMLParser.getElementFromElement(element, "RNN"));
                avto.setIIN(myXMLParser.getElementFromElement(element, "IIN"));
                avto.setOsobOtmetki(myXMLParser.getElementFromElement(element, "OsobOtmetki"));
                avto.setOwnerKato(myXMLParser.getElementFromElement(element, "ownerKato"));
                avto.setBirthDate(myXMLParser.getElementFromElement(element, "birthDate"));
                avto.setStatus(myXMLParser.getElementFromElement(element, "status"));
                avto.setStatusDate(myXMLParser.getElementFromElement(element, "statusDate"));
                avto.setAutoFirstRegDate(myXMLParser.getElementFromElement(element, "autoFirstRegDate"));
                cars.add(avto);
            }
        }
        return cars;
    }

    @GetMapping("/number/{number}")
    public TF autoByNumber(@Parameter(description = "гос номер авто", example = "") @PathVariable String number) throws Exception {
        SessionESBD sessionESBD = new SessionESBD();
        String sessionID = sessionESBD.getSessionID();
        String xmlText = new soapRequest().getAutoInfoByNumber(number, sessionID);
        TF tf = new TF();
        MyXMLParser myXMLParser = new MyXMLParser();
        tf.setTfid(myXMLParser.getElementFromXMLByName(xmlText,"TF_ID"));
        tf.setTftypeid(myXMLParser.getElementFromXMLByName(xmlText,"TF_TYPE_ID"));
        tf.setVin(myXMLParser.getElementFromXMLByName(xmlText,"VIN"));
        tf.setBodyNum(myXMLParser.getElementFromXMLByName(xmlText,"BodyNum"));
        tf.setChassisNum(myXMLParser.getElementFromXMLByName(xmlText,"chassisNum"));
        tf.setBorn(myXMLParser.getElementFromXMLByName(xmlText,"BORN"));
        tf.setBornmonth(myXMLParser.getElementFromXMLByName(xmlText,"BORN_MONTH"));
        tf.setEnginenumber(myXMLParser.getElementFromXMLByName(xmlText,"ENGINE_NUMBER"));
        tf.setEnginevolume(myXMLParser.getElementFromXMLByName(xmlText,"ENGINE_VOLUME"));
        tf.setEnginepower(myXMLParser.getElementFromXMLByName(xmlText,"ENGINE_POWER"));
        tf.setRighthanddrivebool(myXMLParser.getElementFromXMLByName(xmlText,"RIGHT_HAND_DRIVE_BOOL"));
        tf.setColor(myXMLParser.getElementFromXMLByName(xmlText,"COLOR"));
        tf.setModelid(myXMLParser.getElementFromXMLByName(xmlText,"MODEL_ID"));
        tf.setVoitureModel(myXMLParser.getElementFromXMLByName(xmlText,"VoitureModel"));
        tf.setVoitureMarkId(myXMLParser.getElementFromXMLByName(xmlText,"VoitureMarkId"));
        tf.setVoitureMark(myXMLParser.getElementFromXMLByName(xmlText,"VoitureMark"));
        tf.setNPlaces(myXMLParser.getElementFromXMLByName(xmlText,"nPlaces"));
        tf.setCategory(myXMLParser.getElementFromXMLByName(xmlText,"Category"));
        tf.setVerifiedBool(myXMLParser.getElementFromXMLByName(xmlText,"verified_bool"));
        tf.setDtVerified(myXMLParser.getElementFromXMLByName(xmlText,"dtVerified"));
        return tf;
    }
}
