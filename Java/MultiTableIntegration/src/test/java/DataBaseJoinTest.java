import org.junit.Test;
import org.multiTable.DataBaseInfo;
import org.multiTable.DataBaseInit;
import org.multiTable.DataBaseJoin;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */


public class DataBaseJoinTest {

    @Test
    public void DataBaseJoinTest() throws SQLException {

        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/multitablebase", "root", "mercedesg55amg");
        (new DataBaseInit(dataBaseInfo)).dataBaseInit();

        DataBaseJoin dataBaseJoin = new DataBaseJoin(dataBaseInfo);
        assertEquals("Name ", "artur", dataBaseJoin.dataBaseSelectUserByName("artur").getName());
        assertEquals("Surname ", "tsaplin", dataBaseJoin.dataBaseSelectUserByName("artur").getSurname());
        assertEquals("Login ", "vulgarusart", dataBaseJoin.dataBaseSelectUserByName("artur").getLogin());
        assertEquals("Password ", "mercedesg55amg", dataBaseJoin.dataBaseSelectUserByName("artur").getPassword());

    }

}
