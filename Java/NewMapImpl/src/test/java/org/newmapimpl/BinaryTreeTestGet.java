package org.newmapimpl;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class BinaryTreeTestGet {

    @Test
    public void binaryTreeTestGet() {
        BinaryApex startBinaryApex = new BinaryApex(0, 0);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(startBinaryApex, 12, 14);
        binaryTree.add(startBinaryApex, -12, 15);
        binaryTree.add(startBinaryApex,6, 7);
        binaryTree.add(startBinaryApex, -12, 17);

        assertEquals("get method test", 7, binaryTree.get(startBinaryApex, 6));
        assertEquals("get method test", 17, binaryTree.get(startBinaryApex, -12));


    }

}

