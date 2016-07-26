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
    private User user;

    public TransmitMessages(Socket socket, Console console, User user) {
        this.socket = socket;
        this.console = console;
        this.user = user;
    }

    public void transmitMessages() throws IOException {
        String writeString = "";
        String address = socket.getInetAddress().getHostAddress() + ":" +socket.getLocalPort();
        while (!writeString.equals("exit")) {
            writeString = console.readLine();
            Message message = new Message(user.getUsername(), user.getPassword(), writeString, address);
            socket.getOutputStream().write((new JSonConverter(message).converte() + "^end^").getBytes());
        }
        System.out.println("Close app. Bye!");
    }
}


//                writeString = writeString + "  " + socket.getInetAddress().getHostAddress() + ":" +socket.getLocalPort();
//                socket.getOutputStream().write(writeString.getBytes());