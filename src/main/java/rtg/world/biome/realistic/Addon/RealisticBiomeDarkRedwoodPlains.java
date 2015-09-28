package rwg.biomes.realistic.forest;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import rwg.api.RWGBiomes;
import rwg.biomes.realistic.RealisticBiomeBase;
import rwg.deco.DecoFlowers;
import rwg.deco.DecoGrass;
import rwg.deco.DecoLog;
import rwg.deco.DecoWildWheat;
import rwg.deco.trees.DecoLargePine;
import rwg.deco.trees.DecoShrub;
import rwg.deco.trees.DecoSmallPine;
import rwg.surface.SurfaceBase;
import rwg.surface.SurfaceMountainStone;
import rwg.surface.SurfaceMountainStoneMix1;
import rwg.terrain.TerrainBase;
import rwg.terrain.TerrainGrasslandFlats;
import rwg.terrain.TerrainHilly;
import rwg.util.CellNoise;
import rwg.util.PerlinNoise;

public class RealisticBiomeDarkRedwoodPlains extends RealisticBiomeBase
{
	private TerrainBase terrain;
	private SurfaceBase surface;

	public RealisticBiomeDarkRedwoodPlains() 
	{
		super(0, RWGBiomes.baseColdForest, RealisticBiomeBase.coastDunes, RWGBiomes.baseRiverTemperate);
		
		terrain = new TerrainGrasslandFlats();
		surface = new SurfaceMountainStoneMix1(Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.stone, 0.15f);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		float l = perlin.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
		for (int b1 = 0; b1 < l * strength; b1++)
		{
			if(rand.nextInt(6) == 0)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 110)
				{
					WorldGenerator worldgenerator = new DecoLargePine(11 + rand.nextInt(11), 15 + rand.nextInt(15), 1, 1);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		
		if(l > 5f)
		{
			for (int b2 = 0; b2 < 3f * strength; b2++)
			{
				int j6 = chunkX + rand.nextInt(16) + 8;
				int k10 = chunkY + rand.nextInt(16) + 8;
				int z52 = world.getHeightValue(j6, k10);
	
				if(z52 < 120)
				{
					WorldGenerator worldgenerator = rand.nextInt(4) == 0 ? new DecoSmallPine(4 + rand.nextInt(7), 6 + rand.nextInt(9), 0) : rand.nextInt(6) != 0 ? new WorldGenTrees(false) : new WorldGenForest(false, false);
					worldgenerator.setScale(1.0D, 1.0D, 1.0D);
					worldgenerator.generate(world, rand, j6, z52, k10);
				}
			}
		}
		
    	if(rand.nextInt((int)(4f / strength)) == 0)
    	{
			int x22 = chunkX + rand.nextInt(16) + 8;
			int z22 = chunkY + rand.nextInt(16) + 8;
			int y22 = world.getHeightValue(x22, z22);
			if(y22 < 100)
			{
				(new DecoLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);	
			}
    	}
    		
		for(int f24 = 0; f24 < 3f * strength; f24++)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 110)
			{
				(new DecoShrub(rand.nextInt(4) + 1, 0, rand.nextInt(3))).generate(world, rand, i1, k1, j1);
			}
    	}
		
		if(rand.nextInt((int)(150f / strength)) == 0)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(60) + 60;
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
		}
    	
		if(rand.nextInt((int)(15f / strength)) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(100);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		for(int f23 = 0; f23 < 8f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{0,1,2,3,4,5,6,7,8,9,10,11})).generate(world, rand, j15, j17, j20);
		}
    	
		for(int l14 = 0; l14 < 12f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = rand.nextInt(128);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new DecoGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
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
