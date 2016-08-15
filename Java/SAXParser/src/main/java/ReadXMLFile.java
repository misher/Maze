import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;



/**
 *
 * Created by A.V.Tsaplin on 14.08.2016.
 */


public class ReadXMLFile {

    public static List<ManagedObjects> toReadXMLFile() {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            XMLParser xmlParser = new XMLParser();
            saxParser.parse("src\\main\\resources\\example.xml", xmlParser);
            return xmlParser.getListManagedObjects();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
