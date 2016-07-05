package org.maze;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by A.V.Tsaplin on 04.07.2016.
 */

public class BuilderStr implements IBuilder {


    String url = "jdbc:mysql://localhost:3306/mydb";
    String user = "root";
    String password = "mercedesg55amg";
    String db = "mydb";
    String table = "mapMaze";
    int mazeId;

    private String[] someStr = null;

    public BuilderStr(String[] someStr){
        super();
        this.someStr = someStr;
    }

    @Override
    public void doBuild(int mazeId) {

        for (String someFileDirectory: someStr) {
            // Start file parser
            System.out.println(someFileDirectory);
            try {
                InputStream input = new FileInputStream(someFileDirectory);
                FileParser someFileParser = new FileParser(input);
                ArrayList<ParePointValue> parePointValueMap = someFileParser.parseFile();
                // Write map to db from side-file
                SqlAddFromFile toSqlFromFile = new SqlAddFromFile(parePointValueMap, url, user, password);
                toSqlFromFile.addFromFile(mazeId, db);
            } catch (IOException ioEx){
                System.out.println("InputStream exception. Check file exist.");
                ioEx.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            mazeId++;
        }


    }
}