package rtg.world.biome.realistic;

import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.ReflectionHelper.UnableToAccessFieldException;

import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.config.RTGConfig;
import rtg.api.util.Logger;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexCellularNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.BiomeDecoratorRTG;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.biome.RealisticBiomeManager;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.gen.GenSettingsRepo;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.surface.SurfaceGeneric;
import rtg.api.world.surface.SurfaceRiverOasis;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.TerrainOrganic;
import rtg.world.RTGWorld;
import rtg.world.biome.organic.OrganicBiome;
import rtg.api.world.gen.ChunkProviderSettingsRTG;


//@SuppressWarnings({"WeakerAccess", "UnusedParameters", "unused"})
public abstract class RealisticBiomeBase implements IRealisticBiome {

    protected RTGConfig rtgConfig = RTGAPI.config();
    private static final RealisticBiomeBase[] arrRealisticBiomeIds = new RealisticBiomeBase[256];
    private static final String BIOME_CONFIG_SUBDIR = "biomes";

    public final Biome baseBiome;
    public final Biome riverBiome;
    public final Biome beachBiome;
    public BiomeConfig config;
    public TerrainBase terrain;
    public SurfaceBase surface;
    public SurfaceBase surfaceRiver;
    public SurfaceBase surfaceGeneric;
    public BiomeDecoratorRTG rDecorator;

    private ArrayList<DecoBase> decos;
    private ArrayList<TreeRTG> rtgTrees;

    // lake calculations

// TODO [Generator settings] make these fields final
    private float lakeInterval = 649.0f;
    private float lakeShoreLevel = 0.035f;
    private float lakeDepressionLevel = 0.15f;// the lakeStrength below which land should start to be lowered

    private float largeBendSize = 80;
    private float mediumBendSize = 30;
    private float smallBendSize = 12;
// ...

    public boolean disallowStoneBeaches = false; // this is for rugged biomes that should have sand beaches
    public boolean disallowAllBeaches = false;

    public RealisticBiomeBase(Biome biome, Biome river) {

        arrRealisticBiomeIds[Biome.getIdForBiome(biome)] = this;

        baseBiome = biome;
        riverBiome = river;
        this.config = new BiomeConfig(this.configFile());
        beachBiome = this.beachBiome();

        rDecorator = new BiomeDecoratorRTG(this);

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
// TODO: [Generator Settings] To be removed. Functionality replaced in rNoise
        this.lakeInterval *= rtgConfig.LAKE_FREQUENCY_MULTIPLIER.get();
        this.lakeShoreLevel *= this.lakeSizeMultiplier();
        this.lakeDepressionLevel *= this.lakeSizeMultiplier();

// TODO: [Generator Settings] To be removed. Functionality replaced in rNoise
        this.largeBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();
        this.mediumBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();
        this.smallBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();

        this.init();
    }

    private float lakeSizeMultiplier() {
// TODO: [1.12] THIS is confusing. Find a better way of expressing the required variance in the generator settings.
        // With the river system changing frequency also shinks size and that will confuse the heck out of users.
        return RTGAPI.config().LAKE_SIZE_MULTIPLIER.get() * RTGAPI.config().LAKE_FREQUENCY_MULTIPLIER.get();
    }

    protected void init() {
        initConfig();

        // Realistic biomes have configs... organic biomes do not.
        if (this.hasConfig()) {
            this.getConfig().loadConfig();
        }

        this.adjustBiomeProperties();
        this.terrain = checkTerrain(initTerrain());
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
    public boolean hasConfig() {
        return true;
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
    public int lavaSurfaceLakeChance() {
        return 0;
    }

    @Nullable
    public static RealisticBiomeBase getBiome(int id) {
        return arrRealisticBiomeIds[id];
    }

// TODO: [Clean-up] Remove, unused
    public static RealisticBiomeBase[] arr() {
        return arrRealisticBiomeIds;
    }

    public static boolean isRealisticBiome(int id) {
        RealisticBiomeBase rbb = getBiome(id);
        return !(rbb instanceof OrganicBiome);
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
    @Override
    public Biome beachBiome() {
        return this.beachBiome(this.getPreferredBeachForBiome(this.baseBiome));
    }

    @Override
    public BiomeDecoratorRTG rDecorator() {
        return rDecorator;
    }

    public float rNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

        ChunkProviderSettingsRTG settings = GenSettingsRepo.getSettingsForWorld(rtgWorld.world);

        // we now have both lakes and rivers lowering land
        if (!this.getConfig().ALLOW_RIVERS.get()) {
            float borderForRiver = border*2;
            if (borderForRiver >1f) borderForRiver = 1;
            river = 1f - (1f-borderForRiver)*(1f-river);
            return terrain.generateNoise(rtgWorld, x, y, border, river);
        }
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//      float interval        = lakeInterval        *= settings.RTGlakeFreqMult;
//      float shoreLevel      = lakeShoreLevel      *= settings.RTGlakeFreqMult * settings.RTGlakeSizeMult;
//      float depressionLevel = lakeDepressionLevel *= settings.RTGlakeFreqMult * settings.RTGlakeSizeMult;
//      float lgbend          = largeBendSize       *= settings.RTGlakeShoreBend;
//      float mdbend          = mediumBendSize      *= settings.RTGlakeShoreBend;
//      float smbend          = smallBendSize       *= settings.RTGlakeShoreBend;
//      float lakeStrength = lakePressure(rtgWorld, x, y, border, interval, lgbend, mdbend, smbend);
        float lakeStrength = lakePressure(rtgWorld, x, y, border, lakeInterval, largeBendSize, mediumBendSize, smallBendSize);
        // set the lakeStrength to match river parameters. Changes are needed because the
        // function is much steeper at the edges (rivers) than the center (lakes)
        float //lakeFlattening = lakeFlattening(lakeStrength, lakeWaterLevel, lakeDepressionLevel);
            lakeFlattening = lakeFlattening(lakeStrength, lakeShoreLevel, lakeDepressionLevel);
// TODO: [Generator Settings] Update this to use the generator setting and not the config setting
//          lakeFlattening = lakeFlattening(lakeStrength, shoreLevel, depressionLevel);

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

// TODO: [Clean-up] To be removed
        /*if (riverFlattening <0) riverFlattening = 0;
        if (riverFlattening >1) riverFlattening = 1;
        if ((river<1)&&(lakeFlattening<1)) {
            riverFlattening = (1f-riverFlattening)/riverFlattening+(1f-lakeFlattening)/lakeFlattening;
            riverFlattening = (1f/(riverFlattening+1f));
        }
        else if (lakeFlattening < riverFlattening) riverFlattening = lakeFlattening;
        // the lakes have to have a little less flattening to avoid the rocky edges
        lakeFlattening = lakeFlattening(lakeStrength, lakeWaterLevel, lakeShoreLevel);*/


        // flatten terrain to set up for the water features
        float terrainNoise = terrain.generateNoise(rtgWorld, x, y, border, riverFlattening);
        // place water features
        return this.erodedNoise(rtgWorld, x, y, river, border, terrainNoise);
    }

// TODO: [Clean-up] Moved to top of class with rest of fields
    public static final float actualRiverProportion = 150f/1600f;
    public static final float riverFlatteningAddend = (actualRiverProportion)/(1f-actualRiverProportion);

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
            float irregularity = rtgWorld.simplex.noise2(x / 12f, y / 12f) *
                2f + rtgWorld.simplex.noise2(x / 8f, y / 8f);
            // less on the bottom and more on the sides
            irregularity = irregularity*(1+r);
            return (biomeHeight * (r)) + ((55f + irregularity) * 1.0f) * (1f-r);
        }
        else return biomeHeight;
    }

    public float lakeFlattening(float pressure, float shoreLevel, float topLevel) {
        // adjusts the lake pressure to the river numbers. The lake shoreLevel is mapped
        // to become equivalent to actualRiverProportion
        if (pressure > topLevel) return 1;
        if (pressure<shoreLevel){
            return (pressure/shoreLevel)*actualRiverProportion;
        };
        // proportion between top and shore becomes proportion between 1 and actual river
        float proportion = (pressure-shoreLevel)/(topLevel - shoreLevel);
        return actualRiverProportion + proportion*(1f-actualRiverProportion);
        //return (float)Math.pow((pressure-shoreLevel)/(topLevel-shoreLevel),1.0);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = !this.getConfig().ALLOW_RIVERS.get() ? 0f : river;

        if (rtgConfig.DISABLE_RTG_BIOME_SURFACES.get() || this.getConfig().DISABLE_RTG_SURFACES.get()) {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
        else {

            this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
    }

    protected void rReplaceWithRiver(ChunkPrimer primer, int i, int j, int x, int y, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = !this.getConfig().ALLOW_RIVERS.get() ? 0f : river;

        if (rtgConfig.DISABLE_RTG_BIOME_SURFACES.get() || this.getConfig().DISABLE_RTG_SURFACES.get()) {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
        else {

            this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);

            if (rtgConfig.ENABLE_LUSH_RIVER_BANK_SURFACES_IN_HOT_BIOMES.get()) {

                this.surfaceRiver.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
            }
        }
    }

    public float r3Dnoise(float z) {

        return 0f;
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
    public SurfaceBase surfaceGeneric() {
        return this.surfaceGeneric;
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

    private void adjustBiomeProperties() {

        float biometemp = this.getConfig().BIOME_TEMPERATURE.get();
        biometemp = (biometemp > 2.0f) ? 2.0f : ((biometemp < -2.0f) ? -2.0f : biometemp) ;
        biometemp = (biometemp > 0.1f && biometemp < 0.2f) ? 0.2f : biometemp;

        try {
            ReflectionHelper.setPrivateValue(Biome.class, this.baseBiome, biometemp, "temperature", "field_76750_F");
            Logger.info("Set biome temperature for {} to: {}", this.baseBiome.getBiomeName(), this.baseBiome.getDefaultTemperature());
        }
        catch (UnableToAccessFieldException ex) {
            Logger.error("Unable to set biome temperature for {} to: {}.", this.baseBiome.getBiomeName(), biometemp);
            ex.printStackTrace();
        }
    }

    private File configFile() {
        return RTGAPI.configPath.resolve(BIOME_CONFIG_SUBDIR).resolve(this.modSlug()).resolve(this.biomeSlug()+".cfg").toFile();
    }

    @Override
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

    protected boolean organicTerrain() {
        return !rtgConfig.DISABLE_RTG_TERRAIN.get() || !this.config.DISABLE_RTG_TERRAIN.get();
    }

    protected TerrainBase checkTerrain(TerrainBase terrainIn) {

        return organicTerrain() ? new TerrainOrganic() : terrainIn;
    }

    private Biome getPreferredBeachForBiome(Biome biome) {

        /*
         * Some of this code is from Climate Control, and it's still a bit crude. - Zeno
         * Some of this code is from Pink's brain, and it's also a bit crude. - Pink
         */

        float height = biome.getBaseHeight() + (biome.getHeightVariation() * 2f);
        float temp = biome.getDefaultTemperature();

        // Use a cold beach if the temperature is low enough; otherwise, just use a normal beach.
        Biome beach = (temp <= 0.05f) ? Biomes.COLD_BEACH : Biomes.BEACH;

        // If this is a mountainous biome or a Taiga variant, then let's use a stone beach.
        if ((height > (1.0f + 0.5f) && temp > 0.05f) || this.isTaigaBiome(biome)) {
            beach = Biomes.STONE_BEACH;
        }

        // Snowy biomes should always use cold beach; otherwise, the transition looks too abrupt.
        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY)) {
            beach = Biomes.COLD_BEACH;
        }

        return beach;
    }

// TODO: [Clean-up] BiomeDictionary.Type.SNOWY is not common to all taiga biomes. This method should be renamed to be less ambiguous or the SNOWY type should be removed from the check
    private boolean isTaigaBiome(Biome biome) {
        return BiomeDictionary.hasType(biome, BiomeDictionary.Type.COLD)
            && BiomeDictionary.hasType(biome, BiomeDictionary.Type.CONIFEROUS)
            && BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)
            && !BiomeDictionary.hasType(biome, BiomeDictionary.Type.SNOWY);
    }
}