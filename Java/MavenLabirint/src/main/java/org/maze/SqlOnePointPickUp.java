package org.maze;

/**
 * Created by A.V.Tsaplin on 13.04.2016.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlOnePointPickUp {

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;
    private String table;

    public SqlOnePointPickUp(String url, String user, String password, String table) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
        this.table = table;
    }

    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;
    private ResultSet rs;


    // method which working with query which select one string from db - to pick up one point
    public ParePointValue getPointFromSql(int x, int y, int mazeId) {

        int numberOfAxes = 2;
        String queryStringFromTable = "select * from " + table + " WHERE x = " + x + " AND y = " + y + " AND mazeId = " + mazeId + ";";
        int [] someAxis = {-1,-1};
        Point sp = new Point(2, someAxis);
        ParePointValue retPare = new ParePointValue(sp, -1);

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(queryStringFromTable);
            while (rs.next()) {
                ArrayList<Integer> somePointAxes = new ArrayList<Integer>();
                for (int i = 0; i < numberOfAxes; i++) {
                    somePointAxes.add(rs.getInt(i+2));
                }
                Point somePoint = new Point(numberOfAxes, somePointAxes);
                retPare = new ParePointValue(somePoint, rs.getInt(4));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw new RuntimeException("SQL Exception occurred", sqlEx);
        } finally {
            // close connection, statement and result set here
            try { con.close(); } catch (SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch (SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch (SQLException se) { /*can't do anything */ }
        }
        return retPare;
    }
}

