package org.newmapimpl;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 21.08.2016.
 */

public class BinaryResWithEqualHashTest {

    @Test
    public void binaryResWithEqualHashTest() {

        BinaryApex<Integer, Integer> startBinaryApex = new BinaryApex<>(0, 0);
        BinaryRes binaryRes = new BinaryRes(startBinaryApex);
        binaryRes.add(1, 12);
        binaryRes.add(-2, 13);

        List<String> list = Arrays.asList("pollinating sandboxes",
                "amusement & hemophilias",
                "schoolworks = perversive",
                "electrolysissweeteners.net",
                "constitutionalunstableness.net",
                "grinnerslaphappier.org",
                "BLEACHINGFEMININELY.NET",
                "WWW.BUMRACEGOERS.ORG",
                "WWW.RACCOONPRUDENTIALS.NET",
                "Microcomputers: the unredeemed lollipop...",
                "Incentively, my dear, I don't tessellate a derangement.",
                "A person who never yodelled an apology, never preened vocalizing transsexuals.");
        Integer i = 0;
        for (String s : list) {
            binaryRes.add(s, i);
            i++;
        }

        assertEquals("Equal hashCode = '0'. Check collision container", 0, binaryRes.get("pollinating sandboxes"));
        assertEquals("Equal hashCode = '0'. Check collision container", 1, binaryRes.get("amusement & hemophilias"));
        assertEquals("Equal hashCode = '0'. Check collision container", 3, binaryRes.get("electrolysissweeteners.net"));
        assertEquals("Equal hashCode = '0'. Check collision container", 11, binaryRes.get("A person who never yodelled an apology, never preened vocalizing transsexuals."));
        assertEquals("Any get check", 12, binaryRes.get(1));

    }


}
