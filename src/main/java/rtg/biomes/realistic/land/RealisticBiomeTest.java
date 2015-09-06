package rtg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMelon;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.deco.DecoFlowers;
import rtg.deco.DecoGrass;
import rtg.deco.DecoLog;
import rtg.deco.DecoWildWheat;
import rtg.deco.trees.DecoBirch;
import rtg.deco.trees.DecoJungleFat;
import rtg.deco.trees.DecoJungleSmall;
import rtg.deco.trees.DecoSavannah;
import rtg.deco.trees.DecoShrub;
import rtg.deco.trees.DecoJungleTall;
import rtg.surface.SurfaceBase;
import rtg.surface.SurfaceMountainSnow;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class RealisticBiomeTest extends RealisticBiomeBase
{
	private SurfaceBase surface;
	
	public RealisticBiomeTest() 
	{
		super(0, RTGBiomes.baseColdForest);
		
		surface = new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, true, Blocks.sand, 0.2f);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		/*
		for(int a = 0; a < 1f * strength; a++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
	
			WorldGenerator worldgenerator = new DecoBirch(4 + rand.nextInt(5), 6 + rand.nextInt(5));
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}*/
		
		/*
		for(int f24 = 0; f24 < 3f * strength; f24++)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			(new DecoShrub(rand.nextInt(4) + 1, 3, 3)).generate(world, rand, i1, k1, j1);
    	}
		
		//for(int a = 0; a < 15f * strength; a++)
		//if(rand.nextInt((int)(3f / strength)) == 0) 

		//LARGE LAYER
		for(int a = 0; a < 3f * strength; a++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
	
			WorldGenerator worldgenerator = rand.nextInt(24) == 0 ? new DecoJungleFat(Blocks.log, 3, Blocks.leaves, 3, 11 + rand.nextInt(5), 5 + rand.nextInt(2), 16f, 5, 0.32f, 0.1f) : new DecoJungleTall(Blocks.log, 3, Blocks.leaves, 3, 3 + rand.nextInt(4), 3 + rand.nextInt(2), 9f, 3, 0.32f, 0.1f);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
		
		//GROUND LAYER
		for(int a = 0; a < 5f * strength; a++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);
	
			WorldGenerator worldgenerator = new DecoJungleSmall(Blocks.log, 3, Blocks.leaves, 3, 1 + rand.nextInt(4), 0, 5f, 2, 0.32f, 0.14f);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
		
		if(rand.nextInt((int)(120f / strength)) == 0)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(60) + 60;
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
		}
		
    	if(rand.nextInt((int)(5f / strength)) == 0)
    	{
			int x22 = chunkX + rand.nextInt(16) + 8;
			int z22 = chunkY + rand.nextInt(16) + 8;
			int y22 = world.getHeightValue(x22, z22);
			if(y22 < 100)
			{
				(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
			}
    	}
    	
		if(rand.nextInt((int)(15f / strength)) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(100);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenMelon()).generate(world, rand, j16, j18, j21);
		}*/
		
		/* ==============================================================================================
		for(int f23 = 0; f23 < 8f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{0,1,2,3,4,5,6,7,8,9,10,11})).generate(world, rand, j15, j17, j20);
		}*/
    	
		/*for(int l14 = 0; l14 < 12f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new DecoGrass(Blocks.tallgrass, 1 + rand.nextInt(2))).generate(world, rand, l19, k22, j24);
		}*/
		
		/*
		WorldGenVines worldgenvines = new WorldGenVines();
        for (int b = 0; b < 30f * strength; ++b)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            short short1 = 128;
            int j1 = chunkY + rand.nextInt(16) + 8;
            worldgenvines.generate(world, rand, i1, short1, j1);
        }
        */
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		/*
		float pX = x;// + (perlin.noise1(y / 130f) * 80f);
		float pY = y;// + (perlin.noise1(x / 130f) * 80f);
		
		float st = 1.005f + cell.border2(pX / 250D, pY / 250D, 0.2D, 1F);
		st *= river;
		//st *= st * 2f > 1f ? 1f : st * 2f;
		
		float h = st * 50f;
		*/
		/*
		float h = st * (90f + perlin.noise2(pX / 50f, pY / 50f) * 20f);
		
		if(h < 10f)
		{
			h += perlin.noise2(x / 25f, y / 25f) * (10f - h) * 0.2f;
		}
		
		if(h > 10f)
		{
			float d = (h - 10f) / 2f > 8f ? 8f : (h - 10f) / 2f;
			h += perlin.noise2(x / 35f, y / 35f) * d;
			h += perlin.noise2(x / 60f, y / 60f) * d * 0.5f;

			if(h > 35f)
			{
				float d2 = (h - 35f) / 1.5f > 30f ? 30f : (h - 35f) / 1.5f;
				h += cell.noise(x / 25D, y / 25D, 1D) * d2;
			}
		}

		if(h > 2f)
		{
			float d = (h - 2f) / 2f > 4f ? 4f : (h - 2f) / 2f;
    		h += perlin.noise2(x / 28f, y / 28f) * d;
    		h += perlin.noise2(x / 18f, y / 18f) * (d / 2f);
    		h += perlin.noise2(x / 8f, y / 8f) * (d / 2f);
		}*/
		
		return 70f;
    }

	@Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
		surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
		
		/*
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
    				if(k < 62)
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
    				}
    				else
    				{
    					blocks[(y * 16 + x) * 256 + k] = Blocks.grass;
    				}
        		}
        		else if(depth < 6)
        		{
        			blocks[(y * 16 + x) * 256 + k] = Blocks.dirt;
        		}
            }
		}
		*/
    }
}
