package org.chat.common;


import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 11.07.2016.
 */

public class ServerStart {

    public static void main (String[] args) throws SQLException, InterruptedException {

        // Initialization for dataBases
        DataBaseInit dataBaseInit = new DataBaseInit("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        int sessionId = dataBaseInit.dataBaseInit();

        // Delay
        TimeUnit.SECONDS.sleep(2);

        // Start server
        new ChatTCPServerManyThread(sessionId);

    }
}



//        chatTable = (ChatTable) session.load(ChatTable.class, 1);
//
//        System.out.println("Message Id " + stock.getMessageId());
//        System.out.print("Message " + stock.getMessage());


//        // Try to load message into chatTable
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        session.beginTransaction();
//
//        ChatTable chatTable = new ChatTable();
//
//        chatTable.setIdMessageThisSession(1);
//        chatTable.setIdSession(sessionId);
//        chatTable.setMessage("Hello");
//
//        session.save(chatTable);
//        session.getTransaction().commit();