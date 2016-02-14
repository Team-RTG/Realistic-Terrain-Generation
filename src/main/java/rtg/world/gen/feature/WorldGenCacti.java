package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCacti extends WorldGenerator
{
	private boolean sand;
	private int eHeight;
	
	public WorldGenCacti(boolean sandOnly)
	{
		this(sandOnly, 0);
	}
	
	public WorldGenCacti(boolean sandOnly, int extraHeight)
	{
		sand = sandOnly;
		eHeight = extraHeight;
	}

    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
    	Block b;
        for (int l = 0; l < 10; ++l)
        {
            int i1 = blockPos.getX() + rand.nextInt(8) - rand.nextInt(8);
            int j1 = blockPos.getY() + rand.nextInt(4) - rand.nextInt(4);
            int k1 = blockPos.getZ() + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(new BlockPos(i1, j1, k1)))
            {
            	b = world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock();
            	if(b == Blocks.sand || (!sand && (b == Blocks.grass || b == Blocks.dirt)))
            	{
	                int l1 = 1 + rand.nextInt(rand.nextInt(3) + 1);
	                if(b == Blocks.grass || b == Blocks.dirt)
	                {
	                	world.setBlockState(new BlockPos(i1, j1 - 1, k1), Blocks.sand.getDefaultState(), 2);
	                }
	
	                for (int i2 = 0; i2 < l1 + eHeight; ++i2)
	                {
	                    if (Blocks.cactus.canBlockStay(world, new BlockPos(i1, j1 + i2, k1)))
	                    {
	                    	world.setBlockState(new BlockPos(i1, j1 + i2, k1), Blocks.cactus.getDefaultState(), 2);
	                    }
	                }
            	}
            }
        }

        return true;
    }
}