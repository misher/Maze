package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hibernate.Session;



import java.io.IOException;
import java.io.InputStream;


/**
 *
 * Created by A.V.Tsaplin on 18.07.2016.
 */

public class UserMessageReceiver {

    private InputStream inputStream;
    private Session session;
    private int num;
    private int sessionId;
    private ChatTableDao chatTableDao;
    private volatile boolean stopped;

    private static Logger logUserMessageRec = Logger.getLogger(UserMessageReceiver.class.getName());

    public UserMessageReceiver(InputStream inputStream, Session session, int num, int sessionId, ChatTableDao chatTableDao) {
        this.inputStream = inputStream;
        this.session = session;
        this.num = num;
        this.sessionId = sessionId;
        this.chatTableDao = chatTableDao;
        this.stopped = false;
    }

    public void stopThread () {
        this.stopped = true;
    }

    public boolean getStopped() {
        return stopped;
    }

    public void userMessageReceiver() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // data to zero
                String dataExit = "";
                String data;

                // tcp / ip packet's receiver
                IncomingDataContainer incomingDataContainer = new IncomingDataContainer(512);

                // messages receiver
                while ((!dataExit.equals("exit")) || (!stopped)) {

                    // data buffer 64kb
                    byte buf[] = new byte[64 * 1024];

                    // read 64kb from client, result - count of really received data
                    int bufLength = 0;
                    try {
                        bufLength = inputStream.read(buf);
                    } catch (IOException e) {
                        stopThread();
                        logUserMessageRec.error("User messages receiver IO error. Code 1.");
                        e.printStackTrace();
                    }

                    // new string with a received from client data
                    data = new String(buf, 0, bufLength);

                    // add incoming data to buffer
                    if (incomingDataContainer.incomingDataContainer(data)) {
                        String resString = incomingDataContainer.toParse("^end^");
                        if (resString != null) {
                            incomingDataContainer.clearBuffer();
                            // json string data to object
                            ObjectMapper mapper = new ObjectMapper();
                            if (!resString.isEmpty()) {
                                ChatMessages chatMessages = null;
                                try {
                                    chatMessages = mapper.readValue(resString, ChatMessages.class);
                                } catch (IOException e) {
                                    stopThread();
                                    logUserMessageRec.error("User messages receiver IO error. Code 2.");
                                    e.printStackTrace();
                                }
                                if (chatMessages != null) {
                                    dataExit = chatMessages.getMessage();
                                    // write incoming data's to base
                                    if (!dataExit.equals("exit")) {
                                        ChatTable chatTable = new ChatTable(num, sessionId, chatMessages.getMessage(), chatMessages.getUsername(), chatMessages.getLocalAddress());
                                        chatTableDao.save(chatTable);
                                        AllowCachig.toAllow();
                                    } else {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                logUserMessageRec.info("Current client is disconnected.");
                stopped = true;
                logUserMessageRec.info("User message receiver thread was stopped.");
            }
        }).start();
    }
}
