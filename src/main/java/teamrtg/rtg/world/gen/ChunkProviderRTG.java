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
import teamrtg.rtg.util.math.CanyonColour;
import teamrtg.rtg.util.math.MathUtils;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.util.noise.SimplexCellularNoise;
import teamrtg.rtg.world.biome.BiomeAnalyzer;
import teamrtg.rtg.world.biome.BiomeProviderRTG;
import teamrtg.rtg.world.biome.fake.RealisticBiomeFaker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;

/**
 * Scattered features courtesy of Ezoteric (https://github.com/Ezoteric) and Choonster (https://github.com/Choonster)
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
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private final int parabolicSize;
    private final int parabolicArraySize;
    private final float[] parabolicField;
    public final RealisticBiomeFaker biomeFaker;
    protected BiomeProviderRTG bprv;
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private IBlockState bedrockBlock = Mods.RTG.config.BEDROCK_BLOCK.get();
    private Random rand;
    private Random mapRand;
    private World worldObj;
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


    public ChunkProviderRTG(World world, long l) {
        worldObj = world;
        bprv = (BiomeProviderRTG) worldObj.getBiomeProvider();
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

        biomeFaker = new RealisticBiomeFaker(world);

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
        biomesGeneratedInChunk = new float[256];
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
    public Chunk provideChunk(int cx, int cy) {
        rand.setSeed((long) cx * 0x4f9939f508L + (long) cy * 0x1ef1565bd5L);
        ChunkPrimer primer = new ChunkPrimer();
        float[] noise = new float[256];
        biomesForGeneration = new RealisticBiomeBase[256];
        //this.biomesForGeneration = ( (BiomeProviderRTG) this.worldObj.getBiomeProvider()).getRealisticBiomesForGeneration(this.biomesForGeneration, cx * 4 - 2, cy * 4 - 2, 10, 10);

        int k;

        generateTerrain(bprv, cx, cy, primer, biomesForGeneration, noise);
        // that routine can change the biome array so put it back if not

        //fill with biomeData
        int[] biomeIndices = bprv.getBiomesGens(cx * 16, cy * 16, 16, 16);


        analyzer.newRepair(biomeIndices, biomesForGeneration, this.biomeData, this.sampleSize, noise, -bprv.getRiverStrength(cx * 16 + 7, cy * 16 + 7));


        for (k = 0; k < 256; k++) {
            if (biomesGeneratedInChunk[k] > 0f) {
                biomesGeneratedInChunk[k] = 0f;
            }
            baseBiomesList[k] = biomesForGeneration[k].baseBiome;
        }

        RealisticBiomeBase[] inverseBiomesForGeneration = new RealisticBiomeBase[256];
        BiomeGenBase[] inverseBaseBiomes = new BiomeGenBase[256];
        for (int i = 0; i < 256; i++) {
            inverseBiomesForGeneration[i] = biomesForGeneration[MathUtils.XY_INVERTED[i]];
            inverseBaseBiomes[i] = baseBiomesList[MathUtils.XY_INVERTED[i]];
        }

        //biomesForGeneration = inverseBiomesForGeneration;
        baseBiomesList = inverseBaseBiomes;

        replaceBlocksForBiome(cx, cy, primer, biomesForGeneration, baseBiomesList, noise);

        caveGenerator.generate(worldObj, cx, cy, primer);
        ravineGenerator.generate(worldObj, cx, cy, primer);

        if (mapFeaturesEnabled) {

            if (Mods.RTG.config.GENERATE_MINESHAFTS.get()) {
                mineshaftGenerator.generate(this.worldObj, cx, cy, primer);
            }

            if (Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
                strongholdGenerator.generate(this.worldObj, cx, cy, primer);
            }

            if (Mods.RTG.config.GENERATE_VILLAGES.get()) {

                if (Mods.RTG.config.VILLAGE_CRASH_FIX.get()) {

                    try {
                        villageGenerator.generate(this.worldObj, cx, cy, primer);
                    } catch (Exception e) {
                        // Do nothing.
                    }
                } else {
                    villageGenerator.generate(this.worldObj, cx, cy, primer);
                }
            }

            if (Mods.RTG.config.GENERATE_SCATTERED_FEATURES.get()) {
                scatteredFeatureGenerator.generate(this.worldObj, cx, cy, primer);
            }

            if (Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get()) {
                oceanMonumentGenerator.generate(this.worldObj, cx, cy, primer);
            }
        }

        Chunk chunk = new Chunk(this.worldObj, primer, cx, cy);
        // doJitter no longer needed as the biome array gets fixed
        byte[] abyte1 = chunk.getBiomeArray();
        for (k = 0; k < abyte1.length; ++k) {
            // biomes are y-first and generateNoise x-first
            /*
            * This 2 line separation is needed, because otherwise, AIC's dynamic patching algorith detects biomes pattern here and patches this part following biomes logic.
            * Which causes game to crash.
            * I cannot do much on my part, so i have to do it here.
            * - Elix_x
            */
            byte b = (byte) RealisticBiomeBase.getIdForBiome(this.baseBiomesList[k]);
            abyte1[k] = b;
        }
        chunk.setBiomeArray(abyte1);
        chunk.generateSkylightMap();
        return chunk;
    }

    public void generateTerrain(BiomeProviderRTG cmr, int cx, int cy, ChunkPrimer primer, RealisticBiomeBase biomes[], float[] n) {
        int p, h;
        float[] noise = getNewNoise(cmr, cx * 16, cy * 16, biomes, primer);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                h = (int) noise[i * 16 + j];

                for (int k = 0; k < 256; k++) {
                    if (k > h) {
                        if (k < 63) {
                            primer.setBlockState(i, k, j, Blocks.water.getDefaultState());
                        } else {
                            primer.setBlockState(i, k, j, Blocks.air.getDefaultState());
                        }
                    } else {
                        primer.setBlockState(i, k, j, Blocks.stone.getDefaultState());
                    }
                }
                n[i * 16 + j] = noise[i * 16 + j];
            }
        }
    }

    public void replaceBlocksForBiome(int cx, int cz, ChunkPrimer primer, RealisticBiomeBase[] biomes, BiomeGenBase[] base, float[] n) {
        ChunkGeneratorEvent.ReplaceBiomeBlocks event = new ChunkGeneratorEvent.ReplaceBiomeBlocks(this, cx, cz, primer, worldObj);
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
                                primer.setBlockState(i, k, j, Blocks.water.getDefaultState());
                            } else {
                                primer.setBlockState(i, k, j, Blocks.air.getDefaultState());
                            }
                        } else {
                            primer.setBlockState(i, k, j, Blocks.stone.getDefaultState());
                        }
                    }

                    river = -bprv.getRiverStrength(cx * 16 + i, cz * 16 + j);
                    depth = -1;

                    biome.paintSurface(primer, cx * 16 + i, cz * 16 + j, i, j, depth, worldObj, rand, simplex, cell, n, river, base);

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

    public float[] getNewNoise(BiomeProviderRTG cmr, int x, int y, RealisticBiomeBase biomes[], ChunkPrimer primer) {
        int i, j, k, locationIndex, m, n, p;

        for (i = -sampleSize; i < sampleSize + 5; i++) {
            for (j = -sampleSize; j < sampleSize + 5; j++) {
                biomeData[(i + sampleSize) * sampleArraySize + (j + sampleSize)] = RealisticBiomeBase.getIdForBiome(cmr.getBiomeGenAt(x + ((i * 8) - 8), y + ((j * 8) - 8)));
            }
        }

        for (i = -1; i < 4; i++) {
            for (j = -1; j < 4; j++) {
                hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)] = new float[256];
                for (k = -parabolicSize; k <= parabolicSize; k++) {
                    for (locationIndex = -parabolicSize; locationIndex <= parabolicSize; locationIndex++) {
                        hugeRender[(i * 2 + 2) * 9 + (j * 2 + 2)][biomeData[(i + k + sampleSize + 1) * sampleArraySize + (j + locationIndex + sampleSize + 1)]] += parabolicField[k + parabolicSize + (locationIndex + parabolicSize) * parabolicArraySize] / parabolicFieldTotal;
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

        //RENDER HUGE 2
        for (i = 0; i < 7; i++) {
            for (j = 0; j < 7; j++) {
                if (!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0)) {
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
        }

        //RENDER SMALL 2
        for (i = 0; i < 11; i++) {
            for (j = 0; j < 11; j++) {
                if (!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0)) {
                    smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)] = mix4(new float[][] {
                            smallRender[(i * 2) * 25 + (j * 2 + 2)],
                            smallRender[(i * 2 + 2) * 25 + (j * 2)],
                            smallRender[(i * 2 + 2) * 25 + (j * 2 + 4)],
                            smallRender[(i * 2 + 4) * 25 + (j * 2 + 2)]});
                }
            }
        }

        //RENDER SMALL 3
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                smallRender[(i * 2 + 3) * 25 + (j * 2 + 3)] = mix4(new float[][] {
                        smallRender[(i * 2 + 2) * 25 + (j * 2 + 2)],
                        smallRender[(i * 2 + 4) * 25 + (j * 2 + 2)],
                        smallRender[(i * 2 + 2) * 25 + (j * 2 + 4)],
                        smallRender[(i * 2 + 4) * 25 + (j * 2 + 4)]});
            }
        }

        //RENDER SMALL 4
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {
                if (!(i % 2 == 0 && j % 2 == 0) && !(i % 2 != 0 && j % 2 != 0)) {
                    smallRender[(i + 4) * 25 + (j + 4)] = mix4(new float[][] {
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
            for (j = 0; j < 16; j++) {
                biomes[i * 16 + j] = cmr.getBiomeDataAt(x + (((i - 7) * 8 + 4)), y + (((j - 7) * 8 + 4)));
            }
        }


        float river;
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {
                if (randBiome) {
                    bCount = 0f;
                    bRand = 0.5f + simplex.noise2((float) (x + i) / 15f, (float) (y + j) / 15f);
                    bRand = bRand < 0f ? 0f : bRand > 0.99999f ? 0.99999f : bRand;
                }

                locationIndex = ((i + 4) * 25 + (j + 4));

                testHeight[i * 16 + j] = 0f;

                river = cmr.getRiverStrength(x + i, y + j);

                if (locationIndex == centerLocationIndex) {
                    //biomesGeneratedInChunk[256] = river;
                }

                for (k = 0; k < 256; k++) {
                    if (smallRender[locationIndex][k] > 0f) {
                        if (randBiome && bCount <= 1f) //3f)
                        {
                            bCount += smallRender[locationIndex][k];// * 3f;
                            if (bCount > bRand) {
                                bCount = 2f; //20f;
                            }
                        }

                        if (locationIndex == centerLocationIndex) {
                            biomesGeneratedInChunk[k] = smallRender[centerLocationIndex][k];
                        }
                        float height;
                        height = RealisticBiomeGenerator.forBiome(k).rNoise(simplex, cell, x + i, y + j, smallRender[locationIndex][k], river + 1f, this);
                        testHeight[i * 16 + j] += height * smallRender[locationIndex][k];
                    }
                }
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

    /**
     * @see IChunkProvider
     * <p/>
     * Populates chunk with ores etc etc
     */
    public void populate(int chunkX, int chunkZ) {
        BlockFalling.fallInstantly = true;

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        RealisticBiomeBase biome = bprv.getBiomeDataAt(worldX + 16, worldZ + 16);
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.worldObj.getSeed());
        boolean flag = false;
        ChunkCoordIntPair chunkCoords = new ChunkCoordIntPair(chunkX, chunkZ);
        BlockPos worldCoords = new BlockPos(worldX, 0, worldZ);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, worldObj, rand, chunkX, chunkZ, flag));

        if (mapFeaturesEnabled) {

            if (Mods.RTG.config.GENERATE_MINESHAFTS.get()) {
                mineshaftGenerator.generateStructure(worldObj, rand, chunkCoords);
            }

            if (Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
                strongholdGenerator.generateStructure(worldObj, rand, chunkCoords);
            }

            if (Mods.RTG.config.GENERATE_VILLAGES.get()) {

                if (Mods.RTG.config.VILLAGE_CRASH_FIX.get()) {

                    try {
                        flag = villageGenerator.generateStructure(worldObj, rand, chunkCoords);
                    } catch (Exception e) {
                        flag = false;
                    }
                } else {

                    flag = villageGenerator.generateStructure(worldObj, rand, chunkCoords);
                }
            }

            if (Mods.RTG.config.GENERATE_SCATTERED_FEATURES.get()) {
                scatteredFeatureGenerator.generateStructure(worldObj, rand, chunkCoords);
            }

            if (Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get()) {
                oceanMonumentGenerator.generateStructure(worldObj, rand, chunkCoords);
            }
        }

        RealisticBiomeGenerator.forBiome(biome.baseBiome).populatePreDecorate(this, worldObj, rand, chunkX, chunkZ, flag);

        /**
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno.
         */

        final int adjust = 32;// seems off? but decorations aren't matching their chunks.
        for (int bx = -4; bx <= 4; bx++) {

            for (int by = -4; by <= 4; by++) {
                borderNoise[RealisticBiomeBase.getIdForBiome(bprv.getBiomeGenAt(worldX + adjust + bx * 4, worldZ + adjust + by * 4))] += 0.01234569f;
            }
        }

        /**
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldObj, rand, worldCoords));

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

                /**
                 * When decorating the biome, we need to look at the biome configs to see if RTG is allowed to decorate it.
                 * If the biome configs don't allow it, then we try to let the base biome decorate itself.
                 * However, there are some mod biomes that crash when they try to decorate themselves,
                 * so that's what the try/catch is for. If it fails, then it falls back to RTG decoration.
                 * TODO: Is there a more efficient way to do this? - Pink
                 */
                if (Mods.RTG.config.ENABLE_RTG_BIOME_DECORATIONS.get() && realisticBiome.config.USE_RTG_DECORATIONS.get()) {
                    RealisticBiomeGenerator.forBiome(realisticBiome).decorate(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
                } else {
                    try {
                        realisticBiome.baseBiome.decorate(this.worldObj, rand, worldCoords);
                    } catch (Exception e) {
                        RealisticBiomeGenerator.forBiome(realisticBiome).decorate(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river);
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

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, rand, worldCoords));

        /**
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */

        //Flowing water.
        if (rand.nextInt(100) == 0) {
            for (int l18 = 0; l18 < 50; l18++) {
                int l21 = worldX + rand.nextInt(16) + 8;
                int k23 = rand.nextInt(rand.nextInt(worldHeight - 16) + 10);
                int l24 = worldZ + rand.nextInt(16) + 8;

                (new WorldGenLiquids(Blocks.flowing_water)).generate(worldObj, rand, new BlockPos(l21, k23, l24));
            }
        }

        //Flowing lava.
        if (rand.nextInt(100) == 0) {
            for (int i19 = 0; i19 < 20; i19++) {
                int i22 = worldX + rand.nextInt(16) + 8;
                int l23 = rand.nextInt(worldHeight / 2);
                int i25 = worldZ + rand.nextInt(16) + 8;
                (new WorldGenLiquids(Blocks.flowing_lava)).generate(worldObj, rand, new BlockPos(i22, l23, i25));
            }
        }

        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.worldObj, worldObj.getBiomeGenForCoords(new BlockPos(worldX + 16, 0, worldZ + 16)), worldX + 8, worldZ + 8, 16, 16, this.rand);
        }

        if (TerrainGen.populate(this, worldObj, rand, chunkX, chunkZ, flag, PopulateChunkEvent.Populate.EventType.ICE)) {

            int k1, l1, i2;
            BlockPos.MutableBlockPos bp = new BlockPos.MutableBlockPos(0, 0, 0);
            for (k1 = 0; k1 < 16; ++k1) {

                for (l1 = 0; l1 < 16; ++l1) {

                    i2 = this.worldObj.getPrecipitationHeight(bp.set(worldX + k1, 0, worldZ + l1)).getY();

                    if (this.worldObj.canBlockFreezeNoWater(bp.set(k1 + worldX, i2 - 1, l1 + worldZ))) {
                        this.worldObj.setBlockState(bp.set(k1 + worldX, i2 - 1, l1 + worldZ), Blocks.ice.getDefaultState(), 2);
                    }

                    if (Mods.RTG.config.ENABLE_SNOW_LAYERS.get() && this.worldObj.canSnowAt(bp.set(k1 + worldX, i2, l1 + worldZ), true)) {
                        this.worldObj.setBlockState(bp.set(k1 + worldX, i2, l1 + worldZ), Blocks.snow_layer.getDefaultState(), 2);
                    }
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this, worldObj, rand, chunkX, chunkZ, flag));

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
    public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, BlockPos blockPos) {
        BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(blockPos);
        if (this.mapFeaturesEnabled) {
            if (par1EnumCreatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.func_175798_a(blockPos)) {
                return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
            }

            if (par1EnumCreatureType == EnumCreatureType.MONSTER && Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get() && this.oceanMonumentGenerator.isPositionInStructure(this.worldObj, blockPos)) {
                return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
            }
        }
        return var5 == null ? null : var5.getSpawnableList(par1EnumCreatureType);
    }

    /**
     * @see IChunkProvider
     */
    @Override
    public BlockPos getStrongholdGen(World par1World, String par2Str, BlockPos blockPos) {
        if (!Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
            return null;
        }

        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(par1World, blockPos) : null;
    }

    /**
     * @see IChunkProvider
     */
    @Override
    public void recreateStructures(Chunk chunk, int x, int y) {

        if (mapFeaturesEnabled) {

            if (Mods.RTG.config.GENERATE_MINESHAFTS.get()) {
                mineshaftGenerator.generate(worldObj, x, y, null);
            }

            if (Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
                strongholdGenerator.generate(worldObj, x, y, null);
            }

            if (Mods.RTG.config.GENERATE_VILLAGES.get()) {

                if (Mods.RTG.config.VILLAGE_CRASH_FIX.get()) {

                    try {
                        villageGenerator.generate(this.worldObj, x, y, null);
                    } catch (Exception e) {
                        // Do nothing.
                    }

                } else {
                    villageGenerator.generate(this.worldObj, x, y, null);
                }
            }

            if (Mods.RTG.config.GENERATE_SCATTERED_FEATURES.get()) {
                scatteredFeatureGenerator.generate(this.worldObj, x, y, null);
            }

            if (Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get()) {
                oceanMonumentGenerator.generate(this.worldObj, x, y, null);
            }
        }
    }
}
