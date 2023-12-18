package rtg.api.world.deco;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.util.BlockUtil;
import rtg.api.util.ChunkInfo;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenShrubRTG;

import java.util.Random;


/**
 * @author WhichOnesPink
 */
// TODO: [1.12] This should probably be made to be a tiny DecoTree instead of having all of this code duplication.
public class DecoShrub extends DecoBase {

    private int size;
    private boolean useDefaultRandom;
    private boolean sand;
    private IBlockState[] randomLogBlocks;
    private IBlockState[] randomLeavesBlocks;
    private float loopMultiplier; // Higher = more/bigger shrubs.
    private int minY; // Height restriction.
    private int maxY; // Height restriction.
    private int chance; // Higher = more rare.
    private int notEqualsZeroChance;
    private int loops;
    private int minSize;
    private int maxSize;
    private IBlockState logBlock;
    private IBlockState leavesBlock;

    public DecoShrub() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setSize(-1);
        this.setUseDefaultRandom(false);
        this.setSand(false); //Whether shrubs generate on sand
        this.setRandomLogBlocks(new IBlockState[]{Blocks.LOG.getDefaultState(), BlockUtil.getStateLog(EnumType.SPRUCE)});
        this.setRandomLeavesBlocks(new IBlockState[]{Blocks.LEAVES.getDefaultState(), BlockUtil.getStateLeaf(EnumType.SPRUCE)});
        this.setLoopMultiplier(1f); // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.setMinY(1); // No height limit by default.
        this.setMaxY(255); // No height limit by default.
        this.setChance(1); // 100% chance of generating by default.
        this.setNotEqualsZeroChance(1);
        this.setLoops(1);
        this.setMinSize(3);
        this.setMaxSize(4);
        this.setLogBlock(Blocks.LOG.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES.getDefaultState());
    }

    @Override
// TODO: [1.12] This seems overly complicated. Simplify.
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage, ChunkInfo chunkInot) {

        if (!hasVillage && TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.TREE)) {

            // Shrub size.
            this.size = (this.size == -1) ? rand.nextInt(4) + 1 : this.size;
            if (this.minSize > 0 && this.maxSize > 0 && this.maxSize >= this.minSize) {
                this.size = this.minSize + rand.nextInt(this.maxSize - this.minSize + 1);
            }

            // Do we want random shrubs?
            if (this.useDefaultRandom && this.randomLogBlocks.length > 0 && this.randomLogBlocks.length == this.randomLeavesBlocks.length) {
                int rnd = rand.nextInt(this.randomLogBlocks.length);

                this.setLogBlock(this.randomLogBlocks[rnd]);
                this.setLeavesBlock(this.randomLeavesBlocks[rnd]);
            }

            // TODO: [1.12] This should be done in #setLeavesBlock.
            // Only tweak the leaves after all calls to setLeavesBlock().
            DecoBase.tweakShrubLeaves(this, false, true);

            final int loopCount = (int)((float)this.loops * this.loopMultiplier);
            for (int i = 0; i < loopCount; i++) {

                final BlockPos pos = rtgWorld.world().getHeight(getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16)));
                if (pos.getY() >= this.minY && pos.getY() <= this.maxY) {

                    if (this.notEqualsZeroChance > 1) {
                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
                            new WorldGenShrubRTG(this.size, this.logBlock, this.leavesBlock, this.sand)
                                    .generate(rtgWorld.world(), rand, pos);
                        }
                    }
                    else {
                        if (rand.nextInt(this.chance) == 0) {
                            new WorldGenShrubRTG(this.size, this.logBlock, this.leavesBlock, this.sand)
                                    .generate(rtgWorld.world(), rand, pos);
                        }
                    }
                }
            }
        }
    }

    public int getSize() {

        return size;
    }

    public DecoShrub setSize(int size) {

        this.size = size;
        return this;
    }

    public boolean isUseDefaultRandom() {

        return useDefaultRandom;
    }

    public DecoShrub setUseDefaultRandom(boolean useDefaultRandom) {

        this.useDefaultRandom = useDefaultRandom;
        return this;
    }

    public boolean isSand() {

        return sand;
    }

    public DecoShrub setSand(boolean sand) {

        this.sand = sand;
        return this;
    }

    public IBlockState[] getRandomLogBlocks() {

        return randomLogBlocks;
    }

    public DecoShrub setRandomLogBlocks(IBlockState[] randomLogBlocks) {

        this.randomLogBlocks = randomLogBlocks;
        return this;
    }

    public IBlockState[] getRandomLeavesBlocks() {

        return randomLeavesBlocks;
    }

    public DecoShrub setRandomLeavesBlocks(IBlockState[] randomLeavesBlocks) {

        this.randomLeavesBlocks = randomLeavesBlocks;
        return this;
    }

    public float getLoopMultiplier() {

        return loopMultiplier;
    }

    public DecoShrub setLoopMultiplier(float loopMultiplier) {

        this.loopMultiplier = loopMultiplier;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoShrub setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoShrub setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoShrub setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public int getNotEqualsZeroChance() {

        return notEqualsZeroChance;
    }

    public DecoShrub setNotEqualsZeroChance(int notEqualsZeroChance) {

        this.notEqualsZeroChance = notEqualsZeroChance;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoShrub setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public int getMinSize() {

        return minSize;
    }

    public DecoShrub setMinSize(int minSize) {

        this.minSize = minSize;
        return this;
    }

    public int getMaxSize() {

        return maxSize;
    }

    public DecoShrub setMaxSize(int maxSize) {

        this.maxSize = maxSize;
        return this;
    }

    public IBlockState getLogBlock() {

        return logBlock;
    }

    public DecoShrub setLogBlock(IBlockState logBlock) {

        this.logBlock = logBlock;
        return this;
    }

    public IBlockState getLeavesBlock() {

        return leavesBlock;
    }

    public DecoShrub setLeavesBlock(IBlockState leavesBlock) {

        this.leavesBlock = leavesBlock;
        return this;
    }
}