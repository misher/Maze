package SomePackage;
import java.util.ArrayList;
import java.util.HashSet;


public class StrategyRecursion implements IStrategy{


	@SuppressWarnings("rawtypes")
	HashSet<ArrayList> traceHashSet = new HashSet<ArrayList>();

	
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
		
		for (IPoint somePoint : startPoint.getNeighborPoints()) {
			Point currentPoint = (Point) somePoint;
			stateCorrectPoint = (maze.isRoadPoint(currentPoint) || maze.isTargetPoint(currentPoint)); 
			if (stateCorrectPoint && (traceHashSet.add(currentPoint.axes))){
				if (findNewWay(maze, currentPoint)) {
					return true;
				}
			}
		}
		return false;
	}
}
