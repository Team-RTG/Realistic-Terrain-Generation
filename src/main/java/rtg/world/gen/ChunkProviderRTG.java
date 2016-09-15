package rtg.world.gen;

import java.util.*;
import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
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

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.*;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.biome.IBiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.RealisticBiomePatcher;


@SuppressWarnings({"UnusedParameters", "deprecation"})
public class ChunkProviderRTG implements IChunkGenerator
{
    private static ChunkProviderRTG populatingProvider;
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private final MapGenStronghold strongholdGenerator;
    private final MapGenMineshaft mineshaftGenerator;
    private final MapGenVillage villageGenerator;
    private final MapGenScatteredFeature scatteredFeatureGenerator;
    private final StructureOceanMonument oceanMonumentGenerator;
    private final int sampleSize = 8;
    private final int sampleArraySize;
    private final LandscapeGenerator landscapeGenerator;
    private final LimitedMap<ChunkPos, Chunk> availableChunks;
    private final HashSet<ChunkPos> toDecorate = new HashSet<>();
    private boolean mapFeaturesEnabled;
    private Block bedrockBlock = Block.getBlockFromName(ConfigRTG.bedrockBlockId);
    private byte bedrockByte = (byte) ConfigRTG.bedrockBlockByte;
    private Random rand;
    private Random mapRand;
    private World worldObj;
    private IBiomeProviderRTG cmr;
    private OpenSimplexNoise simplex;
    private CellNoise cell;
    private Biome[] baseBiomesList;
    private boolean[] biomesGeneratedInChunk;
    private float[] borderNoise;
    private long worldSeed;
    private RealisticBiomePatcher biomePatcher;
    private HashMap<ChunkPos, Chunk> inGeneration = new HashMap<>();
    private HashSet<ChunkPos> toCheck = new HashSet<>();
    private Compass compass = new Compass();
    private ArrayList<Direction> directions = compass.directions();

    //private HashSet<PlaneLocation> everGenerated = new HashSet<PlaneLocation>();
    private TimedHashSet<ChunkPos> chunkMade = new TimedHashSet<>(5 * 1000);
    private boolean populating = false;
    private LimitedSet<ChunkPos> alreadyDecorated = new LimitedSet<>(1000);
    private AnvilChunkLoader chunkLoader;

    // we have to store this callback because it's a WeakReference in the event manager
    public final Acceptor<ChunkEvent.Load> delayedDecorator = new Acceptor<ChunkEvent.Load>() {
        @Override
        public void accept(ChunkEvent.Load event) {
            if (event.isCanceled()) return;
            ChunkPos pos = event.getChunk().getChunkCoordIntPair();

            if (!toCheck.contains(pos)) return;
            toCheck.remove(pos);
            for (Direction forPopulation : directions) {
                decorateIfOtherwiseSurrounded(event.getWorld().getChunkProvider(), pos, forPopulation);
            }
            //clearDecorations(0);
        }
    };

    public ChunkProviderRTG(World world, long l) {
        worldObj = world;
        cmr = (BiomeProviderRTG) worldObj.getBiomeProvider();
        rand = new Random(l);
        simplex = new OpenSimplexNoise(l);
        cell = new SimplexCellularNoise(l);
        landscapeGenerator = new LandscapeGenerator(simplex, cell);
        mapRand = new Random(l);
        worldSeed = l;
        Map<String, String> m = new HashMap<>();
        m.put("size", "0");
        m.put("distance", "24");
        mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();
        boolean isRTGWorld = world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG;

        if (isRTGWorld && ConfigRTG.enableCaveModifications) {
            caveGenerator = TerrainGen.getModdedMapGen(new MapGenCavesRTG(), EventType.CAVE);
        }
        else {
            caveGenerator = TerrainGen.getModdedMapGen(new MapGenCaves(), EventType.CAVE);
        }

        if (isRTGWorld && ConfigRTG.enableRavineModifications) {
            ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavineRTG(), EventType.RAVINE);
        }
        else {
            ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavine(), EventType.RAVINE);
        }

        villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(m), EventType.VILLAGE);
        strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(), EventType.STRONGHOLD);
        mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(), EventType.MINESHAFT);
        scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(), EventType.SCATTERED_FEATURE);
        oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonument(), EventType.OCEAN_MONUMENT);
        CanyonColour.init(l);
        sampleArraySize = sampleSize * 2 + 5;
        baseBiomesList = new Biome[256];
        biomesGeneratedInChunk = new boolean[256];
        borderNoise = new float[256];
        biomePatcher = new RealisticBiomePatcher();

        // set up the cache of available chunks
        availableChunks = new LimitedMap<>(1000);
        setWeightings();

        // check for bogus world
        if (worldObj == null) throw new RuntimeException("Attempt to create chunk provider without a world");
    }

    private void setWeightings() {
        float[][] weightings = new float[sampleArraySize * sampleArraySize][256];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                TimeTracker.manager.start("Weighting");
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

        ChunkLandscape landscape = landscapeGenerator.landscape(cmr, cx * 16, cz * 16);

        generateTerrain(cmr, cx, cz, primer, landscape.biome, landscape.noise);
        // that routine can change the blocks.
        //get standard biome Data

        for (int ci = 0; ci < 256; ci++) {
            biomesGeneratedInChunk[Biome.getIdForBiome(landscape.biome[ci].baseBiome)] = true;
        }

        for (k = 0; k < 256; k++) {
            if (biomesGeneratedInChunk[k]) {
                RealisticBiomeBase.getBiome(k).generateMapGen(primer, worldSeed, worldObj, cmr, mapRand, cx, cz, simplex, cell, landscape.noise);
                biomesGeneratedInChunk[k] = false;
            }
            try {
                baseBiomesList[k] = landscape.biome[k].baseBiome;
            }
            catch (Exception e) {
                baseBiomesList[k] = biomePatcher.getPatchedBaseBiome("" + Biome.getIdForBiome(landscape.biome[k].baseBiome));
            }
        }

        replaceBlocksForBiome(cx, cz, primer, landscape.biome, baseBiomesList, landscape.noise);

        caveGenerator.generate(worldObj, cx, cz, primer);
        ravineGenerator.generate(worldObj, cx, cz, primer);

        if (mapFeaturesEnabled) {

            if (ConfigRTG.generateMineshafts) {
                try {
                    mineshaftGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateStrongholds) {
                try {
                    strongholdGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateVillages) {
                try {
                    villageGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateScatteredFeatures) {
                try {
                    scatteredFeatureGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateOceanMonuments) {
                try {
                    oceanMonumentGenerator.generate(this.worldObj, cx, cz, primer);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }
        }

        // store in the in process pile
        Chunk chunk = new Chunk(this.worldObj, primer, cx, cz);
        inGeneration.put(pos, chunk);

        // doJitter no longer needed as the biome array gets fixed
        byte[] abyte1 = chunk.getBiomeArray();

        for (k = 0; k < abyte1.length; ++k) {
            // biomes are y-first and terrain x-first
            byte b = (byte) Biome.getIdForBiome(this.baseBiomesList[k]);
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
        TimeTracker.manager.stop(rtgTerrain);
        return chunk;
    }

    private void generateTerrain(IBiomeProviderRTG cmr, int cx, int cz, ChunkPrimer primer,
                                 RealisticBiomeBase biomes[], float[] noise) {

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

    private void replaceBlocksForBiome(int cx, int cz, ChunkPrimer primer, RealisticBiomeBase[]
        biomes, Biome[] base, float[] n) {

        ChunkGeneratorEvent.ReplaceBiomeBlocks event = new ChunkGeneratorEvent.ReplaceBiomeBlocks(
            this, cx, cz, primer, this.worldObj);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.getResult() == Event.Result.DENY) return;

        int i, j, depth;
        float river;
        RealisticBiomeBase biome;

        for (i = 0; i < 16; i++) {
            for (j = 0; j < 16; j++) {

                /*
                 * Some of the 'i' and 'j' parameters have been flipped when passing them.
                 * Prior to flipping, the surface was being XZ-chunk-flipped. - WhichOnesPink
                 */
                biome = biomes[i * 16 + j];
                river = -cmr.getRiverStrength(cx * 16 + i, cz * 16 + j);
                depth = -1;

                biome.rReplace(primer, cx * 16 + i, cz * 16 + j, i, j, depth, worldObj, rand, simplex, cell, n, river, base);

                int rough;
                int flatBedrockLayers = ConfigRTG.flatBedrockLayers;
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
        if (WorldTypeRTG.chunkProvider != this) return;
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
        RealisticBiomeBase biome = cmr.getBiomeDataAt(worldX + 16, worldZ + 16);
        //Logger.debug("CPRTG#doPopulate: %s at %d %d", biome.baseBiome.getBiomeName(), worldX + 16, worldZ + 16);

        TimeTracker.manager.stop("Biome Layout");
        this.rand.setSeed(this.worldObj.getSeed());
        long i1 = this.rand.nextLong() / 2L * 2L + 1L;
        long j1 = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed((long) chunkX * i1 + (long) chunkZ * j1 ^ this.worldObj.getSeed());
        boolean hasPlacedVillageBlocks = false;
        BlockPos worldCoords = new BlockPos(worldX, 0, worldZ);

        ForgeEventFactory.onChunkPopulate(true, this, this.worldObj, this.rand, chunkX, chunkZ, false);

        if (mapFeaturesEnabled) {

            TimeTracker.manager.start("Mineshafts");
            if (ConfigRTG.generateMineshafts) {
                try {
                    mineshaftGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }
            TimeTracker.manager.stop("Mineshafts");

            TimeTracker.manager.start("Strongholds");
            if (ConfigRTG.generateStrongholds) {
                try {
                    strongholdGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }
            TimeTracker.manager.stop("Strongholds");

            TimeTracker.manager.start("Villages");
            if (ConfigRTG.generateVillages) {
                try {
                    hasPlacedVillageBlocks = villageGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    hasPlacedVillageBlocks = false;
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }
            TimeTracker.manager.stop("Villages");

            TimeTracker.manager.start("Scattered");
            if (ConfigRTG.generateScatteredFeatures) {
                try {
                    scatteredFeatureGenerator.generateStructure(worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }
            TimeTracker.manager.stop("Scattered");

            TimeTracker.manager.start("Monuments");
            if (ConfigRTG.generateOceanMonuments) {
                try {
                    oceanMonumentGenerator.generateStructure(this.worldObj, rand, chunkPos);
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }
            TimeTracker.manager.stop("Monuments");
        }

        TimeTracker.manager.start("Pools");
        biome.rDecorator.rPopulatePreDecorate(this, worldObj, rand, chunkX, chunkZ, hasPlacedVillageBlocks);
        TimeTracker.manager.stop("Pools");

        /*
         * What is this doing? And why does it need to be done here? - Pink
         * Answer: building a frequency table of nearby biomes - Zeno.
         */

        final int adjust = 24;// seems off? but decorations aren't matching their chunks.

        TimeTracker.manager.start("Biome Layout");
        for (int bx = -4; bx <= 4; bx++) {

            for (int bz = -4; bz <= 4; bz++) {
                borderNoise[landscapeGenerator.getBiomeDataAt(cmr, worldX + adjust + bx * 4, worldZ + adjust + bz * 4)] += 0.01234569f;
            }
        }
        TimeTracker.manager.stop("Biome Layout");
        TimeTracker.manager.stop("Features");

        /*
         * ########################################################################
         * # START DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Decorations");
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(worldObj, rand, new BlockPos(worldX, 0, worldZ)));

        //Initialise variables.
        float river = -cmr.getRiverStrength(worldX + 16, worldZ + 16);

        //Border noise. (Does this have to be done here? - Pink)
        RealisticBiomeBase realisticBiome;

        for (int bn = 0; bn < 256; bn++) {
            if (borderNoise[bn] > 0f) {
                if (borderNoise[bn] >= 1f) borderNoise[bn] = 1f;

                realisticBiome = RealisticBiomeBase.getBiome(bn);

                // Do we need to patch the biome?
                if (realisticBiome == null) {
                    realisticBiome = biomePatcher.getPatchedRealisticBiome(
                        "NULL biome (" + bn + ") found when generating border noise.");
                }

                /*
                 * When decorating the biome, we need to look at the biome configs to see if RTG is allowed to decorate it.
                 * If the biome configs don't allow it, then we try to let the base biome decorate itself.
                 * However, there are some mod biomes that crash when they try to decorate themselves,
                 * so that's what the try/catch is for. If it fails, then it falls back to RTG decoration.
                 */
                if (ConfigRTG.enableRTGBiomeDecorations && realisticBiome.config._boolean(BiomeConfig.useRTGDecorationsId)) {

                    realisticBiome.rDecorate(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river, hasPlacedVillageBlocks);
                }
                else {

                    try {

                        realisticBiome.baseBiome.decorate(this.worldObj, rand, new BlockPos(worldX, 0, worldZ));
                    }
                    catch (Exception e) {

                        realisticBiome.rDecorate(this.worldObj, this.rand, worldX, worldZ, simplex, cell, borderNoise[bn], river, hasPlacedVillageBlocks);
                    }
                }
            /*
                if(realisticBiome.baseBiome.getTemperature() < 0.15f) {}
                else {}
            */
                borderNoise[bn] = 0f;
            }
        }

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(worldObj, rand, new BlockPos(worldX, 0, worldZ)));

        TimeTracker.manager.stop("Decorations");

        /*
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */

        TimeTracker.manager.start("Post-decorations");
        biome.rDecorator.rPopulatePostDecorate(worldObj, rand, chunkX, chunkZ);
        TimeTracker.manager.stop("Post-decorations");

        TimeTracker.manager.start("Entities");
        if (TerrainGen.populate(this, this.worldObj, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.worldObj, biome.baseBiome, worldX + 8, worldZ + 8, 16, 16, this.rand);
        }
        TimeTracker.manager.stop("Entities");

        TimeTracker.manager.start("Ice");
        if (TerrainGen.populate(this, this.worldObj, this.rand, chunkX, chunkZ, hasPlacedVillageBlocks, PopulateChunkEvent.Populate.EventType.ICE)) {
            // Not sure why we're modifying the worldCoords here, but this is how it's done in ChunkProviderOverworld. - Pink
            worldCoords = worldCoords.add(8, 0, 8);

            for (int k2 = 0; k2 < 16; ++k2) {
                for (int j3 = 0; j3 < 16; ++j3) {
                    BlockPos blockpos1 = this.worldObj.getPrecipitationHeight(worldCoords.add(k2, 0, j3));
                    BlockPos blockpos2 = blockpos1.down();

                    if (this.worldObj.canBlockFreezeWater(blockpos2)) {
                        this.worldObj.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
                    }

                    if (ConfigRTG.enableSnowLayers && this.worldObj.canSnowAt(blockpos1, true)) {
                        this.worldObj.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
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

        if (ConfigRTG.generateOceanMonuments && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L) {
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

            if (creatureType == EnumCreatureType.MONSTER && ConfigRTG.generateOceanMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.worldObj, pos)) {
                return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
            }
        }
        return biome.getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(@Nonnull World par1World, @Nonnull String par2Str, @Nonnull BlockPos blockPos) {

        if (!ConfigRTG.generateStrongholds) return null;
        return "Stronghold".equals(par2Str) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(par1World, blockPos) : null;
    }

    @Override
    public void recreateStructures(@Nonnull Chunk chunk, int par1, int par2) {

        if (mapFeaturesEnabled) {
            if (ConfigRTG.generateMineshafts) {
                try {
                    mineshaftGenerator.generate(worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateStrongholds) {
                try {
                    strongholdGenerator.generate(worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateVillages) {
                try {
                    villageGenerator.generate(this.worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateScatteredFeatures) {
                try {
                    scatteredFeatureGenerator.generate(this.worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }

            if (ConfigRTG.generateOceanMonuments) {
                try {
                    oceanMonumentGenerator.generate(this.worldObj, par1, par2, new ChunkPrimer());
                }
                catch (Exception e) {
                    if (ConfigRTG.crashOnStructureExceptions) {
                        throw new RuntimeException(e.getMessage());
                    }
                    else {
                        Logger.fatal(e.getMessage(), e);
                    }
                }
            }
        }
    }

    private void decorateIfOtherwiseSurrounded(IChunkProvider world, ChunkPos pos, Direction fromNewChunk) {

        // check if this is the master provider
        if (WorldTypeRTG.chunkProvider != this) return;

        // see if otherwise surrounded besides the new chunk
        ChunkPos probe = new ChunkPos(pos.chunkXPos + fromNewChunk.xOffset, pos.chunkZPos + fromNewChunk.zOffset);

        // check to see if already decorated; shouldn't be but just in case
        if (this.alreadyDecorated.contains(probe)) return;
        // if an in-process chunk; we'll get a populate call later;
        // if (this.inGeneration.containsKey(probe)) return;

        for (Direction checked : directions) {
            if (checked == compass.opposite(fromNewChunk)) continue; // that's the new chunk
            if (!chunkExists(true, probe.chunkXPos + checked.xOffset, probe.chunkZPos + checked.zOffset)) return;// that one's missing
        }
        // passed all checks
        addToDecorationList(probe);
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
        if (WorldTypeRTG.chunkProvider != this) return;
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
}
