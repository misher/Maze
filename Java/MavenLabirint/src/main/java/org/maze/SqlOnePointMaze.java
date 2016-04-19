package org.maze;

/**
 * Created by A.V.Tsaplin on 13.04.2016.
 */
public class SqlOnePointMaze implements IMaze {


    // JDBC URL, username and password of MySQL server
    private String url;
    private String user;
    private String password;
    private String table;
    private int mazeId;


    public SqlOnePointMaze (String url, String user, String password, String table, int mazeId) {
        super();
        this.url = url;
        this.user = user;
        this.password = password;
        this.table = table;
        this.mazeId = mazeId;
    }

    public boolean isRoadPoint(IPoint point) {
        Point somePoint = (Point) point;
        SqlOnePointPickUp someMap = new SqlOnePointPickUp(url, user, password, table);
        ParePointValue pointFromSql = someMap.getPointFromSql(somePoint.getAxis(0), somePoint.getAxis(1), mazeId);
        if ((pointFromSql.getSomePoint().equals(somePoint)) && (pointFromSql.getSomeValue() == 0)){
            System.out.println("Coordinates X, Y, Z ... " + somePoint.getAxis(0) + " " + somePoint.getAxis(1));
            return true;
        }
        return false;
    }

    public boolean isTargetPoint(IPoint point) {
        Point somePoint = (Point) point;
        SqlOnePointPickUp someMap = new SqlOnePointPickUp(url, user, password, table);
        ParePointValue pointFromSql = someMap.getPointFromSql(somePoint.getAxis(0), somePoint.getAxis(1), mazeId);
        if ((pointFromSql.getSomePoint().equals(somePoint)) && (pointFromSql.getSomeValue() == 2)){
            System.out.println("Found money Coordinates X, Y, Z ... "  + somePoint.getAxis(0) + " " + somePoint.getAxis(1));
            return true;
        }
        return false;
    }
}