package APIDocNew.model.xml;

import org.springframework.context.annotation.Scope;
import org.w3c.dom.Document;

@Scope("prototype")
public class MySoapMessage {

    private Document document;
    private boolean fault;

    public MySoapMessage(Document document, boolean fault) {
        this.document = document;
        this.fault = fault;
    }

    public Document getDocument() {
        return document;
    }

    public boolean isFault() {
        return fault;
    }
}
