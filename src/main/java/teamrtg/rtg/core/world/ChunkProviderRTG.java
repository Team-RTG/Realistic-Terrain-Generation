package teamrtg.rtg.core.world;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
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
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.structure.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.ChunkGeneratorEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.*;
import teamrtg.rtg.api.util.math.CanyonColour;
import teamrtg.rtg.api.util.math.MathUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;
import teamrtg.rtg.core.world.gen.MapGenCavesRTG;
import teamrtg.rtg.core.world.gen.MapGenRavineRTG;

import java.util.*;

import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.*;

/**
 * Note from the ChunkProviderRTG-gods:
 * <p>
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
    private static final int sampleSize = 8;
    private static String rtgTerrain = "RTG Terrain";
    private static String rtgNoise = "RTG Noise";
    private static ChunkProviderRTG populatingProvider;
    public final Random rand;
    public final Random mapRand;
    public final World world;
    public final RTGWorld rtgWorld;
    public final LandscapeGenerator landscapeGenerator;
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private final MapGenStronghold strongholdGenerator;
    private final MapGenMineshaft mineshaftGenerator;
    private final MapGenVillage villageGenerator;
    private final MapGenScatteredFeature scatteredFeatureGenerator;
    private final StructureOceanMonument oceanMonumentGenerator;
    private final boolean mapFeaturesEnabled;
    private final int worldHeight;
    private final int sampleArraySize;
    private final BiomeAnalyzer analyzer;
    private final IBlockState bedrockBlock = Mods.RTG.config.BEDROCK_BLOCK.get();
    private final LimitedMap<PlaneLocation, Chunk> availableChunks;
    private final HashSet<PlaneLocation> toDecorate = new HashSet<PlaneLocation>();
    Accessor<ChunkProviderServer, Set<Long>> forServerLoadingChunks =
            new Accessor<ChunkProviderServer, Set<Long>>("loadingChunks");
    private BiomeProviderRTG bprv;
    private float[] borderNoise;
    private float[][] weightings;
    private TimedHashSet<PlaneLocation> chunkMade = new TimedHashSet<>(5 * 1000);
    private PlaneLocation.Probe probe = new PlaneLocation.Probe(0, 0);
    private HashMap<PlaneLocation, Chunk> inGeneration = new HashMap<>();
    private HashSet<PlaneLocation> toCheck = new HashSet<>();
    private Set<Long> serverLoadingChunks;
    private Compass compass = new Compass();
    ArrayList<Direction> directions = compass.directions();
    private boolean populating = false;
    private LimitedSet<PlaneLocation> alreadyDecorated = new LimitedSet<PlaneLocation>(1000);
    // we have to store this callback because it's a WeakReference in the event manager
    public final Acceptor<ChunkEvent.Load> delayedDecorator = new Acceptor<ChunkEvent.Load>() {

        @Override
        public void accept(ChunkEvent.Load accepted) {
            if (accepted.isCanceled()) return;
            int cx = accepted.getChunk().xPosition;
            int cy = accepted.getChunk().zPosition;
            PlaneLocation location = new PlaneLocation.Invariant(cx, cy);
            if (!toCheck.contains(location)) return;
            toCheck.remove(location);
            for (Direction forPopulation : directions) {
                decorateIfOtherwiseSurrounded(location, forPopulation);
            }
            //clearDecorations(0);
        }

    };

    public ChunkProviderRTG(World worldIn, long l) {
        this.world = worldIn;
        bprv = (BiomeProviderRTG) this.world.getBiomeProvider();
        bprv.chunkProvider = this;
        worldHeight = this.world.provider.getActualHeight();
        rand = new Random(l);
        rtgWorld = new RTGWorld(worldIn);

        mapRand = new Random(l);

        landscapeGenerator = new LandscapeGenerator(rtgWorld.simplex, rtgWorld.cell);

        Map m = new HashMap();
        m.put("size", "0");
        m.put("distance", "24");

        mapFeaturesEnabled = worldIn.getWorldInfo().isMapFeaturesEnabled();

        analyzer = new BiomeAnalyzer();

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

        CanyonColour.init();

        sampleArraySize = sampleSize * 2 + 5;

        borderNoise = new float[256];

        availableChunks = new LimitedMap<>(1000);
        setWeightings();
    }

    private void setWeightings() {
        weightings = new float[sampleArraySize * sampleArraySize][256];
        int adjustment = 4;// this should actually vary with sampleSize
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int locationIndex = ((int) (i + adjustment) * 25 + (j + adjustment));
                TimeTracker.manager.start("Weighting");
                float totalWeight = 0;
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

    @Override
    @MethodsReturnNonnullByDefault
    public Chunk provideChunk(int cx, int cz) {
        final PlaneLocation chunkLocation = new PlaneLocation.Invariant(cx, cz);
        if (inGeneration.containsKey(chunkLocation)) {
            return inGeneration.get(chunkLocation);
        }

        //if (everGenerated.contains(chunkLocation)) throw new RuntimeException();

        TimeTracker.manager.start(rtgTerrain);
        rand.setSeed((long) cx * 0x4f9939f508L + (long) cz * 0x1ef1565bd5L);

        ChunkLandscape landscape = landscapeGenerator.landscape(bprv, cx * 16, cz * 16);

        ChunkPrimer primer = new ChunkPrimer();
        Biome[] baseBiomes = Arrays.stream(landscape.biomes).map(RTGBiome::getBiome).toArray(Biome[]::new);
        RTGBiome[] jitteredBiomes = new RTGBiome[256];

        RTGBiome jittered, actual;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                rtgWorld.simplex.evaluateNoise(cx * 16 + i, cz * 16 + j, rtgWorld.surfaceJitter);
                int pX = (int) Math.round(cx * 16 + i + rtgWorld.surfaceJitter.deltax() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
                int pZ = (int) Math.round(cz * 16 + j + rtgWorld.surfaceJitter.deltay() * Mods.RTG.config.SURFACE_BLEED_RADIUS.get());
                actual = bprv.getRTGBiomeAt(cx * 16 + i, cz * 16 + j);
                jittered = bprv.getRTGBiomeAt(pX, pZ);
                jitteredBiomes[i * 16 + j] = (actual.getConfig().SURFACE_BLEED_IN.get() && jittered.getConfig().SURFACE_BLEED_OUT.get()) ? jittered : actual;
            }
        }

        Biome[] inverseBaseBiomes = new Biome[256];
        for (int i = 0; i < 256; i++) {
            inverseBaseBiomes[i] = baseBiomes[MathUtils.XY_INVERTED[i]];
        }

        baseBiomes = inverseBaseBiomes;


        generateTerrain(primer, landscape.noise);

        replaceBlocksForBiome(cx, cz, primer, jitteredBiomes, baseBiomes, landscape.noise);

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
        inGeneration.put(chunkLocation, chunk);
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
            byte b = (byte) BiomeUtils.getId(baseBiomes[k]);
            abyte1[k] = b;
        }
        chunk.setBiomeArray(abyte1);
        chunk.generateSkylightMap();

        toCheck.add(chunkLocation);

        // remove from in process pile
        inGeneration.remove(chunkLocation);
        this.chunkMade.add(chunkLocation);
        //this.everGenerated.add(chunkLocation);
        /*if (!chunkMade.contains(chunkLocation)||!everGenerated.contains(chunkLocation)) {
            throw new RuntimeException(chunkLocation.toString() +  chunkMade.size());
        }*/
        availableChunks.put(chunkLocation,chunk);
        TimeTracker.manager.stop(rtgTerrain);
        return chunk;
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

    private void replaceBlocksForBiome(int cx, int cz, ChunkPrimer primer, RTGBiome[] biomes, Biome[] base, float[] n) {
        ChunkGeneratorEvent.ReplaceBiomeBlocks event = new ChunkGeneratorEvent.ReplaceBiomeBlocks(this, cx, cz, primer, world);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Result.DENY) return;
        int i, j, depth;
        float river;
        RealisticBiomeGenerator generator;
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {

                RTGBiome biome = biomes[i * 16 + j];

                if (rtgWorld.biomeFaker.isFakeBiome(biome.getID())) {
                    rtgWorld.biomeFaker.fakeSurface(cx * 16 + i, cz * 16 + j, primer, biome.getBiome());
                } else {

                    river = -bprv.getRiverStrength(cx * 16 + i, cz * 16 + j);
                    depth = -1;
                    generator = RealisticBiomeGenerator.forBiome(biome.getBiome());
                    for (int by = 255; by > -1; by--) {
                        Block b = primer.getBlockState(i, by, j).getBlock();
                        if (b == Blocks.AIR) {
                            depth = -1;
                        } else if (b == Blocks.STONE) {
                            depth++;
                            generator.paintSurface(primer, cx * 16 + i, by, cz * 16 + j, depth, n, river, rtgWorld);
                        }
                    }
                }

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

    public void decorateIfOtherwiseSurrounded(PlaneLocation source, Direction fromNewChunk) {

        // check if this is the master provider
        //if (WorldTypeRTG.chunkProvider != this) return; // nope, not doing that! (the whole multiple dims thing and such)

        // see if otherwise surrounded besides the new chunk
        int cx = source.x() + fromNewChunk.xOffset;
        int cy = source.z() + fromNewChunk.zOffset;

        // check to see if already decorated; shouldn't be but just in case
        probe.setX(cx);
        probe.setZ(cy);
        if (this.alreadyDecorated.contains(probe)) return;
        // if an in-process chunk; we'll get a populate call later;
        // if (this.inGeneration.containsKey(probe)) return;

        for (Direction checked : directions) {
            if (checked == compass.opposite(fromNewChunk)) continue; // that's the new chunk
            if (!chunkExists(cx + checked.xOffset, cy + checked.zOffset)) return;// that one's missing
        }
        // passed all checks
        addToDecorationList(new PlaneLocation.Invariant(cx, cy));
        //this.doPopulate(world, cx, cy);
    }

    private boolean chunkExists(int par1, int par2) {
        //if (chunkExists(par1,par2)) return true;
        PlaneLocation location = new PlaneLocation.Invariant(par1, par2);
        if (inGeneration.containsKey(location)) return true;
        if (toCheck.contains(location)) return true;
        if (this.chunkMade.contains(location)) return true;
        if (chunkLoader().chunkExists(world, par1, par2)) return true;
        //if (this.everGenerated.contains(location)) throw new RuntimeException("somehow lost "+location.toString());
        return false;
    }

    public Runnable clearOnServerClose() {
        return new Runnable() {
            public void run() {
                clearToDecorateList();
            }
        };
    }

    private void clearToDecorateList() {
        if (populating) return;// in process, do later;
        // we have to make a copy of the set to work on or we'll get errors
        Set<PlaneLocation> toProcess = doableLocations(0);
        while (toProcess.size() > 0) {
            for (PlaneLocation location : toProcess) {
                removeFromDecorationList(location);
            }
            for (PlaneLocation location : toProcess) {
                doPopulate(location.x(), location.z());
            }
            // and loop because the decorating might have created other chunks to decorate;
            toProcess = doableLocations(0);
        }
    }

    private void clearDecorations(int limit) {
        Set<PlaneLocation> toProcess = doableLocations(limit);
        for (PlaneLocation location : toProcess) {
            removeFromDecorationList(location);
        }
        for (PlaneLocation location : toProcess) {
            doPopulate(location.x(), location.z());
        }
    }

    private Set<PlaneLocation> doableLocations(int limit) {
        HashSet<PlaneLocation> toProcess = new HashSet<PlaneLocation>();
        int found = 0;
        synchronized (toDecorate) {
            for (PlaneLocation location : toDecorate) {
                Chunk existing;
                existing = availableChunks.get(location);
                if (existing != null) {
                    if (!existing.isTerrainPopulated()) {
                        //continue; // not populated so let more "normal" systems handle it
                    }
                }
                if (inGeneration.containsKey(location)) continue;
                toProcess.add(location);
                if (++found == limit) return toProcess;
            }
        }
        return toProcess;
    }

    private Converter<Chunk, PlaneLocation> keyer() {
        return new Converter<Chunk, PlaneLocation>() {

            @Override
            public final PlaneLocation result(Chunk original) {
                return new PlaneLocation.Invariant(original.xPosition, original.zPosition);
            }

        };
    }

    private final void addToDecorationList(PlaneLocation toAdd) {
        synchronized (toDecorate) {
            toDecorate.add(toAdd);
        }
    }

    private final void removeFromDecorationList(PlaneLocation toAdd) {
        synchronized (toDecorate) {
            toDecorate.remove(toAdd);
        }
    }

    public boolean neighborsDone(int cx, int cz) {
        if (!chunkExists(cx - 1, cz - 1)) return false;
        if (!chunkExists(cx - 1, cz)) return false;
        if (!chunkExists(cx - 1, cz + 1)) return false;
        if (!chunkExists(cx, cz - 1)) return false;
        if (!chunkExists(cx, cz + 1)) return false;
        if (!chunkExists(cx + 1, cz - 1)) return false;
        if (!chunkExists(cx + 1, cz)) return false;
        if (!chunkExists(cx + 1, cz + 1)) return false;
        return true;
    }

    private AnvilChunkLoader chunkLoader;
    private AnvilChunkLoader chunkLoader() {
        if (chunkLoader == null) {
            ChunkProviderServer server = (ChunkProviderServer)(world.getChunkProvider());
            chunkLoader = (AnvilChunkLoader)(server.chunkLoader);
        }
        return chunkLoader;
    }

    public Set<Long> serverLoadingChunks() {
        if (this.serverLoadingChunks == null) {
            ChunkProviderServer server = (ChunkProviderServer)(world.getChunkProvider());
            chunkLoader = (AnvilChunkLoader)(server.chunkLoader);
            serverLoadingChunks = forServerLoadingChunks.get(server);
        }
        return serverLoadingChunks;
    }

    /**
     * @see IChunkProvider
     * <p/>
     * Populates chunk with ores etc etc
     */
    @Override
    public void populate(int chunkX, int chunkZ){
        //if (this.alreadyDecorated.contains(new PlaneLocation.Invariant(chunkX, chunkZ))) return;
        //if (this.neighborsDone(chunkX, chunkZ)) {
            this.doPopulate(chunkX, chunkZ);
        //}
        clearDecorations(0);
    }

    public static String biomeLayoutActivity = "Biome Layout";

    private void doPopulate(int x, int z) {
        // don't populate if already done

        PlaneLocation location = new PlaneLocation.Invariant(x, z);
        //Logger.warn("trying to decorate "+location.toString());
//        if (alreadyDecorated.contains(location)) return;
//
//        if (populating) {
//            // this has been created by another decoration; put in todo pile
//            addToDecorationList(location);
//            return;
//        }
//
//        if (populatingProvider != null) {
//            throw new RuntimeException(toString() + " " + populatingProvider.toString());
//        }
//        if (inGeneration.containsKey(location)) {
//            addToDecorationList(location);
//            return;
//        }
        //Logger.warn("decorating");
        alreadyDecorated.add(location);
        populating = true;
        populatingProvider = this;

        TimeTracker.manager.start("RTG populate");
        TimeTracker.manager.start("Features");
        BlockFalling.fallInstantly = true;

        int worldX = x * 16;
        int worldZ = z * 16;

        TimeTracker.manager.start(biomeLayoutActivity);
        RTGBiome biome = bprv.getRTGBiomeAt(worldX + 16, worldZ + 16);
        TimeTracker.manager.stop(biomeLayoutActivity);
        this.rand.setSeed(this.world.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) x * i1 + (long) z * j1 ^ this.world.getSeed());
        boolean hasPlacedVillageBlocks = false;
        ChunkPos chunkCoords = new ChunkPos(x, z);
        BlockPos worldCoords = new BlockPos(worldX, 0, worldZ);

        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, world, rand, x, z, hasPlacedVillageBlocks));

        if (mapFeaturesEnabled) {

            TimeTracker.manager.start("Mineshafts");
            if (Mods.RTG.config.GENERATE_MINESHAFTS.get()) {
                mineshaftGenerator.generateStructure(world, rand, chunkCoords);
            }
            TimeTracker.manager.stop("Mineshafts");
            TimeTracker.manager.start("Strongholds");
            if (Mods.RTG.config.GENERATE_STRONGHOLDS.get()) {
                strongholdGenerator.generateStructure(world, rand, chunkCoords);
            }
            TimeTracker.manager.stop("Strongholds");
            TimeTracker.manager.start("Villages");
            if (Mods.RTG.config.GENERATE_VILLAGES.get()) {

                if (Mods.RTG.config.VILLAGE_CRASH_FIX.get()) {

                    try {
                        hasPlacedVillageBlocks = villageGenerator.generateStructure(world, rand, chunkCoords);
                    } catch (Exception e) {
                        hasPlacedVillageBlocks = false;
                    }
                } else {

                    hasPlacedVillageBlocks = villageGenerator.generateStructure(world, rand, chunkCoords);
                }
            }
            TimeTracker.manager.stop("Villages");
            TimeTracker.manager.start("Scattered");
            if (Mods.RTG.config.GENERATE_SCATTERED_FEATURES.get()) {
                scatteredFeatureGenerator.generateStructure(world, rand, chunkCoords);
            }
            TimeTracker.manager.stop("Scattered");
            TimeTracker.manager.start("Monuments");
            if (Mods.RTG.config.GENERATE_OCEAN_MONUMENTS.get()) {
                oceanMonumentGenerator.generateStructure(world, rand, chunkCoords);
            }
            TimeTracker.manager.stop("Monuments");
        }


        TimeTracker.manager.start("Pools");
        RealisticBiomeGenerator.forBiome(biome.getBiome()).populatePreDecorate(this, world, rand, x, z, hasPlacedVillageBlocks);
        TimeTracker.manager.stop("Pools");

        /*
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno.
         */

        TimeTracker.manager.start(biomeLayoutActivity);
        final int adjust = 32;// seems off? but decorations aren't matching their chunks.
        for (int bx = -4; bx <= 4; bx++) {

            for (int by = -4; by <= 4; by++) {
                borderNoise[BiomeUtils.getId(bprv.getBiomeGenAt(worldX + adjust + bx * 4, worldZ + adjust + by * 4))] += 0.01234569f;
            }
        }

        TimeTracker.manager.stop(biomeLayoutActivity);
        TimeTracker.manager.stop("Features");
        /*
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */


        TimeTracker.manager.start("Decorations");
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, rand, worldCoords));

        //Initialise variables.
        float river = -bprv.getRiverStrength(worldX + 16, worldZ + 16);

        //Border noise. (Does this have to be done here? - Pink)
        RTGBiome realisticBiome;
        float snow = 0f;

        for (int bn = 0; bn < 256; bn++) {
            if (borderNoise[bn] > 0f) {
                if (borderNoise[bn] >= 1f) {
                    borderNoise[bn] = 1f;
                }
                realisticBiome = RTGBiome.forBiome(bn);

                /*
                 * When decorating the biome, we need to look at the biome configs to see if RTG is allowed to decorate it.
                 * If the biome configs don't allow it, then we try to let the base biome decorate itself.
                 * However, there are some mod biomes that crash when they try to decorate themselves,
                 * so that's what the try/catch is for. If it fails, then it falls back to RTG decoration.
                 * TODO: Is there a more efficient way to do this? - Pink
                 */
                if (Mods.RTG.config.ENABLE_RTG_BIOME_DECORATIONS.get() && realisticBiome.getConfig().USE_RTG_DECORATIONS.get()) {
                    RealisticBiomeGenerator.forBiome(realisticBiome.getBiome()).rDecorate(rtgWorld, rand, worldX, worldZ, borderNoise[bn], river, hasPlacedVillageBlocks);
                } else {
                    try {
                        realisticBiome.getBiome().decorate(this.world, rand, worldCoords);
                    } catch (Exception e) {
                        RealisticBiomeGenerator.forBiome(realisticBiome.getBiome()).rDecorate(rtgWorld, rand, worldX, worldZ, borderNoise[bn], river, hasPlacedVillageBlocks);
                    }
                }

                if (realisticBiome.getBiome().getTemperature() < 0.15f) {
                    snow -= 0.6f * borderNoise[bn];
                } else {
                    snow += 0.6f * borderNoise[bn];
                }
                borderNoise[bn] = 0f;
            }
        }

        RealisticBiomeGenerator.forBiome(biome.getBiome()).populatePostDecorate(this, world, rand, x, z, hasPlacedVillageBlocks);

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world, rand, worldCoords));

        TimeTracker.manager.stop("Decorations");
        /*
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */


        TimeTracker.manager.start("Post-decorations");
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

        TimeTracker.manager.stop("Post-decorations");
        TimeTracker.manager.start("Entities");
        probe.setX(x);
        probe.setZ(z);
        if (TerrainGen.populate(this, world, rand, x, z, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.world, world.getBiome(new BlockPos(worldX + 16, 0, worldZ + 16)), worldX + 8, worldZ + 8, 16, 16, this.rand);
        }

        TimeTracker.manager.stop("Entities");
        TimeTracker.manager.start("Ice");
        probe.setX(x);
        probe.setZ(z);
        if (TerrainGen.populate(this, world, rand, x, z, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ICE)) {

            int k1, l1, i2;
            BlockPos.MutableBlockPos bp = new BlockPos.MutableBlockPos(0, 0, 0);
            for (k1 = 0; k1 < 16; ++k1) {

                for (l1 = 0; l1 < 16; ++l1) {

                    i2 = this.world.getPrecipitationHeight(bp.setPos(worldX + k1, 0, worldZ + l1)).getY();

                    if (this.world.canBlockFreezeNoWater(bp.setPos(k1 + worldX, i2 - 1, l1 + worldZ))) {
                        this.world.setBlockState(bp.setPos(k1 + worldX, i2 - 1, l1 + worldZ), Blocks.ICE.getDefaultState(), 2);
                    }

                    if (Mods.RTG.config.ENABLE_SNOW_LAYERS.get() && this.world.canSnowAt(bp.setPos(k1 + worldX, i2, l1 + worldZ), true)) {
                        this.world.setBlockState(bp.setPos(k1 + worldX, i2, l1 + worldZ), Blocks.SNOW_LAYER.getDefaultState(), 2);
                    }
                }
            }
        }

        TimeTracker.manager.stop("Ice");
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Post(this, world, rand, x, z, hasPlacedVillageBlocks));

        BlockFalling.fallInstantly = false;

        TimeTracker.manager.stop("RTG populate");
        populating = false;
        populatingProvider = null;
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
        Biome var5 = this.world.getBiome(pos);
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

    private int chunkCoordinate(int biomeMapCoordinate) {
        return (biomeMapCoordinate - sampleSize) * 8;
    }
}
