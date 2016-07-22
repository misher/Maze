package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;


import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by A.V.Tsaplin on 20.07.2016.
 */

public class ConnectionHandler extends Thread implements IConnectionHandler {

    private ServerData serverData;

    public ServerData getServerData() {
        return serverData;
    }

    public void setServerData(ServerData serverData) {
        this.serverData = serverData;
    }

    public ConnectionHandler() {

    }

    @Override
    public void doHandle(ServerData serverData) {

        setServerData(serverData);

        new Thread(new Runnable() {

            public void run() {

                // create variables to transmitter thread
                MessagesTransmitter messagesTransmitter = null;

                try (Session session = HibernateUtil.getSessionFactory().openSession(); Socket socket = getServerData().getSocket()) {

                    // listen client for receiving user's data
                    UserDataReceiver userDataReceiver = new UserDataReceiver(socket.getInputStream());
                    String data = userDataReceiver.userDataReceiver();

                    if (!data.equals("")) {

                        // json string auth data to object
                        ObjectMapper mapper = new ObjectMapper();
                        ChatUsers currentUser = mapper.readValue(data, ChatUsers.class);

                        // check authorization
                        Authorization authorization = new Authorization(session, currentUser);
                        ChatUsers checkUser = authorization.authorization();

                        // compare data's
                        if (checkUser != null){

                            // send confirmation about correct authorization
                            socket.getOutputStream().write("auth correct".getBytes());
                            System.out.println("Authorization is correct!");

                            // thread which show all messages to client
                            messagesTransmitter =  new MessagesTransmitter(socket.getOutputStream(), session);

                            // start messages receiver
                            UserMessageReceiver userMessageReceiver = new UserMessageReceiver(socket.getInputStream(), session, getServerData().getConnectCounter(), getServerData().getSessionId());
                            userMessageReceiver.userMessageReceiver();

                            // stop transmitter thread
                            messagesTransmitter.stopThread();

                            // wait to stop messages-transmitter and close session and connection
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println("Session is closed. Socket is closed.");

                        } else {
                            System.out.println("Authorization is failed. User not match!");
                        }
                    } else {
                        System.out.println("Authorization is failed. Authorization data is empty!");
                    }
                }
                catch(Exception exception) {
                    // exception handling
                    System.out.println("ChatTCPServerHandler error: " + exception);
                    // stop transmitter thread
                    if (messagesTransmitter != null) {
                        messagesTransmitter.stopThread();
                    }
                    // wait to stop messages-transmitter and close session and connection
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Session is closed. Socket is closed.");
                } finally {
                    System.out.println("ChatTCPHandler thread was stopped. 1.");
                }
            }
        }).start();
    }
}
