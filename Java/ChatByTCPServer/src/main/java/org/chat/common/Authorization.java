package org.chat.common;

import org.hibernate.Session;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class Authorization {

    private Socket socket;
    private Session session;

    public Authorization(Socket socket, Session session) {
        super();
        this.socket = socket;
        this.session = session;
    }

    public ChatUsers authorization() {

        session.beginTransaction();
        List<ChatUsers> chatUsers;
        chatUsers = session.createQuery("from " + ChatUsers.class.getName() + " where (user = 'Artur' and password = 'mercedesg55amg')").list();
        session.getTransaction().commit();
        return chatUsers.get(0);

    }
}
