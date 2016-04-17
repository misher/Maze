package SomePackage;


import java.sql.SQLException;
import java.util.ArrayList;

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

	public static void main(String[] args) throws SQLException {


		String url = "jdbc:mysql://localhost:3306/mydb";
		String db = "mydb";
		String user = "root";
		String password = "mercedesg55amg";
		String table = "mapMaze";

		// Start file parser
		FileParser someFileParser = new FileParser("C:/Maze/Java/maze.txt");
		ArrayList<ParePointValue> parePointValueMap = someFileParser.parseFile();

		// DataBase initialization
		SqlInit newSqlInit = new SqlInit(url, table, user, password);
		newSqlInit.Init();

		// Write map to db from side-file
		SqlAddFromFile toSqlFromFile = new SqlAddFromFile(parePointValueMap, url, user, password);
		toSqlFromFile.addFromFile(0, db);

		// Write map to db from array
		SqlAddFromArray toSqlFromArray = new SqlAddFromArray(mapNew, url, user, password);
		toSqlFromArray.addFromArray(1, db);

		// Calculate mazes by recursion
		int [] startPointArray = {2,0};
		Point startPoint = new Point(2, startPointArray);
		SqlOnePointMaze someSqlMaze = new SqlOnePointMaze(url, user, password, table, 1);
		StrategyRecursion startRecursion = new StrategyRecursion();
		startRecursion.findNewWay(someSqlMaze, startPoint);

		System.out.println("Let's calculate next map");

		int [] secondStartPointArray = {4,0};
		Point secondStartPoint = new Point(2, secondStartPointArray);
		SqlOnePointMaze someSecondSqlMaze = new SqlOnePointMaze(url, user, password, table, 0);
		StrategyRecursion startSecondRecursion = new StrategyRecursion();
		startSecondRecursion.findNewWay(someSecondSqlMaze, secondStartPoint);

	}
}
