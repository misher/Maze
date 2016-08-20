package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class BinaryTree {

    public static boolean add(BinaryApex startApex, Object key, Object value) {
        BinaryApex<Object, Object> binaryApex = new BinaryApex<>(key, value);
        if (binaryApex.getKey().hashCode() > startApex.getKey().hashCode()) {
            if (startApex.lookAtRight() == null) {
                startApex.setRightApex(binaryApex);
                return true;
            } else {
                return add(startApex.lookAtRight(), key, value);
            }
        }
        if (binaryApex.getKey().hashCode() < startApex.getKey().hashCode()) {
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

    public static Object get(BinaryApex startApex, Object key) {
        if (key == startApex.getKey()) {
            return startApex.getValue();
        }
        if (key.hashCode() > startApex.getKey().hashCode()) {
            if (startApex.lookAtRight() != null) {
                return get(startApex.lookAtRight(), key);
            }
        }
        if (key.hashCode() < startApex.getKey().hashCode()) {
            if (startApex.lookAtLeft() != null) {
                return get(startApex.lookAtLeft(), key);
            }
        }
        return null;
    }

}
