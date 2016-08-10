
import org.junit.Test;
import org.multiTable.DataBaseInfo;
import org.multiTable.DataBaseInit;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
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
        Map tableMap = new HashMap<Integer, String>();
        tableMap.put(1, "users");
        tableMap.put(2, "roles");
        while (rs.next()) {
            System.out.println(rs.getString(3));
            assertEquals("Table name", true, tableMap.containsValue(rs.getString(3)));
        }
    }

}