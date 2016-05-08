
package rtg.util;


/**
 *
 * @author Zeno410
 */
public  class Direction {
    public static Direction UP = new Direction(0,-1);
    public static Direction UP_RIGHT = new Direction(1,-1);
    public static Direction RIGHT = new Direction(1,0);
    public static Direction DOWN_RIGHT = new Direction(1,1);
    public static Direction DOWN = new Direction(0,1);
    public static Direction DOWN_LEFT = new Direction(-1,1);
    public static Direction LEFT = new Direction(-1,0);
    public static Direction UP_LEFT = new Direction(-1,-1);
    public final int xOffset;
    public final int zOffset;
    private Direction (int _xMultiple, int _zMultiple) {
        xOffset = _xMultiple;
        zOffset = _zMultiple;
    }
}