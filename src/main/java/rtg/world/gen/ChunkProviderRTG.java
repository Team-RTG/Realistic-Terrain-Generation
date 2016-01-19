package rtg.world.gen;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CanyonColor;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.registry.GameData;

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
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkProviderEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
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

    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
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
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private int [] xyinverted = analyzer.xyinverted();
    
    private Block bedrockBlock = GameData.getBlockRegistry().getObject(ConfigRTG.bedrockBlockId);
    private byte bedrockByte = (byte)ConfigRTG.bedrockBlockByte;

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
    private float[] biomesGeneratedInChunk;
    private float[] borderNoise;
    private long worldSeed;

    public ChunkProviderRTG(World world, long l)
    {
        worldObj = world;
        cmr = (WorldChunkManagerRTG) worldObj.getWorldChunkManager();
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

        mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();

        caveGenerator = TerrainGen.getModdedMapGen(new MapGenCavesRTG(), CAVE);
        
        /**
         * RTG doesn't generate ravines, but it still calls getModdedMapGen()
         * so that other mods can hook into InitMapGenEvent if they want to
         * generate their own ravines.
         * 
         * Modders, if you want to generate ravines in RTG, you need to subscribe
         * to InitMapGenEvent with a priority higher than LOW.
         */
        ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavineRTG(), RAVINE);
        
        villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(m), VILLAGE);
		strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(), STRONGHOLD);
		mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(), MINESHAFT);
		scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(), SCATTERED_FEATURE);

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
    	biomesGeneratedInChunk = new float[257];
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
        // that routine can change the biome array so put it back if not

                //fill with biomeData
        int [] biomeIndices= cmr.getBiomesGens(cx *16, cy*16,16,16);

        analyzer.repair(biomeIndices, biomesForGeneration, noise,-cmr.getRiverStrength(cx * 16 + 7, cy * 16 + 7));

        for(k = 0; k < 256; k++)
        {
        	if(biomesGeneratedInChunk[k] > 0f)
        	{
        		RealisticBiomeBase.getBiome(k).generateMapGen(blocks, metadata, worldSeed, worldObj, cmr, mapRand, cx, cy, simplex, cell, noise);
        		biomesGeneratedInChunk[k] = 0f;
        	}
            try {
                baseBiomesList[k] = biomesForGeneration[k].baseBiome;
            } catch (Exception e) {
                throw new RuntimeException(""+biomesForGeneration[k].biomeID);
            }
        }

        replaceBlocksForBiome(cx, cy, blocks, metadata, biomesForGeneration, baseBiomesList, noise);

        caveGenerator.func_151539_a(this, worldObj, cx, cy, blocks);
        ravineGenerator.func_151539_a(this, worldObj, cx, cy, blocks);

        if (mapFeaturesEnabled) {

            if (ConfigRTG.generateMineshafts) {
                mineshaftGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            }
            
            if (ConfigRTG.generateStrongholds) {
                strongholdGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            }
            
            if (ConfigRTG.generateVillages) {
                villageGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            }
            
            if (ConfigRTG.generateScatteredFeatures) {
                scatteredFeatureGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            }
        }

        Chunk chunk = new Chunk(this.worldObj, blocks, metadata, cx, cy);

        byte[] abyte1 = chunk.getBiomeArray();
        for (k = 0; k < abyte1.length; ++k)
        {
            // biomes are y-first and terrain x-first
            abyte1[k] = (byte)this.baseBiomesList[this.xyinverted[k]].biomeID;
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

    private static final int centerLocationIndex = 312;// this is x=8, y=8 with the calcs below

    public float[] getNewNoise(WorldChunkManagerRTG cmr, int x, int y, RealisticBiomeBase biomes[])
    {
    	int i, j, k, locationIndex, m, n, p;

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
        			for(locationIndex = -parabolicSize; locationIndex <= parabolicSize; locationIndex++)
        			{
        				hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)][biomeData[(i + k + sampleSize + 1) * sampleArraySize + (j + locationIndex + sampleSize + 1)]] += parabolicField[k + parabolicSize + (locationIndex + parabolicSize) * parabolicArraySize] / parabolicFieldTotal;
        			}
        		}

        	}
    	}

    	//MAIN BIOME CHECK
    	RealisticBiomeBase realisticBiomeBase = null;
    	for(i = 0; i < 256; i++)
    	{
    		if(hugeRender[4 * 9 + 4][i] > 0.95f)
    		{
    			realisticBiomeBase = RealisticBiomeBase.getBiome(i);
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
        randBiome = false;

        //fill with biomeData
        for (i = 0; i < 16; i++) {
            for (j=0; j<16; j++) {
                biomes[i*16+j] =  cmr.getBiomeDataAt(x + (((i-8) * 8)), y + (((j-8) * 8)));
            }
        }

    	float river;
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

    			locationIndex = ((int)(i + 4) * 25 + (j + 4));

    			testHeight[i * 16 + j] = 0f;

    			river = cmr.getRiverStrength(x + i, y + j);

    			if(locationIndex == centerLocationIndex)
    			{
	    			biomesGeneratedInChunk[256] = river;
    			}

    			for(k = 0; k < 256; k++)
    			{
    				if(smallRender[locationIndex][k] > 0f)
    				{
    	    			if(randBiome && bCount <= 1f) //3f)
    	    			{
	    					bCount += smallRender[locationIndex][k];// * 3f;
    	    				if(bCount > bRand)
    	    				{
    	    					bCount = 2f; //20f;
    	    				}
    	    			}

    	    			if(locationIndex == centerLocationIndex)
    	    			{
    	    				biomesGeneratedInChunk[k] = smallRender[centerLocationIndex][k];
    	    			}

    					testHeight[i * 16 + j] += cmr.calculateRiver(x + i, y + j, river, RealisticBiomeBase.getBiome(k).rNoise(simplex, cell, x + i, y + j, smallRender[locationIndex][k], river + 1f)) * smallRender[locationIndex][k];
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
    			RealisticBiomeBase biome = biomes[j * 16 + i];

    			river = -cmr.getRiverStrength(cx * 16 + j, cy * 16 + i);
    			if(river > 0.05f && river + (simplex.noise2((cx * 16 + j) / 10f, (cy * 16 + i) / 10f) * 0.15f) > 0.8f)
    			{
    				base[j * 16 + i] = biome.riverBiome;
    			}

    			depth = -1;

    			biome.rReplace(blocks, metadata, cx * 16 + j, cy * 16 + i, i, j, depth, worldObj, rand, simplex, cell, n, river, base);

    			int rough;
    			int flatBedrockLayers = (int) ConfigRTG.flatBedrockLayers;
    			flatBedrockLayers = flatBedrockLayers < 0 ? 0 : (flatBedrockLayers > 5 ? 5 : flatBedrockLayers);
    			    			
    			if (flatBedrockLayers > 0) {
    			    for (int bl = 0; bl < flatBedrockLayers; bl++) {
    			        blocks[(j * 16 + i) * 256 + bl] = bedrockBlock;
    			        metadata[(j * 16 + i) * 256 + bl] = bedrockByte;
    			    }
    			}
    			else {
    			    
    			    blocks[(j * 16 + i) * 256] = bedrockBlock;
    			    metadata[(j * 16 + i) * 256] = bedrockByte;
                    
                    rough = rand.nextInt(2);
                    blocks[(j * 16 + i) * 256 + rough] = bedrockBlock;
                    metadata[(j * 16 + i) * 256 + rough] = bedrockByte;
                    
                    rough = rand.nextInt(3);
                    blocks[(j * 16 + i) * 256 + rough] = bedrockBlock;
                    metadata[(j * 16 + i) * 256 + rough] = bedrockByte;
                    
                    rough = rand.nextInt(4);
                    blocks[(j * 16 + i) * 256 + rough] = bedrockBlock;
                    metadata[(j * 16 + i) * 256 + rough] = bedrockByte;
                    
                    rough = rand.nextInt(5);
                    blocks[(j * 16 + i) * 256 + rough] = bedrockBlock;
                    metadata[(j * 16 + i) * 256 + rough] = bedrockByte;
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
    public void populate(IChunkProvider ichunkprovider, int chunkX, int chunkZ)
    {
        BlockFalling.fallInstantly = true;

		int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        RealisticBiomeBase biome = cmr.getBiomeDataAt(worldX + 16, worldZ + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long)chunkX * i1 + (long)chunkZ * j1 ^ this.worldObj.getSeed());
        boolean flag = false;
        boolean gen = false;

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag));

        if (mapFeaturesEnabled) {
            
            if (ConfigRTG.generateMineshafts) {
                mineshaftGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
            }
            
            if (ConfigRTG.generateStrongholds) {
                strongholdGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
            }
            
            if (ConfigRTG.generateVillages) {
                flag = villageGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
            }
            
            if (ConfigRTG.generateScatteredFeatures) {
                scatteredFeatureGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
            }
        }
        
        biome.rPopulatePreDecorate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag);

        /**
         * What is this doing? And why does it need to be done here? - Pink
         */
        for (int bx = -4; bx <= 4; bx++) {
            
        	for(int by = -4; by <= 4; by++)
        	{
        		borderNoise[cmr.getBiomeDataAt(worldX + 24 + bx * 16, worldZ + 24 + by * 16).biomeID] += 0.01234569f;
        	}
        }

        /**
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */
        
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldObj, rand, worldX, worldZ));

        //Initialise variables.
        float river = -cmr.getRiverStrength(worldX + 16, worldZ + 16);
        
        //Clay.
        biome.rDecorateClay(worldObj, rand, chunkX, chunkZ, river, worldX, worldZ);
        
        //Border noise. (Does this have to be done here? - Pink)
        RealisticBiomeBase realisticBiome;
        float snow = 0f;
        
        for(int bn = 0; bn < 256; bn++)
        {
        	if(borderNoise[bn] > 0f)
        	{
        		if(borderNoise[bn] >= 1f)
        		{
        			borderNoise[bn] = 1f;
        		}
        		
        		realisticBiome = RealisticBiomeBase.getBiome(bn);
                if (realisticBiome == null) throw new RuntimeException();
                
                /**
                 * When decorating the biome, we need to look at the biome configs to see if RTG is allowed to decorate it.
                 * If the biome configs don't allow it, then we try to let the base biome decorate itself.
                 * However, there are some mod biomes that crash when they try to decorate themselves,
                 * so that's what the try/catch is for. If it fails, then it falls back to RTG decoration.
                 * TODO: Is there a more efficient way to do this? - Pink
                 */
                if (ConfigRTG.enableRTGBiomeDecorations && realisticBiome.config._boolean(BiomeConfig.useRTGDecorationsId)) {
                    
                    realisticBiome.rDecorate(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
                }
                else {
                    
                    try {
                        
                        realisticBiome.baseBiome.decorate(this.worldObj, rand, worldX, worldZ);
                    }
                    catch (Exception e) {
                        
                        realisticBiome.rDecorate(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
                    }
                }

                if(realisticBiome.baseBiome.temperature < 0.15f)
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
        
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, rand, worldX, worldZ));

        /**
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */
        
        biome.rPopulatePostDecorate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag);
        
        //Flowing water.
        if (rand.nextInt(100) == 0) {
    		for(int l18 = 0; l18 < 50; l18++)
    		{
    			int l21 = worldX + rand.nextInt(16) + 8;
    			int k23 = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);
    			int l24 = worldZ + rand.nextInt(16) + 8;
                
    			(new WorldGenLiquids(Blocks.flowing_water)).generate(worldObj, rand, l21, k23, l24);
    		}
        }

        //Flowing lava.
        if (rand.nextInt(100) == 0) {
    		for(int i19 = 0; i19 < 20; i19++)
    		{
    			int i22 = worldX + rand.nextInt(16) + 8;
    			int l23 = rand.nextInt(worldHeight / 2);
    			int i25 = worldZ + rand.nextInt(16) + 8;
    			(new WorldGenLiquids(Blocks.flowing_lava)).generate(worldObj, rand, i22, l23, i25);
    		}
        }

        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.ANIMALS))
        {
            SpawnerAnimals.performWorldGenSpawning(this.worldObj, worldObj.getBiomeGenForCoords(worldX + 16, worldZ + 16), worldX + 8, worldZ + 8, 16, 16, this.rand);
        }

        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.ICE)) {

            int k1, l1, i2;
            
            for(k1 = 0; k1 < 16; ++k1) {
                
                for(l1 = 0; l1 < 16; ++l1) {
                    
                    i2 = this.worldObj.getPrecipitationHeight(worldX + k1, worldZ + l1);

                    if(this.worldObj.isBlockFreezable(k1 + worldX, i2 - 1, l1 + worldZ)) {
                        this.worldObj.setBlock(k1 + worldX, i2 - 1, l1 + worldZ, Blocks.ice, 0, 2);
                    }

                    if (ConfigRTG.enableSnowLayers && this.worldObj.func_147478_e(k1 + worldX, i2, l1 + worldZ, true)) {
                        this.worldObj.setBlock(k1 + worldX, i2, l1 + worldZ, Blocks.snow_layer, 0, 2);
                    }
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag));

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
        return "ChunkProviderRTG";
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
        if (!ConfigRTG.generateStrongholds) {
            return null;
        }
        
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
            
            if (ConfigRTG.generateMineshafts) {
                mineshaftGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
            }
            
            if (ConfigRTG.generateStrongholds) {
                strongholdGenerator.func_151539_a(this, worldObj, par1, par2, (Block[])null);
            }
            
            if (ConfigRTG.generateVillages) {
                villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
            }
            
            if (ConfigRTG.generateScatteredFeatures) {
                scatteredFeatureGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
            }
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