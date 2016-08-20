package org.newmapimpl;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 20.08.2016.
 */

public class BinaryResTest {

    @Test
    public void binaryResTest() {
        BinaryApex startBinaryApex = new BinaryApex(0, 0);
        BinaryRes binaryRes = new BinaryRes(startBinaryApex);
        binaryRes.add(1, 12);
        binaryRes.add(-2, 13);
        binaryRes.add(6, 1);
        binaryRes.add("one", "two");
        binaryRes.add(7, 14);
        binaryRes.add(-6, 100);
        binaryRes.add(-1, 90);
        assertEquals("check string data", "two", binaryRes.get("one"));
    }

}
