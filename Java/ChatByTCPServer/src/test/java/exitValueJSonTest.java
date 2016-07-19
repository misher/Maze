import com.fasterxml.jackson.databind.ObjectMapper;
import org.chat.common.ChatMessages;
import org.junit.Test;

import java.io.IOException;

public class exitValueJSonTest {

    @Test
    public void exitValueJSonTest() throws IOException {
        String data = "{\"username\":\"Artur\",\"password\":\"mercedesg55amg\",\"message\":\"exit\",\"localAddress\":\"addressEx\"}";
        ObjectMapper mapper = new ObjectMapper();
        ChatMessages chatMessages = mapper.readValue(data, ChatMessages.class);
        String dataExit = chatMessages.getMessage();
        System.out.println(dataExit);
    }
}

