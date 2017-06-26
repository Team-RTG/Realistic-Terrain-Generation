package rtg.api.world.biome;

import java.util.ArrayList;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.SaplingUtil;
import rtg.api.util.noise.SimplexOctave;
import rtg.api.util.noise.VoronoiResult;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.collection.DecoCollectionBase;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import static rtg.api.RTGAPI.rtgConfig;

/**
 * Created by WhichOnesPink on 06/02/2017.
 */
public interface IRealisticBiome {

    IRealisticBiome[] arrRealisticBiomes = new IRealisticBiome[256];

    Biome baseBiome();
    Biome riverBiome();
    Biome beachBiome();

    BiomeDecoratorRTG rDecorator();

    BiomeConfig getConfig();
    void initConfig();
    boolean hasConfig();
    TerrainBase initTerrain();
    SurfaceBase initSurface();
    void initDecos();
    ArrayList<DecoBase> getDecos();
    ArrayList<TreeRTG> getTrees();

    default boolean generatesEmeralds() {
        return false;
    }

    default boolean generatesSilverfish() {
        return false;
    }

    default int waterUndergroundLakeChance() {
        return 1; // Lower equals more frequent.
    }

    default int lavaUndergroundLakeChance() {
        return 1; // Lower equals more frequent.
    }

    default int waterSurfaceLakeChance() {
        return 10; // Lower equals more frequent.
    }

    default int lavaSurfaceLakeChance() {
        return 0; // Lower equals more frequent.
    }

    void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base);

    default float lakePressure(IRTGWorld rtgWorld, int x, int y, float border, float lakeInterval, float largeBendSize, float mediumBendSize, float smallBendSize) {
        if (!this.getConfig().ALLOW_SCENIC_LAKES.get()) return 1f;
        SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        rtgWorld.simplex().riverJitter().evaluateNoise((float)x / 240.0, (float)y / 240.0, jitter);
        double pX = x + jitter.deltax() * largeBendSize;
        double pY = y + jitter.deltay() * largeBendSize;
        rtgWorld.simplex().mountain().evaluateNoise((float)x / 80.0, (float)y / 80.0, jitter);
        pX += jitter.deltax() * mediumBendSize;
        pY += jitter.deltay() * mediumBendSize;
        rtgWorld.simplex().octave(4).evaluateNoise((float)x / 30.0, (float)y / 30.0, jitter);
        pX += jitter.deltax() * smallBendSize;
        pY += jitter.deltay() * smallBendSize;
        //double results =simplexCell.river().noise(pX / lakeInterval, pY / lakeInterval,1.0);
        VoronoiResult lakeResults = rtgWorld.cell().river().eval((float)pX/ lakeInterval, (float)pY/ lakeInterval);
        float results = 1f-(float)(lakeResults.interiorValue());
        if (results >1.01) throw new RuntimeException("" + lakeResults.shortestDistance+ " , "+lakeResults.nextDistance);
        if (results<-.01) throw new RuntimeException("" + lakeResults.shortestDistance+ " , "+lakeResults.nextDistance);
        //return simplexCell.river().noise((float)x/ lakeInterval, (float)y/ lakeInterval,1.0);
        return results;
    }

    /**
     * Returns the number of extra blocks of gold ore to generate in this biome.
     * Defaults to 0, but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     */
    default int getExtraGoldGenCount() {
        return 0;
    }

    /**
     * Returns the minimum Y value at which extra gold ore can generate.
     * Defaults to 32 (BiomeMesa), but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    default int getExtraGoldGenMinHeight() {
        return 32;
    }

    /**
     * Returns the maximum Y value at which extra gold ore can generate.
     * Defaults to 80 (BiomeMesa), but can be overridden by sub-classed biomes.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
    default int getExtraGoldGenMaxHeight() {
        return 80;
    }

    String modSlug();

    default String biomeSlug() {
        return BiomeConfig.formatSlug(this.baseBiome().getBiomeName());
    }

    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the decos in the biome.
     */
    default void addDeco(DecoBase deco, boolean allowed) {

        if (allowed) {

            ArrayList<DecoBase> decos = this.getDecos();

            if (!deco.properlyDefined()) throw new RuntimeException(deco.toString());

            if (deco instanceof DecoBaseBiomeDecorations) {

                for (int i = 0; i < decos.size(); i++) {

                    if (decos.get(i) instanceof DecoBaseBiomeDecorations) {

                        decos.remove(i);
                        break;
                    }
                }
            }

            decos.add(deco);
        }
    }

    /**
     * Convenience method for addDeco() where 'allowed' is assumed to be true.
     */
    default void addDeco(DecoBase deco) {
        if (!deco.properlyDefined()) throw new RuntimeException(deco.toString());
        this.addDeco(deco, true);
    }

    default void addDecoCollection(DecoCollectionBase decoCollection) {

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
     */
    default void addTree(TreeRTG tree, boolean allowed) {

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

            this.getTrees().add(tree);
        }
    }

    /**
     * Convenience method for addTree() where 'allowed' is assumed to be true.
     */
    default void addTree(TreeRTG tree) {
        this.addTree(tree, true);
    }
}
