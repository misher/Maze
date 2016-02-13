
public class AdapterRecursion implements IStrategy{

	public boolean findWay(char[][] map, Point sp) {
		StrategyRecursion startRecursion = new StrategyRecursion(map);
		return startRecursion.findNewWay(sp);
	}
	
}
