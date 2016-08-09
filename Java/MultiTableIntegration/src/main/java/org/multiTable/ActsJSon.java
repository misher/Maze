package org.multiTable;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */

public class ActsJSon {

    @JsonProperty("someActs")
    private List<String> someActs;

    public ActsJSon() {

    }

    public ActsJSon(List<String> someActs) {
        this.someActs = someActs;
    }

    public ActsJSon(int [] someActsArray) {
        AllActs allActs = AllActs.getInstance();
        List<String> intermediateList = new ArrayList<String>(){};
        for (int i = 0; i < someActsArray.length; i++) {
            String oneAct = allActs.getAct(someActsArray[i]);
            intermediateList.add(oneAct);
            setSomeActs(intermediateList);
        }
    }

    public void setSomeActs(List<String> someActs) {
        this.someActs = someActs;
    }

    public List<String> getSomeActs() {
        return someActs;
    }

    public String getOneAct(int id) {
        return someActs.get(id);
    }
}
