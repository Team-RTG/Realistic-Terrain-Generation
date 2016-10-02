package rtg.world.biome.realistic;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.config.rtg.ConfigRTG;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SaplingUtil;
import rtg.util.SimplexOctave;
import rtg.world.biome.BiomeAnalyzer;
import rtg.world.biome.BiomeDecoratorRTG;
import rtg.world.biome.IBiomeProviderRTG;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.collection.DecoCollectionBase;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceGeneric;
import rtg.world.gen.terrain.TerrainBase;

@SuppressWarnings({"WeakerAccess", "UnusedParameters", "unused"})
public class RealisticBiomeBase {

    private static final RealisticBiomeBase[] arrRealisticBiomeIds =
        new RealisticBiomeBase[256];
    public final Biome baseBiome;
    public final Biome riverBiome;
    public final Biome beachBiome;
    public BiomeConfig config;

    public TerrainBase terrain;

    public SurfaceBase[] surfaces;
    public int surfacesLength;
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

    private float lakeInterval = 989.0f;
    private float lakeShoreLevel = 0.15f;
    private float lakeWaterLevel = 0.11f;// the lakeStrength below which things should be below water
    private float lakeDepressionLevel = 0.30f;// the lakeStrength below which land should start to be lowered
    public boolean noLakes = false;
    public boolean noWaterFeatures = false;

    private float largeBendSize = 100;
    private float mediumBendSize = 40;
    private float smallBendSize = 15;

    public boolean disallowStoneBeaches = false; // this is for rugged biomes that should have sand beaches
    public boolean disallowAllBeaches = false;

    public RealisticBiomeBase(BiomeConfig config, Biome biome) {

        this(config, biome, Biomes.RIVER);
    }

    public RealisticBiomeBase(BiomeConfig config, Biome biome, Biome river) {

        if (config == null) throw new RuntimeException("Biome config cannot be NULL when instantiating a realistic biome.");
        this.config = config;
        arrRealisticBiomeIds[Biome.getIdForBiome(biome)] = this;
        baseBiome = biome;
        riverBiome = river;
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
        decoBaseBiomeDecorations.allowed = false;
        this.addDeco(decoBaseBiomeDecorations);
        // set the water feature constants with the config changes
        this.lakeInterval           *= ConfigRTG.lakeFrequencyMultiplier;
        this.lakeWaterLevel *= ConfigRTG.lakeSizeMultiplier();
        this.lakeShoreLevel *= ConfigRTG.lakeSizeMultiplier();
        this.lakeDepressionLevel *= ConfigRTG.lakeSizeMultiplier();

        this.largeBendSize *= ConfigRTG.lakeFrequencyMultiplier;
        this.mediumBendSize *= ConfigRTG.lakeFrequencyMultiplier;
        this.smallBendSize *= ConfigRTG.lakeFrequencyMultiplier;
    }

    public static RealisticBiomeBase getBiome(int id) { return arrRealisticBiomeIds[id]; }
    public static RealisticBiomeBase[] arr() { return arrRealisticBiomeIds; }

    public RealisticBiomeBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase[] s) {

        this(config, b, riverbiome);

        terrain = t;

        surfaces = s;
        surfacesLength = s.length;
    }

    public RealisticBiomeBase(BiomeConfig config, Biome b, Biome riverbiome, TerrainBase t, SurfaceBase s) {

        this(config, b, riverbiome, t, new SurfaceBase[]{s});

        surfaceGeneric = new SurfaceGeneric(config, s.getTopBlock(), s.getFillerBlock());
    }

    /*
     * Returns the beach biome to use for this biome.
     * By default, it uses the beach that has been set in the biome config.
     * If automatic beach detection is enabled (-1), it uses the supplied preferred beach.
     */
    protected Biome beachBiome(Biome preferredBeach) {

        Biome beach;
        int configBeachId = this.config._int(BiomeConfig.beachBiomeId);

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

    public void rMapVolcanoes(
        ChunkPrimer primer, World world, IBiomeProviderRTG cmr,
        Random mapRand, int baseX, int baseY, int chunkX, int chunkY,
        OpenSimplexNoise simplex, CellNoise cell, float noise[]) {

        // Have volcanoes been disabled in the global config?
        if (!ConfigRTG.enableVolcanoes) return;

        // Have volcanoes been disabled in the biome config?
        int biomeId = Biome.getIdForBiome(cmr.getBiomeGenAt(baseX * 16, baseY * 16));
        RealisticBiomeBase realisticBiome = getBiome(biomeId);
        // Do we need to patch the biome?
        if (realisticBiome == null) {
            RealisticBiomePatcher biomePatcher = new RealisticBiomePatcher();
            realisticBiome = biomePatcher.getPatchedRealisticBiome(
                "NULL biome (" + biomeId + ") found when mapping volcanoes.");
        }
        if (!realisticBiome.config._boolean(BiomeConfig.allowVolcanoesId)) return;

        // Have volcanoes been disabled via frequency?
        // Use the global frequency unless the biome frequency has been explicitly set.
        int chance = realisticBiome.config._int(BiomeConfig.volcanoChanceId) == -1 ? ConfigRTG.volcanoChance : realisticBiome.config._int(BiomeConfig.volcanoChanceId);
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
        if (!ConfigRTG.enableVolcanoes) return;

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

    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // we now have both lakes and rivers lowering land
        if (noWaterFeatures) {
            float borderForRiver = border*2;
            if (borderForRiver >1f) borderForRiver = 1;
            river = 1f - (1f-borderForRiver)*(1f-river);
            return terrain.generateNoise(simplex, cell, x, y, border, river);
        }
        float lakeStrength = lakePressure(simplex,cell,x,y,border);
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
        float terrainNoise = terrain.generateNoise(simplex, cell, x, y, border, riverFlattening);
        // place water features
        return this.erodedNoise(simplex, cell, x, y, river, border, terrainNoise,lakeFlattening);
    }

    public static final float actualRiverProportion = 300f/1600f;
    public float erodedNoise(
        OpenSimplexNoise simplex, CellNoise simplexCell, int x, int y, float river,
        float border, float biomeHeight, double lakeFlattening) {

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
            return (biomeHeight * (r)) + ((57f + simplex.noise2(x / 12f, y / 12f) *
                2f + simplex.noise2(x / 8f, y / 8f) * 1.5f) * (1f-r));
        }
        else return biomeHeight;
    }

    public float lakeFlattening(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        return lakeFlattening(lakePressure(simplex, simplexCell, x, y, border), lakeWaterLevel, lakeDepressionLevel);
    }

    public float lakePressure(OpenSimplexNoise simplex, CellNoise simplexCell,int x, int y, float border) {
        if (noLakes) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        simplex.riverJitter().evaluateNoise((float)x / 240.0, (float)y / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;
        simplex.mountain().evaluateNoise((float)x / 80.0, (float)y / 80.0, jitter);
        pX += jitter.deltax() * mediumBendSize;
        pY += jitter.deltay() * mediumBendSize;
        simplex.octave(4).evaluateNoise((float)x / 30.0, (float)y / 30.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pY += jitter.deltay() * smallBendSize;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        double [] lakeResults = simplexCell.river().eval((float)pX/ lakeInterval, (float)pY/ lakeInterval);
        float results = 1f-(float)((lakeResults[1]-lakeResults[0])/lakeResults[1]);
        if (results >1.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        if (results<-.01) throw new RuntimeException("" + lakeResults[0]+ " , "+lakeResults[1]);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
    }

    public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {
        // this number indicates a multiplier to height
        if (pressure > topLevel) return 1;
        if (pressure<bottomLevel) return 0;
        return (float)Math.pow((pressure-bottomLevel)/(topLevel-bottomLevel),1.0);
    }

    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        float riverRegion = this.noWaterFeatures ? 0: river;
        if (ConfigRTG.enableRTGBiomeSurfaces && this.config.getPropertyById(BiomeConfig.useRTGSurfacesId).valueBoolean) {

            for (int s = 0; s < surfacesLength; s++)
                surfaces[s].paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
        }
        else this.surfaceGeneric.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, riverRegion, base);
    }

    public float r3Dnoise(float z) {

        return 0f;
    }

    public TerrainBase getTerrain() {

        return this.terrain;
    }

    public SurfaceBase getSurface() {

        if (this.surfacesLength == 0) {

            throw new RuntimeException(
                "No realistic surfaces found for " + this.baseBiome.getBiomeName() +
                    " (" + Biome.getIdForBiome(this.baseBiome) + ")."
            );
        }

        return this.surfaces[0];
    }

    public SurfaceBase[] getSurfaces() {

        return this.surfaces;
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

    public void rDecorate(
        World world, Random rand, int chunkX, int chunkZ,
        OpenSimplexNoise simplex, CellNoise cell,
        float strength, float river, boolean hasPlacedVillageBlocks) {

        for (DecoBase deco : this.decos) {
            decoStack.add(new ChunkDecoration(new ChunkPos(chunkX, chunkZ), deco));
            if (decoStack.size() > 20) {
                String problem = "";
                for (ChunkDecoration inStack : decoStack) {
                    problem += "" + inStack.chunkLocation.toString() + " " + inStack.decoration.getClass().getSimpleName();
                }
                throw new RuntimeException(problem);
            }
            if (deco.preGenerate(this, world, rand, chunkX, chunkZ, simplex, cell, strength, river, hasPlacedVillageBlocks)) {
                deco.generate(this, world, rand, chunkX, chunkZ, simplex, cell, strength, river, hasPlacedVillageBlocks);
            }
            decoStack.remove(decoStack.size() - 1);
        }
    }

    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the decos in the biome.
     *
     * @param deco
     * @param allowed
     */
    public void addDeco(DecoBase deco, boolean allowed) {

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
    }

    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     *
     * @param deco
     */
    public void addDeco(DecoBase deco) {
        if (!deco.properlyDefined()) throw new RuntimeException(deco.toString());
        this.addDeco(deco, true);
    }

    public void addDecoCollection(DecoCollectionBase decoCollection) {

        if (decoCollection.decos.size() > 0) {
            for (int i = 0; i < decoCollection.decos.size(); i++) {
                this.addDeco(decoCollection.decos.get(i));
            }
        }

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
            tree.saplingBlock = SaplingUtil.getSaplingFromLeaves(tree.leavesBlock);

            /*
             * Make sure all leaves delay their decay to prevent insta-despawning of leaves (e.g. Swamp Willow)
             * The try/catch is a safeguard against trees that use leaves which aren't an instance of BlockLeaves.
             */
            try {
                IBlockState leaves = tree.leavesBlock.withProperty(BlockLeaves.CHECK_DECAY, false);
                tree.leavesBlock = leaves;
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
}