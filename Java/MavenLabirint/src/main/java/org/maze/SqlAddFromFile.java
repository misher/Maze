package org.maze;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by A.V.Tsaplin on 15.04.2016.
 */
public class SqlAddFromFile {

    ArrayList<ParePointValue> map;

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;

    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;

    public SqlAddFromFile(ArrayList<ParePointValue> map, String url, String user, String password) {
        super();
        this.map = map;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void addFromFile(int mazeId, String someDb) throws SQLException {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing insert queries
            PreparedStatement updateemp = con.prepareStatement ("insert into " + someDb + ".mapMaze (id, x, y, value, mazeId) values(?,?,?,?,?)");
            for (int counterId = 0; counterId < map.size(); counterId++) {
                updateemp.setInt(1, counterId);
                updateemp.setInt(2, map.get(counterId).getSomePoint().getAxis(0));
                updateemp.setInt(3, map.get(counterId).getSomePoint().getAxis(1));
                updateemp.setInt(4, (map.get(counterId).getSomeValue()-48));  // TODO: delete crutch about ASCII table values
                updateemp.setInt(5, mazeId);
                updateemp.executeUpdate();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            // close connection and statement
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}
