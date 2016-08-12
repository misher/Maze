import org.junit.Test;
import org.multiTable.CheckUserForAction;
import org.multiTable.DataBaseInfo;
import org.multiTable.DataBaseInit;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 12.08.2016.
 */

public class CheckUserForActionTest {

    @Test
    public void checkUserTForActionTest() throws SQLException {

        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/multiTableBase", "root", "mercedesg55amg");
        DataBaseInit dataBaseInit = new DataBaseInit(dataBaseInfo);
        try {
            dataBaseInit.dataBaseInit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Data base was initialized successful.");

        CheckUserForAction checkUserForAction = new CheckUserForAction(dataBaseInfo);
        assertEquals("By name: ", true, checkUserForAction.checkUserForActionByName("artur", "updateRepository"));
        assertEquals("By surname: ", true, checkUserForAction.checkUserForActionBySurname("tsaplin", "createCompany"));
        assertEquals("By login: ", true, checkUserForAction.checkUserForActionByLogin("misher", "hirePersonal"));

    }

}
