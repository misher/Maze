package org.chat.common;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by A.V.Tsaplin on 20.07.2016.
 */

public class Server implements IServer{

    @Override
    public void startServerApp(IConnection serverConnection, IConnectionHandler connectionHandler) throws SQLException, InterruptedException {

        serverConnection.toAcceptConnection(connectionHandler);

    }
}
