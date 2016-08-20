package org.newmapimpl;

/**
 *
 * Created by A.V.Tsaplin on 19.08.2016.
 */

// TODO! Override hashcode method to deny collisions

public class BinaryApex<T1, T2> {

    private T1 key;
    private T2 value;

    private BinaryApex leftApex;
    private BinaryApex rightApex;

    public BinaryApex(T1 key, T2 value) {
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
    public T1 getKey() {
        return key;
    }
    public T2 getValue() {
        return value;
    }
    public void setValue(T2 value) {
        this.value = value;
    }


}
