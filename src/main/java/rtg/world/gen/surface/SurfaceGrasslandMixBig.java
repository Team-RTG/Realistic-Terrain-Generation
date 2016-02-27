package rtg.world.gen.surface;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;

import java.util.Random;

public class SurfaceGrasslandMixBig extends SurfaceBase
{
	private IBlockState mixBlockTop;
	private IBlockState mixBlockFill;
	private IBlockState cliffBlock1;
	private IBlockState cliffBlock2;
	private float width;
	private float height;
	private float smallW;
	private float smallS;
	
	public SurfaceGrasslandMixBig(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, IBlockState cliff1, IBlockState cliff2, float mixWidth, float mixHeight, float smallWidth, float smallStrength)
	{
		super(config, top, filler);
		
		mixBlockTop = mixTop;
		mixBlockFill = mixFill;
		cliffBlock1 = cliff1;
		cliffBlock2 = cliff2;
		
		width = mixWidth;
		height = mixHeight;
		smallW = smallWidth;
		smallS = smallStrength;
	}
	
	@Override
	public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.4f ? true : false;
		boolean mix = false;
		
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
            			primer.setBlockState((y * 16 + x) * 256 + k, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
            		}
            		else if (depth < 10)
            		{
            			primer.setBlockState((y * 16 + x) * 256 + k, cliffBlock1);
            		}
            	}
            	else
            	{
	        		if(depth == 0 && k > 61)
	        		{
	        			if(simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height)
	        			{
	        				primer.setBlockState((y * 16 + x) * 256 + k, mixBlockTop);
	        				mix = true;
	        			}
	        			else
	        			{
	        				primer.setBlockState((y * 16 + x) * 256 + k, topBlock);
	        			}
	        		}
	        		else if(depth < 4)
	        		{
	        			if(mix)
	        			{
		        			primer.setBlockState((y * 16 + x) * 256 + k, mixBlockFill);
	        			}
	        			else
	        			{
		        			primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock);
	        			}
	        		}
            	}
            }
		}
	}
}
