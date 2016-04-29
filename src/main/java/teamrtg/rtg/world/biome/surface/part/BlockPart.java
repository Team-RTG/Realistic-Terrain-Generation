package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.util.IBlockAt;

import static teamrtg.rtg.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class BlockPart extends SurfacePart {

    private final IBlockAt block;

    public BlockPart(IBlockState blockState) {
        this.block = (x, y, z) -> blockState;
    }

    public BlockPart(IBlockAt block) {
        this.block = block;
    }

    @Override
    public boolean paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river) {
        if (this.block.getAt(x, y, z) == null) return false;
        primer.setBlockState(globalToLocal(x), y, globalToLocal(z), block.getAt(x, y, z));
        return true;
    }
}
