package artur.firsttask;

import java.util.List;

/**
 *
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public class MazeFilling {

    private MapData mapData;
    private List<IPoint> obstacles;

    public MazeFilling(MapData mapData, List<IPoint> obstacles) {
        this.mapData = mapData;
        this.obstacles = obstacles;
    }

    void setObstacles() {
        for (IPoint someObstacle : obstacles) {
            Point sO = (Point) someObstacle;
            mapData.put(sO, -1);
        }
    }

    void fillNeighborsPoints(IPoint sP) {
        Point startPoint = (Point) sP;
        for (IPoint smP : startPoint.getNeighborPoints()) {
            Point somePoint = (Point) smP;
            if ((somePoint.getXCoordinate() >= 0) && (somePoint.getYCoordinate() >= 0) && (somePoint.getXCoordinate() < mapData.getXSize()) && (somePoint.getYCoordinate() < mapData.getYSize())) {
                if (!mapData.containsKey(somePoint)) {
                    int value = mapData.get(startPoint) + 1;
                    mapData.put(somePoint, value);
                }
            }
        }
    }

    void fillMap() {
        setObstacles();
        for (int i = 0; i < mapData.getYSize(); i++) {
            for (int j = 0; j < mapData.getXSize(); j++) {
                Point newStartPoint = new Point(j, i);
                if (mapData.get(newStartPoint) != -1) {
                    fillNeighborsPoints(newStartPoint);
                }
            }
        }
    }



}
