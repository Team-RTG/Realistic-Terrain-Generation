package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeSavanna extends WorldGenerator
{
	private int type;
	private boolean sand;
	
    public WorldGenTreeSavanna(int t)
    {
    	this(t, true);
    }
    
    public WorldGenTreeSavanna(int t, boolean s)
    {
    	type = t;
    	sand = s;
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block b = world.getBlock(x, y - 1, z);
    	if(b != Blocks.grass && b != Blocks.dirt && ((sand && b != Blocks.sand) || !sand))
    	{
    		return false;
    	}
    	
    	if(type == 0)
    	{
	    	int h = 10 + rand.nextInt(5);
	    	int bh = h - 6;
	    	
	    	for(int i = 0; i < h; i++)
	    	{
	    		world.setBlock(x, y + i, z, Blocks.log2, 0, 0);
	    	}
			genLeaves(world, rand, x, y + h, z);
			
			int sh, eh, dir;
			float xd, yd, c;
			
			for(int a = 7 - 1 + rand.nextInt(3); a > -1; a--)
			{
				sh = bh + rand.nextInt(6 - 2);
				eh = h - (int)((h - sh) * 1f);
				dir = rand.nextInt(360);
				xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
				yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
				c = 1;
				
				while(sh < h)
				{
					world.setBlock(x + (int)(xd * c), y + sh, z + (int)(yd * c), Blocks.log2, 0, 0);
					sh++;
					c += 0.5f;
				}
				genLeaves(world, rand, x + (int)(xd * c), y + sh, z + (int)(yd * c));
			}
    	}
    	else if(type == 1)
    	{
	    	int h = 6 + rand.nextInt(3);
	    	int bh = h - 3;
	    	
	    	for(int i = 0; i < h; i++)
	    	{
	    		world.setBlock(x, y + i, z, Blocks.log2, 0, 0);
	    	}
			genLeaves(world, rand, x, y + h, z);
			
			int sh, eh, dir;
			float xd, yd, c;
			
			for(int a = 1 + rand.nextInt(2); a > -1; a--)
			{
				sh = bh + rand.nextInt(3 - 1);
				eh = h - (int)((h - sh) * 0.25f);
				dir = rand.nextInt(360);
				xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
				yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
				c = 1;
				
				while(sh < h)
				{
					world.setBlock(x + (int)(xd * c), y + sh, z + (int)(yd * c), Blocks.log2, 0, 0);
					sh++;
					c += 0.5f;
				}
				genLeaves(world, rand, x + (int)(xd * c), y + sh, z + (int)(yd * c));
			}
    	}
    	else if(type == 2)
    	{
	    	int h = 12 + rand.nextInt(5);
	    	int bh = h - 3;
	    	
	    	for(int i = 0; i < h; i++)
	    	{
	    		world.setBlock(x, y + i, z, Blocks.log2);
	    	}
			genLeaves(world, rand, x, y + h, z);
			
			int sh, eh, dir;
			float xd, yd, c;
			
			for(int a = 1 + rand.nextInt(2); a > -1; a--)
			{
				sh = bh + rand.nextInt(3 - 1);
				eh = h - (int)((h - sh) * 0.25f);
				dir = rand.nextInt(360);
				xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
				yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
				c = 1;
				
				while(sh < h)
				{
					world.setBlock(x + (int)(xd * c), y + sh, z + (int)(yd * c), Blocks.log2);
					sh++;
					c += 0.5f;
				}
				genLeaves(world, rand, x + (int)(xd * c), y + sh, z + (int)(yd * c));
			}
    	}
    	
    	return true;
    }
    
    public void genLeaves(World world, Random rand, int x, int y, int z)
    {
    	if(type == 0)
    	{
	    	int i;
	    	int j;
	    	for(i = -2; i <= 2; i++)
	    	{
	    		for(j = -2; j <= 2; j++)
	    		{
	    			if(world.isAirBlock(x + i, y + 1, z + j) && Math.abs(i) + Math.abs(j) < 4)
	    			{
	    				world.setBlock(x + i, y + 1, z + j, Blocks.leaves2, 0, 0);
	    			}
	    		}
	    	}
	    	
	    	for(i = -3; i <= 3; i++)
	    	{
	    		for(j = -3; j <= 3; j++)
	    		{
	    			if(world.isAirBlock(x + i, y, z + j) && Math.abs(i) + Math.abs(j) < 5)
	    			{
	    				world.setBlock(x + i, y, z + j, Blocks.leaves2, 0, 0);
	    			}
	    		}
	    	}
	    	
	    	world.setBlock(x, y, z, Blocks.log2);
    	}
    	else
    	{
	    	int i;
	    	int j;
	    	for(i = -1; i <= 1; i++)
	    	{
	    		for(j = -1; j <= 1; j++)
	    		{
	    			if(world.isAirBlock(x + i, y + 1, z + j))
	    			{
	    				world.setBlock(x + i, y + 1, z + j, Blocks.leaves2, 0, 0);
	    			}
	    		}
	    	}
	    	
	    	for(i = -2; i <= 2; i++)
	    	{
	    		for(j = -2; j <= 2; j++)
	    		{
	    			if(world.isAirBlock(x + i, y, z + j) && Math.abs(i) + Math.abs(j) < 4)
	    			{
	    				world.setBlock(x + i, y, z + j, Blocks.leaves2, 0, 0);
	    			}
	    		}
	    	}
	    	
	    	world.setBlock(x, y, z, Blocks.log2);
    	}
    }
}
