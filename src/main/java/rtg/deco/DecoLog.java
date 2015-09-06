package rtg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class DecoLog extends WorldGenerator
{
	private int logMeta;
	private int leavesMeta;
	private Block logBlock;
	private Block leavesBlock;
	private int logLength;
	
	public DecoLog(int meta, int length, boolean leaves)
	{
		logBlock = meta > 2 ? Blocks.log2 : Blocks.log;
		leavesBlock = meta > 2 ? Blocks.leaves2 : Blocks.leaves;
		meta = meta > 2 ? meta - 2 : meta;
		
		logMeta = meta;
		leavesMeta = leaves ? meta : -1;
		logLength = length < 2 ? 2 : length;
	}

	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
    	Block g = world.getBlock(x, y - 1, z);
    	if(g.getMaterial() != Material.ground && g.getMaterial() != Material.grass && g.getMaterial() != Material.sand && g.getMaterial() != Material.rock)
    	{
    		return false;
    	}
    	
		int dir = rand.nextInt(2);
		int dirMeta = 4 + (dir * 4) + logMeta;
		boolean leaves = leavesMeta > -1 ? true : false;
		
		int i;
		Block b;
		int air = 0;
		for(i = 0; i < logLength; i++)
		{
			b = world.getBlock(x - (dir == 0 ? 1 : 0), y, z - (dir == 1 ? 1 : 0));
			if(b.getMaterial() != Material.air && b.getMaterial() != Material.vine && b.getMaterial() != Material.plants)
			{
				break;
			}
			
			x -= dir == 0 ? 1 : 0;
			z -= dir == 1 ? 1 : 0;
			
			if(airCheck(world, rand, x, y, z) > 0)
			{
				return false;
			}
		}
		
		for(i = 0; i < logLength * 2; i++)
		{
			b = world.getBlock(x + (dir == 0 ? 1 : 0), y, z + (dir == 1 ? 1 : 0));
			if(b.getMaterial() != Material.air && b.getMaterial() != Material.vine && b.getMaterial() != Material.plants)
			{
				break;
			}
			
			air += airCheck(world, rand, x, y, z);
			if(air > 2)
			{
				return false;
			}

			world.setBlock(x, y, z, logBlock, dirMeta, 0);

			if(leavesMeta > -1)
			{
				addLeaves(world, rand, dir, x, y, z);
			}
			
			x += dir == 0 ? 1 : 0;
			z += dir == 1 ? 1 : 0;
		}
		
		return true;
	}
	
	private int airCheck(World world, Random rand, int x, int y, int z)
	{
		Block b = world.getBlock(x, y - 1, z);
		if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.water || b.getMaterial() == Material.plants)
		{
			b = world.getBlock(x, y - 2, z);
			if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.water || b.getMaterial() == Material.plants)
			{
				return 99;
			}
			return 1;
		}
		
		return 0;
	}
	
	private void addLeaves(World world, Random rand, int dir, int x, int y, int z)
	{
		Block b;
		if(dir == 0)
		{
			b = world.getBlock(x, y, z - 1);
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlock(x, y, z - 1, leavesBlock, leavesMeta, 0);
			}
			b = world.getBlock(x, y, z + 1);
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlock(x, y, z + 1, leavesBlock, leavesMeta, 0);
			}
		}
		else
		{
			b = world.getBlock(x - 1, y, z);
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlock(x - 1, y, z, leavesBlock, leavesMeta, 0);
			}
			b = world.getBlock(x + 1, y, z);
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlock(x + 1, y, z, leavesBlock, leavesMeta, 0);
			}
		}

		b = world.getBlock(x, y + 1, z);
		if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
		{
			world.setBlock(x, y + 1, z, leavesBlock, leavesMeta, 0);
		}
	}
}
