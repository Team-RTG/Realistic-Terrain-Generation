package rtg.world.gen;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;

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
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
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
import rtg.api.util.ChunkOreGenTracker;
import rtg.api.util.Compass;
import rtg.api.util.Direction;
import rtg.api.util.LimitedArrayCacheMap;
import rtg.api.util.LimitedMap;
import rtg.api.util.LimitedSet;
import rtg.api.util.Logger;
import rtg.api.util.TimedHashSet;
import rtg.api.util.Valued;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.ChunkProviderSettingsRTG;
import rtg.api.world.gen.GenSettingsRepo;
import rtg.util.TimeTracker;
import rtg.world.RTGWorld;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;
import rtg.world.gen.structure.WoodlandMansionRTG;


// TODO: [Clean-up 1.12] Revisit the need of the delayed decoration system. It's likely things have improved to the point where it's more of a detriment to performance and compatibility
// TODO: [Clean-up 1.12] Clean up the Time tracker. Make time tracker strings CONSTANTS.
public class ChunkGeneratorRTG implements IChunkGenerator
{
    private static ChunkGeneratorRTG populatingProvider;
    private final ChunkProviderSettingsRTG settings;
    private final RTGConfig rtgConfig = RTGAPI.config();
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private final MapGenStronghold strongholdGenerator;
    private final WoodlandMansionRTG woodlandMansionGenerator;
    private final MapGenMineshaft mineshaftGenerator;
    private final MapGenVillage villageGenerator;
    private final MapGenScatteredFeature scatteredFeatureGenerator;
    private final StructureOceanMonument oceanMonumentGenerator;
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
// TODO: [Clean-up] Find the source of the erroneous flipping and squash it for good. This should not need to be done.
    private int [] xyinverted = analyzer.xyinverted();
    private final LandscapeGenerator landscapeGenerator;
    private final LimitedArrayCacheMap<Long, float[]> noiseCache = new LimitedArrayCacheMap<>(50);// cache the noise array for the last 50 chunks
    private final LimitedMap<ChunkPos, Chunk> availableChunks;
    private final HashSet<ChunkPos> toDecorate = new HashSet<>();
    private boolean mapFeaturesEnabled;
    private Random rand;
    private final World world;
    public final RTGWorld rtgWorld;
    private IBiomeProviderRTG biomeProvider;
    private Biome[] baseBiomesList;
    private float[] borderNoise;
    private RealisticBiomePatcher biomePatcher;
    private HashMap<ChunkPos, Chunk> inGeneration = new HashMap<>();
    private HashSet<ChunkPos> toCheck = new HashSet<>();
    private Compass compass = new Compass();
    private ArrayList<Direction> directions = compass.directions();

    //private HashSet<PlaneLocation> everGenerated = new HashSet<PlaneLocation>();
    private TimedHashSet<ChunkPos> chunkMade = new TimedHashSet<>(5 * 1000);
    private boolean populating = false;
    private boolean fakeGenerator = false;
    private LimitedSet<ChunkPos> alreadyDecorated = new LimitedSet<>(1000);
    private ChunkOreGenTracker chunkOreGenTracker = new ChunkOreGenTracker();
    private AnvilChunkLoader chunkLoader;
    private VolcanoGenerator volcanoGenerator;

    // we have to store this callback because it's a WeakReference in the event manager
    public final Consumer<ChunkEvent.Load> delayedDecorator = event -> {
        if (event.isCanceled()) { Logger.debug("DelayedDecorator: event is cancelled."); return; }
        ChunkPos pos = event.getChunk().getPos();
        if (!this.toCheck.contains(pos)) { Logger.debug("DelayedDecorator: toCheck contains pos."); return; }
        this.toCheck.remove(pos);
        for (Direction forPopulation : this.directions) { decorateIfOtherwiseSurrounded(pos, forPopulation); }
    };

    public ChunkGeneratorRTG(final World world, final long seed, final String generatorOptions) {

        Logger.debug("Instantiating CPRTG using generator settings: {}", generatorOptions);

        this.world = world;
        this.rtgWorld = new RTGWorld(this.world);
        this.settings = GenSettingsRepo.getSettingsForWorld(world);

        this.world.setSeaLevel(this.settings.seaLevel);
        this.biomeProvider = (BiomeProviderRTG) this.world.getBiomeProvider();
        this.rand = new Random(seed);
        this.landscapeGenerator = this.rtgWorld.landscapeGenerator;
        this.volcanoGenerator = new VolcanoGenerator(seed);
        this.mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();

        this.caveGenerator             =                          TerrainGen.getModdedMapGen(new MapGenCavesRTG(this.settings.caveChance, this.settings.caveDensity), EventType.CAVE);
        this.ravineGenerator           =                          TerrainGen.getModdedMapGen(new MapGenRavineRTG(this.settings.ravineChance), EventType.RAVINE);
        this.villageGenerator          = (MapGenVillage)          TerrainGen.getModdedMapGen(new MapGenVillage(StructureType.VILLAGE.getSettings(this.settings)), EventType.VILLAGE);
        this.strongholdGenerator       = (MapGenStronghold)       TerrainGen.getModdedMapGen(new MapGenStronghold(StructureType.STRONGHOLD.getSettings(this.settings)), EventType.STRONGHOLD);
        this.woodlandMansionGenerator  = (WoodlandMansionRTG)     TerrainGen.getModdedMapGen(new WoodlandMansionRTG(new FakeGeneratorForMansion(this.world), this.settings), EventType.WOODLAND_MANSION);
        this.mineshaftGenerator        = (MapGenMineshaft)        TerrainGen.getModdedMapGen(new MapGenMineshaft(StructureType.MINESHAFT.getSettings(this.settings)), EventType.MINESHAFT);
        this.scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(StructureType.TEMPLE.getSettings(this.settings)), EventType.SCATTERED_FEATURE);
        this.oceanMonumentGenerator    = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonument(StructureType.MONUMENT.getSettings(this.settings)), EventType.OCEAN_MONUMENT);

        this.sampleArraySize = this.sampleSize * 2 + 5;
        this.baseBiomesList = new Biome[256];
        this.biomePatcher = new RealisticBiomePatcher();

        // set up the cache of available chunks
        this.availableChunks = new LimitedMap<>(1000);
        this.setWeightings();

        // check for bogus world
// TODO: [Clean-up] Remove. world can never be null in this case; A valid World object will always be passed by WorldType#getChunkGenerator
        if (this.world == null) throw new RuntimeException("Attempt to create chunk provider without a world");

        Logger.debug("FINISHED instantiating CPRTG.");
    }

    private void setWeightings() {
// TODO: [Clean-up] Remove this array, it is unused
        float[][] weightings = new float[this.sampleArraySize * this.sampleArraySize][256];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                float limit = (float) Math.pow((56f * 56f), .7);
                // float limit = 56f;

                for (int mapX = 0; mapX < this.sampleArraySize; mapX++) {
                    for (int mapZ = 0; mapZ < this.sampleArraySize; mapZ++) {
                        float xDist = (i - chunkCoordinate(mapX));
                        float yDist = (j - chunkCoordinate(mapZ));
                        float distanceSquared = xDist * xDist + yDist * yDist;
                        //float distance = (float)Math.sqrt(distanceSquared);
                        float distance = (float) Math.pow(distanceSquared, .7);
                        float weight = 1f - distance / limit;
                        if (weight < 0) weight = 0;
                        weightings[mapX * this.sampleArraySize + mapZ][i * 16 + j] = weight;
                    }
                }
            }
        }
    }

    private int chunkCoordinate(int biomeMapCoordinate) {
        return (biomeMapCoordinate - this.sampleSize) * 8;
    }

    @Override
    public Chunk generateChunk(final int cx, final int cz) {
// TODO: [Clean-up] The delayed decoration system should be updated to ultilise much faster map implementations such as those found in it.unimi.dsi.fastutil to reduce world gen lag
        final ChunkPos pos = new ChunkPos(cx, cz);
        if (this.inGeneration.containsKey(pos)) return this.inGeneration.get(pos);
        if (this.chunkMade.contains(pos)) {
            Chunk available;
            available = this.availableChunks.get(pos);
            // this should never be happening but it came up when Forge/MC re-requested an already
            // made chunk for a lighting check (???)

            // we are having a problem with Forge complaining about double entity registration
            // so we'll unload any loaded entities
            if (available != null) {
                ClassInheritanceMultiMap<Entity>[] entityLists = available.getEntityLists();
                for (ClassInheritanceMultiMap<Entity> entityList : entityLists) {
                    this.world.unloadEntities(entityList);
                }
                this.toCheck.add(pos);
                return available;
            }
        }

        //if (everGenerated.contains(pos)) throw new RuntimeException();

        String rtgTerrain = "RTG Terrain";
        TimeTracker.manager.start(rtgTerrain);
        this.rand.setSeed(cx * 0x4f9939f508L + cz * 0x1ef1565bd5L);
        ChunkPrimer primer = new ChunkPrimer();
        int k;

        String landscaping = "RTG Landscape";
        TimeTracker.manager.start(landscaping);
        ChunkLandscape landscape = this.landscapeGenerator.landscape(this.biomeProvider, cx * 16, cz * 16);
        this.noiseCache.put(ChunkPos.asLong(cx, cz), landscape.noise);

        TimeTracker.manager.stop(landscaping);

        String fill = "RTG Fill";
        TimeTracker.manager.start(fill);
        generateTerrain(primer, landscape.noise);
        TimeTracker.manager.stop(fill);
        // that routine can change the blocks.
        //get standard biome Data
// TODO: [Generator settings] Update to use the generator setting
//      if (settings.useVolcanos) {
        String volcanos = "Volcanos";
        TimeTracker.manager.start(volcanos);
        for (k = 0; k < 256; k++) {

            try {
                this.baseBiomesList[k] = landscape.biome[k].baseBiome();
            }
            catch (Exception ignore) {
                this.baseBiomesList[k] = this.biomePatcher.getPatchedBaseBiome("" + Biome.getIdForBiome(landscape.biome[k].baseBiome()));
            }
        }
        volcanoGenerator.generateMapGen(primer, this.world.getSeed(), this.world, this.biomeProvider, this.rand, cx, cz, this.rtgWorld.simplex, this.rtgWorld.cell, landscape.noise);
        TimeTracker.manager.stop(volcanos);
//      }

        String replace = "RTG Replace";
        TimeTracker.manager.start(replace);

        this.borderNoise = this.landscapeGenerator.noiseFor(this.biomeProvider, cx * 16, cz * 16);

        IRealisticBiome[] jitteredBiomes = new IRealisticBiome[256];

        IRealisticBiome jittered, actual;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.rtgWorld.simplex.evaluateNoise(cx * 16 + i, cz * 16 + j, this.rtgWorld.surfaceJitter);
                int pX = (int) Math.round(cx * 16 + i + this.rtgWorld.surfaceJitter.deltax() * this.rtgConfig.SURFACE_BLEED_RADIUS.get());
                int pZ = (int) Math.round(cz * 16 + j + this.rtgWorld.surfaceJitter.deltay() * this.rtgConfig.SURFACE_BLEED_RADIUS.get());
//                // These wont work, since they are pre-repair
//                actual = biomeProvider.getBiomeDataAt(cx * 16 + i, cz * 16 + j);
//                jittered = biomeProvider.getBiomeDataAt(pX, pZ);
                actual = RealisticBiomeBase.getBiome(this.rtgWorld.getRepairedBiomeAt(this.biomeProvider, cx * 16 + i, cz * 16 + j));
                jittered = RealisticBiomeBase.getBiome(this.rtgWorld.getRepairedBiomeAt(this.biomeProvider, pX, pZ));
                jitteredBiomes[i * 16 + j] = (actual.getConfig().SURFACE_BLEED_IN.get() && jittered.getConfig().SURFACE_BLEED_OUT.get()) ? jittered : actual;
            }
        }

        replaceBiomeBlocks(cx, cz, primer, jitteredBiomes, this.baseBiomesList, landscape.noise);

        TimeTracker.manager.stop(replace);

        String features = "Vanilla Features";
        TimeTracker.manager.start(features);

        if (this.settings.useCaves)      { this.caveGenerator.generate(this.world, cx, cz, primer); }
        if (this.settings.useRavines)    { this.ravineGenerator.generate(this.world, cx, cz, primer); }
        if (this.mapFeaturesEnabled) {
            if (settings.useMineShafts)  { this.mineshaftGenerator.generate(this.world, cx, cz, primer); }
            if (settings.useStrongholds) { this.strongholdGenerator.generate(this.world, cx, cz, primer); }
            if (settings.useVillages)    { this.villageGenerator.generate(this.world, cx, cz, primer); }
            if (settings.useTemples)     { this.scatteredFeatureGenerator.generate(this.world, cx, cz, primer); }
            if (settings.useMonuments)   { this.oceanMonumentGenerator.generate(this.world, cx, cz, primer); }
            if (settings.useMansions)    { this.woodlandMansionGenerator.generate(this.world, cx, cz, primer); }
        }

        TimeTracker.manager.stop(features);

        String housekeeping = "Terrain Housekeeping";
        TimeTracker.manager.start(housekeeping);

        // store in the in process pile
        Chunk chunk = new Chunk(this.world, primer, cx, cz);
        this.inGeneration.put(pos, chunk);

        // doJitter no longer needed as the biome array gets fixed

        byte[] abyte1 = chunk.getBiomeArray();
        for (k = 0; k < abyte1.length; ++k) {
            // Biomes are y-first and terrain x-first
            byte b = (byte) Biome.getIdForBiome(this.baseBiomesList[this.xyinverted[k]]);
            abyte1[k] = b;
        }
        chunk.setBiomeArray(abyte1);

        chunk.generateSkylightMap();
        this.toCheck.add(pos);

        // remove from in process pile
        this.inGeneration.remove(pos);
        this.chunkMade.add(pos);
        //this.everGenerated.add(pos);
        /*if (!chunkMade.contains(pos)||!everGenerated.contains(pos)) {
            throw new RuntimeException(pos.toString() +  chunkMade.size());
        }*/
        this.availableChunks.put(pos, chunk);
        TimeTracker.manager.stop(housekeeping);
        TimeTracker.manager.stop(rtgTerrain);
        return chunk;
    }

    private void generateTerrain(ChunkPrimer primer, float[] noise) {

        int height;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                height = (int) noise[x * 16 + z];

                for (int y = 0; y < 256; y++) {
                    if (y > height) {
                        if (y < this.settings.seaLevel) {
                            primer.setBlockState(x, y, z, Blocks.WATER.getDefaultState());
                        }
                        else {
                            primer.setBlockState(x, y, z, Blocks.AIR.getDefaultState());
                        }
                    }
                    else {
                        primer.setBlockState(x, y, z, Blocks.STONE.getDefaultState());
                    }
                }
            }
        }
    }

    private void replaceBiomeBlocks(int cx, int cz, ChunkPrimer primer, IRealisticBiome[] biomes, Biome[] base, float[] n) {

        ChunkGeneratorEvent.ReplaceBiomeBlocks event = new ChunkGeneratorEvent.ReplaceBiomeBlocks(this, cx, cz, primer, this.world);
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
                river = -this.biomeProvider.getRiverStrength(cx * 16 + i, cz * 16 + j);
                depth = -1;

                if (this.rtgWorld.organicBiomeGenerator.isOrganicBiome(Biome.getIdForBiome(biome.baseBiome()))) {

                    this.rtgWorld.organicBiomeGenerator.organicSurface(cx * 16 + i, cz * 16 + j, primer, biome.baseBiome());
                }
                else {

                    biome.rReplace(primer, cx * 16 + i, cz * 16 + j, i, j, depth, this.rtgWorld, n, river, base);
                }

                // sparse bedrock layers above y=0
                if (this.settings.bedrockLayers > 1) {
                    for (int bl = 9; bl >= 0; --bl) {
                        if (bl <= this.rand.nextInt(this.settings.bedrockLayers)) {
                            primer.setBlockState(i, bl, j, Blocks.BEDROCK.getDefaultState());
                        }
                    }
                }
                else {
                    primer.setBlockState(i, 0, j, Blocks.BEDROCK.getDefaultState());
                }
            }
        }
    }

    @Override
    public void populate(int x, int z) {
        // check if this is the master provider
// TODO: [Clean-up] Remove fake generator functionality as Farseek v2.0+ no longer aquires a 'fake' chunk generator instance from CPRTG
        if (this.fakeGenerator) return;
        //if (this.alreadyDecorated.contains(new PlaneLocation.Invariant(chunkX, chunkZ))) return;
        if (this.neighborsDone(x, z)) {
            this.doPopulate(x, z);
        }
        clearDecorations(0);
    }

    private boolean neighborsDone(int cx, int cz) {
        return chunkExists(cx - 1, cz - 1)
            && chunkExists(cx - 1, cz)
            && chunkExists(cx - 1, cz + 1)
            && chunkExists(cx, cz - 1)
            && chunkExists(cx, cz + 1)
            && chunkExists(cx + 1, cz - 1)
            && chunkExists(cx + 1, cz)
            && chunkExists(cx + 1, cz + 1);
    }

    private void doPopulate(int chunkX, int chunkZ) {
        // don't populate if already done

        ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        //Logger.debug("trying to decorate: " + chunkPos.toString());
        if (this.alreadyDecorated.contains(chunkPos)) return;

        if (this.populating) {
            // this has been created by another decoration; put in to-do pile
            addToDecorationList(chunkPos);
            return;
        }

        if (populatingProvider != null) throw new RuntimeException(toString() + " " + populatingProvider.toString());

        if (this.inGeneration.containsKey(chunkPos)) {
            addToDecorationList(chunkPos);
            return;
        }

        //Logger.debug("decorating");
        this.alreadyDecorated.add(chunkPos);
        this.populating = true;
        populatingProvider = this;

        TimeTracker.manager.start("RTG populate");
        TimeTracker.manager.start("Features");
        BlockFalling.fallInstantly = true;

        int worldX = chunkX * 16;
        int worldZ = chunkZ * 16;
        TimeTracker.manager.start("Biome Layout");

        //Flippy McFlipperson.
        IRealisticBiome biome = this.biomeProvider.getBiomeDataAt(worldX + 16, worldZ + 16);
        //Logger.debug("CPRTG#doPopulate: %s at %d %d", biome.baseBiome.getBiomeName(), worldX + 16, worldZ + 16);

        TimeTracker.manager.stop("Biome Layout");
        this.rand.setSeed(this.world.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.world.getSeed());
        boolean gennedVillage = false;

        ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);

        if (this.mapFeaturesEnabled) {
            if (settings.useMineShafts)  { mineshaftGenerator.generateStructure(world, rand, chunkPos); }
            if (settings.useStrongholds) { strongholdGenerator.generateStructure(world, rand, chunkPos); }
            if (settings.useVillages)    { gennedVillage = villageGenerator.generateStructure(world, rand, chunkPos); }
            if (settings.useTemples)     { scatteredFeatureGenerator.generateStructure(world, rand, chunkPos); }
            if (settings.useMonuments)   { oceanMonumentGenerator.generateStructure(this.world, rand, chunkPos); }
        }

        TimeTracker.manager.start("Pools");
        biome.rDecorator().rPopulatePreDecorate(this, this.world, this.rand, this.settings, chunkX, chunkZ, gennedVillage);
        TimeTracker.manager.stop("Pools");

        /*
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno.
         */

        this.borderNoise = this.landscapeGenerator.noiseFor(this.biomeProvider, worldX, worldZ);

        /*
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Decorations");
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(this.world, this.rand, new BlockPos(worldX, 0, worldZ)));

        Logger.debug("DecorateBiomeEvent.Pre (%d %d)", worldX, worldZ);

        // Ore gen.
// TODO: [1.12] CRITICAL - Ore generation needs to be moved to the biome decorator.
        this.generateOres(biome, this.settings, new BlockPos(worldX, 0, worldZ));

        //Initialise variables.
        float river = -this.biomeProvider.getRiverStrength(worldX + 16, worldZ + 16);

        //Border noise. (Does this have to be done here? - Pink)
        RealisticBiomeBase realisticBiome;

        TreeSet<Valued<RealisticBiomeBase>> activeBiomes = new TreeSet<>();
        for (int bn = 0; bn < 256; bn++) {
            if (this.borderNoise[bn] > 0f) {
                if (this.borderNoise[bn] >= 1f) this.borderNoise[bn] = 1f;

                realisticBiome = RealisticBiomeBase.getBiome(bn);

                // Do we need to patch the biome?
                if (realisticBiome == null) {
                    realisticBiome = this.biomePatcher.getPatchedRealisticBiome(
                        "NULL biome (" + bn + ") found when generating border noise.");
                }
                activeBiomes.add(new Valued<>(this.borderNoise[bn],realisticBiome));

                this.borderNoise[bn] = 0f;
            }
        }

        // for basebiomedeco interference: run the biomes in reverse order of influence
        for (Valued<RealisticBiomeBase> biomeInfluence: activeBiomes.descendingSet()) {
            realisticBiome = biomeInfluence.item();
            float borderNoise = (float)biomeInfluence.value();

            if (this.rtgConfig.DISABLE_RTG_BIOME_DECORATIONS.get() || realisticBiome.getConfig().DISABLE_RTG_DECORATIONS.get()) {

                realisticBiome.baseBiome.decorate(this.world, this.rand, new BlockPos(worldX, 0, worldZ));
            }
            else {

                realisticBiome.rDecorate(this.rtgWorld, this.rand, worldX, worldZ, borderNoise, river, gennedVillage);
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(this.world, this.rand, new BlockPos(worldX, 0, worldZ)));

        Logger.debug("DecorateBiomeEvent.Post (%d %d)", worldX, worldZ);

        TimeTracker.manager.stop("Decorations");

        /*
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Post-decorations");
        biome.rDecorator().rPopulatePostDecorate(this.world, this.rand, this.settings, chunkX, chunkZ, gennedVillage);
        TimeTracker.manager.stop("Post-decorations");

        TimeTracker.manager.start("Entities");
        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, gennedVillage, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.world, biome.baseBiome(), worldX + 8, worldZ + 8, 16, 16, this.rand);
        }
        TimeTracker.manager.stop("Entities");

        TimeTracker.manager.start("Ice");
        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, gennedVillage, PopulateChunkEvent.Populate.EventType.ICE)) {

            int i4, j4;
            IBlockState snowLayerBlock = Blocks.SNOW_LAYER.getDefaultState();
            IBlockState iceBlock = Blocks.ICE.getDefaultState();

            for (i4 = 0; i4 < 16; ++i4) {

                for (j4 = 0; j4 < 16; ++j4) {

                    BlockPos snowPos = this.world.getPrecipitationHeight(new BlockPos(worldX + i4, 0, worldZ + j4));
                    BlockPos icePos = snowPos.down();

                    // Ice.
                    if(this.world.canBlockFreezeWater(icePos)) {
                        this.world.setBlockState(icePos, iceBlock, 2);
                    }

                    // Snow.
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//                  if (settings.useSnowLayers && this.world.canSnowAt(snowPos, true)) {
                    if (rtgConfig.ENABLE_SNOW_LAYERS.get() && this.world.canSnowAt(snowPos, true)) {
                        this.world.setBlockState(snowPos, snowLayerBlock, 2);
                    }
                }
            }
        }
        TimeTracker.manager.stop("Ice");

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, chunkX, chunkZ, gennedVillage);

        BlockFalling.fallInstantly = false;
        TimeTracker.manager.stop("RTG populate");
        this.populating = false;
        populatingProvider = null;
    }

// TODO: [Clean-up] Remove constant condition parameter `limit`
    private void clearDecorations(int limit) {
        if (WorldTypeRTG.chunkProvider != this) return;
        Set<ChunkPos> toProcess = doableLocations(limit);
        toProcess.forEach(this::removeFromDecorationList);
        for (ChunkPos location : toProcess) {
            doPopulate(location.x, location.z);
        }
    }

    private Set<ChunkPos> doableLocations(int limit) {
        HashSet<ChunkPos> toProcess = new HashSet<>();
        int found = 0;
        synchronized (this.toDecorate) {
            for (ChunkPos pos : this.toDecorate) {
            /*
                Chunk existing;
                existing = availableChunks.get(pos);
                if (existing != null) {
                    if (!existing.isTerrainPopulated()) {
                        //continue; // not populated so let more "normal" systems handle it
                    }
                }
            */
                if (this.inGeneration.containsKey(pos)) continue;
                toProcess.add(pos);
                if (++found == limit) return toProcess;
            }
        }
        return toProcess;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        boolean flag = false;
        if (settings.useMonuments && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L) {
            flag = this.oceanMonumentGenerator.generateStructure(this.world, this.rand, new ChunkPos(x, z));
        }
        return flag;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);
        if (this.mapFeaturesEnabled) {
            if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.isSwampHut(pos)) {
                return this.scatteredFeatureGenerator.getMonsters();
            }
            if (creatureType == EnumCreatureType.MONSTER && settings.useMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.world, pos)) {
                return this.oceanMonumentGenerator.getMonsters();
            }
        }
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        if (!this.mapFeaturesEnabled) { return null; }
        if ("Stronghold".equals(structureName) && this.strongholdGenerator != null)       { return this.strongholdGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Mansion".equals(structureName)    && this.woodlandMansionGenerator != null)  { return this.woodlandMansionGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Monument".equals(structureName)   && this.oceanMonumentGenerator != null)    { return this.oceanMonumentGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Village".equals(structureName)    && this.villageGenerator != null)          { return this.villageGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Mineshaft".equals(structureName)  && this.mineshaftGenerator != null)        { return this.mineshaftGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Temple".equals(structureName)     && this.scatteredFeatureGenerator != null) { this.scatteredFeatureGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void recreateStructures(Chunk chunk, int cx, int cz) {
        if (this.mapFeaturesEnabled) {
            if (this.settings.useMineShafts)  { this.mineshaftGenerator.generate(this.world, cx, cz, null); }
            if (this.settings.useVillages)    { this.villageGenerator.generate(this.world, cx, cz, null); }
            if (this.settings.useStrongholds) { this.strongholdGenerator.generate(this.world, cx, cz, null); }
            if (this.settings.useTemples)     { this.scatteredFeatureGenerator.generate(this.world, cx, cz, null); }
            if (this.settings.useMonuments)   { this.oceanMonumentGenerator.generate(this.world, cx, cz, null); }
            if (this.settings.useMansions)    { this.woodlandMansionGenerator.generate(this.world, cx, cz, null);}
        }
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        if (!this.mapFeaturesEnabled) { return false; }
        if ("Stronghold".equals(structureName) && this.strongholdGenerator != null) { return this.strongholdGenerator.isInsideStructure(pos); }
        if ("Mansion".equals(structureName)    && this.woodlandMansionGenerator != null) { return this.woodlandMansionGenerator.isInsideStructure(pos); }
        if ("Monument".equals(structureName)   && this.oceanMonumentGenerator != null) { return this.oceanMonumentGenerator.isInsideStructure(pos); }
        if ("Village".equals(structureName)    && this.villageGenerator != null) { return this.villageGenerator.isInsideStructure(pos); }
        if ("Mineshaft".equals(structureName)  && this.mineshaftGenerator != null) { return this.mineshaftGenerator.isInsideStructure(pos); }
        return ("Temple".equals(structureName) && this.scatteredFeatureGenerator != null) && this.scatteredFeatureGenerator.isInsideStructure(pos);
    }

    private void decorateIfOtherwiseSurrounded(ChunkPos pos, Direction fromNewChunk) {

        // check if this is the master provider
        if (WorldTypeRTG.chunkProvider != this) {
            Logger.debug("Cannot decorate-if-otherwise-surrounded.");
            return;
        }

        // see if otherwise surrounded besides the new chunk
        ChunkPos probe = new ChunkPos(pos.x + fromNewChunk.xOffset, pos.z + fromNewChunk.zOffset);

        // check to see if already decorated; shouldn't be but just in case
        if (this.alreadyDecorated.contains(probe)) {
            Logger.debug("Already decorated (%d %d).", pos.x, pos.z);
            return;
        }

        // if an in-process chunk; we'll get a populate call later;
        // if (this.inGeneration.containsKey(probe)) return;

        for (Direction checked : this.directions) {

            if (checked == this.compass.opposite(fromNewChunk)) {
                Logger.debug("Chunk checked (%d %d). Continuing...", pos.x, pos.z);
                continue; // that's the new chunk
            }

            if (!chunkExists(probe.x + checked.xOffset, probe.z + checked.zOffset)) {
                Logger.debug("Chunk doesn't exist (%d %d). Returning...", pos.x, pos.z);
                return;// that one's missing
            }
        }

        // passed all checks
        addToDecorationList(probe);

        Logger.debug("Chunk added to decoration list (%d %d).", probe.x, probe.z);

        //this.doPopulate(probe.x, probe.z);
    }

    private boolean chunkExists(int cx, int cz) {
        ChunkPos location = new ChunkPos(cx, cz);
        return this.inGeneration.containsKey(location)
            || this.toCheck.contains(location)
            || this.chunkMade.contains(location)
            || chunkLoader().isChunkGeneratedAt(cx, cz);
    }

    private void addToDecorationList(ChunkPos toAdd) {
        synchronized (this.toDecorate) {
            this.toDecorate.add(toAdd);
        }
    }

    private AnvilChunkLoader chunkLoader() {
        if (this.chunkLoader == null) {
            ChunkProviderServer server = (ChunkProviderServer) (this.world.getChunkProvider());
            this.chunkLoader = (AnvilChunkLoader) (server.chunkLoader);
        }
        return this.chunkLoader;
    }

    public Runnable clearOnServerClose() {
        return this::clearToDecorateList;
    }

    private void clearToDecorateList() {
        if (WorldTypeRTG.chunkProvider != this) {
            Logger.debug("Cannot clear the to-decorate list.");
            return;
        }
        if (this.populating) return;// in process, do later;
        // we have to make a copy of the set to work on or we'll get errors
        Set<ChunkPos> toProcess = doableLocations(0);
        while (toProcess.size() > 0) {
            toProcess.forEach(this::removeFromDecorationList);
            for (ChunkPos location : toProcess) {
                doPopulate(location.x, location.z);
            }
            // and loop because the decorating might have created other chunks to decorate;
            toProcess = doableLocations(0);
        }
    }

    private void removeFromDecorationList(ChunkPos toAdd) {
        synchronized (this.toDecorate) {
            this.toDecorate.remove(toAdd);
        }
    }

// TODO: [1.12] CRITICAL - Ore generation needs to be moved to the biome decorator.
    private void generateOres(IRealisticBiome rBiome, ChunkProviderSettingsRTG settings, BlockPos pos) {

        // Have we already generated ores for this chunk?
        if (this.chunkOreGenTracker.hasGeneratedOres(pos)) {
            Logger.debug("Already generated ores for chunk @ x:{} z:{}", pos.getX(), pos.getZ());
            return;
        }

        rBiome.rDecorator().decorateOres(this.world, this.rand, settings, pos);
        this.chunkOreGenTracker.addOreChunk(pos);
    }

    public ChunkOreGenTracker getChunkOreGenTracker() {
        return this.chunkOreGenTracker;
    }

    // A helper class to generate settings maps to configure the vanilla structure classes
    private enum StructureType {

        MINESHAFT, MONUMENT, STRONGHOLD, TEMPLE, VILLAGE;

        Map<String, String> getSettings(ChunkProviderSettingsRTG settings) {

            Map<String, String> ret = new HashMap<>();

            if (this == MINESHAFT) {
                ret.put("chance", String.valueOf(settings.mineShaftChance));
                return ret;
            }

            if (this == MONUMENT) {
                ret.put("separation", String.valueOf(settings.monumentSeparation));
                ret.put("spacing",    String.valueOf(settings.monumentSpacing));
                return ret;
            }

            if (this == STRONGHOLD) {
                ret.put("count",    String.valueOf(settings.strongholdCount));
                ret.put("distance", String.valueOf(settings.strongholdDistance));
                ret.put("spread",   String.valueOf(settings.strongholdSpread));
                return ret;
            }

            if (this == TEMPLE) {
                ret.put("distance", String.valueOf(settings.templeDistance));
                return ret;
            }

            if (this == VILLAGE) {
                ret.put("distance", String.valueOf(settings.villageDistance));
                ret.put("size",     String.valueOf(settings.villageSize));
                return ret;
            }

            return ret;
        }
    }

    private final class FakeGeneratorForMansion extends ChunkGeneratorOverworld {

        private FakeGeneratorForMansion(World world) {
            super(
                world,
                world.getSeed(),
                world.getWorldInfo().isMapFeaturesEnabled(),
                world.getWorldInfo().getGeneratorOptions()
            );
        }

        @Override public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer) {
            float[] noise = ChunkGeneratorRTG.this.noiseCache.get(ChunkPos.asLong(chunkX, chunkZ));
            if (noise == null) {
                ChunkLandscape landscape = new ChunkLandscape();
                ChunkGeneratorRTG.this.landscapeGenerator.getNewerNoise(ChunkGeneratorRTG.this.biomeProvider, chunkX, chunkZ, landscape);
                noise = landscape.noise;
            }
            ChunkGeneratorRTG.this.generateTerrain(primer, noise);
        }
    }
}
