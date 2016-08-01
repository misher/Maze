package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;


import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by A.V.Tsaplin on 20.07.2016.
 */

public class ConnectionHandler extends Thread implements IConnectionHandler {

    private static  Logger logConHndl = Logger.getLogger(ConnectionHandler.class.getName());

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
                            logConHndl.info("Authorization is correct!");

                            // thread which show all messages to client
                            messagesTransmitter =  new MessagesTransmitter(socket.getOutputStream(), session);
                            messagesTransmitter.messageTransmitter();

                            // start messages receiver
                            userMessageReceiver = new UserMessageReceiver(socket.getInputStream(), session, serverData.getConnectCounter(), serverData.getSessionId());
                            userMessageReceiver.userMessageReceiver();

                            while (true) {
                                if (userMessageReceiver.getStopped() || messagesTransmitter.getStopped()) {
                                    // stop threads
                                    messagesTransmitter.stopThread();
                                    userMessageReceiver.stopThread();

                                    // wait to stop messages-transmitter and close session and connection
                                    TimeUnit.MILLISECONDS.sleep(300);
                                    logConHndl.info("Session is closed. Socket is closed.");
                                    // stop this loop
                                    break;
                                }
                            }

                        } else {
                            logConHndl.info("Authorization is failed. User not match!");
                        }
                    } else {
                        logConHndl.info("Authorization is failed. Authorization data is empty!");
                    }
                }
                catch(Exception exception) {
                    // exception handling
                    logConHndl.error("ChatTCPServerHandler error: " + exception);
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
                        logConHndl.error("Timer error. 1.");
                        e.printStackTrace();
                    }
                    logConHndl.info("Session is closed. Socket is closed.");
                } finally {
                    logConHndl.info("ChatTCPHandler thread was stopped. 1.");
                }
            }
        }).start();
    }
}
