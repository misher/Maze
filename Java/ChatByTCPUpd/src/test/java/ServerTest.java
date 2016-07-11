/**
 * Created by A.V.Tsaplin on 11.07.2016.
 */

import org.chat.ChatTCPServerManyThread;
import org.chat.DataBaseInit;
import org.junit.Test;

import java.net.Socket;
import java.sql.*;
import static org.junit.Assert.assertEquals;

public class ServerTest extends Thread {

    private String url = "jdbc:mysql://localhost:3306/chatBase";
    private String user = "root";
    private String password = "mercedesg55amg";

    private Connection con;
    private Statement stmt;

    private String lastMessage;


    @Test
    public void serverConInitRecTest() throws Exception {

        // start server to correct test results!!!
        new ChatTCPServerManyThread();

        // check connection and send test message
        try
        {
            // open socket and connect to localhost 3128
            // answer from socket
            Socket testSocket = new Socket("localhost", 3128);
            // send test message to outputstream
            testSocket.getOutputStream().write("Test message".getBytes());
        }
        // exception handling
        catch(Exception e) {
            System.out.println("Warning! init error: " + e);
        }
        finally {
            // set query in String
            final  String selectMaxId = "SELECT * FROM chatTableUpd ORDER BY id_message DESC LIMIT 1";
            // try to db connect
            try {
                // opening database connection to MySQL server
                con = DriverManager.getConnection(url, user, password);
                // getting Statement object to execute query
                stmt = con.createStatement();
                // executing table select query
                ResultSet resultSet = stmt.executeQuery(selectMaxId);
                while (resultSet.next()) {
                    lastMessage = resultSet.getString(4);
                }
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
                throw sqlEx;
            } finally {
                // close connection and statement
                try { con.close(); } catch(SQLException se) { /*can't do anything */ }
                try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
                int len = lastMessage.toCharArray().length;
                String message = "";
                for (int i = 4; i < len; i++) {
                    message = message + lastMessage.toCharArray()[i];
                }
                assertEquals("Check last message ", "Test message", message);
                System.out.print("End of connection, initialization, record test");
            }
        }
    }



    @Test
    public void sessionIdTest() throws Exception {

        int sessionId = 0;
        String sessionValue = "";

        DataBaseInit init = new DataBaseInit("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        init.init();

        final  String selectMaxInt = "SELECT * FROM chatSessionIdUpd ORDER BY id ASC LIMIT 1";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();
            // executing table select query
            ResultSet resultSet = stmt.executeQuery(selectMaxInt);
            while (resultSet.next()) {
                sessionId = resultSet.getInt(1);
                sessionValue = resultSet.getString(2);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        } finally {
            // close connection and statement
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            assertEquals("Check last message ", 1, sessionId);
            assertEquals("Check last message ", "X", sessionValue);
            System.out.println("End of session values test");
        }
    }

}


