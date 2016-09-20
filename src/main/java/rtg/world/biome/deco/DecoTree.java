package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.util.WorldUtil;
import rtg.util.WorldUtil.SurroundCheckType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.rtg.TreeRTG;

/**
 * @author WhichOnesPink
 */
public class DecoTree extends DecoBase {

    public int loops;
    public float strengthFactorForLoops; // If set, this overrides and dynamically calculates 'loops' based on the strength parameter.
    public boolean strengthNoiseFactorForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * strength)
    public boolean strengthNoiseFactorXForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * X * strength)
    public TreeType treeType; // Enum for the various tree presets.
    public TreeRTG tree;
    public WorldGenerator worldGen;
    public DecoTree.Distribution distribution; // Parameter object for noise calculations.
    public TreeCondition treeCondition; // Enum for the various conditions/chances for tree gen.
    public float treeConditionNoise; // Only applies to a noise-related TreeCondition.
    public int treeConditionChance; // Only applies to a chance-related TreeCondition.
    public float treeConditionFloat; // Multi-purpose float.
    public int minY; // Lower height restriction.
    public int maxY; // Upper height restriction.
    public IBlockState logBlock;
    public IBlockState leavesBlock;
    public int minSize; // Min tree height (only used with certain tree presets)
    public int maxSize; // Max tree height (only used with certain tree presets)
    public int minTrunkSize; // Min tree height (only used with certain tree presets)
    public int maxTrunkSize; // Max tree height (only used with certain tree presets)
    public int minCrownSize; // Min tree height (only used with certain tree presets)
    public int maxCrownSize; // Max tree height (only used with certain tree presets)
    public boolean noLeaves;

    public DecoTree() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.loops = 1;
        this.strengthFactorForLoops = 0f;
        this.strengthNoiseFactorForLoops = false;
        this.strengthNoiseFactorXForLoops = false;
        this.treeType = TreeType.RTG_TREE;
        this.tree = null;
        this.worldGen = null;
        this.distribution = new DecoTree.Distribution(100f, 5f, 0.8f);
        this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        this.treeConditionNoise = 0f;
        this.treeConditionFloat = 0f;
        this.treeConditionChance = 1;
        this.minY = 63; // No underwater trees by default.
        this.maxY = 230; // Sensible upper height limit by default.
        this.logBlock = Blocks.LOG.getDefaultState();
        this.leavesBlock = Blocks.LEAVES.getDefaultState();
        this.minSize = 2;
        this.maxSize = 4;
        this.minTrunkSize = 2;
        this.maxTrunkSize = 4;
        this.minCrownSize = 2;
        this.maxCrownSize = 4;
        this.noLeaves = false;

        this.addDecoTypes(DecoType.TREE);
    }

    public DecoTree(DecoTree source) {

        this();
        this.loops = source.loops;
        this.strengthFactorForLoops = source.strengthFactorForLoops;
        this.strengthNoiseFactorForLoops = source.strengthNoiseFactorForLoops;
        this.strengthNoiseFactorXForLoops = source.strengthNoiseFactorXForLoops;
        this.treeType = source.treeType;
        this.tree = source.tree;
        this.distribution = source.distribution;
        this.treeCondition = source.treeCondition;
        this.treeConditionNoise = source.treeConditionNoise;
        this.treeConditionChance = source.treeConditionChance;
        this.minY = source.minY;
        this.maxY = source.maxY;
        this.logBlock = source.logBlock;
        this.leavesBlock = source.leavesBlock;
        this.minSize = source.minSize;
        this.maxSize = source.maxSize;
        this.minTrunkSize = source.minTrunkSize;
        this.maxTrunkSize = source.maxTrunkSize;
        this.minCrownSize = source.minCrownSize;
        this.maxCrownSize = source.maxCrownSize;
        this.noLeaves = source.noLeaves;
    }

    public DecoTree(TreeRTG tree) {

        this();
        this.tree = tree;
        this.logBlock = tree.logBlock;
        this.leavesBlock = tree.leavesBlock;
        this.minTrunkSize = tree.minTrunkSize;
        this.maxTrunkSize = tree.maxTrunkSize;
        this.minCrownSize = tree.minCrownSize;
        this.maxCrownSize = tree.maxCrownSize;
        this.noLeaves = tree.noLeaves;
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
    public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), TREE)) {

                WorldUtil worldUtil = new WorldUtil(world);
                float noise = simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                int loopCount = this.loops;
                loopCount = (this.strengthFactorForLoops > 0f) ? (int) (this.strengthFactorForLoops * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorForLoops) ? (int) (noise * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorXForLoops) ? (int) (noise * this.strengthFactorForLoops * strength) : loopCount;
                for (int i = 0; i < loopCount; i++) {
                    int intX = chunkX + rand.nextInt(16);// + 8;
                    int intZ = chunkY + rand.nextInt(16);// + 8;
                    int intY = world.getHeight(new BlockPos(intX, 0, intZ)).getY();

                    if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

                        // If we're in a village, check to make sure the tree has extra room to grow to avoid corrupting the village.
                        if (hasPlacedVillageBlocks) {
                            if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, SurroundCheckType.CARDINAL, rand, intX, intY, intZ)) {
                                return;
                            }
                        }

                        switch (this.treeType) {

                            case RTG_TREE:

                                this.tree.setLogBlock(this.logBlock);
                                this.tree.setLeavesBlock(this.leavesBlock);
                                this.tree.setTrunkSize(RandomUtil.getRandomInt(rand, this.minTrunkSize, this.maxTrunkSize));
                                this.tree.setCrownSize(RandomUtil.getRandomInt(rand, this.minCrownSize, this.maxCrownSize));
                                this.tree.setNoLeaves(this.noLeaves);
                                this.tree.generate(world, rand, new BlockPos(intX, intY, intZ));

                                break;

                            case WORLDGEN:

                                WorldGenerator worldgenerator = this.worldGen;
                                worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));

                                break;

                            default:
                                break;
                        }
                    }
                }
            }
        }
    }

    public boolean isValidTreeCondition(float noise, Random rand, float strength) {

        switch (this.treeCondition) {
            case ALWAYS_GENERATE:
                return true;

            case NOISE_GREATER_AND_RANDOM_CHANCE:
                return (noise > this.treeConditionNoise && rand.nextInt(this.treeConditionChance) == 0);

            case RANDOM_CHANCE:
                return rand.nextInt(this.treeConditionChance) == 0;

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
        RANDOM_CHANCE,
        X_DIVIDED_BY_STRENGTH;
    }

    /**
     * Parameter object for noise calculations.
     * <p>
     * simplex.noise2(chunkX / noiseDivisor, chunkY / noiseDivisor) * noiseFactor + noiseAddend;
     *
     * @author WhichOnesPink
     * @author Zeno410
     */
    public static class Distribution {

        public float noiseDivisor;
        public float noiseFactor;
        public float noiseAddend;

        public Distribution(float noiseDivisor, float noiseFactor, float noiseAddend) {

            this.noiseDivisor = noiseDivisor;
            this.noiseFactor = noiseFactor;
            this.noiseAddend = noiseAddend;
        }
    }
}
