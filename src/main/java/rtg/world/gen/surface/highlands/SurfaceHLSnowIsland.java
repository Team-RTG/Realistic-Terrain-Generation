package rtg.world.gen.surface.highlands;

import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceHLSnowIsland extends SurfaceBase
{

	public SurfaceHLSnowIsland(BiomeConfig config, Block top, Block filler)
	{
		super(config, top, (byte)0, filler, (byte)0);
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.4f ? true : false;
		
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
            	else
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = topBlock;
	        		    metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
	        		}
	        		else if(depth < 4)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = fillerBlock;
	        		    metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
	        		}
            	}
            }
		}
	}
}
