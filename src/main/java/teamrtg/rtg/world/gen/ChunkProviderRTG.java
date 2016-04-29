package teamrtg.rtg.world.gen;

import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.structure.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.util.PlaneLocation;
import teamrtg.rtg.util.math.CanyonColour;
import teamrtg.rtg.util.math.MathUtils;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.util.noise.SimplexCellularNoise;
import teamrtg.rtg.util.noise.SimplexOctave;
import teamrtg.rtg.world.biome.BiomeAnalyzer;
import teamrtg.rtg.world.biome.BiomeProviderRTG;
import teamrtg.rtg.world.biome.fake.RealisticBiomeFaker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;

/**
 * Note from the ChunkProviderRTG-gods:
 *
 * Any poor soul who trespass against us,
 * whether it be beast or man,
 * will suffer the bite or be stung dead on sight
 * by those who inhabit this land.
 * For theirs is the power and this is their kingdom,
 * as sure as the sun does burn.
 * So enter this path, but heed these four words:
 * You shall never return
 */
public class ChunkProviderRTG implements IChunkGenerator {
    /**
     * Declare variables.
     */

    private static final int centerLocationIndex = 312;// this is x=8, y=8 with the calcs below
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private final MapGenStronghold strongholdGenerator;
    private final MapGenMineshaft mineshaftGenerator;
    private final MapGenVillage villageGenerator;
    private final MapGenScatteredFeature scatteredFeatureGenerator;
    private final StructureOceanMonument oceanMonumentGenerator;
    private final boolean mapFeaturesEnabled;
    private final int worldHeight;
    private static final int sampleSize = 8;
    private final int sampleArraySize;
    private final int parabolicSize;
    private final int parabolicArraySize;
    private final float[] parabolicField;
    public final RealisticBiomeFaker biomeFaker;
    private BiomeProviderRTG bprv;
    private final BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private final IBlockState bedrockBlock = Mods.RTG.config.BEDROCK_BLOCK.get();
    public final Random rand;
    public final Random mapRand;
    public final World world;
    public final OpenSimplexNoise simplex;
    public final CellNoise cell;
    public final SimplexOctave.Disk surfaceJitter = new SimplexOctave.Disk();
    private RealisticBiomeBase[] biomesForGeneration;
    private BiomeGenBase[] baseBiomesList;
    private int[] biomeData;
    private float parabolicFieldTotal;
    private float[][] hugeRender;
    private float[][] smallRender;
    private float[] testHeight;
    private float[] borderNoise;
    private long worldSeed;


    public ChunkProviderRTG(World worldIn, long l) {
        this.world = worldIn;
        bprv = (BiomeProviderRTG) this.world.getBiomeProvider();
        bprv.chunkProvider = this;
        worldHeight = this.world.provider.getActualHeight();
        rand = new Random(l);
        simplex = new OpenSimplexNoise(l);
        cell = new SimplexCellularNoise(l);

        mapRand = new Random(l);
        worldSeed = l;

        Map m = new HashMap();
        m.put("size", "0");
        m.put("distance", "24");

        mapFeaturesEnabled = worldIn.getWorldInfo().isMapFeaturesEnabled();

        biomeFaker = new RealisticBiomeFaker(this);
        Mods.initAllBiomes(this);
        biomeFaker.initFakeBiomes();

        if (Mods.RTG.config.ENABLE_CAVE_MODIFICATIONS.get()) {
            caveGenerator = TerrainGen.getModdedMapGen(new MapGenCavesRTG(), CAVE);
        } else {
            caveGenerator = TerrainGen.getModdedMapGen(new MapGenCaves(), CAVE);
        }

        if (Mods.RTG.config.ENABLE_RAVINE_MODIFICATIONS.get()) {
            ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavineRTG(), RAVINE);
        } else {
            ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavine(), RAVINE);
        }

        villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(m), VILLAGE);
        strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(), STRONGHOLD);
        mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(), MINESHAFT);
        scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(), SCATTERED_FEATURE);
        oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonument(), OCEAN_MONUMENT);

        CanyonColour.init(l);

        sampleArraySize = sampleSize * 2 + 5;

        parabolicSize = sampleSize;
        parabolicArraySize = parabolicSize * 2 + 1;
        parabolicField = new float[parabolicArraySize * parabolicArraySize];
        for (int j = -parabolicSize; j <= parabolicSize; ++j) {
            for (int k = -parabolicSize; k <= parabolicSize; ++k) {
                float f = 0.445f / MathHelper.sqrt_float((float) (j * j + k * k) + 0.3F);
                parabolicField[j + parabolicSize + (k + parabolicSize) * parabolicArraySize] = f;
                parabolicFieldTotal += f;
            }
        }

        baseBiomesList = new BiomeGenBase[256];
        biomeData = new int[sampleArraySize * sampleArraySize];
        hugeRender = new float[81][256];
        smallRender = new float[625][256];
        testHeight = new float[256];
        borderNoise = new float[256];

        //aic = new AICWrapper();
        //isAICExtendingBiomeIdsLimit = aic.isAICExtendingBiomeIdsLimit();
    }

    /**
     * @see IChunkProvider
     * <p/>
     * Loads or generates the chunk at the chunk location specified.
     */
    public Chunk loadChunk(int par1, int par2) {
        return provideChunk(par1, par2);
    }

    @Override
    public Chunk provideChunk(int cx, int cz) {
        rand.setSeed((long) cx * 0x4f9939f508L + (long) cz * 0x1ef1565bd5L);
        ChunkPrimer primer = new ChunkPrimer();
        BiomeGenBase[] baseBiomes = new BiomeGenBase[256];
        RealisticBiomeBase[] rtgBiomes = new RealisticBiomeBase[256];
        RealisticBiomeBase[] jitteredBiomes = new RealisticBiomeBase[256];

        float[] noise = bprv.getHeights(cx, cz);


        for (int k = 0; k < 256; k++) {
            rtgBiomes[k] = RealisticBiomeBase.getBiome(bprv.getBiomes(cx, cz)[k]);
            baseBiomes[k] = rtgBiomes[k].baseBiome;
        }

        RealisticBiomeBase jittered, actual;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                simplex.evaluateNoise(cx * 16 + i, cz * 16 + j, surfaceJitter);
                int pX = (int) Math.round(cx * 16 + i + surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
                int pZ = (int) Math.round(cz * 16 + j + surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
                actual = bprv.getBiomeDataAt(cx * 16 + i, cz * 16 + j);
                jittered = bprv.getBiomeDataAt(pX, pZ);
                jitteredBiomes[i * 16 + j] = (actual.config.SURFACE_BLEED_IN.get() && jittered.config.SURFACE_BLEED_OUT.get()) ? jittered : actual;
            }
        }

        BiomeGenBase[] inverseBaseBiomes = new BiomeGenBase[256];
        for (int i = 0; i < 256; i++) {
            inverseBaseBiomes[i] = baseBiomes[MathUtils.XY_INVERTED[i]];
        }

        baseBiomes = inverseBaseBiomes;

        generateTerrain(primer, noise);

        replaceBlocksForBiome(cx, cz, primer, jitteredBiomes, baseBiomes, noise);

        caveGenerator.generate(world, cx, cz, primer);
        ravineGenerator.generate(world, cx, cz, primer);

        if (mapFeaturesEnabled) {

            if (Mods.RTG.config.GENERATE_MINESHAFTS.get()) {
                mineshaftGenerator.generate(this.world, cx, cz, primer);
            }

            if (Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
                strongholdGenerator.generate(this.world, cx, cz, primer);
            }

            if (Mods.RTG.config.GENERATE_VILLAGES.get()) {

                if (Mods.RTG.config.VILLAGE_CRASH_FIX.get()) {

                    try {
                        villageGenerator.generate(this.world, cx, cz, primer);
                    } catch (Exception e) {
                        // Do nothing.
                    }
                } else {
                    villageGenerator.generate(this.world, cx, cz, primer);
                }
            }

            if (Mods.RTG.config.GENERATE_SCATTERED_FEATURES.get()) {
                scatteredFeatureGenerator.generate(this.world, cx, cz, primer);
            }

            if (Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get()) {
                oceanMonumentGenerator.generate(this.world, cx, cz, primer);
            }
        }

        Chunk chunk = new Chunk(this.world, primer, cx, cz);
        // doJitter no longer needed as the biome array gets fixed
        byte[] abyte1 = chunk.getBiomeArray();
        for (int k = 0; k < abyte1.length; ++k) {
            // biomes are y-first and generateNoise x-first
            /*
            * This 2 line separation is needed, because otherwise, AIC's dynamic patching algorith detects biomes pattern here and patches this part following biomes logic.
            * Which causes game to crash.
            * I cannot do much on my part, so i have to do it here.
            * - Elix_x
            */
            byte b = (byte) BiomeUtils.getIdForBiome(baseBiomes[k]);
            abyte1[k] = b;
        }
        chunk.setBiomeArray(abyte1);
        chunk.generateSkylightMap();
        return chunk;
    }

    public void requestChunk(int cx, int cz) {
        float[] noise;
        RealisticBiomeBase[] biomes = new RealisticBiomeBase[256];
        int[] biomeIds = new int[256];
        int[] biomeData = new int[sampleArraySize * sampleArraySize];
        float[] riverVals = new float[256];

        int k;

        noise = getNewNoise(bprv, cx * 16, cz * 16, biomes, biomeData, riverVals);
        // that routine can change the biome array so put it back if not

        //fill with biomeData
        int[] biomeIndices = bprv.getBiomesGens(cx * 16, cz * 16, 16, 16);


        analyzer.newRepair(biomeIndices, biomes, biomeData, sampleSize, noise, riverVals);

        for (int i = 0; i < 256; i++) {
            biomeIds[i] = BiomeUtils.getIdForBiome(biomes[i]);
        }

        PlaneLocation loc = new PlaneLocation.Invariant(cx, cz);
        bprv.biomes.put(loc, biomeIds);
        bprv.heights.put(loc, noise);
    }

    private float[] getNewNoise(BiomeProviderRTG cmr, int x, int y, RealisticBiomeBase biomes[], int[] biomeData, float[] riverVals) {

        float[][] hugeRender;
        float[][] smallRender;
        float[] testHeight;
        float[] biomesGeneratedInChunk;

        hugeRender = new float[81][256];
        smallRender = new float[625][256];
        testHeight = new float[256];
        biomesGeneratedInChunk = new float[256];

        int i, j, k, locationIndex, m, n, p;

        for (i = -sampleSize; i < sampleSize + 5; i++) {
            for (j = -sampleSize; j < sampleSize + 5; j++) {
                biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] = BiomeUtils.getIdForBiome(cmr.getBiomeDataAt(x + ((i * 8)), y + ((j * 8))));
            }
        }

        for (i = -1; i < 4; i++) {
            for (j = -1; j < 4; j++) {
                hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)] = new float[256];
                for (k = -parabolicSize; k <= parabolicSize; k++) {
                    for (locationIndex = -parabolicSize; locationIndex <= parabolicSize; locationIndex++) {
                        hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)]
                            [biomeData[(i + k + sampleSize + 1) * sampleArraySize + (j + locationIndex + sampleSize + 1)]]
                            += parabolicField[k + parabolicSize + (locationIndex + parabolicSize) * parabolicArraySize] / parabolicFieldTotal;
                    }
                }

            }
        }

        //RENDER HUGE 1
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                hugeRender[(i * 2 + 1) * 9 + (j * 2 + 1)] = mix4(new float[][] {
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
        for (i = -1; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                hugeRender[(i * 2 + 2) * 9 + (j * 2 + 1)] = mix2(
                    hugeRender[(i * 2 + 2) * 9 + (j * 2)],
                    hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)]);
            }
        }

        // RENDER HUGE 3 (Zeno410)
        for (i = 0; i < 4; i++) {
            for (j = -1; j < 4; j++) {
                hugeRender[(i * 2 + 1) * 9 + (j * 2 + 2)] = mix2(
                    hugeRender[(i * 2) * 9 + (j * 2 + 2)],
                    hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)]);
            }
        }

        //RENDER SMALL 0
        for (i = 0; i < 7; i++) {
            for (j = 0; j < 7; j++) {
                if (false) //if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
                {
                    smallRender[(i * 4) * 25 + (j * 4)] = mix4(new float[][] {
                        hugeRender[(i) * 9 + (j + 1)],
                        hugeRender[(i + 1) * 9 + (j)],
                        hugeRender[(i + 1) * 9 + (j + 2)],
                        hugeRender[(i + 2) * 9 + (j + 1)]});
                } else {
                    smallRender[(i * 4) * 25 + (j * 4)] = hugeRender[(i + 1) * 9 + (j + 1)];
                }
            }
        }

        //RENDER SMALL 1
        for (i = 0; i < 6; i++) {
            for (j = 0; j < 6; j++) {
                smallRender[(i * 4 + 2) * 25 + (j * 4 + 2)] = mix4(new float[][] {
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
        for (i = 0; i < 13; i += 2) //0,2,...20
        {
            for (j = 0; j < 11; j += 2)// 0,2,..18
            {
                smallRender[(i * 2) * 25 + (j * 2 + 2)] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2)],
                    smallRender[(i * 2) * 25 + (j * 2 + 4)]);

            }
        }

        //RENDER SMALL 2.2 - points horizontally between 2 known spots
        for (i = 0; i < 11; i += 2) {
            for (j = 0; j < 13; j += 2) {
                smallRender[(i * 2 + 2) * 25 + (j * 2)] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2)],
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
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 12; j++) {
                //if(!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0))
                {
                    smallRender[(i * 2 + 1) * 25 + (j * 2 + 1)] = mix4(new float[][] {
                        smallRender[(i * 2) * 25 + (j * 2)],
                        smallRender[(i * 2 + 2) * 25 + (j * 2)],
                        smallRender[(i * 2) * 25 + (j * 2 + 2)],
                        smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)]});
                }
            }
        }
        //RENDER SMALL 3.1 - points vertically between 2 known spots
        for (i = 0; i < 13; i++) //0,2,...20
        {
            for (j = 0; j < 12; j++)// 0,2,..18
            {
                smallRender[(i * 2) * 25 + (j * 2 + 1)] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2)],
                    smallRender[(i * 2) * 25 + (j * 2 + 2)]);

            }
        }

        //RENDER SMALL 3.2 - points horizontally between 2 known spots
        for (i = 0; i < 12; i++) {
            for (j = 0; j < 13; j++) {
                smallRender[(i * 2 + 1) * 25 + (j * 2)] = mix2(
                    smallRender[(i * 2) * 25 + (j * 2)],
                    smallRender[(i * 2 + 2) * 25 + (j * 2)]);
            }
        }

        for (i = 0; i < 25; i++) {
            for (j = 0; j < 25; j++) {
                if (this.totalNotOne(smallRender[i * 25 + j])) throw new RuntimeException("" + i + " " + j);
            }
        }

        //fill biomes array with biomeData
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {
                biomes[i * 16 + j] = cmr.getBiomeDataAt(x + (((i - 7) * 8 + 4)), y + (((j - 7) * 8 + 4)));
            }
        }

        float river;

        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {

                locationIndex = ((int) (i + 4) * 25 + (j + 4));

                testHeight[i * 16 + j] = 0f;

                river = cmr.getRiverStrength(x + i, y + j);
                riverVals[i * 16 + j] = -river;
                float totalBorder = 0f;

                for (k = 0; k < 256; k++) {

                    if (smallRender[locationIndex][k] > 0f) {

                        if (locationIndex == centerLocationIndex) {
                            biomesGeneratedInChunk[k] = smallRender[centerLocationIndex][k];
                        }

                        totalBorder += smallRender[locationIndex][k];
                        testHeight[i * 16 + j] += RealisticBiomeGenerator.forBiome(k).rNoise(simplex, cell, x + i, y + j, smallRender[locationIndex][k], river + 1f, this) * smallRender[locationIndex][k];
                    }
                }
                if (totalBorder < .999 || totalBorder > 1.001) throw new RuntimeException("" + totalBorder);
            }
        }
        return testHeight;
    }

    public float[] mix4(float[][] ingredients) {
        float[] result = new float[256];
        int i, j;
        for (i = 0; i < 256; i++) {
            for (j = 0; j < 4; j++) {
                if (ingredients[j][i] > 0f) {
                    result[i] += ingredients[j][i] / 4f;
                }
            }
        }

        return result;
    }

    public float[] mix2(float[] first, float[] second) {
        float[] result = new float[256];
        int i, j;
        //if (this.totalNotOne(first)) throw new RuntimeException();
        //if (this.totalNotOne(second)) throw new RuntimeException();
        for (i = 0; i < 256; i++) {
            if (first[i] > 0f) {
                result[i] += first[i] / 2f;
            }
            if (second[i] > 0f) {
                result[i] += second[i] / 2f;
            }
        }

        //if (this.totalNotOne(result)) throw new RuntimeException();
        return result;
    }

    private boolean totalNotOne(float[] tested) {
        float total = 0;
        for (int i = 0; i < tested.length; i++) {
            total += tested[i];
        }
        if (total < .999 || total > 1.001f) return true;
        return false;
    }

    private void generateTerrain(ChunkPrimer primer, float[] heights) {
        int h;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                h = (int) heights[i * 16 + j];

                for (int k = 0; k < 256; k++) {
                    if (k > h) {
                        if (k < 63) {
                            primer.setBlockState(i, k, j, Blocks.WATER.getDefaultState());
                        } else {
                            primer.setBlockState(i, k, j, Blocks.AIR.getDefaultState());
                        }
                    } else {
                        primer.setBlockState(i, k, j, Blocks.STONE.getDefaultState());
                    }
                }
            }
        }
    }

    private void replaceBlocksForBiome(int cx, int cz, ChunkPrimer primer, RealisticBiomeBase[] biomes, BiomeGenBase[] base, float[] n) {
        ChunkGeneratorEvent.ReplaceBiomeBlocks event = new ChunkGeneratorEvent.ReplaceBiomeBlocks(this, cx, cz, primer, world);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;
        int i, j, h, depth;
        float river;
        biomeFaker.fakeSurface(cx, cz, primer, base);
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {

                RealisticBiomeBase biome = biomes[i * 16 + j];

                if (!biomeFaker.isFakeBiome(biome.getID())) {

                    h = (int) n[i * 16 + j];

                    for (int k = 0; k < 256; k++) {
                        if (k > h) {
                            if (k < 63) {
                                primer.setBlockState(i, k, j, Blocks.WATER.getDefaultState());
                            } else {
                                primer.setBlockState(i, k, j, Blocks.AIR.getDefaultState());
                            }
                        } else {
                            primer.setBlockState(i, k, j, Blocks.STONE.getDefaultState());
                        }
                    }

                    river = -bprv.getRiverStrength(cx * 16 + i, cz * 16 + j);
                    depth = -1;

                    RealisticBiomeGenerator.forBiome(biome).paintSurface(primer, cx * 16 + i, cz * 16 + j, i, j, depth, world, rand, simplex, cell, n, river, base);

                    int rough;
                    int flatBedrockLayers = Mods.RTG.config.FLAT_BEDROCK_LAYERS.get();
                    flatBedrockLayers = flatBedrockLayers < 0 ? 0 : (flatBedrockLayers > 5 ? 5 : flatBedrockLayers);

                    if (flatBedrockLayers > 0) {
                        for (int bl = 0; bl < flatBedrockLayers; bl++) {
                            primer.setBlockState(i, bl, j, bedrockBlock);
                        }
                    } else {

                        primer.setBlockState(i, 0, j, bedrockBlock);

                        rough = rand.nextInt(2);
                        primer.setBlockState(i, rough, j, bedrockBlock);

                        rough = rand.nextInt(3);
                        primer.setBlockState(i, rough, j, bedrockBlock);

                        rough = rand.nextInt(4);
                        primer.setBlockState(i, rough, j, bedrockBlock);

                        rough = rand.nextInt(5);
                        primer.setBlockState(i, rough, j, bedrockBlock);
                    }
                }
            }
        }
    }

    /**
     * @see IChunkProvider
     * <p/>
     * Populates chunk with ores etc etc
     */
    @Override
    public void populate(int x, int z) {
        BlockFalling.fallInstantly = true;

        int worldX = x * 16;
        int worldZ = z * 16;
        RealisticBiomeBase biome = bprv.getBiomeDataAt(worldX + 16, worldZ + 16);
        this.rand.setSeed(this.world.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) x * i1 + (long) z * j1 ^ this.world.getSeed());
        boolean flag = false;
        ChunkCoordIntPair chunkCoords = new ChunkCoordIntPair(x, z);
        BlockPos worldCoords = new BlockPos(worldX, 0, worldZ);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, world, rand, x, z, flag));

        if (mapFeaturesEnabled) {

            if (Mods.RTG.config.GENERATE_MINESHAFTS.get()) {
                mineshaftGenerator.generateStructure(world, rand, chunkCoords);
            }

            if (Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
                strongholdGenerator.generateStructure(world, rand, chunkCoords);
            }

            if (Mods.RTG.config.GENERATE_VILLAGES.get()) {

                if (Mods.RTG.config.VILLAGE_CRASH_FIX.get()) {

                    try {
                        flag = villageGenerator.generateStructure(world, rand, chunkCoords);
                    } catch (Exception e) {
                        flag = false;
                    }
                } else {

                    flag = villageGenerator.generateStructure(world, rand, chunkCoords);
                }
            }

            if (Mods.RTG.config.GENERATE_SCATTERED_FEATURES.get()) {
                scatteredFeatureGenerator.generateStructure(world, rand, chunkCoords);
            }

            if (Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get()) {
                oceanMonumentGenerator.generateStructure(world, rand, chunkCoords);
            }
        }

        RealisticBiomeGenerator.forBiome(biome.baseBiome).populatePreDecorate(this, world, rand, x, z, flag);

        /*
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno.
         */

        final int adjust = 32;// seems off? but decorations aren't matching their chunks.
        for (int bx = -4; bx <= 4; bx++) {

            for (int by = -4; by <= 4; by++) {
                borderNoise[BiomeUtils.getIdForBiome(bprv.getBiomeGenAt(worldX + adjust + bx * 4, worldZ + adjust + by * 4))] += 0.01234569f;
            }
        }

        /*
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, rand, worldCoords));

        //Initialise variables.
        float river = -bprv.getRiverStrength(worldX + 16, worldZ + 16);

        //Border noise. (Does this have to be done here? - Pink)
        RealisticBiomeBase realisticBiome;
        float snow = 0f;

        for (int bn = 0; bn < 256; bn++) {
            if (borderNoise[bn] > 0f) {
                if (borderNoise[bn] >= 1f) {
                    borderNoise[bn] = 1f;
                }
                realisticBiome = RealisticBiomeBase.getBiome(bn);

                /*
                 * When decorating the biome, we need to look at the biome configs to see if RTG is allowed to decorate it.
                 * If the biome configs don't allow it, then we try to let the base biome decorate itself.
                 * However, there are some mod biomes that crash when they try to decorate themselves,
                 * so that's what the try/catch is for. If it fails, then it falls back to RTG decoration.
                 * TODO: Is there a more efficient way to do this? - Pink
                 */
                if (Mods.RTG.config.ENABLE_RTG_BIOME_DECORATIONS.get() && realisticBiome.config.USE_RTG_DECORATIONS.get()) {
                    RealisticBiomeGenerator.forBiome(realisticBiome).decorate(this.world, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
                } else {
                    try {
                        realisticBiome.baseBiome.decorate(this.world, rand, worldCoords);
                    } catch (Exception e) {
                        RealisticBiomeGenerator.forBiome(realisticBiome).decorate(this.world, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
                    }
                }

                if (realisticBiome.baseBiome.getTemperature() < 0.15f) {
                    snow -= 0.6f * borderNoise[bn];
                } else {
                    snow += 0.6f * borderNoise[bn];
                }
                borderNoise[bn] = 0f;
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world, rand, worldCoords));

        /*
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */

        //Flowing WATER.
        if (rand.nextInt(100) == 0) {
            for (int l18 = 0; l18 < 50; l18++) {
                int l21 = worldX + rand.nextInt(16) + 8;
                int k23 = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);
                int l24 = worldZ + rand.nextInt(16) + 8;

                (new WorldGenLiquids(Blocks.FLOWING_WATER)).generate(world, rand, new BlockPos(l21, k23, l24));
            }
        }

        //Flowing lava.
        if (rand.nextInt(100) == 0) {
            for (int i19 = 0; i19 < 20; i19++) {
                int i22 = worldX + rand.nextInt(16) + 8;
                int l23 = rand.nextInt(worldHeight / 2);
                int i25 = worldZ + rand.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.FLOWING_LAVA)).generate(world, rand, new BlockPos(i22, l23, i25));
            }
        }

        if (TerrainGen.populate(this, world, rand, x, z, flag, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.world, world.getBiomeGenForCoords(new BlockPos(worldX + 16, 0, worldZ + 16)), worldX + 8, worldZ + 8, 16, 16, this.rand);
        }

        if (TerrainGen.populate(this, world, rand, x, z, flag, PopulateChunkEvent.Populate.EventType.ICE)) {

            int k1, l1, i2;
            BlockPos.MutableBlockPos bp = new BlockPos.MutableBlockPos(0, 0, 0);
            for (k1 = 0; k1 < 16; ++k1) {

                for (l1 = 0; l1 < 16; ++l1) {

                    i2 = this.world.getPrecipitationHeight(bp.set(worldX + k1, 0, worldZ + l1)).getY();

                    if (this.world.canBlockFreezeNoWater(bp.set(k1 + worldX, i2 - 1, l1 + worldZ))) {
                        this.world.setBlockState(bp.set(k1 + worldX, i2 - 1, l1 + worldZ), Blocks.ICE.getDefaultState(), 2);
                    }

                    if (Mods.RTG.config.ENABLE_SNOW_LAYERS.get() && this.world.canSnowAt(bp.set(k1 + worldX, i2, l1 + worldZ), true)) {
                        this.world.setBlockState(bp.set(k1 + worldX, i2, l1 + worldZ), Blocks.SNOW_LAYER.getDefaultState(), 2);
                    }
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this, world, rand, x, z, flag));

        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    /**
     * @see IChunkProvider
     * <p/>
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    @Override
    public List getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        BiomeGenBase var5 = this.world.getBiomeGenForCoords(pos);
        if (this.mapFeaturesEnabled) {
            if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.isSwampHut(pos)) {
                return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
            }

            if (creatureType == EnumCreatureType.MONSTER && Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get() && this.oceanMonumentGenerator.isPositionInStructure(this.world, pos)) {
                return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
            }
        }
        return var5 == null ? null : var5.getSpawnableList(creatureType);
    }

    /**
     * @see IChunkProvider
     */
    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
        if (!Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
            return null;
        }

        return "Stronghold".equals(structureName) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn, position) : null;
    }

    /**
     * @see IChunkProvider
     */
    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

        if (mapFeaturesEnabled) {

            if (Mods.RTG.config.GENERATE_MINESHAFTS.get()) {
                mineshaftGenerator.generate(world, x, z, null);
            }

            if (Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
                strongholdGenerator.generate(world, x, z, null);
            }

            if (Mods.RTG.config.GENERATE_VILLAGES.get()) {

                if (Mods.RTG.config.VILLAGE_CRASH_FIX.get()) {

                    try {
                        villageGenerator.generate(this.world, x, z, null);
                    } catch (Exception e) {
                        // Do nothing.
                    }

                } else {
                    villageGenerator.generate(this.world, x, z, null);
                }
            }

            if (Mods.RTG.config.GENERATE_SCATTERED_FEATURES.get()) {
                scatteredFeatureGenerator.generate(this.world, x, z, null);
            }

            if (Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get()) {
                oceanMonumentGenerator.generate(this.world, x, z, null);
            }
        }
    }
}
