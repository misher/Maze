package artur.firsttask;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public class MapFillAndTravelTest {

    private MapData mapData;

    @Before
    public void init() {

        Point obstacleOne = new Point(1,1);
        Point obstacleTwo = new Point(1,2);
        Point obstacleThree = new Point(1,3);
        Point obstacleFour = new Point(2,3);
        Point obstacleFive = new Point(3,3);
        Point obstacleSix = new Point(2,5);
        Point obstacleSeven = new Point(3,5);
        List<IPoint> obstacleList = new ArrayList<>();
        obstacleList.add(obstacleOne);
        obstacleList.add(obstacleTwo);
        obstacleList.add(obstacleThree);
        obstacleList.add(obstacleFour);
        obstacleList.add(obstacleFive);
        obstacleList.add(obstacleSix);
        obstacleList.add(obstacleSeven);

        // let's fill our map
        mapData = new MapData(7,7);
        MazeFilling mazeFilling = new MazeFilling(mapData, obstacleList);
        mazeFilling.fillMap();

    }

    @Test
    public void mapFillTest() {

        int[][] checkArray =   {{0, 1, 2, 3, 4, 5, 6},
                                {1, -1, 2, 3, 4, 5, 6},
                                {2, -1, 3, 3, 4, 5, 6},
                                {3, -1, -1, -1, 4, 5, 6},
                                {4, 4, 5, 5, 5, 5, 6},
                                {5, 5, -1, -1, 6, 6, 6},
                                {6, 6, 6, 7, 7, 7, 7}};

        // let's print our map and check waiting result
        Map<IPoint, Integer> resMap = mapData.getMap();
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                Point somePoint = new Point(j,i);
                System.out.print(resMap.get(somePoint));
                assertEquals("Must be equal", new Integer(checkArray[i][j]), resMap.get(somePoint));
            }
            System.out.println();
        }

    }

    @Test
    public void mapTravelTest() {

        // let's test that map was traveled
        Point sP = new Point(3,6);
        Maze maze = new Maze(mapData);
        MazeTraveling mazeTraveling = new MazeTraveling();
        boolean result = mazeTraveling.travel(sP, maze);
        assertEquals("Must be true", true, result);

        // let's check trace
        List<IPoint> traceList = mazeTraveling.getTraceList();
        for (int i = 0; i < traceList.size(); i++) {
            Point point = (Point) traceList.get(i);
            assertEquals("Check value", new Integer(traceList.size() - i), mapData.getMap().get(point));
            System.out.println("Point number: " + (i+1) + " Coordinates X: " + point.getXCoordinate() + ", Coordinates Y: " + point.getYCoordinate() + ", Value: " + mapData.getMap().get(point));
        }

    }

}
