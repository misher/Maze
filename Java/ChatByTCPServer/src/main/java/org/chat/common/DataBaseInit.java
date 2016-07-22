package org.chat.common;

import java.sql.*;


/**
 *
 * Created by A.V.Tsaplin on 08.07.2016.
 */

public class DataBaseInit {

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;
    private int sessionId;


    public DataBaseInit (String url, String user, String password) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public int dataBaseInit() throws SQLException {

        final String queryCreateUsers = "create table if not exists chatusers (id_user int(11) not null  AUTO_INCREMENT," +
                "user varchar(25) not null, password varchar(25) not null, primary key(id_user))" +
                " engine = INNODB default charset = latin1";
        final String queryCreateTable = "create table if not exists chatTable (id_message int(11) not null  AUTO_INCREMENT," +
                " id_session int(11) not null, connect_numbers int (11) not null, message varchar(45) not null, author varchar(25) not null, " +
                "local_address varchar(40) not null, primary key(id_message)) engine = INNODB default charset = latin1";
        final String queryCreateSessionId = "create table if not exists chatSessionId (id int(11) not null  AUTO_INCREMENT," +
                "message char(1) not null, primary key(id))" +
                " engine = INNODB default charset = latin1";
        final  String incrementQuery = "insert into chatBase.chatSessionId (message) values('X')";
        final  String selectMaxInt = "SELECT * FROM chatSessionId ORDER BY id DESC LIMIT 1";

        try (Connection con = DriverManager.getConnection(url, user, password); Statement stmt = con.createStatement()) {

            stmt.executeUpdate(queryCreateTable);
            // executing users
            stmt.executeUpdate(queryCreateUsers);
            // executing session_Id table create query
            stmt.executeUpdate(queryCreateSessionId);
            // executing session_Id table insert query
            stmt.executeUpdate(incrementQuery);

            // executing table select query
            ResultSet resultSet = stmt.executeQuery(selectMaxInt);
            while (resultSet.next()) {
                sessionId = resultSet.getInt(1);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        }
        return sessionId;
    }
}




// opening database connection to MySQL server
//            con = DriverManager.getConnection(url, user, password);
// getting Statement object to execute query
//            stmt = con.createStatement();
// executing table create query