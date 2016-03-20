package SomePackage;

/**
 * Created by A.V.Tsaplin on 11.03.2016.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SqlMapCreator {

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;
    private String table;

    public SqlMapCreator(String url, String user, String password, String table) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
        this.table = table;
    }

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public  Map<Point, Integer> getMapFromSql() {

        int numberOfAxes = 2;
        //TODO: not fixed! I see that you trying to make your system working with any size space dimension. But looking
        //for columns number in information_schema - is very bad. Find a way to make it working without that.
        //One solution could be to check query result - how many columns it has - but event that it is not good solution.
        //Think about something better if you will not find anything fix as I wrote and later we will discuss.

        String queryColumnsCount = "SELECT COUNT(*) FROM information_schema.COLUMNS WHERE TABLE_NAME='" + table + "';";
        String queryStringsFromTable = "select * from " + table + " limit 100;";
        Map<Point, Integer> hashSqlMap = new HashMap<Point, Integer>();

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing SELECT query
            rs = stmt.executeQuery(queryColumnsCount);
            while (rs.next()) {
                int columns = rs.getInt(1);
                System.out.println("Total number of columns in the table : " + columns);
                numberOfAxes = columns - 2;
            }
            rs = stmt.executeQuery(queryStringsFromTable);
            while (rs.next()) {
                ArrayList<Integer> somePointAxes = new ArrayList<Integer>();
                for (int i = 0; i < numberOfAxes; i++) {
                    somePointAxes.add(rs.getInt(i+2));
                }
                Point somePoint = new Point(numberOfAxes, somePointAxes);
                hashSqlMap.put(somePoint, rs.getInt(numberOfAxes + 2));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        return hashSqlMap;
    }
}
