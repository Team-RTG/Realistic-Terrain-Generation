package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeBirchSmall extends WorldGenerator
{
	private int startHeight;
	private int treeSize;
	private int metadata;
	
	public WorldGenTreeBirchSmall(int start, int s)
	{
		this(start, s, 1);
	}
	
	public WorldGenTreeBirchSmall(int start, int s, int m)
	{
		startHeight = start;
		treeSize = s;
		metadata = m;
	}
	
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	int i;
    	for(i = 0; i < startHeight; i++)
    	{
    		world.setBlock(x, y, z, Blocks.log, metadata, 0);
    		y++;
    	}
    	
    	int pX = 0;
    	int pZ = 0;
    	for(i = 0; i < treeSize; i++)
    	{
    		if(rand.nextInt(1) == 0 && i < treeSize - 2)
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

        		buildBranch(world, rand, x, y, z, dX, dZ, i < treeSize - 10 ? 2 : 1, i < treeSize - 6 ? 2 : 1);
    		}
    		world.setBlock(x, y, z, Blocks.log, metadata, 0);
    		
    		if(i < treeSize - 2)
	    	{
	    		if(rand.nextBoolean()) { buildLeaves(world, x, y, z + 1); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x, y, z - 1); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x + 1, y, z); }
	    		if(rand.nextBoolean()) { buildLeaves(world, x - 1, y, z); }
    		}
    		
    		y++;
    	}
    	
    	buildLeaves(world, x, y - 1, z + 1);
    	buildLeaves(world, x, y - 1, z - 1);
    	buildLeaves(world, x + 1, y - 1, z);
    	buildLeaves(world, x - 1, y - 1, z);
    	buildLeaves(world, x, y, z);
    	
    	return true;
    }
    
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize)
    {
    	for(int i = -1; i <= 1; i++)
    	{
    		for(int j = -1; j <= 1; j++)
    		{
    			for(int k = 0; k < 2; k++)
    			{
    				if(Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1)
    				{
        				buildLeaves(world, x + i+ (dX * logLength), y + k, z + j + (dZ * logLength));
    				}
    			}
    		}
    	}
    	
    	for(int m = 1; m <= logLength; m++)
    	{
        	world.setBlock(x + (dX * m), y, z + (dZ * m), Blocks.log, metadata, 0);
    	}
    }
    
    public void buildLeaves(World world, int x, int y, int z)
    {
    	Block b = world.getBlock(x, y, z);
    	if(b.getMaterial() == Material.air)
    	{
    		world.setBlock(x, y, z, Blocks.leaves, metadata, 0);
    	}
    }
}
