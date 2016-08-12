package org.multiTable;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by A.V.Tsaplin on 10.08.2016.
 */
public class AllActs {

    public static AllActs instance = null;

    private static Map<Integer, String> acts = null;

    public AllActs() {
        acts = new HashMap<>();
        acts.put(0, "createCompany");
        acts.put(1, "buyAnotherCompany");
        acts.put(2, "sellSomethingCompany");
        acts.put(3, "destroyCompany");
        acts.put(4, "hirePersonal");
        acts.put(5, "firePersonal");
        acts.put(6, "changeDataBase");
        acts.put(7, "deleteDataBase");
        acts.put(8, "createLocalNetwork");
        acts.put(9, "destroyLocalNetwork");
        acts.put(10, "updateRepository");
        acts.put(11, "lookToRepository");
    }

    public static Map<Integer, String> getActs() {
        return acts;
    }

    public static AllActs getInstance() {
        if(instance == null) {
            instance = new AllActs();
        }
        return instance;
    }

    public static String getAct(int id) {
        return getActs().get(id);
    }

}
