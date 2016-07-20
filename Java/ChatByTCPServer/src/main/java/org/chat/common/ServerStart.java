package org.chat.common;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 11.07.2016.
 */

public class ServerStart {

    public static void main (String[] args) throws SQLException, InterruptedException {

        ServerConnection serverConnection = new ServerConnection();
        Server server = new Server();
        server.startServerApp(serverConnection);

    }
}

