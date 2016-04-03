package rtg.world.gen.surface.vanilla;

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

public class SurfaceVanillaDesertM extends SurfaceBase
{
	private int[] claycolor = new int[100];
	private int grassRaise = 0;
	
	public SurfaceVanillaDesertM(BiomeConfig config, Block top, byte topByte, Block fill, byte fillByte, int grassHeight)
	{
		super(config, top, topByte, fill, fillByte);
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
		return (claycolor[k] == 0) ? (byte)12 : ((claycolor[k] == 1) ? (byte)0 : (byte)claycolor[k]);
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

                if(k > 110)
                {
                    blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
                    metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
                }
                else if(depth > -1 && depth < 12)
	        	{
	            	if(cliff)
	            	{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
	        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	            	}
	            	else
	            	{
	        			if(depth > 4)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
		        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
	        			}
	        			else if(k > 74 + grassRaise)
	        			{

	        				if(depth == 0)
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = topBlock;
		        				metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
	        				}
	        				else
	        				{
		        				blocks[(y * 16 + x) * 256 + k] = fillerBlock;
		        				metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
	        				}
	        			}
	        			else if(k < 62)
	        			{
                            blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                            metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
	        			}
	        			else if(k < 62 + grassRaise)
	        			{
                            if(depth == 0)
                            {
                                blocks[(y * 16 + x) * 256 + k] = topBlock;
                                metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                            }
                            else
                            {
                                blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                                metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                            }
	        			}
	        			else if(k < 75 + grassRaise)
	        			{
                            if(depth == 0)
                            {
                                blocks[(y * 16 + x) * 256 + k] = topBlock;
                                metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                            }
                            else
                            {
                                blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                                metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                            }
	        			}
	        			else
	        			{
                            if(depth == 0)
                            {
                                blocks[(y * 16 + x) * 256 + k] = topBlock;
                                metadata[(y * 16 + x) * 256 + k] = topBlockMeta;
                            }
                            else
                            {
                                blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                                metadata[(y * 16 + x) * 256 + k] = fillerBlockMeta;
                            }
	        			}
	            	}
        		}
        		else if(k > 63)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay;
        			metadata[(y * 16 + x) * 256 + k] = getClayColorForHeight(k);
        		}
            }
		}
	}
}