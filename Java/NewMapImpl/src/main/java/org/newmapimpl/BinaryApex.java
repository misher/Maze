package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

public class BinaryApex {

    private int key;
    private Object value;

    private BinaryApex leftApex;
    private BinaryApex rightApex;

    public BinaryApex(int key, Object value) {
        this.key = key;
        this.value = value;
        this.leftApex = null;
        this.rightApex = null;
    }

    // return left or right apex (null or not null)
    public BinaryApex lookAtLeft() {
        return leftApex;
    }
    public BinaryApex lookAtRight() {
        return rightApex;
    }

    // set apexes to this apex
    public void setLeftApex(BinaryApex leftApex) {
        this.leftApex = leftApex;
    }
    public void setRightApex(BinaryApex rightApex) {
        this.rightApex = rightApex;
    }

    // getters and setters for key and value
    public int getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(Object value) {
        this.value = value;
    }


}
