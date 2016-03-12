package SomePackage;

import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by A.V.Tsaplin on 11.03.2016.
 */
public class SqlTest {

    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/mydb";
    private static final String user = "root";
    private static final String password = "mercedesg55amg";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    static char[][] mapNew = {{'1', '1', '0', '1', '1', '1', '1', '1', '1'},
            {'1', '0', '0', '0', '0', '0', '0', '0', '1'},
            {'1', '1', '1', '1', '0', '1', '1', '1', '1'},
            {'1', '1', '1', '1', '0', '0', '0', '0', '1'},
            {'1', '1', '0', '0', '0', '1', '0', '1', '1'},
            {'1', '1', '0', '1', '0', '1', '0', '0', '1'},
            {'1', '1', '0', '1', '0', '1', '1', '0', '2'},
            {'1', '1', '1', '1', '1', '1', '1', '1', '1'}};

    int borderY = mapNew.length;    // y-8
    int borderX = mapNew[0].length; // x-9

    @Test
    public void connectionTest() {

        String queryDropTable = "Drop table test";

        String queryCreateTable = "create table if not exists test (id int(11) not null," +
                " x int(11) not null, y int(11) not null, value int(11) not null, primary key(id))" +
                " engine = INNODB default charset = latin1";

        String queryColumnsCount = "SELECT COUNT(*) FROM information_schema.COLUMNS WHERE TABLE_NAME='test';";

        String queryCount = "select count(*) from test";

        int numberOfColumns = 2;
        int numberOfRecs = 0;

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing drop query
            stmt.executeUpdate(queryDropTable);
            // executing create query
            stmt.executeUpdate(queryCreateTable);
            // executing insert queries
            for (int i = 0; i <  borderX; i++) {
                for (int j = 0; j < borderY; j++) {
                    try {
                        String queryInsertData = "INSERT INTO mydb.test (id, x, y, value) VALUES (" + (i*borderY+j) + ", " + i + ", " + j + ", " + mapNew[j][i] + ");";
                        stmt.executeUpdate(queryInsertData);
                    } catch (Exception e) {

                    }
                }
            }
            // executing SELECT query
            rs = stmt.executeQuery(queryColumnsCount);
            while (rs.next()) {
                int columns = rs.getInt(1);
                System.out.println("Total number of columns in the table : " + columns);
                numberOfColumns = columns;
            }
            rs = stmt.executeQuery(queryCount);
            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of recs in the table : " + count);
                numberOfRecs = count;
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and result set here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
        assertEquals("number of columns", 4, numberOfColumns);
        assertEquals("number of recs", 72, numberOfRecs);
    }
}
