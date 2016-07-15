package org.chat.common;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Created by A.V.Tsaplin on 11.07.2016.
 */

public class ServerStart {

    public static void main (String[] args) throws SQLException, InterruptedException {
        // new ChatTCPServerManyThread();

        // Initialization for dataBases
        DataBaseInit dataBaseInit = new DataBaseInit("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        dataBaseInit.dataBaseInit();

        // Delay
        TimeUnit.SECONDS.sleep(2);

        // Try to load message into chatTable

        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Stock stock = new Stock();

        stock = (Stock) session.load(Stock.class, 1);

        System.out.println("Code " + stock.getStockCode());
        System.out.print("Name " + stock.getStockName());


        session.save(stock);
        session.getTransaction().commit();


    }
}
