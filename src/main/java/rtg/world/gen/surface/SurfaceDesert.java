package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;

import java.util.Random;

public class SurfaceDesert extends SurfaceBase
{
	private Block cliffBlock1;
	private Block cliffBlock2;
	private Block bottomBlock;
	
	public SurfaceDesert(BiomeConfig config, Block top, Block filler, Block bottom, Block cliff1, Block cliff2)
	{
	    super(config, top, filler);
		
		bottomBlock = bottom; 
		cliffBlock1 = cliff1;
		cliffBlock2 = cliff2;
	}
	
	@Override
	public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 2.8f ? true : false;

		for(int k = 255; k > -1; k--)
		{
			Block b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;

            	if(cliff)
            	{
            		if(depth > -1 && depth < 2)
            		{
            			primer.setBlockState((y * 16 + x) * 256 + k, rand.nextInt(3) == 0 ? cliffBlock2.getDefaultState() : cliffBlock1.getDefaultState());
            		}
            		else if (depth < 10)
            		{
            			primer.setBlockState((y * 16 + x) * 256 + k, cliffBlock1.getDefaultState());
            		}
            	}
            	else if(depth < 6)
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
	        		}
	        		else if(depth < 4)
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
	        		}
	        		else
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, bottomBlock.getDefaultState());
	        		}
            	}
            }
		}
	}
}
