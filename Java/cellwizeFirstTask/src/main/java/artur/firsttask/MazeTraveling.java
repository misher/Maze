package artur.firsttask;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public class MazeTraveling {

    private int xSize;
    private int ySize;
    private int[][] map;
    private List<IPoint> obstacles;
    private List<IPoint> traceList;

    public MazeTraveling(int xSize, int ySize, List<IPoint> obstacles) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.obstacles = obstacles;
        this.traceList = new ArrayList<>();
        this.map = new int[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                map[i][j] = -2;
            }
        }
        map[0][0] = 0;
    }

    public List<IPoint> getTraceList() {
        return traceList;
    }

    public int[][] getMap() {
        return map;
    }

    void setObstacles() {
        for (IPoint someObstacle : obstacles) {
            Point sO = (Point) someObstacle;
            map[sO.getYCoordinate()][sO.getXCoordinate()] = -1;
        }
    }

    void fillMap(IPoint sP) {
        Point startPoint = (Point) sP;
        for (IPoint smP : startPoint.getNeighborPoints()) {
            Point somePoint = (Point) smP;
            if ((somePoint.getXCoordinate() >= 0) && (somePoint.getYCoordinate() >= 0) && (somePoint.getXCoordinate() < xSize) && (somePoint.getYCoordinate() < ySize)) {
                if ((map[somePoint.getYCoordinate()][somePoint.getXCoordinate()] != -1) && (map[somePoint.getYCoordinate()][somePoint.getXCoordinate()] == -2)) {
                    map[somePoint.getYCoordinate()][somePoint.getXCoordinate()] = (map[startPoint.getYCoordinate()][startPoint.getXCoordinate()] + 1);
                }
            }
        }
    }

    void fillNeighborsPoints() {
        setObstacles();
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                Point newStartPoint = new Point(j, i);
                if (map[i][j] != -1) {
                    fillMap(newStartPoint);
                }
            }
        }
    }

    boolean travel(IPoint startPoint) {
        Point sP = (Point) startPoint;
        if (map[sP.getYCoordinate()][sP.getXCoordinate()] == 0) {
            return true;
        }
        traceList.add(sP);
        for (IPoint somePoint : sP.getNeighborPoints()) {
            Point smP = (Point) somePoint;
            if ((smP.getYCoordinate() >= 0) && (smP.getXCoordinate() >= 0) && (smP.getXCoordinate() < xSize) && (smP.getYCoordinate() < ySize)) {
                if (map[smP.getYCoordinate()][smP.getXCoordinate()] == (map[sP.getYCoordinate()][sP.getXCoordinate()] - 1)) {
                    if (travel(smP)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
