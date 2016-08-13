import org.junit.Test;
import org.multiTable.CheckUserForActionHibernate;
import org.multiTable.DataBaseInfo;
import org.multiTable.DataBaseInit;
import org.multiTable.FieldToSearchEnum;

import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 13.08.2016.
 */

public class CheckUserForActionHibernateTest {

    @Test
    public void checkUserForActionHibernateTest() {
        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/multiTableBase", "root", "mercedesg55amg");
        DataBaseInit dataBaseInit = new DataBaseInit(dataBaseInfo);
        try {
            dataBaseInit.dataBaseInit();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Data base was initialized successful.");

        CheckUserForActionHibernate checkUserForActionHibernate = new CheckUserForActionHibernate();
        assertEquals("Check method", false, checkUserForActionHibernate.checkUserForActionHibernate(FieldToSearchEnum.NAME, "ilya", "createCompany"));
        assertEquals("Check method", false, checkUserForActionHibernate.checkUserForActionHibernate(FieldToSearchEnum.SURNAME, "isakin", "createCompany"));
        assertEquals("Check method", true, checkUserForActionHibernate.checkUserForActionHibernate(FieldToSearchEnum.NAME, "artur", "createCompany"));
    }

}
