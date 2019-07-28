package rtg.api.world.biome;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraftforge.common.BiomeDictionary;
import rtg.RTG;
import rtg.RTGConfig;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.VoronoiResult;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.surface.SurfaceRiverOasis;
import rtg.api.world.terrain.TerrainBase;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;


public abstract class RealisticBiomeBase implements IRealisticBiome {

    private static final String BIOME_CONFIG_SUBDIR = "biomes";
    protected static final double GENERAL_SNOWLAYER_REDUCTION = 0.75d;

    private final Biome baseBiome;
    private final ResourceLocation baseBiomeResLoc;
    private final int baseBiomeId;
    private final RiverType riverType;
    private final BeachType beachType;
    private final BiomeConfig config;
    private final TerrainBase terrain;
    private final SurfaceBase surface;
    private final SurfaceBase surfaceRiver;

    private Collection<DecoBase> decos;
    // TODO: [1.12] To be removed. All trees need to be a Deco and be added through #addDeco.
    @Deprecated
    private Collection<TreeRTG> rtgTrees;


    public RealisticBiomeBase(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeBase(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    public RealisticBiomeBase(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        ResourceLocation resloc = baseBiome.getRegistryName();
        if (resloc == null) {
            throw new IllegalStateException(String.format("Biome with ID: %s, of class: %s, does not have a registry name set.",
                Biome.getIdForBiome(baseBiome), baseBiome.getClass().getName()));
        }

        this.baseBiome = baseBiome;
        this.baseBiomeResLoc = resloc;
        this.baseBiomeId = Biome.getIdForBiome(baseBiome);
        this.riverType = riverType;
        this.beachType = beachType;
        this.config = new BiomeConfig(getConfigFile());
        this.terrain = initTerrain();
        this.surface = initSurface();
        this.surfaceRiver = new SurfaceRiverOasis(config);
        // TODO [1.12] Ultimately it would be best for this Collection to be hash-based with a Deco's hash determined by its internal data.
        //             Thus there could be, for example, multiple instances of a flower deco with differing hashes based on different flower
        //             types, but not multiple identical decos.
        this.decos = new ArrayList<>();
        this.rtgTrees = new ArrayList<>();

        initConfig();

        getConfig().loadConfig();

        initDecos();

        overrideDecorations();
    }

    @Override
    public BiomeConfig getConfig() {
        return this.config;
    }

    @Override
    public final Biome baseBiome() {
        return baseBiome;
    }

    @Override
    public RiverType getRiverType() {
        return riverType;
    }

    @Override
    public BeachType getBeachType() {
        return beachType;
    }

    @Override
    public Biome preferredBeach() {
        return this.beachType.getBiome();
    }

    @Override
    public IRealisticBiome getRiverBiome() {
        return this.riverType.getRTGBiome();
    }

    @Override
    public IRealisticBiome getBeachBiome() {
        IRealisticBiome rbb = RTGAPI.getRTGBiome(Biome.getIdForBiome(this.preferredBeach()));
        int configBiomeId = this.getConfig().BEACH_BIOME.get();
        if (configBiomeId > -1) {
            rbb = RTGAPI.getRTGBiome(configBiomeId);
        }
        return rbb;
    }

    @Override
    public Collection<DecoBase> getDecos() {
        return this.decos;
    }

    @Override
    public Collection<TreeRTG> getTrees() {
        return this.rtgTrees;
    }

    @Override
    public ResourceLocation baseBiomeResLoc() {
        return baseBiomeResLoc;
    }

    @Override
    public int baseBiomeId() {
        return this.baseBiomeId;
    }

    @Override
    public float rNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        // we now have both lakes and rivers lowering land
        if (!this.getConfig().ALLOW_RIVERS.get()) {
            float borderForRiver = border * 2;
            if (borderForRiver > 1f) {
                borderForRiver = 1;
            }
            river = 1f - (1f - borderForRiver) * (1f - river);
            return terrain.generateNoise(rtgWorld, x, y, border, river);
        }

        float lakeStrength = lakePressure(rtgWorld, x, y, border, rtgWorld.getLakeFrequency(),
            rtgWorld.getLakeBendSizeLarge(), rtgWorld.getLakeBendSizeMedium(), rtgWorld.getLakeBendSizeSmall());
        float lakeFlattening = lakeFlattening(lakeStrength, rtgWorld.getLakeShoreLevel(), rtgWorld.getLakeDepressionLevel());

        // combine rivers and lakes
        if ((river < 1) && (lakeFlattening < 1)) {
            river = (1f - river) / river + (1f - lakeFlattening) / lakeFlattening;
            river = (1f / (river + 1f));
        }
        else if (lakeFlattening < river) {
            river = lakeFlattening;
        }

        // smooth the edges on the top
        river = 1f - river;
        river = river * (river / (river + 0.05f) * (1.05f));
        river = 1f - river;

        // make the water areas flat for water features
        float riverFlattening = river * (1f + RTGWorld.RIVER_FLATTENING_ADDEND) - RTGWorld.RIVER_FLATTENING_ADDEND;
        if (riverFlattening < 0) {
            riverFlattening = 0;
        }

        // flatten terrain to set up for the water features
        float terrainNoise = terrain.generateNoise(rtgWorld, x, y, border, riverFlattening);
        // place water features
        return this.erodedNoise(rtgWorld, x, y, river, border, terrainNoise);
    }

    public float erodedNoise(RTGWorld rtgWorld, int x, int y, float river, float border, float biomeHeight) {
        float r;
        // river of actualRiverProportions now maps to 1;
        float riverFlattening = 1f - river;
        riverFlattening = riverFlattening - (1 - RTGWorld.ACTUAL_RIVER_PROPORTION);
        // return biomeHeight if no river effect
        if (riverFlattening < 0) {
            return biomeHeight;
        }
        // what was 1 set back to 1;
        riverFlattening /= RTGWorld.ACTUAL_RIVER_PROPORTION;

        // back to usual meanings: 1 = no river 0 = river
        r = 1f - riverFlattening;

        if ((r < 1f && biomeHeight > 55f)) {
            float irregularity = rtgWorld.simplexInstance(0).noise2f(x / 12f, y / 12f) * 2f + rtgWorld.simplexInstance(0).noise2f(x / 8f, y / 8f);
            // less on the bottom and more on the sides
            irregularity = irregularity * (1 + r);
            return (biomeHeight * (r)) + ((55f + irregularity) * 1.0f) * (1f - r);
        }
        else {
            return biomeHeight;
        }
    }

    @Override
    public float lakePressure(RTGWorld rtgWorld, int x, int y, float border, float lakeInterval, float largeBendSize, float mediumBendSize, float smallBendSize) {

        if (!this.getConfig().ALLOW_SCENIC_LAKES.get()) {
            return 1f;
        }

        double pX = x;
        double pY = y;
        ISimplexData2D jitterData = SimplexData2D.newDisk();

        rtgWorld.simplexInstance(1).multiEval2D(x / 240.0d, y / 240.0d, jitterData);
        pX += jitterData.getDeltaX() * largeBendSize;
        pY += jitterData.getDeltaY() * largeBendSize;

        rtgWorld.simplexInstance(0).multiEval2D(x / 80.0d, y / 80.0d, jitterData);
        pX += jitterData.getDeltaX() * mediumBendSize;
        pY += jitterData.getDeltaY() * mediumBendSize;

        rtgWorld.simplexInstance(4).multiEval2D(x / 30.0d, y / 30.0d, jitterData);
        pX += jitterData.getDeltaX() * smallBendSize;
        pY += jitterData.getDeltaY() * smallBendSize;

        VoronoiResult lakeResults = rtgWorld.cellularInstance(0).eval2D(pX / lakeInterval, pY / lakeInterval);
        return (float)(1.0d - lakeResults.interiorValue());
    }

    public float lakeFlattening(float pressure, float shoreLevel, float topLevel) {
        // adjusts the lake pressure to the river numbers. The lake shoreLevel is mapped
        // to become equivalent to actualRiverProportion
        if (pressure > topLevel) {
            return 1;
        }
        if (pressure < shoreLevel) {
            return (pressure / shoreLevel) * RTGWorld.ACTUAL_RIVER_PROPORTION;
        }
        // proportion between top and shore becomes proportion between 1 and actual river
        float proportion = (pressure - shoreLevel) / (topLevel - shoreLevel);
        return RTGWorld.ACTUAL_RIVER_PROPORTION + proportion * (1f - RTGWorld.ACTUAL_RIVER_PROPORTION);
        //return (float)Math.pow((pressure-shoreLevel)/(topLevel-shoreLevel),1.0);
    }

    @Override
    public void rReplace(ChunkPrimer primer, BlockPos blockPos, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {
        rReplace(primer, blockPos.getX(), blockPos.getZ(), x, y, depth, rtgWorld, noise, river, base);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {
        if (RTG.surfacesDisabled() || this.getConfig().DISABLE_RTG_SURFACES.get()) { return; }
        float riverRegion = !this.getConfig().ALLOW_RIVERS.get() ? 0f : river;
        this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
    }

    protected void rReplaceWithRiver(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {
        if (RTG.surfacesDisabled() || this.getConfig().DISABLE_RTG_SURFACES.get()) { return; }
        float riverRegion = !this.getConfig().ALLOW_RIVERS.get() ? 0f : river;
        this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        if (RTGConfig.lushRiverbanksInDesert()) {
            this.surfaceRiver.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
    }

    @Override
    public TerrainBase terrain() {
        return this.terrain;
    }

    @Override
    public SurfaceBase surface() {
        return this.surface;
    }

    @Override
    public double waterLakeMult() {
        return this.getConfig().SURFACE_WATER_LAKE_MULT.get();
    }

    @Override
    public double lavaLakeMult() {
        return this.getConfig().SURFACE_LAVA_LAKE_MULT.get();
    }

    private File getConfigFile() {
        return RTGAPI.getConfigPath()
            .resolve(BIOME_CONFIG_SUBDIR)
            .resolve(baseBiomeResLoc().getNamespace())
            .resolve(baseBiomeResLoc().getPath() + ".cfg")
            .toFile();
    }

    protected BeachType determineBeachType() {

        if (baseBiome().getDefaultTemperature() <= 0.05f || BiomeDictionary.hasType(baseBiome(), BiomeDictionary.Type.SNOWY)) {
            return BeachType.COLD;
        }

        float height = baseBiome().getBaseHeight() + (baseBiome().getHeightVariation() * 2f);
        if (height > 1.5f || isTaigaBiome(baseBiome())) {
            return BeachType.STONE;
        }

        return BeachType.NORMAL;
    }

    private boolean isTaigaBiome(Biome biome) {
        return BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD)
            && BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS)
            && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST);
    }

    public enum BeachType {
        NORMAL,
        STONE,
        COLD;

        private IRealisticBiome rtgBiome;
        private boolean locked = false;

        public Biome getBiome() {
            return (this == STONE) ? Biomes.STONE_BEACH : ((this == COLD) ? Biomes.COLD_BEACH : Biomes.BEACH);
        }

        public IRealisticBiome getRTGBiome() {
            return rtgBiome;
        }

        public IRealisticBiome setRTGBiome(IRealisticBiome rtgBiome) {
            if (!locked) {
                this.rtgBiome = rtgBiome;
                this.locked = true;
            }
            return rtgBiome;
        }

        public BeachType getTypeFromBiome(Biome beachBiome) {
            return (beachBiome == Biomes.STONE_BEACH) ? STONE : ((beachBiome == Biomes.COLD_BEACH) ? COLD : NORMAL);
        }
    }

    public enum RiverType {
        NORMAL,
        FROZEN;

        private IRealisticBiome rtgBiome;
        private boolean locked = false;

        public static RiverType getTypeFromBiome(Biome riverBiome) {
            return (riverBiome == Biomes.FROZEN_RIVER) ? FROZEN : NORMAL;
        }

        public Biome getBiome() {
            return this == NORMAL ? Biomes.RIVER : Biomes.FROZEN_RIVER;
        }

        public IRealisticBiome getRTGBiome() {
            return rtgBiome;
        }

        public IRealisticBiome setRTGBiome(IRealisticBiome rtgBiome) {
            if (!locked) {
                this.rtgBiome = rtgBiome;
                this.locked = true;
            }
            return rtgBiome;
        }
    }
}