package rtg.world.biome.realistic.Addon;

import java.util.Random;

import rtg.util.CellNoise;
import rtg.util.PerlinNoise;
import rtg.world.biome.BiomeBase;
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
import rtg.world.gen.surface.*;
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

public class RealisticBiomeMesaPlains extends RealisticBiomeBase
{
	
	public RealisticBiomeMesaPlains() 
	{
		super(
				RealisticBiomeAddonBase.mesaPlains,
				BiomeBase.climatizedBiome(BiomeGenBase.river, BiomeBase.Climate.WET),
				new TerrainMesa(),
				new SurfaceCanyon(Blocks.sand, Blocks.sand, (byte)1, 20)
			);
	}

	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		for (int l = 0; l < 1f * strength; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
		    if(k1 < 83)
			{
				(new WorldGenBlob(Blocks.cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		if(river > 0.7f)
		{
			for(int b33 = 0; b33 < 6f * strength; b33++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 90)
				{
					WorldGenerator worldgenerator = rand.nextInt(3) != 0 ? new WorldGenShrub(0, 0) : new WorldGenTreeSavanna(1, false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		else
		{
			if(rand.nextInt((int)(2f / strength)) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);

				if(z52 < 90)
				{
					WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(12) == 0 ? new WorldGenTreeSavanna(0) : new WorldGenTreeSavanna(1);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		
		for(int i15 = 0; i15 < 5; i15++)
		{
			int i17 = chunkX + rand.nextInt(16) + 8;
			int i20 = 64 + rand.nextInt(100);
			int l22 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
		}
		
		for(int k18 = 0; k18 < 15; k18++)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = 64 + rand.nextInt(80);
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenCacti(false)).generate(world, rand, k21, j23, k24);
		}

		for(int f23 = 0; f23 < 3; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenFlowers(new int[]{9,9,9,9,3,3,3,3,3,2,2,2,11,11,11})).generate(world, rand, j15, j17, j20);
		}
		
		for(int l14 = 0; l14 < 8f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = 60 + rand.nextInt(70);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
    }
    
    public float rNoise(PerlinNoise perlin, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	return terrain.generateNoise(perlin, cell, x, y, ocean, border, river);
    }
    
   // public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, PerlinNoise perlin, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
   // {
   // 	surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, perlin, cell, noise, river, base);
   // }
}
