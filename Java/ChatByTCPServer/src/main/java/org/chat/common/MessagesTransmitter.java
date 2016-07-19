package org.chat.common;

import org.hibernate.Session;

import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 15.07.2016.
 */
public class MessagesTransmitter extends Thread {

    private Socket socket;
    private Session session;
    public volatile boolean stopped;

    public MessagesTransmitter (Socket socket, Session session) {
        super();
        this.socket = socket;
        this.session = session;
        this.stopped = false;
        // start new calculation thread (f-ion run)
        setDaemon(false);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void stopThread() {
        stopped = true;
    }

    public boolean getStopped() {
        return stopped;
    }

    public void run() {
        try {

            int lastMessageIndex = 0;
            int realMessageIndex = 0;

            // create output stream
            OutputStream os = socket.getOutputStream();

            // show 5 last messages to client when it just connected
            List<ChatTable> lastChatTable = session.createQuery("from " + ChatTable.class.getName() + " order by id_message desc").setMaxResults(5).list();
            for (int i = 0; i < lastChatTable.size(); i++) {
                os.write((lastChatTable.get(lastChatTable.size() - 1 - i).getAuthor() + ": " + lastChatTable.get(lastChatTable.size() - 1 - i).getMessage() + '\n').getBytes());
            }
            if (lastChatTable.size() != 0) {
                lastMessageIndex = lastChatTable.get(0).getIdMessage();
            }

            // refresh client messages list
            while (stopped != true) {
                TimeUnit.SECONDS.sleep(1);
                lastChatTable = session.createQuery("from " + ChatTable.class.getName() + " order by id_message desc").setMaxResults(5).list();
                if (lastChatTable.size() != 0) {
                    realMessageIndex = lastChatTable.get(0).getIdMessage();
                }
                if (realMessageIndex > lastMessageIndex) {
                    for (int i = 0; i < (realMessageIndex - lastMessageIndex); i++) {
                        os.write((lastChatTable.get((realMessageIndex - lastMessageIndex - 1) - i).getAuthor() + ": " + lastChatTable.get((realMessageIndex - lastMessageIndex - 1) - i).getMessage() + '\n').getBytes());
                    }
                    lastMessageIndex = realMessageIndex;
                }
            }
            System.out.println("Messages Transmitter was stopped.");
        }
        catch(Exception exception) { // exception handling
            System.out.println("Messages Transmitter error: " + exception);
        }
    }
}
