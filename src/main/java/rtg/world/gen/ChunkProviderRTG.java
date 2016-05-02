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
import net.minecraft.world.gen.MapGenRavine;
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
import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.AICWrapper;
import rtg.util.CanyonColour;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.PlaneLocation;
import rtg.util.SimplexCellularNoise;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.registry.GameData;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import rtg.util.Accessor;
import rtg.util.Compass;
import rtg.util.Direction;
import rtg.util.LimitedSet;
import rtg.util.TimeTracker;


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
    protected RTGBiomeProvider cmr;
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
    private float[] riverVals = new float[256];
    private long worldSeed;
    private RealisticBiomePatcher biomePatcher;
    private HashMap<PlaneLocation,Chunk> inGeneration = new HashMap<PlaneLocation,Chunk>();
    private static String rtgTerrain = "RTG Terrain";
    
    private AICWrapper aic;
    private boolean isAICExtendingBiomeIdsLimit;
    private AnvilChunkLoader chunkLoader;
    private Set<Long> serverLoadingChunks;

    Accessor<ChunkProviderServer,Set<Long>> forServerLoadingChunks =
            new Accessor<ChunkProviderServer,Set<Long>>("loadingChunks");

    private Compass compass = new Compass();
    ArrayList<Direction> directions = compass.directions();

    public ChunkProviderRTG(World world, long l)
    {
        worldObj = world;
        cmr = (WorldChunkManagerRTG) worldObj.getWorldChunkManager();
        worldHeight = worldObj.provider.getActualHeight();
        rand = new Random(l);
        simplex = new OpenSimplexNoise(l);
        cell = new SimplexCellularNoise(l);

    	mapRand = new Random(l);
    	worldSeed = l;

        Map m = new HashMap();
        m.put("size", "0");
        m.put("distance", "24");

        mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();

        if (ConfigRTG.enableCaveModifications) {
            caveGenerator = TerrainGen.getModdedMapGen(new MapGenCavesRTG(), CAVE);
        }
        else {
            caveGenerator = TerrainGen.getModdedMapGen(new MapGenCaves(), CAVE);
        }
        
        if (ConfigRTG.enableRavineModifications) {
            ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavineRTG(), RAVINE);
        }
        else {
            ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavine(), RAVINE);
        }

        villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(m), VILLAGE);
		strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(), STRONGHOLD);
		mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(), MINESHAFT);
		scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(), SCATTERED_FEATURE);

		CanyonColour.init(l);

        sampleArraySize = sampleSize * 2 + 5;

        parabolicSize = sampleSize;
        parabolicArraySize = parabolicSize * 2 + 1;
        parabolicField = new float[parabolicArraySize * parabolicArraySize];
        for (int j = -parabolicSize; j <= parabolicSize; ++j)
        {
            for (int k = -parabolicSize; k <= parabolicSize; ++k)
            {
                float f = 0.445f / MathHelper.sqrt_float((float)((j * 1) * (j * 1) + (k * 1) * (k * 1)) + 0.3F);
                //float f = 0.445f / (float)Math.pow(((j * 1) * (j * 1) + (k * 1) * (k * 1)) + 0.3F,1.5);
                parabolicField[j + parabolicSize + (k + parabolicSize) * parabolicArraySize] = f;
                parabolicFieldTotal += f;
            }
        }

        baseBiomesList = new BiomeGenBase[256];
        biomeData = new int[sampleArraySize * sampleArraySize];
    	hugeRender = new float[81][256];
    	smallRender = new float[625][256];
    	testHeight = new float[256];
    	biomesGeneratedInChunk = new float[256];
    	borderNoise = new float[256];
    	biomePatcher = new RealisticBiomePatcher();
    	
    	aic = new AICWrapper();
    	isAICExtendingBiomeIdsLimit = aic.isAICExtendingBiomeIdsLimit();
    }

    /**
     * @see IChunkProvider
     *
     * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the
     * specified chunk from the map seed and chunk seed
     */
    private LimitedSet<PlaneLocation> chunkMade  = new LimitedSet<PlaneLocation>(10000);
    private WeakHashMap<PlaneLocation,Chunk> availableChunks = new WeakHashMap<PlaneLocation,Chunk> ();
    public Chunk provideChunk(int cx, int cy)
    {

        PlaneLocation chunkLocation = new PlaneLocation.Invariant(cx,cy);
        if (inGeneration.containsKey(chunkLocation)) {
            return inGeneration.get(chunkLocation);
        }
        if (chunkMade.contains(chunkLocation)) {
            Chunk available = availableChunks.get(chunkLocation);
            if (available != null) {
                // this should never be happening but it came up when Forge/MC re-requested an already
                // made chunk for a lighting check (???)

                // we are having a problem with Forge complaining about double entity registration
                // so we'll unload any loaded entities
                List [] entityLists = available.entityLists;
                for (int i = 0; i< entityLists.length; i++) {
                    Iterator iterator = entityLists[i].iterator();
                    while (iterator.hasNext()) {
                        iterator.remove();
                    }
                    worldObj.unloadEntities(entityLists[i]);
                }
                return available;
            }
            throw new RuntimeException();
        }

        TimeTracker.manager.start(rtgTerrain);
        TimeTracker.manager.start("RTG chunk");
    	rand.setSeed((long)cx * 0x4f9939f508L + (long)cy * 0x1ef1565bd5L);
        Block[] blocks = new Block[65536];
        byte[] metadata = new byte[65536];
        float[] noise = new float[256];
        biomesForGeneration = new RealisticBiomeBase[256];
        int k;

        generateTerrain(cmr, cx, cy, blocks, metadata, biomesForGeneration, noise);
        // that routine can change the blocks.
        //get standard biome Data
        int [] biomeIndices= cmr.getBiomesGens(cx *16, cy*16,16,16);

            analyzer.newRepair(biomeIndices, biomesForGeneration, this.biomeData, this.sampleSize, noise,riverVals);//-cmr.getRiverStrength(cx * 16 + 7, cy * 16 + 7));


        for(k = 0; k < 256; k++)
        {
            // Is this a single biome world?
            if (biomePatcher.isSingleBiomeWorld())
            {
                baseBiomesList[k] = biomePatcher.getSingleBaseBiome();
            }
            else
            {
                if(biomesGeneratedInChunk[k] > 0f)
                {
                    RealisticBiomeBase.getBiome(k).generateMapGen(blocks, metadata, worldSeed, worldObj, cmr, mapRand, cx, cy, simplex, cell, noise);
                    biomesGeneratedInChunk[k] = 0f;
                }
                try {
                    baseBiomesList[k] = biomesForGeneration[k].baseBiome;
                } catch (Exception e) {
                    baseBiomesList[k] = biomePatcher.getPatchedBaseBiome(""+biomesForGeneration[k].biomeID);
                }
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
                
                if (ConfigRTG.villageCrashFix) {
                    
                    try {
                        villageGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
                    }
                    catch (Exception e) {
                        // Do nothing.
                    }
                }
                else {
                    villageGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
                }
            }
            
            if (ConfigRTG.generateScatteredFeatures) {
                scatteredFeatureGenerator.func_151539_a(this, this.worldObj, cx, cy, blocks);
            }
        }
            // store in the in process pile
        Chunk chunk = new Chunk(this.worldObj, blocks, metadata, cx, cy);
        inGeneration.put(chunkLocation, chunk);
        
        if(isAICExtendingBiomeIdsLimit){
        	aic.setBiomeArray(chunk, baseBiomesList, xyinverted);
        } else {
        	// doJitter no longer needed as the biome array gets fixed
        	byte[] abyte1 = chunk.getBiomeArray();
        	for (k = 0; k < abyte1.length; ++k)
        	{
        		// biomes are y-first and terrain x-first
        		/*
        		* This 2 line separation is needed, because otherwise, AIC's dynamic patching algorith detects vanilla pattern here and patches this part following vanilla logic.
        		* Which causes game to crash.
        		* I cannot do much on my part, so i have to do it here.
        		* - Elix_x
        		*/
        		byte b = (byte)this.baseBiomesList[this.xyinverted[k]].biomeID;
        		abyte1[k] = b;
        	}
        	chunk.setBiomeArray(abyte1);
        }
        chunk.generateSkylightMap();
        // check for other chunks now needing population
        long chunkHandle = ChunkCoordIntPair.chunkXZ2Int(cx,cy);
        // test to make sure we've got the right loadingchunks array and nothing odd is going on
        if (!this.serverLoadingChunks().contains(chunkHandle)) throw new RuntimeException();
        serverLoadingChunks().remove(chunkHandle);
        for (Direction forPopulation: directions) {
            this.decorateIfOtherwiseSurrounded(worldObj.getChunkProvider(), cx, cy, forPopulation);
        }
        clearToDecorateList(worldObj.getChunkProvider());

        List [] entityLists = chunk.entityLists;
        for (int i = 0; i< entityLists.length; i++) {
            Iterator iterator = entityLists[i].iterator();
            Set toRemoveSet = new HashSet();
            while (iterator.hasNext()) {
                toRemoveSet.add(iterator.next());
            }
            for (Object toRemove: toRemoveSet) {
                entityLists[i].remove(toRemove);
            }
            worldObj.unloadEntities(entityLists[i]);
        }
        // just on general principles restore earlier state
        serverLoadingChunks().add(chunkHandle);

        // remove from in process pile
        inGeneration.remove(chunkLocation);
        this.chunkMade.add(chunkLocation);
        probe.setX(cx);
        probe.setZ(cy);
        if (!chunkMade.contains(probe)) throw new RuntimeException();
        availableChunks.put(chunkLocation, chunk);
        TimeTracker.manager.stop(rtgTerrain);
        return chunk;
    }

    public void decorateIfOtherwiseSurrounded(IChunkProvider world, int cx, int cy, Direction fromNewChunk) {

        // see if otherwise surrounded besides the new chunk
        cx += fromNewChunk.xOffset;
        cy += fromNewChunk.zOffset;
        
        // check to see if already decorated; shouldn't be but just in case
        probe.setX(cx);
        probe.setZ(cy);
        if (this.alreadyDecorated.contains(probe)) return;
        // if an in-process chunk; we'll get a populate call later;
        if (this.inGeneration.containsKey(probe)) return;

        for (Direction checked: directions) {
            if (checked == compass.opposite(fromNewChunk)) continue; // that's the new chunk
            if (!chunkExists(world,cx+checked.xOffset, cy+checked.zOffset)) return;// that one's missing
        }
        // passed all checks
        this.doPopulate(world, cx, cy);
    }

    public void generateTerrain(RTGBiomeProvider cmr, int cx, int cy, Block[] blocks, byte[] metadata, RealisticBiomeBase biomes[], float[] n)
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

    private boolean totalNotOne(float [] tested) {
        float total = 0;
        for (int i = 0; i < tested.length; i++) {
            total += tested[i];
        }
        if (total<.999||total>1.001f) return true;
        return false;
    }

    public float[] getNewNoise(RTGBiomeProvider cmr, int x, int y, RealisticBiomeBase biomes[])
    {
    	int i, j, k, locationIndex, m, n, p;

    	for(i = -sampleSize; i < sampleSize + 5; i++)
    	{
    		for(j = -sampleSize; j < sampleSize + 5; j++)
    		{
    			biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] = cmr.getBiomeDataAt(x + ((i * 8)), y + ((j * 8))).biomeID;
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
        				hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)]
                                [biomeData[(i + k + sampleSize + 1) * sampleArraySize + (j + locationIndex + sampleSize + 1)]]
                                += parabolicField[k + parabolicSize + (locationIndex + parabolicSize) * parabolicArraySize] / parabolicFieldTotal;
        			}
        		}

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

        /* Trying to fix the dots problem
         * The existing code could create spots that aren't the
         * logical blend of their parents
         */
        // RENDER HUGE 2 (Zeno410)
    	for(i = -1; i < 4; i++)
    	{
    		for(j = 0; j < 4; j++)
    		{
    			hugeRender[(i * 2 + 2) * 9 + (j * 2 + 1)] = mix2(
					hugeRender[(i * 2 + 2) * 9 + (j * 2)],
					hugeRender[(i * 2 + 2) * 9 + (j * 2 +2)]);
    		}
    	}

        // RENDER HUGE 3 (Zeno410)
    	for(i = 0; i < 4; i++)
    	{
    		for(j = -1; j < 4; j++)
    		{
    			hugeRender[(i * 2 + 1) * 9 + (j * 2 + 2)] = mix2(
					hugeRender[(i * 2 ) * 9 + (j * 2 + 2)],
					hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)]);
    		}
    	}

    	//RENDER SMALL 0
    	for(i = 0; i < 7; i++)
    	{
    		for(j = 0; j < 7; j++)
    		{
    			if (false) //if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
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
            //if (y==64&&)
    	}

    	//RENDER SMALL 2 - points with four diagonal neighbors
        /*
    	for(i = 0; i < 11; i++)
    	{
    		for(j = 0; j < 11; j++)
    		{
    			if(!(i % 2 == 0 && j % 2 == 0))// && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)] = mix4(new float[][]{
    					smallRender[(i * 2) * 25 + (j * 2)],
    					smallRender[(i * 2 + 4) * 25 + (j * 2)],
    					smallRender[(i * 2 ) * 25 + (j * 2 + 4)],
    					smallRender[(i * 2 + 4) * 25 + (j * 2 + 4)]});
    			}
    		}
    	}*/
        
        //RENDER SMALL 2.1 - points vertically between 2 known spots
    	for(i = 0; i < 13; i +=2 ) //0,2,...20
    	{
    		for(j = 0; j < 11; j += 2)// 0,2,..18
    		{
                smallRender[(i * 2) * 25 + (j * 2 + 2)] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2 )],
                    smallRender[(i * 2 ) * 25 + (j * 2 + 4)]);

    		}
    	}

        //RENDER SMALL 2.2 - points horizontally between 2 known spots
    	for(i = 0; i < 11; i += 2)
    	{
    		for(j = 0; j < 13; j += 2)
    		{
                smallRender[(i * 2 + 2) * 25 + (j * 2 )] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2 )],
                    smallRender[(i * 2 + 4) * 25 + (j * 2)]);
    		}
    	}
        /*
    	//RENDER SMALL 3 -  points with four diagonal neighbors
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
         * */
    	//RENDER SMALL 4
    	for(i = 0; i < 12; i++)
    	{
    		for(j = 0; j < 12; j++)
    		{
    			//if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
    			{
    				smallRender[(i * 2 + 1) * 25 + (j * 2 + 1)] = mix4(new float[][]{
    					smallRender[(i * 2) * 25 + (j * 2)],
    					smallRender[(i * 2 + 2) * 25 + (j * 2)],
    					smallRender[(i * 2) * 25 + (j * 2 + 2)],
    					smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)]});
    			}
    		}
    	}
        //RENDER SMALL 3.1 - points vertically between 2 known spots
    	for(i = 0; i < 13; i ++ ) //0,2,...20
    	{
    		for(j = 0; j < 12; j ++ )// 0,2,..18
    		{
                smallRender[(i * 2) * 25 + (j * 2 + 1)] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2 )],
                    smallRender[(i * 2 ) * 25 + (j * 2 + 2)]);

    		}
    	}

        //RENDER SMALL 3.2 - points horizontally between 2 known spots
    	for(i = 0; i < 12; i ++)
    	{
    		for(j = 0; j < 13; j ++)
    		{
                smallRender[(i * 2 + 1) * 25 + (j * 2 )] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2 )],
                    smallRender[(i * 2 + 2) * 25 + (j * 2)]);
    		}
    	}
        
        for (i = 0; i < 25; i ++) {
            for (j = 0; j < 25; j++) {
                if (this.totalNotOne(smallRender[i * 25 + j])) throw new RuntimeException("" + i + " " + j);
            }
        }

        //fill biomes array with biomeData
        for (i = 0; i < 16; i++) {
            for (j=0; j<16; j++) {
                biomes[i*16+j] =  cmr.getBiomeDataAt(x + (((i-7) * 8+4)), y + (((j-7) * 8+4)));
            }
        }

    	float river;
        
    	for(i = 0; i < 16; i++)
    	{
    		for(j = 0; j < 16; j++)
    		{

    			locationIndex = ((int)(i + 4) * 25 + (j + 4));

    			testHeight[i * 16 + j] = 0f;

    			river = cmr.getRiverStrength(x + i, y + j);
                this.riverVals[i * 16 + j] = -river;
                float totalBorder = 0f;

    			for(k = 0; k < 256; k++)
    			{

    				if(smallRender[locationIndex][k] > 0f)
    				{

    	    			if(locationIndex == centerLocationIndex)
    	    			{
    	    				biomesGeneratedInChunk[k] = smallRender[centerLocationIndex][k];
    	    			}

                        totalBorder += smallRender[locationIndex][k];
    					testHeight[i * 16 + j] += RealisticBiomeBase.getBiome(k).rNoise(simplex, cell, x + i, y + j, smallRender[locationIndex][k], river + 1f) * smallRender[locationIndex][k];
    				}
    			}
                if (totalBorder <.999||totalBorder>1.001) throw new RuntimeException("" + totalBorder);
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

    public float[] mix2(float[] first, float [] second )
    {
    	float[] result = new float[256];
    	int i, j;
        //if (this.totalNotOne(first)) throw new RuntimeException();
        //if (this.totalNotOne(second)) throw new RuntimeException();
    	for(i = 0; i < 256; i++)
    	{
            if(first[i] > 0f)
            {
                result[i] += first[i] / 2f;
            }
            if(second[i] > 0f)
            {
                result[i] += second[i] / 2f;
            }
    	}

        //if (this.totalNotOne(result)) throw new RuntimeException();
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

    private boolean chunkExists(IChunkProvider world, int par1, int par2) {
        if (chunkExists(par1,par2)) return true;
        probe.setX(par1);
        probe.setZ(par2);
        if (this.chunkMade.contains(probe)) return true;
        if  (world.chunkExists(par1, par2)) return true;
        return chunkLoader.chunkExists(worldObj, par1, par2);
    }

    private PlaneLocation.Probe probe = new PlaneLocation.Probe(0, 0);
    /**
     * @see IChunkProvider
     *
     * Checks to see if a chunk exists at x, y
     */
    public boolean chunkExists(int par1, int par2)
    {
        probe.setX(par1);
        probe.setZ(par2);
        /**
         * TODO: Write custom logic to determine whether chunk exists, instead of assuming it does.
         */
        return this.inGeneration.containsKey(probe);
        //return true;
    }
    /**
     * @see IChunkProvider
     *
     * Populates chunk with ores etc etc
     */
    public void populate(IChunkProvider ichunkprovider, int chunkX, int chunkZ){
        //if (this.neighborsDone(ichunkprovider, chunkX-1, chunkZ-1)) this.doPopulate(ichunkprovider, chunkX - 1, chunkZ - 1);
        //if (this.neighborsDone(ichunkprovider, chunkX-1, chunkZ)) this.doPopulate(ichunkprovider, chunkX - 1, chunkZ);
        //if (this.neighborsDone(ichunkprovider, chunkX-1, chunkZ + 1)) this.doPopulate(ichunkprovider, chunkX - 1, chunkZ + 1);
        //if (this.neighborsDone(ichunkprovider, chunkX, chunkZ-1)) this.doPopulate(ichunkprovider, chunkX, chunkZ - 1);
        if (this.neighborsDone(ichunkprovider, chunkX, chunkZ)) {
            this.doPopulate(ichunkprovider, chunkX, chunkZ);
        }
        clearToDecorateList(ichunkprovider);
        //if (this.neighborsDone(ichunkprovider, chunkX, chunkZ+1)) this.doPopulate(ichunkprovider, chunkX, chunkZ + 1);
        //if (this.neighborsDone(ichunkprovider, chunkX+1, chunkZ-1)) this.doPopulate(ichunkprovider, chunkX + 1, chunkZ - 1);
        //if (this.neighborsDone(ichunkprovider, chunkX+1, chunkZ)) this.doPopulate(ichunkprovider, chunkX + 1, chunkZ);
        //if (this.neighborsDone(ichunkprovider, chunkX+1, chunkZ+1)) this.doPopulate(ichunkprovider, chunkX + 1, chunkZ + 1);
    }
    
    private void clearToDecorateList(IChunkProvider ichunkprovider) {
        if (populating) return;// in process, do later;
        // we have to make a copy of the set to work on or we'll get errors
        Set<PlaneLocation> toProcess = doableLocations();
        while (toProcess.size() >0) {
            for (PlaneLocation location: toProcess) {
                doPopulate(ichunkprovider,location.x(),location.z());
                toDecorate.remove(location);
            }
            // and loop because the decorating might have created other chunks to decorate;
            toProcess = doableLocations();
        }
    }

    private Set<PlaneLocation> doableLocations() {
        HashSet<PlaneLocation> toProcess = new HashSet<PlaneLocation>();
        for (PlaneLocation location: toDecorate) {
            Chunk existing = availableChunks.get(location);
            if (existing != null&& !existing.isTerrainPopulated) {
                continue;
            }
            if (inGeneration.containsKey(location)) continue;
            toDecorate.add(location);
        }
        return toProcess;
    }

    private boolean populating = false;

    private HashSet<PlaneLocation> toDecorate = new HashSet<PlaneLocation>();
    private LimitedSet<PlaneLocation> alreadyDecorated  = new LimitedSet<PlaneLocation>(10000);
    private void doPopulate(IChunkProvider ichunkprovider, int chunkX, int chunkZ)
    {
        // don't populate if already done
        PlaneLocation location = new PlaneLocation.Invariant(chunkX, chunkZ);
        if (alreadyDecorated.contains(location)) return;

        if (populating) {
            // this has been created by another decoration; put in todo pile
            toDecorate.add(location);
            return;
        }
        if (inGeneration.containsKey(location)) throw new RuntimeException();
        alreadyDecorated.add(location);
        populating = true;

        TimeTracker.manager.start("RTG populate");
        TimeTracker.manager.start("Features");
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

        TimeTracker.manager.start("Mineshafts");
            if (ConfigRTG.generateMineshafts) {
                mineshaftGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
            }

        TimeTracker.manager.stop("Mineshafts");
        TimeTracker.manager.start("Strongholds");
            if (ConfigRTG.generateStrongholds) {
                strongholdGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
            }

        TimeTracker.manager.stop("Strongholds");
                TimeTracker.manager.start("Villages");
            if (ConfigRTG.generateVillages) {
                
                if (ConfigRTG.villageCrashFix) {
                    
                    try {
                        flag = villageGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
                    }
                    catch (Exception e) {
                        flag = false;
                    }
                }
                else {
                    
                    flag = villageGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
                }
            }

                TimeTracker.manager.stop("Villages");
                TimeTracker.manager.start("Scattered");
            if (ConfigRTG.generateScatteredFeatures) {
                scatteredFeatureGenerator.generateStructuresInChunk(worldObj, rand, chunkX, chunkZ);
            }
                TimeTracker.manager.stop("Scattered");
        }

                TimeTracker.manager.start("Pools");
        biome.rPopulatePreDecorate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag);
                TimeTracker.manager.stop("Pools");

        /**
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno. 
         */

        final int adjust = 32;// seems off? but decorations aren't matching their chunks.
        for (int bx = -4; bx <= 4; bx++) {
            
        	for(int by = -4; by <= 4; by++)
        	{
        		borderNoise[cmr.getBiomeDataAt(worldX + adjust + bx * 4, worldZ + adjust  + by * 4).biomeID] += 0.01234569f;
        	}
        }
        TimeTracker.manager.stop("Features");
        /**
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Decorations");
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

                // Is this a single biome world?
                if (biomePatcher.isSingleBiomeWorld())
                {
                    realisticBiome = biomePatcher.getSingleRealisticBiome();
                }
                else
                {
                    realisticBiome = RealisticBiomeBase.getBiome(bn);
                    
                    // Do we need to patch the biome?
                    if (realisticBiome == null)
                    {
                        realisticBiome = biomePatcher.getPatchedRealisticBiome("NULL biome (" + bn + ") found when generating border noise.");
                    }
                }
                
                /**
                 * When decorating the biome, we need to look at the biome configs to see if RTG is allowed to decorate it.
                 * If the biome configs don't allow it, then we try to let the base biome decorate itself.
                 * However, there are some mod biomes that crash when they try to decorate themselves,
                 * so that's what the try/catch is for. If it fails, then it falls back to RTG decoration.
                 */
                if (ConfigRTG.enableRTGBiomeDecorations && realisticBiome.config._boolean(BiomeConfig.useRTGDecorationsId)) {

                	realisticBiome.decorateInAnOrderlyFashion(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
                }
                else {
                    
                    try {
                        
                        realisticBiome.baseBiome.decorate(this.worldObj, rand, worldX, worldZ);
                    }
                    catch (Exception e) {

                    	realisticBiome.decorateInAnOrderlyFashion(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
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

        TimeTracker.manager.stop("Decorations");
        /**
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */
        TimeTracker.manager.start("Post-decorations");
        biome.rPopulatePostDecorate(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag);
        
        //Flowing water.
        if (rand.nextInt(100) == 0) {
    		for(int l18 = 0; l18 < 50; l18++)
    		{
    			int l21 = worldX + rand.nextInt(16);// + 8;
    			int k23 = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);
    			int l24 = worldZ + rand.nextInt(16);// + 8;
                
    			(new WorldGenLiquids(Blocks.flowing_water)).generate(worldObj, rand, l21, k23, l24);
    		}
        }

        //Flowing lava.
        if (rand.nextInt(100) == 0) {
    		for(int i19 = 0; i19 < 20; i19++)
    		{
    			int i22 = worldX + rand.nextInt(16);// + 8;
    			int l23 = rand.nextInt(worldHeight / 2);
    			int i25 = worldZ + rand.nextInt(16);// + 8;
    			(new WorldGenLiquids(Blocks.flowing_lava)).generate(worldObj, rand, i22, l23, i25);
    		}
        }

        TimeTracker.manager.stop("Post-decorations");
        TimeTracker.manager.start("Entities");
        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.ANIMALS))
        {
            SpawnerAnimals.performWorldGenSpawning(this.worldObj, worldObj.getBiomeGenForCoords(worldX + 16, worldZ + 16), worldX, worldZ, 16, 16, this.rand);
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
        } else {
            throw new RuntimeException();
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(ichunkprovider, worldObj, rand, chunkX, chunkZ, flag));

        TimeTracker.manager.stop("Entities");
        BlockFalling.fallInstantly = false;
        TimeTracker.manager.stop("RTG populate");
        populating = false;
        TimeTracker.manager.stop("RTG chunk");
    }



    public boolean neighborsDone(IChunkProvider world, int cx, int cz) {
        if (!chunkExists(world,cx - 1, cz - 1)) return false;
        if (!chunkExists(world,cx - 1, cz)) return false;
        if (!chunkExists(world,cx - 1, cz + 1)) return false;
        if (!chunkExists(world,cx, cz - 1)) return false;
        if (!chunkExists(world,cx, cz + 1)) return false;
        if (!chunkExists(world,cx + 1, cz - 1)) return false;
        if (!chunkExists(world,cx + 1, cz)) return false;
        if (!chunkExists(world,cx + 1, cz + 1)) return false;
        return true;
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
                
                if (ConfigRTG.villageCrashFix) {
                    
                    try {
                        villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
                    }
                    catch (Exception e) {
                        // Do nothing.
                    }
                    
                }
                else {
                    villageGenerator.func_151539_a(this, this.worldObj, par1, par2, (Block[])null);
                }
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

    public Set<Long> serverLoadingChunks() {
        if (this.serverLoadingChunks == null) {
            ChunkProviderServer server = (ChunkProviderServer)(worldObj.getChunkProvider());
            chunkLoader = (AnvilChunkLoader)(server.currentChunkLoader);
            serverLoadingChunks = forServerLoadingChunks.get(server);
        }
        return serverLoadingChunks;
    }

}
