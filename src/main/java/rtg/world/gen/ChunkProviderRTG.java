package rtg.world.gen;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.COAL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.CUSTOM;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIAMOND;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.DIRT;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GOLD;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.GRAVEL;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.IRON;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.LAPIS;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.REDSTONE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rtg.config.rtg.ConfigRTG;
import rtg.util.CanyonColor;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenClay;
import cpw.mods.fml.common.eventhandler.Event.Result;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

/**
 * Scattered features courtesy of Ezoteric (https://github.com/Ezoteric) and Choonster (https://github.com/Choonster)
 */
public class ChunkProviderRTG implements IChunkProvider
{
    /**
     * Declare variables.
     */

    private final MapGenBase caves;
    private final MapGenStronghold strongholdGenerator;
    private final MapGenMineshaft mineshaftGenerator;
    private final MapGenVillage villageGenerator;
    private final MapGenScatteredFeature scatteredFeatureGenerator;
    private final boolean mapFeaturesEnabled;
    private final int worldHeight;
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private final int parabolicSize;
    private final int parabolicArraySize;
    private final float[] parabolicField;

    private Random rand;
    private Random mapRand;
    private World worldObj;
    private WorldChunkManagerRTG cmr;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
	private RealisticBiomeBase[] biomesForGeneration;
	private BiomeGenBase[] baseBiomesList;
    private int[] biomeData;
    private float parabolicFieldTotal;
    private float[][] hugeRender;
    private float[][] smallRender;
    private float[] testHeight;
    private float[] mapGenBiomes;
    private float[] borderNoise;
    private long worldSeed;
	private WorldGenMinable ore_dirt = new WorldGenMinable(Blocks.dirt, 32);
	private WorldGenMinable ore_gravel = new WorldGenMinable(Blocks.gravel, 32);
	private WorldGenMinable ore_coal = new WorldGenMinable(Blocks.coal_ore, 16);
	private WorldGenMinable ore_iron = new WorldGenMinable(Blocks.iron_ore, 8);
	private WorldGenMinable ore_gold = new WorldGenMinable(Blocks.gold_ore, 8);
	private WorldGenMinable ore_redstone = new WorldGenMinable(Blocks.redstone_ore, 7);
	private WorldGenMinable ore_diamond = new WorldGenMinable(Blocks.diamond_ore, 7);
	private WorldGenMinable ore_lapis = new WorldGenMinable(Blocks.lapis_ore, 6);
	private WorldGenMinable ore_emerald = new WorldGenMinable(Blocks.emerald_ore, 1);

    public ChunkProviderRTG(World world, long l)
    {
    	mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();

    	caves = TerrainGen.getModdedMapGen(new MapGenCaves(), CAVE);
        worldObj = world;
        cmr = (WorldChunkManagerRTG)worldObj.getWorldChunkManager();
        worldHeight = worldObj.provider.getActualHeight();
        rand = new Random(l);
        simplex = new OpenSimplexNoise(l);
    	cell = new CellNoise(l, (short)0);
    	cell.setUseDistance(true);

    	mapRand = new Random(l);
    	worldSeed = l;

        Map m = new HashMap();
        m.put("size", "0");
        m.put("distance", "24");

        villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(m), VILLAGE);
		strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(), STRONGHOLD);
		mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(), MINESHAFT);
		scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new net.minecraft.world.gen.structure.MapGenScatteredFeature(), SCATTERED_FEATURE);

        CanyonColor.init(l);

        sampleArraySize = sampleSize * 2 + 5;

        parabolicSize = sampleSize;
        parabolicArraySize = parabolicSize * 2 + 1;
        parabolicField = new float[parabolicArraySize * parabolicArraySize];
        for (int j = -parabolicSize; j <= parabolicSize; ++j)
        {
            for (int k = -parabolicSize; k <= parabolicSize; ++k)
            {
                float f = 0.445f / MathHelper.sqrt_float((float)((j * 1) * (j * 1) + (k * 1) * (k * 1)) + 0.3F);
                parabolicField[j + parabolicSize + (k + parabolicSize) * parabolicArraySize] = f;
                parabolicFieldTotal += f;
            }
        }

        baseBiomesList = new BiomeGenBase[256];
        biomeData = new int[sampleArraySize * sampleArraySize];
    	hugeRender = new float[81][256];
    	smallRender = new float[625][256];
    	testHeight = new float[256];
    	mapGenBiomes = new float[258];
    	borderNoise = new float[256];
    }

    /**
     * @see IChunkProvider
     *
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    public Chunk provideChunk(int cx, int cy)
    {
    	rand.setSeed((long)cx * 0x4f9939f508L + (long)cy * 0x1ef1565bd5L);
        Block[] blocks = new Block[65536];
        byte[] metadata = new byte[65536];
        float[] noise = new float[256];
        biomesForGeneration = new RealisticBiomeBase[256];
        int k;

        generateTerrain(cmr, cx, cy, blocks, metadata, biomesForGeneration, noise);

        for(k = 0; k < 256; k++)
        {
        	if(mapGenBiomes[k] > 0f)
        	{
        		RealisticBiomeBase.getBiome(k).generateMapGen(blocks, metadata, worldSeed, worldObj, cmr, mapRand, cx, cy, simplex, cell, noise);
        		mapGenBiomes[k] = 0f;
        	}
        	baseBiomesList[k] = biomesForGeneration[k].baseBiome;
        }

        replaceBlocksForBiome(cx, cy, blocks, metadata, biomesForGeneration, baseBiomesList, noise);

        caves.func_151539_a(this, worldObj, cx, cy, blocks);

        if (mapFeaturesEnabled) {
            mineshaftGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            strongholdGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            villageGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            scatteredFeatureGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
        }

        Chunk chunk = new Chunk(this.worldObj, blocks, metadata, cx, cy);
        byte[] abyte1 = chunk.getBiomeArray();
        for (k = 0; k < abyte1.length; ++k)
        {
            abyte1[k] = (byte)this.baseBiomesList[k].biomeID;
        }
        chunk.generateSkylightMap();

        return chunk;
    }

    public void generateTerrain(WorldChunkManagerRTG cmr, int cx, int cy, Block[] blocks, byte[] metadata, RealisticBiomeBase biomes[], float[] n)
    {
    	int p, h;
    	float[] noise = getNewNoise(cmr, cx * 16, cy * 16, biomes);
    	for(int i = 0; i < 16; i++)
    	{
    		for(int j = 0; j < 16; j++)
    		{
    			h = (int)noise[j * 16 + i];

    			for(int k = 0; k < 256; k++)
    			{
    				p = (j * 16 + i) * 256 + k;
    				if(k > h)
    				{
    					if(k < 63)
        				{
        					blocks[p] = Blocks.water;
        				}
        				else
        				{
        					blocks[p] = Blocks.air;
        				}
    				}
    				else
    				{
    					blocks[p] = Blocks.stone;
    				}
    			}
    			n[j * 16 + i] = noise[j * 16 + i];
    		}
    	}
    }

    public float[] getNewNoise(WorldChunkManagerRTG cmr, int x, int y, RealisticBiomeBase biomes[])
    {
    	int i, j, k, l, m, n, p;

    	for(i = -sampleSize; i < sampleSize + 5; i++)
    	{
    		for(j = -sampleSize; j < sampleSize + 5; j++)
    		{
    			biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] = cmr.getBiomeDataAt(x + ((i * 8) - 8), y + ((j * 8) - 8)).biomeID;
    		}
    	}

    	for(i = -1; i < 4; i++)
    	{
        	for(j = -1; j < 4; j++)
        	{
        		hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)] = new float[256];
        		for(k = -parabolicSize; k <= parabolicSize; k++)
        		{
        			for(l = -parabolicSize; l <= parabolicSize; l++)
        			{
        				hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)][biomeData[(i + k + sampleSize + 1) * sampleArraySize + (j + l + sampleSize + 1)]] += parabolicField[k + parabolicSize + (l + parabolicSize) * parabolicArraySize] / parabolicFieldTotal;
        			}
        		}

        	}
    	}

    	//MAIN BIOME CHECK
    	RealisticBiomeBase b = null;
    	for(i = 0; i < 256; i++)
    	{
    		if(hugeRender[4 * 9 + 4][i] > 0.95f)
    		{
    			b = RealisticBiomeBase.getBiome(i);
    		}
    	}

    	//RENDER HUGE 1
    	for(i = 0; i < 4; i++)
    	{
    		for(j = 0; j < 4; j++)
    		{
    			hugeRender[(i * 2 + 1) * 9 + (j * 2 + 1)] = mix4(new float[][]{
					hugeRender[(i * 2) * 9 + (j * 2)],
					hugeRender[(i * 2 + 2) * 9 + (j * 2)],
					hugeRender[(i * 2) * 9 + (j * 2 + 2)],
					hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)]});
    		}
    	}

    	//RENDER HUGE 2
    	for(i = 0; i < 7; i++)
    	{
    		for(j = 0; j < 7; j++)
    		{
    			if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i * 4) * 25 + (j * 4)] = mix4(new float[][]{
						hugeRender[(i) * 9 + (j + 1)],
						hugeRender[(i + 1) * 9 + (j)],
						hugeRender[(i + 1) * 9 + (j + 2)],
						hugeRender[(i + 2) * 9 + (j + 1)]});
    			}
    			else
    			{
    				smallRender[(i * 4) * 25 + (j * 4)] = hugeRender[(i + 1) * 9 + (j + 1)];
    			}
    		}
    	}

    	//RENDER SMALL 1
    	for(i = 0; i < 6; i++)
    	{
    		for(j = 0; j < 6; j++)
    		{
    			smallRender[(i * 4 + 2) * 25 + (j * 4 + 2)] = mix4(new float[][]{
    				smallRender[(i * 4) * 25 + (j * 4)],
    				smallRender[(i * 4 + 4) * 25 + (j * 4)],
    				smallRender[(i * 4) * 25 + (j * 4 + 4)],
    				smallRender[(i * 4 + 4) * 25 + (j * 4 + 4)]});
    		}
    	}

    	//RENDER SMALL 2
    	for(i = 0; i < 11; i++)
    	{
    		for(j = 0; j < 11; j++)
    		{
    			if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)] = mix4(new float[][]{
    					smallRender[(i * 2) * 25 + (j * 2 + 2)],
    					smallRender[(i * 2 + 2) * 25 + (j * 2)],
    					smallRender[(i * 2 + 2) * 25 + (j * 2 + 4)],
    					smallRender[(i * 2 + 4) * 25 + (j * 2 + 2)]});
    			}
    		}
    	}

    	//RENDER SMALL 3
    	for(i = 0; i < 9; i++)
    	{
    		for(j = 0; j < 9; j++)
    		{
    			smallRender[(i * 2 + 3) * 25 + (j * 2 + 3)] = mix4(new float[][]{
        				smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)],
        				smallRender[(i * 2 + 4) * 25 + (j * 2 + 2)],
        				smallRender[(i * 2 + 2) * 25 + (j * 2 + 4)],
        				smallRender[(i * 2 + 4) * 25 + (j * 2 + 4)]});
    		}
    	}

    	//RENDER SMALL 4
    	for(i = 0; i < 16; i++)
    	{
    		for(j = 0; j < 16; j++)
    		{
    			if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i + 4) * 25 + (j + 4)] = mix4(new float[][]{
    					smallRender[(i + 3) * 25 + (j + 4)],
    					smallRender[(i + 4) * 25 + (j + 3)],
    					smallRender[(i + 4) * 25 + (j + 5)],
    					smallRender[(i + 5) * 25 + (j + 4)]});
    			}
    		}
    	}

    	//CREATE BIOMES ARRAY
    	boolean randBiome = true;
    	float bCount = 0f, bRand = 0f;
    	if(b != null)
    	{
    		randBiome = false;
    		for(i = 0; i < 256; i++)
    		{
    			biomes[i] = b;
    		}
    	}

    	float river, ocean;
    	for(i = 0; i < 16; i++)
    	{
    		for(j = 0; j < 16; j++)
    		{
    			if(randBiome)
    			{
    				bCount = 0f;
    				bRand = 0.5f + simplex.noise2((float)(x + i) / 15f, (float)(y + j) / 15f);
    				bRand = bRand < 0f ? 0f : bRand > 0.99999f ? 0.99999f : bRand;
    			}

    			ocean = cmr.getOceanValue(x + i, y + j);
    			l = ((int)(i + 4) * 25 + (j + 4));

    			testHeight[i * 16 + j] = 0f;

    			river = cmr.getRiverStrength(x + i, y + j);

    			if(l == 312)
    			{
	    			mapGenBiomes[256] = ocean;
	    			mapGenBiomes[257] = river;
    			}

    			for(k = 0; k < 256; k++)
    			{
    				if(smallRender[l][k] > 0f)
    				{
    	    			if(randBiome && bCount <= 1f) //3f)
    	    			{
	    					bCount += smallRender[l][k];// * 3f;
    	    				if(bCount > bRand)
    	    				{
    	    					biomes[j * 16 + i] = RealisticBiomeBase.getBiome(k);
    	    					bCount = 2f; //20f;
    	    				}
    	    			}

    	    			if(l == 312)
    	    			{
    	    				mapGenBiomes[k] = smallRender[312][k];
    	    			}

    					testHeight[i * 16 + j] += cmr.calculateRiver(x + i, y + j, river, RealisticBiomeBase.getBiome(k).rNoise(simplex, cell, x + i, y + j, ocean, smallRender[l][k], river + 1f)) * smallRender[l][k];
    				}
    			}
    		}
    	}

    	return testHeight;
    }

    public float[] mix4(float[][] ingredients)
    {
    	float[] result = new float[256];
    	int i, j;
    	for(i = 0; i < 256; i++)
    	{
    		for(j = 0; j < 4; j++)
    		{
    			if(ingredients[j][i] > 0f)
    			{
    				result[i] += ingredients[j][i] / 4f;
    			}
    		}
    	}

    	return result;
    }

    public void replaceBlocksForBiome(int cx, int cy, Block[] blocks, byte[] metadata, RealisticBiomeBase[] biomes, BiomeGenBase[] base, float[] n)
    {
        ChunkProviderEvent.ReplaceBiomeBlocks event = new ChunkProviderEvent.ReplaceBiomeBlocks(this, cx, cy, blocks, metadata, base, worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;

    	int i, j, depth;
    	float river;
    	for(i = 0; i < 16; i++)
    	{
    		for(j = 0; j < 16; j++)
    		{
    			RealisticBiomeBase biome = biomes[i * 16 + j];

    			river = -cmr.getRiverStrength(cx * 16 + j, cy * 16 + i);
    			if(river > 0.05f && river + (simplex.noise2((cx * 16 + j) / 10f, (cy * 16 + i) / 10f) * 0.15f) > 0.8f)
    			{
    				base[i * 16 + j] = biome.riverBiome;
    			}

    			depth = -1;

    			biome.rReplace(blocks, metadata, cx * 16 + j, cy * 16 + i, i, j, depth, worldObj, rand, simplex, cell, n, river, base);

    			int flatBedrockLayers = (int) ConfigRTG.flatBedrockLayers;
    			flatBedrockLayers = flatBedrockLayers < 0 ? 0 : (flatBedrockLayers > 5 ? 5 : flatBedrockLayers);
    			
    			if (flatBedrockLayers > 0) {
    			    for (int bl = 0; bl < flatBedrockLayers; bl++) {
    			        blocks[(j * 16 + i) * 256 + bl] = Blocks.bedrock;
    			    }
    			}
    			else {
                    blocks[(j * 16 + i) * 256] = Blocks.bedrock;
                    blocks[(j * 16 + i) * 256 + rand.nextInt(2)] = Blocks.bedrock;
                    blocks[(j * 16 + i) * 256 + rand.nextInt(3)] = Blocks.bedrock;
                    blocks[(j * 16 + i) * 256 + rand.nextInt(4)] = Blocks.bedrock;
                    blocks[(j * 16 + i) * 256 + rand.nextInt(5)] = Blocks.bedrock;
    			}

    		}
    	}
    }

    /**
     * @see IChunkProvider
     *
     * Loads or generates the chunk at the chunk location specified.
     */
    public Chunk loadChunk(int par1, int par2)
    {
        return provideChunk(par1, par2);
    }

    private double[] func_4061_a(double ad[], int i, int j, int k, int l, int i1, int j1)
    {
    	return null;
    }

    /**
     * @see IChunkProvider
     *
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        /**
         * TODO: Write custom logic to determine whether chunk exists, instead of assuming it does.
         */
        return true;
    }

    /**
     * @see IChunkProvider
     *
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        BlockFalling.fallInstantly = true;

		int x = i * 16;
        int y = j * 16;
        RealisticBiomeBase biome = cmr.getBiomeDataAt(x + 16, y + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)i * i1 + (long)j * j1 ^ this.worldObj.getSeed());
        boolean flag = false;
        boolean gen = false;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, i, j, flag));

        if (mapFeaturesEnabled) {
            mineshaftGenerator.generateStructuresInChunk(worldObj, rand, i, j);
            strongholdGenerator.generateStructuresInChunk(worldObj, rand, i, j);
            flag = villageGenerator.generateStructuresInChunk(worldObj, rand, i, j);
            scatteredFeatureGenerator.generateStructuresInChunk(worldObj, rand, i, j);
        }

        if (ConfigRTG.enableWaterLakes) {
            gen = TerrainGen.populate(this, worldObj, rand, i, j, flag, PopulateChunkEvent.Populate.EventType.LAKE);
            if(gen && (RandomUtil.getRandomInt(1, ConfigRTG.waterLakeChance) == 1))
    		{
    			int i2 = x + rand.nextInt(16) + 8;
    			int l4 = rand.nextInt(50);
    			int i8 = y + rand.nextInt(16) + 8;
    			(new WorldGenLakes(Blocks.water)).generate(worldObj, rand, i2, l4, i8);
    		}
        }

        if (ConfigRTG.enableLavaLakes) {
            gen = TerrainGen.populate(this, worldObj, rand, i, j, flag, PopulateChunkEvent.Populate.EventType.LAVA);
    		if(gen && (RandomUtil.getRandomInt(1, ConfigRTG.lavaLakeChance) == 1))
    		{
    			int j2 = x + rand.nextInt(16) + 8;
    			int i5 = rand.nextInt(rand.nextInt(45) + 8);
    			int j8 = y + rand.nextInt(16) + 8;
    			if(i5 < 64 || rand.nextInt(10) == 0)
    			{
    				(new WorldGenLakes(Blocks.lava)).generate(worldObj, rand, j2, i5, j8);
    			}
    		}
        }

		gen = TerrainGen.populate(this, worldObj, rand, i, j, flag, PopulateChunkEvent.Populate.EventType.DUNGEON);
		for(int k1 = 0; k1 < 8 && gen; k1++)
		{
			int j5 = x + rand.nextInt(16) + 8;
			int k8 = rand.nextInt(128);
			int j11 = y + rand.nextInt(16) + 8;
			(new WorldGenDungeons()).generate(worldObj, rand, j5, k8, j11);
		}

		/**
		 * ########################################################################
		 * # START GENERATE ORES
		 * ########################################################################
		 */

		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(worldObj, rand, x, y));

		/**
		 * Clay
		 */
        float river = -cmr.getRiverStrength(x + 16, y + 16);
        if(river > 0.85f)
        {
			for(int j2 = 0; j2 < 3; j2++)
			{
				int l5 = x + rand.nextInt(16);
				int i9 = 53 + rand.nextInt(15);
				int l11 = y + rand.nextInt(16);
				(new WorldGenClay(Blocks.clay, 0, 20)).generate(worldObj, rand, l5, i9, l11);
			}
        }

        /**
         * Dirt
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (TerrainGen.generateOre(worldObj, rand, ore_dirt, x, y, DIRT))
		{
			for(int j2 = 0; j2 < 10; j2++)
			{
				int l5 = x + rand.nextInt(16);
				int i9 = rand.nextInt(64);
				int l11 = y + rand.nextInt(16);
				ore_dirt.generate(worldObj, rand, l5, i9, l11);
			}
		}

		/**
		 * Gravel
		 * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
		 */
		if (TerrainGen.generateOre(worldObj, rand, ore_gravel, x, y, GRAVEL))
		{
			for(int k2 = 0; k2 < 5; k2++)
			{
				int i6 = x + rand.nextInt(16);
				int j9 = rand.nextInt(64);
				int i12 = y + rand.nextInt(16);
				ore_gravel.generate(worldObj, rand, i6, j9, i12);
			}
		}

        /**
         * Coal
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (ConfigRTG.generateOreCoal && TerrainGen.generateOre(worldObj, rand, ore_coal, x, y, COAL))
		{
			for(int i3 = 0; i3 < 20; i3++)
			{
				int j6 = x + rand.nextInt(16);
				int k9 = rand.nextInt(128);
				int j12 = y + rand.nextInt(16);
				ore_coal.generate(worldObj, rand, j6, k9, j12);
			}
		}

        /**
         * Iron
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (ConfigRTG.generateOreIron && TerrainGen.generateOre(worldObj, rand, ore_iron, x, y, IRON))
		{
			for(int j3 = 0; j3 < 20; j3++)
			{
				int k6 = x + rand.nextInt(16);
				int l9 = rand.nextInt(64);
				int k12 = y + rand.nextInt(16);
				ore_iron.generate(worldObj, rand, k6, l9, k12);
			}
		}

        /**
         * Gold
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (ConfigRTG.generateOreGold && TerrainGen.generateOre(worldObj, rand, ore_gold, x, y, GOLD))
		{
			for(int k3 = 0; k3 < 2; k3++)
			{
				int l6 = x + rand.nextInt(16);
				int i10 = rand.nextInt(32);
				int l12 = y + rand.nextInt(16);
				ore_gold.generate(worldObj, rand, l6, i10, l12);
			}
		}

        /**
         * Redstone
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (ConfigRTG.generateOreRedstone && TerrainGen.generateOre(worldObj, rand, ore_redstone, x, y, REDSTONE))
		{
			for(int l3 = 0; l3 < 8; l3++)
			{
				int i7 = x + rand.nextInt(16);
				int j10 = rand.nextInt(16);
				int i13 = y + rand.nextInt(16);
				ore_redstone.generate(worldObj, rand, i7, j10, i13);
			}
		}

        /**
         * Diamond
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (ConfigRTG.generateOreDiamond && TerrainGen.generateOre(worldObj, rand, ore_diamond, x, y, DIAMOND))
		{
			for(int i4 = 0; i4 < 1; i4++)
			{
				int j7 = x + rand.nextInt(16);
				int k10 = rand.nextInt(16);
				int j13 = y + rand.nextInt(16);
				ore_diamond.generate(worldObj, rand, j7, k10, j13);
			}
		}

        /**
         * Lapis
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (ConfigRTG.generateOreLapis && TerrainGen.generateOre(worldObj, rand, ore_lapis, x, y, LAPIS))
		{
			for(int j4 = 0; j4 < 1; j4++)
			{
				int k7 = x + rand.nextInt(16);
				int l10 = rand.nextInt(16) + rand.nextInt(16);
				int k13 = y + rand.nextInt(16);
				ore_lapis.generate(worldObj, rand, k7, l10, k13);
			}
		}

        /**
         * Emerald
         * TerrainGen.generateOre() automatically posts an event to ORE_GEN_BUS, so we don't need to post it here.
         */
		if (ConfigRTG.generateOreEmerald && TerrainGen.generateOre(worldObj, rand, ore_lapis, x, y, CUSTOM)) {

			for (int g12 = 0; g12 < 1; ++g12) {
				int n1 = x + rand.nextInt(16);
				int m1 = rand.nextInt(28) + 4;
				int p1 = y + rand.nextInt(16);

				if (rand.nextInt(4) == 0) {
				    ore_emerald.generate(worldObj, rand, n1, m1, p1);
				}
			}
		}

		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(worldObj, rand, x, y));

        /**
         * ########################################################################
         * # END GENERATE ORES
         * ########################################################################
         */

		if(rand.nextInt(5) == 0)
		{
			int k15 = x + rand.nextInt(16) + 8;
			int k17 = rand.nextInt(64);
			int k20 = y + rand.nextInt(16) + 8;

			if(rand.nextBoolean())
			{
				(new WorldGenFlowers(Blocks.brown_mushroom)).generate(worldObj, rand, k15, k17, k20);
			}
			else
			{
				(new WorldGenFlowers(Blocks.red_mushroom)).generate(worldObj, rand, k15, k17, k20);
			}
		}

        for(int bx = -4; bx <= 4; bx++)
        {
        	for(int by = -4; by <= 4; by++)
        	{
        		borderNoise[cmr.getBiomeDataAt(x + 24 + bx * 16, y + 24 + by * 16).biomeID] += 0.01234569f;
        	}
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldObj, rand, x, y));

        //Sand.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.SAND)) {
          //TODO
        }
        
        //Clay.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.CLAY)) {
          //TODO
        }
        
        //Sand (Pass 2).
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.SAND_PASS2)) {
          //TODO
        }
        
        //Trees.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.TREE)) {
          //TODO
        }
        
        //Big shrooms.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.BIG_SHROOM)) {
          //TODO
        }

        //Border noise. (Does this have to be done here? - Pink
        RealisticBiomeBase b;
        float snow = 0f;
        for(int bn = 0; bn < 256; bn++)
        {
        	if(borderNoise[bn] > 0f)
        	{
        		if(borderNoise[bn] >= 1f)
        		{
        			borderNoise[bn] = 1f;
        		}
        		b = RealisticBiomeBase.getBiome(bn);
                b.rDecorate(this.worldObj, this.rand, x, y, simplex, cell, borderNoise[bn], river);

                if(b.baseBiome.temperature < 0.15f)
                {
                	snow -= 0.6f * borderNoise[bn];
                }
                else
                {
                	snow += 0.6f * borderNoise[bn];
                }
                borderNoise[bn] = 0f;
        	}
        }

        //Flowers.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.FLOWERS)) {
          //TODO
        }
        
        //Grass.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.GRASS)) {
          //TODO
        }
        
        //Dead bushes.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH)) {
          //TODO
        }
        
        //Lilypads.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.LILYPAD)) {
          //TODO
        }
        
        //Shrooms.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.SHROOM)) {
          //TODO
        }
        
        //Sugarcane.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.REED)) {
          //TODO
        }
        
        //Pumpkins.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.PUMPKIN)) {
          //TODO
        }
        
        //Cactii
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.CACTUS)) {
          //TODO
        }
        
        //Lakes.
        if (TerrainGen.decorate(worldObj, rand, x, y, DecorateBiomeEvent.Decorate.EventType.LAKE)) {
          //TODO
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, rand, x, y));

        //Flowing water.
        if (rand.nextInt(2) == 0) {
    		for(int l18 = 0; l18 < 50; l18++)
    		{
    			int l21 = x + rand.nextInt(16) + 8;
    			int k23 = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);
    			int l24 = y + rand.nextInt(16) + 8;
                
    			(new WorldGenLiquids(Blocks.flowing_water)).generate(worldObj, rand, l21, k23, l24);
    		}
        }

        //Flowing lava.
        if (rand.nextInt(2) == 0) {
    		for(int i19 = 0; i19 < 20; i19++)
    		{
    			int i22 = x + rand.nextInt(16) + 8;
    			int l23 = rand.nextInt(worldHeight / 2);
    			int i25 = y + rand.nextInt(16) + 8;
    			(new WorldGenLiquids(Blocks.flowing_lava)).generate(worldObj, rand, i22, l23, i25);
    		}
        }

        if (TerrainGen.populate(this, worldObj, rand, i, j, flag, PopulateChunkEvent.Populate.EventType.ANIMALS))
        {
            SpawnerAnimals.performWorldGenSpawning(this.worldObj, worldObj.getBiomeGenForCoords(x + 16, y + 16), x + 8, y + 8, 16, 16, this.rand);
        }

        if (TerrainGen.populate(this, worldObj, rand, i, j, flag, PopulateChunkEvent.Populate.EventType.ICE)) {
            
            if(snow < 0.59f)
            {
    	        x += 8;
    	        y += 8;
    			float s;
    			Block b1, b2;
    
    	        for (int sn1 = 0; sn1 < 16; ++sn1)
    	        {
    	            for (int sn2 = 0; sn2 < 16; ++sn2)
    	            {
    	            	if(snow < -0.59f)
    	            	{
    	            		s = -1f;
    	            	}
    	            	else
    	            	{
    	            		s = simplex.noise2((sn1 + x) / 3f, (sn2 + y) / 3f) + snow;
    	            	}
    
    	            	if(s < 0f)
    	            	{
    		                int sn3 = worldObj.getPrecipitationHeight(x + sn1, y + sn2);
    		                b1 = worldObj.getBlock(sn1 + x, sn3, sn2 + y);
    		                b2 = worldObj.getBlock(sn1 + x, sn3 - 1, sn2 + y);
    
    		                if (b2 == Blocks.water || b2 == Blocks.flowing_water)
    		                {
    		                	worldObj.setBlock(sn1 + x, sn3 - 1, sn2 + y, Blocks.ice, 0, 2);
    		                }
    
    		                if (Blocks.snow_layer.canPlaceBlockAt(worldObj, sn1 + x, sn3, sn2 + y) && b2 != Blocks.ice && b2 != Blocks.water && sn3 > 62)
    		                {
    		                	if(b1 != Blocks.snow_layer && b2 != Blocks.packed_ice)
    		                	{
    
    		                		/**
    		                		 * Not sure if this is the right 'temperature' we need to check, and not sure
    		                		 * if the value is low/high enough, so some testing is still needed here.
    		                		 */
    		                        if (biome.baseBiome.temperature < 0.15f)
                                    {
    			                		/** This line spawns those annoying snow layers */
    			                		worldObj.setBlock(sn1 + x, sn3, sn2 + y, Blocks.snow_layer, 0, 2);
                                    }
    		                	}
    		                }
    	            	}
    	            }
    	        }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, i, j, flag));

        BlockFalling.fallInstantly = false;
    }

    /**
     * @see IChunkProvider
     *
     * Two modes of operation: if passed true, save all Chunks in one go.  If passed false, save up to two chunks.
     * Return true if all chunks have been saved.
     */
    public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
    {
        return true;
    }

    /**
     * @see IChunkProvider
     *
     * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
     */
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    /**
     * @see IChunkProvider
     *
     * Returns if the IChunkProvider supports saving.
     */
    public boolean canSave()
    {
        return true;
    }

    /**
     * IChunkProvider
     *
     * Converts the instance data to a readable string.
     */
    public String makeString()
    {
        return "RandomLevelSourceRTG";
    }

    /**
     * @see IChunkProvider
     *
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
    {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    /**
     * @see IChunkProvider
     */
    public ChunkPosition func_147416_a(World par1World, String par2Str, int par3, int par4, int par5)
    {
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.func_151545_a(par1World, par3, par4, par5) : null;
    }

    /**
     * @see IChunkProvider
     */
    public int getLoadedChunkCount()
    {
        return 0;
    }

    /**
     * @see IChunkProvider
     */
    public void recreateStructures(int par1, int par2)
    {
    	if (mapFeaturesEnabled) {
    		strongholdGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
    		mineshaftGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
            villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
    		scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
    	}
	}

    /**
     * @see IChunkProvider
     *
     * Save extra data not associated with any Chunk.  Not saved during autosave, only during world unload.
     * Currently unimplemented.
     */
    public void saveExtraData() {}
}
