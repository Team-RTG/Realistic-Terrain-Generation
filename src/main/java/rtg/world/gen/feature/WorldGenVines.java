package rtg.world.gen.feature;

import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created by VelocityRa on 13/5/2016.
 */
public class WorldGenVines extends WorldGenerator {
    
    public boolean generate(World world, Random rand, int blockX, int blockY, int blockZ)
    {
        int l = blockX;

        for (int i1 = blockZ; blockY < 128; ++blockY)
        {
            if (world.isAirBlock(blockX, blockY, blockZ))
            {
                for (int j1 = 2; j1 <= 5; ++j1)
                {
                    if (Blocks.vine.canPlaceBlockOnSide(world, blockX, blockY, blockZ, j1))
                    {
                        world.setBlock(blockX, blockY, blockZ, Blocks.vine, 1 << Direction.facingToDirection[Facing.oppositeSide[j1]], 2);
                        break;
                    }
                }
            }
            else
            {
                blockX = l + rand.nextInt(4) - rand.nextInt(4);
                blockZ = i1 + rand.nextInt(4) - rand.nextInt(4);
            }
        }
        return true;
    }
}
