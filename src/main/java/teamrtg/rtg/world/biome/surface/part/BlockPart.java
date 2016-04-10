package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.util.IBlockAt;
import teamrtg.rtg.util.noise.RTGNoise;

import static teamrtg.rtg.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class BlockPart extends SurfacePart {

    private final IBlockAt block;
    private RTGNoise noise;

    public BlockPart(RealisticBiomeBase biome, IBlockState blockState) {
        super(biome);
        this.block = (x, y, z) -> blockState;
    }

    public BlockPart(RealisticBiomeBase biome, IBlockAt block) {
        super(biome);
        this.block = block;
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river) {
        primer.setBlockState(globalToLocal(x), y, globalToLocal(z), block.getAt(x, y, z));
    }
}
