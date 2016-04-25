package org.maze;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by A.V.Tsaplin on 25.04.2016.
 */
public class mySqlTest {

    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String db = "mydb";
    private String user = "root";
    private String password = "mercedesg55amg";
    private String table = "testTable";

    private static Connection con;
    private static Statement stmt;

    static void init(String url, String user, String password, String table, String db) {

        String queryDropTable = "Drop table if exists " + table;
        String queryCreateTable = "create table if not exists " + table + " (id int(11) not null," +
                " x int(11) not null, y int(11) not null, value int(11) not null, mazeId int(11) not null," +
                " stringNumber int(11) not null AUTO_INCREMENT, primary key(stringNumber))";

        char[][] sampleMap = {{'1', '1', '3', '1', '1', '1', '1', '1', '1'},
                {'1', '0', '0', '0', '0', '0', '0', '0', '1'},
                {'1', '1', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '0', '0', '0', '0', '1'},
                {'1', '1', '0', '0', '0', '1', '0', '1', '1'},
                {'1', '1', '0', '1', '0', '1', '0', '0', '1'},
                {'1', '1', '0', '1', '0', '1', '1', '0', '2'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1'}};

        int borderY = sampleMap.length;    // y-8
        int borderX = sampleMap[0].length; // x-9

        try {
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing drop query
            stmt.executeUpdate(queryDropTable);
            // executing create query
            stmt.executeUpdate(queryCreateTable);
            // executing insert queries
            PreparedStatement updateemp = con.prepareStatement("insert into " + table + " (id, x, y, value, mazeId) values(?,?,?,?,?)");
            for (int i = 0; i < borderX; i++) {
                for (int j = 0; j < borderY; j++) {
                    updateemp.setInt(1, i * borderY + j);
                    updateemp.setInt(2, i);
                    updateemp.setInt(3, j);
                    updateemp.setInt(4, sampleMap[j][i] - 48);
                    updateemp.setInt(5, 0);
                    updateemp.executeUpdate();
                }
            }
        } catch(SQLException se) {
            System.out.print("Exception detected");
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
    public void getStartPointTest() {
        init(url, user, password, table, db);
        int [] spArray = {2,0};
        Point sp = new Point (2, spArray);
        SqlGetStartPoint sqlGetStartPoint = new SqlGetStartPoint(url, user, password, table, 0);
        assertEquals("Start point x-Axis check by getStartPoint method", sp.getAxis(0), sqlGetStartPoint.sqlGetStartPoint().getAxis(0));
        assertEquals("Start point y-Axis check by getStartPoint method", sp.getAxis(1), sqlGetStartPoint.sqlGetStartPoint().getAxis(1));
        close();
    }
    @Test
    public void getSomePointTest() {
        init(url, user, password, table, db);
        int [] spArray = {2,0};
        Point sp = new Point (2, spArray);
        SqlOnePointPickUp sqlOnePointPickUp = new SqlOnePointPickUp(url, user, password, table);
        assertEquals("Start point x-Axis check by get some point method", sp.getAxis(0), sqlOnePointPickUp.getPointFromSql(2,0,0).getSomePoint().getAxis(0));
        assertEquals("Start point y-Axis check by get some point method", sp.getAxis(1), sqlOnePointPickUp.getPointFromSql(2,0,0).getSomePoint().getAxis(1));
        close();
    }
}
