package artur.firsttask;

import java.util.ArrayList;

/**
 *
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public class Point implements IPoint{

    private int xCoordinate;
    private int yCoordinate;

    public Point(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }


    public ArrayList<IPoint> getNeighborPoints() {
        ArrayList<IPoint> neighborPointList = new ArrayList<>();
        Point addPoint = new Point(this.xCoordinate + 1, this.yCoordinate + 1);
        neighborPointList.add(addPoint);
        addPoint = new Point(this.xCoordinate + 1, this.yCoordinate);
        neighborPointList.add(addPoint);
        addPoint = new Point(this.xCoordinate + 1, this.yCoordinate - 1);
        neighborPointList.add(addPoint);
        addPoint = new Point(this.xCoordinate, this.yCoordinate - 1);
        neighborPointList.add(addPoint);
        addPoint = new Point(this.xCoordinate - 1, this.yCoordinate - 1);
        neighborPointList.add(addPoint);
        addPoint = new Point(this.xCoordinate - 1, this.yCoordinate);
        neighborPointList.add(addPoint);
        addPoint = new Point(this.xCoordinate - 1, this.yCoordinate + 1);
        neighborPointList.add(addPoint);
        addPoint = new Point(this.xCoordinate, this.yCoordinate + 1);
        neighborPointList.add(addPoint);
        return neighborPointList;
    }

}
