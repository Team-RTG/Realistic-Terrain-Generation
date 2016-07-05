package teamrtg.rtg.api.tools.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenJungleCacti extends WorldGenerator
{
	private boolean sand;
	private byte sandByte;
	private int eHeight;
	
	public WorldGenJungleCacti(boolean sandOnly)
	{
		this(sandOnly, 0, (byte)1);
	}
	
	public WorldGenJungleCacti(boolean sandOnly, int extraHeight, byte sandMeta)
	{
		sand = sandOnly;
		eHeight = extraHeight;
		sandByte = sandMeta;
	}

	@Override
	public boolean generate(World world, Random rand, BlockPos pos)
	{
		int x = pos.getX(); int y = pos.getY(); int z = pos.getZ();
    	IBlockState b;
        for (int l = 0; l < 10; ++l)
        {
            int i1 = x + rand.nextInt(8) - rand.nextInt(8);
            int j1 = y + rand.nextInt(4) - rand.nextInt(4);
            int k1 = z + rand.nextInt(8) - rand.nextInt(8);

            if (world.isAirBlock(new BlockPos(i1, j1, k1)))
            {
            	b = world.getBlockState(new BlockPos(i1, j1 - 1, k1));
            	if(b == Blocks.SAND.getDefaultState() || (!sand && (b == Blocks.GRASS.getDefaultState() || b == Blocks.DIRT.getDefaultState())))
            	{
	                int l1 = 1 + rand.nextInt(rand.nextInt(3) + 1);
	                if(b == Blocks.GRASS.getDefaultState() || b == Blocks.DIRT.getDefaultState())
	                {
	                	world.setBlockState(new BlockPos(i1, j1 - 1, k1), Blocks.SAND.getStateFromMeta(sandByte), 2);
	                }
	
	                for (int i2 = 0; i2 < l1 + eHeight; ++i2)
	                {
	                    if (Blocks.CACTUS.canBlockStay(world, new BlockPos(i1, j1 + i2, k1)))
	                    {
	                    	world.setBlockState(new BlockPos(i1, j1 + i2, k1), Blocks.CACTUS.getDefaultState(), 2);
	                    }
	                }
            	}
            }
        }

        return true;
    }
}