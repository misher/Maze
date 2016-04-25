package org.maze;

import org.junit.Test;

import java.sql.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by A.V.Tsaplin on 11.03.2016.
 */

public class hSqlTest {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:hsqldb:testdb";
    private static final String user = "SA";
    private static final String password = "";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private static final char[][] mapNew = {{'1', '1', '3', '1', '1', '1', '1', '1', '1'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '1'},
            {'1', '1', '1', '1', '0', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '0', '0', '0', '0', '1'},
            {'1', '1', '0', '0', '0', '1', '0', '1', '1'},
            {'1', '1', '0', '1', '0', '1', '0', '0', '1'},
            {'1', '1', '0', '1', '0', '1', '1', '0', '2'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1'}};

    private static final int borderY = mapNew.length;    // y-8
    private static final int borderX = mapNew[0].length; // x-9

    static void init() {

        String queryDropTable = "drop table if exists test";

        String queryCreateTable = "create table test (id int, x int, y int, value int, mazeID int, primary key(id))";

        try {
            Class.forName("org.hsqldb.jdbcDriver");
            // opening database connection to HSQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing drop query
            stmt.executeUpdate(queryDropTable);
            // executing create query
            stmt.executeUpdate(queryCreateTable);
            // executing insert queries
            PreparedStatement updateemp = con.prepareStatement ("insert into test(id, x, y, value, mazeId) values(?,?,?,?,?)");
            for (int i = 0; i <  borderX; i++) {
                for (int j = 0; j < borderY; j++) {
                    updateemp.setInt(1, i*borderY+j );
                    updateemp.setInt(2, i );
                    updateemp.setInt(3, j );
                    updateemp.setInt(4, mapNew[j][i] - 48 );
                    updateemp.setInt(5, 0);
                    updateemp.executeUpdate();
                }
            }
        } catch (SQLException sqlEx) {
            System.out.print("Connection or insert query error. Check it and reload test.");
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    static void close() {
        //close connection and statement
        try {
            con.close();
        } catch (SQLException se) {
            System.out.print("Connection close exception.");
        }
        try {
            stmt.close();
        } catch (SQLException se) {
            System.out.print("Statement close exception.");
        }
    }


    @Test
    public void conAndQueriesTest() throws Exception {
        init();
        int numberOfRecs = 0;
        String queryCount = "select count(*) from test";
        rs = stmt.executeQuery(queryCount);
        while (rs.next()) {
            int count = rs.getInt(1);
            System.out.println("Total number of recs in the table : " + count);
            numberOfRecs = count;
        }
        assertEquals("number of recs", 72, numberOfRecs);
        close();
    }
    @Test
    public void getStartPointTest() {
        init();
        int [] spArray = {2,0};
        Point sp = new Point (2, spArray);
        SqlGetStartPoint sqlGetStartPoint = new SqlGetStartPoint(url, user, password, "test", 0);
        assertEquals("Start point x-Axis check by getStartPoint method", sp.getAxis(0), sqlGetStartPoint.sqlGetStartPoint().getAxis(0));
        assertEquals("Start point y-Axis check by getStartPoint method", sp.getAxis(1), sqlGetStartPoint.sqlGetStartPoint().getAxis(1));
        close();
    }
    @Test
    public void getSomePointTest() {
        init();
        int [] spArray = {2,0};
        Point sp = new Point (2, spArray);
        SqlOnePointPickUp sqlOnePointPickUp = new SqlOnePointPickUp(url, user, password, "test");
        assertEquals("Start point x-Axis check by get some point method", sp.getAxis(0), sqlOnePointPickUp.getPointFromSql(2,0,0).getSomePoint().getAxis(0));
        assertEquals("Start point y-Axis check by get some point method", sp.getAxis(1), sqlOnePointPickUp.getPointFromSql(2,0,0).getSomePoint().getAxis(1));
        close();
    }
}
