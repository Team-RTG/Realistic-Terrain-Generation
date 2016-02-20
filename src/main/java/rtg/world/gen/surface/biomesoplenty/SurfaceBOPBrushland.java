package rtg.world.gen.surface.biomesoplenty;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

import java.util.Random;

public class SurfaceBOPBrushland extends SurfaceBase
{
	private Block mixBlock;
	private float width;
	private float height;
	
	public SurfaceBOPBrushland(BiomeConfig config, Block top, Block filler, Block mix, float mixWidth, float mixHeight)
	{
		super(config, top, (byte)0, filler, (byte)0);
		
		mixBlock = mix;
		
		width = mixWidth;
		height = mixHeight;
	}
	
	@Override
	public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
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
            		if(depth > -1 && depth < 2)
            		{
                        if (rand.nextInt(3) == 0) {
                            
                            primer.setBlockState((y * 16 + x) * 256 + k, hcCobble(world, i, j, x, y, k));
                        }
                        else {
                            
                            primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
                        }
            		}
            		else if (depth < 10)
            		{
                        primer.setBlockState((y * 16 + x) * 256 + k, hcStone(world, i, j, x, y, k));
            		}
            	}
            	else
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			if(simplex.noise2(i / width, j / width) > height) // > 0.27f, i / 12f
	        			{
	        				primer.setBlockState((y * 16 + x) * 256 + k, mixBlock.getDefaultState());
	        			}
	        			else
	        			{
	        				primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
	        			}
	        		}
	        		else if(depth < 4)
	        		{
	        			primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
	        		}
            	}
            }
		}
	}
}
