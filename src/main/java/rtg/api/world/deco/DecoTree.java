package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import rtg.api.event.DecorateBiomeEventRTG;
import rtg.api.util.DecoUtil;
import rtg.api.util.RandomUtil;
import rtg.api.util.WorldUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;

/**
 * @author WhichOnesPink
 */
public class DecoTree extends DecoBase {

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
    protected Scatter scatter;

    public DecoTree() {

        super();

        /**
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
        this.setScatter(new Scatter(16, 0));

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
        this.setScatter(source.scatter);
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

    public boolean properlyDefined() {

        if (this.treeType == TreeType.RTG_TREE) {
            if (this.tree == null) {
                return false;
            }
        }
        return super.properlyDefined();
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            /*
             * Determine how many trees we're going to try to generate (loopCount).
             * The actual number of trees that end up being generated could be *less* than this value,
             * depending on environmental conditions.
             */
            float noise = rtgWorld.simplex().noise2(worldX / this.distribution.noiseDivisor, worldZ / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;
            int loopCount = this.loops;
            loopCount = (this.strengthFactorForLoops > 0f) ? (int) (this.strengthFactorForLoops * strength) : loopCount;
            loopCount = (this.strengthNoiseFactorForLoops) ? (int) (noise * strength) : loopCount;
            loopCount = (this.strengthNoiseFactorXForLoops) ? (int) (noise * this.strengthFactorForLoops * strength) : loopCount;

            if (loopCount < 1) {
                return;
            }

            // Now let's check the configs to see if we should increase/decrease this value.
            DecoUtil decoUtil = new DecoUtil(this);
            loopCount = decoUtil.calculateLoopCountFromTreeDensity(loopCount, biome);

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
            DecorateBiomeEventRTG.DecorateRTG event = new DecorateBiomeEventRTG.DecorateRTG(
                rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), TREE, loopCount
            );
            MinecraftForge.TERRAIN_GEN_BUS.post(event);

            if (event.getResult() != Event.Result.DENY) {

                loopCount = event.getModifiedAmount();

                if (loopCount < 1) {
                    return;
                }

                WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
                DecoBase.tweakTreeLeaves(this, false, true);

                for (int i = 0; i < loopCount; i++) {

                    int intX = scatter.get(rand, worldX); // + 8;
                    int intZ = scatter.get(rand, worldZ); // + 8;
                    int intY = rtgWorld.world().getHeight(new BlockPos(intX, 0, intZ)).getY();

                    //Logger.info("noise = %f", noise);

                    if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

                        // If we're in a village, check to make sure the tree has extra room to grow to avoid corrupting the village.
                        if (hasPlacedVillageBlocks) {
                            if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, WorldUtil.SurroundCheckType.CARDINAL, rand, intX, intY, intZ)) {
                                return;
                            }
                        }

                        switch (this.treeType) {

                            case RTG_TREE:

                                //this.setLogBlock(strength < 0.2f ? BlockUtil.getStateLog(2) : this.logBlock);

                                this.tree.setLogBlock(this.logBlock);
                                this.tree.setLeavesBlock(this.leavesBlock);
                                this.tree.setTrunkSize(RandomUtil.getRandomInt(rand, this.minTrunkSize, this.maxTrunkSize));
                                this.tree.setCrownSize(RandomUtil.getRandomInt(rand, this.minCrownSize, this.maxCrownSize));
                                this.tree.setNoLeaves(this.noLeaves);
                                this.tree.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));

                                break;

                            case WORLDGEN:

                                WorldGenerator worldgenerator = this.worldGen;
                                worldgenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));

                                break;

                            default:
                                break;
                        }
                    }
                    else {
                        //Logger.debug("%d/%d/%d - minY = %d; maxY = %d; noise = %f", intX, intY, intZ, minY, maxY, noise);
                    }
                }
            }
            else {
                //Logger.debug("Tree generation was cancelled.");
            }
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
                noiseLessThanMax = noise <= this.treeConditionNoise2;
                randomResult = rand.nextInt(this.treeConditionChance) == 0;
                valid = (noiseGreaterThanMin && noiseLessThanMax && randomResult);

                if (!valid) {
                    valid = false;
                }
                else {
                    valid = true;
                }

                return valid;

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

    public static class Scatter {

        int bound;
        int reach;

        public Scatter(int bound, int reach) {

            if (bound < 1) {
                throw new RuntimeException("Scatter bound must be greater than 0.");
            };

            this.bound = bound;
            this.reach = reach;
        }

        public int get(Random rand, int coord) {
            return coord + rand.nextInt(bound) + reach;
        }
    }

    /**
     * Parameter object for noise calculations.
     * <p>
     * simplex.noise2(chunkX / noiseDivisor, chunkZ / noiseDivisor) * noiseFactor + noiseAddend;
     *
     * @author WhichOnesPink
     * @author Zeno410
     */
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

    public Scatter getScatter() {

        return scatter;
    }

    public DecoTree setScatter(Scatter scatter) {

        this.scatter = scatter;
        return this;
    }
}
