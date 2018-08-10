package rtg.api.util;

@UtilityClass
public final class MathUtils {

    private MathUtils() {

    }

    public static int globalToChunk(int x) {
        return x >> 4;
    }

    public static int globalToLocal(int x) {
        return x & 15;
    }

    public static int pow2(int i) {
        return i * i;
    }

    public static long pow2(long l) {
        return l * l;
    }

    public static float pow2(float f) {
        return f * f;
    }

    public static double pow2(double d) {
        return d * d;
    }
}
