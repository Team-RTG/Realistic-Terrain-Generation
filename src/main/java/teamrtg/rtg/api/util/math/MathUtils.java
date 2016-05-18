package teamrtg.rtg.api.util.math;

public class MathUtils {

    public static final int[] XY_INVERTED;

    static {
        int[] result = new int[256];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                result[i * 16 + j] = j * 16 + i;
            }
        }
        for (int i = 0; i < 256; i++) {
            if (result[result[i]] != i) throw new RuntimeException("" + i + "" + result[i] + " " + result[result[i]]);
        }
        XY_INVERTED = result;
    }

    public static final int globalToLocal(int x) {
        return ((x % 16) + 16) % 16;
    }

    public static final int globalToChunk(int x) {
        return (int) Math.floor((double) x / 16d);
    }

    public static final int globalToIndex(int x, int z) {
        return globalToLocal(x) * 16 + globalToLocal(z);
    }
}
