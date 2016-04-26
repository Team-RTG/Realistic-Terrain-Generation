package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class TreeRTGSalixMyrtilloides extends TreeRTG
{
	public TreeRTGSalixMyrtilloides()
	{
		super();
		
		this.setLogBlock(Blocks.log)
			.setLogMeta((byte)0)
			.setLeavesBlock(Blocks.leaves)
			.setLeavesMeta((byte)0);
	}

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block cb;
    	boolean earth = false;
    	boolean water = false;
    	for(int c1 = -2; c1 <= 2; c1++)
    	{
        	for(int c3 = -2; c3 <= 2; c3++)
        	{
            	for(int c2 = -1; c2 <= 1; c2++)
            	{
            		cb = world.getBlock(x + c1, y + c2, z + c3);
            		if(cb == Blocks.grass)
            		{
            			earth = true;
            		}
            		else if(cb == Blocks.water)
            		{
            			water = true;
            		}
            	}
        	}
    	}
    	
    	if(!(earth && water))
    	{
    		return false;
    	}
    	
    	int height = 13;
    	int leaveheight = 5;
    	int branches = 6;
    	int branchLenght = 6;
    	
    	for(int i = 0; i < height; i++)
    	{
    		world.setBlock(x, y + i, z, this.logBlock, this.logMeta, 0);
    	}
    	createLeavesAroundBranch(world, rand, x, y + height, z, 3, 2);
    	createTrunk(world, rand, x, y, z);
    	
    	int dir = rand.nextInt((int)(360f / branches));
    	int bl;
    	float xd, yd, hd, c;
    	boolean m;
    	for(int b = 0; b < branches; b++)
    	{
    		c = 0;
    		hd = height - rand.nextFloat() * leaveheight - 2f;
    		dir += (int)(360f / branches);
			xd = (float)Math.cos(dir * Math.PI / 180f);
			yd = (float)Math.sin(dir * Math.PI / 180f);
			m = false;
    		
    		while(c < branchLenght)
    		{
    			if(c > branchLenght / 2 && !m)
    			{
    				m = true;
    				createLeavesAroundBranch(world, rand, x + (int)(c * xd), y + (int)hd, z + (int)(c * yd), 2, 1);
    			}
    			c++;
    			hd += 0.5f;
    			world.setBlock(x + (int)(c * xd), y + (int)hd, z + (int)(c * yd), this.logBlock, this.logMeta + 12, 0);
    		}
    		createLeavesAroundBranch(world, rand, x + (int)(c * xd), y + (int)hd, z + (int)(c * yd), 2, 1);
    	}
    	
    	return true;
    }
    
    private void createLeavesAroundBranch(World world, Random rand, int x, int y, int z, int s, int c)
    {
    	int l;
    	int t = (int)Math.pow(s, 2);
    	for(int i = -s; i <= s; i++)
    	{
    		for(int j = -s; j <= s; j++)
    		{
    			for(int k = -s; k <= s; k++)
    			{
    				l = i*i + j*j + k*k;
    				if(l <= t)
    				{
    					if(world.isAirBlock(x + i, y + j, z + k) && (l < t - c || rand.nextBoolean()))
    					{
    						world.setBlock(x + i, y + j, z + k, this.leavesBlock, this.leavesMeta, 0);
    						if(j < -(s - 2) && rand.nextInt(3) != 0)
    						{
    							createVine(world, rand, x + i, y + j, z + k);
    						}
    					}
    				}
    			}
    		}
    	}
    }
    
    private void createVine(World world, Random rand, int x, int y, int z)
    {
    	int r = rand.nextInt(3) + 5;
    	for(int i = -1; i > -r; i--)
    	{
			if(!world.isAirBlock(x, y + i, z))
			{
				break;
			}
			world.setBlock(x, y + i, z, this.leavesBlock, this.leavesMeta, 0);
    	}
    }
    
    private void createTrunk(World world, Random rand, int x, int y, int z)
    {
    	int[] pos = new int[]{0,0, 1,0, 0,1, -1,0, 0,-1};
    	int sh;
    	for(int t = 0; t < 5; t++)
    	{    	
    		sh = rand.nextInt(3) + y;
    		while(sh > y - 3)
    		{
    			world.setBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1], this.logBlock, this.logMeta + 12, 0);
    			sh--;
    		}
    	}
    }
}
