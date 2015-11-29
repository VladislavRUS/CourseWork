import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Владислав on 14.10.2015.
 */
public class Starter {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File f = new File("config.xml");
        if(!f.exists()){
            new PreServerThread();
        }
        else {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            MyHandler handler = new MyHandler();

            parser.parse(f, handler);
            String s = (MyRequest.sendPOST(handler.getPass(), GetDriveInfo.getSerialNumber()));
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            if(s.equals("OK"))
                new Thread(new ServerThread()).start();
        }
    }
}
