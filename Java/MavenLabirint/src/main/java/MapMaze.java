
public class MapMaze implements IMaze {

	public char[][] map;
	int borderY;
	int borderX;

	public MapMaze (char[][] map) {
		super();
		this.map = map;
		this.borderY = map.length;    // y-8
		this.borderX = map[0].length; // x-9
	}


	public boolean isRoadPoint(Point point) {
		if ((point.getX() < borderX) && (point.getY() < borderY) && (point.getX() != -1) && (point.getY() != -1)) {
			return map[point.getY()][point.getX()] == '0' || map[point.getY()][point.getX()] == '$'; 
		}
		return false; 
	}
	public boolean isTargetPoint(Point point) {
		if ((point.getX() < borderX) && (point.getY() < borderY) && (point.getX() != -1) && (point.getY() != -1)) {
			return  map[point.getY()][point.getX()] == '$';
		}
		return false; 
	}
}
