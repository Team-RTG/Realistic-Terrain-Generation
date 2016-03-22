package rtg.util.math;

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
}
