package org.maze;

/**
 * Created by A.V.Tsaplin on 13.04.2016.
 */

public class ParePointValue {

    private final Point somePoint;
    private final int someValue;

    public ParePointValue(Point somePoint, int someValue) {
        super();
        this.somePoint = somePoint;
        this.someValue = someValue;
    }
    public Point getSomePoint() {
        return somePoint;
    }
    public int getSomeValue() {
        return someValue;
    }
}
