package teamrtg.rtg.world.biome.surface.part;

import java.util.Random;

/**
 * @author topisani
 */
public class RandomPart extends SurfacePart {

    private final Random rand;
    private final int chance;

    public RandomPart(Random rand, int chance) {
        this.chance = chance;
        this.rand = rand;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river) {
        return rand.nextInt(chance) == 0;
    }
}
