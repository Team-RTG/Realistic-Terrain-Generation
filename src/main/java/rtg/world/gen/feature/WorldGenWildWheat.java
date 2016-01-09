package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWildWheat extends WorldGenerator
{
	private Block farmtype;
	
	/**
	 * 
	 * 0 = potatoes, 1 = carrots, 2 = wheat
	 * 
	 */
    public WorldGenWildWheat(int type)
    {
    	farmtype = type == 0 ? Blocks.potatoes : type == 1 ? Blocks.carrots : Blocks.wheat;
    }

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
    	
    	for(int j = 0; j < 4; j++)
    	{
        	b = world.getBlock(j == 0 ? x - 1 : j == 1 ? x + 1 : x, y, j == 2 ? z - 1 : j == 3 ? z + 1 : z);
        	if(b.getMaterial() != Material.ground && b.getMaterial() != Material.grass)
        	{
        		return false;
        	}
    	}
    	
    	int rx, ry, rz;
    	for(int i = 0; i < 30; i++)
    	{
    		rx = rand.nextInt(5) - 2;
    		ry = rand.nextInt(2) - 1;
    		rz = rand.nextInt(5) - 2;
    		b = world.getBlock(x + rx, y + ry, z + rz);
    		
    		if((b == Blocks.grass || b == Blocks.dirt) && world.isAirBlock(x + rx, y + ry + 1, z + rz))
    		{
    			world.setBlock(x + rx, y + ry, z + rz, Blocks.farmland, rand.nextInt(4) + 4, 0);
    			world.setBlock(x + rx, y + ry + 1, z + rz, farmtype, rand.nextInt(4) + 4, 0);
    		}
    	}
    	
    	world.setBlock(x, y, z, Blocks.water);
    	return true;
    }
}
