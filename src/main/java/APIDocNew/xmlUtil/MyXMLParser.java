package APIDocNew.xmlUtil;

import APIDocNew.model.xml.MySoapMessage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class MyXMLParser {
    public String getElementFromXMLByName(String xmlText, String tagName) throws Exception {
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
        NodeList nodes = doc2.getElementsByTagName(tagName);
        String ElementFromXMLByName = "Not Found";
        Element element = (Element) nodes.item(0);
        //System.out.println(element.getTextContent());
        if (element!=null){
            ElementFromXMLByName = element.getTextContent();
        }
        return ElementFromXMLByName;
    }
    public String getElementFromElement(Element element, String elemName) {
        String ElementFromXMLByName = "Not Found";
        NodeList nodes = element.getElementsByTagName(elemName);
        Element el = (Element) nodes.item(0);
        //System.out.println(element.getTextContent());
        if (el!=null){
            ElementFromXMLByName = el.getTextContent();
        }
        return ElementFromXMLByName;
    }
}
