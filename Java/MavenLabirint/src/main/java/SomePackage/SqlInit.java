package SomePackage;

/**
 * Created by A.V.Tsaplin on 03.03.2016.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



//TODO: the class was not covered by tests. How you will be sure that it does what you are expecting without running your programm?
public class SqlInit {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "mercedesg55amg";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;

    private char[][] map;

    public SqlInit (char[][] map) {
        super();
        this.map = map;
    }

    public void Init() {//TODO: check the naming convention!

        /*
        TODO: you are using simple drop table. If the table exists it would be removed. Your next statement is
        create if exists - it would not be existing at creation moment. But it is possible that table would not exists
        when you will try to remove it - and it could fail - so it is better to use if not exist clause when you
        dropping the table.
         */
        final String queryDropTable = "Drop table mapnumberone";

        final String queryCreateTable = "create table if not exists mapnumberone (id int(11) not null," +
                " x int(11) not null, y int(11) not null, value int(11) not null, primary key(id))" +
                " engine = INNODB default charset = latin1";

        int borderY = map.length;    // y-8
        int borderX = map[0].length; // x-9

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing create query
            stmt.executeUpdate(queryCreateTable);
            // executing drop query
            stmt.executeUpdate(queryDropTable);
            // executing create query
            stmt.executeUpdate(queryCreateTable);
            // executing insert queries
            int counterId = 0;
            for (int i = 0; i <  borderX; i++) {
                for (int j = 0; j < borderY; j++) {
                    try {
                        if ((map[j][i] == '0') || (map[j][i] == '2')){
                            //TODO: what is mapnumberone - table name. Rename it to something more clear.
                            String queryInsertData = "INSERT INTO mydb.mapnumberone (id, x, y, value) VALUES (" + counterId + ", " + i + ", " + j + ", " + map[j][i] + ");";
                            stmt.executeUpdate(queryInsertData);
                            counterId++;
                        }
                    } catch (Exception e) {
                        //TODO: you are hiding exception. do something and fix that in all other code.
                    }
                }
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}

