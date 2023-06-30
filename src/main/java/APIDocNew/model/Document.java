package APIDocNew.model;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class Document {
    private String iin;
    private String number;
    public String getIin() {
        return iin;
    }

    public void setIin(String iin) {
        this.iin = iin;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Document getDocument(String iin) {
        Document document = new Document();
        document.setIin(iin);
        document.setNumber("123");
        return document;
    }
}
