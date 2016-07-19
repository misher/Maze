package org.chat;

import java.io.Console;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class TransmitMessages {

    private Socket socket;
    private Console console;

    public TransmitMessages(Socket socket, Console console) {
        this.socket = socket;
        this.console = console;
    }

    public void transmitMessages() throws IOException {
        String writeString = "";
        while (!writeString.equals("exit")) {
            writeString = console.readLine();
            if (!writeString.equals("exit")) {
                // TODO: to realize by json
                writeString = writeString + "  " + socket.getInetAddress().getHostAddress() + ":" +socket.getLocalPort();
                socket.getOutputStream().write(writeString.getBytes());
                writeString = "";
            }
        }
        System.out.println("Close app. Bye!");
        socket.getOutputStream().write("exit".getBytes());
        socket.close();
    }

}
