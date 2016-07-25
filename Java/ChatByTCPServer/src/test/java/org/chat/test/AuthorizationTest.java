package org.chat.test;


import org.chat.common.Authorization;
import org.chat.common.ChatUsers;
import org.chat.common.DataBaseInfo;
import org.chat.common.DataBaseInit;
import org.chat.persistence.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 20.07.2016.
 */

public class AuthorizationTest {

    @Test
    public void authorizationTest() throws IOException, SQLException {

        // Initialization for dataBases
        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        new DataBaseInit(dataBaseInfo).dataBaseInit();


        int idValue = 0;

        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg"); Statement stmt = con.createStatement()) {

            String queryInsert = "insert into chatBase.chatUsers (user, password) values('test', 'test')";
            stmt.executeUpdate(queryInsert);
            String selectMaxInt = "SELECT * FROM chatUsers ORDER BY id_user DESC LIMIT 1";
            ResultSet resultSet = stmt.executeQuery(selectMaxInt);
            while (resultSet.next()) {
                idValue = resultSet.getInt(1);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            throw sqlEx;
        }

        Session session = HibernateUtil.getSessionFactory().openSession();
        ChatUsers chatUsers = new ChatUsers(idValue, "test", "test");
        Authorization authorization = new Authorization(session, chatUsers);
        assertEquals("User match", chatUsers.getUser(), authorization.authorization().getUser());
        assertEquals("Password match", chatUsers.getPassword(), authorization.authorization().getPassword());

    }
}
