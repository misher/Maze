package org.chat.common;

import java.sql.*;

/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */
public class DataBaseInit {

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;


    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;

    public DataBaseInit (String url, String user, String password) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void dataBaseInit() throws SQLException {

        final String queryCreateTable = "create table if not exists chatTable (id_message int(11) not null  AUTO_INCREMENT," +
                " id_Session int(11) not null, id_Message_This_Session int (11) not null, message varchar(45) not null, primary key(id_message))" +
                " engine = INNODB default charset = latin1";
        final String queryCreateSessionId = "create table if not exists chatSessionId (id int(11) not null  AUTO_INCREMENT," +
                "message char(1) not null, primary key(id))" +
                " engine = INNODB default charset = latin1";
        final  String incrementQuery = "insert into chatBase.chatSessionId ( message) values('X')";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing table create query
            stmt.executeUpdate(queryCreateTable);
            // executing session_Id table create query
            stmt.executeUpdate(queryCreateSessionId);
            // executing session_Id table insert query
            stmt.executeUpdate(incrementQuery);
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
