package SomePackage;
import java.util.ArrayList;


public class StrategyRecursion implements IStrategy{

	ArrayList<Point> traceList = new ArrayList<Point>();

	public boolean findNewWay(IMaze maze, Point startPoint) {

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
				Point currentPoint = startPoint.getDirPoint(i, direction);
				stateCorrectPoint = (maze.isRoadPoint(currentPoint) || maze.isTargetPoint(currentPoint)); 		
				if (stateCorrectPoint && (!traceList.contains(currentPoint))){
					Point previousPoint = startPoint;
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
