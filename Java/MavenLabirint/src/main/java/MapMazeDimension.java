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
		if ((point.getCoordinate(1) < borderX) && (point.getCoordinate(2) < borderY) && (point.getCoordinate(1) != -1) && (point.getCoordinate(2) != -1)) {
			return map[point.getCoordinate(2)][point.getCoordinate(1)] == '0' || map[point.getCoordinate(2)][point.getCoordinate(1)] == '$'; 
		}
		return false; 
	}
	public boolean isTargetPoint(PointDimension point) {
		if ((point.getCoordinate(1) < borderX) && (point.getCoordinate(2) < borderY) && (point.getCoordinate(1) != -1) && (point.getCoordinate(2) != -1)) {
			return  map[point.getCoordinate(2)][point.getCoordinate(1)] == '$';
		}
		return false; 
	}
}