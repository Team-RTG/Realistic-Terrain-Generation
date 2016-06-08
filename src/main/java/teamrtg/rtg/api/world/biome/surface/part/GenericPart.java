package teamrtg.rtg.api.world.biome.surface.part;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.world.RTGWorld;

import static teamrtg.rtg.api.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class GenericPart extends SurfacePart {

    private final IBlockState top;
    private final IBlockState fill;

    public GenericPart(IBlockState top, IBlockState fill) {
        this.top = top;
        this.fill = fill;
    }

    @Override
    protected boolean paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river, RTGWorld rtgWorld) {
        if (depth == 0 && y > 61) {
            primer.setBlockState(globalToLocal(x), y, globalToLocal(z), top);
            return true;
        } else if (depth < 4) {
            primer.setBlockState(globalToLocal(x), y, globalToLocal(z), fill);
            return true;
        }
        return false;
    }
}
