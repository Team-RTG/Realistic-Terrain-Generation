package com.sdj64.highlands.generator;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWatermelon extends WorldGenerator
{

	public boolean generate(World world, Random random, BlockPos pos)
	{
		int locX = pos.getX();
        int locY = pos.getY();
        int locZ = pos.getZ();
        
		for (int var6 = 0; var6 < 64; ++var6)
        {
			BlockPos pos2 = new BlockPos(
					locX + random.nextInt(8) - random.nextInt(8),
					locY + random.nextInt(4) - random.nextInt(4),
					locZ + random.nextInt(8) - random.nextInt(8));

            if (world.isAirBlock(pos2) && world.getBlockState(pos2.down(1)).getBlock() == Blocks.grass && Blocks.pumpkin.canPlaceBlockAt(world, pos2))
            {
                world.setBlockState(pos2, Blocks.melon_block.getDefaultState());
            }
        }

        return true;
	}
}
