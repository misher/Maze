import java.util.ArrayList;


public class StrategyRecursion implements IStrategy{

	
	public Point previousPoint = new Point(-1,-1);
	ArrayList<Point> traceList = new ArrayList<Point>();
	

	public boolean findNewWay(IMaze maze, Point sp) {
	
		boolean stateOfCorPnt = false;
		System.out.println("Coordinates X, Y "+sp.getX()+" "+sp.getY());
		
		if (maze.isTargetPoint(sp)){
			System.out.println("Found money Coordinates X, Y "+sp.getX()+" "+sp.getY());
			return true;
		}
		
		for (Direction dir : Direction.values()) {
			Point curPnt = sp.getDirPoint(dir);
			stateOfCorPnt = (maze.isRoadPoint(curPnt) || maze.isTargetPoint(curPnt));
			if (stateOfCorPnt & !(traceList.contains(curPnt))){
				Point previousPoint = sp;
				traceList.add(previousPoint);
				if (findNewWay(maze, curPnt)) {
					return true;
				}
			}
		}
		return false;
	}
}
