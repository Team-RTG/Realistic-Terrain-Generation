package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TreeRTGPinusNigra extends TreeRTG
{
	public TreeRTGPinusNigra()
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
    	
    	int height = this.trunkSize;
    	int leafheight = this.crownSize;
    	float branchIncrease = 0.25f;
    	
    	for(int i = 0; i <= height; i++)
    	{
    		world.setBlock(x, y + i, z, this.logBlock, this.logMeta, 0);
    	}
    	buildLeaves(world, rand, x, y + height, z, 2);
    	buildTrunk(world, rand, x, y, z);
    	
    	int dir = 0, b;
    	float xd, yd, bl = 1f;
    	for(int j = height; j >= height - leafheight; j--)
    	{
    		bl += branchIncrease;
    		dir += rand.nextInt(180) + 90;
    		dir -= dir > 360 ? 360 : 0;
			xd = (float)Math.cos(dir * Math.PI / 180f);
			yd = (float)Math.sin(dir * Math.PI / 180f);
			
			for(b = 0; b <= bl; b++)
			{
				world.setBlock(x + (int)(b * xd), y + j, z + (int)(b * yd), this.logBlock, this.logMeta + 12, 0);
			}
	    	buildLeaves(world, rand, x, y + j, z, 2);
	    	buildLeaves(world, rand, x + (int)(b * xd), y + j, z + (int)(b * yd), 2);
    	}
    	
    	return true;
    }
    
	@Override
    public void buildLeaves(World world, Random rand, int x, int y, int z, int size)
    {
		if (this.noLeaves) {
			return;
		}
		
    	int l;
    	int t = (int)Math.pow(size, 2);
    	for(int i = -size; i <= size; i++)
    	{
    		for(int j = -size; j <= size; j++)
    		{
    			for(int k = -size; k <= size; k++)
    			{
    				l = i*i + j*j + k*k;
    				if(l <= t)
    				{
    					if(world.isAirBlock(x + i, y + j, z + k) && (l < t / 2 || rand.nextBoolean()))
    					{
    						world.setBlock(x + i, y + j, z + k, this.leavesBlock, this.leavesMeta, 0);
    					}
    				}
    			}
    		}
    	}
    }
    
    @Override
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {
    	int[] pos = new int[]{0,0, 1,0, 0,1, -1,0, 0,-1};
    	int sh;
    	for(int t = 0; t < 5; t++)
    	{    	
    		sh = rand.nextInt(3) + y;
    		while(sh > y - 3)
    		{
    			if(world.getBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1]) == Blocks.dirt)
    			{
    				break;
    			}
    			world.setBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1], this.logBlock, this.logMeta + 12, 0);
    			sh--;
    		}
    	}
    }
}
