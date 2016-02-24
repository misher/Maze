package DimensionPackage;

public class MapMazeDimension implements IMazeDimension {

	public char[][] map;
	int borderY;
	int borderX;

	public MapMazeDimension (char[][] map) {
		super();
		this.map = map;
		this.borderY = map.length;    // y-8
		this.borderX = map[0].length; // x-9
	}

	public boolean isRoadPoint(PointDimension point) {
		if ((point.getAxis(0) < borderX) && (point.getAxis(1) < borderY) && (point.getAxis(0) != -1) && (point.getAxis(1) != -1)) {
			return map[point.getAxis(1)][point.getAxis(0)] == '0' || map[point.getAxis(1)][point.getAxis(0)] == '$'; 
		}
		return false; 
	}
	
	public boolean isTargetPoint(PointDimension point) {
		if ((point.getAxis(0) < borderX) && (point.getAxis(1) < borderY) && (point.getAxis(0) != -1) && (point.getAxis(1) != -1)) {
			return  map[point.getAxis(1)][point.getAxis(0)] == '$';
		}
		return false; 
	}
}