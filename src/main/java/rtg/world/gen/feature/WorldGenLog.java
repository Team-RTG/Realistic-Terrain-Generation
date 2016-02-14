package rtg.world.gen.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenLog extends WorldGenerator
{
	private int logMeta;
	private int leavesMeta;
	private Block logBlock;
	private Block leavesBlock;
	private int logLength;
	
    public WorldGenLog(int meta, int length, boolean leaves)
    {
        logBlock = meta > 2 ? Blocks.log2 : Blocks.log;
        leavesBlock = meta > 2 ? Blocks.leaves2 : Blocks.leaves;
        meta = meta > 2 ? meta - 2 : meta;
        
        logMeta = meta;
        leavesMeta = leaves ? meta : -1;
        logLength = length < 2 ? 2 : length;
    }
    
    /**
     * 
     * @param blockLog
     * @param metaLog
     * @param blockLeaves
     * @param metaLeaves Set to -1 to disable leaves.
     * @param length
     */
    public WorldGenLog(Block blockLog, int metaLog, Block blockLeaves, int metaLeaves, int length)
    {
        logBlock = blockLog;
        leavesBlock = blockLeaves;      
        logMeta = metaLog;
        leavesMeta = metaLeaves;
        logLength = length;
    }

	public boolean generate(World world, Random rand, BlockPos blockPos)
	{
    	Block g = world.getBlockState(blockPos.down()).getBlock();
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
			b = world.getBlockState(new BlockPos(blockPos.getX() - (dir == 0 ? 1 : 0), blockPos.getY(), blockPos.getZ() - (dir == 1 ? 1 : 0))).getBlock();
			if(b.getMaterial() != Material.air && b.getMaterial() != Material.vine && b.getMaterial() != Material.plants)
			{
				break;
			}
			
			int x = dir == 0 ? 1 : 0;
			int z = dir == 1 ? 1 : 0;

			blockPos = blockPos.add(-x,0,-z);
			if(airCheck(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ()) > 0)
			{
				return false;
			}
		}
		
		for(i = 0; i < logLength * 2; i++)
		{
			b = world.getBlockState(new BlockPos(blockPos.getX() + (dir == 0 ? 1 : 0), blockPos.getY(), blockPos.getZ() + (dir == 1 ? 1 : 0))).getBlock();
			if(b.getMaterial() != Material.air && b.getMaterial() != Material.vine && b.getMaterial() != Material.plants)
			{
				break;
			}
			
			air += airCheck(world, rand, blockPos.getX(), blockPos.getY(), blockPos.getZ());
			if(air > 2)
			{
				return false;
			}

			world.setBlockState(blockPos, logBlock.getStateFromMeta(dirMeta), 0);

			if(leavesMeta > -1)
			{
				addLeaves(world, rand, dir, blockPos.getX(), blockPos.getY(), blockPos.getZ());
			}
			
			int x = dir == 0 ? 1 : 0;
			int z = dir == 1 ? 1 : 0;

			blockPos = blockPos.add(x,0,z);
		}
		
		return true;
	}
	
	private int airCheck(World world, Random rand, int x, int y, int z)
	{
		Block b = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();
		if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.water || b.getMaterial() == Material.plants)
		{
			b = world.getBlockState(new BlockPos(x, y - 2, z)).getBlock();
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
			b = world.getBlockState(new BlockPos(x, y, z - 1)).getBlock();
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlockState(new BlockPos(x, y, z - 1), leavesBlock.getStateFromMeta(leavesMeta), 0);
			}
			b = world.getBlockState(new BlockPos(x, y, z + 1)).getBlock();
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlockState(new BlockPos(x, y, z + 1), leavesBlock.getStateFromMeta(leavesMeta), 0);
			}
		}
		else
		{
			b = world.getBlockState(new BlockPos(x - 1, y, z)).getBlock();
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlockState(new BlockPos(x - 1, y, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
			}
			b = world.getBlockState(new BlockPos(x + 1, y, z)).getBlock();
			if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
			{
				world.setBlockState(new BlockPos(x + 1, y, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
			}
		}

		b = world.getBlockState(new BlockPos(x, y + 1, z)).getBlock();
		if((b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants) && rand.nextInt(3) == 0)
		{
			world.setBlockState(new BlockPos(x, y + 1, z), leavesBlock.getStateFromMeta(leavesMeta), 0);
		}
	}
}
