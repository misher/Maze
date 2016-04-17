package SomePackage;

/**
 * Created by A.V.Tsaplin on 03.03.2016.
 */

import java.sql.*;


public class SqlInit {

    // JDBC URL, username and password of MySQL server
    private String url = "jdbc:mysql://localhost:3306/mydb";
    private String table = "mapMaze";
    private String user = "root";
    private String password = "mercedesg55amg";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    public SqlInit (String url, String table, String user, String password) {
        super();
        this.url = url;
        this.table = table;
        this.user = user;
        this.password = password;
    }

    public void Init() throws SQLException {

        final String queryDropTable = "Drop table if exists " + table;

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
            //close connection ,stmt and result set here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}

