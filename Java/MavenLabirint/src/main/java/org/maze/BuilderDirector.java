package org.maze;

/**
 * Created by A.V.Tsaplin on 04.07.2016.
 */
public class BuilderDirector {

    private String[] paramObjectStr = null;
    private char[][] paramObjectArray = null;

    public BuilderDirector(String[] paramObjectStr) {
        super();
        this.paramObjectStr = paramObjectStr;
    }

    public BuilderDirector(char[][] paramObjectArray) {
        super();
        this.paramObjectArray = paramObjectArray;
    }

    public void buildSomeMap(int mazeId) {
        if ((paramObjectStr != null) && (paramObjectArray == null)) {
            BuilderStr oneBuilderStr = new BuilderStr(paramObjectStr);
            oneBuilderStr.doBuild(mazeId);
        } else if ((paramObjectArray != null) && (paramObjectStr == null)) {
            BuilderArray oneBuilderArray = new BuilderArray(paramObjectArray);
            oneBuilderArray.doBuild(mazeId);
        }
    }

}
