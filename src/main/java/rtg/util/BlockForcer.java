
package rtg.util;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 *
 * @author Zeno410
 */
public class BlockForcer {

    public void placeBlock(World target, int x,  int y , int z, Block placed, int metadata) {
        Chunk chunk = target.getChunkFromBlockCoords(new BlockPos(x, 0, z));
        chunk.setBlockState(new BlockPos(x&15, y, z&15), placed.getStateFromMeta(metadata));
    }

}
