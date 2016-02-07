package rtg.world.gen.feature.tree;

import java.util.Random;

import rtg.config.rtg.ConfigRTG;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGCocoaSmall extends WorldGenerator
{
	private static int[] cocoas = new int[]{
		2, 0, -2, 1,
		1, 1, -2, 0,
		0, 0, -2, -1,
		3, -1, -2, 0
	};
	
	public WorldGenTreeRTGCocoaSmall()
	{
		
	}

	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block b = world.getBlock(x, y - 1, z);
    	
    	if (b == Blocks.sand && !ConfigRTG.allowTreesToGenerateOnSand) {
    	    return false;
    	}
    	
    	if(b != Blocks.grass && b != Blocks.dirt && b != Blocks.sand)
    	{
    		return false;
    	}
    	
    	Material m = world.getBlock(x, y, z).getMaterial();
    	if(m != Material.air && m != Material.vine)
    	{
    		return false;
    	}
    	
    	int h = y + 2 + rand.nextInt(3);
    	for(; y < h; y++)
    	{
    		world.setBlock(x, y, z, Blocks.log, 3, 0);
    	}
    	
    	for(int i = -2; i <= 2; i++)
    	{
        	for(int j = -2; j <= 2; j++)
        	{
        		if(Math.abs(i) + Math.abs(j) < 3)
        		{
        			buildBlock(world, x + i, y - 1, z + j, Blocks.leaves, 3, 0);
        		}
        	}
    	}

		world.setBlock(x, y - 1, z, Blocks.log, 3, 0);
		buildBlock(world, x + 1, y, z, Blocks.leaves, 3, 0);
		buildBlock(world, x - 1, y, z, Blocks.leaves, 3, 0);
		buildBlock(world, x, y, z, Blocks.leaves, 3, 0);
		buildBlock(world, x, y, z + 1, Blocks.leaves, 3, 0);
		buildBlock(world, x, y, z - 1, Blocks.leaves, 3, 0);
    	
    	for(int k = 0; k < 16; k+=4)
    	{
    		if(rand.nextInt(20) == 0)
    		{
    			buildBlock(world, x + cocoas[k + 1], y + cocoas[k + 2], z + cocoas[k + 3], Blocks.cocoa, cocoas[k + 0] + 8, 0);
    		}
    	}
    	
		return true;
	}
	
	private void buildBlock(World w, int x, int y, int z, Block b, int m, int u)
	{
		Material ma = w.getBlock(x, y, z).getMaterial();
		
		if(ma == Material.air || ma == Material.vine)
		{
			w.setBlock(x, y, z, b, m, u);
		}
	}

}
