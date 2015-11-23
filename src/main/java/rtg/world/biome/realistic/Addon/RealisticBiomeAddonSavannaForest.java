package rtg.world.biome.realistic.addon;

import java.util.Random;

import rtg.config.addon.ConfigAddon;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;

import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.biome.BiomeBase.Climate;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenCacti;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.WorldGenWaterGrass;
import rtg.world.gen.feature.tree.WorldGenTreeBirch;
import rtg.world.gen.feature.tree.WorldGenTreeJungleSmall;
import rtg.world.gen.feature.tree.WorldGenTreePineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeSavanna;
import rtg.world.gen.feature.tree.WorldGenTreeSpruceSmall;
import rtg.world.gen.surface.*;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.terrain.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

public class RealisticBiomeAddonSavannaForest extends RealisticBiomeBase
{	
	private TerrainBase terrain;
	private SurfaceBase surface;

	public RealisticBiomeAddonSavannaForest() 
	{
		super(
				RealisticBiomeAddonBase.savannaForest,
				BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainGrasslandMountains(),
				new SurfaceMountainStone(Blocks.grass, Blocks.dirt, false, null, 0.6f)
				);
		
		this.setRealisticBiomeName("Savanna Forest");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigAddon.weightSavannaForest;
		this.generateVillages = ConfigAddon.villageSavannaForest;	
	}

	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise perlin, CellNoise cell, float strength, float river)
    {
		for (int i23 = 0; i23 < 1; i23++)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		//	(new WorldGenBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
		}
		
		float n = perlin.noise2((chunkX + 1000f) / 150f, chunkY / 150f);
		if(river > 0.8f)
		{
			for(int b33 = 0; b33 < 15f * strength; b33++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(10) == 0 ? new WorldGenTreeSavanna(0): new WorldGenTreeSavanna(2);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			
			for(int k18 = 0; k18 < 12f * strength; k18++)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(160);
				int k24 = chunkY + rand.nextInt(16) + 8;
				if(j23 < 120f)
				{
					(new WorldGenCacti(false)).generate(world, rand, k21, j23, k24);
				}
			}
			
			for(int f25 = 0; f25 < 2f * strength; f25++)
			{
				int i18 = chunkX + rand.nextInt(16) + 8;
				int i23 = chunkY + rand.nextInt(16) + 8;
				(new WorldGenReed()).generate(world, rand, i18, 60 + rand.nextInt(8), i23);
			}
		}
		else if(n > -0.25f && n < 0.25f)
		{
			for(int b33 = 0; b33 < 7f * strength; b33++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
				{
					WorldGenerator worldgenerator = rand.nextInt(9) == 0 ? new WorldGenShrub(0, 0) : rand.nextInt(7) == 0 ? new WorldGenTreeSavanna(1): new WorldGenTreeSavanna(2);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			
			for(int k18 = 0; k18 < 12f * strength; k18++)
			{
				int k21 = chunkX + rand.nextInt(16) + 8;
				int j23 = rand.nextInt(160);
				int k24 = chunkY + rand.nextInt(16) + 8;
				if(j23 < 120f)
				{
					(new WorldGenCacti(false)).generate(world, rand, k21, j23, k24);
				}
			}
		}
		else
		{
			int a = 3 - (int)(perlin.noise2(chunkX / 100f, chunkY / 100f) * 7);
			if(a < 1 || rand.nextInt(a) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 100f || (z52 < 120f && rand.nextInt(10) == 0))
				{
					WorldGenerator worldgenerator = rand.nextBoolean() ? new WorldGenShrub(0, 0) : rand.nextInt(5) == 0 ? new WorldGenTreeSavanna(0) : new WorldGenTreeSavanna(1);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
			
			if(n > 0.25f)
			{
				for(int k18 = 0; k18 < 13f * strength; k18++)
				{
					int k21 = chunkX + rand.nextInt(16) + 8;
					int k24 = chunkY + rand.nextInt(16) + 8;
					int j23 = world.getHeightValue(k21, k24);
					if(j23 < 120f)
					{
						(new WorldGenCacti(false)).generate(world, rand, k21, j23, k24);
					}
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
			(new WorldGenFlowers(new int[]{9,9,9,9,3,3,3,3,3,2,2,2,11,11,11})).generate(world, rand, j15, j17, j20);
		}
		
		for(int l14 = 0; l14 < 15; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;

			if(rand.nextInt(6) == 0)
			{
				(new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
			}
			else
			{
				(new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
			}
		}
    }
    
    public float rNoise(OpenSimplexNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border);
    }
    
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
    }
}
