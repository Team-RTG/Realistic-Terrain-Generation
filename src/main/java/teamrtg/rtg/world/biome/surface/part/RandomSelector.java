package teamrtg.rtg.world.biome.surface.part;

import teamrtg.rtg.world.gen.ChunkProviderRTG;

/**
 * @author topisani
 */
public class RandomSelector extends SurfacePart {

    private final int chance;

    public RandomSelector(int chance) {
        this.chance = chance;
    }

    @Override
    public boolean applies(int x, int y, int z, int depth, float[] noise, float river, ChunkProviderRTG provider) {
        return provider.rand.nextInt(chance) == 0;
    }
}
