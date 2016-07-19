package org.chat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by A.V.Tsaplin on 18.07.2016.
 */
public class JSonConverter {

    Object obj;

    public JSonConverter (Object obj) {
        this.obj = obj;
    }

    public String converte() {
        ObjectMapper mapper = new ObjectMapper();
        String JSonString = null;
        try {
            JSonString = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return JSonString;
    }
}
