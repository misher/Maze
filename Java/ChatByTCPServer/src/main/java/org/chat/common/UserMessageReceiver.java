package org.chat.common;

import org.hibernate.Session;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */

public class UserMessageReceiver {

    private Socket socket;
    private Session session;
    private int num;
    private int sessionId;

    public UserMessageReceiver(Socket socket, Session session, int num, int sessionId) {
        this.socket = socket;
        this.session = session;
        this.num = num;
        this.sessionId = sessionId;
    }

    public void userMessageReceiver() throws IOException, InterruptedException {

        // thread which show all messages to client
        MessagesTransmitter messagesTransmitter =  new MessagesTransmitter(socket, session);

        // data to zero
        String data = "";

        // messages receiver
        while (!data.equals("exit")) {

            // take entering data stream from client socket
            InputStream is = socket.getInputStream();

            // data buffer 64kb
            byte buf[] = new byte[64*1024];

            // read 64kb from client, result - count of really received data
            int bufLength = is.read(buf);

            // new string with a received from client data
            data = new String(buf, 0, bufLength);

            // write incoming data's to base
            if ((!data.isEmpty()) && (!data.equals("exit"))){
                session.beginTransaction();
                ChatTable chatTable = new ChatTable();
                chatTable.setIdMessageThisSession(num);
                chatTable.setIdSession(sessionId);
                chatTable.setMessage(data);
                session.save(chatTable);
                session.getTransaction().commit();
            }

        }

        System.out.println("Current client is disconnected.");
        // stop thread and close connection
        messagesTransmitter.stopThread();
        // wait to stop messages-transmitter and close session and connection
        TimeUnit.SECONDS.sleep(1);
        session.close();
        socket.close();
        System.out.println("User message receiver thread was stopped. Session closed. Socket closed.");
    }
}
