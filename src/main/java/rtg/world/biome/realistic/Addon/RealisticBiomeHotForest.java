package rwg.biomes.realistic.savanna;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import rwg.api.RWGBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.deco.DecoBlob;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoLog;
import rwg.deco.DecoWaterGrass;
import rwg.deco.DecoWildWheat;
import rwg.deco.trees.DecoBirch;
import rwg.deco.trees.DecoShrub;
import rwg.deco.trees.DecoSmallPine;
import rwg.deco.trees.DecoSmallSpruce;
import rwg.surface.SurfaceBase;
import rwg.surface.SurfaceGrasslandMixBig;
import rwg.surface.SurfaceGrasslandMix1;
import rwg.terrain.TerrainBase;
import rwg.terrain.TerrainGrasslandFlats;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class RealisticBiomeHotForest extends RealisticBiomeBase
{
	private TerrainBase terrain;
	private SurfaceBase surface;
	
	public RealisticBiomeHotForest() 
	{
		super(0, RWGBiomes.baseHotForest, RealisticBiomeBase.coastDunes, RWGBiomes.baseRiverHot);
		
		terrain = new TerrainGrasslandFlats();
		surface = new SurfaceGrasslandMixBig(Blocks.sand, Blocks.sand, Blocks.grass, Blocks.dirt, Blocks.stone, Blocks.cobblestone, 60f, -0.14f, 14f, 0.25f);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
        if(rand.nextInt((int)(15f / strength)) == 0)
		{
			int i2 = chunkX + rand.nextInt(16) + 8;
			int i8 = chunkY + rand.nextInt(16) + 8;
			int l4 = world.getHeightValue(i2, i8);
			if(l4 > 63)
			{
				(new WorldGenLakes(Blocks.water)).generate(world, rand, i2, l4, i8);
			}
		}
        
		//boulders
		for (int l = 0; l < 3f * strength; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 95 && (k1 < 64 || rand.nextInt(7) == 0))
			{
		    	(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		//trees
		float l = perlin.noise2((chunkX + 16f) / 60f, (chunkY + 16f) / 60f) * 6f + 0.2f;
		for (int b1 = 0; b1 < l * 4f * strength; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			WorldGenerator worldgenerator = rand.nextInt(6) == 0 ? new WorldGenTrees(false) : rand.nextInt(12) == 0 ? new DecoBirch(4 + rand.nextInt(5), 6 + rand.nextInt(5)) : rand.nextInt(6) == 0 ? new DecoSmallPine(3 + rand.nextInt(2), 3 + rand.nextInt(3), 0) : new DecoSmallPine(6 + rand.nextInt(5), 3 + rand.nextInt(6), 0);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}
		
		if(l < 0.5f)
		{
			for(int l14 = 0; l14 < (l + 2f) * 4f * strength; l14++)
			{
				int l19 = chunkX + rand.nextInt(16) + 8;
				int k22 = 64 + rand.nextInt(64);
				int j24 = chunkY + rand.nextInt(16) + 8;

				(new DecoWaterGrass(Blocks.tallgrass, 1, 65)).generate(world, rand, l19, k22, j24);
			}
		}
		
    	if(l > 0f && rand.nextInt(6) == 0)
    	{
			int x22 = chunkX + rand.nextInt(16) + 8;
			int z22 = chunkY + rand.nextInt(16) + 8;
			int y22 = world.getHeightValue(x22, z22);
			(new DecoLog(0, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
    	}
		
    	for(int b = 0; b < 2f * strength; b++)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(rand.nextInt(10) == 0)
		    {
    		    (new DecoShrub(rand.nextInt(5) + 4, rand.nextInt(2), 0)).generate(world, rand, i1, k1, j1);
		    }
		    else
		    {
		    	(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), 0)).generate(world, rand, i1, k1, j1);
		    }
    	}

		if(rand.nextInt((int)(3f / strength)) == 0)
		{
			int k15 = chunkX + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(64) + 64;
			int k20 = chunkY + rand.nextInt(16) + 8;
			
			if(rand.nextBoolean())
			{
				(new WorldGenFlowers(Blocks.brown_mushroom)).generate(world, rand, k15, k17, k20);
			}
			else
			{
				(new WorldGenFlowers(Blocks.red_mushroom)).generate(world, rand, k15, k17, k20);
			}
		}
		
		if(rand.nextInt((int)(20f / strength)) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(128);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		for(int f23 = 0; f23 < 4f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{9,9,9,9,3,3,3,3,3,2,2,2,11,11,11})).generate(world, rand, j15, j17, j20);
		}

		for(int l14 = 0; l14 < 10f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
    }

	@Override
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
		return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }

	@Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
