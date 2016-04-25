package rtg.world.gen.feature.tree.deprecated;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * @see rtg.world.gen.feature.tree.rtg.pinaceae.pinus.TreeRTGPinaceaePinusNigra
 */
@Deprecated
public class WorldGenTreeRTGPineEuro extends WorldGenerator
{
	@Deprecated
	public WorldGenTreeRTGPineEuro()
	{
	}

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block g = world.getBlock(x, y - 1, z);
    	if(g != Blocks.grass && g != Blocks.dirt)
    	{
    		return false;
    	}
    	
    	int height = 18 + rand.nextInt(10);
    	int leafheight = 7 + rand.nextInt(4);
    	float branchIncrease = 0.25f;
    	
    	for(int i = 0; i <= height; i++)
    	{
    		world.setBlock(x, y + i, z, Blocks.log, 0, 0);
    	}
    	createRandomLeaves(world, rand, x, y + height, z, 2);
    	createTrunk(world, rand, x, y, z);
    	
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
				world.setBlock(x + (int)(b * xd), y + j, z + (int)(b * yd), Blocks.log, 12, 0);
			}
	    	createRandomLeaves(world, rand, x, y + j, z, 2);
	    	createRandomLeaves(world, rand, x + (int)(b * xd), y + j, z + (int)(b * yd), 2);
    	}
    	
    	return true;
    }
    
    private void createRandomLeaves(World world, Random rand, int x, int y, int z, int size)
    {
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
    						world.setBlock(x + i, y + j, z + k, Blocks.leaves, 4, 0);
    					}
    				}
    			}
    		}
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
    			if(world.getBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1]) == Blocks.dirt)
    			{
    				break;
    			}
    			world.setBlock(x + pos[t * 2], sh, z + pos[t * 2 + 1], Blocks.log, 12, 0);
    			sh--;
    		}
    	}
    }
}
