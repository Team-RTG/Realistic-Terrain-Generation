package rtg.world.gen.feature.tree;

import java.util.Random;

import net.minecraft.util.BlockPos;
import rtg.config.rtg.ConfigRTG;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTreeRTGShrub extends WorldGenerator
{
	private int size;
	private Block logBlock;
	private int logMeta;
	private Block leaveBlock;
	private int leaveMeta;
	private boolean sand;

	public WorldGenTreeRTGShrub(int s, int log, int leav)
	{
		this(s, log, leav, false);
	}
	
	public WorldGenTreeRTGShrub(int s, int log, int leav, boolean sa)
	{
		size = s;
		sand = sa;

		logBlock = log < 3 ? Blocks.log : Blocks.log2;
		logMeta = log > 2 ? log - 3 : log;
		
		leaveBlock = leav < 4 ? Blocks.leaves : Blocks.leaves2;
		leaveMeta = leav > 3 ? leav - 4 : leav;
	}
	
	@Override
	public boolean generate(World world, Random rand, BlockPos blockPos)
	{
		int width = size > 6 ? 6 : size;
		int height = size > 3 ? 2 : 1;
		
		for(int i = 0; i < size; i++)
		{
			int rX = rand.nextInt(width * 2) - width;
			int rY = rand.nextInt(height);
			int rZ = rand.nextInt(width * 2) - width;
			
			if(i == 0 && size > 4)
			{
				buildLeaves(world, blockPos.add(rX, 0, rZ), 3);
			}
			else if(i == 1 && size > 2)
			{
				buildLeaves(world,  blockPos.add(rX, 0, rZ), 2);
			}
			else
			{
				buildLeaves(world,  blockPos.add(rX, rY, rZ), 1);
			}
		}
		return true;
	}
	
	public void buildLeaves(World world, BlockPos blockPos, int size)
	{
	    Block b = world.getBlockState(blockPos.down(2)).getBlock();
	    Block b1 = world.getBlockState(blockPos.down()).getBlock();
		
        if ((b == Blocks.sand || b1 == Blocks.sand) && !ConfigRTG.allowTreesToGenerateOnSand) {
            return;
        }
		
		if(b.getMaterial() == Material.grass || b.getMaterial() == Material.ground || (sand && b.getMaterial() == Material.sand))
		{
			if(world.getBlockState(blockPos.down()).getBlock() != Blocks.water )
			{
				for(int i = -size; i <= size; i++)
				{
					for(int j = -1; j <= 1; j++)
					{
						for(int k = -size; k <= size; k++)
						{
							if(Math.abs(i) + Math.abs(j) + Math.abs(k) <= size)
							{
								buildBlock(world, blockPos.add(i,j,k), leaveBlock, leaveMeta);
							}
						}
					}
				}
				world.setBlockState(blockPos.down(), logBlock.getStateFromMeta(logMeta), 0);
			}
		}
	}
	
	public void buildBlock(World world, BlockPos blockPos, Block block, int meta)
	{
		Block b = world.getBlockState(blockPos).getBlock();
		if(b.getMaterial() == Material.air || b.getMaterial() == Material.vine || b.getMaterial() == Material.plants || b == Blocks.snow_layer)
		{
			world.setBlockState(blockPos, block.getStateFromMeta(meta), 0);
		}
	}
}
