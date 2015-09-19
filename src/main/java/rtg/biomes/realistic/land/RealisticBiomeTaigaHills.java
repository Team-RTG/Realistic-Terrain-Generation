package rtg.biomes.realistic.land;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.RTGBiomes;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.biomes.vanilla.VanillaBiomes.Climate;
import rtg.biomes.realistic.RealisticBiomeBase;
import rtg.deco.DecoBlob;
import rtg.deco.DecoFlowers;
import rtg.deco.DecoGrass;
import rtg.deco.DecoLog;
import rtg.deco.DecoWildWheat;
import rtg.deco.trees.DecoPineTree;
import rtg.deco.trees.DecoShrub;
import rtg.deco.trees.DecoSmallPine;
import rtg.deco.trees.DecoSmallSpruce;
import rtg.surface.SurfaceBase;
import rtg.surface.SurfaceMountainSnow;
import rtg.terrain.TerrainBase;
import rtg.terrain.TerrainFlatLakes;
import rtg.terrain.TerrainMountainRiver;
import rtg.util.CellNoise;
import rtg.util.PerlinNoise;

public class RealisticBiomeTaigaHills extends RealisticBiomeBase
{
	private TerrainBase terrain;
	private SurfaceBase surface;

	public RealisticBiomeTaigaHills() 
	{
		super(0, RTGBiomes.baseColdForest, VanillaBiomes.climatizedBiome(BiomeGenBase.river, Climate.COLD));
		
		terrain = new TerrainMountainRiver();
		surface = new SurfaceMountainSnow(Blocks.grass, Blocks.dirt, true, Blocks.sand, 0.2f);
	}
	
	@Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, PerlinNoise perlin, CellNoise cell, float strength, float river)
    {
		for (int l = 0; l < 6f * strength; ++l)
		{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 95 && (k1 < 64 || rand.nextInt(15) == 0))
			{
		    	(new DecoBlob(Blocks.mossy_cobblestone, 0)).generate(world, rand, i1, k1, j1);
			}
		}
		
		float l = perlin.noise2(chunkX / 100f, chunkY / 100f) * 12f + 4f;
		for (int b1 = 0; b1 < l * strength; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 90)
			{
				WorldGenerator worldgenerator = rand.nextInt(10) != 0 ? new DecoPineTree(4, rand.nextInt(4) == 0 ? 1 : 0) : rand.nextInt(3) != 0 ? new DecoSmallPine(3 + rand.nextInt(6), 6 + rand.nextInt(8), 0) : new DecoSmallSpruce(rand.nextInt(2) + 1);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
			else if(z52 < 120)
			{
				WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new DecoSmallPine(1 + rand.nextInt(3), 3 + rand.nextInt(5), rand.nextInt(2)) : new DecoSmallSpruce(rand.nextInt(2));
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
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
    		
		for(int f24 = 0; f24 < 4f * strength; f24++)
    	{
			int i1 = chunkX + rand.nextInt(16) + 8;
			int j1 = chunkY + rand.nextInt(16) + 8;
		    int k1 = world.getHeightValue(i1, j1);
			if(k1 < 110)
			{
				(new DecoShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
			}
    	}

		if(rand.nextInt((int)(150f / strength)) == 0)
		{
			int k21 = chunkX + rand.nextInt(16) + 8;
			int j23 = rand.nextInt(60) + 60;
			int k24 = chunkY + rand.nextInt(16) + 8;
			(new DecoWildWheat(rand.nextInt(3))).generate(world, rand, k21, j23, k24);
		}
    	
		if(rand.nextInt((int)(20f / strength)) == 0)
		{
			int j16 = chunkX + rand.nextInt(16) + 8;
			int j18 = rand.nextInt(100);
			int j21 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenPumpkin()).generate(world, rand, j16, j18, j21);
		}
		
		for(int f23 = 0; f23 < 4f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = rand.nextInt(128);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new DecoFlowers(new int[]{9,0,3})).generate(world, rand, j15, j17, j20);
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
