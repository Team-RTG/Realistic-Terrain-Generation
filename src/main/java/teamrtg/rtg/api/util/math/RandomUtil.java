package teamrtg.rtg.api.util.math;

import java.util.Random;

public class RandomUtil {
    public static int getRandomInt(int intStart, int intEnd) {
        return (int) ((Math.random() * intEnd) + intStart);
    }

    public static int getRandomInt(Random rand, int intStart, int intEnd) {
        return intStart + rand.nextInt(intEnd - intStart + 1);
    }
}