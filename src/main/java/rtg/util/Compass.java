
package rtg.util;

import java.util.ArrayList;

/**
 * provide integer access to compass directions, and clockwise/counterclockwise options
 * @author Zeno410
 */
public class Compass {
    private Direction [] directions = new Direction [8];
    
    public Compass() {
        directions[0] = Direction.UP;
        directions[1] = Direction.UP_RIGHT;
        directions[2] = Direction.RIGHT;
        directions[3] = Direction.DOWN_RIGHT;
        directions[4] = Direction.DOWN;
        directions[5] = Direction.DOWN_LEFT;
        directions[6] = Direction.LEFT;
        directions[7] = Direction.UP_LEFT;
    }

    public Direction direction(int index) {
        return directions[index];
    }
    public int index(Direction compassDirection) {
        for (int i = 0; i < 8 ; i++) {
            if (directions[i].equals(compassDirection)) return i;
        }
        throw new RuntimeException("nonexistent compass direction");
    }

    public Direction clockwise(Direction start) {
        int result = index(start)+1;
        return directions[result % 8];
    }

    public Direction counterClockwise(Direction start) {
        int result = index(start)+7;
        return directions[result % 8];
    }

    public Direction opposite(Direction start) {
        int result = index(start)+4;
        return directions[result % 8];
    }

    public ArrayList<Direction> directions() {
        ArrayList result = new ArrayList<Direction>(8);
        for (int i = 0; i < 8; i++) result.add(direction(i));
        return result;
    }
}
