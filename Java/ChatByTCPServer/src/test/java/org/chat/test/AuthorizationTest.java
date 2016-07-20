package org.chat.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.chat.common.Authorization;
import org.chat.common.ChatMessages;
import org.chat.common.ChatUsers;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by A.V.Tsaplin on 20.07.2016.
 */
public class AuthorizationTest {

    @Test
    public void authorizationTest() throws IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ChatUsers chatUsers = new ChatUsers(0, "Artur", "mercedesg55amg");
        Authorization authorization = new Authorization(session, chatUsers);
        assertEquals("User match", chatUsers.getUser(), authorization.authorization().getUser());
        assertEquals("Password match", chatUsers.getPassword(), authorization.authorization().getPassword());
    }
}
