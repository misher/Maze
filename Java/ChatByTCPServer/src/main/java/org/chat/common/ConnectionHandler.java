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


    public ConnectionHandler() {

    }

    @Override
    public void doHandle(final ServerData serverData) {

        new Thread(new Runnable() {

            public void run() {

                // create variables to transmitter thread
                MessagesTransmitter messagesTransmitter = null;
                UserMessageReceiver userMessageReceiver = null;

                try (Session session = HibernateUtil.getSessionFactory().openSession(); Socket socket = serverData.getSocket()) {

                    // listen client for receiving user's data
                    UserDataReceiver userDataReceiver = new UserDataReceiver(socket.getInputStream());
                    String data = userDataReceiver.userDataReceiver();

                    if (data != null) {

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
                            messagesTransmitter.messageTransmitter();

                            // start messages receiver
                            userMessageReceiver = new UserMessageReceiver(socket.getInputStream(), session, serverData.getConnectCounter(), serverData.getSessionId());
                            userMessageReceiver.userMessageReceiver();

                            while (true) {
                                if (userMessageReceiver.getStopped()) {
                                    // stop transmitter thread
                                    messagesTransmitter.stopThread();

                                    // wait to stop messages-transmitter and close session and connection
                                    TimeUnit.MILLISECONDS.sleep(300);
                                    System.out.println("Session is closed. Socket is closed.");

                                    // stop this loop
                                    break;
                                }
                            }

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
                    // stop receiver - transmitter threads
                    if (messagesTransmitter != null) {
                        messagesTransmitter.stopThread();
                    }
                    if (userMessageReceiver != null) {
                        userMessageReceiver.stopThread();
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
