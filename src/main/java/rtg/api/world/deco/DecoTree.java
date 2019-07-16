package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.fml.common.eventhandler.Event;

import rtg.RTGConfig;
import rtg.api.event.DecorateBiomeEventRTG;
import rtg.api.util.BlockUtil;
import rtg.api.util.BlockUtil.MatchType;
import rtg.api.util.Logger;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;


/**
 * @author WhichOnesPink
 */
public class DecoTree extends DecoBase {

    public static final double MAX_TREE_DENSITY = 5.0D;

    protected int loops;
    protected float strengthFactorForLoops; // If set, this overrides and dynamically calculates 'loops' based on the strength parameter.
    protected boolean strengthNoiseFactorForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * strength)
    protected boolean strengthNoiseFactorXForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * X * strength)
    protected TreeType treeType; // Enum for the various tree presets.
    protected TreeRTG tree;
    protected WorldGenerator worldGen;
    protected DecoTree.Distribution distribution; // Parameter object for noise calculations.
    protected TreeCondition treeCondition; // Enum for the various conditions/chances for tree gen.
    protected float treeConditionNoise; // Only applies to a noise-related TreeCondition.
    protected float treeConditionNoise2; // Only applies to a noise-related TreeCondition.
    protected int treeConditionChance; // Only applies to a chance-related TreeCondition.
    protected float treeConditionFloat; // Multi-purpose float.
    protected int minY; // Lower height restriction.
    protected int maxY; // Upper height restriction.
    protected IBlockState logBlock;
    protected IBlockState leavesBlock;
    protected int minSize; // Min tree height (only used with certain tree presets)
    protected int maxSize; // Max tree height (only used with certain tree presets)
    protected int minTrunkSize; // Min tree height (only used with certain tree presets)
    protected int maxTrunkSize; // Max tree height (only used with certain tree presets)
    protected int minCrownSize; // Min tree height (only used with certain tree presets)
    protected int maxCrownSize; // Max tree height (only used with certain tree presets)
    protected boolean noLeaves;

    public DecoTree() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setLoops(1);
        this.setStrengthFactorForLoops(0f);
        this.setStrengthNoiseFactorForLoops(false);
        this.setStrengthNoiseFactorXForLoops(false);
        this.setTreeType(TreeType.RTG_TREE);
        this.tree = null;
        this.worldGen = null;
        this.setDistribution(new DecoTree.Distribution(100f, 5f, 0.8f));
        this.setTreeCondition(TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        this.setTreeConditionNoise(0f);
        this.setTreeConditionNoise2(0f);
        this.setTreeConditionFloat(0f);
        this.setTreeConditionChance(1);
        this.setMinY(63); // No underwater trees by default.
        this.setMaxY(230); // Sensible upper height limit by default.
        this.setLogBlock(Blocks.LOG.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        this.setMinSize(2);
        this.setMaxSize(4);
        this.setMinTrunkSize(2);
        this.setMaxTrunkSize(4);
        this.setMinCrownSize(2);
        this.setMaxCrownSize(4);
        this.setNoLeaves(false);

        this.addDecoTypes(DecoType.TREE);
    }

    public DecoTree(DecoTree source) {

        this();
        this.setLoops(source.loops);
        this.setStrengthFactorForLoops(source.strengthFactorForLoops);
        this.setStrengthNoiseFactorForLoops(source.strengthNoiseFactorForLoops);
        this.setStrengthNoiseFactorXForLoops(source.strengthNoiseFactorXForLoops);
        this.setTreeType(source.treeType);
        this.tree = source.tree;
        this.worldGen = source.worldGen;
        this.setDistribution(source.distribution);
        this.setTreeCondition(source.treeCondition);
        this.setTreeConditionNoise(source.treeConditionNoise);
        this.setTreeConditionNoise2(source.treeConditionNoise2);
        this.setTreeConditionFloat(source.treeConditionFloat);
        this.setTreeConditionChance(source.treeConditionChance);
        this.setMinY(source.minY);
        this.setMaxY(source.maxY);
        this.setLogBlock(source.logBlock);
        this.setLeavesBlock(source.leavesBlock);
        this.setMinSize(source.minSize);
        this.setMaxSize(source.maxSize);
        this.setMinTrunkSize(source.minTrunkSize);
        this.setMaxTrunkSize(source.maxTrunkSize);
        this.setMinCrownSize(source.minCrownSize);
        this.setMaxCrownSize(source.maxCrownSize);
        this.setNoLeaves(source.noLeaves);
    }

    public DecoTree(TreeRTG tree) {

        this();
        this.tree = tree;
        this.setLogBlock(tree.getLogBlock());
        this.setLeavesBlock(tree.getLeavesBlock());
        this.setMinTrunkSize(tree.getMinTrunkSize());
        this.setMaxTrunkSize(tree.getMaxTrunkSize());
        this.setMinCrownSize(tree.getMinCrownSize());
        this.setMaxCrownSize(tree.getMaxCrownSize());
        this.setNoLeaves(tree.getNoLeaves());
    }

    public DecoTree(WorldGenerator worldGen) {

        this();
        this.worldGen = worldGen;
    }

    // TODO: [1.12] Both `tree` and `worldGen` are WorldGenerators so there is no reason to treat them differently.
    //              All RTG-specific aspects of the TreeRTG WorldGenerator should be passed at the time of object
    //              creation and those objects should *only* be created at the time of generation instead of this
    //              class hanging on to a single object that gets reused. Choosing which generator to use can simply
    //              be done by checking the TreeType. This change would negate the need for this confusing check.
    @Deprecated
    public boolean properlyDefined() {

        if (this.treeType == TreeType.RTG_TREE) {
            if (this.tree == null) {
                return false;
            }
        }
        return super.properlyDefined();
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        final BlockPos offsetPos = getOffsetPos(chunkPos);
        /*
         * Determine how many trees we're going to try to generate (loopCount).
         * The actual number of trees that end up being generated could be *less* than this value,
         * depending on environmental conditions.
         */
        // TODO: [1.12] What is the point of deriving a noise value from static BlockPos within a chunk (population origin) and then applying
        //              it to a feature taking place at some other arbitrary place in the chunk. This seems nonsensical and makes needless
        //              calls to the noise generator. This should be replaced by a random amount.
        float noise = rtgWorld.simplexInstance(0)
            .noise2f(offsetPos.getX() / this.distribution.getNoiseDivisor(), offsetPos.getZ() / this.distribution.getNoiseDivisor())
            * this.distribution.getNoiseFactor() + this.distribution.getNoiseAddend();
        int loopCount = (this.strengthFactorForLoops > 0f) ? (int) (this.strengthFactorForLoops * strength) : this.loops;
        loopCount = (this.strengthNoiseFactorForLoops) ? (int) (noise * strength) : loopCount;
        loopCount = (this.strengthNoiseFactorXForLoops) ? (int) (noise * this.strengthFactorForLoops * strength) : loopCount;

        if (loopCount < 1) {
            return;
        }

        // Now let's check the configs to see if we should increase/decrease this value.
        loopCount = this.calculateLoopCountFromTreeDensity(loopCount, biome);

        if (loopCount < 1) {
            return;
        }

        /*
         * Since RTG posts a TREE event for each batch of trees it tries to generate (instead of one event per chunk),
         * we post this custom event so that we can pass the number of trees RTG expects to generate in each batch.
         *
         * This provides more contextual information to mods like Recurrent Complex, which can use the info to better
         * determine how to handle each batch of trees.
         *
         * Because the custom event extends DecorateBiomeEvent.Decorate, it still works with mods that don't need
         * the additional context.
         */
        //TODO [1.12] Trees should just generate how they do in the vanilla BiomeDecorator::genDecorations and use the Forge event.
        DecorateBiomeEventRTG.DecorateRTG event = new DecorateBiomeEventRTG.DecorateRTG(rtgWorld.world(), rand, offsetPos, Decorate.EventType.TREE, loopCount);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);

        if (event.getResult() != Event.Result.DENY) {

            loopCount = event.getModifiedAmount();
            if (loopCount < 1) { return; }

            // TODO: [1.12] This should be done in #setLeavesBlock.
            DecoBase.tweakTreeLeaves(this, false, true);

            for (int i = 0; i < loopCount; i++) {

                final BlockPos pos = offsetPos.add(rand.nextInt(16), 0, rand.nextInt(16));
                int y = rtgWorld.world().getHeight(pos).getY();
                if (y <= this.maxY && y >= this.minY && isValidTreeCondition(noise, rand, strength)) {

                    // If we're in a village, check to make sure the tree has extra room to grow to avoid corrupting the village.
                    if (hasVillage) {
                        if (BlockUtil.checkVerticalBlocks(MatchType.ALL, rtgWorld.world(), pos, -1, Blocks.FARMLAND) ||
                            !BlockUtil.checkAreaBlocks(MatchType.ALL_IGNORE_REPLACEABLE, rtgWorld.world(), pos, 2)) {
                            return;
                        }
                    }

                    switch (this.treeType) {

                        case RTG_TREE:

                            //this.setLogBlock(strength < 0.2f ? BlockUtil.getStateLog(2) : this.logBlock);

                            this.tree.setLogBlock(this.logBlock);
                            this.tree.setLeavesBlock(this.leavesBlock);
                            this.tree.setTrunkSize(getRangedRandom(rand, this.minTrunkSize, this.maxTrunkSize));
                            this.tree.setCrownSize(getRangedRandom(rand, this.minCrownSize, this.maxCrownSize));
                            this.tree.setNoLeaves(this.noLeaves);
                            this.tree.generate(rtgWorld.world(), rand, pos.up(y));

                            break;

                        case WORLDGEN:

                            WorldGenerator worldgenerator = this.worldGen;
                            worldgenerator.generate(rtgWorld.world(), rand, pos.up(y));

                            break;

                        default:
                            break;
                    }
                }
            }
        }
        else if (RTGConfig.enableDebugging()) {
            Logger.debug("Tree generation was cancelled @ ChunkPos{}", chunkPos);
        }
    }

    public boolean isValidTreeCondition(float noise, Random rand, float strength) {

        boolean noiseGreaterThanMin;
        boolean noiseLessThanMax;
        boolean randomResult;
        boolean valid;

        switch (this.treeCondition) {
            case ALWAYS_GENERATE:
                return true;

            case NOISE_GREATER_AND_RANDOM_CHANCE:
                return (noise > this.treeConditionNoise && rand.nextInt(this.treeConditionChance) == 0);

            case NOISE_LESSER_AND_RANDOM_CHANCE:
                return (noise < this.treeConditionNoise && rand.nextInt(this.treeConditionChance) == 0);

            case NOISE_BETWEEN_AND_RANDOM_CHANCE:
                noiseGreaterThanMin = noise >= this.treeConditionNoise;
                noiseLessThanMax =- noise <= this.treeConditionNoise2;
                randomResult = rand.nextInt(this.treeConditionChance) == 0;
                return (noiseGreaterThanMin && noiseLessThanMax && randomResult);

            case RANDOM_CHANCE:
                return rand.nextInt(this.treeConditionChance) == 0;

            case RANDOM_NOT_EQUALS_CHANCE:
                return rand.nextInt(this.treeConditionChance) != 0;

            case X_DIVIDED_BY_STRENGTH:
                return rand.nextInt((int) (this.treeConditionFloat / strength)) == 0;

            default:
                return false;
        }
    }

    public int getLoops() {

        return loops;
    }

    public DecoTree setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public float getStrengthFactorForLoops() {

        return strengthFactorForLoops;
    }

    public DecoTree setStrengthFactorForLoops(float strengthFactorForLoops) {

        this.strengthFactorForLoops = strengthFactorForLoops;
        return this;
    }

    public boolean isStrengthNoiseFactorForLoops() {

        return strengthNoiseFactorForLoops;
    }

    public DecoTree setStrengthNoiseFactorForLoops(boolean strengthNoiseFactorForLoops) {

        this.strengthNoiseFactorForLoops = strengthNoiseFactorForLoops;
        return this;
    }

    public boolean isStrengthNoiseFactorXForLoops() {

        return strengthNoiseFactorXForLoops;
    }

    public DecoTree setStrengthNoiseFactorXForLoops(boolean strengthNoiseFactorXForLoops) {

        this.strengthNoiseFactorXForLoops = strengthNoiseFactorXForLoops;
        return this;
    }

    public TreeType getTreeType() {

        return treeType;
    }

    public DecoTree setTreeType(TreeType treeType) {

        this.treeType = treeType;
        return this;
    }

    public TreeRTG getTree() {

        return tree;
    }

    public DecoTree setTree(TreeRTG tree) {

        this.tree = tree;
        return this;
    }

    public WorldGenerator getWorldGen() {

        return worldGen;
    }

    public DecoTree setWorldGen(WorldGenerator worldGen) {

        this.worldGen = worldGen;
        return this;
    }

    public Distribution getDistribution() {

        return distribution;
    }

    public DecoTree setDistribution(Distribution distribution) {

        this.distribution = distribution;
        return this;
    }

    public TreeCondition getTreeCondition() {

        return treeCondition;
    }

    public DecoTree setTreeCondition(TreeCondition treeCondition) {

        this.treeCondition = treeCondition;
        return this;
    }

    public float getTreeConditionNoise() {

        return treeConditionNoise;
    }

    public DecoTree setTreeConditionNoise(float treeConditionNoise) {

        this.treeConditionNoise = treeConditionNoise;
        return this;
    }

    public float getTreeConditionNoise2() {

        return treeConditionNoise2;
    }

    public DecoTree setTreeConditionNoise2(float treeConditionNoise2) {

        this.treeConditionNoise2 = treeConditionNoise2;
        return this;
    }

    public int getTreeConditionChance() {

        return treeConditionChance;
    }

    public DecoTree setTreeConditionChance(int treeConditionChance) {

        this.treeConditionChance = treeConditionChance;
        return this;
    }

    public float getTreeConditionFloat() {

        return treeConditionFloat;
    }

    public DecoTree setTreeConditionFloat(float treeConditionFloat) {

        this.treeConditionFloat = treeConditionFloat;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoTree setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoTree setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public IBlockState getLogBlock() {

        return logBlock;
    }

    public DecoTree setLogBlock(IBlockState logBlock) {

        this.logBlock = logBlock;
        return this;
    }

    public IBlockState getLeavesBlock() {

        return leavesBlock;
    }

    public DecoTree setLeavesBlock(IBlockState leavesBlock) {

        this.leavesBlock = leavesBlock;
        return this;
    }

    public int getMinSize() {

        return minSize;
    }

    public DecoTree setMinSize(int minSize) {

        this.minSize = minSize;
        return this;
    }

    public int getMaxSize() {

        return maxSize;
    }

    public DecoTree setMaxSize(int maxSize) {

        this.maxSize = maxSize;
        return this;
    }

    public int getMinTrunkSize() {

        return minTrunkSize;
    }

    public DecoTree setMinTrunkSize(int minTrunkSize) {

        this.minTrunkSize = minTrunkSize;
        return this;
    }

    public int getMaxTrunkSize() {

        return maxTrunkSize;
    }

    public DecoTree setMaxTrunkSize(int maxTrunkSize) {

        this.maxTrunkSize = maxTrunkSize;
        return this;
    }

    public int getMinCrownSize() {

        return minCrownSize;
    }

    public DecoTree setMinCrownSize(int minCrownSize) {

        this.minCrownSize = minCrownSize;
        return this;
    }

    public int getMaxCrownSize() {

        return maxCrownSize;
    }

    public DecoTree setMaxCrownSize(int maxCrownSize) {

        this.maxCrownSize = maxCrownSize;
        return this;
    }

    public boolean isNoLeaves() {

        return noLeaves;
    }

    public DecoTree setNoLeaves(boolean noLeaves) {

        this.noLeaves = noLeaves;
        return this;
    }

    public enum TreeType {
        RTG_TREE,
        WORLDGEN;
    }

    public enum TreeCondition {
        ALWAYS_GENERATE,
        NOISE_GREATER_AND_RANDOM_CHANCE,
        NOISE_LESSER_AND_RANDOM_CHANCE,
        NOISE_BETWEEN_AND_RANDOM_CHANCE,
        RANDOM_CHANCE,
        RANDOM_NOT_EQUALS_CHANCE,
        X_DIVIDED_BY_STRENGTH;
    }

    /**
     * Parameter object for noise calculations.
     * <p>
     * simplex.noise2(chunkX / noiseDivisor, chunkZ / noiseDivisor) * noiseFactor + noiseAddend;
     *
     * @author WhichOnesPink
     * @author Zeno410
     */
    // TODO: [1.12] Due to the lack of variance in usage, the use of this class can be extracted to the few places it
    //              gets used, if that usage is even kept. (Usage of noise decoration should be removed.)
    @Deprecated
    public static class Distribution {

        protected float noiseDivisor;
        protected float noiseFactor;
        protected float noiseAddend;

        public Distribution(float noiseDivisor, float noiseFactor, float noiseAddend) {

            this.noiseDivisor = noiseDivisor;
            this.noiseFactor = noiseFactor;
            this.noiseAddend = noiseAddend;
        }

        public float getNoiseDivisor() {

            return noiseDivisor;
        }

        public Distribution setNoiseDivisor(float noiseDivisor) {

            this.noiseDivisor = noiseDivisor;
            return this;
        }

        public float getNoiseFactor() {

            return noiseFactor;
        }

        public Distribution setNoiseFactor(float noiseFactor) {

            this.noiseFactor = noiseFactor;
            return this;
        }

        public float getNoiseAddend() {

            return noiseAddend;
        }

        public Distribution setNoiseAddend(float noiseAddend) {

            this.noiseAddend = noiseAddend;
            return this;
        }
    }

    private int calculateLoopCountFromTreeDensity(int loopCount, IRealisticBiome biome) {

        float biomeMultiplier;
        double multiplier = RTGConfig.treeDensityMultiplier();
        if ((biomeMultiplier = biome.getConfig().TREE_DENSITY_MULTIPLIER.get()) >= 0f) {
            multiplier = (biomeMultiplier > MAX_TREE_DENSITY) ? MAX_TREE_DENSITY : biomeMultiplier;
        }

        loopCount = (int) (loopCount * multiplier);

        return loopCount;
    }
}
