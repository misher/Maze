
public class AdapterBifurcation implements IStrategy {

	public boolean findWay(char[][] map, Point sp) {
		StrategyBifurcation startStrategy = new StrategyBifurcation(map);
		Point resPoint = startStrategy.findNewWay(sp.getX(), sp.getY());
		if ((resPoint.getX() == -1) && (resPoint.getY() == -1)) {
			return false;
		}		
		return true;
	}

}
