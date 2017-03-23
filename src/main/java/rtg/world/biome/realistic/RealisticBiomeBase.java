package rtg.world.biome.realistic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.RTG;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.config.RTGConfig;
import rtg.api.util.Accessor;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexCellularNoise;
import rtg.api.util.noise.SimplexOctave;
import rtg.api.util.noise.VoronoiResult;
import rtg.api.world.RTGWorld;
import rtg.util.Logger;
import rtg.util.SaplingUtil;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.BiomeDecoratorRTG;
import rtg.world.biome.IBiomeProviderRTG;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.collection.DecoCollectionBase;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGeneric;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.terrain.TerrainBase;

@SuppressWarnings({"WeakerAccess", "UnusedParameters", "unused"})
public abstract class RealisticBiomeBase {

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

    public int waterSurfaceLakeChance; //Lower = more frequent
    public int lavaSurfaceLakeChance; //Lower = more frequent

    public int waterUndergroundLakeChance; //Lower = more frequent
    public int lavaUndergroundLakeChance; //Lower = more frequent

    public boolean generateVillages;

    public boolean generatesEmeralds;
    public boolean generatesSilverfish;

    public ArrayList<DecoBase> decos;
    public ArrayList<TreeRTG> rtgTrees;

    // lake calculations

    private float lakeInterval = 649.0f;
    private float lakeShoreLevel = 0.035f;
    private float lakeDepressionLevel = 0.15f;// the lakeStrength below which land should start to be lowered
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    private float largeBendSize = 80;
    private float mediumBendSize = 30;
    private float smallBendSize = 12;

    public boolean disallowStoneBeaches = false; // this is for rugged biomes that should have sand beaches
    public boolean disallowAllBeaches = false;

    public RealisticBiomeBase(Biome biome, Biome river) {

        arrRealisticBiomeIds[Biome.getIdForBiome(biome)] = this;

        baseBiome = biome;
        riverBiome = river;
        this.config = new BiomeConfig();
        beachBiome = this.beachBiome();

        rDecorator = new BiomeDecoratorRTG(this);

        waterSurfaceLakeChance = 10;
        lavaSurfaceLakeChance = 0; // Disabled.

        waterUndergroundLakeChance = 1;
        lavaUndergroundLakeChance = 1;

        generateVillages = true;

        generatesEmeralds = false;
        generatesSilverfish = false;
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
        this.lakeShoreLevel *= rtgConfig.lakeSizeMultiplier();
        this.lakeDepressionLevel *= rtgConfig.lakeSizeMultiplier();

        this.largeBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();
        this.mediumBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();
        this.smallBendSize *= rtgConfig.LAKE_SHORE_BENDINESS_MULTIPLIER.get();

        this.init();
    }

    private void init() {
        initConfig();
        this.getConfig().load(this.configPath());
        this.adjustBiomeProperties();
        this.terrain = initTerrain();
        this.surface = initSurface();
        this.surfaceRiver = new SurfaceRiverOasis(config);
        this.surfaceGeneric = new SurfaceGeneric(config, this.surface.getTopBlock(), this.surface.getFillerBlock());
        initDecos();
    }

    public abstract void initConfig();
    public abstract TerrainBase initTerrain();
    public abstract SurfaceBase initSurface();
    public abstract void initDecos();

    public BiomeConfig getConfig() {
        return this.config;
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

    public float rNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
        // we now have both lakes and rivers lowering land
        if (noWaterFeatures) {
            float borderForRiver = border*2;
            if (borderForRiver >1f) borderForRiver = 1;
            river = 1f - (1f-borderForRiver)*(1f-river);
            return terrain.generateNoise(rtgWorld, x, y, border, river);
        }
        float lakeStrength = lakePressure(rtgWorld, x, y, border);
        // set the lakeStrength to match river parameters. Changes are needed because the 
        // function is much steeper at the edges (rivers) than the center (lakes)
        float //lakeFlattening = lakeFlattening(lakeStrength, lakeWaterLevel, lakeDepressionLevel);
                lakeFlattening = lakeFlattening(lakeStrength, lakeShoreLevel, lakeDepressionLevel);
        
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

    public float lakePressure(RTGWorld rtgWorld, int x, int y, float border) {
        if (noLakes) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        rtgWorld.simplex.riverJitter().evaluateNoise((float)x / 240.0, (float)y / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;
        rtgWorld.simplex.mountain().evaluateNoise((float)x / 80.0, (float)y / 80.0, jitter);
        pX += jitter.deltax() * mediumBendSize;
        pY += jitter.deltay() * mediumBendSize;
        rtgWorld.simplex.octave(4).evaluateNoise((float)x / 30.0, (float)y / 30.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pY += jitter.deltay() * smallBendSize;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        VoronoiResult lakeResults = rtgWorld.cell.river().eval((float)pX/ lakeInterval, (float)pY/ lakeInterval);
        float results = 1f-(float)(lakeResults.interiorValue());
        if (results >1.01) throw new RuntimeException("" + lakeResults.shortestDistance+ " , "+lakeResults.nextDistance);
        if (results<-.01) throw new RuntimeException("" + lakeResults.shortestDistance+ " , "+lakeResults.nextDistance);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
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

    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = this.noWaterFeatures ? 0f : river;

        if (rtgConfig.ENABLE_RTG_BIOME_SURFACES.get() && this.getConfig().USE_RTG_SURFACES.get()) {

            this.surface.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
        else {

            this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, rtgWorld, noise, riverRegion, base);
        }
    }

    protected void rReplaceWithRiver(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        float riverRegion = this.noWaterFeatures ? 0f : river;

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

        for (DecoBase deco : this.decos) {
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

    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the decos in the biome.
     */
    public RealisticBiomeBase addDeco(DecoBase deco, boolean allowed) {

        if (allowed) {
            if (!deco.properlyDefined()) throw new RuntimeException(deco.toString());

            if (deco instanceof DecoBaseBiomeDecorations) {

                for (int i = 0; i < this.decos.size(); i++) {

                    if (this.decos.get(i) instanceof DecoBaseBiomeDecorations) {

                        this.decos.remove(i);
                        break;
                    }
                }
            }

            this.decos.add(deco);
        }

        return this;
    }

    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     */
    public RealisticBiomeBase addDeco(DecoBase deco) {
        if (!deco.properlyDefined()) throw new RuntimeException(deco.toString());
        this.addDeco(deco, true);
        return this;
    }

    public void addDecoCollection(DecoCollectionBase decoCollection) {

        // Don't add the desert river deco collection if the user has disabled it.
        if (decoCollection instanceof DecoCollectionDesertRiver) {
            if (!rtgConfig.ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES.get()) {
                return;
            }
        }

        // Add this collection's decos to master deco list.
        if (decoCollection.decos.size() > 0) {
            for (int i = 0; i < decoCollection.decos.size(); i++) {
                this.addDeco(decoCollection.decos.get(i));
            }
        }

        // If there are any tree decos in this collection, then add the individual TreeRTG objects to master tree list.
        if (decoCollection.rtgTrees.size() > 0) {
            for (int i = 0; i < decoCollection.rtgTrees.size(); i++) {
                this.addTree(decoCollection.rtgTrees.get(i));
            }
        }
    }

    /**
     * Adds a tree to the list of RTG trees associated with this biome.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the trees in the biome.
     *
     * @param tree
     * @param allowed
     */
    public void addTree(TreeRTG tree, boolean allowed) {

        if (allowed) {

            // Set the sapling data for this tree before we add it to the list.
            tree.setSaplingBlock(SaplingUtil.getSaplingFromLeaves(tree.getLeavesBlock()));

            /*
             * Make sure all leaves delay their decay to prevent insta-despawning of leaves (e.g. Swamp Willow)
             * The try/catch is a safeguard against trees that use leaves which aren't an instance of BlockLeaves.
             */
            try {
                IBlockState leaves = tree.getLeavesBlock().withProperty(BlockLeaves.CHECK_DECAY, false);
                tree.setLeavesBlock(leaves);
            }
            catch (Exception e) {
                // Do nothing.
            }

            this.rtgTrees.add(tree);
        }
    }

    /**
     * Convenience method for addTree() where 'allowed' is assumed to be true.
     *
     * @param tree
     */
    public void addTree(TreeRTG tree) {

        this.addTree(tree, true);
    }

    /**
     * Returns the number of extra blocks of gold ore to generate in this biome.
     * Defaults to 0, but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     */
    public int getExtraGoldGenCount() {
        return 0;
    }

    /**
     * Returns the minimum Y value at which extra gold ore can generate.
     * Defaults to 32 (BiomeMesa), but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    public int getExtraGoldGenMinHeight() {
        return 32;
    }

    /**
     * Returns the maximum Y value at which extra gold ore can generate.
     * Defaults to 80 (BiomeMesa), but can be overridden by sub-classed biomes.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    public int getExtraGoldGenMaxHeight() {
        return 80;
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

        Biome biome = this.baseBiome;
        int biomeId = Biome.getIdForBiome(biome);
        String biomeName = biome.getBiomeName();

        // Temperature.

        String configTemperature = this.getConfig().TEMPERATURE.get();

        if (!configTemperature.isEmpty()) {

            float biomeTemperature = Float.valueOf(configTemperature);

            if (biomeTemperature > 0.1f && biomeTemperature < 0.2f)
            {
                throw new RuntimeException("Invalid biome temperature for " + biomeName + ".");
            }
            else if (biomeTemperature < -2f || biomeTemperature > 2f) {
                throw new RuntimeException("Biome temperature out of range for " + biomeName + ".");
            }

            try {
                Accessor<Biome, Float> biomeTemp = new Accessor<>("temperature", "field_76750_F");
                biomeTemp.setField(biome, biomeTemperature);

                Logger.info("Set biome temperature to %f for %s", biomeTemperature, biomeName);
            }
            catch (Exception e) {
                Logger.warn("Unable to set biome temperature to %f for %s. Reason: %s", biomeTemperature, biomeName, e.getMessage());
            }
        }
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
}