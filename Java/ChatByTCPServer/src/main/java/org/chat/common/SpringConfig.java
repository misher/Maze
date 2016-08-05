package org.chat.common;

import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 *
 * Created by A.V.Tsaplin on 04.08.2016.
 */

@Configuration
@ComponentScan("org.chat.common")
@EnableTransactionManagement

public class SpringConfig {

    @Bean(name = "hibernateUtil")
    public HibernateUtil getHibernateUtil() {
        return new HibernateUtil();
    }

//    @Bean(name = "sessionFactory")
//    public SessionFactory getSessionFactory(DataSource dataSource) {
//        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
//        sessionBuilder.addAnnotatedClasses(ChatTable.class, ChatUsers.class);
//        return sessionBuilder.buildSessionFactory();
//    }


    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(HibernateUtil hibernateUtil) {
        return hibernateUtil.getSessionFactory();
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/chatbase");
        dataSource.setUsername("root");
        dataSource.setPassword("mercedesg55amg");
        return dataSource;
    }

    @Bean(name = "session")
    public Session getSession(SessionFactory sessionFactory) {
        return sessionFactory.openSession();
    }

//
//    @Bean
//    public Properties getHibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", "true");
//        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        return properties;
//    }

//    @Autowired
//    @Bean(name = "hibernateTransactionManager")
//    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
//        return transactionManager;
//    }


    @Bean(name = "chatTableDao")
    public ChatTableDao getChatTableDao(Session session) {
        return new ChatTableDao(session);
    }

}
