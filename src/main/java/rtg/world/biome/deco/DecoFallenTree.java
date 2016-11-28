package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.util.WorldUtil;
import rtg.util.WorldUtil.SurroundCheckType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;

/**
 * @author WhichOnesPink
 */
public class DecoFallenTree extends DecoBase {

    public int loops;
    public DecoFallenTree.Distribution distribution; // Parameter object for noise calculations.
    public LogCondition logCondition; // Enum for the various conditions/chances for log gen.
    public float logConditionNoise; // Only applies to a noise-related LogCondition.
    public int logConditionChance; // Only applies to a chance-related LogCondition.
    public int maxY; // Height restriction.
    public IBlockState logBlock;
    public IBlockState leavesBlock;
    public int minSize; // Min log height (only used with certain log presets)
    public int maxSize; // Max log height (only used with certain log presets)
    public IBlockState[] randomLogBlocks;

    public DecoFallenTree() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.loops = 1;
        this.distribution = new DecoFallenTree.Distribution(100f, 5f, 0.8f);
        this.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        this.logConditionNoise = 0f;
        this.logConditionChance = 1;
        this.maxY = 80;
        this.logBlock = Blocks.LOG.getDefaultState();
        this.leavesBlock = Blocks.LEAVES.getDefaultState();
        this.minSize = 2;
        this.maxSize = 4;
        this.randomLogBlocks = new IBlockState[]{};

        this.addDecoTypes(DecoType.FALLEN_TREE);
    }

    public DecoFallenTree(DecoFallenTree source) {

        this();
        this.loops = source.loops;
        this.distribution = source.distribution;
        this.logCondition = source.logCondition;
        this.logConditionNoise = source.logConditionNoise;
        this.logConditionChance = source.logConditionChance;
        this.maxY = source.maxY;
        this.logBlock = source.logBlock;
        this.leavesBlock = source.leavesBlock;
        this.minSize = source.minSize;
        this.maxSize = source.maxSize;
        this.randomLogBlocks = source.randomLogBlocks;
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            World world = rtgWorld.world;
            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;

            float noise = simplex.noise2(worldX / this.distribution.noiseDivisor, worldZ / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;
            WorldUtil worldUtil = new WorldUtil(world);

            //Do we want to choose a random log?
            if (this.randomLogBlocks.length > 0) {
                this.logBlock = this.randomLogBlocks[rand.nextInt(this.randomLogBlocks.length)];
            }

            WorldGenerator worldGenerator = null;
            int finalSize = 4;
            if (this.maxSize > this.minSize) {
                finalSize = this.minSize + rand.nextInt(this.maxSize - this.minSize);
                worldGenerator = new WorldGenLog(this.logBlock, this.leavesBlock, finalSize);
            }
            else if (this.maxSize == this.minSize) {
                finalSize = this.minSize;
                worldGenerator = new WorldGenLog(this.logBlock, this.leavesBlock, finalSize);
            }
            else {
                worldGenerator = new WorldGenLog(this.logBlock, this.leavesBlock, finalSize);
            }

            for (int i = 0; i < this.loops; i++) {
                if (isValidLogCondition(noise, strength, rand)) {
                    int x22 = worldX + rand.nextInt(16);// + 8;
                    int z22 = worldZ + rand.nextInt(16);// + 8;
                    int y22 = world.getHeight(new BlockPos(x22, 0, z22)).getY();

                    if (y22 <= this.maxY) {

                        // If we're in a village, check to make sure the log has extra room to grow to avoid corrupting the village.
                        if (hasPlacedVillageBlocks) {
                            if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), finalSize, SurroundCheckType.CARDINAL, rand, x22, y22, z22)) {
                                return;
                            }
                        }

                        worldGenerator.generate(world, rand, new BlockPos(x22, y22, z22));
                    }
                }
            }
        }
    }

    public boolean isValidLogCondition(float noise, float strength, Random rand) {

        switch (this.logCondition) {
            case ALWAYS_GENERATE:

                return true;

            case RANDOM_CHANCE:

                return (rand.nextInt(this.logConditionChance) == 0);

            case NOISE_GREATER_AND_RANDOM_CHANCE:

                return (noise > this.logConditionNoise && rand.nextInt(this.logConditionChance) == 0);

            case NOISE_LESS_AND_RANDOM_CHANCE:

                return (noise < this.logConditionNoise && rand.nextInt(this.logConditionChance) == 0);

            case X_DIVIDED_BY_STRENGTH:

                return (rand.nextInt((int) (this.logConditionNoise / strength)) == 0);

            default:
                return false;
        }
    }

    public enum LogCondition {
        ALWAYS_GENERATE,
        RANDOM_CHANCE,
        NOISE_GREATER_AND_RANDOM_CHANCE,
        NOISE_LESS_AND_RANDOM_CHANCE,
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
