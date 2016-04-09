package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

/**
 * @author topisani
 */
public class GenericPart extends SurfacePartBase {

    public GenericPart(RealisticBiomeBase biome) {
        super(biome);
    }

    @Override
    public boolean paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise) {
        if (depth == 0 && y > 61) {
            primer.setBlockState(x, y, y, biome.config.FILL_BLOCK.get());
        } else if (depth < 4) {
            primer.setBlockState(x, y, y, biome.config.TOP_BLOCK.get());
        }
        return true;
    }
}
