package rtg.world.gen.feature.tree.rtg.pinaceae.picea;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import rtg.world.gen.feature.tree.rtg.pinaceae.TreeRTGPinaceaeBase;

public class TreeRTGPinaceaePiceaBase extends TreeRTGPinaceaeBase
{

	public TreeRTGPinaceaePiceaBase()
	{
		super();
	}
	
	@Override
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {

    }
	
	@Override
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
        	world.setBlock(x + (dX * m), y, z + (dZ * m), this.logBlock, this.logMeta, 0);
    	}
    }

	@Override
    public void buildLeaves(World world, int x, int y, int z)
    {
		if (this.noLeaves) {
			return;
		}
		
    	Block b = world.getBlock(x, y, z);
    	if(b.getMaterial() == Material.air)
    	{
    		world.setBlock(x, y, z, this.leavesBlock, this.leavesMeta, 0);
    	}
    }
}
