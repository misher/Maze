package org.chat;
/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */

import java.net.Socket;


public class ChatTCPClient {
    public static void main(String args[])
    {
        try
        {
            // open socket and connect to localhost 3128
            // answer from socket
            Socket socket = new Socket("localhost", 3128);

            // write first argument to outputstream
            // specified in the call, the address of the socket and open its port
            args[0] = args[0] + "   " + socket.getInetAddress().getHostAddress() + ":" +socket.getLocalPort();
            socket.getOutputStream().write(args[0].getBytes());

            // read answer
            byte buf[] = new byte[64*1024];
            int read = socket.getInputStream().read(buf);
            String data = new String(buf, 0, read);

            // write answer
            System.out.println(data);
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} // exception handling
    }
}
