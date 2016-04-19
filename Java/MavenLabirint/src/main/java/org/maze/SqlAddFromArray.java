package org.maze;

/**
 * Created by A.V.Tsaplin on 15.04.2016.
 */

import java.sql.*;

public class SqlAddFromArray {

    private char[][] map;

    // JDBC URL, username and password of MySQL server
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String user = "root";
    private String password = "mercedesg55amg";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    public SqlAddFromArray(char[][] map, String url, String user, String password) {
        super();
        this.map = map;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void addFromArray(int mazeId, String someDb) throws SQLException {

        int borderY = map.length;    // y-8
        int borderX = map[0].length; // x-9

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing insert queries
            PreparedStatement updateemp = con.prepareStatement ("insert into " + someDb + ".mapMaze (id, x, y, value, mazeId) values(?,?,?,?,?)");
            int counterId = 0;
            for (int i = 0; i <  borderX; i++) {
                for (int j = 0; j < borderY; j++) {
                    if ((map[j][i] == '0') || (map[j][i] == '2')) {
                        updateemp.setInt(1, counterId);
                        updateemp.setInt(2, i);
                        updateemp.setInt(3, j);
                        updateemp.setInt(4, Character.getNumericValue(map[j][i]));
                        updateemp.setInt(5, mazeId);
                        updateemp.executeUpdate();
                        counterId++;
                    }
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            //close connection ,stmt and result set here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}


