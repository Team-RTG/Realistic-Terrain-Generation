package rtg.api.world.biome;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.RTGAPI;
import rtg.api.config.BiomeConfig;
import rtg.api.util.SaplingUtil;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.collection.DecoCollectionBase;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.biome.RealisticBiomeBase.BeachType;
import rtg.api.world.biome.RealisticBiomeBase.RiverType;


/**
 * Created by WhichOnesPink on 06/02/2017.
 */
// TODO: [1.12] The name of this interface can be shortened to `RTGBiome`. Basically the whole mod codebase can do a find&replace of `realistic` -> `RTG`
// TODO: [1.12] All default implementations can be removed. Implementation can go in the base class.
public interface IRealisticBiome {

    Biome baseBiome();

    ResourceLocation baseBiomeResLoc();

    int baseBiomeId();

    RiverType getRiverType();

    BeachType getBeachType();

    IRealisticBiome getRiverBiome();

    IRealisticBiome getBeachBiome();

    // TODO: [1.12] This should not return a BiomeConfig object, but an object holder class that holds the raw config values that can be resynced on-demand.
    BiomeConfig getConfig();

    // TODO: [1.12] TerrainBase should be made into a @FunctionalInterface. All current static methods in TerrainBase can become defaults.
    TerrainBase terrain();

    // TODO: [1.12] TerrainBase should be made into a @FunctionalInterface. All current static methods in TerrainBase can become defaults.
    SurfaceBase surface();

    void rReplace(final ChunkPrimer primer, final BlockPos blockPos, final int x, final int y, final int depth, final RTGWorld rtgWorld, final float[] noise, final float river, final Biome[] base);

    void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base);

    float rNoise(RTGWorld rtgWorld, int x, int y, float border, float river);

    int waterUndergroundLakeChance();

    int lavaUndergroundLakeChance();

    int waterSurfaceLakeChance();

    int lavaSurfaceLakeChance();

    BiomeDecoratorRTG rDecorator();

    float lakePressure(RTGWorld rtgWorld, int x, int y, float border, float lakeInterval, float largeBendSize, float mediumBendSize, float smallBendSize);



/* TO BE MOVED TO THE BIOME DECORATOR */

    // TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
    void initDecos();

    @Deprecated
    ArrayList<DecoBase> getDecos();

    @Deprecated
    ArrayList<TreeRTG> getTrees();

    // TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
    default boolean generatesEmeralds() {
        return false;
    }

    // TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
    default boolean generatesSilverfish() {
        return false;
    }

    /**
     * Returns the number of extra blocks of gold ore to generate in this biome.
     * Defaults to 0, but can be overridden by sub-classed biomes.
     * Currently only used by vanilla Mesa biome variants.
     */
// TODO: [1.12] To be moved to the biome decorator. Extra gold should be determined by the decorator and should not be in this interface.
    @Deprecated
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
// TODO: [1.12] To be moved to the biome decorator. Extra gold should be determined by the decorator and should not be in this interface.
    @Deprecated
    default int getExtraGoldGenMinHeight() {
        return 32;
    }

    /**
     * Returns the maximum Y value at which extra gold ore can generate.
     * Defaults to 80 (BiomeMesa), but can be overridden by sub-classed biomes.
     *
     * @see net.minecraft.world.biome.BiomeMesa
     */
// TODO: [1.12] To be moved to the biome decorator. Extra gold should be determined by the decorator and should not be in this interface.
    @Deprecated
    default int getExtraGoldGenMaxHeight() {
        return 80;
    }

    /**
     * Adds a deco object to the list of biome decos.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the decos in the biome.
     */
// TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
    default void addDeco(DecoBase deco, boolean allowed) {

        if (allowed) {

            ArrayList<DecoBase> decos = this.getDecos();

            if (!deco.properlyDefined()) {
                throw new RuntimeException(deco.toString());
            }

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
// TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
    default void addDeco(DecoBase deco) {
        if (!deco.properlyDefined()) {
            throw new RuntimeException(deco.toString());
        }
        this.addDeco(deco, true);
    }

    // TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
    default void addDecoCollection(DecoCollectionBase decoCollection) {

        // Don't add the desert river deco collection if the user has disabled it.
        if (decoCollection instanceof DecoCollectionDesertRiver) {
            if (!RTGAPI.config().ENABLE_LUSH_RIVER_BANK_DECORATIONS_IN_HOT_BIOMES.get()) {
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
// TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
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
// TODO: [1.12] To be moved to the biome decorator.
    @Deprecated
    default void addTree(TreeRTG tree) {
        this.addTree(tree, true);
    }

    // TODO: [1.12] To be moved to the biome decorator. Added to this interface temporarily.
    @Deprecated
    void rDecorate(RTGWorld rtgWorld, Random rand, BlockPos pos, float strength, float river, boolean hasPlacedVillageBlocks);



/* TO BE REMOVED */

    // TODO: [1.12] To be removed. Initialisation should take place in the constructor.
    @Deprecated
    TerrainBase initTerrain();

    // TODO: [1.12] To be removed. Initialisation should take place in the constructor.
    @Deprecated
    SurfaceBase initSurface();

    // TODO: [1.12] To be removed. This should not be part of the API and should be done in the constructor
    @Deprecated
    void initConfig();
}
