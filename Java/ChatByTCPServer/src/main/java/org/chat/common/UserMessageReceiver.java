package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.io.IOException;
import java.io.InputStream;


/**
 *
 * Created by A.V.Tsaplin on 18.07.2016.
 */

public class UserMessageReceiver {

    private InputStream inputStream;
    private Session session;
    private int num;
    private int sessionId;
    private volatile boolean stopped;

    public UserMessageReceiver(InputStream inputStream, Session session, int num, int sessionId) {
        this.inputStream = inputStream;
        this.session = session;
        this.num = num;
        this.sessionId = sessionId;
        this.stopped = false;
    }

    public void stopThread () {
        this.stopped = true;
    }

    public boolean getStopped() {
        return stopped;
    }

    public void userMessageReceiver() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // data to zero
                String dataExit = "";
                String data;

                // messages receiver
                while ((!dataExit.equals("exit")) || (!stopped)) {

                    // data buffer 64kb
                    byte buf[] = new byte[64 * 1024];

                    // read 64kb from client, result - count of really received data
                    int bufLength = 0;
                    try {
                        bufLength = inputStream.read(buf);
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.print("huy");
                    }

                    // new string with a received from client data
                    data = new String(buf, 0, bufLength);

                    // json string data to object
                    ObjectMapper mapper = new ObjectMapper();
                    if (!data.isEmpty()) {
                        ChatMessages chatMessages = null;
                        try {
                            chatMessages = mapper.readValue(data, ChatMessages.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if (chatMessages != null) {
                            dataExit = chatMessages.getMessage();
                            // write incoming data's to base
                            if (!dataExit.equals("exit")) {
                                session.beginTransaction();
                                ChatTable chatTable = new ChatTable();
                                chatTable.setConnectNumbers(num);
                                chatTable.setIdSession(sessionId);
                                chatTable.setMessage(chatMessages.getMessage());
                                chatTable.setAuthor(chatMessages.getUsername());
                                chatTable.setLocalAddress(chatMessages.getLocalAddress());
                                session.save(chatTable);
                                session.getTransaction().commit();
                            } else {
                                break;
                            }
                        }
                    }
                }
                System.out.println("Current client is disconnected.");
                stopped = true;
                System.out.println("User message receiver thread was stopped.");
            }
        }).start();
    }
}
