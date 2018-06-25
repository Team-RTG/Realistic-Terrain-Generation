package rtg.world.biome.realistic;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper.UnableToAccessFieldException;

import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.util.Logger;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.VoronoiResult;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.BiomeDecoratorRTG;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.surface.SurfaceRiverOasis;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.RTGWorld;
import rtg.api.world.gen.RTGChunkGenSettings;


public abstract class RealisticBiomeBase implements IRealisticBiome
{
    private static final String BIOME_CONFIG_SUBDIR = "biomes";

    public static final float actualRiverProportion = 150f/1600f;
    public static final float riverFlatteningAddend = actualRiverProportion / (1f - actualRiverProportion);

// TODO: [1.12] These fields should all be private and final, all should be set in the constructor and #init removed. Use the interface!
    public  final Biome             baseBiome;
    private final ResourceLocation  baseBiomeResLoc;
    private final int               baseBiomeId;
    private final Biome             riverBiome;
    private final Biome             beachBiome;
    public  final BiomeConfig       config;
    private final TerrainBase       terrain;
    private final SurfaceBase       surface;
    private final SurfaceBase       surfaceRiver;
    private final BiomeDecoratorRTG rDecorator;

    // lake calculations
    private final float             lakeInterval        = 649.0f;
    private final float             lakeShoreLevel      = 0.035f;
    private final float             lakeDepressionLevel = 0.15f;// the lakeStrength below which land should start to be lowered
    private final float             largeBendSize       = 80;
    private final float             mediumBendSize      = 30;
    private final float             smallBendSize       = 12;

// TODO: [1.12] To be removed.
    private boolean disallowStoneBeaches = false; // this is for rugged biomes that should have sand beaches
    private boolean disallowAllBeaches   = false;
    private ArrayList<DecoBase> decos;
    private ArrayList<TreeRTG> rtgTrees;
// ...


    public RealisticBiomeBase(Biome biome, Biome river) {

        this.baseBiome = biome;
        ResourceLocation resloc = baseBiome().getRegistryName();
        if (resloc == null) {
            throw new IllegalStateException(String.format("Biome with ID: %s, of class: %s, does not have a registry name set.",
                Biome.getIdForBiome(baseBiome()), baseBiome().getClass().getName()));
        }
        this.baseBiomeResLoc = resloc;
        this.baseBiomeId = Biome.getIdForBiome(biome);
        this.riverBiome = river;
        this.beachBiome = getBeachBiome(getPreferredBeachForBiome(baseBiome));
        this.config = new BiomeConfig(getConfigFile());
        this.terrain = initTerrain();
        this.surface = initSurface();
        this.surfaceRiver = new SurfaceRiverOasis(config);
        this.rDecorator = new BiomeDecoratorRTG(this);

        this.decos = new ArrayList<>();
        this.rtgTrees = new ArrayList<>();

        /*
         *  Disable base biome decorations by default.
         *  This also needs to be here so that ores get generated.
         */
// TODO: [1.12] To be removed. This will not be required with the planned overhaul of the decoration system.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setAllowed(false);
        this.addDeco(decoBaseBiomeDecorations);

        initConfig();

        // Realistic biomes have configs... organic biomes do not.
// TODO: [1.12] To be removed.
        if (this.hasConfig()) { this.getConfig().loadConfig(); }

        this.adjustBiomeProperties();

        initDecos();


// TODO: [1.12] #init functionality should be moved here since it gets called from here.
        this.init();
    }

    protected void init() {
    }

    @Override
    public BiomeConfig getConfig() {
        return this.config;
    }

    @Override
    public boolean hasConfig() {
        return true;
    }

    @Override
    public final Biome baseBiome() {
        return baseBiome;
    }

    @Override
    public Biome riverBiome() {
        return this.riverBiome;
    }

    @Nullable
    @Override
    public IRealisticBiome getRTGRiverBiome() {
        return RTGAPI.getRTGBiome(riverBiome());
    }

    @Override
    public ArrayList<DecoBase> getDecos() {
        return this.decos;
    }

    @Override
    public ArrayList<TreeRTG> getTrees() {
        return this.rtgTrees;
    }

    @Override
    public int waterUndergroundLakeChance() {
        return 1; // Lower equals more frequent.
    }

    @Override
    public int lavaUndergroundLakeChance() {
        return 1; // Lower equals more frequent.
    }


    @Override
    public int waterSurfaceLakeChance() {
        return 10;
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    /*
     * Returns the beach biome to use for this biome.
     * By default, it uses the beach that has been set in the biome config.
     * If automatic beach detection is enabled (-1), it uses the supplied preferred beach.
     */
    protected Biome getBeachBiome(Biome preferredBeach) {

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

    @Override
    public ResourceLocation baseBiomeResLoc() {
        return baseBiomeResLoc;
    }

    @Override
    public int baseBiomeId() {
        return this.baseBiomeId;
    }

    /*
     * Returns the beach biome to use for this biome, with a dynamically-calculated preferred beach.
     */
    @Override
    public Biome beachBiome() {
        return this.beachBiome;
    }

    @Override
    public BiomeDecoratorRTG rDecorator() {
        return rDecorator;
    }

    @Override
    public float rNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        RTGChunkGenSettings settings = rtgWorld.getGeneratorSettings();

        // we now have both lakes and rivers lowering land
// TODO: [1.12] This config setting should be replaced by a generator setting. (Configurable per world, not per biome.)
        if (!this.getConfig().ALLOW_RIVERS.get()) {
            float borderForRiver = border*2;
            if (borderForRiver >1f) borderForRiver = 1;
            river = 1f - (1f-borderForRiver)*(1f-river);
            return terrain.generateNoise(rtgWorld, x, y, border, river);
        }

        float interval        = lakeInterval        * settings.RTGlakeFreqMult;
        float shoreLevel      = lakeShoreLevel      * settings.RTGlakeFreqMult * settings.RTGlakeSizeMult;
        float depressionLevel = lakeDepressionLevel * settings.RTGlakeFreqMult * settings.RTGlakeSizeMult;
        float lgbend          = largeBendSize       * settings.RTGlakeShoreBend;
        float mdbend          = mediumBendSize      * settings.RTGlakeShoreBend;
        float smbend          = smallBendSize       * settings.RTGlakeShoreBend;
        float lakeStrength    = lakePressure(rtgWorld, x, y, border, interval, lgbend, mdbend, smbend);
        float lakeFlattening  = lakeFlattening(lakeStrength, shoreLevel, depressionLevel);

        // combine rivers and lakes
        if ((river<1)&&(lakeFlattening<1)) {
            river = (1f-river)/river+(1f-lakeFlattening)/lakeFlattening;
            river = (1f/(river+1f));
        }
        else if (lakeFlattening < river) river = lakeFlattening;

        // smooth the edges on the top
        river = 1f - river;
        river = river*(river/(river + 0.05f)*(1.05f));
        river = 1f - river;

        // make the water areas flat for water features
        float riverFlattening = river*(1f+riverFlatteningAddend)-riverFlatteningAddend;
        if (riverFlattening <0 ) riverFlattening = 0;

        // flatten terrain to set up for the water features
        float terrainNoise = terrain.generateNoise(rtgWorld, x, y, border, riverFlattening);
        // place water features
        return this.erodedNoise(rtgWorld, x, y, river, border, terrainNoise);
    }

// TODO: [Clean-up] Moved to top of class with rest of fields

    public float erodedNoise(RTGWorld rtgWorld, int x, int y, float river, float border, float biomeHeight) {
        float r;
        // river of actualRiverProportions now maps to 1; TODO
        float riverFlattening = 1f-river;
        riverFlattening = riverFlattening - (1-actualRiverProportion);
        // return biomeHeight if no river effect
        if (riverFlattening <0) return biomeHeight;
        // what was 1 set back to 1;
        riverFlattening /= (actualRiverProportion);

        // back to usual meanings: 1 = no river 0 = river
        r = 1f - riverFlattening;
        // flat spot in middle;
        riverFlattening = riverFlattening *1.4f - 0.4f;
        if (riverFlattening < 0) riverFlattening = 0;

        if ((r < 1f && biomeHeight > 55f)) {
            float irregularity = rtgWorld.simplexInstance(0).noise2f(x / 12f, y / 12f) * 2f + rtgWorld.simplexInstance(0).noise2f(x / 8f, y / 8f);
            // less on the bottom and more on the sides
            irregularity = irregularity*(1+r);
            return (biomeHeight * (r)) + ((55f + irregularity) * 1.0f) * (1f-r);
        }
        else return biomeHeight;
    }

    @Override
    public float lakePressure(IRTGWorld rtgWorld, int x, int y, float border, float lakeInterval, float largeBendSize, float mediumBendSize, float smallBendSize) {

        if (!this.getConfig().ALLOW_SCENIC_LAKES.get()) { return 1f; }

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
        double result = 1.0d - lakeResults.interiorValue();

// TODO: [1.12] Oh look.. It's more RuntimeExceptions, because we should just crash at every opertunity. *sigh*
        if (result >  1.01d) throw new RuntimeException("" + lakeResults.getShortestDistance()+ " , "+lakeResults.getNextDistance());
        if (result < -0.01d) throw new RuntimeException("" + lakeResults.getShortestDistance()+ " , "+lakeResults.getNextDistance());

        return (float)result;
    }

    public float lakeFlattening(float pressure, float shoreLevel, float topLevel) {
        // adjusts the lake pressure to the river numbers. The lake shoreLevel is mapped
        // to become equivalent to actualRiverProportion
        if (pressure > topLevel) return 1;
        if (pressure<shoreLevel){
            return (pressure/shoreLevel)*actualRiverProportion;
        }
        // proportion between top and shore becomes proportion between 1 and actual river
        float proportion = (pressure-shoreLevel)/(topLevel - shoreLevel);
        return actualRiverProportion + proportion*(1f-actualRiverProportion);
        //return (float)Math.pow((pressure-shoreLevel)/(topLevel-shoreLevel),1.0);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = !this.getConfig().ALLOW_RIVERS.get() ? 0f : river;

        if (!(RTGAPI.config().DISABLE_RTG_BIOME_SURFACES.get() || this.getConfig().DISABLE_RTG_SURFACES.get())) {

            this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
    }

    protected void rReplaceWithRiver(ChunkPrimer primer, int i, int j, int x, int y, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = !this.getConfig().ALLOW_RIVERS.get() ? 0f : river;
        if (!(RTGAPI.config().DISABLE_RTG_BIOME_SURFACES.get() || this.getConfig().DISABLE_RTG_SURFACES.get())) {

            this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
            if (RTGAPI.config().ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES.get()) {
                this.surfaceRiver.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
            }
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

    private class ChunkDecoration {
        BlockPos chunkLocation;
        DecoBase decoration;
        ChunkDecoration(BlockPos chunkLocation,DecoBase decoration) {
            this.chunkLocation = chunkLocation;
            this.decoration = decoration;
        }
    }

    public static ArrayList<ChunkDecoration> decoStack = new ArrayList<>();

    @Override
// TODO: [1.12] This should be a proxy call to RTGBiomeDecorator#decorate
    public void rDecorate(RTGWorld rtgWorld, Random rand, BlockPos pos, float strength, float river, boolean hasPlacedVillageBlocks) {

        ArrayList<DecoBase> decos = this.getDecos();

        for (DecoBase deco : decos) {
            decoStack.add(new ChunkDecoration(pos, deco));
            if (decoStack.size() > 20) {
                String problem = "";
                for (ChunkDecoration inStack : decoStack) {
                    problem += "" + inStack.chunkLocation.toString() + " " + inStack.decoration.getClass().getSimpleName();
                }
                throw new RuntimeException(problem);
            }
            if (deco.preGenerate(river)) {
// TODO: [1.12] Decorators need to be updated to accept BlockPos
                deco.generate(this, rtgWorld, rand, pos.getX(), pos.getZ(), strength, river, hasPlacedVillageBlocks);
            }
            decoStack.remove(decoStack.size() - 1);
        }
    }

// TODO: [1.12] Why are we adjusting biome temperatures again? Currently only used in Taigas. 0.2f -> 0.25f
    private void adjustBiomeProperties() {

        if (this.getConfig().USE_CUSTOM_BIOME_TEMPERATURE.get()) {

            float biometemp = this.getConfig().BIOME_TEMPERATURE.get();
            biometemp = (biometemp > 2.0f) ? 2.0f : ((biometemp < -2.0f) ? -2.0f : biometemp);
            biometemp = (biometemp > 0.1f && biometemp < 0.2f) ? 0.2f : biometemp;

            try {
                ReflectionHelper.setPrivateValue(Biome.class, this.baseBiome, biometemp, "temperature", "field_76750_F");
                Logger.info("Set biome temperature for {} to: {}", this.baseBiome.getBiomeName(), this.baseBiome.getDefaultTemperature());
            } catch (UnableToAccessFieldException ex) {
                Logger.error("Unable to set biome temperature for {} to: {}.", this.baseBiome.getBiomeName(), biometemp);
                ex.printStackTrace();
            }
        }
    }

    private File getConfigFile() {
        return RTGAPI.getConfigPath()
            .resolve(BIOME_CONFIG_SUBDIR)
            .resolve(baseBiomeResLoc().getResourceDomain())
            .resolve(baseBiomeResLoc().getResourcePath()+".cfg")
            .toFile();
    }

    private Biome getPreferredBeachForBiome(Biome biome) {

        float height = biome.getBaseHeight() + (biome.getHeightVariation() * 2f);
        // Use a cold beach if the temperature is low enough or the biome has the BiomeDictionary tag 'SNOWY',
        // or use a stone beach if this is a mountainous biome or a Taiga variant, or use normal beach.
        return (biome.getDefaultTemperature() <= 0.05f || BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY))
            ? Biomes.COLD_BEACH
            : (height > 1.5f || isTaigaBiome(biome) ? Biomes.STONE_BEACH : Biomes.BEACH);
    }

    @Override
    public boolean disallowBeaches() {
        return disallowAllBeaches;
    }

    @Override
    public boolean disallowStoneBeaches() {
        return disallowStoneBeaches;
    }

    private boolean isTaigaBiome(Biome biome) {
        return BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD)
            && BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS)
            && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST);
    }
}