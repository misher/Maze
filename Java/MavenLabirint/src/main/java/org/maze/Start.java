package org.maze;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;


public class Start {


	static char[][] mapArray = {{'1', '1', '3', '1', '1', '1', '1', '1', '1'},
								{'1', '0', '0', '0', '0', '0', '0', '0', '1'},
								{'1', '1', '1', '1', '0', '1', '1', '1', '1'},
								{'1', '1', '1', '1', '0', '0', '0', '0', '1'},
								{'1', '1', '0', '0', '0', '1', '0', '1', '1'},
								{'1', '1', '0', '1', '0', '1', '0', '0', '1'},
								{'1', '1', '0', '1', '0', '1', '1', '0', '2'},
								{'1', '1', '1', '1', '1', '1', '1', '1', '1'}};




	public void drawMap(){
		for(char[] g1 : mapArray) {
			for(char g2 : g1) {
				System.out.print(g2);
			}
			System.out.println();
		}
	}


	public static void main(String[] args) throws SQLException {


		String url = "jdbc:mysql://localhost:3306/mydb";
		String user = "root";
		String password = "mercedesg55amg";
		String db = "mydb";
		String table = "mapMaze";

		ArrayList<ParePointValue> parePointValueMap;

		int indexOfMaze = 0;
		int quantityOfMaps;


		// DataBase initialization
		SqlInit newSqlInit = new SqlInit(url, user, password, table);
		newSqlInit.Init();


		// Handle of input arguments, which is file directories, record mazes to db
		for (String someFileDirectory: args) {
			// Start file parser
			System.out.println(someFileDirectory);
			try {
				InputStream input = new FileInputStream(someFileDirectory);
				FileParser someFileParser = new FileParser(input);
				parePointValueMap = someFileParser.parseFile();
				// Write map to db from side-file
				SqlAddFromFile toSqlFromFile = new SqlAddFromFile(parePointValueMap, url, user, password);
				toSqlFromFile.addFromFile(indexOfMaze, db);
			} catch (IOException ioEx){
				System.out.println("InputStream exception. Check file exist.");
				ioEx.printStackTrace();
			}
			indexOfMaze++;
		}


		// Write map to db from array
		SqlAddFromArray toSqlFromArray = new SqlAddFromArray(mapArray, url, user, password);
		toSqlFromArray.addFromArray(indexOfMaze, db, table);
		quantityOfMaps = indexOfMaze;


		// Let's calculate all maps in db
		for ( indexOfMaze = 0; indexOfMaze <= quantityOfMaps; indexOfMaze++) {
			SqlGetStartPoint getStartPoint = new SqlGetStartPoint(url, user, password, table, indexOfMaze);
			SqlOnePointMaze someSqlMaze = new SqlOnePointMaze(url, user, password, table, indexOfMaze);
			StrategyRecursion startRecursion = new StrategyRecursion();
			startRecursion.findNewWay(someSqlMaze, getStartPoint.sqlGetStartPoint());
		}
	}
}
