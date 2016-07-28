package org.chat.test;

import org.chat.common.IncomingDataContainer;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 28.07.2016.
 */

public class IncomingDataContainerTest {

    @Test
    public void incomingDataContainerTest() {

        String data = "{\"username\":\"Artur\",\"password\":\"mercedesg55amg\",\"message\"";
        String data2 = ":\"exit\",\"localAddress\":\"addressEx\"}^end^";
        IncomingDataContainer incomingDataContainer = new IncomingDataContainer(512);
        incomingDataContainer.incomingDataContainer(data);
        assertEquals("check 1", null, incomingDataContainer.toParse("^end^"));
        incomingDataContainer.incomingDataContainer(data2);
        assertEquals("check 2", "{\"username\":\"Artur\",\"password\":\"mercedesg55amg\",\"message\":\"exit\",\"localAddress\":\"addressEx\"}", incomingDataContainer.toParse("^end^"));

    }
}
