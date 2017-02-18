package rtg.api.util;

import java.util.Random;
import javax.annotation.Nonnull;

public class RandomUtil
{
    public static int getRandomInt(int intStart, int intEnd) {
        return (int)( (Math.random() * intEnd) + intStart );
    }
    
    public static int getRandomInt(Random rand, int intStart, int intEnd) {
        return intStart + rand.nextInt(intEnd - intStart + 1);
    }

    /**
     * Gets a Positive Gaussian Number with derivation and adds that to min.
     * @deprecated For Performance Reasons, please concider using {@link RandomUtil#weightedRandomFactor2(int, int, int)}
     * @param rand A Random Number Generator.
     * @param min The minimum outcome.
     * @param derivation The derivation value. This is not the maximal outcome!
     * @return A Pseudo-Random number strongly biased towards min
     */
    @Deprecated
    public static double weightedRandomGaussianAbs(@Nonnull Random rand, double min, double derivation) {
        return min + Math.abs(rand.nextGaussian()) * derivation;
    }

    /**
     * This method will create a pattern which will average to 2.
     * Every number <i>n</i> will have double the probability then <i>n+1</i>, min having a probability of 50%.
     * For "truer" randomness, use a random generator or consider using {@link RandomUtil#weightedRandomGaussianAbs(Random, double, double)}
     * @param generator A number for the base of the outcome
     * @param min The minimum outcome
     * @param max The maximum outcome
     * @return A number between min and max, min is most often.
     */
    public static int weightedRandomFactor2(int generator, int min, int max) {
        for(int i = 0; i < max; i++) {
            if(generator % (1 << (i+1)) != 0) {
                return min + i;
            }
        }

        return max;
    }
}