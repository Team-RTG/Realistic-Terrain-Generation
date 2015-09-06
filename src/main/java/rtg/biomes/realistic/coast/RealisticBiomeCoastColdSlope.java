package rtg.biomes.realistic.coast;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.deco.DecoBlob;
import rtg.deco.DecoFlowers;
import rtg.deco.DecoGrass;
import rtg.deco.DecoLog;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;

public class RealisticBiomeCoastColdSlope extends RealisticBiomeBase
{
	public RealisticBiomeCoastColdSlope() 
	{
		super(0, RTGBiomes.baseColdPlains);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		for (int l = 0; l < 2f * strength; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 68 || rand.nextInt(4) == 0)
			{
		    	(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
    	if(rand.nextInt((int)(4f / strength)) == 0)
    	{
			int x22 = chunkX + rand.nextInt(16) + 8;
			int z22 = chunkY + rand.nextInt(16) + 8;
			int y22 = world.getHeightValue(x22, z22);
			if(y22 < 70)
			{
				(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
			}
    	}
		
		for(int f23 = 0; f23 < 1f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = 64 + rand.nextInt(64);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
		}
		
		for(int l14 = 0; l14 < 3f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = 64 + rand.nextInt(64);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		river = river > 0.5f ? 1f : river * 2f;

		float start = (perlin.noise2(x / 90f, y / 90f) * 1f) + (perlin.noise2(x / 40f, y / 40f) * 0.15f) + (perlin.noise2(x / 9f, y / 9f) * 0.07f);
		
		float h = 0f;
		h = ocean < 1f ? ocean * 15f : 15f;
		
		float c = 0f;
		if(ocean + start > 0.8f)
		{
			c = ocean + start > 1.5f ? 0.7f : (ocean + start) - 0.8f;
			c *= 35f + perlin.noise2(x / 50f, y / 50f) * 8f;
		}
		
		if(ocean < 1.6f)
		{
			float st = (1.2f - ocean) * 20f;
			st = st > 1f ? 1f : st;
			
			h += perlin.noise2(x / 12f, y / 12f);
			h += perlin.noise2(x / 20f, y / 20f) * 2;
		}
		
		return 55f + h + (c * river);
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
		float c = CliffCalculator.calc(x, y, noise);
		boolean cliff = c > 0.5f ? true : false;
		boolean gravel = false;
		
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
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else if (depth < 10)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
            		}
            	}
            	else
            	{
	        		if(depth == 0)
	        		{
	        			if(k > 64)
	        			{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	        			}
		        		else
		        		{
		        			blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
		        			gravel = true;
		        		}
	        		}
	        		else if(depth < 4)
	        		{
	        			if(gravel)
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.gravel;
	        			}
	        			else
	        			{
	        				blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        			}
	        		}
            	}
            }
		}
    }
}
