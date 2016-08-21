package org.newmapimpl;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class BinaryTree {

    private List<BinaryApex<Object, Object>> listOfApexesWithEqualsKeys;

    public BinaryTree() {
        this.listOfApexesWithEqualsKeys = null;
    }

    public List<BinaryApex<Object, Object>> getListOfApexesWithEqualsKeys() {
        return listOfApexesWithEqualsKeys;
    }

    public void setListOfApexesWithEqualsKeys(BinaryApex<Object, Object> binaryApex) {
        if (listOfApexesWithEqualsKeys == null) {
            listOfApexesWithEqualsKeys = new ArrayList<>();
        }
        listOfApexesWithEqualsKeys.add(binaryApex);
    }

    public boolean add(BinaryApex startApex, Object key, Object value) {
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
        if (binaryApex.getKey().hashCode() == startApex.getKey().hashCode()) {
            if (!binaryApex.getKey().equals(startApex.getKey())) {
                setListOfApexesWithEqualsKeys(binaryApex);
                return true;
            } else {
                startApex.setValue(binaryApex.getValue());
                return true;
            }
        }
        return false;
    }

    public Object get(BinaryApex startApex, Object key) {
        if (key.hashCode() == startApex.getKey().hashCode()) {
            if (key.equals(startApex.getKey())) {
                return startApex.getValue();
            } else if (getListOfApexesWithEqualsKeys() != null){
                for (int i = 0; i < getListOfApexesWithEqualsKeys().size(); i++) {
                    if (key.equals(getListOfApexesWithEqualsKeys().get(i).getKey())) {
                        return getListOfApexesWithEqualsKeys().get(i).getValue();
                    }
                }
                return null;
            }
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
