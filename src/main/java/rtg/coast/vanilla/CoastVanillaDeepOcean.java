package rtg.coast.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.deco.DecoCacti;
import rtg.deco.DecoFlowers;
import rtg.deco.DecoGrass;
import rtg.deco.DecoWaterGrass;
import rtg.deco.trees.DecoSavannah;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.PerlinNoise;

public class CoastVanillaDeepOcean extends RealisticBiomeBase
{
	public CoastVanillaDeepOcean() 
	{
		super(0, VanillaBiomes.vanillaRiverOasis);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		if(strength > 0.7f)
		{
			for(int b33 = 0; b33 < 12f * strength; b33++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 > 65f && z52 < 90f)
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(6) != 0 ? new DecoSavannah(1) : rand.nextInt(5) != 0 ? new DecoSavannah(2) : new DecoSavannah(0);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		
		for(int k18 = 0; k18 < 12f * strength; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(160);
			int k24 = chunkY + rand.nextInt(16) + 8;
			if(j23 < 120f)
			{
				(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
			}
		}

		for(int f25 = 0; f25 < 2f * strength; f25++)
		{
			int i18 = chunkX + rand.nextInt(16) + 8;
			int i23 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
		}
		
		if(rand.nextInt(28) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(128);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		for(int f23 = 0; f23 < 3; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{9,9,9,9,3,3,3,3,3,2,2,2,11,11,11})).generate(world, rand, j15, j17, j20);
		}
		
		for(int l14 = 0; l14 < 15; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			if(rand.nextInt(6) == 0)
			{
				(new DecoGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
			}
			else
			{
				(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
			}
		}
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		river = river > 0.5f ? 1f : river * 2f;

		float start = (perlin.noise2(x / 90f, y / 90f) * 1f) + (perlin.noise2(x / 40f, y / 40f) * 0.15f) + (perlin.noise2(x / 9f, y / 9f) * 0.07f);
		
		float h = 0f;
		h = ocean < 1f ? ocean * 10f : 10f;
		
		float c = 0f;
		if(ocean + start > 1f)
		{
			c = ocean + start > 1.2f ? 0.2f : (ocean + start) - 1f;
			c *= 100 + perlin.noise2(x / 50f, y / 50f) * 55f;
		}
		
		if(ocean < 1.2f)
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
            			blocks[(y * 16 + x) * 256 + k] = rand.nextInt(3) == 0 ? Blocks.cobblestone : Blocks.stone; 
            		}
            		else if (depth < 10)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.stone;
            		}
            	}
            	else
            	{
            		if(depth < 7 && k < 65)
            		{
            			blocks[(y * 16 + x) * 256 + k] = Blocks.sand;
            		}
            		else if(depth == 0)// && k > 61)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
	        		}
	        		else if(depth < 4)
	        		{
	        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
	        		}
            	}
            }
		}
    }
}
