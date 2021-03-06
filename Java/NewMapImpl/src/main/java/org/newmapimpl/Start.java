package org.newmapimpl;

import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class Start {

    public static void main(String[] args) {

        BinaryApex<Integer, Integer> startBinaryApex = new BinaryApex<>(0, 0);
        BinaryRes binaryRes = new BinaryRes(startBinaryApex);

        binaryRes.add(1, 12);
        binaryRes.add(-2, 13);
        binaryRes.add(6, 1);
        binaryRes.add("one", "two");
        binaryRes.add(7, 14);
        binaryRes.add(-6, 100);
        binaryRes.add(-1, 90);
        binaryRes.add(-6, 101);
        binaryRes.add(-6, 102);

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


        System.out.println("Value with non unique key 'electrolysissweeteners.net' = " + binaryRes.get("electrolysissweeteners.net"));
        System.out.println("Value with unique key '-6' = " + binaryRes.get(-6));

    }
}
