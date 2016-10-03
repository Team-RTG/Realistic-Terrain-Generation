package rtg.util;

import java.util.Random;

public class CliffCalculator {

    public static float calc(int x, int z, float[] noise) {

        float cliff = 0f;

        if (x > 0) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[(x - 1) * 16 + z]));
        }

        if (z > 0) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[x * 16 + z - 1]));
        }

        if (x < 15) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[(x + 1) * 16 + z]));
        }

        if (z < 15) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[x * 16 + z + 1]));
        }

        return cliff;
    }

    public static float calcNoise(int x, int z, float[] noise, Random rand, float randomNoise) {

        float cliff = 0f;

        if (x > 0) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[(x - 1) * 16 + z]));
        }

        if (z > 0) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[x * 16 + z - 1]));
        }

        if (x < 15) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[(x + 1) * 16 + z]));
        }

        if (z < 15) {
            cliff = test(cliff, Math.abs(noise[x * 16 + z] - noise[x * 16 + z + 1]));
        }

        return cliff - randomNoise + rand.nextFloat() * randomNoise * 2;
    }

    private static float test(float cliff, float value) {

        if (value > cliff) {
            return value;
        }

        return cliff;
    }
}
