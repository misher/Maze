package SomePackage;


import java.util.ArrayList;
import java.util.HashMap;

public class Start {
	
	static char[][] mapNew = {{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
								{'1', '0', '0', '0', '0', '0', '0', '0', '1'},
								{'1', '1', '1', '1', '0', '1', '1', '1', '1'},
								{'1', '1', '1', '1', '0', '0', '0', '0', '1'},
								{'1', '1', '0', '0', '0', '1', '0', '1', '1'},
								{'1', '1', '0', '1', '0', '1', '0', '0', '1'},
								{'1', '1', '0', '1', '0', '1', '1', '0', '2'},
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

		SqlInit newSqlInit = new SqlInit(mapNew);
		newSqlInit.Init();

//		int [] startPointArray = {2,0};
//		Point startPoint = new Point(2, startPointArray);
//		MapMaze someMapMaze = new MapMaze(mapNew);
//		StrategyBifurcation startStrategy = new StrategyBifurcation();
//		startStrategy.findNewWay(someMapMaze, startPoint);

//		int [] startPointArray = {2,0};
//		Point startPoint = new Point(2, startPointArray);
//		MapMaze someMapMaze = new MapMaze(mapNew);
//		StrategyRecursion startRecursion = new StrategyRecursion();
//

		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "mercedesg55amg";
		String table = "mapnumberone";
		SqlMapCreator someMap = new SqlMapCreator(url, user, password, table);
		HashMap<Point, Integer> mapFromSql = (HashMap<Point, Integer>) someMap.getMapFromSql();

		int [] startPointArray = {2,0};
		Point startPoint = new Point(2, startPointArray);
		SqlMaze someSqlMaze = new SqlMaze(mapFromSql);
		StrategyRecursion startRecursion = new StrategyRecursion();
		startRecursion.findNewWay(someSqlMaze, startPoint);


	}
}
