package rtg.surface.vanilla;

import java.util.Random;

import rtg.surface.SurfaceBase;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceVanillaExtremeHills extends SurfaceBase
{
	private boolean beach;
	private Block beachBlock;
	private float min;
	
	private float sCliff = 1.5f;
	private float sHeight = 60f;
	private float sStrength = 65f;
	private float cCliff = 1.5f;
	
	public byte topByte = 0;
	
	public SurfaceVanillaExtremeHills(Block top, Block fill, boolean genBeach, Block genBeachBlock, float minCliff) 
	{
		super(top, fill);
		beach = genBeach;
		beachBlock = genBeachBlock;
		min = minCliff;
	}
	
	public SurfaceVanillaExtremeHills(Block top, Block fill, boolean genBeach, Block genBeachBlock, float minCliff, float stoneCliff, float stoneHeight, float stoneStrength, float clayCliff)
	{
		this(top, fill, genBeach, genBeachBlock, minCliff);
		
		sCliff = stoneCliff;
		sHeight = stoneHeight;
		sStrength = stoneStrength;
		cCliff = clayCliff;
	}
	
	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
	{
		float c = CliffCalculator.calc(x, y, noise);
		int cliff = 0;
		boolean gravel = false;
		
    	Block b;
		for(int k = 255; k > -1; k--)
		{
			b = blocks[(y * 16 + x) * 256 + k];
            if(b == Blocks.air)
            {
            	depth = -1;
            }
            else if(b == Blocks.stone)
            {
            	depth++;
            	
            	if(depth == 0)
            	{
            		if(k < 63)
            		{
            			if(beach)
            			{
            				gravel = false; //This will fix falling blocks spam crash (this was true)
            			}
            		}

					float p = perlin.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
        			if(c > min && c > sCliff - ((k - sHeight) / sStrength) + p)
        			{
        				cliff = 1;
        			}
            		if(c > cCliff)
        			{
        				cliff = 2;
        			}
            		
            		if(cliff == 1)
            		{
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
        				metadata[(y * 16 + x) * 256 + k] = 9; 
            		}
            		else if(k < 63)
            		{
            			if(beach)
            			{
	            			blocks[(y * 16 + x) * 256 + k] = beachBlock;
	            			gravel = false; //This will fix falling blocks spam crash (this was true)
            			}
            			else if(k < 62)
            			{
                			blocks[(y * 16 + x) * 256 + k] = fillerBlock;
            			}
            			else
            			{
                			blocks[(y * 16 + x) * 256 + k] = topBlock;
                			metadata[(y * 16 + x) * 256 + k] = topByte;
            			}
            		}
            		else
            		{
            			blocks[(y * 16 + x) * 256 + k] = topBlock;
            			metadata[(y * 16 + x) * 256 + k] = topByte;
            		}
            	}
            	else if(depth < 6)
        		{
            		if(cliff == 1)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone; 
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = Blocks.stained_hardened_clay; 
        				metadata[(y * 16 + x) * 256 + k] = 9; 
            		}
            		else if(gravel)
            		{
            			blocks[(y * 16 + x) * 256 + k] = beachBlock;
            		}
            		else
            		{
            			blocks[(y * 16 + x) * 256 + k] = fillerBlock;
            		}
        		}
            }
		}
	}
}
