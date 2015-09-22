package rtg.world.gen.surface;

import java.util.Random;


import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceDesert extends SurfaceBase
{
	private Block cliffBlock1;
	private Block cliffBlock2;
	private Block bottomBlock;
	
	public SurfaceDesert(Block top, Block filler, Block bottom, Block cliff1, Block cliff2)
	{
		super(top, filler);
		
		bottomBlock = bottom; 
		cliffBlock1 = cliff1;
		cliffBlock2 = cliff2;
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 2.8f ? true : false;

		for(int k = 255; k > -1; k--)
		{
			Block b = blocks[(y * 16 + x) * 256 + k];
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
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1; 
            		}
            		else if (depth < 10)
            		{
            			blocks[(y * 16 + x) * 256 + k] = cliffBlock1;
            		}
            	}
            	else if(depth < 6)
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = topBlock;
	        		}
	        		else if(depth < 4)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = fillerBlock;
	        		}
	        		else
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = bottomBlock;
	        		}
            	}
            }
		}
	}
}
