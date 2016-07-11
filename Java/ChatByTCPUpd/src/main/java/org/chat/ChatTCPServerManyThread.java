package org.chat;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.sql.SQLException;

/**
 * Created by A.V.Tsaplin on 11.07.2016.
 */
public class ChatTCPServerManyThread implements Runnable {

    private static int connectCounter = 0;
    private static int  sessionId;

    Thread thread;

    public ChatTCPServerManyThread() {
        // create new thread
        thread = new Thread(this, "serverThread");
        thread.start(); // Запускаем поток
    }

    @Override
    public void run()  {

        DataBaseInit init = new DataBaseInit("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        try {
            init.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SessionIdPickUp sessionIdPickUp = new SessionIdPickUp("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        try {
            sessionId = sessionIdPickUp.pickUp();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try
        {
            // set socket to localhost and port 3128
            ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("localhost"));
            System.out.println("server is started");
            // listen port
            while(true)
            {
                // wait a new connect and then handle client
                // new calculation thread and counter increment
                new ChatTCPServerHandler(connectCounter, server.accept(), sessionId);
                connectCounter++;
            }
        }
        catch(Exception exception)
        {System.out.println("init error: " + exception);} // exception handling
    }


}
