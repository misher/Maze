package DimensionPackage;
import java.util.ArrayList;


public class StrategyRecursionDimension implements IStrategyDimension{

	ArrayList<PointDimension> traceList = new ArrayList<PointDimension>();

	public boolean findNewWay(IMazeDimension maze, PointDimension startPoint) {

		boolean stateCorrectPoint = false;
		
		System.out.print("Coordinates X, Y, Z ... ");
		for (int i = 0; i < startPoint.dimension; i++){
			if (i == (startPoint.dimension - 1)){
				System.out.println(startPoint.getAxis(i)+" ");
				break;
			}
			System.out.print(startPoint.getAxis(i)+" ");
		}

		if (maze.isTargetPoint(startPoint)){
			System.out.print("Found money Coordinates X, Y, Z ... ");
			for (int i = 0; i < startPoint.dimension; i++){
				System.out.print(startPoint.getAxis(i)+" ");
			}
			return true;
		}

		for (int i = 0; i < startPoint.dimension; i++){
			for (AbstractDirection direction : AbstractDirection.values()) {
				PointDimension currentPoint = startPoint.getDirPoint(i, direction);
				stateCorrectPoint = (maze.isRoadPoint(currentPoint) || maze.isTargetPoint(currentPoint)); 		
				if (stateCorrectPoint && (!traceList.contains(currentPoint))){
					PointDimension previousPoint = startPoint;
					traceList.add(previousPoint);
					if (findNewWay(maze, currentPoint)) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
