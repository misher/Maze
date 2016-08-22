package artur.firsttask;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public class MapData {

    private int xSize;
    private int ySize;
    private Map<IPoint, Integer> map;

    public MapData(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.map = new HashMap<>();
        map.put(new Point(0,0), 0);
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }

    public Map<IPoint, Integer> getMap() {
        return map;
    }

    public void put(IPoint point, Integer value) {
        this.map.put(point, value);
    }

    public Integer get(IPoint point) {
        return this.map.get(point);
    }

    public boolean containsKey(IPoint point) {
        return this.map.containsKey(point);
    }

}
