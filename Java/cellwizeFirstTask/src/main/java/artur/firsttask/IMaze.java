package artur.firsttask;

/**
 *
 * Created by A.V.Tsaplin on 22.08.2016.
 */

public interface IMaze {
    boolean isTargetPoint(IPoint point);
    boolean isRoadPoint(IPoint startPoint, IPoint somePoint);
}
