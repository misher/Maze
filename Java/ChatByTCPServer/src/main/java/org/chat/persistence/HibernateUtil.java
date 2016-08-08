package org.chat.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

import java.util.Properties;


/**
 *
 * Created by A.V.Tsaplin on 14.07.2016.
 */

@Component ("hibernateUtil")
public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();
//
//    private static String url;
//    private static String user;
//    private static String password;

//    public HibernateUtil(String url, String user, String password) {
//        this.url = url;
//        this.password = password;
//        this.user = user;
//    }

    private static SessionFactory buildSessionFactory() {

        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {

            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            throw new ExceptionInInitializerError("Initial SessionFactory failed " + e);
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

}




//            Properties c = new Properties();
//            c.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/chatBase");
//            c.setProperty("hibernate.connection.username", "root");
//            c.setProperty("hibernate.connection.password", "mercedesg55amg");