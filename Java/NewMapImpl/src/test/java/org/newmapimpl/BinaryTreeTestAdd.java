package org.newmapimpl;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class BinaryTreeTestAdd {

    @Test
    public void binaryTreeTestAdd() {
        BinaryApex<Integer, Integer> startBinaryApex = new BinaryApex<>(0, 0);
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(startBinaryApex, 12, 14);
        binaryTree.add(startBinaryApex, -12, 15);
        assertEquals("right point test ", 14, startBinaryApex.lookAtRight().getValue());
        assertEquals("leftt point test ", 15, startBinaryApex.lookAtLeft().getValue());
        binaryTree.add(startBinaryApex,6, 7);
        assertEquals("right point test in generation ", 7, startBinaryApex.lookAtRight().lookAtLeft().getValue());
        binaryTree.add(startBinaryApex,6, 7);
        assertEquals("right point test in generation ", 7, startBinaryApex.lookAtRight().lookAtLeft().getValue());
    }


}
