package rtg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.RTGBiomes;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.config.ConfigRTG;
import rtg.deco.DecoBlob;
import rtg.deco.DecoGrass;
import rtg.deco.trees.DecoJungleFat;
import rtg.deco.trees.DecoJungleSmall;
import rtg.deco.trees.DecoJungleTall;
import rtg.deco.trees.DecoLargePine;
import rtg.deco.trees.DecoSavannah;
import rtg.surface.SurfaceBase;
import rtg.surface.SurfaceGrassland;
import rtg.terrain.TerrainBase;
import rtg.terrain.TerrainHilly;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class RealisticBiomeRedwoodJungle extends RealisticBiomeBase
{
	private TerrainBase terrain;
	private SurfaceBase surface;
	
	public RealisticBiomeRedwoodJungle() 
	{
		super(0, RTGBiomes.baseJungle);
		terrain = new TerrainHilly(230f, 100f, 0f);
		surface = new SurfaceGrassland(Blocks.grass, Blocks.dirt, Blocks.stone, Blocks.cobblestone);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		if ( ConfigRTG.enableCobblestoneBoulders  && DecoBlob.shouldGenerateCobblestoneBoulder()) {

			for (int i23 = 0; i23 < 2f * strength; i23++)
			{
				int i1 = chunkX + rand.nextInt(16) + 8;
				int j1 = chunkY + rand.nextInt(16) + 8;
				int k1 = world.getHeightValue(i1, j1);
				(new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		float l = perlin.noise2(chunkX / 80f, chunkY / 80f) * 34f + 10f;
		if(rand.nextInt((int)(50f / strength)) == 0)
		{
			int j6 = chunkX + rand.nextInt(6) + 13;
			int k10 = chunkY + rand.nextInt(6) + 13;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 110)
			{
				WorldGenerator worldgenerator = new DecoJungleFat(Blocks.log, 3, Blocks.leaves, 3, 20 + rand.nextInt(8), 7 + rand.nextInt(2), 23f, 7, 0.22f, 0.2f);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
		
		for(int a = 0; a < l * strength; a++)
		{
			if(rand.nextInt(3) == 0)
			{
				int j6 = chunkX + rand.nextInt(12) + 10;
				int k10 = chunkY + rand.nextInt(12) + 10;
				int z52 = world.getHeightValue(j6, k10);
		
				WorldGenerator worldgenerator = rand.nextInt(24) == 0 ? new DecoJungleFat(Blocks.log, 3, Blocks.leaves, 3, 14 + rand.nextInt(8), 5 + rand.nextInt(2), 16f, 5, 0.32f, 0.1f) : new DecoJungleTall(Blocks.log, 3, Blocks.leaves, 3, 3 + rand.nextInt(7), 3 + rand.nextInt(2), 9f, 3, 0.32f, 0.1f);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
		
		for(int b33 = 0; b33 < 3f * strength; b33++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			WorldGenerator worldgenerator = rand.nextInt(10) == 0 ? new DecoJungleSmall(Blocks.log, 3, Blocks.leaves, 3, 1 + rand.nextInt(4), 0, 5f, 2, 0.32f, 0.14f) : new WorldGenShrub(0, 0);
			worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			worldgenerator.generate(world, rand, j6, z52, k10);
		}

		for(int b = 0; b < 2f * strength; b++)
		{
			int i18 = chunkX + rand.nextInt(16) + 8;
			int i23 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
		}
		
		for(int l14 = 0; l14 < 13; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			if(rand.nextInt(6) == 0)
			{
				(new DecoGrass(Blocks.double_plant, rand.nextBoolean() ? 2 : 3)).generate(world, rand, l19, k22, j24);
			}
			else
			{
				(new DecoGrass(Blocks.tallgrass, rand.nextBoolean() ? 1 : 2)).generate(world, rand, l19, k22, j24);
			}
		}
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
