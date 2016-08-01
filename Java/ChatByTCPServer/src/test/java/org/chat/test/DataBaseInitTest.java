package org.chat.test;

import org.chat.common.DataBaseInfo;
import org.chat.common.DataBaseInit;
import org.junit.Test;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 25.07.2016.
 */

public class DataBaseInitTest {

    @Test
    public void dataBaseInitTest() throws SQLException {

        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/chatBase", "root", "mercedesg55amg");
        new DataBaseInit(dataBaseInfo).dataBaseInit();

        Connection conn = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword());
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        Map tableMap = new HashMap<Integer, String>();
        tableMap.put(1, "chatsessionid");
        tableMap.put(2, "chattable");
        tableMap.put(3, "chatusers");
        while (rs.next()) {
            System.out.println(rs.getString(3));
            assertEquals("Table name", true, tableMap.containsValue(rs.getString(3)));
        }
    }

}
