package org.chat;

/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */

import java.io.Console;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;


public class ChatTCPClient {

    public static void main(String args[]) throws IOException {

        // write user's data from console
        Console console = System.console();
        ReadUserData readUserData = new ReadUserData(console);
        readUserData.ReadUserData();
        User user = readUserData.getUser();

        // start connection
        Socket socket = new Socket("localhost", 3128);

        // convert obj to json and send to server
        JSonConverter jSonConverter = new JSonConverter(readUserData.getUser());
        socket.getOutputStream().write(jSonConverter.converte().getBytes());

        // authorization end check
        AuthorizationEndCheck authorizationEndCheck = new AuthorizationEndCheck(socket);
        authorizationEndCheck.authorizationEndCheck();

        System.out.println("Application was started! Write your messages." + '\n');

        // start to show messages to client
        new ShowMessages(socket);

        // start to send messages to chat
        TransmitMessages transmitMessages = new TransmitMessages(socket, console, user);
        transmitMessages.transmitMessages();

        // close socket after 'exit' and stop system
        socket.close();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}


