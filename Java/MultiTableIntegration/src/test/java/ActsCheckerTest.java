import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.multiTable.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by A.V.Tsaplin on 10.08.2016.
 */
public class ActsCheckerTest {

    @Test
    public void actsChaeckerTest() throws SQLException, IOException {

        AllActs.getInstance();

        String ableActs = "";
        String name = "artur";
        DataBaseInfo dataBaseInfo = new DataBaseInfo("jdbc:mysql://localhost:3306/multitablebase", "root", "mercedesg55amg");
        DataBaseJoin dataBaseJoin = new DataBaseJoin(dataBaseInfo);
        SomeUser someUser = dataBaseJoin.dataBaseSelectUserByName(name);

        try {
            ableActs = dataBaseJoin.dataBaseSelectByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
        ObjectMapper mapper = new ObjectMapper();
        ActsJSon actsJSon = mapper.readValue(ableActs, ActsJSon.class);

        SomeRole someRole = new SomeRole(someUser.getRoleName(), actsJSon.getSomeActs());
        List<String> actsForCheck = new ArrayList<>();
        actsForCheck.add(AllActs.getAct(6));
        actsForCheck.add(AllActs.getAct(10));
        actsForCheck.add(AllActs.getAct(11));
        assertEquals("Will be true ", true, someRole.check(actsForCheck));
    }
}
