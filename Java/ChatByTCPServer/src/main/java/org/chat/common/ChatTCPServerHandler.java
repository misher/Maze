package org.chat.common;

/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */

import org.hibernate.Session;

import java.io.*;
import java.net.*;


public class ChatTCPServerHandler extends Thread {

    private Session session;
    private int  sessionId;
    private int num;
    private Socket socket;



    public ChatTCPServerHandler(Session session, int num, Socket socket, int sessionId) {
        // data copy
        this.session = session;
        this.sessionId = sessionId;
        this.num = num;
        this.socket = socket;
        // start new calculation thread (f-ion run)
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }


    public void run()
    {
        try
        {
            // take entering data stream from client socket
            InputStream is = socket.getInputStream();

            // thence - data stream from server to client
            OutputStream os = socket.getOutputStream();

            // data buffer 64kb
            byte buf[] = new byte[64*1024];

            // read 64kb from client, result - count of really received data
            int bufLength = is.read(buf);

            // new string with a received from client data
            String data = new String(buf, 0, bufLength);

            // write data
            os.write(data.getBytes());

            // at this place is data-base transmit function
            session.beginTransaction();
            ChatTable chatTable = new ChatTable();
            chatTable.setIdMessageThisSession(num);
            chatTable.setIdSession(sessionId);
            chatTable.setMessage(data);
            session.save(chatTable);
            session.getTransaction().commit();

            // close connection
            socket.close();
        }
        catch(Exception exception) { // exception handling
            System.out.println("init error: " + exception);
        }
    }
}






//    public static void main(String args[]) throws SQLException {
//
//        DataBaseInit init = new DataBaseInit("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
//        init.init();
//        SessionIdPickUp sessionIdPickUp = new SessionIdPickUp("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
//        sessionId = sessionIdPickUp.pickUp();
//
//        try
//        {
//            // set socket to localhost and port 3128
//            ServerSocket server = new ServerSocket(3128, 0, InetAddress.getByName("localhost"));
//            System.out.println("server is started");
//            // listen port
//            while(true)
//            {
//                // wait a new connect and then handle client
//                // new calculation thread and counter increment
//                new ChatTCPServerHandler(connectCounter, server.accept());
//                connectCounter++;
//            }
//        }
//        catch(Exception exception)
//        {System.out.println("init error: " + exception);} // exception handling
//    }

