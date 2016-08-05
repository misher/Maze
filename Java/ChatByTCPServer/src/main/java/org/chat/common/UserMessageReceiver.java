package org.chat.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import javax.sql.DataSource;
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
    private volatile boolean stopped;

    private static Logger logUserMessageRec = Logger.getLogger(UserMessageReceiver.class.getName());

    public UserMessageReceiver(InputStream inputStream, Session session, int num, int sessionId) {
        this.inputStream = inputStream;
        this.session = session;
        this.num = num;
        this.sessionId = sessionId;
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

                AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//                ApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
//                AbstractApplicationContext appContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
//                ChatTableDao chatTableDao = (ChatTableDao) appContext.getBean("chatTableDao");
//                context.getBean("sessionFactory", context.getBean("dataSource"));
//                Object obj = context.getBean("chatTableDao",  context.getBean("sessionFactory", context.getBean("dataSource")));

//                BasicDataSource basicDataSource = (BasicDataSource) context.getBean("dataSource");
//                SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory", basicDataSource);


                IChatTableDao chatTableDao = (IChatTableDao) context.getBean("chatTableDao");

//                ChatTableDao chatTableDao = (ChatTableDao) context.getBean("chatTableDao",  context.getBean("sessionFactory", context.getBean("dataSource"))) ;

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
//                                        session.beginTransaction();
//                                        ChatTable chatTable = (ChatTable) context.getBean("chatTable");
                                        ChatTable chatTable = new ChatTable();
                                        chatTable.setConnectNumbers(num);
                                        chatTable.setIdSession(sessionId);
                                        chatTable.setMessage(chatMessages.getMessage());
                                        chatTable.setAuthor(chatMessages.getUsername());
                                        chatTable.setLocalAddress(chatMessages.getLocalAddress());
//                                        session.save(chatTable);
//                                        session.getTransaction().commit();
                                        chatTableDao.save(chatTable);
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
