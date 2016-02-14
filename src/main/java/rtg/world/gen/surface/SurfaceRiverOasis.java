package rtg.world.gen.surface;

import java.util.Random;


import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceRiverOasis extends SurfaceBase
{
	public SurfaceRiverOasis() 
	{
		super(Blocks.grass, Blocks.dirt);
	}
	
	@Override
	public void paintTerrain(ChunkPrimer primer, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		if(river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.15f) > 0.8f)
		{
			Block b;
			for(int k = 255; k > -1; k--)
			{
				b = primer.getBlockState((y * 16 + x) * 256 + k).getBlock();
	            if(b == Blocks.air)
	            {
	            	depth = -1;
	            }
	            else if(b != Blocks.water)
	            {
	            	depth++;
	            	
	        		if(depth == 0 && k > 61)
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, Blocks.grass.getDefaultState());
	        		}
	        		else if(depth < 4)
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getDefaultState());
	        		}
	        		else if(depth > 4)
	        		{
	        			return;
	        		}
	            }
			}
		}
	}
}
