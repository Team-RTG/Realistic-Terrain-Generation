package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.BlockUtil;
import rtg.api.util.RandomUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenSeaweed;

/**
 * @author WhichOnesPink
 */
public class DecoSeaweed extends DecoBase {

    protected int loops;
    protected float strengthFactorForLoops; // If set, this overrides and dynamically calculates 'loops' based on the strength parameter.
    protected boolean strengthNoiseFactorForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * strength)
    protected boolean strengthNoiseFactorXForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * X * strength)
    protected DecoSeaweed.Distribution distribution; // Parameter object for noise calculations.
    protected Condition condition; // Enum for the various conditions/chances for seaweed gen.
    protected float conditionNoise; // Only applies to a noise-related Condition.
    protected float conditionNoise2; // Only applies to a noise-related Condition.
    protected int conditionChance; // Only applies to a chance-related Condition.
    protected float conditionFloat; // Multi-purpose float.
    protected int minY; // Lower height restriction.
    protected int maxY; // Upper height restriction.
    protected IBlockState seaweedBlock;
    protected int minHeight; // Min seaweed height (only used with certain seaweed presets)
    protected int maxHeight; // Max seaweed height (only used with certain seaweed presets)
    protected Scatter scatter;

    public DecoSeaweed() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setLoops(1);
        this.setStrengthFactorForLoops(0f);
        this.setStrengthNoiseFactorForLoops(false);
        this.setStrengthNoiseFactorXForLoops(false);
        this.setDistribution(new DecoSeaweed.Distribution(100f, 5f, 0.8f));
        this.setCondition(condition.NOISE_GREATER_AND_RANDOM_CHANCE);
        this.setConditionNoise(0f);
        this.setConditionNoise2(0f);
        this.setConditionFloat(0f);
        this.setConditionChance(1);
        this.setMinY(15); // Few blocks below min ocean floor by default.
        this.setMaxY(58); // No seaweed sticking out of the water by default.
        this.setSeaweedBlock(BlockUtil.getStateLeaf(3));
        this.setMinHeight(1);
        this.setMaxHeight(4);
        this.setScatter(new Scatter(16, 0));

        this.addDecoTypes(DecoType.SEAWEED);
    }

    @Override
    public boolean properlyDefined() {
        return super.properlyDefined();
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            /*
             * Determine how much seaweed we're going to try to generate (loopCount).
             * The actual amount of seaweed that ends up being generated could be *less* than this value,
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

            WorldGenerator worldGen = new WorldGenSeaweed(this.seaweedBlock, RandomUtil.getRandomInt(rand, this.minHeight, this.maxHeight));

            for (int i = 0; i < loopCount; i++) {

                int intX = scatter.get(rand, worldX); // + 8;
                int intZ = scatter.get(rand, worldZ); // + 8;
                int intY = RandomUtil.getRandomInt(rand, this.minY, this.maxY);

                //Logger.info("noise = %f", noise);

                if (intY <= this.maxY && intY >= this.minY && isValidCondition(noise, rand, strength)) {

                    worldGen.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                }
            }
        }
    }

    public boolean isValidCondition(float noise, Random rand, float strength) {

        switch (this.condition) {
            case ALWAYS_GENERATE:
                return true;

            case NOISE_GREATER_AND_RANDOM_CHANCE:
                return (noise > this.conditionNoise && rand.nextInt(this.conditionChance) == 0);

            case NOISE_LESSER_AND_RANDOM_CHANCE:
                return (noise < this.conditionNoise && rand.nextInt(this.conditionChance) == 0);

            case NOISE_BETWEEN_AND_RANDOM_CHANCE:
                return (noise > this.conditionNoise && noise < this.conditionNoise2 && rand.nextInt(this.conditionChance) == 0);

            case RANDOM_CHANCE:
                return rand.nextInt(this.conditionChance) == 0;

            case X_DIVIDED_BY_STRENGTH:
                return rand.nextInt((int) (this.conditionFloat / strength)) == 0;

            default:
                return false;
        }
    }

    public enum Condition {
        ALWAYS_GENERATE,
        NOISE_GREATER_AND_RANDOM_CHANCE,
        NOISE_LESSER_AND_RANDOM_CHANCE,
        NOISE_BETWEEN_AND_RANDOM_CHANCE,
        RANDOM_CHANCE,
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

    public DecoSeaweed setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public float getStrengthFactorForLoops() {

        return strengthFactorForLoops;
    }

    public DecoSeaweed setStrengthFactorForLoops(float strengthFactorForLoops) {

        this.strengthFactorForLoops = strengthFactorForLoops;
        return this;
    }

    public boolean isStrengthNoiseFactorForLoops() {

        return strengthNoiseFactorForLoops;
    }

    public DecoSeaweed setStrengthNoiseFactorForLoops(boolean strengthNoiseFactorForLoops) {

        this.strengthNoiseFactorForLoops = strengthNoiseFactorForLoops;
        return this;
    }

    public boolean isStrengthNoiseFactorXForLoops() {

        return strengthNoiseFactorXForLoops;
    }

    public DecoSeaweed setStrengthNoiseFactorXForLoops(boolean strengthNoiseFactorXForLoops) {

        this.strengthNoiseFactorXForLoops = strengthNoiseFactorXForLoops;
        return this;
    }

    public Distribution getDistribution() {

        return distribution;
    }

    public DecoSeaweed setDistribution(Distribution distribution) {

        this.distribution = distribution;
        return this;
    }

    public Condition getCondition() {

        return condition;
    }

    public DecoSeaweed setCondition(Condition condition) {

        this.condition = condition;
        return this;
    }

    public float getConditionNoise() {

        return conditionNoise;
    }

    public DecoSeaweed setConditionNoise(float conditionNoise) {

        this.conditionNoise = conditionNoise;
        return this;
    }

    public float getConditionNoise2() {

        return conditionNoise2;
    }

    public DecoSeaweed setConditionNoise2(float conditionNoise2) {

        this.conditionNoise2 = conditionNoise2;
        return this;
    }

    public int getConditionChance() {

        return conditionChance;
    }

    public DecoSeaweed setConditionChance(int conditionChance) {

        this.conditionChance = conditionChance;
        return this;
    }

    public float getConditionFloat() {

        return conditionFloat;
    }

    public DecoSeaweed setConditionFloat(float conditionFloat) {

        this.conditionFloat = conditionFloat;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoSeaweed setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoSeaweed setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public IBlockState getSeaweedBlock() {

        return seaweedBlock;
    }

    public DecoSeaweed setSeaweedBlock(IBlockState seaweedBlock) {

        this.seaweedBlock = seaweedBlock;
        return this;
    }

    public int getMinHeight() {

        return minHeight;
    }

    public DecoSeaweed setMinHeight(int minHeight) {

        this.minHeight = minHeight;
        return this;
    }

    public int getMaxHeight() {

        return maxHeight;
    }

    public DecoSeaweed setMaxHeight(int maxHeight) {

        this.maxHeight = maxHeight;
        return this;
    }

    public Scatter getScatter() {

        return scatter;
    }

    public DecoSeaweed setScatter(Scatter scatter) {

        this.scatter = scatter;
        return this;
    }
}
