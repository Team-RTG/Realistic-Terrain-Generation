package rtg.world.gen.feature.tree.rtg.pinaceae.pinus;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import rtg.world.gen.feature.tree.rtg.pinaceae.TreeRTGPinaceaeBase;

public class TreeRTGPinaceaePinusBase extends TreeRTGPinaceaeBase
{

	public TreeRTGPinaceaePinusBase()
	{
		super();
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
