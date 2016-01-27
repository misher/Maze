

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

	public boolean findNewWay(Point sp) {


		// Calculate sizes of map
		int borderY = map.length;	 // y-8
		int borderX = map[0].length; // x-9

		boolean state1 = false;
		boolean state2 = false;
		boolean state3 = false;
		boolean state4 = false;
		

		ArrayList<Point> traceList = new ArrayList<Point>();
//		Point currentPoint = new Point(0,0);

		System.out.println("Coordinates X, Y "+sp.getX()+" "+sp.getY());

		
		
		if (map[sp.getY()][sp.getX()] == '$'){
			System.out.println("Found money Coordinates X, Y "+sp.getX()+" "+sp.getY());
			return true;
		}
		

		
		
		Point rightP = sp.getRightPoint();
		if (rightP.getX() < (borderX)) {
			state1 = ((map[rightP.getY()][rightP.getX()] == '0') | (map[rightP.getY()][rightP.getX()] == '$'));
			Point currentPoint = new Point(rightP.getX(),rightP.getY());
//			currentPoint.setXY(startX+1,startY);
			if (state1 & (rightP.getX() != previousPoint.x) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(rightP)) {
					return true;
				}
			}
		}
	
		
		Point bottomP = sp.getBottomPoint();
		if (bottomP.getY() < (borderY)) {
			state2 = ((map[bottomP.getY()][bottomP.getX()] == '0') | (map[bottomP.getY()][bottomP.getX()] == '$'));
			Point currentPoint = new Point(bottomP.getX(),bottomP.getY());
//			currentPoint.setXY(startX,startY+1);
			if (state2 & (bottomP.getY() != previousPoint.y) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(bottomP)) {
					return true;
				}
			}
		}
		
		
		Point leftP = sp.getLeftPoint();
		if (leftP.getX() != -1) {
			state3 = ((map[leftP.getY()][leftP.getX()] == '0') | (map[leftP.getY()][leftP.getX()] == '$'));
			Point currentPoint = new Point(leftP.getX(),leftP.getY());
//			currentPoint.setXY(startX-1,startY);
			if (state3 & (leftP.getX() != previousPoint.x) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(leftP)) {
					return true;
				}
			}
		}
		
		Point topP = sp.getTopPoint();
		if ((topP.getY()) != -1) {
			state4 = ((map[topP.getY()][topP.getX()] == '0') | (map[topP.getY()][topP.getX()] == '$'));
			Point currentPoint = new Point(topP.getX(),topP.getY());
//			currentPoint.setXY(startX,startY-1);
			if (state4 & (topP.getY() != previousPoint.y) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
//				previousPoint.setXY(startX, startY);
				traceList.add(previousPoint);
				if (findNewWay(topP)) {
					return true;
				}
			}
		}
		return false;
	}
}
