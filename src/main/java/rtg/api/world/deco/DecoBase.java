package rtg.api.world.deco;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * Documentation for the new biome system can be found here:
 * https://teamrtg.gitbooks.io/rtg-code-documentation/content/biome_decoration.html
 *
 * @author WhichOnesPink
 */
public abstract class DecoBase {

    /**
     * If false, the deco won't get generated during chunk decoration.
     * Currently, the only deco that uses allow=false is the DecoBaseBiomeDecorations deco, and it only gets
     * set to false when we need to generate ores in biomes that don't let the base biome handle decoration at all.
     */
    protected boolean allowed;
    protected ArrayList<DecoType> decoTypes;
    protected boolean checkRiver;
    protected float minRiver; // Minimum river value required to generate.
    protected float maxRiver; // Maximum river value required to generate.

    public DecoBase() {

        this.allowed = true;
        this.decoTypes = new ArrayList<DecoType>();
        this.checkRiver = false;
        this.minRiver = -2f;
        this.setMaxRiver(2f);
    }

    public boolean properlyDefined() {
        // this procedure should return true if the deco can respond properly to a generate() call
        // in particular it should not crash.
        return true;
    }

    /**
     * Performs pre-generation checks to determine if the deco is allowed to generate.
     */
    public boolean preGenerate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.checkRiver) {

            if (river > this.maxRiver || river < this.minRiver) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generates the decoration.
     * This method should be overridden in the individual deco objects.
     */
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

    }

    /**
     * Adds one or more deco types.
     *
     * @param decos
     */
    public void addDecoTypes(DecoType... decos) {

        for (int i = 0; i < decos.length; i++) {
            this.decoTypes.add(decos[i]);
        }
    }

    /**
     * Enum to classify the various decos.
     *
     * @author WhichOnesPink
     */
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
        WAVE,
        WHEAT
    }

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

    public boolean isAllowed() {

        return allowed;
    }

    public DecoBase setAllowed(boolean allowed) {

        this.allowed = allowed;
        return this;
    }

    public ArrayList<DecoType> getDecoTypes() {

        return decoTypes;
    }

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
}