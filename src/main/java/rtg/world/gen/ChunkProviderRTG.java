package rtg.world.gen;

import java.util.*;
import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.structure.*;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.util.*;
import rtg.api.world.biome.IRealisticBiome;
import rtg.world.RTGWorld;
import rtg.util.TimeTracker;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.BiomeProviderRTG;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenStrongholdRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import rtg.world.gen.structure.StructureOceanMonumentRTG;


@SuppressWarnings({"UnusedParameters", "deprecation"})
public class ChunkProviderRTG implements IChunkGenerator
{
    private static ChunkProviderRTG populatingProvider;
    private RTGConfig rtgConfig = RTGAPI.config();
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private final MapGenStronghold strongholdGenerator;
    private final MapGenMineshaft mineshaftGenerator;
    private final MapGenVillage villageGenerator;
    private final MapGenScatteredFeature scatteredFeatureGenerator;
    private final StructureOceanMonument oceanMonumentGenerator;
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private int [] xyinverted = analyzer.xyinverted();
    private final LandscapeGenerator landscapeGenerator;
    private final LimitedMap<ChunkPos, Chunk> availableChunks;
    private final HashSet<ChunkPos> toDecorate = new HashSet<>();
    private boolean mapFeaturesEnabled;
    private Block bedrockBlock = Block.getBlockFromName(rtgConfig.BEDROCK_BLOCK_ID.get());
    private byte bedrockByte = (byte) rtgConfig.BEDROCK_BLOCK_BYTE.get();
    private Random rand;
    private Random mapRand;
    public final World worldObj;
    public final RTGWorld rtgWorld;
    private WorldUtil worldUtil;
    private IBiomeProviderRTG cmr;
    private Biome[] baseBiomesList;
    private float[] borderNoise;
    private long worldSeed;
    private RealisticBiomePatcher biomePatcher;
    private HashMap<ChunkPos, Chunk> inGeneration = new HashMap<>();
    private HashSet<ChunkPos> toCheck = new HashSet<>();
    private Compass compass = new Compass();
    private ArrayList<Direction> directions = compass.directions();
    private PlateauBand plateauBand;

    //private HashSet<PlaneLocation> everGenerated = new HashSet<PlaneLocation>();
    private TimedHashSet<ChunkPos> chunkMade = new TimedHashSet<>(5 * 1000);
    private boolean populating = false;
    private boolean fakeGenerator = false;
    private LimitedSet<ChunkPos> alreadyDecorated = new LimitedSet<>(1000);
    private ChunkOreGenTracker chunkOreGenTracker = new ChunkOreGenTracker();
    private AnvilChunkLoader chunkLoader;
    private VolcanoGenerator volcanoGenerator;

    // we have to store this callback because it's a WeakReference in the event manager
    public final Acceptor<ChunkEvent.Load> delayedDecorator = new Acceptor<ChunkEvent.Load>() {
        @Override
        public void accept(ChunkEvent.Load event) {

            if (event.isCanceled()) {
                Logger.debug("CPRTG#Acceptor: event is cancelled.");
                return;
            }

            ChunkPos pos = event.getChunk().getChunkCoordIntPair();

            if (!toCheck.contains(pos)) {
                Logger.debug("CPRTG#Acceptor: toCheck contains pos.");
                return;
            }

            toCheck.remove(pos);

            for (Direction forPopulation : directions) {
                decorateIfOtherwiseSurrounded(event.getWorld().getChunkProvider(), pos, forPopulation);
            }
            //clearDecorations(0);
        }
    };

    public ChunkProviderRTG(World world, long seed) {

        Logger.debug("STARTED instantiating CPRTG.");

        worldObj = world;
        worldUtil = new WorldUtil(world);
        rtgWorld = new RTGWorld(worldObj);
        cmr = (BiomeProviderRTG) worldObj.getBiomeProvider();
        rand = new Random(seed);
        landscapeGenerator = rtgWorld.landscapeGenerator;
        mapRand = new Random(seed);
        worldSeed = seed;
        volcanoGenerator = new VolcanoGenerator(seed);
        Map<String, String> m = new HashMap<>();
        m.put("size", "0");
        m.put("distance", "24");
        mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();
        boolean isRTGWorld = DimensionManagerRTG.isValidDimension(world.provider.getDimension());

        plateauBand = PlateauBand.getInstance();
        plateauBand.init(rtgWorld);

        if (isRTGWorld && rtgConfig.ENABLE_CAVE_MODIFICATIONS.get()) {
            caveGenerator = (MapGenCaves) TerrainGen.getModdedMapGen(new MapGenCavesRTG(), EventType.CAVE);
        }
        else {
            caveGenerator = (MapGenCaves) TerrainGen.getModdedMapGen(new MapGenCaves(), EventType.CAVE);
        }

        if (isRTGWorld && rtgConfig.ENABLE_RAVINE_MODIFICATIONS.get()) {
            ravineGenerator = (MapGenRavine) TerrainGen.getModdedMapGen(new MapGenRavineRTG(), EventType.RAVINE);
        }
        else {
            ravineGenerator = (MapGenRavine) TerrainGen.getModdedMapGen(new MapGenRavine(), EventType.RAVINE);
        }

        if (isRTGWorld && rtgConfig.ENABLE_VILLAGE_MODIFICATIONS.get()) {
            villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillageRTG(), EventType.VILLAGE);
        }
        else {
            villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(m), EventType.VILLAGE);
        }

        if (isRTGWorld && rtgConfig.ENABLE_STRONGHOLD_MODIFICATIONS.get()) {
            strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStrongholdRTG(), EventType.STRONGHOLD);
        }
        else {
            strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(), EventType.STRONGHOLD);
        }

        mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(), EventType.MINESHAFT);

        if (isRTGWorld && rtgConfig.ENABLE_SCATTERED_FEATURE_MODIFICATIONS.get()) {
            scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeatureRTG(), EventType.SCATTERED_FEATURE);
        }
        else {
            scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(), EventType.SCATTERED_FEATURE);
        }

        if (isRTGWorld && rtgConfig.ENABLE_OCEAN_MONUMENT_MODIFICATIONS.get()) {
            oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonumentRTG(), EventType.OCEAN_MONUMENT);
        }
        else {
            oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonument(), EventType.OCEAN_MONUMENT);
        }

        sampleArraySize = sampleSize * 2 + 5;
        baseBiomesList = new Biome[256];
        biomePatcher = new RealisticBiomePatcher();

        // set up the cache of available chunks
        availableChunks = new LimitedMap<>(1000);
        setWeightings();

        // check for bogus world
        if (worldObj == null) throw new RuntimeException("Attempt to create chunk provider without a world");

        Logger.debug("FINISHED instantiating CPRTG.");
    }

    private void setWeightings() {
        float[][] weightings = new float[sampleArraySize * sampleArraySize][256];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                float limit = (float) Math.pow((56f * 56f), .7);
                // float limit = 56f;

                for (int mapX = 0; mapX < sampleArraySize; mapX++) {
                    for (int mapZ = 0; mapZ < sampleArraySize; mapZ++) {
                        float xDist = (i - chunkCoordinate(mapX));
                        float yDist = (j - chunkCoordinate(mapZ));
                        float distanceSquared = xDist * xDist + yDist * yDist;
                        //float distance = (float)Math.sqrt(distanceSquared);
                        float distance = (float) Math.pow(distanceSquared, .7);
                        float weight = 1f - distance / limit;
                        if (weight < 0) weight = 0;
                        weightings[mapX * sampleArraySize + mapZ][i * 16 + j] = weight;
                    }
                }
            }
        }
    }

    private int chunkCoordinate(int biomeMapCoordinate) {
        return (biomeMapCoordinate - sampleSize) * 8;
    }

    public void isFakeGenerator() {

        this.fakeGenerator = true;
        this.mapFeaturesEnabled = false;
    }

    @Nonnull
    public Chunk provideChunk(final int cx, final int cz) {
        final ChunkPos pos = new ChunkPos(cx, cz);
        if (inGeneration.containsKey(pos)) return inGeneration.get(pos);
        //if (availableChunks.size() > 1000) throw new RuntimeException();
        if (chunkMade.contains(pos)) {
            Chunk available;
            available = availableChunks.get(pos);
            // this should never be happening but it came up when Forge/MC re-requested an already
            // made chunk for a lighting check (???)

            // we are having a problem with Forge complaining about double entity registration
            // so we'll unload any loaded entities
            if (available != null) {
                ClassInheritanceMultiMap<Entity>[] entityLists = available.getEntityLists();
                for (ClassInheritanceMultiMap<Entity> entityList : entityLists) {
                /*
                    Iterator iterator = entityLists[i].iterator();
                    while (iterator.hasNext()) {

                        iterator.next();
                        iterator.remove();
                    }
                */
                    worldObj.unloadEntities(entityList);
                }
                toCheck.add(pos);
                return available;
            }
        }

        //if (everGenerated.contains(pos)) throw new RuntimeException();

        String rtgTerrain = "RTG Terrain";
        TimeTracker.manager.start(rtgTerrain);
        rand.setSeed((long) cx * 0x4f9939f508L + (long) cz * 0x1ef1565bd5L);
        ChunkPrimer primer = new ChunkPrimer();
        int k;

        String landscaping = "RTG Landscape";
        TimeTracker.manager.start(landscaping);
        ChunkLandscape landscape = landscapeGenerator.landscape(cmr, cx * 16, cz * 16);

        TimeTracker.manager.stop(landscaping);

        String fill = "RTG Fill";
        TimeTracker.manager.start(fill);
        generateTerrain(cmr, cx, cz, primer, landscape.biome, landscape.noise);
        TimeTracker.manager.stop(fill);
        // that routine can change the blocks.
        //get standard biome Data
        String volcanos = "Volcanos";
        TimeTracker.manager.start(volcanos);
        for (k = 0; k < 256; k++) {

            try {
                baseBiomesList[k] = landscape.biome[k].baseBiome();
            }
            catch (Exception e) {
                baseBiomesList[k] = biomePatcher.getPatchedBaseBiome("" + Biome.getIdForBiome(landscape.biome[k].baseBiome()));
            }
        }
        volcanoGenerator.generateMapGen(primer, worldSeed, worldObj, cmr, mapRand, cx, cz, rtgWorld.simplex, rtgWorld.cell, landscape.noise);
        TimeTracker.manager.stop(volcanos);

        String replace = "RTG Replace";
        TimeTracker.manager.start(replace);

        borderNoise = landscapeGenerator.noiseFor(cmr, cx * 16, cz * 16);

        IRealisticBiome[] jitteredBiomes = new IRealisticBiome[256];

        IRealisticBiome jittered, actual;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                rtgWorld.simplex.evaluateNoise(cx * 16 + i, cz * 16 + j, rtgWorld.surfaceJitter);
                int pX = (int) Math.round(cx * 16 + i + rtgWorld.surfaceJitter.deltax() * rtgConfig.SURFACE_BLEED_RADIUS.get());
                int pZ = (int) Math.round(cz * 16 + j + rtgWorld.surfaceJitter.deltay() * rtgConfig.SURFACE_BLEED_RADIUS.get());
//                // These wont work, since they are pre-repair
//                actual = cmr.getBiomeDataAt(cx * 16 + i, cz * 16 + j);
//                jittered = cmr.getBiomeDataAt(pX, pZ);
                actual = RealisticBiomeBase.getBiome(rtgWorld.getRepairedBiomeAt(cmr, cx * 16 + i, cz * 16 + j));
                jittered = RealisticBiomeBase.getBiome(rtgWorld.getRepairedBiomeAt(cmr, pX, pZ));
                jitteredBiomes[i * 16 + j] = (actual.getConfig().SURFACE_BLEED_IN.get() && jittered.getConfig().SURFACE_BLEED_OUT.get()) ? jittered : actual;
            }
        }

        replaceBlocksForBiome(cx, cz, primer, jitteredBiomes, baseBiomesList, landscape.noise);

        TimeTracker.manager.stop(replace);

        String features = "Vanilla Features";
        TimeTracker.manager.start(features);
        caveGenerator.generate(worldObj, cx, cz, primer);
        ravineGenerator.generate(worldObj, cx, cz, primer);
        if (mapFeaturesEnabled) {

            if (rtgConfig.GENERATE_MINESHAFTS.get()) {
                try {
                    mineshaftGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in mineshaftGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in mineshaftGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_STRONGHOLDS.get()) {
                try {
                    strongholdGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in strongholdGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in strongholdGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_VILLAGES.get()) {
                try {
                    villageGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in villageGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in villageGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_SCATTERED_FEATURES.get()) {
                try {
                    scatteredFeatureGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in scatteredFeatureGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in scatteredFeatureGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get()) {
                try {
                    oceanMonumentGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in oceanMonumentGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in oceanMonumentGenerator");
                        e.printStackTrace();
                    }
                }
            }
        }

        TimeTracker.manager.stop(features);

        String housekeeping = "Terrain Housekeeping";
        TimeTracker.manager.start(housekeeping);

        // store in the in process pile
        Chunk chunk = new Chunk(this.worldObj, primer, cx, cz);
        inGeneration.put(pos, chunk);

        // doJitter no longer needed as the biome array gets fixed

        byte[] abyte1 = chunk.getBiomeArray();
        for (k = 0; k < abyte1.length; ++k) {
            // Biomes are y-first and terrain x-first
            byte b = (byte) Biome.getIdForBiome(this.baseBiomesList[xyinverted[k]]);
            abyte1[k] = b;
        }
        chunk.setBiomeArray(abyte1);

        chunk.generateSkylightMap();
        toCheck.add(pos);

        // remove from in process pile
        inGeneration.remove(pos);
        this.chunkMade.add(pos);
        //this.everGenerated.add(pos);
        /*if (!chunkMade.contains(pos)||!everGenerated.contains(pos)) {
            throw new RuntimeException(pos.toString() +  chunkMade.size());
        }*/
        availableChunks.put(pos, chunk);
        TimeTracker.manager.stop(housekeeping);
        TimeTracker.manager.stop(rtgTerrain);
        return chunk;
    }

    private void generateTerrain(IBiomeProviderRTG cmr, int cx, int cz, ChunkPrimer primer,
                                 IRealisticBiome biomes[], float[] noise) {

        int h;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                h = (int) noise[i * 16 + j];

                for (int k = 0; k < 256; k++) {
                    if (k > h) {
                        if (k < 63) {
                            primer.setBlockState(i, k, j, Blocks.WATER.getDefaultState());
                        }
                        else {
                            primer.setBlockState(i, k, j, Blocks.AIR.getDefaultState());
                        }
                    }
                    else {
                        primer.setBlockState(i, k, j, Blocks.STONE.getDefaultState());
                    }
                }
            }
        }
    }

    private void replaceBlocksForBiome(int cx, int cz, ChunkPrimer primer, IRealisticBiome[]
        biomes, Biome[] base, float[] n) {

        ChunkGeneratorEvent.ReplaceBiomeBlocks event = new ChunkGeneratorEvent.ReplaceBiomeBlocks(
            this, cx, cz, primer, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return;

        int i, j, depth;
        float river;
        IRealisticBiome biome;

        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {

                /*
                 * Some of the 'i' and 'j' parameters have been flipped when passing them.
                 * Prior to flipping, the surface was being XZ-chunk-flipped. - WhichOnesPink
                 */
                biome = biomes[i * 16 + j];
                river = -cmr.getRiverStrength(cx * 16 + i, cz * 16 + j);
                depth = -1;

                if (rtgWorld.organicBiomeGenerator.isOrganicBiome(Biome.getIdForBiome(biome.baseBiome()))) {

                    rtgWorld.organicBiomeGenerator.organicSurface(cx * 16 + i, cz * 16 + j, primer, biome.baseBiome());
                }
                else {

                    biome.rReplace(primer, cx * 16 + i, cz * 16 + j, i, j, depth, rtgWorld, n, river, base);
                }

                int rough;
                int flatBedrockLayers = rtgConfig.FLAT_BEDROCK_LAYERS.get();
                flatBedrockLayers = flatBedrockLayers < 0 ? 0 : (flatBedrockLayers > 5 ? 5 : flatBedrockLayers);

                if (flatBedrockLayers > 0) {
                    for (int bl = 0; bl < flatBedrockLayers; bl++) {
                        primer.setBlockState(i, bl, j, bedrockBlock.getStateFromMeta(bedrockByte));
                    }
                }
                else {

                    primer.setBlockState(i, 0, j, bedrockBlock.getStateFromMeta(bedrockByte));

                    rough = rand.nextInt(2);
                    primer.setBlockState(i, rough, j, bedrockBlock.getStateFromMeta(bedrockByte));

                    rough = rand.nextInt(3);
                    primer.setBlockState(i, rough, j, bedrockBlock.getStateFromMeta(bedrockByte));

                    rough = rand.nextInt(4);
                    primer.setBlockState(i, rough, j, bedrockBlock.getStateFromMeta(bedrockByte));

                    rough = rand.nextInt(5);
                    primer.setBlockState(i, rough, j, bedrockBlock.getStateFromMeta(bedrockByte));
                }
            }
        }
    }

    @Override
    public void populate(int x, int z) {
        // check if this is the master provider
        if (this.fakeGenerator) return;
        //if (this.alreadyDecorated.contains(new PlaneLocation.Invariant(chunkX, chunkZ))) return;
        if (this.neighborsDone(x, z)) {
            this.doPopulate(x, z);
        }
        clearDecorations(0);
    }

    private boolean neighborsDone(int cx, int cz) {
        return chunkExists(true, cx - 1, cz - 1) && chunkExists(true, cx - 1, cz) && chunkExists(true, cx - 1, cz + 1) && chunkExists(true, cx, cz - 1) && chunkExists(true, cx, cz + 1) && chunkExists(true, cx + 1, cz - 1) && chunkExists(true, cx + 1, cz) && chunkExists(true, cx + 1, cz + 1);
    }

    private void doPopulate(int chunkX, int chunkZ) {
        // don't populate if already done

        ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        //Logger.debug("trying to decorate: " + chunkPos.toString());
        if (alreadyDecorated.contains(chunkPos)) return;

        if (populating) {
            // this has been created by another decoration; put in to-do pile
            addToDecorationList(chunkPos);
            return;
        }

        if (populatingProvider != null) throw new RuntimeException(toString() + " " + populatingProvider.toString());

        if (inGeneration.containsKey(chunkPos)) {
            addToDecorationList(chunkPos);
            return;
        }

        //Logger.debug("decorating");
        alreadyDecorated.add(chunkPos);
        populating = true;
        populatingProvider = this;

        TimeTracker.manager.start("RTG populate");
        TimeTracker.manager.start("Features");
        BlockFalling.fallInstantly = true;

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        TimeTracker.manager.start("Biome Layout");

        //Flippy McFlipperson.
        IRealisticBiome biome = cmr.getBiomeDataAt(worldX + 16, worldZ + 16);
        //Logger.debug("CPRTG#doPopulate: %s at %d %d", biome.baseBiome.getBiomeName(), worldX + 16, worldZ + 16);

        TimeTracker.manager.stop("Biome Layout");
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.worldObj.getSeed());
        boolean hasPlacedVillageBlocks = false;

        ForgeEventFactory.onChunkPopulate(true, this, this.worldObj, this.rand, chunkX, chunkZ, false);

        if (mapFeaturesEnabled) {

            TimeTracker.manager.start("Mineshafts");
            if (rtgConfig.GENERATE_MINESHAFTS.get()) {
                try {
                    mineshaftGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in mineshaftGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in mineshaftGenerator");
                        e.printStackTrace();
                    }
                }
            }
            TimeTracker.manager.stop("Mineshafts");

            TimeTracker.manager.start("Strongholds");
            if (rtgConfig.GENERATE_STRONGHOLDS.get()) {
                try {
                    strongholdGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in strongholdGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in strongholdGenerator");
                        e.printStackTrace();
                    }
                }
            }
            TimeTracker.manager.stop("Strongholds");

            TimeTracker.manager.start("Villages");
            if (rtgConfig.GENERATE_VILLAGES.get()) {
                try {
                    hasPlacedVillageBlocks = villageGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    hasPlacedVillageBlocks = false;
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in villageGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in villageGenerator");
                        e.printStackTrace();
                    }
                }
            }
            TimeTracker.manager.stop("Villages");

            TimeTracker.manager.start("Scattered");
            if (rtgConfig.GENERATE_SCATTERED_FEATURES.get()) {
                try {
                    scatteredFeatureGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in scatteredFeatureGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in scatteredFeatureGenerator");
                        e.printStackTrace();
                    }
                }
            }
            TimeTracker.manager.stop("Scattered");

            TimeTracker.manager.start("Monuments");
            if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get()) {
                try {
                    oceanMonumentGenerator.generateStructure(this.worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in oceanMonumentGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in oceanMonumentGenerator");
                        e.printStackTrace();
                    }
                }
            }
            TimeTracker.manager.stop("Monuments");
        }

        TimeTracker.manager.start("Pools");
        biome.rDecorator().rPopulatePreDecorate(this, worldObj, rand, chunkX, chunkZ, hasPlacedVillageBlocks);
        TimeTracker.manager.stop("Pools");

        /*
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno.
         */

        borderNoise = landscapeGenerator.noiseFor(cmr, worldX, worldZ);

        /*
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Decorations");
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldObj, rand, new BlockPos(worldX, 0, worldZ)));

        Logger.debug("DecorateBiomeEvent.Pre (%d %d)", worldX, worldZ);

        // Ore gen.
        this.generateOres(biome, new BlockPos(worldX, 0, worldZ));

        //Initialise variables.
        float river = -cmr.getRiverStrength(worldX + 16, worldZ + 16);

        //Border noise. (Does this have to be done here? - Pink)
        RealisticBiomeBase realisticBiome;

        TreeSet<Valued<RealisticBiomeBase>> activeBiomes = new TreeSet();
        for (int bn = 0; bn < 256; bn++) {
            if (borderNoise[bn] > 0f) {
                if (borderNoise[bn] >= 1f) borderNoise[bn] = 1f;

                realisticBiome = RealisticBiomeBase.getBiome(bn);

                // Do we need to patch the biome?
                if (realisticBiome == null) {
                    realisticBiome = biomePatcher.getPatchedRealisticBiome(
                        "NULL biome (" + bn + ") found when generating border noise.");
                }
                activeBiomes.add(new Valued(borderNoise[bn],realisticBiome));

                borderNoise[bn] = 0f;
            }
        }

        // for basebiomedeco interference: run the biomes in reverse order of influence
        for (Valued<RealisticBiomeBase> biomeInfluence: activeBiomes.descendingSet()) {
            realisticBiome = biomeInfluence.item();
            float borderNoise = (float)biomeInfluence.value();
                /*
                 * When decorating the biome, we need to look at the biome configs to see if RTG is allowed to decorate it.
                 * If the biome configs don't allow it, then we try to let the base biome decorate itself.
                 * However, there are some mod biomes that crash when they try to decorate themselves,
                 * so that's what the try/catch is for. If it fails, then it falls back to RTG decoration.
                 */
            if (rtgConfig.ENABLE_RTG_BIOME_DECORATIONS.get() && realisticBiome.getConfig().USE_RTG_DECORATIONS.get()) {

                realisticBiome.rDecorate(this.rtgWorld, this.rand, worldX, worldZ, borderNoise, river, hasPlacedVillageBlocks);
            }
            else {

                try {

                    realisticBiome.baseBiome.decorate(this.worldObj, rand, new BlockPos(worldX, 0, worldZ));
                }
                catch (Exception e) {

                    realisticBiome.rDecorate(this.rtgWorld, this.rand, worldX, worldZ, borderNoise, river, hasPlacedVillageBlocks);
                }
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, rand, new BlockPos(worldX, 0, worldZ)));

        Logger.debug("DecorateBiomeEvent.Post (%d %d)", worldX, worldZ);

        TimeTracker.manager.stop("Decorations");

        /*
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Post-decorations");
        biome.rDecorator().rPopulatePostDecorate(worldObj, rand, chunkX, chunkZ, hasPlacedVillageBlocks);
        TimeTracker.manager.stop("Post-decorations");

        TimeTracker.manager.start("Entities");
        if (TerrainGen.populate(this, this.worldObj, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome.baseBiome(), worldX + 8, worldZ + 8, 16, 16, this.rand);
        }
        TimeTracker.manager.stop("Entities");

        TimeTracker.manager.start("Ice");
        if (TerrainGen.populate(this, this.worldObj, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ICE)) {

            int i4, j4;
            IBlockState snowLayerBlock = Blocks.SNOW_LAYER.getDefaultState();
            IBlockState iceBlock = Blocks.ICE.getDefaultState();

            for (i4 = 0; i4 < 16; ++i4) {

                for (j4 = 0; j4 < 16; ++j4) {

                    BlockPos snowPos = this.worldObj.getPrecipitationHeight(new BlockPos(worldX + i4, 0, worldZ + j4));
                    BlockPos icePos = snowPos.down();

                    // Ice.
                    if(this.worldObj.canBlockFreezeWater(icePos)) {
                        this.worldObj.setBlockState(icePos, iceBlock, 2);
                    }

                    // Snow.
                    if (rtgConfig.ENABLE_SNOW_LAYERS.get() && this.worldUtil.canSnowAt(snowPos, true)) {
                        this.worldObj.setBlockState(snowPos, snowLayerBlock, 2);
                    }
                }
            }
        }
        TimeTracker.manager.stop("Ice");

        ForgeEventFactory.onChunkPopulate(false, this, this.worldObj, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks);

        BlockFalling.fallInstantly = false;
        TimeTracker.manager.stop("RTG populate");
        populating = false;
        populatingProvider = null;
    }

    private void clearDecorations(int limit) {
        if (WorldTypeRTG.chunkProvider != this) return;
        Set<ChunkPos> toProcess = doableLocations(limit);
        toProcess.forEach(this::removeFromDecorationList);
        for (ChunkPos location : toProcess) {
            doPopulate(location.chunkXPos, location.chunkZPos);
        }
    }

    private Set<ChunkPos> doableLocations(int limit) {
        HashSet<ChunkPos> toProcess = new HashSet<>();
        int found = 0;
        synchronized (toDecorate) {
            for (ChunkPos pos : toDecorate) {
            /*
                Chunk existing;
                existing = availableChunks.get(pos);
                if (existing != null) {
                    if (!existing.isTerrainPopulated()) {
                        //continue; // not populated so let more "normal" systems handle it
                    }
                }
            */
                if (inGeneration.containsKey(pos)) continue;
                toProcess.add(pos);
                if (++found == limit) return toProcess;
            }
        }
        return toProcess;
    }

    @Override
    public boolean generateStructures(@Nonnull Chunk chunkIn, int x, int z) {
        boolean flag = false;

        if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get() && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L) {
            flag = this.oceanMonumentGenerator.generateStructure(this.worldObj, this.rand, new ChunkPos(x, z));
        }
        return flag;
    }

    @Override
    @Nonnull
    public List<Biome.SpawnListEntry> getPossibleCreatures(@Nonnull EnumCreatureType creatureType, @Nonnull BlockPos pos) {
        Biome biome = this.worldObj.getBiome(pos);

        if (this.mapFeaturesEnabled) {
            if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.isSwampHut(pos)) {
                return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
            }

            if (creatureType == EnumCreatureType.MONSTER && rtgConfig.GENERATE_OCEAN_MONUMENTS.get() && this.oceanMonumentGenerator.isPositionInStructure(this.worldObj, pos)) {
                return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
            }
        }
        return biome.getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(@Nonnull World par1World, @Nonnull String par2Str, @Nonnull BlockPos blockPos) {

        if (!rtgConfig.GENERATE_STRONGHOLDS.get()) return null;
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(par1World, blockPos) : null;
    }

    @Override
    public void recreateStructures(@Nonnull Chunk chunk, int par1, int par2) {

        if (mapFeaturesEnabled) {
            if (rtgConfig.GENERATE_MINESHAFTS.get()) {
                try {
                    mineshaftGenerator.generate(worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in mineshaftGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in mineshaftGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_STRONGHOLDS.get()) {
                try {
                    strongholdGenerator.generate(worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in strongholdGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in strongholdGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_VILLAGES.get()) {
                try {
                    villageGenerator.generate(this.worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in villageGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in villageGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_SCATTERED_FEATURES.get()) {
                try {
                    scatteredFeatureGenerator.generate(this.worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in scatteredFeatureGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in scatteredFeatureGenerator");
                        e.printStackTrace();
                    }
                }
            }

            if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get()) {
                try {
                    oceanMonumentGenerator.generate(this.worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
                        Logger.fatal("Exception in oceanMonumentGenerator");
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.error("Exception in oceanMonumentGenerator");
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void decorateIfOtherwiseSurrounded(IChunkProvider world, ChunkPos pos, Direction fromNewChunk) {

        // check if this is the master provider
        if (WorldTypeRTG.chunkProvider != this) {
            Logger.debug("Cannot decorate-if-otherwise-surrounded.");
            return;
        }

        // see if otherwise surrounded besides the new chunk
        ChunkPos probe = new ChunkPos(pos.chunkXPos + fromNewChunk.xOffset, pos.chunkZPos + fromNewChunk.zOffset);

        // check to see if already decorated; shouldn't be but just in case
        if (this.alreadyDecorated.contains(probe)) {
            Logger.debug("Already decorated (%d %d).", pos.chunkXPos, pos.chunkZPos);
            return;
        }

        // if an in-process chunk; we'll get a populate call later;
        // if (this.inGeneration.containsKey(probe)) return;

        for (Direction checked : directions) {

            if (checked == compass.opposite(fromNewChunk)) {
                Logger.debug("Chunk checked (%d %d). Continuing...", pos.chunkXPos, pos.chunkZPos);
                continue; // that's the new chunk
            }

            if (!chunkExists(true, probe.chunkXPos + checked.xOffset, probe.chunkZPos + checked.zOffset)) {
                Logger.debug("Chunk doesn't exist (%d %d). Returning...", pos.chunkXPos, pos.chunkZPos);
                return;// that one's missing
            }
        }

        // passed all checks
        addToDecorationList(probe);

        Logger.debug("Chunk added to decoration list (%d %d).", probe.chunkXPos, probe.chunkZPos);

        //this.doPopulate(probe.chunkXPos, probe.chunkZPos);
    }

    private boolean chunkExists(boolean checkNeighbours, int cx, int cz) {
        //if (chunkExists(cx,cz)) return true;
        ChunkPos location = new ChunkPos(cx, cz);
        if (inGeneration.containsKey(location)) return true;
        if (toCheck.contains(location)) return true;
        if (this.chunkMade.contains(location)) return true;
        //if  (world.chunkExists(cx, cz)) return true;
        if (chunkLoader().chunkExists(worldObj, cx, cz)) return true;
        //if (this.everGenerated.contains(location)) throw new RuntimeException("somehow lost "+location.toString());
        return false;
    }

    public boolean chunkExists(int x, int z) {
        return this.chunkExists(true, x, z);
    }

    private void addToDecorationList(ChunkPos toAdd) {
        synchronized (toDecorate) {
            toDecorate.add(toAdd);
        }
    }

    private AnvilChunkLoader chunkLoader() {
        if (chunkLoader == null) {
            ChunkProviderServer server = (ChunkProviderServer) (worldObj.getChunkProvider());
            chunkLoader = (AnvilChunkLoader) (server.chunkLoader);
        }
        return chunkLoader;
    }

    /**
     * @see IChunkProvider
     * Loads or generates the chunk at the chunk location specified.
     */
    @SuppressWarnings("unused")
    public Chunk loadChunk(int par1, int par2) {
        throw new RuntimeException();
//      return provideChunk(par1, par2);
    }

    public Runnable clearOnServerClose() {
        return this::clearToDecorateList;
    }

    private void clearToDecorateList() {
        if (WorldTypeRTG.chunkProvider != this) {
            Logger.debug("Cannot clear the to-decorate list.");
            return;
        }
        if (populating) return;// in process, do later;
        // we have to make a copy of the set to work on or we'll get errors
        Set<ChunkPos> toProcess = doableLocations(0);
        while (toProcess.size() > 0) {
            toProcess.forEach(this::removeFromDecorationList);
            for (ChunkPos location : toProcess) {
                doPopulate(location.chunkXPos, location.chunkZPos);
            }
            // and loop because the decorating might have created other chunks to decorate;
            toProcess = doableLocations(0);
        }
    }

    private void removeFromDecorationList(ChunkPos toAdd) {
        synchronized (toDecorate) {
            toDecorate.remove(toAdd);
        }
    }

    private void generateOres(IRealisticBiome rb, BlockPos pos) {

        int x = pos.getX();
        int z = pos.getZ();

        // Have we already generated ores for this chunk?
        if (chunkOreGenTracker.hasGeneratedOres(pos)) {
            Logger.debug("Already generated ores for %d %d", x, z);
            return;
        }

        rb.rDecorator().decorateOres(this.worldObj, this.rand, x, z);
        chunkOreGenTracker.addOreChunk(pos);
    }

    public ChunkOreGenTracker getChunkOreGenTracker() {
        return this.chunkOreGenTracker;
    }
}