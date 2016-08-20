package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 20.08.2016.
 */

public class BinaryRes {

    BinaryApex startApex;

    public BinaryRes(BinaryApex startApex) {
        this.startApex = startApex;
    }

    public void add(Object key, Object value) {
        BinaryTree.add(startApex, key, value);
    }

    public Object get(Object key) {
        return BinaryTree.get(startApex, key);
    }

}
