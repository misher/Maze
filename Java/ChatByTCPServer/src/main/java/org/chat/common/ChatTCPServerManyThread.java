package org.chat.common;

import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;

import java.net.InetAddress;
import java.net.ServerSocket;


/**
 * Created by A.V.Tsaplin on 11.07.2016.
 */

public class ChatTCPServerManyThread implements Runnable {

    private int connectCounter = 0;
    private int sessionId;
    private Session session;

    Thread thread;

    public ChatTCPServerManyThread(int sessionId) {
        this.sessionId = sessionId;
        // create new thread
        thread = new Thread(this, "serverThread");
        thread.start();
    }

    @Override
    public void run()  {
        try
        {
            // set socket to localhost and port 3128
            ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("localhost"));
            System.out.println("server is started");
            // create db session
            session = HibernateUtil.getSessionFactory().openSession();
            // listen port
            while(true)
            {
                // wait a new connect and then handle client
                // new calculation thread and counter increment
                new ChatTCPServerHandler(session, connectCounter, server.accept(), sessionId);
                connectCounter++;
            }
        }
        catch(Exception exception)
        {System.out.println("init error: " + exception);} // exception handling
    }
}
