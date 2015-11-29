import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by Владислав on 14.10.2015.
 */
public class MyHandler extends DefaultHandler {
    String pass = "";
    String element;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        element = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (element.equals("password")) {
            pass = new String(ch, start, length);
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        element = "";
    }

    public String getPass(){
        return pass;
    }
}
