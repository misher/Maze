package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 20.07.2016.
 */
public class ConnectionHandler extends Thread implements IConnectionHandler {

    private ServerData serverData;
    private Session session;

    public ConnectionHandler() {

    }

    @Override
    public void doHandle(ServerData serverData) {
        this.serverData = serverData;
        Thread thread = new Thread(this, "serverThread");
        setDaemon(false);
        setPriority(NORM_PRIORITY);
        thread.start();
    }


    public void run() {

        // create variables to transmitter thread
        MessagesTransmitter messagesTransmitter = null;

        try {

            // create db session
            session = HibernateUtil.getSessionFactory().openSession();

            // listen client for receiving user's data
            UserDataReceiver userDataReceiver = new UserDataReceiver(serverData.getSocket());
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
                    serverData.getSocket().getOutputStream().write("auth correct".getBytes());
                    System.out.println("Authorization is correct!");

                    // thread which show all messages to client
                    messagesTransmitter =  new MessagesTransmitter(serverData.getSocket(), session);

                    // start messages receiver
                    UserMessageReceiver userMessageReceiver = new UserMessageReceiver(serverData.getSocket(), session, serverData.getConnectCounter(), serverData.getSessionId());
                    userMessageReceiver.userMessageReceiver();

                    // stop transmitter thread
                    messagesTransmitter.stopThread();

                    // wait to stop messages-transmitter and close session and connection
                    TimeUnit.SECONDS.sleep(1);
                    session.close();
                    serverData.getSocket().close();
                    System.out.println("Session is closed. Socket is closed.");

                } else {
                    System.out.println("Authorization is failed. User not match!");
                    session.close();
                    serverData.getSocket().close();
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
                serverData.getSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Session is closed. Socket is closed.");
        } finally {
            System.out.println("ChatTCPHandler thread was stopped. 1");
        }
    }
}
