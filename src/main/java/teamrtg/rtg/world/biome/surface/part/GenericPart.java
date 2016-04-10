package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.biome.RealisticBiomeBase;

import static teamrtg.rtg.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class GenericPart extends SurfacePart {

    public GenericPart(RealisticBiomeBase biome) {
        super(biome);
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river) {
        if (depth == 0 && y > 61) {
            primer.setBlockState(globalToLocal(x), y, globalToLocal(z), biome.config.FILL_BLOCK.get());
        } else if (depth < 4) {
            primer.setBlockState(globalToLocal(x), y, globalToLocal(z), biome.config.TOP_BLOCK.get());
        }
    }
}
