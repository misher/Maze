package DimensionPackage;


public class StartDimension {
	
	static char[][] mapNew = {{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
		{'1', '0', '0', '0', '0', '0', '0', '0', '1'},
		{'1', '1', '1', '1', '0', '1', '1', '1', '1'},
		{'1', '1', '1', '1', '0', '0', '0', '0', '1'},
		{'1', '1', '0', '0', '0', '1', '0', '1', '1'},
		{'1', '1', '0', '1', '0', '1', '0', '0', '0'},
		{'1', '1', '0', '1', '0', '1', '1', '0', '$'},
		{'1', '1', '1', '1', '1', '1', '1', '1', '1'}};

	public void drawMap(){
		for(char[] g1 : mapNew) {
			for(char g2 : g1) {
				System.out.print(g2);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		//MapMaze someMapMaze = new MapMaze(mapNew);
		//StrategyBifurcation startStrategy = new StrategyBifurcation();
		//startStrategy.findNewWay(someMapMaze, new Point(2, 0));
		//
		int [] startPointArray = {2,0};
		PointDimension startPoint = new PointDimension(2, startPointArray);
		MapMazeDimension someMapMaze = new MapMazeDimension(mapNew);
		StrategyRecursionDimension startRecursion = new StrategyRecursionDimension();
		startRecursion.findNewWay(someMapMaze, startPoint);
	}
}
