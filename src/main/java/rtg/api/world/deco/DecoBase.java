package rtg.api.world.deco;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * Documentation for the new biome system can be found here:
 * https://teamrtg.gitbooks.io/rtg-code-documentation/content/biome_decoration.html
 *
 * @author WhichOnesPink
 */
public abstract class DecoBase {

    // TODO [1.12] Temporary; To be removed.
    static final int strength = 1;

    @Deprecated
    private ArrayList<DecoType> decoTypes;
    private boolean checkRiver;
    private float minRiver; // Minimum river value required to generate.
    private float maxRiver; // Maximum river value required to generate.

    public DecoBase() {

        this.decoTypes = new ArrayList<>();
        this.checkRiver = false;
        this.minRiver = -2f;
        this.setMaxRiver(2f);
    }

    // TODO: [1.12] These tweak methods can be performed once in #setLeavesBlock during deco creation, instead of in every
    //              chunk during world gen. The state of the leaves should not be changed during world gen.
    public static void tweakTreeLeaves(DecoTree deco, boolean checkDecay, boolean decayable) {
        if (deco.getLeavesBlock().getBlock() instanceof BlockLeaves) {
            IBlockState leaves = deco.getLeavesBlock()
                .withProperty(BlockLeaves.CHECK_DECAY, checkDecay)
                .withProperty(BlockLeaves.DECAYABLE, decayable);
            deco.setLeavesBlock(leaves);
        }
    }

    public static void tweakShrubLeaves(DecoShrub deco, boolean checkDecay, boolean decayable) {
        if (deco.getLeavesBlock().getBlock() instanceof BlockLeaves) {
            IBlockState leaves = deco.getLeavesBlock()
                .withProperty(BlockLeaves.CHECK_DECAY, checkDecay)
                .withProperty(BlockLeaves.DECAYABLE, decayable);
            deco.setLeavesBlock(leaves);
        }
    }

    @Deprecated //TODO: [1.12] See: DecoTree override
    public boolean properlyDefined() {
        // this procedure should return true if the deco can respond properly to a generate() call
        // in particular it should not crash.
        return true;
    }

    /**
     * Performs pre-generation checks to determine if the deco is allowed to generate.
     */
    // TODO: [1.12] This check is only relevant for Decos added to DecoCollectionDesertRiver and only used in desert biomes. This functionality
    //              can be extracted and pushed down to the desert biome classes to simplify the call to IRealisticBiome#rDecorate.
    public boolean preGenerate(float river) {
        return !this.checkRiver || !(river > this.maxRiver) && !(river < this.minRiver);
    }

    public abstract void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage);

    @Deprecated
    public void addDecoTypes(DecoType... decos) {
        this.decoTypes.addAll(Arrays.asList(decos));
    }

    @Deprecated
    public ArrayList<DecoType> getDecoTypes() {

        return decoTypes;
    }

    @Deprecated
    public DecoBase setDecoTypes(ArrayList<DecoType> decoTypes) {

        this.decoTypes = decoTypes;
        return this;
    }

    public boolean isCheckRiver() {

        return checkRiver;
    }

    public DecoBase setCheckRiver(boolean checkRiver) {

        this.checkRiver = checkRiver;
        return this;
    }

    public float getMinRiver() {

        return minRiver;
    }

    public DecoBase setMinRiver(float minRiver) {

        this.minRiver = minRiver;
        return this;
    }

    public float getMaxRiver() {

        return maxRiver;
    }

    public DecoBase setMaxRiver(float maxRiver) {

        this.maxRiver = maxRiver;
        return this;
    }

    /**
     * Enum to classify the various decos.
     *
     * @author WhichOnesPink
     */
    // TODO: [1.12] These enum values are added to a list for each Deco but are not used anywhere. Marked for removal.
    @Deprecated
    public enum DecoType {
        BASE_BIOME_DECORATION,
        BOULDER,
        COBWEB,
        CACTUS,
        DEAD_BUSH,
        DESERT_WELL,
        FALLEN_LEAVES,
        FALLEN_TREE,
        FERN,
        FERN_DOUBLE,
        FLOWER,
        GRASS,
        GRASS_DOUBLE,
        LAYER,
        LEAVES,
        LILYPAD,
        MUSHROOM,
        PUMPKIN,
        REED,
        ROCK_SPIRE,
        SEAWEED,
        SHRUB,
        SPONGE,
        TEST,
        TREE,
        VINE,
        WHEAT
    }

    static BlockPos getOffsetPos(final ChunkPos pos) {
        return new BlockPos(pos.x * 16 + 8, 0, pos.z * 16 + 8);
    }

    public static int getRangedRandom(Random rand, int min, int max) {
        return min + rand.nextInt(max - min + 1);
    }
}