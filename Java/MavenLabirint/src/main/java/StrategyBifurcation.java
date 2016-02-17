import java.util.ArrayList;


public class StrategyBifurcation implements IStrategy{

	static private boolean findNewWayOldest(MapMaze maze, Point sp) {

		// Creating points of running
		Point currentPoint = sp;
		Point previousPoint = sp;
		Point bifurcationPoint = new Point(-1, -1);

		// Initialization of bifurcation points array		
		ArrayList<Point> pointsOfBifurcation = new ArrayList<Point>();
		pointsOfBifurcation.add(bifurcationPoint);

		// Initialization of trace array
		ArrayList<Point> traceList = new ArrayList<Point>();
		traceList.add(previousPoint);

		// Initialization of bifurcation calculating counters
		int bifurcationDelta = 0;
		int iterLoopVal = 0;
		int dirCounter = 0;
		int hitCounter = 0;

		// Initialization of boolean constants
		boolean stateOfWhile = true;
		boolean stateCorRoad = false;


		// Loop for finding moneys
		while(stateOfWhile){

			for (Direction dir : Direction.values()) {
				dirCounter++;
				// Look out for a new ways
				if (dirCounter == 1){
					for (Direction dir2 : Direction.values()) {
						if (maze.isTargetPoint(currentPoint.getDirPoint(dir2))) {
							System.out.println("Moneys are found. Coordinates: "+(currentPoint.getDirPoint(dir2).getX())+" "+(currentPoint.getDirPoint(dir2).getY()));
							stateOfWhile = false;
							break;
						}
						stateCorRoad = ((!(traceList.contains(currentPoint.getDirPoint(dir2)))) && (maze.isRoadPoint(currentPoint.getDirPoint(dir2))));
						if (stateCorRoad == true){
							iterLoopVal++;
						} 
						if (stateCorRoad == false){
							hitCounter++;
						}
					}
				}
				// Find bifurcation point
				if (iterLoopVal > 1) {
					bifurcationPoint = currentPoint;
					pointsOfBifurcation.add(bifurcationPoint);
					bifurcationDelta = 1;
				}
				stateCorRoad = ((!(traceList.contains(currentPoint.getDirPoint(dir)))) && (maze.isRoadPoint(currentPoint.getDirPoint(dir))));
				// Way state handler
				if (stateCorRoad == true){
					if (bifurcationPoint.equals(currentPoint)){
						maze.map[currentPoint.getDirPoint(dir).getY()][currentPoint.getDirPoint(dir).getX()]='X';
					}
					traceList.add(currentPoint);
					previousPoint = currentPoint;
					currentPoint = currentPoint.getDirPoint(dir);
					dirCounter = 0;
					iterLoopVal = 0;
					hitCounter = 0;
					System.out.println("Current point "+(currentPoint.getX())+" "+(currentPoint.getY()));
				}
				// If hit a dead end
				if (((maze.isRoadPoint(currentPoint.getDirPoint(dir)) == false) || (traceList.contains(currentPoint.getDirPoint(dir))))  && (hitCounter == 4)){
					if (pointsOfBifurcation.size() > 0){
						bifurcationPoint = pointsOfBifurcation.get(pointsOfBifurcation.size()-bifurcationDelta);
						currentPoint = bifurcationPoint;
						dirCounter = 0;
						iterLoopVal = 0;
						hitCounter = 0;
					}
					if (pointsOfBifurcation.size() == bifurcationDelta) {
						System.out.println("Labirinit has not ways to money");
						return false;
					}
					bifurcationDelta++;
				}
				if (dirCounter == 4){
					dirCounter = 0;
				}
			}
		}
		return true;
	}

	public boolean findNewWay(IMaze maze, Point sp) {
		
		char[][] mapNew = {{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
			  {'1', '0', '0', '0', '0', '0', '0', '0', '1'},
			  {'1', '1', '1', '1', '0', '1', '1', '1', '1'},
			  {'1', '1', '1', '1', '0', '0', '0', '0', '1'},
			  {'1', '1', '0', '0', '0', '1', '0', '1', '1'},
			  {'1', '1', '0', '1', '0', '1', '0', '$', '1'},
			  {'1', '1', '0', '1', '0', '1', '1', '1', '1'},
			  {'1', '1', '1', '1', '1', '1', '1', '1', '1'}};

		MapMaze someMapMaze = new MapMaze(mapNew);
		maze = someMapMaze;
				
		return findNewWayOldest(someMapMaze, sp);
		
	}
}
