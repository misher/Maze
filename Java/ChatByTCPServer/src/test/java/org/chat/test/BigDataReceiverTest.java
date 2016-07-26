package org.chat.test;

import junit.framework.Assert;
import org.chat.common.BigDataReceiver;
import org.junit.Test;



import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 26.07.2016.
 */
public class BigDataReceiverTest {

    @Test
    public void bigDataReceiverTest() {

        BigDataReceiver bigDataReceiver = new BigDataReceiver(256);

        String data1 = "{\"username\":\"Artur\",\"password\":\"mercedesg55amg\",\"message\":\"exit\",\"localAddress\":\"addressEx\"}^end^";
        BigDataReceiver.StringIndexPare stringIndexPare = bigDataReceiver.bigDataReceiver(data1);
        String resString1 = bigDataReceiver.toParse(stringIndexPare);
        assertEquals("String 1: ", resString1, "{\"username\":\"Artur\",\"password\":\"mercedesg55amg\",\"message\":\"exit\",\"localAddress\":\"addressEx\"}");


        String data2 = "{\"username\":\"TEST\",\"password\":\"TEST\",\"message\":\"TEST\",\"localAddress\":\"TEST\"}^end^";
        stringIndexPare = bigDataReceiver.bigDataReceiver(data2);
        String resString2 = bigDataReceiver.toParse(stringIndexPare);
        assertEquals("String 2: ", resString2, "{\"username\":\"TEST\",\"password\":\"TEST\",\"message\":\"TEST\",\"localAddress\":\"TEST\"}");


        String data3 = "{\"username\":\"TEST2\",\"password\":\"TEST2\",\"message\":\"TEST2\",\"localAddress\":\"TEST2\"}^end^";
        stringIndexPare = bigDataReceiver.bigDataReceiver(data3);
        String resString3 = bigDataReceiver.toParse(stringIndexPare);
        assertEquals("String 3: ", resString3, "{\"username\":\"TEST2\",\"password\":\"TEST2\",\"message\":\"TEST2\",\"localAddress\":\"TEST2\"}");


        String data4 = "{\"username\":\"TEST3\",\"password\":\"TEST3\",\"message\":\"TEST3\",\"localAddress\":\"TEST3\"}^end^";
        stringIndexPare = bigDataReceiver.bigDataReceiver(data4);
        String resString4 = bigDataReceiver.toParse(stringIndexPare);
        assertEquals("String 4: ", resString4, "{\"username\":\"TEST3\",\"password\":\"TEST3\",\"message\":\"TEST3\",\"localAddress\":\"TEST3\"}");


        String data5 = "{\"username\":\"TEST4\",\"password\":\"TEST4\",\"message\":\"TEST4\",\"localAddress\":\"TEST4\"}^end^";
        stringIndexPare = bigDataReceiver.bigDataReceiver(data5);
        String resString5 = bigDataReceiver.toParse(stringIndexPare);
        assertEquals("String 5: ", resString5, "{\"username\":\"TEST4\",\"password\":\"TEST4\",\"message\":\"TEST4\",\"localAddress\":\"TEST4\"}");


        String data6 = "{\"username\":\"TEST5\",\"password\":\"TEST5\",\"message\":\"TEST5\",\"localAddress\":\"TEST5\"}^end^";
        stringIndexPare = bigDataReceiver.bigDataReceiver(data6);
        String resString6 = bigDataReceiver.toParse(stringIndexPare);
        assertEquals("String 6: ", resString6, "{\"username\":\"TEST5\",\"password\":\"TEST5\",\"message\":\"TEST5\",\"localAddress\":\"TEST5\"}");


        String data7 = "{\"username\":\"TEST6\",\"password\":\"TEST6\",\"message\":\"TEST6\",\"localAddress\":\"TEST6\"}^end^";
        stringIndexPare = bigDataReceiver.bigDataReceiver(data7);
        String resString7 = bigDataReceiver.toParse(stringIndexPare);
        assertEquals("String 7: ", resString7, "{\"username\":\"TEST6\",\"password\":\"TEST6\",\"message\":\"TEST6\",\"localAddress\":\"TEST6\"}");

        System.out.println("Stopped at: " + stringIndexPare.getLastIndexValue() + " index.");

        System.out.println("Additional test start");

        BigDataReceiver bigDataReceiver2 = new BigDataReceiver(256);
        String str = "";
        for (int i = 0; i < 248; i++) {
            str = str + "g";
            if (i == 67) {
                str = str + "^end^";
            }
        }
        bigDataReceiver2.bigDataReceiver(str);
        String str2 = "^end^";
        BigDataReceiver.StringIndexPare stringIndexPare1 = bigDataReceiver2.bigDataReceiver(str2);
        String huy = bigDataReceiver2.toParse(stringIndexPare1);
        assertEquals("One test: ", huy.length(), 180);

    }
}
