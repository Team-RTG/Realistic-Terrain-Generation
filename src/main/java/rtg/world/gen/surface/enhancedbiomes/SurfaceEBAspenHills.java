package rtg.world.gen.surface.enhancedbiomes;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBAspenHills;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class SurfaceEBAspenHills extends SurfaceEBBase
{
	private boolean beach;
	private Block beachBlock;
	private float min;

	private float sCliff = 1.5f;
	private float sHeight = 60f;
	private float sStrength = 65f;
	private float iCliff = 0.3f;
	private float iHeight = 100f;
	private float iStrength = 50f;
	private float cCliff = 1.5f;
	
    private byte topByte;
    private byte fillerByte;
	
	public SurfaceEBAspenHills(Block top, byte topMeta, Block fill, byte fillMeta, boolean genBeach, Block genBeachBlock, float minCliff) 
	{
		super(top, fill);
		beach = genBeach;
		beachBlock = genBeachBlock;
		min = minCliff;
	}
	
	public SurfaceEBAspenHills(Block top, byte topMeta, Block fill, byte fillMeta, boolean genBeach, Block genBeachBlock, float minCliff, float stoneCliff, float stoneHeight, float stoneStrength, float snowCliff, float snowHeight, float snowStrength, float clayCliff)
	{
		this(top, topMeta, fill, fillMeta, genBeach, genBeachBlock, minCliff);
		
		sCliff = stoneCliff;
		sHeight = stoneHeight;
		sStrength = stoneStrength;
		iCliff = snowCliff;
		iHeight = snowHeight;
		iStrength = snowStrength;
		cCliff = clayCliff;
		
        topByte = topMeta;
        fillerByte = fillMeta;
	}

	@Override
	public void paintTerrain(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
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

                if (shouldReplaceStone()) {
                    blocks[(y * 16 + x) * 256 + k] = RealisticBiomeEBAspenHills.ebDominantStoneBlock[0];
                    metadata[(y * 16 + x) * 256 + k] = RealisticBiomeEBAspenHills.ebDominantStoneMeta[0];
                }
            	
            	if(depth == 0)
            	{
            		if(k < 63)
            		{
            			if(beach)
            			{
            				gravel = true;
            			}
            		}

					float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
        			if(c > min && c > sCliff - ((k - sHeight) / sStrength) + p)
        			{
        				cliff = 1;
        			}
            		if(c > cCliff)
        			{
        				cliff = 2;
        			}
        			if(k > 110 + (p * 4) && c < iCliff + ((k - iHeight) / iStrength) + p)
        			{
        				cliff = 3;
        			}
            		
            		if(cliff == 1)
            		{
                        if (rand.nextInt(3) == 0) {
                            
                            blocks[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, hcCobble(world, i, j, x, y, k));
                            metadata[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EBAPI.SLATE, hcCobbleMeta(world, i, j, x, y, k));
                        }
                        else {
                            
                            blocks[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, hcStone(world, i, j, x, y, k));
                            metadata[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EBAPI.SLATE, hcStoneMeta(world, i, j, x, y, k));
                        }
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = shadowStoneBlock; 
        				metadata[(y * 16 + x) * 256 + k] = shadowStoneByte;
            		}
            		else if(cliff == 3)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            		}
            		else if(k < 63)
            		{
            			if(beach)
            			{
	            			blocks[(y * 16 + x) * 256 + k] = beachBlock;
	            			gravel = true;
            			}
            			else if(k < 62)
            			{
                            blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                            metadata[(y * 16 + x) * 256 + k] = fillerByte;
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
                        blocks[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, hcStone(world, i, j, x, y, k)); 
                        metadata[(y * 16 + x) * 256 + k] = EBAPI.ebStonify(EBAPI.SLATE, hcStoneMeta(world, i, j, x, y, k)); 
            		}
            		else if(cliff == 2)
            		{
        				blocks[(y * 16 + x) * 256 + k] = shadowStoneBlock; 
        				metadata[(y * 16 + x) * 256 + k] = shadowStoneByte;
            		}
            		else if(cliff == 3)
            		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.snow;
            		}
            		else if(gravel)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
            		}
            		else
            		{
                        blocks[(y * 16 + x) * 256 + k] = fillerBlock;
                        metadata[(y * 16 + x) * 256 + k] = fillerByte;
            		}
        		}
            }
		}
	}
}
