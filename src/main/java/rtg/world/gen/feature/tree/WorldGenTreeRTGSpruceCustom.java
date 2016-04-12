package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGSpruceCustom extends WorldGenerator
{

	protected Block logBlock;
	protected byte logMeta;
	protected Block leavesBlock;
	protected byte leavesMeta;
	protected int start;
	protected int small;
	protected int large;
	
	public WorldGenTreeRTGSpruceCustom()
	{

		this(Blocks.log, (byte)1, Blocks.leaves, (byte)1, 1, 2, 0);
	}
	
	public WorldGenTreeRTGSpruceCustom(Block logBlock, byte logMeta, Block leavesBlock, byte leavesMeta, int start, int small, int large)
	{

		this.logBlock = logBlock;
		this.logMeta = logMeta;
		this.leavesBlock = leavesBlock;
		this.leavesMeta = leavesMeta;
		this.start = start;
		this.small = small;
		this.large = large;
	}

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	int i, j, k;
    	for(i = 0; i < start; i++)
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
