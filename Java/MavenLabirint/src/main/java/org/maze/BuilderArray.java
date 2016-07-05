package org.maze;

import java.sql.SQLException;

/**
 * Created by A.V.Tsaplin on 04.07.2016.
 */
public class BuilderArray implements IBuilder {

    String url = "jdbc:mysql://localhost:3306/mydb";
    String user = "root";
    String password = "mercedesg55amg";
    String db = "mydb";
    String table = "mapMaze";
    int mazeId;

    private char[][] someArray = null;

    public BuilderArray(char[][] someArray){
        super();
        this.someArray = someArray;
    }

    @Override
    public void doBuild(int mazeId) {
        SqlAddFromArray toSqlFromArray = new SqlAddFromArray(someArray, url, user, password);
        try {
            toSqlFromArray.addFromArray(mazeId, db, table);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
