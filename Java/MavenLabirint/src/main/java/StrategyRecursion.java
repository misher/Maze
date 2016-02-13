import java.util.ArrayList;


public class StrategyRecursion {

	
	public char[][] map;
	public Point previousPoint = new Point(-1,-1);


	public StrategyRecursion(char[][] map){
		super();
		this.map = map;
	}
	
	
	ArrayList<Point> traceList = new ArrayList<Point>();

	
	public boolean findNewWay(Point sp) {


		// Calculate sizes of map
		int borderY = map.length;	 // y-8
		int borderX = map[0].length; // x-9

		
		boolean state1 = false;
		boolean state2 = false;
		boolean state3 = false;
		boolean state4 = false;
		

		System.out.println("Coordinates X, Y "+sp.getX()+" "+sp.getY());

		
		if (map[sp.getY()][sp.getX()] == '$'){
			System.out.println("Found money Coordinates X, Y "+sp.getX()+" "+sp.getY());
			return true;
		}
		
		
		Point rightP = sp.getRightPoint();

		if (rightP.getX() < (borderX)) {
			state1 = ((map[rightP.getY()][rightP.getX()] == '0') | (map[rightP.getY()][rightP.getX()] == '$'));
			Point currentPoint = new Point(rightP.getX(),rightP.getY());
			if (state1 & (rightP.getX() != previousPoint.x) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
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
			if (state2 & (bottomP.getY() != previousPoint.y) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
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
			if (state3 & (leftP.getX() != previousPoint.x) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
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
			if (state4 & (topP.getY() != previousPoint.y) & !(traceList.contains(currentPoint))){
				Point previousPoint = new Point(sp.getX(), sp.getY());
				traceList.add(previousPoint);
				if (findNewWay(topP)) {
					return true;
				}
			}
		}
		
		
		return false;
		
		
	}
	
	
}
