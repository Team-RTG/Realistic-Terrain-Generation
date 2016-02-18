package rtg.world.gen.surface.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraft.world.chunk.ChunkPrimer;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.SurfaceBase;

public class SurfaceVanillaMesaPlateau extends SurfaceBase
{
	private int[] claycolor = new int[100];
	private byte blockByte = 0;
	private int grassRaise = 0;
	
	public SurfaceVanillaMesaPlateau(Block top, Block fill, byte b, int grassHeight)
	{
		super(top, fill);
		blockByte = b;
		grassRaise = grassHeight;
		
		int[] c = new int[]{1, 8, 0};
		OpenSimplexNoise simplex = new OpenSimplexNoise(2L);
		
		float n;
		for(int i = 0; i < 100; i++)
		{
			n = simplex.noise1(i / 3f) * 3f + simplex.noise1(i / 1f) * 0.3f + 1.5f;
			n = n >= 3f ? 2.9f : n < 0f ? 0f : n;
			claycolor[i] = c[(int)n];
		}
	}
	
	public byte getClayColorForHeight(int k)
	{
		k -= 60;
		k = k < 0 ? 0 : k > 99 ? 99 : k;
		return (byte)claycolor[k];
	}
	
	@Override
	public void paintTerrain(ChunkPrimer primer, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 1.3f ? true : false;
		
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

        		if(depth > -1 && depth < 12)
	        	{
	            	if(cliff)
	            	{
	        			primer.setBlockState((y * 16 + x) * 256 + k, Blocks.stained_hardened_clay.getDefaultState());
	        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	            	}
	            	else
	            	{
	        			if(depth > 4)
	        			{
		        			primer.setBlockState((y * 16 + x) * 256 + k, Blocks.stained_hardened_clay.getDefaultState());
		        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	        			}
	        			else if(k > 74 + grassRaise)
	        			{
	        				if(rand.nextInt(5) == 0)
	        				{
		        				primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getDefaultState());
	        				}
	        				else
	        				{
		        				if(depth == 0)
		        				{
			        				primer.setBlockState((y * 16 + x) * 256 + k, topBlock.getDefaultState());
			        				metadata[(y * 16 + x) * 256 + k] = blockByte;
		        				}
		        				else
		        				{
			        				primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock.getDefaultState());
			        				metadata[(y * 16 + x) * 256 + k] = blockByte;
		        				}
	        				}
	        			}
	        			else if(k < 62)
	        			{
	        				primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getDefaultState());
	        			}
	        			else if(k < 62 + grassRaise)
	        			{
	        				if(depth == 0)
	        				{
	        					primer.setBlockState((y * 16 + x) * 256 + k, Blocks.grass.getDefaultState());
	        				}
	        				else
	        				{
	        					primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getDefaultState());
	        				}
	        			}
	        			else if(k < 75 + grassRaise)
	        			{
	        				if(depth == 0)
	        				{
		        				int r = (int)((k - (62 + grassRaise)) / 2f);
		        				if(rand.nextInt(r + 1) == 0)
		        				{
			        				primer.setBlockState((y * 16 + x) * 256 + k, Blocks.grass.getDefaultState());
		        				}
		        				else if(rand.nextInt((int)(r / 2f) + 1) == 0)
		        				{
			        				primer.setBlockState((y * 16 + x) * 256 + k, Blocks.dirt.getDefaultState());
		        				}
		        				else
		        				{
			        				primer.setBlockState((y * 16 + x) * 256 + k, topBlock.getDefaultState());
			        				metadata[(y * 16 + x) * 256 + k] = blockByte;
		        				}
	        				}
	        				else
	        				{
		        				primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock.getDefaultState());
		        				metadata[(y * 16 + x) * 256 + k] = blockByte;
	        				}
	        			}
	        			else
	        			{
	        				if(depth == 0)
	        				{
		        				primer.setBlockState((y * 16 + x) * 256 + k, topBlock.getDefaultState());
		        				metadata[(y * 16 + x) * 256 + k] = blockByte;
	        				}
	        				else
	        				{
		        				primer.setBlockState((y * 16 + x) * 256 + k, fillerBlock.getDefaultState());
		        				metadata[(y * 16 + x) * 256 + k] = blockByte;
	        				}
	        			}
	            	}
        		}
        		else if(k > 63)
        		{
        			primer.setBlockState((y * 16 + x) * 256 + k, Blocks.stained_hardened_clay.getDefaultState());
        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
        		}
            }
		}
	}
}
