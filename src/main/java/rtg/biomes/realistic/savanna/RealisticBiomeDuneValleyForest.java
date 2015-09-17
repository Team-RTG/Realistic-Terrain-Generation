package rtg.biomes.realistic.savanna;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.RTGBiomes;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.deco.DecoBlob;
import rtg.deco.DecoCacti;
import rtg.deco.DecoFlowers;
import rtg.deco.DecoGrass;
import rtg.deco.trees.DecoJungleTall;
import rtg.deco.trees.DecoSavannah;
import rtg.surface.SurfaceBase;
import rtg.surface.SurfaceDuneValley;
import rtg.terrain.TerrainBase;
import rtg.terrain.TerrainDuneValley;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class RealisticBiomeDuneValleyForest extends RealisticBiomeBase
{
	private TerrainBase terrain;
	private SurfaceBase surface;

	public RealisticBiomeDuneValleyForest() 
	{
		super(0, RTGBiomes.baseHotForest, VanillaBiomes.vanillaRiverOasis);

		terrain = new TerrainDuneValley(220f);
		surface = new SurfaceDuneValley(Blocks.grass, Blocks.dirt, 220f, false, true);
	}

	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		if(rand.nextInt((int)(2f / strength)) == 0)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 85)
			{
				(new DecoBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		float t = perlin.noise2(chunkX / 30f, chunkY / 30f) * 8f;
		t = t > 4f ? 4f : t;
		
		if(t > 0f)
		{
			if(rand.nextInt((int)((10f - t) / strength)) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 82)
				{
					WorldGenerator worldgenerator = new DecoJungleTall(Blocks.log, 2, Blocks.leaves, 2, 6 + rand.nextInt(4), 3 + rand.nextInt(2), 9f + rand.nextFloat() * 4f, 3, 0.32f, 0.1f);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		
		if(river > 0.8f)
		{
			for(int b33 = 0; b33 < 8f * strength; b33++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 82)
				{
					WorldGenerator worldgenerator = rand.nextInt(2) != 0 ? new WorldGenShrub(0, 0) : new DecoSavannah(1, false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		else
		{
			for(int b33 = 0; b33 < (1f + t) * strength; b33++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 82)
				{
					WorldGenerator worldgenerator = rand.nextInt(6) != 0 ? new WorldGenShrub(0, 0) : new DecoSavannah(1, false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		
		if(rand.nextInt((int)(3f / strength)) == 0) 
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
		
		for(int k18 = 0; k18 < 26f * strength; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = 66 + rand.nextInt(20);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoCacti(false)).generate(world, rand, k21, j23, k24);
		}
		
		for(int l14 = 0; l14 < 8f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = 60 + rand.nextInt(40);
			int j24 = chunkY + rand.nextInt(16) + 8;

			if(rand.nextInt(3) == 0)
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
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }

	@Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
		surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
