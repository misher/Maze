package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * Created by A.V.Tsaplin on 20.07.2016.
 */

public class ConnectionHandler extends Thread implements IConnectionHandler, DisposableBean, ApplicationContextAware {

    private static  Logger logConHndl = Logger.getLogger(ConnectionHandler.class.getName());

    private Session session;
    private ApplicationContext applicationContext;

    public ConnectionHandler(Session session, ApplicationContext applicationContext) {
        this.session = session;
        this.applicationContext = applicationContext;
    }


    @Override
    public void doHandle(final ServerData serverData) {

        new Thread(new Runnable() {

            private Socket socket;

            public void run() {

                try {
                    socket = serverData.getSocket();
                    // listen client for receiving user's data
                    UserDataReceiver userDataReceiver = new UserDataReceiver(socket.getInputStream());
                    String data = userDataReceiver.userDataReceiver();

                    if (data != null) {

                        // json string auth data to object
                        ObjectMapper mapper = new ObjectMapper();
                        ChatUsers currentUser = mapper.readValue(data, ChatUsers.class);

                        // check authorization
                        Authorization authorization = new Authorization(session, currentUser);
                        ChatUsers checkUser = authorization.authorization();

                        // compare data's
                        if (checkUser != null){

                            // send confirmation about correct authorization
                            OutputStream socketOutputStream = socket.getOutputStream();
                            InputStream socketInputStream = socket.getInputStream();
                            socketOutputStream.write("auth correct".getBytes());
                            logConHndl.info("Authorization is correct!");

                            // thread which show all messages to client
                            final MessagesTransmitter messagesTransmitter = new MessagesTransmitter(socketOutputStream, session);
                            messagesTransmitter.messageTransmitter();

                            // start messages receiver
                            final UserMessageReceiver userMessageReceiver = new UserMessageReceiver(socketInputStream, serverData.getConnectCounter(), serverData.getSessionId(), new ChatDao(applicationContext, session));
                            userMessageReceiver.userMessageReceiver();

                            IExitHandler userMessageReceiverErrHdlr = new IExitHandler() {
                                @Override
                                public void exitByError(String s, Exception e) {
                                    try {
                                        socket.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    messagesTransmitter.stopThread();
                                }
                                @Override
                                public void exitByRequest() {
                                    try {
                                        socket.close();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    }
                                    messagesTransmitter.stopThread();
                                }
                            };
                            userMessageReceiver.setExitHandler(userMessageReceiverErrHdlr);

                            IExitHandler userMessageTransmitterErrHdlr = new IExitHandler() {
                                @Override
                                public void exitByError(String s, Exception e) {
                                    userMessageReceiver.stopThread();
                                }
                                @Override
                                public void exitByRequest() {
                                    userMessageReceiver.stopThread();
                                }
                            };
                            messagesTransmitter.setExitHandler(userMessageTransmitterErrHdlr);


                        } else {
                            logConHndl.info("Authorization is failed. User not match!");
                        }
                    } else {
                        logConHndl.info("Authorization is failed. Authorization data is empty!");
                    }

                }
                catch(Exception exception) {
                    // exception handling
                    logConHndl.error("Connection handler thread error: " + exception);
                    logConHndl.info("Session is closed. Socket is closed.");
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                } finally {
                    logConHndl.info("Connection handler thread is over.");
                }
            }
        }).start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
