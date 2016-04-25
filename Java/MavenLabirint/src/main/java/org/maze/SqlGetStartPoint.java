package org.maze;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A.V.Tsaplin on 21.04.2016.
 */
public class SqlGetStartPoint {

    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;
    private ResultSet rs;

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;
    private String table;
    private int indexOfMaze;

    public SqlGetStartPoint(String url, String user, String password, String table, int indexOfMaze) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
        this.table = table;
        this.indexOfMaze = indexOfMaze;
    }

    public Point sqlGetStartPoint() {
        int retPointAxes[] = {-1, -1};
        Point retPoint = new Point(2, retPointAxes);
        String queryStringFromTable = "select * from " + table + " WHERE value = 3 AND mazeId = " + indexOfMaze + ";";
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(queryStringFromTable);
            // fill array of axes
            while (rs.next()) {
                retPointAxes[0] = rs.getInt(2);
                retPointAxes[1] = rs.getInt(3);
            }
            // set returning point
            retPoint = new Point(2, retPointAxes);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException("SQL Exception occurred", sqlEx);
        } finally {
            // close connection, statement and result set
            try { con.close(); } catch (SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch (SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch (SQLException se) { /*can't do anything */ }
            return retPoint;
        }
    }
}
