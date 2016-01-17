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

public class SurfaceVanillaBeach extends SurfaceBase
{
	private Block cliffBlock1;
	private Block cliffBlock2;
	private byte sandMetadata;
	private int cliffType;
	
	public SurfaceVanillaBeach(Block top, Block filler, Block cliff1, Block cliff2, byte metadata, int cliff)
	{
		super(Blocks.dirt, Blocks.dirt);
		
		cliffBlock1 = Blocks.dirt;
		cliffBlock2 = Blocks.stone;
		sandMetadata = metadata;
		cliffType = cliff;
	}
	
	@SuppressWarnings("unused")
    @Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.3f ? true : false;
		boolean dirt = false;

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
            		if(cliffType == 1)
            		{
            			if (depth < 6)
	            		{
                			blocks[(y * 16 + x) * 256 + k] = cliffBlock1;
                			metadata[(y * 16 + x) * 256 + k] = 14;
	            		}
            		}
            		else
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
            	}
            	else if(depth < 6)
            	{
	        		if(depth == 0 && k > 61 && k < 64)
	        		{
	        			//if(simplex.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f))
                        if (false)
	        			{
	        				dirt = true;
		        			blocks[(y * 16 + x) * 256 + k] = topBlock;
	        			}
	        			else
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
		        			metadata[(y * 16 + x) * 256 + k] = sandMetadata;
	        			}
	        		}
	        		else if(depth < 4)
	        		{
	        			if(dirt)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = fillerBlock;
	        			}
	        			else
	        			{
                            if (k > 61 && k < 69) {
	        				    blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
		        			    metadata[(y * 16 + x) * 256 + k] = sandMetadata;
                            }
	        			}
	        		}
	        		else if(!dirt)
	        		{
                        if ( k > 56 && k < 68) { // one lower for under sand and 4 deeper
	        			    blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
                        } else {
                            blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
                        }
	        		}
            	}
            }
		}
	}
}
