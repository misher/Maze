package org.chat.common;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by A.V.Tsaplin on 20.07.2016.
 */
public class ServerConnection extends Thread implements IConnection {

    private int connectCounter = 0;
    private int sessionId;
    private IConnectionHandler connectionHandler;
    private ServerData serverData;

    public ServerConnection(int sessionId, int connectCounter) {
        this.sessionId = sessionId;
        this.connectCounter = connectCounter;
    }

    public ServerConnection() {

    }

    @Override
    public void run()  {
        try {
            // set socket to localhost and port 3128
            ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("localhost"));
            System.out.println("server is started");

            // listen port
            while(true) {
                // wait a new connect and then handle client
                // new calculation thread and counter increment
//                Socket socket = server.accept();
//                serverData = new ServerData(sessionId, connectCounter, server.accept());
                connectionHandler.doHandle(new ServerData(sessionId, connectCounter, server.accept()));
                connectCounter++;
            }
        }
        catch(Exception exception) { // exception handling
            exception.printStackTrace();
            System.out.println("ServerConnection error 1: " + exception);
        }
    }

    @Override
    public Object toAcceptConnection(IConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        setDaemon(false);
        setPriority(NORM_PRIORITY);
        start();
        return serverData;
    }
}
