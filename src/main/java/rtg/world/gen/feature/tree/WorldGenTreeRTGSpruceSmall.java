package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGSpruceSmall extends WorldGenerator
{
	private int treeSize;
	
	public WorldGenTreeRTGSpruceSmall(int s)
	{
		treeSize = s;
	}

    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
    	Block g = world.getBlockState(blockPos.down()).getBlock();
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	int leavesMeta = rand.nextInt(2);
    	
    	int start = 1;
    	int small = 2;
    	int large = 0;
    	if(treeSize == 1)
    	{
    		small = 2;
    		large = 2;
    	}
    	else if(treeSize == 2)
    	{
        	start = 1 + rand.nextInt(2);
    		small = 3;
    		large = 3;
    	}
    	
    	int i, j, k;
    	for(i = 0; i < start; i++)
    	{
    		world.setBlockState(blockPos, Blocks.log.getDefaultState(), 0);
    		blockPos = blockPos.up();
    	}

    	for(i = 0; i < large; i++)
    	{
    		for(j = -2; j <= 2; j++)
    		{
    			for(k = -2; k <= 2; k++)
    			{
    				if(Math.abs(j) + Math.abs(k) != 4 && ((j > -2 && k > -2 && j < 2 && k < 2) || rand.nextInt(4) != 0))
    				{
    					world.setBlockState(blockPos.add(j,0,k), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    				}
    			}
    		}
    		world.setBlockState(blockPos, Blocks.log.getDefaultState(), 0);
    		blockPos = blockPos.up();
    	}
    	
    	for(i = 0; i < small; i++)
    	{
    		for(j = -1; j <= 1; j++)
    		{
    			for(k = -1; k <= 1; k++)
    			{
    				if(Math.abs(j) + Math.abs(k) < 2 || (rand.nextInt(4) != 0))
    				{
    					world.setBlockState(blockPos.add(j,0,k), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    				}
    			}
    		}
    		
    		if(i == 0)
    		{
    	    	world.setBlockState(blockPos.east(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    	    	world.setBlockState(blockPos.west(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    	    	world.setBlockState(blockPos.south(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    	    	world.setBlockState(blockPos.north(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
				world.setBlockState(blockPos.east(2), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
				world.setBlockState(blockPos.west(2), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
				world.setBlockState(blockPos.south(2), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
				world.setBlockState(blockPos.north(2), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    		}
    		
    		world.setBlockState(blockPos, Blocks.log.getDefaultState(), 0);
    		blockPos = blockPos.up();
    	}
    	
		world.setBlockState(blockPos, Blocks.log.getDefaultState(), 0);
		world.setBlockState(blockPos.east(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
		world.setBlockState(blockPos.west(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
		world.setBlockState(blockPos.south(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
		world.setBlockState(blockPos.north(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    	
    	world.setBlockState(blockPos.up(), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
    	world.setBlockState(blockPos.up(2), Blocks.leaves.getStateFromMeta(leavesMeta), 0);
		return true;
	}
}
