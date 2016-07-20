package teamrtg.rtg.api.tools.feature.tree.abyssalcraft;

import com.shinoow.abyssalcraft.api.block.ACBlocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenTreeACDarkwood extends WorldGenerator
{
	private int startHeight;
	private int treeSize;
	
	private int metadataLog;
	private int metadataLeaves;
	
	public WorldGenTreeACDarkwood(int start, int s)
	{
		this(start, s, 0, 0);
	}
	
	public WorldGenTreeACDarkwood(int start, int s, int log, int leaves)
	{
		startHeight = start;
		treeSize = s;
		metadataLog = log;
		metadataLeaves = leaves;
	}

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

    	int startY = y;
    	
    	IBlockState g = world.getBlockState(new BlockPos(x, y - 1, z));
    	if(g != ACBlocks.darklands_grass.getDefaultState())
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
    		world.setBlockState(new BlockPos(x, y, z), ACBlocks.darklands_oak_wood.getStateFromMeta(metadataLog), 2);
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
    		world.setBlockState(new BlockPos(x, y, z), ACBlocks.darklands_oak_wood.getStateFromMeta(metadataLog), 2);
    		
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
        	world.setBlockState(new BlockPos(x + (dX * m), y, z + (dZ * m)), ACBlocks.darklands_oak_wood.getStateFromMeta(metadataLog), 2);
    	}
    }
    
    public void buildLeaves(World world, int x, int y, int z)
    {
    	IBlockState b = world.getBlockState(new BlockPos(x, y, z));
    	if(b.getMaterial() == Material.AIR)
    	{
    		world.setBlockState(new BlockPos(x, y, z), ACBlocks.darklands_oak_leaves.getStateFromMeta(metadataLeaves), 2);
    	}
    }
    
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {
    	int h = (int)Math.ceil(startHeight / 4f);
    	h = h + rand.nextInt(h * 2);
    	for(int i = -1; i < h; i++)
    	{
    		world.setBlockState(new BlockPos(x, y + i, z), ACBlocks.darklands_oak_wood.getStateFromMeta(metadataLog), 2);
    	}
    }
}
