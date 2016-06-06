package teamrtg.rtg.api.world.biome.surface.part;

import teamrtg.rtg.api.world.RTGWorld;

/**
 * @author topisani
 */
public class RandomSelector extends SurfacePart {

    private final int chance;

    public RandomSelector(int chance) {
        this.chance = chance;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        return rtgWorld.rand.nextInt(chance) == 0;
    }
}
