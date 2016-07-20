package org.chat.common;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 20.07.2016.
 */
public class Server implements IServer{

    @Override
    public void startServerApp(IConnection serverConnection) throws SQLException, InterruptedException {

        // Initialization for dataBases
        DataBaseInit dataBaseInit = new DataBaseInit("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        int sessionId = dataBaseInit.dataBaseInit();

        // Delay
        TimeUnit.SECONDS.sleep(2);

        // example of IConnectionHandler
        ConnectionHandler connectionHandler = new ConnectionHandler();

        // Start server
        serverConnection = new ServerConnection(sessionId, 0);
        serverConnection.toAcceptConnection(connectionHandler);
    }
}
