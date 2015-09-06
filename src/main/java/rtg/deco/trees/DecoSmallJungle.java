package rtg.deco.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoSmallJungle extends WorldGenerator
{
	public DecoSmallJungle()
	{
	}

	@Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
		int size = 3;
		
    	Block b = world.getBlock(x, y - 1, z);
    	if(b != Blocks.grass && b != Blocks.dirt && b != Blocks.sand)
    	{
    		return false;
    	}
    	
    	Material m = world.getBlock(x, y, z).getMaterial();
    	if(m != Material.air && m != Material.vine)
    	{
    		return false;
    	}
    	
    	int i = 0, j, dx, dz, l, s, h = (size * 2) + rand.nextInt(size);
    	for(; i < h; i++)
    	{
    		world.setBlock(x, y + i, z, Blocks.log, 3, 0);
    	}
    	buildLeaves(world, rand, x, y + h - 1, z);
    	
    	if(size > 1)
    	{
	    	for(i = 0; i < size + 1; i++)
	    	{
	    		dx = rand.nextInt(3) - 1;
	    		dz = rand.nextInt(3) - 1;
	    		if(dx == 0 && dz == 0)
	    		{
	        		dx = rand.nextInt(3) - 1;
	        		dz = rand.nextInt(3) - 1;
	    		}
	    		
	    		l = (size - 1) + (Math.abs(dx) + Math.abs(dz) == 1 ? rand.nextInt(size - 1) : 0);
	    		
	    		s = h - size - rand.nextInt(size);
	    		for(j = 1; j <= l; j++)
	    		{
	    			world.setBlock(x + (dx * j), y + s + j, z + (dz * j), Blocks.log, 3, 0);
	    		}
	    		j--;
	    		buildLeaves(world, rand, x + (dx * j), y + s + j, z + (dz * j));
	    	}
    	}
    	
		return true;
    }
	
	private void buildLeaves(World w, Random rand, int x, int y, int z)
	{
		int i, j;
		for(i = -1; i <= 1; i++)
		{
			for(j = -1; j <= 1; j++)
			{
				buildBlock(w, x + i, y, z + j, Blocks.leaves, 3, 0);
			}
		}
		
		for(i = -1; i < 2; i+=2)
		{
			buildBlock(w, x + 1, y + i, z, Blocks.leaves, 3, 0);
			buildBlock(w, x - 1, y + i, z, Blocks.leaves, 3, 0);
			buildBlock(w, x, y + i, z + 1, Blocks.leaves, 3, 0);
			buildBlock(w, x, y + i, z - 1, Blocks.leaves, 3, 0);
			buildBlock(w, x, y + i, z, Blocks.leaves, 3, 0);
		}
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
