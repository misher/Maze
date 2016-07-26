import org.chat.JSonConverter;
import org.chat.Message;
import org.chat.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by A.V.Tsaplin on 19.07.2016.
 */
public class JSonTest {

    User user = new User("Artur", "mercedesg55amg");
    Message message = new Message(user.getUsername(), user.getPassword(), "exit", "addresEx");

    @Test
    public void jSonTest() throws Exception {

        JSonConverter jSonConverter = new JSonConverter(message);
        System.out.println(jSonConverter.converte());
        assertEquals("Check jSon convert ", "{\"username\":\"Artur\",\"password\":\"mercedesg55amg\",\"message\":\"exit\",\"localAddress\":\"addresEx\"}", jSonConverter.converte());

    }
}
