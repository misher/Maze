package SomePackage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by A.V.Tsaplin on 11.03.2016.
 */

public class SqlMaze implements IMaze {

    Map<Point, Integer> someMap = new HashMap<Point, Integer>();

    public SqlMaze (HashMap<Point, Integer> newMap) {
        super();
        this.someMap = newMap;
    }

    public boolean isRoadPoint(IPoint point) {
        Point somePoint = (Point) point;
        if (someMap.containsKey(somePoint) && (someMap.get(somePoint) == 0)){
            System.out.println("Coordinates X, Y, Z ... " + somePoint.getAxis(0) + " " + somePoint.getAxis(1));
            return true;
        }
        return false;
    }

    public boolean isTargetPoint(IPoint point) {
        Point somePoint = (Point) point;
        if (someMap.containsKey(somePoint) && (someMap.get(somePoint) == 2)){
            System.out.println("Found money Coordinates X, Y, Z ... "  + somePoint.getAxis(0) + " " + somePoint.getAxis(1));
            return true;
        }
        return false;
    }
}
