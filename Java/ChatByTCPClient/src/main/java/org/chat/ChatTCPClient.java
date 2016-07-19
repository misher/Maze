package org.chat;

/**
 * Created by A.V.Tsaplin on 08.07.2016.
 */

import java.io.Console;
import java.io.IOException;
import java.net.Socket;



public class ChatTCPClient {

    public static void main(String args[]) throws IOException {

        // write user's data from console
        Console console = System.console();
        ReadUserData readUserData = new ReadUserData(console);
        readUserData.ReadUserData();

        // start connection
        Socket socket = new Socket("localhost", 3128);

        // convert obj to json and send to server
        JSonConverter jSonConverter = new JSonConverter(readUserData.getUser());
        socket.getOutputStream().write(jSonConverter.converte().getBytes());

        // authorization end check TODO: Timer fix!
        AuthorizationEndCheck authorizationEndCheck = new AuthorizationEndCheck(socket);
        authorizationEndCheck.authorizationEndCheck();

        System.out.println("Application was started! Write your messages." + '\n');

        // start to show messages to client
        new ShowMessages(socket);

        // TODO: to realize by json
        TransmitMessages transmitMessages = new TransmitMessages(socket, console);
        transmitMessages.transmitMessages();
    }
}







//        while (!writeString.equals("exit")) {
//            writeString = console.readLine();
//            if (!writeString.equals("exit")) {
//
//                writeString = writeString + "  " + socket.getInetAddress().getHostAddress() + ":" +socket.getLocalPort();
//                socket.getOutputStream().write(writeString.getBytes());
//                writeString = "";
//            }
//        }
//        System.out.println("Close app. Bye!");
//        socket.getOutputStream().write("exit".getBytes());
//        socket.close();