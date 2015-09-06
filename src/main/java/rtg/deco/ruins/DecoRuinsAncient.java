package rtg.deco.ruins;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;

public class DecoRuinsAncient 
{
	private Block block;
	
	public DecoRuinsAncient(Block b)
	{
		block = b;
	}
	
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		int type = rand.nextInt(2);
		
		Block g = world.getBlock(x, y - 1, z);
    	if(g.getMaterial() != Material.ground && g.getMaterial() != Material.grass && g.getMaterial() != Material.sand && g.getMaterial() != Material.rock)
    	{
    		return false;
    	}
    	
    	int high = y;
    	int low = y;
    	for(int cX = -6; cX <= 6; cX += 12)
    	{
    		for(int cZ = -6; cZ <= 6; cZ += 12)
    		{
    			for(int cY = y + 10; cY > 40; cY--)
    			{
					Block b = world.getBlock(x + cX, cY, z + cZ);
			    	if(b.getMaterial() == Material.ground || b.getMaterial() == Material.grass || b.getMaterial() == Material.sand || b.getMaterial() == Material.rock)
			    	{
			    		high = cY > high ? cY : high;
			    		low = cY < low ? cY : low;
			    		
			    		break;
			    	}
    			}
    		}
    	}
    	
    	if(high - low > 6)
    	{
    		return false;
    	}
		
		if(type == 0) //pillars
		{
			for(int a = rand.nextInt(4) + 3; a > -1; a--)
			{
				int sX = x - 4 + rand.nextInt(9);
				int sZ = z - 4 + rand.nextInt(9);
				int sY = y + 10;
				
				for(; sY > 50; sY--)
				{
					Block b = world.getBlock(sX, sY - 1, sZ);
			    	if(b.getMaterial() == Material.ground || b.getMaterial() == Material.grass || b.getMaterial() == Material.sand || b.getMaterial() == Material.rock)
			    	{
			    		break;
			    	}
				}
				
				int h = rand.nextInt(3) + 2 - rand.nextInt(2);
				for(int i = 0; i < h; i++)
				{
					world.setBlock(sX, sY + i, sZ, block, 0, 0);
				}
			}
		}
		else if(type == 1) //big pillar
		{
			for(int i = -1; i <= 1; i++)
			{
				for(int j = -1; j <= 1; j++)
				{
					int h = rand.nextInt(4) + (i == 0 && j == 0 ? 3 : 1);
					for(int k = -2; k < h; k++)
					{
						world.setBlock(x + i, y + k, z + j, block, 0, 0);
					}
				}
			}
		}
		
		return true;
	}
}
