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

    public void BuildSomeMap(int mazeId) {
        if (paramObjectStr != null) {
            BuilderStr oneBuilderStr = new BuilderStr(paramObjectStr);
            oneBuilderStr.doBuild(mazeId);
        }
        if (paramObjectArray != null) {
            BuilderArray oneBuilderArray = new BuilderArray(paramObjectArray);
            oneBuilderArray.doBuild(mazeId);
        }
    }

}
