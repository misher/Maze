package org.chat.common;

import org.hibernate.Session;

import java.util.List;

/**
 *
 * Created by A.V.Tsaplin on 18.07.2016.
 */

public class Authorization {

    private Session session;
    private ChatUsers currentUser;

    public Authorization(Session session, ChatUsers currentUser) {
        super();
        this.session = session;
        this.currentUser = currentUser;
    }

    public ChatUsers authorization() {

        session.beginTransaction();
        List<ChatUsers> chatUsers = session.createQuery("from " + ChatUsers.class.getName() + " where (user = '" + currentUser.getUser() + "' and password = '" + currentUser.getPassword() + "')").list();
        session.getTransaction().commit();
        if (!chatUsers.isEmpty()) {
            return chatUsers.get(0);
        }
        return null;
    }
}
