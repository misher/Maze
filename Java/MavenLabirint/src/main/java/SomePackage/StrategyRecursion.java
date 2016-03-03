package SomePackage;
import java.util.HashSet;


public class StrategyRecursion implements IStrategy{


	HashSet<IPoint> traceHashSet = new HashSet<IPoint>();


	public boolean findNewWay(IMaze maze, IPoint startPoint) {

		if (maze.isTargetPoint(startPoint)){
			return true;
		}
		
		for (IPoint currentPoint : startPoint.getNeighborPoints()) {
			boolean stateCorrectPoint = (maze.isRoadPoint(currentPoint) || maze.isTargetPoint(currentPoint));
			if (stateCorrectPoint && (traceHashSet.add(currentPoint))){
				if (findNewWay(maze, currentPoint)) {
					return true;
				}
			}
		}
		return false;
	}
}
