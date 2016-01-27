

import java.util.ArrayList;



public class StrategyRecursion {

	public char[][] map;
	public Point bifurcationPoint = new Point(0,0);
	public Point previousPoint = new Point(-1,-1);
	public int counter;


	public StrategyRecursion(char[][] map){
		super();
		this.map = map;
	}

	public boolean findNewWay(int startX, int startY) {


		// Calculate sizes of map
		int borderY = map.length;	 // y-8
		int borderX = map[0].length; // x-9

		boolean state1 = false;
		boolean state2 = false;
		boolean state3 = false;
		boolean state4 = false;
		

		ArrayList<Point> traceList = new ArrayList<Point>();
//		Point currentPoint = new Point(0,0);

		System.out.println("Coordinates X, Y "+startX+" "+startY);

		if (map[startY][startX] == '$'){
			System.out.println("Found money Coordinates X, Y "+startX+" "+startY);
			return true;
		}

		if ((startX+1) < (borderX)) {
			state1 = ((map[startY][startX+1] == '0') | (map[startY][startX+1] == '$'));
			Point currentPoint = new Point(startX+1,startY);
//			currentPoint.setXY(startX+1,startY);
			if (state1 & ((startX+1) != previousPoint.x) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(startX, startY);
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(startX+1, startY)) {
					return true;
				}
			}
		}
		if ((startY+1) < (borderY)) {
			state2 = ((map[startY+1][startX] == '0') | (map[startY+1][startX] == '$'));
			Point currentPoint = new Point(startX,startY+1);
//			currentPoint.setXY(startX,startY+1);
			if (state2 & ((startY+1) != previousPoint.y) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(startX, startY);
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(startX, startY+1)) {
					return true;
				}
			}
		}
		if ((startX-1) != -1) {
			state3 = ((map[startY][startX-1] == '0') | (map[startY][startX-1] == '$'));
			Point currentPoint = new Point(startX-1,startY);
//			currentPoint.setXY(startX-1,startY);
			if (state3 & ((startX-1) != previousPoint.x) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(startX, startY);
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(startX-1, startY)) {
					return true;
				}
			}
		}
		if ((startY-1) != -1) {
			state4 = ((map[startY-1][startX] == '0') | (map[startY-1][startX] == '$'));
			Point currentPoint = new Point(startX,startY-1);
//			currentPoint.setXY(startX,startY-1);
			if (state4 & ((startY-1) != previousPoint.y) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(startX, startY);
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(startX, startY-1)) {
					return true;
				}
			}
		}
		return false;
	}
}
