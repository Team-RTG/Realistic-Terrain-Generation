package teamrtg.rtg.core.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

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
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonument;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.util.Acceptor;
import teamrtg.rtg.api.util.Accessor;
import teamrtg.rtg.api.util.BiomeUtils;
import teamrtg.rtg.api.util.Compass;
import teamrtg.rtg.api.util.Converter;
import teamrtg.rtg.api.util.Direction;
import teamrtg.rtg.api.util.LimitedMap;
import teamrtg.rtg.api.util.LimitedSet;
import teamrtg.rtg.api.util.PlaneLocation;
import teamrtg.rtg.api.util.TimeTracker;
import teamrtg.rtg.api.util.TimedHashSet;
import teamrtg.rtg.api.util.math.CanyonColour;
import teamrtg.rtg.api.util.math.MathUtils;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

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

    private static final int sampleSize = 8;
    public static String biomeLayoutActivity = "Biome Layout";
    private static String rtgTerrain = "RTG Terrain";
    private static ChunkProviderRTG populatingProvider;
    public final Random rand;
    public final Random mapRand;
    public final World world;
    public final RTGWorld rtgWorld;
    public final LandscapeGenerator landscapeGenerator;
    private final boolean mapFeaturesEnabled;
    private final int worldHeight;
    private final int sampleArraySize;
    private final IBlockState bedrockBlock = Mods.RTG.config.BEDROCK_BLOCK.get();
    private final LimitedMap<PlaneLocation, Chunk> availableChunks;
    private final HashSet<PlaneLocation> toDecorate = new HashSet<>();
    private MapGenBase caveGenerator = new MapGenCaves();
    private MapGenStronghold strongholdGenerator = new MapGenStronghold();
    private MapGenVillage villageGenerator = new MapGenVillage();
    private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
    private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
    private MapGenBase ravineGenerator = new MapGenRavine();
    private StructureOceanMonument oceanMonumentGenerator = new StructureOceanMonument();
    private Biome[] biomesForGeneration;
    private ChunkProviderSettings settings;
    private Accessor<ChunkProviderServer, Set<Long>> forServerLoadingChunks = new Accessor<>("loadingChunks");
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
    private LimitedSet<PlaneLocation> alreadyDecorated = new LimitedSet<>(1000);
    private AnvilChunkLoader chunkLoader;
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

    public ChunkProviderRTG(World worldIn, long l, String jsonSettings) {
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

        {
            caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
            strongholdGenerator = (MapGenStronghold) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(strongholdGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.STRONGHOLD);
            villageGenerator = (MapGenVillage) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(villageGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.VILLAGE);
            mineshaftGenerator = (MapGenMineshaft) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(mineshaftGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.MINESHAFT);
            scatteredFeatureGenerator = (MapGenScatteredFeature) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(scatteredFeatureGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.SCATTERED_FEATURE);
            ravineGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(ravineGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.RAVINE);
            oceanMonumentGenerator = (StructureOceanMonument) net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(oceanMonumentGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.OCEAN_MONUMENT);
        }

        if (jsonSettings != null) {
            this.settings = ChunkProviderSettings.Factory.jsonToFactory(jsonSettings).build();
            worldIn.setSeaLevel(this.settings.seaLevel);
        }

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
                int locationIndex = ((i + adjustment) * 25 + (j + adjustment));
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

    private int chunkCoordinate(int biomeMapCoordinate) {
        return (biomeMapCoordinate - sampleSize) * 8;
    }

    public Chunk provideChunk(int cx, int cz) {
        this.rand.setSeed((long) cx * 341873128712L + (long) cz * 132897987541L);
        final PlaneLocation chunkLocation = new PlaneLocation.Invariant(cx, cz);
        if (inGeneration.containsKey(chunkLocation)) {
            return inGeneration.get(chunkLocation);
        }

        //if (everGenerated.contains(chunkLocation)) throw new RuntimeException();

        TimeTracker.manager.start(rtgTerrain);
        rand.setSeed((long) cx * 0x4f9939f508L + (long) cz * 0x1ef1565bd5L);

        ChunkLandscape landscape = landscapeGenerator.landscape(bprv, cx * 16, cz * 16);

        ChunkPrimer primer = new ChunkPrimer();

        this.biomesForGeneration = Arrays.stream(landscape.biomes).map(RTGBiome::getBiome).toArray(Biome[]::new);
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
            inverseBaseBiomes[i] = this.biomesForGeneration[MathUtils.XY_INVERTED[i]];
        }

        generateTerrain(primer, landscape.noise);
        this.replaceBiomeBlocks(cx, cz, primer, jitteredBiomes, landscape.noise);

        this.biomesForGeneration = inverseBaseBiomes;

        if (this.settings.useCaves) {
            this.caveGenerator.generate(this.world, cx, cz, primer);
        }

        if (this.settings.useRavines) {
            this.ravineGenerator.generate(this.world, cx, cz, primer);
        }

        if (this.mapFeaturesEnabled) {
            if (this.settings.useMineShafts) {
                this.mineshaftGenerator.generate(this.world, cx, cz, primer);
            }

            if (this.settings.useVillages) {
                this.villageGenerator.generate(this.world, cx, cz, primer);
            }

            if (this.settings.useStrongholds) {
                this.strongholdGenerator.generate(this.world, cx, cz, primer);
            }

            if (this.settings.useTemples) {
                this.scatteredFeatureGenerator.generate(this.world, cx, cz, primer);
            }

            if (this.settings.useMonuments) {
                this.oceanMonumentGenerator.generate(this.world, cx, cz, primer);
            }
        }

        Chunk chunk = new Chunk(this.world, primer, cx, cz);
        inGeneration.put(chunkLocation, chunk);
        byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i) {
            abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
        }

        chunk.setBiomeArray(abyte);
        chunk.generateSkylightMap();
        toCheck.add(chunkLocation);

        inGeneration.remove(chunkLocation);
        this.chunkMade.add(chunkLocation);
        availableChunks.put(chunkLocation, chunk);
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

    private void replaceBiomeBlocks(int cx, int cz, ChunkPrimer primer, RTGBiome[] biomes, float[] n) {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, cx, cz, primer, this.world)) return;
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

    /**
     * @see IChunkProvider
     * <p/>
     * Populates chunk with ores etc etc
     */
    @Override
    public void populate(int chunkX, int chunkZ) {
//        if (this.alreadyDecorated.contains(new PlaneLocation.Invariant(chunkX, chunkZ))) return;
//        if (this.neighborsDone(chunkX, chunkZ)) {
        this.doPopulate(chunkX, chunkZ);
//        }
        clearDecorations(0);
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        boolean flag = false;

        if (this.settings.useMonuments && this.mapFeaturesEnabled && chunkIn.getInhabitedTime() < 3600L) {
            flag = this.oceanMonumentGenerator.generateStructure(this.world, this.rand, new ChunkPos(x, z));
        }

        return flag;
    }

    /**
     * @see IChunkProvider
     * <p/>
     * Returns a list of creatures of the specified type that can spawn at the given location.
     */
    @Override
    public List getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome biome = this.world.getBiome(pos);

        if (this.mapFeaturesEnabled) {
            if (creatureType == EnumCreatureType.MONSTER && this.scatteredFeatureGenerator.isSwampHut(pos)) {
                return this.scatteredFeatureGenerator.getScatteredFeatureSpawnList();
            }

            if (creatureType == EnumCreatureType.MONSTER && this.settings.useMonuments && this.oceanMonumentGenerator.isPositionInStructure(this.world, pos)) {
                return this.oceanMonumentGenerator.getScatteredFeatureSpawnList();
            }
        }

        return biome.getSpawnableList(creatureType);
    }

    /**
     * @see IChunkProvider
     */
    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
        return "Stronghold".equals(structureName) && this.strongholdGenerator != null ? this.strongholdGenerator.getClosestStrongholdPos(worldIn, position) : null;
    }

    /**
     * @see IChunkProvider
     */
    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
        if (this.mapFeaturesEnabled) {
            if (this.settings.useMineShafts) {
                this.mineshaftGenerator.generate(this.world, x, z, null);
            }

            if (this.settings.useVillages) {
                this.villageGenerator.generate(this.world, x, z, null);
            }

            if (this.settings.useStrongholds) {
                this.strongholdGenerator.generate(this.world, x, z, null);
            }

            if (this.settings.useTemples) {
                this.scatteredFeatureGenerator.generate(this.world, x, z, null);
            }

            if (this.settings.useMonuments) {
                this.oceanMonumentGenerator.generate(this.world, x, z, null);
            }
        }
    }

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
        BlockPos blockpos = new BlockPos(worldX, 0, worldZ);

        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, x, z, hasPlacedVillageBlocks);

        if (this.mapFeaturesEnabled) {
            if (this.settings.useMineShafts) {
                this.mineshaftGenerator.generateStructure(this.world, this.rand, chunkCoords);
            }

            if (this.settings.useVillages) {
                hasPlacedVillageBlocks = this.villageGenerator.generateStructure(this.world, this.rand, chunkCoords);
            }

            if (this.settings.useStrongholds) {
                this.strongholdGenerator.generateStructure(this.world, this.rand, chunkCoords);
            }

            if (this.settings.useTemples) {
                this.scatteredFeatureGenerator.generateStructure(this.world, this.rand, chunkCoords);
            }

            if (this.settings.useMonuments) {
                this.oceanMonumentGenerator.generateStructure(this.world, this.rand, chunkCoords);
            }
        }


        TimeTracker.manager.start("Pools");
        if (biome.getConfig().WATER_POND_CHANCE.get() > 0 && this.settings.useWaterLakes && !hasPlacedVillageBlocks && this.rand.nextInt(this.settings.waterLakeChance) == 0)
            if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, hasPlacedVillageBlocks, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE)) {
                int i2 = this.rand.nextInt(16) + 8;
                int j2 = this.rand.nextInt(256);
                int k1 = this.rand.nextInt(16) + 8;
                (new WorldGenLakes(Blocks.WATER)).generate(this.world, this.rand, blockpos.add(i2, j2, k1));
            }

        if (!hasPlacedVillageBlocks && this.rand.nextInt(this.settings.lavaLakeChance / 10) == 0 && this.settings.useLavaLakes)
            if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, hasPlacedVillageBlocks, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAVA)) {
                int i2 = this.rand.nextInt(16) + 8;
                int l2 = this.rand.nextInt(this.rand.nextInt(248) + 8);
                int k3 = this.rand.nextInt(16) + 8;

                if (l2 < this.world.getSeaLevel() || this.rand.nextInt(this.settings.lavaLakeChance / 8) == 0) {
                    (new WorldGenLakes(Blocks.LAVA)).generate(this.world, this.rand, blockpos.add(i2, l2, k3));
                }
            }

        if (this.settings.useDungeons)
            if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, hasPlacedVillageBlocks, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.DUNGEON)) {
                for (int j2 = 0; j2 < this.settings.dungeonChance; ++j2) {
                    int i3 = this.rand.nextInt(16) + 8;
                    int l3 = this.rand.nextInt(256);
                    int l1 = this.rand.nextInt(16) + 8;
                    (new WorldGenDungeons()).generate(this.world, this.rand, blockpos.add(i3, l3, l1));
                }
            }
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
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(world, rand, blockpos));

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
                        realisticBiome.getBiome().decorate(this.world, rand, blockpos);
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

        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(world, rand, blockpos));

        TimeTracker.manager.stop("Decorations");
        /*
         * ########################################################################
         * # END DECORATE BIOME
         * ########################################################################
         */

        blockpos = blockpos.add(8, 0, 8);

        TimeTracker.manager.start("Post-decorations");

        TimeTracker.manager.stop("Post-decorations");
        TimeTracker.manager.start("Entities");

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, hasPlacedVillageBlocks, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
            WorldEntitySpawner.performWorldGenSpawning(this.world, biome.getBiome(), worldX + 8, worldZ + 8, 16, 16, this.rand);

        TimeTracker.manager.stop("Entities");
        TimeTracker.manager.start("Ice");
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.world, this.rand, x, z, hasPlacedVillageBlocks, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE)) {
            for (int k2 = 0; k2 < 16; ++k2) {
                for (int j3 = 0; j3 < 16; ++j3) {
                    BlockPos blockpos1 = this.world.getPrecipitationHeight(blockpos.add(k2, 0, j3));
                    BlockPos blockpos2 = blockpos1.down();

                    if (this.world.canBlockFreezeWater(blockpos2)) {
                        this.world.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
                    }

                    if (this.world.canSnowAt(blockpos1, true)) {
                        this.world.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
                    }
                }
            }
        }//Forge: End ICE

        TimeTracker.manager.stop("Ice");
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, x, z, hasPlacedVillageBlocks);

        BlockFalling.fallInstantly = false;

        TimeTracker.manager.stop("RTG populate");
        populating = false;
        populatingProvider = null;
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

    private final void removeFromDecorationList(PlaneLocation toAdd) {
        synchronized (toDecorate) {
            toDecorate.remove(toAdd);
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

    private final void addToDecorationList(PlaneLocation toAdd) {
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

    public Runnable clearOnServerClose() {
        return this::clearToDecorateList;
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

    private Converter<Chunk, PlaneLocation> keyer() {
        return new Converter<Chunk, PlaneLocation>() {

            @Override
            public final PlaneLocation result(Chunk original) {
                return new PlaneLocation.Invariant(original.xPosition, original.zPosition);
            }

        };
    }

    public boolean neighborsDone(int cx, int cz) {
        if (!chunkExists(cx - 1, cz - 1)) return false;
        if (!chunkExists(cx - 1, cz)) return false;
        if (!chunkExists(cx - 1, cz + 1)) return false;
        if (!chunkExists(cx, cz - 1)) return false;
        if (!chunkExists(cx, cz + 1)) return false;
        if (!chunkExists(cx + 1, cz - 1)) return false;
        if (!chunkExists(cx + 1, cz)) return false;
        return chunkExists(cx + 1, cz + 1);
    }

    public Set<Long> serverLoadingChunks() {
        if (this.serverLoadingChunks == null) {
            ChunkProviderServer server = (ChunkProviderServer) (world.getChunkProvider());
            chunkLoader = (AnvilChunkLoader) (server.chunkLoader);
            serverLoadingChunks = forServerLoadingChunks.get(server);
        }
        return serverLoadingChunks;
    }
}
