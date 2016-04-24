package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * @see rtg.world.gen.feature.tree.rtg.pinaceae.pinus.TreeRTGPinaceaePinusPonderosa
 */
@Deprecated
public class WorldGenTreeRTGPineBig extends WorldGenerator
{
	private int startHeight;
	private int treeSize;
	
	private int metadataLog;
	private int metadataLeaves;
	
	@Deprecated
	public WorldGenTreeRTGPineBig(int start, int s)
	{
		this(start, s, 0, 0);
	}
	
	@Deprecated
	public WorldGenTreeRTGPineBig(int start, int s, int log, int leaves)
	{
		startHeight = start;
		treeSize = s;
		metadataLog = log;
		metadataLeaves = leaves;
	}
	
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	int startY = y;
    	
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	buildTrunk(world, rand, x + 1, y, z);
    	buildTrunk(world, rand, x - 1, y, z);
    	buildTrunk(world, rand, x, y, z + 1);
    	buildTrunk(world, rand, x, y, z - 1);
    	
    	int i;
    	for(i = 0; i < startHeight; i++)
    	{
    		world.setBlock(x, y, z, Blocks.log, metadataLog, 0);
    		if(i > 5 && rand.nextInt(7) == 0)
    		{
    			int dX = -1 + rand.nextInt(3);
    			int dZ = -1 + rand.nextInt(3);
    			
    			if(dX == 0 && dZ == 0)
    			{
    				dX = -1 + rand.nextInt(3);
    				dZ = -1 + rand.nextInt(3);
    			}
    			
    			buildBranch(world, rand, x, y, z, dX, dZ, 1, 1);
    		}
    		
    		y++;
    	}
    	
    	int pX = 0;
    	int pZ = 0;
    	int j;
    	for(i = 0; i < treeSize; i++)
    	{
    		if(rand.nextInt(i < treeSize - 12 && i > 2 ? 2 : 1) == 0 && i < treeSize - 2)
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

        		buildBranch(world, rand, x, y, z, dX, dZ, 
        			i < treeSize - 12 && i > 3 ? 3 : i < treeSize - 8 ? 2 : 1, 
        			i < treeSize - 5 ? 2 : 1
        		);
    		}
    		world.setBlock(x, y, z, Blocks.log, metadataLog, 0);
    		
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
    	if(logLength == 3 && Math.abs(dX) + Math.abs(dZ) == 2)
    	{
    		logLength--;
    	}
    	
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
        	world.setBlock(x + (dX * m), y, z + (dZ * m), Blocks.log, metadataLog, 0);
    	}
    }
    
    public void buildLeaves(World world, int x, int y, int z)
    {
    	Block b = world.getBlock(x, y, z);
    	if(b.getMaterial() == Material.air)
    	{
    		world.setBlock(x, y, z, Blocks.leaves, metadataLeaves, 0);
    	}
    }
    
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {
    	int h = (int)Math.ceil(startHeight / 4f);
    	h = h + rand.nextInt(h * 2);
    	for(int i = -1; i < h; i++)
    	{
    		world.setBlock(x, y + i, z, Blocks.log, metadataLog + 12, 0);
    	}
    }
}
