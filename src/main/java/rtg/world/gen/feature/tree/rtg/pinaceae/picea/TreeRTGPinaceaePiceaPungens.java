package rtg.world.gen.feature.tree.rtg.pinaceae.picea;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TreeRTGPinaceaePiceaPungens extends TreeRTGPinaceaePiceaBase
{

	public TreeRTGPinaceaePiceaPungens()
	{
		super();
	}

	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	    	
    	int small = (int)Math.ceil((double)(this.crownSize / 2));
    	int large = small;
    	
    	int i, j, k;
    	for(i = 0; i < this.trunkSize; i++)
    	{
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    		y++;
    	}

    	for(i = 0; i < large; i++)
    	{
    		for(j = -2; j <= 2; j++)
    		{
    			for(k = -2; k <= 2; k++)
    			{
    				if(Math.abs(j) + Math.abs(k) != 4 && ((j > -2 && k > -2 && j < 2 && k < 2) || rand.nextInt(4) != 0))
    				{
    					world.setBlock(x + j, y, z + k, this.leavesBlock, this.leavesMeta, 0);
    				}
    			}
    		}
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    		y++;
    	}
    	
    	for(i = 0; i < small; i++)
    	{
    		for(j = -1; j <= 1; j++)
    		{
    			for(k = -1; k <= 1; k++)
    			{
    				if(Math.abs(j) + Math.abs(k) < 2 || (rand.nextInt(4) != 0))
    				{
    					world.setBlock(x + j, y, z + k, this.leavesBlock, this.leavesMeta, 0);
    				}
    			}
    		}
    		
    		if(i == 0)
    		{
    	    	world.setBlock(x + 1, y, z, this.leavesBlock, this.leavesMeta, 0);
    	    	world.setBlock(x - 1, y, z, this.leavesBlock, this.leavesMeta, 0);
    	    	world.setBlock(x, y, z + 1, this.leavesBlock, this.leavesMeta, 0);
    	    	world.setBlock(x, y, z - 1, this.leavesBlock, this.leavesMeta, 0);
    	    	world.setBlock(x + 2, y, z, this.leavesBlock, this.leavesMeta, 0);
    	    	world.setBlock(x - 2, y, z, this.leavesBlock, this.leavesMeta, 0);
    	    	world.setBlock(x, y, z + 2, this.leavesBlock, this.leavesMeta, 0);
    	    	world.setBlock(x, y, z - 2, this.leavesBlock, this.leavesMeta, 0);
    		}
    		
    		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    		y++;
    	}
    	
		world.setBlock(x, y, z, this.logBlock, this.logMeta, 0);
    	world.setBlock(x + 1, y, z, this.leavesBlock, this.leavesMeta, 0);
    	world.setBlock(x - 1, y, z, this.leavesBlock, this.leavesMeta, 0);
    	world.setBlock(x, y, z + 1, this.leavesBlock, this.leavesMeta, 0);
    	world.setBlock(x, y, z - 1, this.leavesBlock, this.leavesMeta, 0);
    	
    	world.setBlock(x, y + 1, z, this.leavesBlock, this.leavesMeta, 0);
    	world.setBlock(x, y + 2, z, this.leavesBlock, this.leavesMeta, 0);
		return true;
	}
}
