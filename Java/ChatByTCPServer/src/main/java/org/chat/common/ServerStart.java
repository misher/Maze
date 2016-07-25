package org.chat.common;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by A.V.Tsaplin on 11.07.2016.
 */

public class ServerStart {

    public static void main (String[] args) throws SQLException, InterruptedException {

        // Initialization for dataBases
        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        int sessionId = new DataBaseInit(dataBaseInfo).dataBaseInit();

        // Delay
        TimeUnit.SECONDS.sleep(2);

        // Start server
        Server server = new Server();
        server.startServerApp(new ServerConnection(sessionId, 0), new ConnectionHandler());

    }
}

