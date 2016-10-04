package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.WorldUtil;
import rtg.util.WorldUtil.SurroundCheckType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenShrubRTG;

/**
 * @author WhichOnesPink
 */
public class DecoShrub extends DecoBase {

    public int size;
    public boolean useDefaultRandom;
    public boolean Sand;
    public IBlockState[] randomLogBlocks;
    public IBlockState[] randomLeavesBlocks;
    public float strengthFactor; // Higher = more/bigger shrubs.
    public int minY; // Height restriction.
    public int maxY; // Height restriction.
    public int chance; // Higher = more rare.
    public int notEqualsZerochance;
    public int loops;
    public int minSize;
    public int maxSize;
    public IBlockState logBlock;
    public IBlockState leavesBlock;

    public DecoShrub() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.size = -1;
        this.useDefaultRandom = false;
        this.Sand = true; //Whether shrubs generate on sand
        this.randomLogBlocks = new IBlockState[]{Blocks.LOG.getDefaultState(), Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE)}; //Not sure if this breaks the random generator
        this.randomLeavesBlocks = new IBlockState[]{Blocks.LEAVES.getDefaultState(), Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE)}; //Not sure if this breaks the random generator
        this.strengthFactor = 3f; // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.minY = 1; // No height limit by default.
        this.maxY = 255; // No height limit by default.
        this.chance = 1; // 100% chance of generating by default.
        this.notEqualsZerochance = 1;
        this.loops = 1;
        this.minSize = 3;
        this.maxSize = 4;
        this.logBlock = Blocks.LOG.getDefaultState();
        this.leavesBlock = Blocks.LEAVES.getDefaultState();

        this.addDecoTypes(DecoType.SHRUB);
    }

    public DecoShrub(boolean useDefaultRandom) {

        this();
        this.useDefaultRandom = true;
    }

    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

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

                this.logBlock = this.randomLogBlocks[rnd];
                this.leavesBlock = this.randomLeavesBlocks[rnd];
            }

            WorldUtil worldUtil = new WorldUtil(world);
            WorldGenerator worldGenerator = new WorldGenShrubRTG(this.size, this.logBlock, this.leavesBlock, this.Sand);

            int loopCount = this.loops;
            loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : loopCount;
            for (int i = 0; i < loopCount; i++) {
                int intX = chunkX + rand.nextInt(16);// + 8;
                int intZ = chunkY + rand.nextInt(16);// + 8;
                int intY = world.getHeight(new BlockPos(intX, 0, intZ)).getY();

                if (this.notEqualsZerochance > 1) {

                    if (intY >= this.minY && intY <= this.maxY && rand.nextInt(this.notEqualsZerochance) != 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, world, rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
                else {

                    if (intY >= this.minY && intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, world, rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
            }
        }
    }

    private boolean generateWorldGenerator(WorldGenerator worldGenerator, WorldUtil worldUtil, World world, Random rand, int x, int y, int z, boolean hasPlacedVillageBlocks) {
        // If we're in a village, check to make sure the shrub has extra room to grow to avoid corrupting the village.
        if (hasPlacedVillageBlocks) {
            if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, SurroundCheckType.CARDINAL, rand, x, y, z)) {
                return false;
            }
        }

        return worldGenerator.generate(world, rand, new BlockPos(x, y, z));
    }
}