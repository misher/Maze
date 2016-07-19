package org.chat.common;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class UserDataReceiver {

    private Socket socket;

    public UserDataReceiver(Socket socket) {
        this.socket = socket;
    }

    public String userDataReceiver() throws IOException {

        String data = "";

        // timer start
        DelayThread delayThread = new DelayThread(10);

        // listen port to json
        while ((data.equals("")) && (delayThread.timeOut == false)) {

            // take entering data stream from client socket
            InputStream is = socket.getInputStream();

            // data buffer 64kb
            byte buf[] = new byte[64*1024];

            // read 64kb from client, result - count of really received data
            int bufLength = is.read(buf);

            // new string with a received from client data
            data = new String(buf, 0, bufLength);
        }
        return data;
    }
}
