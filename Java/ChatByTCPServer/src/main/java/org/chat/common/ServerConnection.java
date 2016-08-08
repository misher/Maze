package org.chat.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;

import java.net.InetAddress;
import java.net.ServerSocket;

/**
 *
 * Created by A.V.Tsaplin on 20.07.2016.
 */

public class ServerConnection implements IConnection, DisposableBean {

    private int sessionId;
    private int connectCounter = 0;
    private IConnectionHandler connectionHandler;

    private static Logger logServerCon = Logger.getLogger(ServerConnection.class.getName());


    public ServerConnection(int sessionId, int connectCounter, IConnectionHandler iConnectionHandlerIn) {
        this.sessionId = sessionId;
        this.connectCounter = connectCounter;
        this.connectionHandler = iConnectionHandlerIn;
    }


    @Override
    public void toAcceptConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // set socket to localhost and port 3128
                    ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("localhost"));
                    logServerCon.info("server is started");
                    // listen port
                    while(true) {
                        // wait a new connect and then handle client
                        // new calculation thread and counter increment
                        final ServerData serverData;
                        serverData = new ServerData(sessionId, connectCounter, server.accept());
                        connectionHandler.doHandle(serverData);
                        connectCounter++;
                    }
                }
                catch(Exception exception) { // exception handling
                    exception.printStackTrace();
                    logServerCon.error("ServerConnection error 1: " + exception);
                }
            }
        }).start();
    }

    @Override
    public void destroy() throws Exception {
        logServerCon.info("Server was stopped.");
    }
}
