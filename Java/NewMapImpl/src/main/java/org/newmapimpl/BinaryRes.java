package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 20.08.2016.
 */

public class BinaryRes {

    private BinaryApex startApex;
    private BinaryTree binaryTree;

    public BinaryRes(BinaryApex startApex) {
        this.startApex = startApex;
        this.binaryTree = new BinaryTree();
    }

    public void add(Object key, Object value) {
        binaryTree.add(startApex, key, value);
    }

    public Object get(Object key) {
        return binaryTree.get(startApex, key);
    }

}
