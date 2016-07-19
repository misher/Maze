package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
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



        // data to zero
        String dataExit = "";
        String data;

        // messages receiver
        while (!dataExit.equals("exit")) {

            // take entering data stream from client socket
            InputStream is = socket.getInputStream();

            // data buffer 64kb
            byte buf[] = new byte[64*1024];

            // read 64kb from client, result - count of really received data
            int bufLength = is.read(buf);

            // new string with a received from client data
            data = new String(buf, 0, bufLength);


            // json string data to object
            ObjectMapper mapper = new ObjectMapper();
            if (!data.isEmpty()) {
                ChatMessages chatMessages = mapper.readValue(data, ChatMessages.class);
                dataExit = chatMessages.getMessage();
                // write incoming data's to base
                if (!dataExit.equals("exit")){
                    session.beginTransaction();
                    ChatTable chatTable = new ChatTable();
                    chatTable.setConnectNumbers(num);
                    chatTable.setIdSession(sessionId);
                    chatTable.setMessage(chatMessages.getMessage());
                    chatTable.setAuthor(chatMessages.getUsername());
                    chatTable.setLocalAddress(chatMessages.getLocalAddress());
                    session.save(chatTable);
                    session.getTransaction().commit();
                }
            }
        }
        System.out.println("Current client is disconnected.");
        System.out.println("User message receiver thread was stopped.");
    }
}
