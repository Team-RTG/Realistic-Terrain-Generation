package rtg.world.gen.surface;

import java.util.Random;


import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceRedDesert extends SurfaceBase
{
	private Block cliffBlock1;
	private Block cliffBlock2;
	private Block bottomBlock;
	
	public SurfaceRedDesert()
	{
		super(Blocks.sand, Blocks.sand);
		
		bottomBlock = Blocks.sandstone; 
		cliffBlock1 = Blocks.stained_hardened_clay;
	}
	
	@Override
	public void paintTerrain(ChunkPrimer primer, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.4f ? true : false;

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
            		if (depth < 6)
            		{                			
            			primer.setBlockState((y * 16 + x) * 256 + k, cliffBlock1.getDefaultState());
            			metadata[(y * 16 + x) * 256 + k] = (byte)14;
            		}
            	}
            	else if(depth < 6)
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, topBlock.getDefaultState());
	        			metadata[(y * 16 + x) * 256 + k] = (byte)1;
	        		}
	        		else if(depth < 4)
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock.getDefaultState());
	        			metadata[(y * 16 + x) * 256 + k] = (byte)1;
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
