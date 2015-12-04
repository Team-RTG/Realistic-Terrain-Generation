package rtg.world.gen.surface.vanilla;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceVanillaSavannaPlateau extends SurfaceBase
{
    
	public SurfaceVanillaSavannaPlateau(Block top, Block fill, byte b)
	{
		super(top, fill);
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.3f ? true : false;
		
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

        		if(depth > -1 && depth < 12)
	        	{
	            	if(cliff)
	            	{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	        			metadata[(y * 16 + x) * 256 + k] = 0;
	            	}
	            	else
	            	{
	        			if(depth > 4)
	        			{
	                        blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	                        metadata[(y * 16 + x) * 256 + k] = 0;
	        			}
	        			else
	        			{
	        				if(depth == 0)
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = topBlock;
	        				}
	        				else
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = fillerBlock;
	        				}
	        			}
	            	}
        		}
        		else if(k > 63)
        		{
                    blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
                    metadata[(y * 16 + x) * 256 + k] = 0;
        		}
            }
		}
	}
}
