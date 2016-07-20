package org.chat.common;

import java.sql.SQLException;

/**
 * Created by A.V.Tsaplin on 20.07.2016.
 */

public interface IServer {
    void startServerApp(IConnection serverConnection) throws SQLException, InterruptedException;
}
