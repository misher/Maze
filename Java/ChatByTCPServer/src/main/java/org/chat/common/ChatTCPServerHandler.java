package org.chat.common;


/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */


import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;


public class ChatTCPServerHandler extends Thread {


    private Session session;
    private int  sessionId;
    private int num;
    private Socket socket;


    public ChatTCPServerHandler(int num, Socket socket, int sessionId) {
        // data copy
        this.sessionId = sessionId;
        this.num = num;
        this.socket = socket;
        // start new calculation thread (f-ion run)
        setDaemon(false);
        setPriority(NORM_PRIORITY);
        start();
    }


    public void run() {

        // create variables to transmitter thread
        MessagesTransmitter messagesTransmitter = null;

        try {

            // create db session
            session = HibernateUtil.getSessionFactory().openSession();

            // listen client for receiving user's data
            UserDataReceiver userDataReceiver = new UserDataReceiver(socket);
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
                    messagesTransmitter =  new MessagesTransmitter(socket, session);

                    // start messages receiver
                    UserMessageReceiver userMessageReceiver = new UserMessageReceiver(socket, session, num, sessionId);
                    userMessageReceiver.userMessageReceiver();

                    // stop transmitter thread
                    messagesTransmitter.stopThread();

                    // wait to stop messages-transmitter and close session and connection
                    TimeUnit.SECONDS.sleep(1);
                    session.close();
                    socket.close();
                    System.out.println("Session is closed. Socket is closed.");

                } else {
                    System.out.println("Authorization is failed. User not match!");
                    session.close();
                    socket.close();
                }
            } else {
                System.out.println("Authorization is failed. Authorization data is empty!");
            }
        }
        catch(Exception exception) { // exception handling
            System.out.println("ChatTCPServerHandler error: " + exception);
            // stop transmitter thread
            messagesTransmitter.stopThread();
            // wait to stop messages-transmitter and close session and connection
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            session.close();
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Session is closed. Socket is closed.");
        } finally {
            System.out.println("ChatTCPHandler thread was stopped.");
        }
    }
}

