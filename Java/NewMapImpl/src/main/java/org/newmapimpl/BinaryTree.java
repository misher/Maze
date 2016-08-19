package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class BinaryTree {

    public boolean add(BinaryApex startApex, int key, Object value) {
        BinaryApex binaryApex = new BinaryApex(key, value);
        if (binaryApex.getKey() > startApex.getKey()) {
            if (startApex.lookAtRight() == null) {
                startApex.setRightApex(binaryApex);
                return true;
            } else {
                return add(startApex.lookAtRight(), key, value);
            }
        }
        if (binaryApex.getKey() < startApex.getKey()) {
            if (startApex.lookAtLeft() == null) {
                startApex.setLeftApex(binaryApex);
                return true;
            } else {
                return add(startApex.lookAtLeft(), key, value);
            }
        }
        if (binaryApex.getKey() == startApex.getKey()) {
            startApex.setValue(binaryApex.getValue());
            return true;
        }
        return false;
    }

    public Object get(BinaryApex startApex, int key) {
        if (key == startApex.getKey()) {
            return startApex.getValue();
        }
        if (key > startApex.getKey()) {
            if (startApex.lookAtRight() != null) {
                return get(startApex.lookAtRight(), key);
            } else {
                return null;
            }
        }
        if (key < startApex.getKey()) {
            if (startApex.lookAtLeft() != null) {
                return get(startApex.lookAtLeft(), key);
            } else {
                return null;
            }
        }
        return null;
    }

}
