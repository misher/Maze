package org.chat.test;

import org.chat.common.UserDataReceiver;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


import static org.junit.Assert.assertEquals;

/**
 *
 * Created by A.V.Tsaplin on 22.07.2016.
 */
public class UserDataReceiverTest {

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Test
    public void userDataReceiverTest() {


        final InputStream anyInputStream = new ByteArrayInputStream("test data".getBytes());

        UserDataReceiver userDataReceiver = new UserDataReceiver(anyInputStream);

        try {
            setData(userDataReceiver.userDataReceiver());
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals("UserDataReceiver :", "test data", getData());

    }
}
