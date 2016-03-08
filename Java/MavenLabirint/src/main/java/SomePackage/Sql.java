package SomePackage;

/**
 * Created by A.V.Tsaplin on 03.03.2016.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sql {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "mercedesg55amg";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static ResultSet rsTwo;


    public static void main(String args[]) {

//      ArrayList<Integer> someArray= new ArrayList<Integer>();
        ArrayList<ArrayList<Integer>> pointsArray = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> xArray = new ArrayList<Integer>();
        ArrayList<Integer> yArray = new ArrayList<Integer>();

        String query = "select X from mapnumberone";
        String queryTwo = "select Y from mapnumberone";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int xAxis = rs.getInt(1);
                xArray.add(xAxis);
            }

            rs = stmt.executeQuery(queryTwo);

            while (rs.next()) {
                int yAxis = rs.getInt(1);
                yArray.add(yAxis);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
        for (int i = 0; i < xArray.size(); i++){
            System.out.print("Point X " + xArray.get(i));
            System.out.println(" Y " + yArray.get(i));
        }

    }

}
