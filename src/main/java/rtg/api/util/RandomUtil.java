package rtg.api.util;

import java.util.Random;


// TODO: [Clean-up] Phase out the use of this util in all world gen and use java.util.Random with the world seed
@UtilityClass
public final class RandomUtil {

    private RandomUtil() {
    }

    public static int getRandomInt(Random rand, int intStart, int intEnd) {
        return intStart + rand.nextInt(intEnd - intStart + 1);
    }
}