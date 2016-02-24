package DimensionPackage;

import java.util.ArrayList;


public class StrategyBifurcationDimension implements IStrategyDimension{

	public boolean findNewWay(IMazeDimension maze, PointDimension startPoint) {

		// Creating points of running
		PointDimension currentPoint = startPoint;
		PointDimension previousPoint = startPoint;
		int [] arrayBifurcationPoint = {-1, -1};
		PointDimension bifurcationPoint = new PointDimension(2, arrayBifurcationPoint);

		// Initialization of bifurcation points array		
		ArrayList<PointDimension> pointsOfBifurcation = new ArrayList<PointDimension>();
		pointsOfBifurcation.add(bifurcationPoint);

		// Initialization of trace array
		ArrayList<PointDimension> traceList = new ArrayList<PointDimension>();
		traceList.add(previousPoint);

		// Initialization of bifurcation calculating counters
		int bifurcationDelta = 0;
		int onePointDirectionNumber = 0;
		int startDirectionFinder = 0;
		int deadEndCounter = 0;

		// Initialization of boolean constants
		boolean stateOfWhile = true;
		boolean stateCorRoad = false;


		// Loop for finding moneys
		while(stateOfWhile){
			for (int i = 0; i < startPoint.dimension; i++) {
				for (AbstractDirection dir : AbstractDirection.values()) {
					startDirectionFinder++;
					// Look out for a new ways
					if (startDirectionFinder == 1){
						for (int o = 0; o < startPoint.dimension; o++) {
							for (AbstractDirection dir2 : AbstractDirection.values()) {
								if (maze.isTargetPoint(currentPoint.getDirPoint(o, dir2))) {
									System.out.println("Moneys are found. Coordinates: "+(currentPoint.getDirPoint(o, dir2).getAxis(0))+" "+(currentPoint.getDirPoint(o, dir2).getAxis(1)));
									stateOfWhile = false;
									break;
								}
								stateCorRoad = ((!(traceList.contains(currentPoint.getDirPoint(o, dir2)))) && (maze.isRoadPoint(currentPoint.getDirPoint(o, dir2))));
								if (stateCorRoad == true){
									onePointDirectionNumber++;
								} 
								if (stateCorRoad == false){
									deadEndCounter++;
								}
							}
						}
					}
					// Find bifurcation point
					if (onePointDirectionNumber > 1) {
						bifurcationPoint = currentPoint;
						pointsOfBifurcation.add(bifurcationPoint);
						bifurcationDelta = 1;
					}
					stateCorRoad = ((!(traceList.contains(currentPoint.getDirPoint(i, dir)))) && (maze.isRoadPoint(currentPoint.getDirPoint(i, dir))));
					// Way state handler
					if (stateCorRoad == true){
						traceList.add(currentPoint);
						previousPoint = currentPoint;
						currentPoint = currentPoint.getDirPoint(i, dir);
						startDirectionFinder = 0;
						onePointDirectionNumber = 0;
						deadEndCounter = 0;
						System.out.println("Current point "+(currentPoint.getAxis(0))+" "+(currentPoint.getAxis(1)));
					}
					// If hit a dead end
					if (((maze.isRoadPoint(currentPoint.getDirPoint(i, dir)) == false) || (traceList.contains(currentPoint.getDirPoint(i, dir))))  && (deadEndCounter == 4)){
						if (pointsOfBifurcation.size() > 0){
							bifurcationPoint = pointsOfBifurcation.get(pointsOfBifurcation.size()-bifurcationDelta);
							traceList.add(currentPoint);
							currentPoint = bifurcationPoint;
							startDirectionFinder = 0;
							onePointDirectionNumber = 0;
							deadEndCounter = 0;
						}
						if (pointsOfBifurcation.size() == bifurcationDelta) {
							System.out.println("Labirinit has not ways to money");
							return false;
						}
						bifurcationDelta++;
					}
					if (startDirectionFinder == 4){
						startDirectionFinder = 0;
					}
				}
			}
		}
		return true;
	}
}
