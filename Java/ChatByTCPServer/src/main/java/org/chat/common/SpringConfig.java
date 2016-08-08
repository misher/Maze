package org.chat.common;

import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;

/**
 *
 * Created by A.V.Tsaplin on 04.08.2016.
 */

@Configuration
@ComponentScan(basePackages = "org.chat.*")
@PropertySource("classpath:/database.properties")
@EnableTransactionManagement

public class SpringConfig {


    @Autowired
    Environment env;


    @Bean(name = "dataBaseInitialization")
    public int initializeDataBase() throws SQLException {
        // Initialization for dataBases
        DataBaseInfo dataBaseInfo = new DataBaseInfo(env.getProperty("mysql.url"), env.getProperty("mysql.username"), env.getProperty("mysql.password"));
        int sessionId = new DataBaseInit(dataBaseInfo).dataBaseInit();
        return sessionId;
    }


    @Bean(name = "connectionHandler")
    public ConnectionHandler getConnectionHandler() {
        Session thisSession = getSession();
        return new ConnectionHandler(thisSession, getChatTableDao(thisSession));
    }


    @Bean(name = "serverConnection")
    public ServerConnection getServerConnection() throws SQLException {
        ServerConnection serverConnection = new ServerConnection(initializeDataBase(), 0, getConnectionHandler());
        serverConnection.toAcceptConnection();
        return serverConnection;
    }


    @Bean(name = "hibernateUtil")
    public HibernateUtil getHibernateUtil() {
        return new HibernateUtil();
    }


    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(HibernateUtil hibernateUtil) {
        return hibernateUtil.getSessionFactory();
    }


    @Bean(name = "session")
    public Session getSession() {
        return getSessionFactory(getHibernateUtil()).openSession();
    }


    @Bean(name = "chatTableDao")
    public ChatTableDao getChatTableDao(Session session) {
        return new ChatTableDao(session);
    }


}










//
//    @Bean(name = "dataSource")
//    public DataSource getDataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/chatbase");
//        dataSource.setUsername("root");
//        dataSource.setPassword("mercedesg55amg");
//        return dataSource;
//    }

//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//        sessionBuilder.addAnnotatedClasses(ChatTable.class, ChatUsers.class);
//        return sessionBuilder.buildSessionFactory();
//    }
//
//    @Bean
//    public Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        return properties;
//    }
//
//    @Autowired
//    @Bean(name = "hibernateTransactionManager")
//    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
//        return transactionManager;
//    }