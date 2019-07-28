package rtg.world.gen;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.*;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.InitMapGenEvent.EventType;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.RTG;
import rtg.RTGConfig;
import rtg.api.RTGAPI;
import rtg.api.util.LimitedArrayCacheMap;
import rtg.api.util.Logger;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.RTGChunkGenSettings;
import rtg.api.world.gen.feature.WorldGenPond;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.gen.structure.WoodlandMansionRTG;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class ChunkGeneratorRTG implements IChunkGenerator {

    public final RTGWorld rtgWorld;
    private final RTGChunkGenSettings settings;
    private final MapGenBase caveGenerator;
    private final MapGenBase ravineGenerator;
    private final MapGenStronghold strongholdGenerator;
    private final WoodlandMansionRTG woodlandMansionGenerator;
    private final MapGenMineshaft mineshaftGenerator;
    private final MapGenVillage villageGenerator;
    private final MapGenScatteredFeature scatteredFeatureGenerator;
    private final StructureOceanMonument oceanMonumentGenerator;
    private final World world;
    private final LimitedArrayCacheMap<ChunkPos, ChunkLandscape> landscapeCache = new LimitedArrayCacheMap<>(1024);// cache ChunkLandscape objects
    private final int sampleSize = 8;
    private final int sampleArraySize = sampleSize * 2 + 5;
    private final int[] biomeData = new int[sampleArraySize * sampleArraySize];
    private final float[] weightedBiomes = new float[256];
    private final float[][] weightings = new float[sampleArraySize * sampleArraySize][256];
    private final MesaBiomeCombiner mesaCombiner = new MesaBiomeCombiner();
    private BiomeAnalyzer analyzer = new BiomeAnalyzer();
    private int[] xyinverted = analyzer.xyinverted();
    private boolean mapFeaturesEnabled;
    private Random rand;
    private Biome[] baseBiomesList;

    public ChunkGeneratorRTG(RTGWorld rtgWorld) {

        Logger.debug("Instantiating CPRTG using generator settings: {}", rtgWorld.world().getWorldInfo().getGeneratorOptions());

        this.world = rtgWorld.world();
        this.rtgWorld = rtgWorld;
        this.settings = rtgWorld.getGeneratorSettings();

// TODO: [1.12] seaLevel will be removed as terrain noise values are all hardcoded and will not variate properly.
//        this.world.setSeaLevel(this.settings.seaLevel);
        this.rand = new Random(rtgWorld.seed());
        this.rtgWorld.setRandom(this.rand);
        this.mapFeaturesEnabled = world.getWorldInfo().isMapFeaturesEnabled();

        this.caveGenerator = TerrainGen.getModdedMapGen(new MapGenCavesRTG(this.settings.caveChance, this.settings.caveDensity), EventType.CAVE);
        this.ravineGenerator = TerrainGen.getModdedMapGen(new MapGenRavineRTG(this.settings.ravineChance), EventType.RAVINE);
        this.villageGenerator = (MapGenVillage) TerrainGen.getModdedMapGen(new MapGenVillage(StructureType.VILLAGE.getSettings(this.settings)), EventType.VILLAGE);
        this.strongholdGenerator = (MapGenStronghold) TerrainGen.getModdedMapGen(new MapGenStronghold(StructureType.STRONGHOLD.getSettings(this.settings)), EventType.STRONGHOLD);
        this.woodlandMansionGenerator = new WoodlandMansionRTG(this, StructureType.MANSION.getSettings(this.settings));//don't allow mods to override our generator.
        this.mineshaftGenerator = (MapGenMineshaft) TerrainGen.getModdedMapGen(new MapGenMineshaft(StructureType.MINESHAFT.getSettings(this.settings)), EventType.MINESHAFT);
        this.scatteredFeatureGenerator = (MapGenScatteredFeature) TerrainGen.getModdedMapGen(new MapGenScatteredFeature(StructureType.TEMPLE.getSettings(this.settings)), EventType.SCATTERED_FEATURE);
        this.oceanMonumentGenerator = (StructureOceanMonument) TerrainGen.getModdedMapGen(new StructureOceanMonument(StructureType.MONUMENT.getSettings(this.settings)), EventType.OCEAN_MONUMENT);

        this.baseBiomesList = new Biome[256];

        setWeightings();// landscape generator init

        Logger.debug("FINISHED instantiating CPRTG.");
    }

    @Override
    public Chunk generateChunk(final int cx, final int cz) {

        final ChunkPos chunkPos = new ChunkPos(cx, cz);
        final BlockPos blockPos = new BlockPos(cx * 16, 0, cz * 16);
        final BiomeProvider biomeProvider = this.world.getBiomeProvider();

        this.rand.setSeed(cx * 341873128712L + cz * 132897987541L);

        final ChunkPrimer primer = new ChunkPrimer();
        final ChunkLandscape landscape = getLandscape(biomeProvider, chunkPos);
        generateTerrain(primer, landscape.noise);

        //get standard biome Data
        for (int i = 0; i < 256; i++) {
            this.baseBiomesList[i] = landscape.biome[i].baseBiome();
        }

        ISimplexData2D jitterData = SimplexData2D.newDisk();
        IRealisticBiome[] jitteredBiomes = new IRealisticBiome[256];
        IRealisticBiome jitterbiome, actualbiome;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int x = blockPos.getX() + i;
                int z = blockPos.getZ() + j;
                this.rtgWorld.simplexInstance(0).multiEval2D(x, z, jitterData);
                int pX = (int) Math.round(x + jitterData.getDeltaX() * RTGConfig.surfaceBlendRadius());
                int pZ = (int) Math.round(z + jitterData.getDeltaY() * RTGConfig.surfaceBlendRadius());
                actualbiome = landscape.biome[(x & 15) * 16 + (z & 15)];
                jitterbiome = landscape.biome[(pX & 15) * 16 + (pZ & 15)];
                jitteredBiomes[i * 16 + j] = (actualbiome.getConfig().SURFACE_BLEED_IN.get() && jitterbiome.getConfig().SURFACE_BLEED_OUT.get()) ? jitterbiome : actualbiome;
            }
        }

        replaceBiomeBlocks(cx, cz, primer, jitteredBiomes, this.baseBiomesList, landscape.noise);

        if (this.settings.useCaves) {
            this.caveGenerator.generate(this.world, cx, cz, primer);
        }
        if (this.settings.useRavines) {
            this.ravineGenerator.generate(this.world, cx, cz, primer);
        }
        if (this.mapFeaturesEnabled) {
            if (settings.useMineShafts) {
                this.mineshaftGenerator.generate(this.world, cx, cz, primer);
            }
            if (settings.useStrongholds) {
                this.strongholdGenerator.generate(this.world, cx, cz, primer);
            }
            if (settings.useVillages) {
                this.villageGenerator.generate(this.world, cx, cz, primer);
            }
            if (settings.useTemples) {
                this.scatteredFeatureGenerator.generate(this.world, cx, cz, primer);
            }
            if (settings.useMonuments) {
                this.oceanMonumentGenerator.generate(this.world, cx, cz, primer);
            }
            if (settings.useMansions) {
                this.woodlandMansionGenerator.generate(this.world, cx, cz, primer);
            }
        }


        // store in the in process pile
        Chunk chunk = new Chunk(this.world, primer, cx, cz);

        byte[] abyte1 = chunk.getBiomeArray();
        for (int i = 0; i < abyte1.length; ++i) {
            // Biomes are y-first and terrain x-first
            byte b = (byte) Biome.getIdForBiome(this.baseBiomesList[this.xyinverted[i]]);
            abyte1[i] = b;
        }
        chunk.setBiomeArray(abyte1);

        chunk.generateSkylightMap();

        return chunk;
    }

    public void generateTerrain(ChunkPrimer primer, float[] noise) {

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

    private void replaceBiomeBlocks(int cx, int cz, ChunkPrimer primer, IRealisticBiome[] biomes, Biome[] base, float[] noise) {

        if (!ForgeEventFactory.onReplaceBiomeBlocks(this, cx, cz, primer, this.world)) {
            return;
        }

        int worldX = cx * 16;
        int worldZ = cz * 16;

        MutableBlockPos mpos = new MutableBlockPos();
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                mpos.setPos(worldX + x, 0, worldZ + z);

                float river = -TerrainBase.getRiverStrength(mpos, rtgWorld);
                int depth = -1;
                biomes[x * 16 + z].rReplace(primer, mpos, x, z, depth, rtgWorld, noise, river, base);

                // sparse bedrock layers above y=0
                if (this.settings.bedrockLayers > 1) {
                    for (int bl = 9; bl >= 0; --bl) {
                        if (bl <= this.rand.nextInt(this.settings.bedrockLayers)) {
                            primer.setBlockState(x, bl, z, Blocks.BEDROCK.getDefaultState());
                        }
                    }
                }
                else {
                    primer.setBlockState(x, 0, z, Blocks.BEDROCK.getDefaultState());
                }
            }
        }
    }

    @SuppressWarnings("ConstantConditions") //false-positive on `hasVillage`, IDEA is probably not looking deep enough.
    @Override
    public void populate(int chunkX, int chunkZ) {

        BlockFalling.fallInstantly = true;

        final BiomeProvider biomeProvider = this.world.getBiomeProvider();
        final ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        final BlockPos blockPos = new BlockPos(chunkX * 16, 0, chunkZ * 16);
        final BlockPos offsetpos = blockPos.add(8, 0, 8);

        IRealisticBiome biome = RTGAPI.getRTGBiome(biomeProvider.getBiome(blockPos.add(16, 0, 16)));

        this.rand.setSeed(rtgWorld.getChunkSeed(chunkX, chunkZ));

        boolean hasVillage = false;

        ForgeEventFactory.onChunkPopulate(true, this, this.world, this.rand, chunkX, chunkZ, false);

        if (this.mapFeaturesEnabled) {
            if (settings.useMineShafts) {
                mineshaftGenerator.generateStructure(world, rand, chunkPos);
            }
            if (settings.useStrongholds) {
                strongholdGenerator.generateStructure(world, rand, chunkPos);
            }
            if (settings.useVillages) {
                hasVillage = villageGenerator.generateStructure(world, rand, chunkPos);
            }
            if (settings.useTemples) {
                scatteredFeatureGenerator.generateStructure(world, rand, chunkPos);
            }
            if (settings.useMonuments) {
                oceanMonumentGenerator.generateStructure(this.world, rand, chunkPos);
            }
            if (settings.useMansions) {
                woodlandMansionGenerator.generateStructure(world, rand, chunkPos);
            }
        }

        // water lakes.
        if (settings.useWaterLakes && settings.waterLakeChance > 0 && !hasVillage) {

            final long     nextchance    = rand.nextLong();
            final int      surfacechance = settings.getSurfaceWaterLakeChance(biome.waterLakeMult());
            final BlockPos pos           = offsetpos.add(rand.nextInt(16), 0, rand.nextInt(16));

            // possibly reduced chance to generate anywhere, including on surface
            if (surfacechance > 0 && nextchance % surfacechance == 0) {
                if (TerrainGen.populate(this, world, rand, chunkX, chunkZ, hasVillage, PopulateChunkEvent.Populate.EventType.LAKE)) {
                    (new WorldGenPond(Blocks.WATER.getDefaultState())).generate(world, rand, pos.up(rand.nextInt(256)));
                }
            }
            // normal chance to generate underground
            else if (nextchance % settings.waterLakeChance == 0) {
                if (TerrainGen.populate(this, world, rand, chunkX, chunkZ, hasVillage, PopulateChunkEvent.Populate.EventType.LAKE)) {
                    (new WorldGenLakes(Blocks.WATER)).generate(world, rand, pos.up(rand.nextInt(50) + 4));//make sure that underground lakes are sufficiently underground
                }
            }
        }

        // lava lakes.
        if (settings.useLavaLakes && settings.lavaLakeChance > 0 && !hasVillage) {

            final long     nextchance    = rand.nextLong();
            final int      surfacechance = settings.getSurfaceLavaLakeChance(biome.lavaLakeMult());
            final BlockPos pos           = offsetpos.add(rand.nextInt(16), 0, rand.nextInt(16));

            // possibly reduced chance to generate anywhere, including on surface
            if (surfacechance > 0 && nextchance % surfacechance == 0) {
                if (TerrainGen.populate(this, world, rand, chunkX, chunkZ, hasVillage, PopulateChunkEvent.Populate.EventType.LAVA)) {
                    (new WorldGenPond(Blocks.LAVA.getDefaultState())).generate(world, rand, pos.up(rand.nextInt(256)));
                }
            }
            // normal chance to generate underground
            else if (nextchance % settings.lavaLakeChance == 0) {
                if (TerrainGen.populate(this, world, rand, chunkX, chunkZ, hasVillage, PopulateChunkEvent.Populate.EventType.LAVA)) {
                    (new WorldGenLakes(Blocks.LAVA)).generate(world, rand, pos.up(rand.nextInt(50) + 4));//make sure that underground lakes are sufficiently underground
                }
            }
        }

        if (settings.useDungeons) {
            if (TerrainGen.populate(this, world, rand, chunkX, chunkZ, hasVillage, PopulateChunkEvent.Populate.EventType.DUNGEON)) {
                for (int i = 0; i < settings.dungeonChance; i++) {
                    (new WorldGenDungeons()).generate(world, rand, offsetpos.add(rand.nextInt(16), rand.nextInt(256), rand.nextInt(16)));
                }
            }
        }

        float river = -TerrainBase.getRiverStrength(blockPos.add(16, 0, 16), rtgWorld);

        if (RTG.decorationsDisable() || biome.getConfig().DISABLE_RTG_DECORATIONS.get()) {
            if (river > 0.9f) {
                biome.getRiverBiome().baseBiome().decorate(this.world, this.rand, blockPos);
            }
            else {
                biome.baseBiome().decorate(this.world, this.rand, blockPos);
            }
        }
        else {
            if (river > 0.9f) {
                biome.getRiverBiome().rDecorate(this.rtgWorld, this.rand, chunkPos, river, hasVillage);
            }
            else {
                biome.rDecorate(this.rtgWorld, this.rand, chunkPos, river, hasVillage);
            }
        }

        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, hasVillage, PopulateChunkEvent.Populate.EventType.ANIMALS)) {
            WorldEntitySpawner.performWorldGenSpawning(this.world, biome.baseBiome(), blockPos.getX() + 8, blockPos.getZ() + 8, 16, 16, this.rand);
        }

        if (TerrainGen.populate(this, this.world, this.rand, chunkX, chunkZ, hasVillage, PopulateChunkEvent.Populate.EventType.ICE)) {

            final int xPos = blockPos.getX() + 8;
            final int zPos = blockPos.getZ() + 8;
            final MutableBlockPos mpos = new MutableBlockPos();
            for (int x = 0; x < 16; ++x) {
                for (int z = 0; z < 16; ++z) {

                    // Adjust the height check of cold biomes using IRealisticBiome#getSnowLayerMultiplier instead of using reflection
                    // to alter the base biome's temperature. If the multiplier is < 1.0 it checks a lower altitude to see if water
                    // will freeze or if it can snow which results in a higher snow layer altitude.
                    int precHeight = this.world.getPrecipitationHeight(mpos.setPos(xPos + x, 0, zPos + z)).getY();
                    final BlockPos snowPos = new BlockPos(mpos.getX(), (int)(precHeight * biome.getSnowLayerMultiplier()), mpos.getZ());
                    final BlockPos icePos  = snowPos.down();

                    // Ice.
                    if (this.world.canBlockFreezeWater(icePos)) {
                        this.world.setBlockState(icePos, Blocks.ICE.getDefaultState(), 2);
                    }

                    // Snow.
                    if (settings.useSnowLayers && this.world.canSnowAt(snowPos, true)) {
                        this.world.setBlockState(snowPos, Blocks.SNOW_LAYER.getDefaultState(), 2);
                    }
                }
            }
        }

        ForgeEventFactory.onChunkPopulate(false, this, this.world, this.rand, chunkX, chunkZ, hasVillage);

        BlockFalling.fallInstantly = false;
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
        if (!this.mapFeaturesEnabled) {
            return null;
        }
        if ("Stronghold".equals(structureName) && this.strongholdGenerator != null) {
            return this.strongholdGenerator.getNearestStructurePos(worldIn, position, findUnexplored);
        }
        if ("Mansion".equals(structureName) && this.woodlandMansionGenerator != null) {
            return this.woodlandMansionGenerator.getNearestStructurePos(worldIn, position, findUnexplored);
        }
        if ("Monument".equals(structureName) && this.oceanMonumentGenerator != null) {
            return this.oceanMonumentGenerator.getNearestStructurePos(worldIn, position, findUnexplored);
        }
        if ("Village".equals(structureName) && this.villageGenerator != null) {
            return this.villageGenerator.getNearestStructurePos(worldIn, position, findUnexplored);
        }
        if ("Mineshaft".equals(structureName) && this.mineshaftGenerator != null) {
            return this.mineshaftGenerator.getNearestStructurePos(worldIn, position, findUnexplored);
        }
        if ("Temple".equals(structureName) && this.scatteredFeatureGenerator != null) {
            this.scatteredFeatureGenerator.getNearestStructurePos(worldIn, position, findUnexplored);
        }
        return null;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void recreateStructures(Chunk chunk, int cx, int cz) {
        if (this.mapFeaturesEnabled) {
            if (this.settings.useMineShafts) {
                this.mineshaftGenerator.generate(this.world, cx, cz, null);
            }
            if (this.settings.useVillages) {
                this.villageGenerator.generate(this.world, cx, cz, null);
            }
            if (this.settings.useStrongholds) {
                this.strongholdGenerator.generate(this.world, cx, cz, null);
            }
            if (this.settings.useTemples) {
                this.scatteredFeatureGenerator.generate(this.world, cx, cz, null);
            }
            if (this.settings.useMonuments) {
                this.oceanMonumentGenerator.generate(this.world, cx, cz, null);
            }
            if (this.settings.useMansions) {
                this.woodlandMansionGenerator.generate(this.world, cx, cz, null);
            }
        }
    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        if (!this.mapFeaturesEnabled) {
            return false;
        }
        if ("Stronghold".equals(structureName) && this.strongholdGenerator != null) {
            return this.strongholdGenerator.isInsideStructure(pos);
        }
        if ("Mansion".equals(structureName) && this.woodlandMansionGenerator != null) {
            return this.woodlandMansionGenerator.isInsideStructure(pos);
        }
        if ("Monument".equals(structureName) && this.oceanMonumentGenerator != null) {
            return this.oceanMonumentGenerator.isInsideStructure(pos);
        }
        if ("Village".equals(structureName) && this.villageGenerator != null) {
            return this.villageGenerator.isInsideStructure(pos);
        }
        if ("Mineshaft".equals(structureName) && this.mineshaftGenerator != null) {
            return this.mineshaftGenerator.isInsideStructure(pos);
        }
        return ("Temple".equals(structureName) && this.scatteredFeatureGenerator != null) && this.scatteredFeatureGenerator.isInsideStructure(pos);
    }


    /* Landscape Geneator */

    private void setWeightings() {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                float limit = (float) Math.pow((56f * 56f), 0.7D);
                for (int mapX = 0; mapX < sampleArraySize; mapX++) {
                    for (int mapZ = 0; mapZ < sampleArraySize; mapZ++) {
                        float xDist = (x - (mapX - sampleSize) * 8);
                        float zDist = (z - (mapZ - sampleSize) * 8);
                        float distanceSquared = xDist * xDist + zDist * zDist;
                        float distance = (float) Math.pow(distanceSquared, 0.7D);
                        float weight = 1f - distance / limit;
                        if (weight < 0) {
                            weight = 0;
                        }
                        weightings[mapX * sampleArraySize + mapZ][x * 16 + z] = weight;
                    }
                }
            }
        }
    }

    public ChunkLandscape getLandscape(final BiomeProvider biomeProvider, final ChunkPos chunkPos) {
        final BlockPos blockPos = new BlockPos(chunkPos.x * 16, 0, chunkPos.z * 16);
        ChunkLandscape landscape = landscapeCache.get(chunkPos);
        if (landscape == null) {
            landscape = generateLandscape(biomeProvider, blockPos);
            landscapeCache.put(chunkPos, landscape);
        }
        return landscape;
    }

    private synchronized ChunkLandscape generateLandscape(BiomeProvider biomeProvider, BlockPos blockPos) {
        final ChunkLandscape landscape = new ChunkLandscape();
        getNewerNoise(biomeProvider, blockPos.getX(), blockPos.getZ(), landscape);
        Biome[] biomes = new Biome[256];
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                biomes[x * 16 + z] = biomeProvider.getBiome(blockPos.add(x, 0, z));
            }
        }
        analyzer.newRepair(biomes, this.biomeData, landscape);
        return landscape;
    }

    private synchronized void getNewerNoise(final BiomeProvider biomeProvider, final int worldX, final int worldZ, ChunkLandscape landscape) {

        // get area biome map
        for (int x = -sampleSize; x < sampleSize + 5; x++) {
            for (int z = -sampleSize; z < sampleSize + 5; z++) {
                biomeData[(x + sampleSize) * sampleArraySize + (z + sampleSize)] = Biome.getIdForBiome(biomeProvider.getBiome(new BlockPos(worldX + ((x * 8)), 0, worldZ + ((z * 8)))));
            }
        }

        // fill the old smallRender
        MutableBlockPos mpos = new MutableBlockPos(worldX, 0, worldZ);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                float totalWeight = 0;
                for (int mapX = 0; mapX < sampleArraySize; mapX++) {
                    for (int mapZ = 0; mapZ < sampleArraySize; mapZ++) {
                        float weight = weightings[mapX * sampleArraySize + mapZ][x * 16 + z];
                        if (weight > 0) {
                            totalWeight += weight;
                            weightedBiomes[biomeData[mapX * sampleArraySize + mapZ]] += weight;
                        }
                    }
                }

                // normalize biome weights
                for (int biomeIndex = 0; biomeIndex < weightedBiomes.length; biomeIndex++) {
                    weightedBiomes[biomeIndex] /= totalWeight;
                }

                // combine mesa biomes
                mesaCombiner.adjust(weightedBiomes);

                landscape.noise[x * 16 + z] = 0f;

                float river = TerrainBase.getRiverStrength(mpos.setPos(worldX + x, 0, worldZ + z), rtgWorld);
                landscape.river[x * 16 + z] = -river;

                for (int i = 0; i < 256; i++) {

                    if (weightedBiomes[i] > 0f) {

                        landscape.noise[x * 16 + z] += RTGAPI.getRTGBiome(i).rNoise(this.rtgWorld, worldX + x, worldZ + z, weightedBiomes[i], river + 1f) * weightedBiomes[i];

                        // 0 for the next column
                        weightedBiomes[i] = 0f;
                    }
                }
            }
        }

        //fill biomes array with biomeData
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                BlockPos pos = new BlockPos(worldX + (x - 7) * 8 + 4, 0, worldZ + (z - 7) * 8 + 4);
                landscape.biome[x * 16 + z] = RTGAPI.getRTGBiome(biomeProvider.getBiome(pos));
            }
        }
    }

    // A helper class to generate settings maps to configure the vanilla structure classes
    private enum StructureType {

        MINESHAFT,
        MONUMENT,
        STRONGHOLD,
        TEMPLE,
        VILLAGE,
        MANSION;

        Map<String, String> getSettings(RTGChunkGenSettings settings) {

            Map<String, String> ret = new HashMap<>();

            if (this == MINESHAFT) {
                ret.put("chance", String.valueOf(settings.mineShaftChance));
                return ret;
            }

            if (this == MONUMENT) {
                ret.put("separation", String.valueOf(settings.monumentSeparation));
                ret.put("spacing", String.valueOf(settings.monumentSpacing));
                return ret;
            }

            if (this == STRONGHOLD) {
                ret.put("count", String.valueOf(settings.strongholdCount));
                ret.put("distance", String.valueOf(settings.strongholdDistance));
                ret.put("spread", String.valueOf(settings.strongholdSpread));
                return ret;
            }

            if (this == TEMPLE) {
                ret.put("distance", String.valueOf(settings.templeDistance));
                return ret;
            }

            if (this == VILLAGE) {
                ret.put("distance", String.valueOf(settings.villageDistance));
                ret.put("size", String.valueOf(settings.villageSize));
                return ret;
            }

            if (this == MANSION) {
                ret.put("spacing", String.valueOf(settings.mansionSpacing));
                ret.put("separation", String.valueOf(settings.mansionSeparation));
                return ret;
            }

            return ret;
        }
    }
}
