package artur.firsttask;

/**
 *
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public class Maze implements IMaze{

    private MapData mapData;

    public Maze(MapData mapData) {
        this.mapData = mapData;
    }

    @Override
    public boolean isTargetPoint(IPoint point) {
        Point sP = (Point) point;
        return (mapData.get(sP) == 0);
    }

    @Override
    public boolean isRoadPoint(IPoint startPoint, IPoint somePoint) {
        Point smP = (Point) somePoint;
        Point sP = (Point) startPoint;
        if ((smP.getYCoordinate() >= 0) && (smP.getXCoordinate() >= 0) && (smP.getXCoordinate() < mapData.getXSize()) && (smP.getYCoordinate() < mapData.getYSize())) {
            if (mapData.get(smP) == (mapData.get(sP) - 1)) {
                return true;
            }
        }
        return false;
    }
}
