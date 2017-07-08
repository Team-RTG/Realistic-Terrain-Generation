package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenShrubRTG;

/**
 * @author WhichOnesPink
 */
public class DecoShrub extends DecoBase {

    private int size;
    private boolean useDefaultRandom;
    private boolean sand;
    private IBlockState[] randomLogBlocks;
    private IBlockState[] randomLeavesBlocks;
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
        this.size = -1;
        this.useDefaultRandom = false;
        this.sand = true; //Whether shrubs generate on sand
        this.randomLogBlocks = new IBlockState[]{Blocks.LOG.getDefaultState(), BlockUtil.getStateLog(1)};
        this.randomLeavesBlocks = new IBlockState[]{Blocks.LEAVES.getDefaultState(), BlockUtil.getStateLeaf(1)};
        this.setMinSize(3);
        this.setMaxSize(4);
        this.setLogBlock(Blocks.LOG.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES.getDefaultState());

        this.addDecoTypes(DecoType.SHRUB);
    }

    public DecoShrub(boolean useDefaultRandom) {

        this();
        this.useDefaultRandom = true;
    }

    @Override
    public String friendlyName() {
        return "Shrubs";
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(63);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1);
        this.config().addProperty(this.config().NOT_EQUALS_ZERO_CHANCE).set(1);
        this.config().addProperty(this.config().CHANCE).set(1);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(3f);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            DecoBase.tweakShrubLeaves(this, false, true);

            // Shrub size.
            this.size = (this.size == -1) ? rand.nextInt(4) + 1 : this.size;
            if (this.minSize > 0 && this.maxSize > 0 && this.maxSize >= this.minSize) {
                this.size = this.minSize + rand.nextInt(this.maxSize - this.minSize + 1);
            }

            // Do we want random shrubs?
            if (this.useDefaultRandom &&
                this.randomLogBlocks.length > 0 &&
                this.randomLogBlocks.length == this.randomLeavesBlocks.length) {
                int rnd = rand.nextInt(this.randomLogBlocks.length);

                this.setLogBlock(this.randomLogBlocks[rnd]);
                this.setLeavesBlock(this.randomLeavesBlocks[rnd]);
            }

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
            WorldGenerator worldGenerator = new WorldGenShrubRTG(this.size, this.logBlock, this.leavesBlock, this.sand);

            int loopCount = this.config().LOOPS.get();
            loopCount = (this.config().STRENGTH_FACTOR.get() > 0f) ? (int) (this.config().STRENGTH_FACTOR.get() * strength) : loopCount;
            for (int i = 0; i < loopCount; i++) {
                int intX = worldX + rand.nextInt(16);// + 8;
                int intZ = worldZ + rand.nextInt(16);// + 8;
                int intY = rtgWorld.world().getHeight(new BlockPos(intX, 0, intZ)).getY();

                if (this.config().NOT_EQUALS_ZERO_CHANCE.get() > 1) {

                    if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get() && rand.nextInt(this.config().NOT_EQUALS_ZERO_CHANCE.get()) != 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, rtgWorld.world(), rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
                else {

                    if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get() && rand.nextInt(this.config().CHANCE.get()) == 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, rtgWorld.world(), rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
            }
        }
    }

    private boolean generateWorldGenerator(WorldGenerator worldGenerator, WorldUtil worldUtil, World world, Random rand, int x, int y, int z, boolean hasPlacedVillageBlocks) {
        // If we're in a village, check to make sure the shrub has extra room to grow to avoid corrupting the village.
        if (hasPlacedVillageBlocks) {
            if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, WorldUtil.SurroundCheckType.CARDINAL, rand, x, y, z)) {
                return false;
            }
        }

        return worldGenerator.generate(world, rand, new BlockPos(x, y, z));
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