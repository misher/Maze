package SomePackage;
import java.util.ArrayList;


public class StrategyBifurcation implements IStrategy{


	public boolean findNewWay(IMaze maze, IPoint startPoint) {

		// Creating points of running
		IPoint currentPoint = startPoint;
		IPoint bifurcationPoint;

		// Initialization of bifurcation points array
		ArrayList<IPoint> pointsOfBifurcation = new ArrayList<IPoint>();
//		pointsOfBifurcation.add(bifurcationPoint);

		// Initialization of trace array
		ArrayList<IPoint> traceList = new ArrayList<IPoint>();

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
			for (IPoint somePoint : currentPoint.getNeighborPoints()) {
				startDirectionFinder++;
				// Look out for a new ways
				if (startDirectionFinder == 1){
					for (IPoint somePointSecondLoop : currentPoint.getNeighborPoints()) {
						if (maze.isTargetPoint(somePointSecondLoop)) {
							stateOfWhile = false;
							break;
						}
						stateCorRoad = ((!(traceList.contains((somePointSecondLoop)))) && (maze.isRoadPoint((somePointSecondLoop))));
						if (stateCorRoad == true){
							onePointDirectionNumber++;
						}
						if (stateCorRoad == false){
							deadEndCounter++;
						}
					}
				}
				// Find bifurcation point
				if (onePointDirectionNumber > 1) {
					bifurcationPoint = currentPoint;
					pointsOfBifurcation.add(bifurcationPoint);
					bifurcationDelta = 1;
					onePointDirectionNumber = 0;
				}
				stateCorRoad = ((!(traceList.contains(somePoint))) && (maze.isRoadPoint(somePoint)));
				// Way state handler
				if (stateCorRoad == true){
					traceList.add(currentPoint);
					currentPoint = somePoint;
					startDirectionFinder = 0;
					onePointDirectionNumber = 0;
					deadEndCounter = 0;
					break;
				}
				// If hit a dead end
				if (((maze.isRoadPoint((Point) somePoint) == false) || (traceList.contains((Point) somePoint)))  && (deadEndCounter == 4)){
					if ((pointsOfBifurcation.size() > 0) && (bifurcationDelta <= pointsOfBifurcation.size())){
						bifurcationPoint = pointsOfBifurcation.get(pointsOfBifurcation.size()-bifurcationDelta);
						traceList.add(currentPoint);
						currentPoint = bifurcationPoint;
						startDirectionFinder = 0;
						onePointDirectionNumber = 0;
						deadEndCounter = 0;
					}
					if (bifurcationDelta > pointsOfBifurcation.size()) {
						System.out.println("Labirinit has not ways to money");
						return false;
					}
					bifurcationDelta++;
				}
			}
		}
		return true;
	}
}
