import org.junit.Test;
import org.multiTable.JSonConverter;
import org.multiTable.SomeUser;
import sun.plugin2.message.Message;

import static junit.framework.TestCase.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 19.07.2016.
 */
public class JSonTest {

    SomeUser user = new SomeUser(0, "test", "test", "test", "test", "test");

    @Test
    public void jSonTest() throws Exception {

        JSonConverter jSonConverter = new JSonConverter(user);
        assertEquals("Check jSon convert ", "{\"id\":0,\"login\":\"test\",\"password\":\"test\",\"name\":\"test\",\"surname\":\"test\",\"roleName\":\"test\"}", jSonConverter.converte());

    }
}


