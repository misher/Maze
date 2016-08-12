
import org.junit.Test;
import org.multiTable.DataBaseInfo;
import org.multiTable.DataBaseInit;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */


public class DataBaseInitTest {
    @Test
    public void dataBaseInitTest() throws SQLException {

        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/multitablebase", "root", "mercedesg55amg");
        new DataBaseInit(dataBaseInfo).dataBaseInit();

        Connection conn = DriverManager.getConnection(dataBaseInfo.getAddress(), dataBaseInfo.getUser(), dataBaseInfo.getPassword());
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        Map tableMap = new HashMap<>();
        tableMap.put(1, "t_users");
        tableMap.put(2, "t_roles");
        tableMap.put(3, "t_users_roles");
        tableMap.put(4, "t_roles_actions");
        tableMap.put(5, "t_actions");
        while (rs.next()) {
            System.out.println(rs.getString(3));
            assertEquals("Table name", true, tableMap.containsValue(rs.getString(3)));
        }
    }

}