package org.chat;

import java.io.InputStream;
import java.net.Socket;

/**
 * Created by A.V.Tsaplin on 15.07.2016.
 */

public class ShowMessages extends Thread{

    Socket socket;

    public ShowMessages(Socket socket) {
        super();
        this.socket = socket;
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run() {
        try {

            // create variable for input data
            String messages;

            while (true) {
                // take entering data stream from client socket
                InputStream is = socket.getInputStream();
                // data buffer 64kb
                byte buf[] = new byte[64*1024];
                // read 64kb from server, result - count of really received data
                int bufLength = is.read(buf);
                // new string with a received from client data
                messages = new String(buf, 0, bufLength);
                // write data
                System.out.print(messages);
            }

        }
        catch(Exception exception) { // exception handling
            System.out.println("Stop to show messages.");
        }
    }
}
