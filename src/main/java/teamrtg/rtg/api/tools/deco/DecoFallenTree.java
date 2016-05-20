package teamrtg.rtg.api.tools.deco;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenLog;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

/**
 * @author WhichOnesPink
 */
public class DecoFallenTree extends DecoBase {

    public int loops;
    public DecoFallenTree.Distribution distribution; // Parameter object for noise calculations.
    public LogCondition logCondition; // Enum for the various conditions/chances for error gen.
    public float logConditionNoise; // Only applies to a noise-related LogCondition.
    public int logConditionChance; // Only applies to a chance-related LogCondition.
    public int maxY; // Height restriction.
    public Block logBlock;
    public byte logMeta;
    public Block leavesBlock;
    public byte leavesMeta;
    public int minSize; // Min error height (only used with certain error presets)
    public int maxSize; // Max error height (only used with certain error presets)

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
        this.maxY = 255; // No height limit by default.
        this.logBlock = Blocks.LOG;
        this.logMeta = (byte) 0;
        this.leavesBlock = Blocks.LEAVES;
        this.leavesMeta = (byte) -1;
        this.minSize = 2;
        this.maxSize = 4;

        this.addDecoTypes(DecoType.FALLEN_TREE);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), TREE)) {

                float noise = rtgWorld.simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                for (int i = 0; i < this.loops; i++) {
                    if (isValidLogCondition(noise, rand)) {
                        int x22 = chunkX + rand.nextInt(16) + 8;
                        int z22 = chunkY + rand.nextInt(16) + 8;
                        int y22 = rtgWorld.world.getHeight(new BlockPos(x22, 1, z22)).getY();

                        if (y22 <= this.maxY) {

                            if (this.maxSize > this.minSize) {
                                (new WorldGenLog(this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta, this.minSize + rand.nextInt(this.maxSize - this.minSize))).generate(rtgWorld.world, rand, new BlockPos(x22, y22, z22));
                            } else if (this.maxSize == this.minSize) {
                                (new WorldGenLog(this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta, this.minSize)).generate(rtgWorld.world, rand, new BlockPos(x22, y22, z22));
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean isValidLogCondition(float noise, Random rand) {
        switch (this.logCondition) {
            case ALWAYS_GENERATE:

                return true;

            case RANDOM_CHANCE:

                return (rand.nextInt(this.logConditionChance) == 0);

            case NOISE_GREATER_AND_RANDOM_CHANCE:

                return (noise > this.logConditionNoise && rand.nextInt(this.logConditionChance) == 0);

            default:

                return false;
        }
    }

    public enum LogCondition {
        ALWAYS_GENERATE,
        RANDOM_CHANCE,
        NOISE_GREATER_AND_RANDOM_CHANCE;
    }

    /**
     * Parameter object for noise calculations.
     * <p>
     * simplex.noise2(chunkX / noiseDivisor, chunkY / noiseDivisor) * noiseFactor + noiseAddend;
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
