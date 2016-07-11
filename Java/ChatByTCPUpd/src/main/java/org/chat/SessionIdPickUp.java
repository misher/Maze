package org.chat;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */

public class  SessionIdPickUp {

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;
    private int sessionId;


    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;

    public SessionIdPickUp (String url, String user, String password) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public int pickUp() throws SQLException {

        final  String selectMaxInt = "SELECT * FROM chatSessionIdUpd ORDER BY id DESC LIMIT 1";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing table select query
            ResultSet resultSet = stmt.executeQuery(selectMaxInt);
            while (resultSet.next()) {
                sessionId = resultSet.getInt(1);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            // close connection and statement
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            return sessionId;
        }

    }

}