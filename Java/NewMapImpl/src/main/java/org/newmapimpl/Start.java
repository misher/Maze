package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class Start {

    public static void main(String[] args) {

        BinaryApex startBinaryApex = new BinaryApex(0, 0);
        BinaryTree startBinaryTree = new BinaryTree();
        startBinaryTree.add(startBinaryApex, 1, 12);
        startBinaryTree.add(startBinaryApex, -2, 13);
        startBinaryTree.add(startBinaryApex, 6, 1);
        startBinaryTree.add(startBinaryApex, 3, 2);
        startBinaryTree.add(startBinaryApex, 7, 14);
        startBinaryTree.add(startBinaryApex, -6, 100);
        startBinaryTree.add(startBinaryApex, -1, 90);

        System.out.print(startBinaryTree.get(startBinaryApex, 7));

    }
}
