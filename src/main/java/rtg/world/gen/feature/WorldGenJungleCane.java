package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenJungleCane extends WorldGenerator 
{
	private int height;
	
    public WorldGenJungleCane(int h)
    {
    	height = h;
    }

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
    	Block b;
    	while(y > 0)
    	{
    		b = world.getBlock(x, y, z);
    		if(!world.isAirBlock(x, y, z) || b.isLeaves(world, x, y, z))
    		{
    			break;
    		}
    		y--;
    	}
    	
    	b = world.getBlock(x, y, z);
    	if(b != Blocks.grass && b != Blocks.dirt)
    	{
    		return false;
    	}

    	int j, sx, sz, ra;
    	for(j = 0; j < 4; j++)
    	{
        	b = world.getBlock(j == 0 ? x - 1 : j == 1 ? x + 1 : x, y, j == 2 ? z - 1 : j == 3 ? z + 1 : z);
        	if(b.getMaterial() != Material.ground && b.getMaterial() != Material.grass)
        	{
        		return false;
        	}
    	}
    	
    	for(j = 0; j < 4; j++)
    	{
    		sx = j == 0 ? x - 1 : j == 1 ? x + 1 : x;
    		sz = j == 2 ? z - 1 : j == 3 ? z + 1 : z;
    		ra = rand.nextInt(height * 2 + 1) + height;
    		
        	b = world.getBlock(sx, y + 1, sz);
        	if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine)
        	{
        		for(int k = 0; k < ra; k++)
        		{
                	b = world.getBlock(sx, y + 1 + k, sz);
                	if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine)
                	{
            			world.setBlock(sx, y + 1 + k, sz, Blocks.reeds, 0, 2);
                	}
                	else
                	{
                		break;
                	}
        		}
        	}
    	}

    	world.setBlock(x, y, z, Blocks.water);
    	
    	return true;
	}
}
