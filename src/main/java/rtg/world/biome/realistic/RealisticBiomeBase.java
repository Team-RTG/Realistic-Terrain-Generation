package rtg.world.biome.realistic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.RTG;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.config.RTGConfig;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexCellularNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.BiomeDecoratorRTG;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.biome.RealisticBiomeManager;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.gen.feature.WorldGenVolcano;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.surface.SurfaceGeneric;
import rtg.api.world.surface.SurfaceRiverOasis;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.IBiomeProviderRTG;

@SuppressWarnings({"WeakerAccess", "UnusedParameters", "unused"})
public abstract class RealisticBiomeBase implements IRealisticBiome {

    protected RTGConfig rtgConfig = RTGAPI.config();
    private static final RealisticBiomeBase[] arrRealisticBiomeIds = new RealisticBiomeBase[256];

    public final Biome baseBiome;
    public final Biome riverBiome;
    public final Biome beachBiome;
    public BiomeConfig config;
    public String configPath;
    public TerrainBase terrain;
    public SurfaceBase surface;
    public SurfaceBase surfaceRiver;
    public SurfaceBase surfaceGeneric;
    public BiomeDecoratorRTG rDecorator;

    private ArrayList<DecoBase> decos;
    private ArrayList<TreeRTG> rtgTrees;

    // lake calculations

    private float lakeInterval = 989.0f;
    private float lakeShoreLevel = 0.15f;
    private float lakeWaterLevel = 0.11f;// the lakeStrength below which things should be below water
    private float lakeDepressionLevel = 0.30f;// the lakeStrength below which land should start to be lowered

    private float largeBendSize = 100;
    private float mediumBendSize = 40;
    private float smallBendSize = 15;

    public boolean disallowStoneBeaches = false; // this is for rugged biomes that should have sand beaches
    public boolean disallowAllBeaches = false;

    public RealisticBiomeBase(Biome biome, Biome river) {

        arrRealisticBiomeIds[Biome.getIdForBiome(biome)] = this;

        baseBiome = biome;
        riverBiome = river;
        this.config = new BiomeConfig();
        beachBiome = this.beachBiome();

        rDecorator = new BiomeDecoratorRTG(this, biome);

        decos = new ArrayList<>();
        rtgTrees = new ArrayList<>();

        /*
         *  Disable base biome decorations by default.
         *  This also needs to be here so that ores get generated.
         */
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setAllowed(false);
        this.addDeco(decoBaseBiomeDecorations);

        // set the water feature constants with the config changes
        this.lakeInterval *= rtgConfig.LAKE_FREQUENCY_MULTIPLIER.get();
        this.lakeWaterLevel *= rtgConfig.lakeSizeMultiplier();
        this.lakeShoreLevel *= rtgConfig.lakeSizeMultiplier();
        this.lakeDepressionLevel *= rtgConfig.lakeSizeMultiplier();

        this.largeBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();
        this.mediumBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();
        this.smallBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();

        this.init();
    }

    protected void init() {
        initConfig();
        this.getConfig().load(this.configPath());
        this.terrain = initTerrain();
        this.surface = initSurface();
        this.surfaceRiver = new SurfaceRiverOasis(config);
        this.surfaceGeneric = new SurfaceGeneric(config, this.surface.getTopBlock(), this.surface.getFillerBlock());
        initDecos();
    }

    @Override
    public BiomeConfig getConfig() {
        return this.config;
    }

    @Override
    public ArrayList<DecoBase> getDecos() {
        return this.decos;
    }

    @Override
    public ArrayList<TreeRTG> getTrees() {
        return this.rtgTrees;
    }

    public static RealisticBiomeBase getBiome(int id) {
        return arrRealisticBiomeIds[id];
    }

    public static RealisticBiomeBase[] arr() {
        return arrRealisticBiomeIds;
    }

    /*
     * Returns the beach biome to use for this biome.
     * By default, it uses the beach that has been set in the biome config.
     * If automatic beach detection is enabled (-1), it uses the supplied preferred beach.
     */
    protected Biome beachBiome(Biome preferredBeach) {

        Biome beach;
        int configBeachId = this.getConfig().BEACH_BIOME.get();

        if (configBeachId > -1 && configBeachId < 256) {
            beach = Biome.getBiome(configBeachId, preferredBeach);
        }
        else {
            beach = preferredBeach;
        }

        return beach;
    }

    /*
     * Returns the beach biome to use for this biome, with a dynamically-calculated preferred beach.
     */
    public Biome beachBiome() {
        return this.beachBiome(BiomeAnalyzer.getPreferredBeachForBiome(this.baseBiome));
    }

    public BiomeDecoratorRTG rDecorator() {
        return rDecorator;
    }

    public void rMapVolcanoes(
        ChunkPrimer primer, World world, IBiomeProviderRTG cmr,
        Random mapRand, int baseX, int baseY, int chunkX, int chunkY,
        OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        // Have volcanoes been disabled in the global config?
        if (!rtgConfig.ENABLE_VOLCANOES.get()) return;

        // Have volcanoes been disabled in the biome config?
        int biomeId = Biome.getIdForBiome(cmr.getBiomeGenAt(baseX * 16, baseY * 16));
        RealisticBiomeBase realisticBiome = getBiome(biomeId);
        // Do we need to patch the biome?
        if (realisticBiome == null) {
            RealisticBiomePatcher biomePatcher = new RealisticBiomePatcher();
            realisticBiome = biomePatcher.getPatchedRealisticBiome(
                "NULL biome (" + biomeId + ") found when mapping volcanoes.");
        }
        if (!realisticBiome.getConfig().ALLOW_VOLCANOES.get()) return;

        // Have volcanoes been disabled via frequency?
        // Use the global frequency unless the biome frequency has been explicitly set.
        int chance = realisticBiome.getConfig().VOLCANO_CHANCE.get() == -1 ? rtgConfig.VOLCANO_CHANCE.get() : realisticBiome.getConfig().VOLCANO_CHANCE.get();
        if (chance < 1) return;

        // If we've made it this far, let's go ahead and generate the volcano. Exciting!!! :D
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(chance) == 0) {

            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16)) {
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());

                WorldGenVolcano.build(primer, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }

    public void generateMapGen(ChunkPrimer primer, Long seed, World world, IBiomeProviderRTG cmr, Random mapRand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        // Have volcanoes been disabled in the global config?
        if (!rtgConfig.ENABLE_VOLCANOES.get()) return;

        final int mapGenRadius = 5;
        final int volcanoGenRadius = 15;

        mapRand.setSeed(seed);
        long l = (mapRand.nextLong() / 2L) * 2L + 1L;
        long l1 = (mapRand.nextLong() / 2L) * 2L + 1L;

        // Volcanoes generation
        for (int baseX = chunkX - volcanoGenRadius; baseX <= chunkX + volcanoGenRadius; baseX++) {
            for (int baseY = chunkY - volcanoGenRadius; baseY <= chunkY + volcanoGenRadius; baseY++) {
                mapRand.setSeed((long) baseX * l + (long) baseY * l1 ^ seed);
                rMapVolcanoes(primer, world, cmr, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }

    public float rNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
        // we now have both lakes and rivers lowering land
        if (this.noWaterFeatures()) {
            float borderForRiver = border*2;
            if (borderForRiver >1f) borderForRiver = 1;
            river = 1f - (1f-borderForRiver)*(1f-river);
            return terrain.generateNoise(rtgWorld, x, y, border, river);
        }
        float lakeStrength = lakePressure(rtgWorld, x, y, border, lakeInterval, largeBendSize, mediumBendSize, smallBendSize);
        float lakeFlattening = lakeFlattening(lakeStrength, lakeShoreLevel, lakeDepressionLevel);
        // we add some flattening to the rivers. The lakes are pre-flattened.
        float riverFlattening = river*1.25f-0.25f;
        if (riverFlattening <0) riverFlattening = 0;
        if ((river<1)&&(lakeFlattening<1)) {
            riverFlattening = (1f-riverFlattening)/riverFlattening+(1f-lakeFlattening)/lakeFlattening;
            riverFlattening = (1f/(riverFlattening+1f));
        }
        else if (lakeFlattening < riverFlattening) riverFlattening = lakeFlattening;
        // the lakes have to have a little less flattening to avoid the rocky edges
        lakeFlattening = lakeFlattening(lakeStrength, lakeWaterLevel, lakeDepressionLevel);

        if ((river<1)&&(lakeFlattening<1)) {
            river = (1f-river)/river+(1f-lakeFlattening)/lakeFlattening;
            river = (1f/(river+1f));
        }
        else if (lakeFlattening < river) river = lakeFlattening;
        // flatten terrain to set up for the water features
        float terrainNoise = terrain.generateNoise(rtgWorld, x, y, border, riverFlattening);
        // place water features
        return this.erodedNoise(rtgWorld, x, y, river, border, terrainNoise, lakeFlattening);
    }

    public static final float actualRiverProportion = 300f/1600f;
    public float erodedNoise(RTGWorld rtgWorld, int x, int y, float river, float border, float biomeHeight, double lakeFlattening) {

        float r;
        // put a flat spot in the middle of the river
        float riverFlattening = river; // moved the flattening to terrain stage
        if (riverFlattening <0) riverFlattening = 0;

        // check if rivers need lowering
        //if (riverFlattening < actualRiverProportion) {
        r = riverFlattening/actualRiverProportion;
        //}

        //if (1>0) return 62f+r*10f;
        if ((r < 1f && biomeHeight > 57f)) {
            return (biomeHeight * (r)) + ((57f + rtgWorld.simplex.noise2(x / 12f, y / 12f) *
                2f + rtgWorld.simplex.noise2(x / 8f, y / 8f) * 1.5f) * (1f-r));
        }
        else return biomeHeight;
    }

    public float lakeFlattening(RTGWorld rtgWorld,int x, int y, float border) {
        return lakeFlattening(lakePressure(rtgWorld, x, y, border, lakeInterval, largeBendSize, mediumBendSize, smallBendSize), lakeWaterLevel, lakeDepressionLevel);
    }

    public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {
        // this number indicates a multiplier to height
        if (pressure > topLevel) return 1;
        if (pressure<bottomLevel) return 0;
        return (float)Math.pow((pressure-bottomLevel)/(topLevel-bottomLevel),1.0);
    }

    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = this.noWaterFeatures() ? 0f : river;

        if (rtgConfig.ENABLE_RTG_BIOME_SURFACES.get() && this.getConfig().USE_RTG_SURFACES.get()) {

            this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
        else {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
    }

    protected void rReplaceWithRiver(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = this.noWaterFeatures() ? 0f : river;

        if (rtgConfig.ENABLE_RTG_BIOME_SURFACES.get() && this.getConfig().USE_RTG_SURFACES.get()) {

            this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);

            if (rtgConfig.ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES.get()) {

                this.surfaceRiver.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
            }
        }
        else {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
    }

    public float r3Dnoise(float z) {

        return 0f;
    }

    public TerrainBase getTerrain() {

        return this.terrain;
    }

    public SurfaceBase getSurface() {

        return this.surface;
    }

    private class ChunkDecoration {
        ChunkPos chunkLocation;
        DecoBase decoration;
        ChunkDecoration(ChunkPos chunkLocation,DecoBase decoration) {
            this.chunkLocation = chunkLocation;
            this.decoration = decoration;
        }
    }

    public static ArrayList<ChunkDecoration> decoStack = new ArrayList<>();

    public void rDecorate(RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        ArrayList<DecoBase> decos = this.getDecos();

        for (DecoBase deco : decos) {
            decoStack.add(new ChunkDecoration(new ChunkPos(worldX, worldZ), deco));
            if (decoStack.size() > 20) {
                String problem = "";
                for (ChunkDecoration inStack : decoStack) {
                    problem += "" + inStack.chunkLocation.toString() + " " + inStack.decoration.getClass().getSimpleName();
                }
                throw new RuntimeException(problem);
            }
            if (deco.preGenerate(this, rtgWorld, rand, worldX, worldZ, strength, river, hasPlacedVillageBlocks)) {
                deco.generate(this, rtgWorld, rand, worldX, worldZ, strength, river, hasPlacedVillageBlocks);
            }
            decoStack.remove(decoStack.size() - 1);
        }
    }

    public boolean compareTerrain(RTGWorld rtgWorld, TerrainBase oldTerrain) {

        OpenSimplexNoise simplex = new OpenSimplexNoise(4444);
        SimplexCellularNoise cell = new SimplexCellularNoise(4444);
        Random rand = new Random(4444);

        float oldNoise;

        TerrainBase newTerrain = this.initTerrain();
        float newNoise;

        for (int x = -64; x <= 64; x++) {
            for (int z = -64; z <= 64; z++) {

                oldNoise = oldTerrain.generateNoise(rtgWorld, x, z, 0.5f, 0.5f);
                newNoise = newTerrain.generateNoise(rtgWorld, x, z, 0.5f, 0.5f);

                //Logger.info("%s (%d) = oldNoise = %f | newNoise = %f", this.baseBiome.getBiomeName(), Biome.getIdForBiome(this.baseBiome), oldNoise, newNoise);

                if (oldNoise != newNoise) {
                   throw new RuntimeException(
                       "Terrains do not match in biome ID " + Biome.getIdForBiome(this.baseBiome) + " (" + this.baseBiome.getBiomeName() + ")."
                   );
                }
            }
        }

        return true;
    }

    public String configPath() {
        return RTG.configPath + "biomes/" + this.modSlug() + "/" + this.biomeSlug() + ".cfg";
    }

    public String modSlug() {
        throw new RuntimeException("Realistic biomes need a mod slug.");
    }

    public String biomeSlug() {
        return BiomeConfig.formatSlug(this.baseBiome.getBiomeName());
    }

    public static void addModBiomes() {
        ArrayList<IRealisticBiome> realisticBiomes = RealisticBiomeManager.getBiomes();
        RealisticBiomeBase rbb;
        for (IRealisticBiome irb : realisticBiomes) {
            rbb = new RealisticBiomeCreator(irb);
            rbb.decos = irb.getDecos();
            rbb.rtgTrees = irb.getTrees();
        }
    }
}