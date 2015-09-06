package rtg.deco.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoDeadDesertTrees extends WorldGenerator
{
	private int type;
	
    public DecoDeadDesertTrees(int t)
    {
    	type = t;
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt && g != Blocks.sand)
    	{
    		return false;
    	}
    	
    	if(type == 0)
    	{
    		int i, h = 8;
    		
    		for(i = 0; i < h; i++)
    		{
    			world.setBlock(x, y + i, z, Blocks.log2, 0, 0);
    		}
    		
    		int branches = 2 + rand.nextInt(3);
    		float dir, xd, yd, zd, l, c, sk = (360f / branches);
    		
    		for(i = 0; i < branches; i++)
    		{
    			dir = i * sk + rand.nextFloat() * sk;
				xd = (float)Math.cos(dir * Math.PI / 180f);
				zd = (float)Math.sin(dir * Math.PI / 180f);
				l = 1f + rand.nextFloat() * 3f;
				c = 1f;
				
				while(c < l)
				{
					world.setBlock(x + (int)(xd * c), y + h + (int)c, z + (int)(zd * c), Blocks.log2, 12, 0);
					c += 1f;
				}
    		}
    	}
    	else
    	{
    		int h = rand.nextInt(3) + 2;
	    	for(int i = 0; i < h; i++)
	    	{
	    		world.setBlock(x, y + i, z, Blocks.log2, 0, 0);
	    	}
	    	
	    	h--;
	    	world.setBlock(x + 1, y + h, z, Blocks.leaves2, 0, 0);
	    	world.setBlock(x - 1, y + h, z, Blocks.leaves2, 0, 0);
	    	world.setBlock(x, y + h, z + 1, Blocks.leaves2, 0, 0);
	    	world.setBlock(x, y + h, z - 1, Blocks.leaves2, 0, 0);
	    	world.setBlock(x, y + h + 1, z, Blocks.leaves2, 0, 0);
    	}
    	
    	return true;
    }
}
