package org.chat;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class AuthorizationEndCheck {

    private Socket socket;

    public AuthorizationEndCheck(Socket socket) {
        this.socket = socket;
    }

    public boolean authorizationEndCheck() throws IOException {

        boolean auth = false;

        // Timer start
        DelayThread delayThread = new DelayThread(10);

        while (auth == false) {

            // take entering data stream from server socket
            InputStream is = socket.getInputStream();

            // data buffer 64kb
            byte buf[] = new byte[64*1024];

            // read 64kb from client, result - count of really received data
            int bufLength = is.read(buf);

            // new string with a received from client data
            String data = new String(buf, 0, bufLength);

            if ((auth == false) && (delayThread.getTimeOut() == true)) {
                socket.close();
                System.out.println("Authorization crash");
                System.exit(0);
            }

            if (data.equals("auth correct")) {
                auth = true;
                break;
            }
        }

        return auth;


    }

}
