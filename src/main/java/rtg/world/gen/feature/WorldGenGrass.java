package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenGrass extends WorldGenerator
{
	private Block block;
	private int metadata;

    public WorldGenGrass(Block b, int m)
    {
		block = b;
		metadata = m;
    }

    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
    	while(blockPos.getY() > 0)
    	{
    		if(!world.isAirBlock(blockPos) || world.getBlockState(blockPos).getBlock().isLeaves(world, blockPos))
    		{
    			break;
    		}
    		blockPos = blockPos.down();
    	}
    	
    	if(block == Blocks.double_plant)
    	{
            for (int l = 0; l < 64; ++l)
            {
                int i1 = blockPos.getX() + rand.nextInt(8) - rand.nextInt(8);
                int j1 = blockPos.getY() + rand.nextInt(4) - rand.nextInt(4);
                int k1 = blockPos.getZ() + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && j1 < 254 && Blocks.double_plant.canPlaceBlockAt(world, new BlockPos(i1, j1, k1)))
                {
                    Blocks.double_plant.placeAt(world, new BlockPos(i1, j1, k1), BlockDoublePlant.EnumPlantType.byMetadata(metadata), 0);
                }
            }
    	}
    	else if(block == Blocks.leaves)
    	{
            for (int l = 0; l < 64; ++l)
            {
                int i1 = blockPos.getX() + rand.nextInt(8) - rand.nextInt(8);
                int j1 = blockPos.getY() + rand.nextInt(4) - rand.nextInt(4);
                int k1 = blockPos.getZ() + rand.nextInt(8) - rand.nextInt(8);

                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock() == Blocks.grass)
                {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(metadata), 0);
                }
            }
    	}
    	else
    	{
            for (int l = 0; l < 128; ++l)
            {
                int i1 = blockPos.getX() + rand.nextInt(8) - rand.nextInt(8);
                int j1 = blockPos.getY() + rand.nextInt(4) - rand.nextInt(4);
                int k1 = blockPos.getZ() + rand.nextInt(8) - rand.nextInt(8);

                // TODO: less hacky way to test if the plant can stay.
                if (world.isAirBlock(new BlockPos(i1, j1, k1)) && world.getBlockState(new BlockPos(i1, j1 - 1, k1)).getBlock().canSustainPlant(world, new BlockPos(i1,j1,k1), EnumFacing.UP, Blocks.tallgrass ))
                {
                    world.setBlockState(new BlockPos(i1, j1, k1), block.getStateFromMeta(metadata), 0);
                }
            }
    	}
    	return true;
    }
}
