
package rtg.util;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

/**
 *
 * @author Zeno410
 */
public class BlockForcer {

    public void placeBlock(World target, int x,  int y , int z, Block placed, int metadata) {
        Chunk chunk = target.getChunkFromBlockCoords(x, z);
        chunk.func_150807_a(x&15, y, z&15, placed, metadata);
    }

}
