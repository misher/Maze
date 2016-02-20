import java.util.ArrayList;


public class StrategyRecursionDimension {
	
	int dimension;
	PointDimension previousPoint;
	ArrayList<PointDimension> traceList;
	
	public StrategyRecursionDimension(int dimension) {
		super();
		this.dimension = dimension;
		
		ArrayList<Integer> prvPntArr = new ArrayList<Integer>();
		prvPntArr.add(dimension);
		for (int i = 1; i<=dimension; i++){
			prvPntArr.add(-1);
		}
		this.previousPoint = new PointDimension(prvPntArr);
		this.traceList = new ArrayList<PointDimension>();
	}
	

	public boolean findNewWay(IMazeDimension maze, PointDimension sp) {
	
		boolean stateOfCorPnt = false;
		System.out.println("Coordinates X, Y "+sp.getCoordinate(1)+" "+sp.getCoordinate(2));
		
		if (maze.isTargetPoint(sp)){
			System.out.println("Found money Coordinates X, Y "+sp.getCoordinate(1)+" "+sp.getCoordinate(2));
			return true;
		}
		
		for (int i = 1; i <= dimension; i++){
			for (abstractDirection dir : abstractDirection.values()) {
				PointDimension curPnt = sp.getDirPoint(i, dir);
				stateOfCorPnt = (maze.isRoadPoint(curPnt) || maze.isTargetPoint(curPnt));
				if (stateOfCorPnt & !(traceList.contains(curPnt))){
					PointDimension previousPoint = sp;
					traceList.add(previousPoint);
					if (findNewWay(maze, curPnt)) {
						return true;
					}
				}
			}
		}
		return false;
		
	}
}
