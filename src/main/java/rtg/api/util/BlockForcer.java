
package rtg.api.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 *
 * @author Zeno410
 */
public class BlockForcer {

    public void placeBlock(World target, int x, int y , int z, IBlockState  placed) {
        Chunk chunk = target.getChunkFromBlockCoords(new BlockPos(x, 0, z));
        chunk.setBlockState(new BlockPos(x&15, y, z&15), placed);
    }

}
