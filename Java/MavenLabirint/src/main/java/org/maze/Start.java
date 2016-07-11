package org.maze;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.*;


public class Start {


	private static char[][] mapArray = {{'1', '1', '3', '1', '1', '1', '1', '1', '1'},
								{'1', '0', '0', '0', '0', '0', '0', '0', '1'},
								{'1', '1', '1', '1', '0', '1', '1', '1', '1'},
								{'1', '1', '1', '1', '0', '0', '0', '0', '1'},
								{'1', '1', '0', '0', '0', '1', '0', '1', '1'},
								{'1', '1', '0', '1', '0', '1', '0', '0', '1'},
								{'1', '1', '0', '1', '0', '1', '1', '0', '2'},
								{'1', '1', '1', '1', '1', '1', '1', '1', '1'}};




	private static void drawMap(){
		for(char[] g1 : mapArray) {
			for(char g2 : g1) {
				System.out.print(g2);
			}
			System.out.println();
		}
	}


	public static void main(String[] args) throws SQLException {

		ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");


		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "mercedesg55amg";
		String db = "mydb";
		String table = "mapMaze";

//		ArrayList<ParePointValue> parePointValueMap;

		int mazeId;
		int quantityOfMaps = 1;


		// DataBase initialization
		SqlInit newSqlInit = new SqlInit(url, user, password, table);
		newSqlInit.Init();


//		// Handle of input arguments, which is file directories, record mazes to db
//		for (String someFileDirectory: args) {
//			// Start file parser
//			System.out.println(someFileDirectory);
//			try {
//				InputStream input = new FileInputStream(someFileDirectory);
//				FileParser someFileParser = new FileParser(input);
//				parePointValueMap = someFileParser.parseFile();
//				// Write map to db from side-file
//				SqlAddFromFile toSqlFromFile = new SqlAddFromFile(parePointValueMap, url, user, password);
//				toSqlFromFile.addFromFile(mazeId, db);
//			} catch (IOException ioEx){
//				System.out.println("InputStream exception. Check file exist.");
//				ioEx.printStackTrace();
//			}
//			mazeId++;
//		}
//
//
//		// Write map to db from array
//		SqlAddFromArray toSqlFromArray = new SqlAddFromArray(mapArray, url, user, password);
//		toSqlFromArray.addFromArray(mazeId, db, table);
//		quantityOfMaps = mazeId;


		BuilderDirector builderDirector = new BuilderDirector(mapArray);
		builderDirector.buildSomeMap(0);


		// Let's calculate all maps in db
		for ( mazeId = 0; mazeId <= quantityOfMaps; mazeId++) {
			SqlGetStartPoint getStartPoint = new SqlGetStartPoint(url, user, password, table, mazeId);
			SqlOnePointMaze someSqlMaze = new SqlOnePointMaze(url, user, password, table, mazeId);
			IStrategy startRecursion = (IStrategy) context.getBean("startRecursion");
			startRecursion.findNewWay(someSqlMaze, getStartPoint.sqlGetStartPoint());
		}
	}
}
