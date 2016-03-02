package SomePackage;

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

	public boolean isRoadPoint(IPoint point) {
		Point somePoint = (Point) point;
		if ((somePoint.getAxis(0) < borderX) && (somePoint.getAxis(1) < borderY) && (somePoint.getAxis(0) != -1) && (somePoint.getAxis(1) != -1)) {
			System.out.print("Coordinates X, Y, Z ... ");
			for (int i = 0; i < somePoint.dimension; i++){
				if (i == (somePoint.dimension - 1)){
					System.out.println(somePoint.getAxis(i)+" ");
					break;
				}
				System.out.print(somePoint.getAxis(i)+" ");
			}
			return map[somePoint.getAxis(1)][somePoint.getAxis(0)] == '0' || map[somePoint.getAxis(1)][somePoint.getAxis(0)] == '$';
		}
		return false; 
	}
	
	public boolean isTargetPoint(IPoint point) {
		Point somePoint = (Point) point;
		if ((somePoint.getAxis(0) < borderX) && (somePoint.getAxis(1) < borderY) && (somePoint.getAxis(0) != -1) && (somePoint.getAxis(1) != -1)) {
			if (map[somePoint.getAxis(1)][somePoint.getAxis(0)] == '$') {
				System.out.print("Found money Coordinates X, Y, Z ... ");
				for (int i = 0; i < somePoint.dimension; i++){
					System.out.print(somePoint.getAxis(i)+" ");
				}
				return true;
			}
		}
		return false; 
	}
}