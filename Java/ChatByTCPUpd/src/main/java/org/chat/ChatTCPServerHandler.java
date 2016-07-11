package org.chat;

/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */

import java.io.*;
import java.net.*;
import java.sql.SQLException;


public class ChatTCPServerHandler extends Thread {

    private static int connectCounter = 0;
    private static int  sessionId;
    private Socket socket;
    private int num;


    public ChatTCPServerHandler(int num, Socket socket, int sessionId) {
        // data copy
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

            // add data about socket address
            data = "" + num + ": " + " " + data;

            // write data
            os.write(data.getBytes());

            // at this place would be data-base transmit function
            int sessionId = this.sessionId;
            ChatDataBaseInput dbInput = new ChatDataBaseInput(data, sessionId, connectCounter, "jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
            dbInput.put();
            System.out.println(data);

            // close connection
            socket.close();
        }
        catch(Exception exception)
        {System.out.println("init error: " + exception);} // exception handling
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

