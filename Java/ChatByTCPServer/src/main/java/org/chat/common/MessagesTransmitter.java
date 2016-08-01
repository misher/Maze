package org.chat.common;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * Created by A.V.Tsaplin on 15.07.2016.
 */

public class MessagesTransmitter {

    private OutputStream outputStream;
    private Session session;
    private volatile boolean stopped;

    private static Logger logMesTrans = Logger.getLogger(MessagesTransmitter.class.getName());

    public MessagesTransmitter (OutputStream outputStream, Session session) {
        super();
        this.outputStream = outputStream;
        this.session = session;
        this.stopped = false;
    }

    public void stopThread() {
        stopped = true;
    }

    public boolean getStopped() {
        return stopped;
    }

    public void messageTransmitter() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    int lastMessageIndex = 0;
                    int realMessageIndex = 0;

                    // show 5 last messages to client when it just connected
                    List<ChatTable> lastChatTable = session.createQuery("from " + ChatTable.class.getName() + " order by id_message desc").setMaxResults(5).list();
                    for (int i = 0; i < lastChatTable.size(); i++) {
                        outputStream.write((lastChatTable.get(lastChatTable.size() - 1 - i).getAuthor() + ": " + lastChatTable.get(lastChatTable.size() - 1 - i).getMessage() + '\n' + "^end^").getBytes());
                    }
                    if (lastChatTable.size() != 0) {
                        lastMessageIndex = lastChatTable.get(0).getIdMessage();
                    }

                    // refresh client messages list
                    while (!stopped) {
                        TimeUnit.SECONDS.sleep(1);
                        lastChatTable = session.createQuery("from " + ChatTable.class.getName() + " order by id_message desc").setMaxResults(5).list();
                        if (lastChatTable.size() != 0) {
                            realMessageIndex = lastChatTable.get(0).getIdMessage();
                        }
                        if (realMessageIndex > lastMessageIndex) {
                            for (int i = 0; i < (realMessageIndex - lastMessageIndex); i++) {
                                outputStream.write((lastChatTable.get((realMessageIndex - lastMessageIndex - 1) - i).getAuthor() + ": " + lastChatTable.get((realMessageIndex - lastMessageIndex - 1) - i).getMessage() + '\n' + "^end^").getBytes());
                            }
                            lastMessageIndex = realMessageIndex;
                        }
                    }
                    logMesTrans.info("Messages Transmitter was stopped.");
                }
                catch(Exception exception) { // exception handling
                    logMesTrans.error("Messages Transmitter error: " + exception);
                    stopThread();
                    exception.printStackTrace();
                }
            }
        }).start();
    }
}
