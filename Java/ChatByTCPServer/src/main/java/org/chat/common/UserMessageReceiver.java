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
    private int num;
    private int sessionId;
    private volatile boolean stopped;
    private IExitHandler exitHandler;

    private static Logger logUserMessageRec = Logger.getLogger(UserMessageReceiver.class.getName());

    private IDaoFactory daoFactory;

    public UserMessageReceiver(InputStream inputStream, int num, int sessionId, IDaoFactory daoFactory) {
        this.inputStream = inputStream;
        this.num = num;
        this.sessionId = sessionId;
        this.stopped = false;
        this.daoFactory = daoFactory;
    }

    public void stopThread () {
        this.stopped = true;
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
                        exitHandler.exitByError("InputStream error.", e);
                        logUserMessageRec.error("User messages receiver IO error. Code 1.");
                        e.printStackTrace();
                        stopped = true;
                        return;
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
                                    exitHandler.exitByError("Json parsing error", e);
                                    stopped = true;
                                    logUserMessageRec.error("User messages receiver IO error. Code 2.");
                                    e.printStackTrace();
                                    return;
                                }
                                if (chatMessages != null) {
                                    dataExit = chatMessages.getMessage();
                                    // write incoming data's to base
                                    if (!dataExit.equals("exit")) {
                                        ChatTable chatTable = new ChatTable(num, sessionId, chatMessages.getMessage(), chatMessages.getUsername(), chatMessages.getLocalAddress());
                                        ChatTableDao chatTableDao = daoFactory.createChatTableDao();
                                        chatTableDao.save(chatTable);
                                        AllowCachig.toAllow();
                                    } else {
                                        exitHandler.exitByRequest();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                logUserMessageRec.info("Current client is disconnected.");
                stopThread();
                logUserMessageRec.info("User message receiver thread was stopped.");
            }
        }).start();
    }

    public void setExitHandler(IExitHandler exitHandler) {
        this.exitHandler = exitHandler;
    }

    public IExitHandler getExitHandler() {
        return exitHandler;
    }
}
