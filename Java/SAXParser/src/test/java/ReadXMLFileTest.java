import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 14.08.2016.
 */


public class ReadXMLFileTest {

    @Test
    public void readXMLFileTest() {
        ReadXMLFile.main(null);
        assertEquals("Check by id", "172.23.13.49", (ParseResultInList.getParseResultList().get(0).getFields().get("ipAddress")));
        assertEquals("Check by name", "RNKG02", (ParseResultInList.getParseResultList().get(0).getFields().get("name")));
        ArrayList someList = (ArrayList) ParseResultInList.getParseResultList().get(0).getFields().get("RncOptions");
        assertEquals("Check by list one p", "9", someList.get(4));
        ArrayList aL = (ArrayList) ParseResultInList.getParseResultList().get(1).getFields().get("CBCPLMNId");
        HashMap map = (HashMap) aL.get(0);
        assertEquals("Check in item by one field", "425", map.get("MCC"));
        assertEquals("Check in item by one field", "02", map.get("MNC"));
    }

}
