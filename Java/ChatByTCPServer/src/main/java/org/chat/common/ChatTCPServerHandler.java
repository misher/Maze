package org.chat.common;


/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */


import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;
import java.net.*;
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
        try {

            // create db session
            session = HibernateUtil.getSessionFactory().openSession();

            // listen client for receiving user's data
            UserDataReceiver userDataReceiver = new UserDataReceiver(socket);
            String data = userDataReceiver.userDataReceiver();

            if (!data.equals("")) {

                // json string data to object
                ObjectMapper mapper = new ObjectMapper();
                ChatUsers currentUser = mapper.readValue(data, ChatUsers.class);

                // check authorization
                Authorization authorization = new Authorization(socket, session);
                ChatUsers checkUser = authorization.authorization();

                // compare data's
                if ((currentUser.getUser().equals(checkUser.getUser()))&&(currentUser.getPassword().equals(checkUser.getPassword()))){
                    socket.getOutputStream().write("auth correct".getBytes());
                    System.out.println("Authorization is correct!");
                    // start messages receiver
                    UserMessageReceiver userMessageReceiver = new UserMessageReceiver(socket, session, num, sessionId);
                    userMessageReceiver.userMessageReceiver();
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
        } finally {
            System.out.println("ChatTCPHandler thread was stopped.");
        }
    }
}

