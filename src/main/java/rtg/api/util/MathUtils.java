package rtg.api.util;

@UtilityClass
public final class MathUtils {
    private MathUtils() {}
    public static int globalToChunk(int x) { return x >> 4; }
    public static int globalToLocal(int x) { return x & 15; }
}
