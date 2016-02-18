package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGBirchSmall extends WorldGenerator
{
	private int startHeight;
	private int treeSize;
	private int metadata;

	public WorldGenTreeRTGBirchSmall(int start, int s)
	{
		this(start, s, 1);
	}

	public WorldGenTreeRTGBirchSmall(int start, int s, int m)
	{
		startHeight = start;
		treeSize = s;
		metadata = m;
	}

    public boolean generate(World world, Random rand, BlockPos blockPos)
    {
    	Block g = world.getBlockState(blockPos.down()).getBlock();
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}

    	int i;
    	for(i = 0; i < startHeight; i++)
    	{
    		world.setBlockState(blockPos, Blocks.log.getStateFromMeta(metadata),2);
    		blockPos = blockPos.up();
    	}

    	int pX = 0;
    	int pZ = 0;
    	for(i = 0; i < treeSize; i++)
    	{
    		if(rand.nextInt(2) == 0 && i < treeSize - 2)
    		{
    			int dX = -1 + rand.nextInt(3);
    			int dZ = -1 + rand.nextInt(3);

    			if(dX == 0 && dZ == 0)
    			{
    				dX = -1 + rand.nextInt(3);
    				dZ = -1 + rand.nextInt(3);
    			}

    			if(pX == dX && rand.nextBoolean())
    			{
    				dX = -dX;
    			}
    			if(pZ == dZ && rand.nextBoolean())
    			{
    				dZ = -dZ;
    			}

    			pX = dX;
    			pZ = dZ;

        		buildBranch(world, rand, blockPos, dX, dZ, i < treeSize - 10 ? 2 : 1, i < treeSize - 6 ? 2 : 1);
    		}
    		world.setBlockState(blockPos, Blocks.log.getStateFromMeta(metadata),2);

    		if(i < treeSize - 2)
	    	{
				if(rand.nextBoolean()) { buildLeaves(world, blockPos.add(0,0,1)); }
				if(rand.nextBoolean()) { buildLeaves(world, blockPos.add(0,0,-1)); }
				if(rand.nextBoolean()) { buildLeaves(world, blockPos.add(1,0,0)); }
				if(rand.nextBoolean()) { buildLeaves(world, blockPos.add(-1,0,0)); }
    		}

    		blockPos = blockPos.up();
    	}

		buildLeaves(world, blockPos.add(0,-1,1));
		buildLeaves(world, blockPos.add(0,-1,-1));
		buildLeaves(world, blockPos.add(1,-1,0));
		buildLeaves(world, blockPos.add(-1,-1,0));
		buildLeaves(world, blockPos);

    	return true;
    }

    public void buildBranch(World world, Random rand, BlockPos blockPos, int dX, int dZ, int logLength, int leaveSize)
    {
    	for(int i = -1; i <= 1; i++)
    	{
    		for(int j = -1; j <= 1; j++)
    		{
    			for(int k = 0; k < 2; k++)
    			{
    				if(Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1)
    				{
        				buildLeaves(world, blockPos.add( i + (dX * logLength), k, j + (dZ * logLength)));
    				}
    			}
    		}
    	}

    	for(int m = 1; m <= logLength; m++)
    	{
        	world.setBlockState(blockPos.add((dX * m), 0, (dZ * m)), Blocks.log.getStateFromMeta(metadata),2);
    	}
    }

    public void buildLeaves(World world, BlockPos blockPos)
    {
    	Block b = world.getBlockState(blockPos).getBlock();
    	if(b.getMaterial() == Material.air)
    	{
    		world.setBlockState(blockPos, Blocks.leaves.getStateFromMeta(metadata),2);
    	}
    }
}
