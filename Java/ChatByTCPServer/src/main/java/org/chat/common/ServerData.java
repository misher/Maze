package org.chat.common;

import java.net.Socket;

/**
 * Created by A.V.Tsaplin on 20.07.2016.
 */
public class ServerData extends Object {

    private int connectCounter = 0;
    private int sessionId;
    private Socket socket;

    public ServerData(int sessionId, int connectCounter, Socket socket) {
        this.sessionId = sessionId;
        this.connectCounter = connectCounter;
        this.socket = socket;
    }

    public int getConnectCounter() {
        return connectCounter;
    }

    public void setConnectCounter(int connectCounter) {
        this.connectCounter = connectCounter;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
