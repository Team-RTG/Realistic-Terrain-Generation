package rtg.world.gen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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
import net.minecraft.world.gen.ChunkGeneratorOverworld;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraft.world.gen.structure.WoodlandMansion;

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
import rtg.api.util.Acceptor;
import rtg.api.util.ChunkOreGenTracker;
import rtg.api.util.Compass;
import rtg.api.util.Direction;
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
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenStrongholdRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import rtg.world.gen.structure.StructureOceanMonumentRTG;


@SuppressWarnings({"UnusedParameters", "deprecation"})
// TODO: [Clean-up 1.12] Revisit the need of the delayed decoration system. It's likely things have improved to the point where it's more of a detriment to performance and compatibility
// TODO: [Clean-up 1.12] Clean up the Time tracker. Make time tracker strings CONSTANTS.
public class ChunkProviderRTG implements IChunkGenerator
{
    private static ChunkProviderRTG populatingProvider;
    private final ChunkProviderSettingsRTG settings;
    private final RTGConfig rtgConfig = RTGAPI.config();
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private final MapGenStronghold strongholdGenerator;
    private final WoodlandMansion woodlandMansionGenerator;
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
    public final Acceptor<ChunkEvent.Load> delayedDecorator = new Acceptor<ChunkEvent.Load>() {
        @Override
        public void accept(ChunkEvent.Load event) {

            if (event.isCanceled()) {
                Logger.debug("CPRTG#Acceptor: event is cancelled.");
                return;
            }

            ChunkPos pos = event.getChunk().getPos();

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

    public ChunkProviderRTG(final World world, final long seed, final String generatorOptions) {

        Logger.debug("STARTED instantiating CPRTG.");

        this.world = world;
        rtgWorld = new RTGWorld(this.world);
        this.settings = GenSettingsRepo.getSettingsForWorld(world);

        this.world.setSeaLevel(settings.seaLevel);
// TODO: [Clean-up] No need to cast here as it is stored in biomeProvider as a IBiomeProvider object and BiomeProviderRTG has no custom functionality
        biomeProvider = (BiomeProviderRTG) this.world.getBiomeProvider();
        rand = new Random(seed);
        landscapeGenerator = rtgWorld.landscapeGenerator;
        volcanoGenerator = new VolcanoGenerator(seed);
// TODO: [Generator settings] Remove this map, No longer needed.
        Map<String, String> m = new HashMap<>();
        m.put("size", "0");
        m.put("distance", "24");
        mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();
        boolean isRTGWorld = DimensionManagerRTG.isValidDimension(world.provider.getDimension());

// TODO: [Generator Settings] Use only MapGenCavesRTG() to generate caves using generator settings. Replace if-else
//      caveGenerator = TerrainGen.getModdedMapGen(new MapGenCavesRTG(settings.caveChance, settings.caveDensity), EventType.CAVE);
        if (isRTGWorld && rtgConfig.ENABLE_CAVE_MODIFICATIONS.get()) {
            caveGenerator = (MapGenCaves) TerrainGen.getModdedMapGen(new MapGenCavesRTG(), EventType.CAVE);
        }
        else {
            caveGenerator = (MapGenCaves) TerrainGen.getModdedMapGen(new MapGenCaves(), EventType.CAVE);
        }

// TODO: [Generator Settings] Use only MapGenRavineRTG() to generate ravines using generator settings. Replace if-else
//      ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavineRTG(settings.ravineChance), EventType.RAVINE);
        if (isRTGWorld && rtgConfig.ENABLE_RAVINE_MODIFICATIONS.get()) {
            ravineGenerator = (MapGenRavine) TerrainGen.getModdedMapGen(new MapGenRavineRTG(), EventType.RAVINE);
        }
        else {
            ravineGenerator = (MapGenRavine) TerrainGen.getModdedMapGen(new MapGenRavine(), EventType.RAVINE);
        }

// TODO: [Generator settings] Replace the RTG custom village class with a call to the vanilla class. Replace if-else
//      this.villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(StructureType.VILLAGE.getSettings(settings)), EventType.VILLAGE);
        if (isRTGWorld && rtgConfig.ENABLE_VILLAGE_MODIFICATIONS.get()) {
            villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillageRTG(), EventType.VILLAGE);
        }
        else {
            villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(m), EventType.VILLAGE);
        }

// TODO: [Generator settings] Replace the RTG custom stronghold class with a call to the vanilla class. Replace if-else
//      this.strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(StructureType.STRONGHOLD.getSettings(settings)), EventType.STRONGHOLD);
        if (isRTGWorld && rtgConfig.ENABLE_STRONGHOLD_MODIFICATIONS.get()) {
            strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStrongholdRTG(), EventType.STRONGHOLD);
        }
        else {
            strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(), EventType.STRONGHOLD);
        }

        this.woodlandMansionGenerator = (WoodlandMansion) TerrainGen.getModdedMapGen(new WoodlandMansion(new FakeGeneratorForMansion(this.world)), EventType.WOODLAND_MANSION);

// TODO: [Generator settings] Pass settings to mineshafts.
//      mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(StructureType.MINESHAFT.getSettings(settings)), EventType.MINESHAFT);
        mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(), EventType.MINESHAFT);

// TODO: [Generator settings] Replace the RTG custom scattered features class with a call to the vanilla class. Replace if-else
//      scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(StructureType.TEMPLE.getSettings(settings)), EventType.SCATTERED_FEATURE);
        if (isRTGWorld && rtgConfig.ENABLE_SCATTERED_FEATURE_MODIFICATIONS.get()) {
            scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeatureRTG(), EventType.SCATTERED_FEATURE);
        }
        else {
            scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(), EventType.SCATTERED_FEATURE);
        }

// TODO: [Generator settings] Replace the RTG custom monument class with a call to the vanilla class. Replace if-else
//      oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonument(StructureType.MONUMENT.getSettings(settings)), EventType.OCEAN_MONUMENT);
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
// TODO: [Clean-up] Remove. world can never be null in this case; A valid World object will always be passed by WorldType#getChunkGenerator
        if (this.world == null) throw new RuntimeException("Attempt to create chunk provider without a world");

        Logger.debug("FINISHED instantiating CPRTG.");
    }

    private void setWeightings() {
// TODO: [Clean-up] Remove this array, it is unused
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

    // Streams compatibility
// TODO: [Clean-up] Remove fake generator functionality as Farseek v2.0+ no longer aquires a 'fake' chunk generator instance from CPRTG
    public void isFakeGenerator() {

        this.fakeGenerator = true;
        this.mapFeaturesEnabled = false;
    }

    @Override
    public Chunk generateChunk(final int cx, final int cz) {
// TODO: [Clean-up] The delayed decoration system should be updated to ultilise much faster map implementations such as those found in it.unimi.dsi.fastutil to reduce world gen lag
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
                    world.unloadEntities(entityList);
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
        ChunkLandscape landscape = landscapeGenerator.landscape(biomeProvider, cx * 16, cz * 16);

        TimeTracker.manager.stop(landscaping);

        String fill = "RTG Fill";
        TimeTracker.manager.start(fill);
        generateTerrain(biomeProvider, cx, cz, primer, landscape.biome, landscape.noise);
        TimeTracker.manager.stop(fill);
        // that routine can change the blocks.
        //get standard biome Data
// TODO: [Generator settings] Update to use the generator setting
//      if (settings.useVolcanos) {
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
// TODO: [Clean-up] Update to get the seed from the world object, and use the existing Random instance
//      volcanoGenerator.generateMapGen(primer, world.getSeed(), world, biomeProvider, rand, cx, cz, rtgWorld.simplex, rtgWorld.cell, landscape.noise);
        volcanoGenerator.generateMapGen(primer, world.getSeed(), world, biomeProvider, rand, cx, cz, rtgWorld.simplex, rtgWorld.cell, landscape.noise);
        TimeTracker.manager.stop(volcanos);
//      }

        String replace = "RTG Replace";
        TimeTracker.manager.start(replace);

        borderNoise = landscapeGenerator.noiseFor(biomeProvider, cx * 16, cz * 16);

        IRealisticBiome[] jitteredBiomes = new IRealisticBiome[256];

        IRealisticBiome jittered, actual;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                rtgWorld.simplex.evaluateNoise(cx * 16 + i, cz * 16 + j, rtgWorld.surfaceJitter);
                int pX = (int) Math.round(cx * 16 + i + rtgWorld.surfaceJitter.deltax() * rtgConfig.SURFACE_BLEED_RADIUS.get());
                int pZ = (int) Math.round(cz * 16 + j + rtgWorld.surfaceJitter.deltay() * rtgConfig.SURFACE_BLEED_RADIUS.get());
//                // These wont work, since they are pre-repair
//                actual = biomeProvider.getBiomeDataAt(cx * 16 + i, cz * 16 + j);
//                jittered = biomeProvider.getBiomeDataAt(pX, pZ);
                actual = RealisticBiomeBase.getBiome(rtgWorld.getRepairedBiomeAt(biomeProvider, cx * 16 + i, cz * 16 + j));
                jittered = RealisticBiomeBase.getBiome(rtgWorld.getRepairedBiomeAt(biomeProvider, pX, pZ));
                jitteredBiomes[i * 16 + j] = (actual.getConfig().SURFACE_BLEED_IN.get() && jittered.getConfig().SURFACE_BLEED_OUT.get()) ? jittered : actual;
            }
        }

        replaceBiomeBlocks(cx, cz, primer, jitteredBiomes, baseBiomesList, landscape.noise);

        TimeTracker.manager.stop(replace);

        String features = "Vanilla Features";
        TimeTracker.manager.start(features);
// TODO: [Generator settings] Update to use a generator settings check here
//      if (settings.useCaves) {
        caveGenerator.generate(world, cx, cz, primer);
//      }

//      if (settings.useRavines) {
        ravineGenerator.generate(world, cx, cz, primer);
//      }

        if (mapFeaturesEnabled) {
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useMineShafts) {
            if (rtgConfig.GENERATE_MINESHAFTS.get()) {
                try {
                    mineshaftGenerator.generate(this.world, cx, cz, primer);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useStrongholds) {
            if (rtgConfig.GENERATE_STRONGHOLDS.get()) {
                try {
                    strongholdGenerator.generate(this.world, cx, cz, primer);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useVillages) {
            if (rtgConfig.GENERATE_VILLAGES.get()) {
                try {
                    villageGenerator.generate(this.world, cx, cz, primer);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useTemples) {
            if (rtgConfig.GENERATE_SCATTERED_FEATURES.get()) {
                try {
                    scatteredFeatureGenerator.generate(this.world, cx, cz, primer);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useMonuments) {
            if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get()) {
                try {
                    oceanMonumentGenerator.generate(this.world, cx, cz, primer);
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
// TODO: [Generator Settings] Add this in 1.11+
//          if (settings.useMansions) {
//              try {
//                  mansionGenerator.generate(/*params*/);
//              }
//              catch (Exception e) {
//                  if (rtgConfig.CRASH_ON_STRUCTURE_EXCEPTIONS.get()) {
//                      Logger.fatal("Exception in mansionGenerator");
//                      throw new RuntimeException(e.getMessage());
//                  }
//                  else {
//                      Logger.error("Exception in mansionGenerator");
//                      e.printStackTrace();
//                  }
//              }
//          }
        }

        TimeTracker.manager.stop(features);

        String housekeeping = "Terrain Housekeeping";
        TimeTracker.manager.start(housekeeping);

        // store in the in process pile
        Chunk chunk = new Chunk(this.world, primer, cx, cz);
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

    private void generateTerrain(IBiomeProviderRTG cmr, int cx, int cz, ChunkPrimer primer, IRealisticBiome biomes[], float[] noise) {

        int h;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                h = (int) noise[i * 16 + j];

                for (int k = 0; k < 256; k++) {
                    if (k > h) {
                        if (k < settings.seaLevel) {
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
                river = -biomeProvider.getRiverStrength(cx * 16 + i, cz * 16 + j);
                depth = -1;

                if (rtgWorld.organicBiomeGenerator.isOrganicBiome(Biome.getIdForBiome(biome.baseBiome()))) {

                    rtgWorld.organicBiomeGenerator.organicSurface(cx * 16 + i, cz * 16 + j, primer, biome.baseBiome());
                }
                else {

                    biome.rReplace(primer, cx * 16 + i, cz * 16 + j, i, j, depth, rtgWorld, n, river, base);
                }

                // sparse bedrock layers above y=0
                if (settings.bedrockLayers > 1) {
                    for (int bl = 9; bl >= 0; --bl) {
                        if (bl <= this.rand.nextInt(settings.bedrockLayers)) {
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
// TODO: [Clean-up] wrap long line
        return chunkExists(true, cx - 1, cz - 1)
            && chunkExists(true, cx - 1, cz)
            && chunkExists(true, cx - 1, cz + 1)
            && chunkExists(true, cx, cz - 1)
            && chunkExists(true, cx, cz + 1)
            && chunkExists(true, cx + 1, cz - 1)
            && chunkExists(true, cx + 1, cz)
            && chunkExists(true, cx + 1, cz + 1);
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
        IRealisticBiome biome = biomeProvider.getBiomeDataAt(worldX + 16, worldZ + 16);
        //Logger.debug("CPRTG#doPopulate: %s at %d %d", biome.baseBiome.getBiomeName(), worldX + 16, worldZ + 16);

        TimeTracker.manager.stop("Biome Layout");
        this.rand.setSeed(this.world.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.world.getSeed());
        boolean hasPlacedVillageBlocks = false;

        ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);

        if (mapFeaturesEnabled) {

            TimeTracker.manager.start("Mineshafts");
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useMineShafts) {
            if (rtgConfig.GENERATE_MINESHAFTS.get()) {
                try {
                    mineshaftGenerator.generateStructure(world, rand, chunkPos);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useStrongholds) {
            if (rtgConfig.GENERATE_STRONGHOLDS.get()) {
                try {
                    strongholdGenerator.generateStructure(world, rand, chunkPos);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useVillages) {
            if (rtgConfig.GENERATE_VILLAGES.get()) {
                try {
                    hasPlacedVillageBlocks = villageGenerator.generateStructure(world, rand, chunkPos);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useTemples) {
            if (rtgConfig.GENERATE_SCATTERED_FEATURES.get()) {
                try {
                    scatteredFeatureGenerator.generateStructure(world, rand, chunkPos);
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useMonuments) {
            if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get()) {
                try {
                    oceanMonumentGenerator.generateStructure(this.world, rand, chunkPos);
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
        biome.rDecorator().rPopulatePreDecorate(this, world, rand, settings, chunkX, chunkZ, hasPlacedVillageBlocks);
        TimeTracker.manager.stop("Pools");

        /*
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno.
         */

        borderNoise = landscapeGenerator.noiseFor(biomeProvider, worldX, worldZ);

        /*
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Decorations");
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, rand, new BlockPos(worldX, 0, worldZ)));

        Logger.debug("DecorateBiomeEvent.Pre (%d %d)", worldX, worldZ);

        // Ore gen.
        this.generateOres(biome, settings, new BlockPos(worldX, 0, worldZ));

        //Initialise variables.
        float river = -biomeProvider.getRiverStrength(worldX + 16, worldZ + 16);

        //Border noise. (Does this have to be done here? - Pink)
        RealisticBiomeBase realisticBiome;

        TreeSet<Valued<RealisticBiomeBase>> activeBiomes = new TreeSet<>();
        for (int bn = 0; bn < 256; bn++) {
            if (borderNoise[bn] > 0f) {
                if (borderNoise[bn] >= 1f) borderNoise[bn] = 1f;

                realisticBiome = RealisticBiomeBase.getBiome(bn);

                // Do we need to patch the biome?
                if (realisticBiome == null) {
                    realisticBiome = biomePatcher.getPatchedRealisticBiome(
                        "NULL biome (" + bn + ") found when generating border noise.");
                }
                activeBiomes.add(new Valued<>(borderNoise[bn],realisticBiome));

                borderNoise[bn] = 0f;
            }
        }

        // for basebiomedeco interference: run the biomes in reverse order of influence
        for (Valued<RealisticBiomeBase> biomeInfluence: activeBiomes.descendingSet()) {
            realisticBiome = biomeInfluence.item();
            float borderNoise = (float)biomeInfluence.value();

            if (rtgConfig.DISABLE_RTG_BIOME_DECORATIONS.get() || realisticBiome.getConfig().DISABLE_RTG_DECORATIONS.get()) {

                realisticBiome.baseBiome.decorate(this.world, rand, new BlockPos(worldX, 0, worldZ));
            }
            else {

                realisticBiome.rDecorate(this.rtgWorld, this.rand, worldX, worldZ, borderNoise, river, hasPlacedVillageBlocks);
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world, rand, new BlockPos(worldX, 0, worldZ)));

        Logger.debug("DecorateBiomeEvent.Post (%d %d)", worldX, worldZ);

        TimeTracker.manager.stop("Decorations");

        /*
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Post-decorations");
        biome.rDecorator().rPopulatePostDecorate(world, rand, settings, chunkX, chunkZ, hasPlacedVillageBlocks);
        TimeTracker.manager.stop("Post-decorations");

        TimeTracker.manager.start("Entities");
        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.world, biome.baseBiome(), worldX + 8, worldZ + 8, 16, 16, this.rand);
        }
        TimeTracker.manager.stop("Entities");

        TimeTracker.manager.start("Ice");
        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ICE)) {

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

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks);

        BlockFalling.fallInstantly = false;
        TimeTracker.manager.stop("RTG populate");
        populating = false;
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      if (settings.useMonuments && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L) {
        if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get() && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L) {
            flag = this.oceanMonumentGenerator.generateStructure(this.world, this.rand, new ChunkPos(x, z));
        }
        return flag;
    }

    @Override
    @Nonnull
    public List<Biome.SpawnListEntry> getPossibleCreatures(@Nonnull EnumCreatureType creatureType, @Nonnull BlockPos pos) {
        Biome biome = this.world.getBiome(pos);

        if (this.mapFeaturesEnabled) {
            if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.isSwampHut(pos)) {
                return this.scatteredFeatureGenerator.getMonsters();
            }
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (creatureType == EnumCreatureType.MONSTER && settings.useMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.world, pos)) {
            if (creatureType == EnumCreatureType.MONSTER && rtgConfig.GENERATE_OCEAN_MONUMENTS.get() && this.oceanMonumentGenerator.isPositionInStructure(this.world, pos)) {
                return this.oceanMonumentGenerator.getMonsters();
            }
        }
        return biome.getSpawnableList(creatureType);
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        if (!this.mapFeaturesEnabled) { return null; }
        if ("Stronghold".equals(structureName) && this.strongholdGenerator != null) { return this.strongholdGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Mansion".equals(structureName) && this.woodlandMansionGenerator != null) { return this.woodlandMansionGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Monument".equals(structureName) && this.oceanMonumentGenerator != null) { return this.oceanMonumentGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Village".equals(structureName) && this.villageGenerator != null) { return this.villageGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        if ("Mineshaft".equals(structureName) && this.mineshaftGenerator != null) { return this.mineshaftGenerator.getNearestStructurePos(worldIn, position, findUnexplored); }
        return "Temple".equals(structureName) && this.scatteredFeatureGenerator != null ? this.scatteredFeatureGenerator.getNearestStructurePos(worldIn, position, findUnexplored) : null;
    }

    @Override
    public void recreateStructures(@Nonnull Chunk chunk, int par1, int par2) {

        if (mapFeaturesEnabled) {
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useMineShafts) {
            if (rtgConfig.GENERATE_MINESHAFTS.get()) {
                try {
                    mineshaftGenerator.generate(world, par1, par2, new ChunkPrimer());
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useStrongholds) {
            if (rtgConfig.GENERATE_STRONGHOLDS.get()) {
                try {
                    strongholdGenerator.generate(world, par1, par2, new ChunkPrimer());
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useVillages) {
            if (rtgConfig.GENERATE_VILLAGES.get()) {
                try {
                    villageGenerator.generate(this.world, par1, par2, new ChunkPrimer());
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useTemples) {
            if (rtgConfig.GENERATE_SCATTERED_FEATURES.get()) {
                try {
                    scatteredFeatureGenerator.generate(this.world, par1, par2, new ChunkPrimer());
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
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          if (settings.useMonuments) {
            if (rtgConfig.GENERATE_OCEAN_MONUMENTS.get()) {
                try {
                    oceanMonumentGenerator.generate(this.world, par1, par2, new ChunkPrimer());
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

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        if (!this.mapFeaturesEnabled) { return false; }
        if ("Stronghold".equals(structureName) && this.strongholdGenerator != null) { return this.strongholdGenerator.isInsideStructure(pos); }
        if ("Mansion".equals(structureName) && this.woodlandMansionGenerator != null) { return this.woodlandMansionGenerator.isInsideStructure(pos); }
        if ("Monument".equals(structureName) && this.oceanMonumentGenerator != null) { return this.oceanMonumentGenerator.isInsideStructure(pos); }
        if ("Village".equals(structureName) && this.villageGenerator != null) { return this.villageGenerator.isInsideStructure(pos); }
        if ("Mineshaft".equals(structureName) && this.mineshaftGenerator != null) { return this.mineshaftGenerator.isInsideStructure(pos); }
        return ("Temple".equals(structureName) && this.scatteredFeatureGenerator != null) && this.scatteredFeatureGenerator.isInsideStructure(pos);
    }

    private void decorateIfOtherwiseSurrounded(IChunkProvider worldIn, ChunkPos pos, Direction fromNewChunk) {

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

        for (Direction checked : directions) {

            if (checked == compass.opposite(fromNewChunk)) {
                Logger.debug("Chunk checked (%d %d). Continuing...", pos.x, pos.z);
                continue; // that's the new chunk
            }

            if (!chunkExists(true, probe.x + checked.xOffset, probe.z + checked.zOffset)) {
                Logger.debug("Chunk doesn't exist (%d %d). Returning...", pos.x, pos.z);
                return;// that one's missing
            }
        }

        // passed all checks
        addToDecorationList(probe);

        Logger.debug("Chunk added to decoration list (%d %d).", probe.x, probe.z);

        //this.doPopulate(probe.x, probe.z);
    }

// TODO: [Clean-up] Remove constant condition parameter `checkNeighbours`
    private boolean chunkExists(boolean checkNeighbours, int cx, int cz) {
        //if (chunkExists(cx,cz)) return true;
        ChunkPos location = new ChunkPos(cx, cz);
        if (inGeneration.containsKey(location)) return true;
        if (toCheck.contains(location)) return true;
        if (this.chunkMade.contains(location)) return true;
        //if  (world.chunkExists(cx, cz)) return true;
        if (chunkLoader().chunkExists(world, cx, cz)) return true;
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
            ChunkProviderServer server = (ChunkProviderServer) (world.getChunkProvider());
            chunkLoader = (AnvilChunkLoader) (server.chunkLoader);
        }
        return chunkLoader;
    }

    /**
     * @see IChunkProvider
     * Loads or generates the chunk at the chunk location specified.
     */
    @SuppressWarnings("unused")
// TODO: [Cllean-up] To be removed. This is vestigal from 1.7.10 and is no longer in the interface and never used.
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
                doPopulate(location.x, location.z);
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

    private void generateOres(IRealisticBiome rBiome, ChunkProviderSettingsRTG settings, BlockPos pos) {

        // Have we already generated ores for this chunk?
        if (chunkOreGenTracker.hasGeneratedOres(pos)) {
            Logger.debug("Already generated ores for chunk @ x:{} z:{}", pos.getX(), pos.getZ());
            return;
        }

        rBiome.rDecorator().decorateOres(this.world, this.rand, settings, pos);
        chunkOreGenTracker.addOreChunk(pos);
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
                ret.put("distance", String.valueOf(settings.templeMaxDistance));
                return ret;
            }

            if (this == VILLAGE) {
                ret.put("distance", String.valueOf(settings.villageMaxDistance));
                ret.put("size",     String.valueOf(settings.villageSize));
                return ret;
            }

            return ret;
        }
    }

    private final class FakeGeneratorForMansion extends ChunkGeneratorOverworld {
        private FakeGeneratorForMansion(World world) { super(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), world.getWorldInfo().getGeneratorOptions()); }
        @Override public void setBlocksInChunk(int x, int z, ChunkPrimer primer) { super.setBlocksInChunk(x, z, primer); }
    }
}