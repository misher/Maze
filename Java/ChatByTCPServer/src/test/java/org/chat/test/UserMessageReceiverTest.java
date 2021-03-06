package org.chat.test;

import org.chat.common.*;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 25.07.2016.
 */
public class UserMessageReceiverTest {

    @Test
    public void userMessageReceiverTest() throws IOException, InterruptedException {

        String messageDataJSon = "{\"username\":\"test\",\"password\":\"test\",\"message\":\"test\",\"localAddress\":\"test\"}^end^";
        InputStream inputStream = new ByteArrayInputStream(messageDataJSon.getBytes());

        Session session = HibernateUtil.getSessionFactory().openSession();

        // App context
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);


        UserMessageReceiver userMessagesReceiver = new UserMessageReceiver(inputStream, 0, 0, new ChatDao(context, session));
        userMessagesReceiver.userMessageReceiver();

        // wait a few time
        TimeUnit.SECONDS.sleep(2);

        userMessagesReceiver.stopThread();

        List<ChatTable> lastChatTable = session.createQuery("from " + ChatTable.class.getName() + " order by id_message desc").setMaxResults(1).list();
        assertEquals("Author ", lastChatTable.get(0).getAuthor(), "test");
        assertEquals("Password ", lastChatTable.get(0).getAuthor(), "test");
        assertEquals("Message ", lastChatTable.get(0).getAuthor(), "test");
        assertEquals("Address ", lastChatTable.get(0).getAuthor(), "test");

    }
}
