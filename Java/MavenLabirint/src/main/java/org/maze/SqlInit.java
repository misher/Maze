package org.maze;

/**
 * Created by A.V.Tsaplin on 03.03.2016.
 */

import java.sql.*;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;


public class SqlInit {

    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;
    private String table;

    // JDBC variables for opening and managing connection
    private Connection con;
    private Statement stmt;

    public SqlInit (String url, String user, String password, String table) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
        this.table = table;
    }

    public void Init() throws SQLException {

        final String queryDropTable = "drop table if exists " + table;

        final String queryCreateTable = "create table if not exists " + table + " (id int(11) not null," +
                " x int(11) not null, y int(11) not null, value int(11) not null, mazeId int(11) not null," +
                " stringNumber int(11) not null AUTO_INCREMENT, primary key(stringNumber))" +
                " engine = INNODB default charset = latin1";
        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing drop query
            stmt.executeUpdate(queryDropTable);
            // executing create query
            stmt.executeUpdate(queryCreateTable);
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

