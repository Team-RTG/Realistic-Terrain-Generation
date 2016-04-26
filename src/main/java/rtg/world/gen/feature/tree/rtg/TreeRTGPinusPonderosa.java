package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TreeRTGPinusPonderosa extends TreeRTG
{

	public TreeRTGPinusPonderosa()
	{
		super();
	}
	
	@Override
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
    	for(i = 0; i < trunkSize; i++)
    	{
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
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
    	for(i = 0; i < crownSize; i++)
    	{
    		if(rand.nextInt(i < crownSize - 12 && i > 2 ? 2 : 1) == 0 && i < crownSize - 2)
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
        			i < crownSize - 12 && i > 3 ? 3 : i < crownSize - 8 ? 2 : 1, 
        			i < crownSize - 5 ? 2 : 1
        		);
    		}
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    		
    		if(i < crownSize - 2)
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
	
	@Override
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {
    	int h = (int)Math.ceil(this.trunkSize / 4f);
    	h = h + rand.nextInt(h * 2);
    	for(int i = -1; i < h; i++)
    	{
    		world.setBlock(x, y + i, z, this.logBlock, this.logMeta + 12, 0);
    	}
    }
	
	@Override
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
        	world.setBlock(x + (dX * m), y, z + (dZ * m), this.logBlock, this.logMeta, 0);
    	}
    }
	
	@Override
    public void buildLeaves(World world, int x, int y, int z)
    {
		if (!this.noLeaves) {
		
	    	Block b = world.getBlock(x, y, z);
	    	if(b.getMaterial() == Material.air)
	    	{
	    		world.setBlock(x, y, z, this.leavesBlock, this.leavesMeta, 0);
	    	}
		}
    }
}
