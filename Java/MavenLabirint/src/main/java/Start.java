

public class Start {
	
	static char[][] mapNew = {{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
							  {'1', '0', '0', '0', '0', '0', '0', '0', '1'},
							  {'1', '1', '1', '1', '0', '1', '1', '1', '1'},
							  {'1', '1', '1', '1', '0', '0', '0', '0', '1'},
							  {'1', '1', '0', '0', '0', '1', '0', '1', '1'},
							  {'1', '1', '0', '1', '0', '1', '0', '0', '1'},
							  {'1', '1', '0', '1', '0', '1', '1', '1', '1'},
							  {'1', '1', '1', '1', '$', '1', '1', '1', '1'}};
	
	public void drawMap(){
		for(char[] g1 : mapNew) {
			for(char g2 : g1) {
				System.out.print(g2);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
//		StrategyBifurcation startStrategy = new StrategyBifurcation(mapNew);
//		startStrategy.findNewWay(2, 0);
		Point start = new Point(2,0);
		StrategyRecursion startRecursion = new StrategyRecursion(mapNew);
		startRecursion.findNewWay(start);
	}
}
