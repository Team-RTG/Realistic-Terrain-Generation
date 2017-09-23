package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.WorldUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenWave;

/**
 * @author WhichOnesPink
 */
public class DecoWave extends DecoBase {

    private IBlockState waveBlock;
    private int loops;
    private DecoWave.Distribution distribution; // Parameter object for noise calculations.
    private ConditionType conditionType; // Enum for the various conditions/chances for generation.
    private float conditionNoise; // Only applies to a noise-related ConditionType.
    private int conditionChance; // Only applies to a chance-related ConditionType.
    private int minY; // Height restriction.
    private int maxY; // Height restriction.
    private int minSize; // Min height (only used with certain presets)
    private int maxSize; // Max height (only used with certain presets)

    public DecoWave() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setLoops(1);
        this.setDistribution(new DecoWave.Distribution(100f, 5f, 0.8f));
        this.setConditionType(ConditionType.NOISE_GREATER_AND_RANDOM_CHANCE);
        this.setConditionNoise(0f);
        this.setConditionChance(1);
        this.setMinY(63);
        this.setMaxY(63);
        this.setWaveBlock(Blocks.WATER.getDefaultState().withProperty(BlockLiquid.LEVEL, 6));
        this.setMinSize(10);
        this.setMaxSize(12);

        this.addDecoTypes(DecoType.WAVE);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            float noise = rtgWorld.simplex().noise2(worldX / this.distribution.noiseDivisor, worldZ / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;
            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());

            WorldGenerator worldGenerator = null;
            int finalSize = 8;
            if (this.maxSize > this.minSize) {
                finalSize = this.minSize + rand.nextInt(this.maxSize - this.minSize);
                worldGenerator = new WorldGenWave(finalSize);
            }
            else if (this.maxSize == this.minSize) {
                finalSize = this.minSize;
                worldGenerator = new WorldGenWave(finalSize);
            }
            else {
                worldGenerator = new WorldGenWave(finalSize);
            }

            for (int i = 0; i < this.loops; i++) {
                if (isValidCondition(noise, strength, rand)) {
                    int x22 = worldX + rand.nextInt(16);// + 8;
                    int z22 = worldZ + rand.nextInt(16);// + 8;
                    int y22 = rtgWorld.world().getHeight(new BlockPos(x22, 0, z22)).getY();

                    //Logger.info("Strength = %f @ %d %d", strength, worldX, worldZ);

                    if (y22 >= this.minY && y22 <= this.maxY) {

                        worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(x22, y22, z22));
                    }
                }
            }
        }
    }

    public boolean isValidCondition(float noise, float strength, Random rand) {

        switch (this.conditionType) {
            case ALWAYS_GENERATE:

                return true;

            case RANDOM_CHANCE:

                return (rand.nextInt(this.conditionChance) == 0);

            case NOISE_GREATER_AND_RANDOM_CHANCE:

                return (noise > this.conditionNoise && rand.nextInt(this.conditionChance) == 0);

            case NOISE_LESS_AND_RANDOM_CHANCE:

                return (noise < this.conditionNoise && rand.nextInt(this.conditionChance) == 0);

            case X_DIVIDED_BY_STRENGTH:

                return (rand.nextInt((int) (this.conditionNoise / strength)) == 0);

            default:
                return false;
        }
    }

    public enum ConditionType {
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

        private float noiseDivisor;
        private float noiseFactor;
        private float noiseAddend;

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

    public DecoWave setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public Distribution getDistribution() {

        return distribution;
    }

    public DecoWave setDistribution(Distribution distribution) {

        this.distribution = distribution;
        return this;
    }

    public ConditionType getConditionType() {

        return conditionType;
    }

    public DecoWave setConditionType(ConditionType conditionType) {

        this.conditionType = conditionType;
        return this;
    }

    public float getConditionNoise() {

        return conditionNoise;
    }

    public DecoWave setConditionNoise(float conditionNoise) {

        this.conditionNoise = conditionNoise;
        return this;
    }

    public int getConditionChance() {

        return conditionChance;
    }

    public DecoWave setConditionChance(int conditionChance) {

        this.conditionChance = conditionChance;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoWave setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoWave setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public IBlockState getWaveBlock() {

        return waveBlock;
    }

    public DecoWave setWaveBlock(IBlockState waveBlock) {

        this.waveBlock = waveBlock;
        return this;
    }

    public int getMinSize() {

        return minSize;
    }

    public DecoWave setMinSize(int minSize) {

        this.minSize = minSize;
        return this;
    }

    public int getMaxSize() {

        return maxSize;
    }

    public DecoWave setMaxSize(int maxSize) {

        this.maxSize = maxSize;
        return this;
    }
}
