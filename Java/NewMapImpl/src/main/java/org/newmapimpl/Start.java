package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class Start {

    public static void main(String[] args) {

        BinaryApex startBinaryApex = new BinaryApex(0, 0);
        BinaryRes binaryRes = new BinaryRes(startBinaryApex);

        binaryRes.add(1, 12);
        binaryRes.add(-2, 13);
        binaryRes.add(6, 1);
        binaryRes.add(3, 2);
        binaryRes.add(7, 14);
        binaryRes.add(-6, 100);
        binaryRes.add(-1, 90);

        System.out.print(binaryRes.get(7));

    }
}
