package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.api.biome.RealisticBiomeBase;

import java.util.Random;

/**
 * @author topisani
 */
public class RandomPart extends SurfacePart {

    private final Random rand;
    private final int chance;

    public RandomPart(RealisticBiomeBase biome, long seed, int chance) {
        super(biome);
        this.chance = chance;
        this.rand = new Random(seed);
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return rand.nextInt(chance) == 0;
    }
}
