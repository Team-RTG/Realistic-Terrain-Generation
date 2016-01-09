package rtg.world.gen.surface.highlands;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceHLOutback extends SurfaceBase
{

	private byte sandMetadata;
	private int cliffType;
	
	public SurfaceHLOutback(Block top, Block filler, byte metadata, int cliff)
	{
		super(top, filler);

		sandMetadata = metadata;
		cliffType = cliff;
	}
	
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
                            blocks[(y * 16 + x) * 256 + k] = hcStone(world, i, j, x, y, k);
                            metadata[(y * 16 + x) * 256 + k] = hcStoneMeta(world, i, j, x, y, k);
	            		}
            		}
            		else
            		{
	            		if(depth > -1 && depth < 2)
	            		{
	                        if (rand.nextInt(3) == 0) {
	                            
	                            blocks[(y * 16 + x) * 256 + k] = hcCobble(world, i, j, x, y, k);
	                            metadata[(y * 16 + x) * 256 + k] = hcCobbleMeta(world, i, j, x, y, k);
	                        }
	                        else {
	                            
	                            blocks[(y * 16 + x) * 256 + k] = hcStone(world, i, j, x, y, k);
	                            metadata[(y * 16 + x) * 256 + k] = hcStoneMeta(world, i, j, x, y, k);
	                        }
	            		}
	            		else if (depth < 10)
	            		{
	                        blocks[(y * 16 + x) * 256 + k] = hcStone(world, i, j, x, y, k);
	                        metadata[(y * 16 + x) * 256 + k] = hcStoneMeta(world, i, j, x, y, k);
	            		}
            		}
            	}
            	else if(depth < 6)
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			if(simplex.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f))
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
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
		        			metadata[(y * 16 + x) * 256 + k] = sandMetadata;
	        			}
	        		}
	        		else if(!dirt)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.sandstone;
	        		}
            	}
            }
		}
	}
}
