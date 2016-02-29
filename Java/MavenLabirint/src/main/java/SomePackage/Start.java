package SomePackage;


public class Start {
	
	static char[][] mapNew = {{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
								{'1', '0', '0', '0', '0', '0', '0', '0', '1'},
								{'1', '1', '1', '1', '0', '1', '1', '1', '1'},
								{'1', '1', '1', '1', '0', '0', '0', '0', '1'},
								{'1', '1', '0', '0', '0', '1', '0', '1', '1'},
								{'1', '1', '0', '1', '0', '1', '0', '0', '1'},
								{'1', '1', '0', '1', '0', '1', '1', '0', '1'},
								{'1', '1', '$', '1', '1', '1', '1', '1', '1'}};

	public void drawMap(){
		for(char[] g1 : mapNew) {
			for(char g2 : g1) {
				System.out.print(g2);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		
		int [] startPointArray = {2,0};
		Point startPoint = new Point(2, startPointArray);
		MapMaze someMapMaze = new MapMaze(mapNew);
		StrategyBifurcation startStrategy = new StrategyBifurcation();
		startStrategy.findNewWay(someMapMaze, startPoint);
		
//		int [] startPointArray = {2,0};
//		Point startPoint = new Point(2, startPointArray);
//		MapMaze someMapMaze = new MapMaze(mapNew);
//		StrategyRecursion startRecursion = new StrategyRecursion();
//		startRecursion.findNewWay(someMapMaze, startPoint);
	}
}
