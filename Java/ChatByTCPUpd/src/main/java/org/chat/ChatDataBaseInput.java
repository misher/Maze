package org.chat;

import java.sql.*;

/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */
public class ChatDataBaseInput {

    // Input Data variable
    private String inputData;
    private int sessionId;
    private int connectionCounter;

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;

    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;

    public ChatDataBaseInput(String inputData, int sessionId, int connectionCounter, String url, String user, String password) {
        super();
        this.sessionId = sessionId;
        this.inputData = inputData;
        this.connectionCounter = connectionCounter;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    void put() throws SQLException {
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing insert queries
            PreparedStatement updateemp = con.prepareStatement ("insert into chatBase.chatTableUpd (id_Session, id_Message_This_Session, message) values(?,?,?)");
            updateemp.setInt(1, sessionId);
            updateemp.setInt(2, connectionCounter);
            updateemp.setString(3, inputData);
            updateemp.executeUpdate();

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


