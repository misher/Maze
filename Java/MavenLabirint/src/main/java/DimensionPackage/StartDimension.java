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
		int [] startPntArr = {2,2,0};
		PointDimension sp = new PointDimension(startPntArr);
		MapMazeDimension someMapMaze = new MapMazeDimension(mapNew);
		StrategyRecursionDimension startRecursion = new StrategyRecursionDimension(2);
		startRecursion.findNewWay(someMapMaze, sp);
	}
}
