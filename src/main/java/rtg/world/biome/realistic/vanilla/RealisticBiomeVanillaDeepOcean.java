package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.config.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeGenManager;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.feature.tree.WorldGenTreeJungleSmall;
import rtg.world.gen.feature.tree.WorldGenTreeJungleTall;
import rtg.world.gen.feature.tree.WorldGenTreePalm;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceIslandMountainStone;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDeepOcean;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase
{	
	public static SurfaceBase islandSurface = new SurfaceIslandMountainStone(Blocks.grass, Blocks.dirt, 67, Blocks.sand, 0f);
	public static Block topBlock = BiomeGenBase.deepOcean.topBlock;
	public static Block fillerBlock = BiomeGenBase.deepOcean.fillerBlock;
	
	public RealisticBiomeVanillaDeepOcean()
	{
		super(
			BiomeGenBase.deepOcean,
			BiomeBase.climatizedBiome(BiomeGenBase.ocean, BiomeBase.Climate.TEMPERATE),
			new TerrainVanillaDeepOcean(),
			new SurfaceVanillaDeepOcean(Blocks.sand, Blocks.sand, (byte)0, 0)
		);
		
		this.setRealisticBiomeName("Vanilla Deep Ocean");
		BiomeGenManager.addCoolBiome(this, ConfigRTG.weightVanillaDeepOcean);
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
    	if(rand.nextInt((int)(2f / strength)) == 0)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 < 80)
			{
				WorldGenerator worldgenerator = new WorldGenTreePalm();
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
    	
    	for(int a = 0; a < 3f * strength; a++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 > 68 && z52 < 80 && rand.nextInt(3) != 0)
			{
				WorldGenerator worldgenerator = new WorldGenTreeJungleTall(Blocks.log, 3, Blocks.leaves, 3, 3 + rand.nextInt(3), 0, 9f, 3, 0.32f, 0.1f);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
    	
		for(int a = 0; a < 6f * strength; a++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if((z52 > 68 && z52 < 105) || rand.nextInt(12) == 0 && z52 < 115)
			{
				WorldGenerator worldgenerator = new WorldGenTreeJungleSmall(Blocks.log, 3, Blocks.leaves, 3, 3 + rand.nextInt(2), 0, 5f, 2, 0.32f, 0.14f);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
		
		for (int b1 = 0; b1 < 3f * strength; b1++)
		{
			int j6 = chunkX + rand.nextInt(16) + 8;
			int k10 = chunkY + rand.nextInt(16) + 8;
			int z52 = world.getHeightValue(j6, k10);

			if(z52 > 67 && z52 < 100)
			{
				WorldGenerator worldgenerator = new WorldGenTreeShrub(0, 0, z52);
				worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				worldgenerator.generate(world, rand, j6, z52, k10);
			}
		}
		
		for(int f23 = 0; f23 < 3f * strength; f23++)
		{
			int j15 = chunkX + rand.nextInt(16) + 8;
			int j17 = 64 + rand.nextInt(64);
			int j20 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenFlowers(new int[]{0,1,2,3,4,5,6,7,8,9,10,11})).generate(world, rand, j15, j17, j20);
		}
    	
		for(int l14 = 0; l14 < 6f * strength; l14++)
		{
			int l19 = chunkX + rand.nextInt(16) + 8;
			int k22 = 64 + rand.nextInt(64);
			int j24 = chunkY + rand.nextInt(16) + 8;
			(new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
		}
    }
    
    @Override
    public void rMapGen(Block[] blocks, byte[] metadata, World world, WorldChunkManagerRTG cmr, Random mapRand, int baseX, int baseY, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[])
    {
        if(baseX % 4 == 0 && baseY % 4 == 0 && shouldGenerateVolcano()) //Not sure if the factor-of-4 thing is important, or if it's just used to randomly generate the volcano.
        {
        	float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
        	if(river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16) && cmr.getNoiseWithRiverOceanAt(baseX * 16, baseY * 16, river, cmr.getOceanValue(baseX * 16, baseY * 16)) > 110f)
        	{
	            long i1 = mapRand.nextLong() / 2L * 2L + 1L;
	            long j1 = mapRand.nextLong() / 2L * 2L + 1L;
	            mapRand.setSeed((long)chunkX * i1 + (long)chunkY * j1 ^ world.getSeed());

	            WorldGenVolcano.build(blocks, metadata, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
        	}
        }
    }
    
    @Override
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float ocean, float border, float river)
    {
    	float st = 15f - ((cell.noise(x / 500D, y / 500D, 1D) * 42f) + (simplex.noise2(x / 30f, y / 30f) * 2f));
    	
    	st = st < 0f ? 0f : st;

    	float h = st;
    	h = h < 0f ? 0f : h;
    	h += (h * 0.4f) * ((h * 0.4f) * 2f);
    	
		if(h > 10f)
		{
			float d2 = (h - 10f) / 1.5f > 30f ? 30f : (h - 10f) / 1.5f;
			h += cell.noise(x / 25D, y / 25D, 1D) * d2;
		}
		
		h += simplex.noise2(x / 18f, y / 18f) * 3;
		h += simplex.noise2(x / 8f, y / 8f) * 2;
    	
    	return 55f + h * border;
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    	islandSurface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
    
    public static boolean shouldGenerateTropicalIsland()
    {
    	int tropicalIslandChance = (ConfigRTG.tropicalIslandChance < 1) ? 1 : ((ConfigRTG.tropicalIslandChance > 100) ? 100 : ConfigRTG.tropicalIslandChance);
    	int random = RandomUtil.getRandomInt(1, tropicalIslandChance);
    	return (random == 1) ? true : false;
    }
    
    public static boolean shouldGenerateVolcano()
    {
    	boolean shouldGenerateVolcano = false;
    	
    	if (ConfigRTG.generateVolcanoesInTropicalIslands) {
	    	int volcanoChance = (ConfigRTG.volcanoChance < 1) ? 1 : ((ConfigRTG.volcanoChance > 100) ? 100 : ConfigRTG.volcanoChance);
	    	int random = RandomUtil.getRandomInt(1, volcanoChance);
	    	shouldGenerateVolcano = (random == 1) ? true : false;
    	}
    	
    	return shouldGenerateVolcano;
    }
}
