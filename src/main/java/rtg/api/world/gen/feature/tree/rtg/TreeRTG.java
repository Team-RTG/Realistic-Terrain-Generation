package rtg.api.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSand.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.util.BlockUtil;


/**
 * The base class for all RTG trees.
 *
 * @author WhichOnesPink
 * @see <a href="http://imgur.com/a/uoJsU">RTG Tree Gallery</a>
 */
public class TreeRTG extends WorldGenAbstractTree {

    protected IBlockState logBlock;
    protected IBlockState leavesBlock;
    protected int trunkSize;
    protected int crownSize;
    protected boolean noLeaves;

    protected IBlockState saplingBlock;

    protected int generateFlag;

    protected int minTrunkSize;
    protected int maxTrunkSize;
    protected int minCrownSize;
    protected int maxCrownSize;

    protected ArrayList<IBlockState> validGroundBlocks;

    protected RTGConfig rtgConfig = RTGAPI.config();
    private boolean allowBarkCoveredLogs;

    public TreeRTG() {
        this(false);
    }

    public TreeRTG(boolean notify) {

        super(notify);

        this.setLogBlock(Blocks.LOG.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        this.trunkSize = 2;
        this.crownSize = 4;
        this.setNoLeaves(false);

        this.saplingBlock = Blocks.SAPLING.getDefaultState();

        this.generateFlag = 2;

        // These need to default to zero as they're only used when generating trees from saplings.
        this.setMinTrunkSize(0);
        this.setMaxTrunkSize(0);
        this.setMinCrownSize(0);
        this.setMaxCrownSize(0);

        // Each tree sub-class is responsible for using (or not using) this list as part of its generation logic.
        this.validGroundBlocks = new ArrayList<>(Arrays.asList(
            Blocks.GRASS.getDefaultState(),
            Blocks.DIRT.getDefaultState(),
            BlockUtil.getStateDirt(DirtType.PODZOL),
            Blocks.SAND.getDefaultState(),
            BlockUtil.getStateSand(EnumType.RED_SAND)
        ));

        this.allowBarkCoveredLogs = rtgConfig.ALLOW_BARK_COVERED_LOGS.get();
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        return false;
    }

    public void buildTrunk(World world, Random rand, int x, int y, int z) {

    }

    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize) {

    }

    public void buildLeaves(World world, int x, int y, int z) {

        if (this.noLeaves) {
            return;
        }
    }

    public void buildLeaves(World world, Random rand, int x, int y, int z, int size) {

    }

    protected boolean isGroundValid(World world, BlockPos trunkPos) {

        return this.isGroundValid(world, trunkPos, rtgConfig.ALLOW_TREES_TO_GENERATE_ON_SAND.get());
    }

    protected boolean isGroundValid(World world, BlockPos trunkPos, boolean sandAllowed) {

        IBlockState g = world.getBlockState(new BlockPos(trunkPos.getX(), trunkPos.getY() - 1, trunkPos.getZ()));

        if (g.getBlock() == Blocks.SAND && !sandAllowed) {
            return false;
        }

        for (int i = 0; i < this.validGroundBlocks.size(); i++) {
            if (g == this.validGroundBlocks.get(i)) {
                return true;
            }
        }

        return false;
    }

    protected boolean isGroundValid(World world, ArrayList<BlockPos> trunkPos) {

        if (trunkPos.isEmpty()) {
            throw new RuntimeException("Unable to determine if ground is valid. No trunks.");
        }

        for (int i = 0; i < trunkPos.size(); i++) {
            if (!this.isGroundValid(world, trunkPos.get(i))) {
                return false;
            }
        }

        return true;
    }

    protected void placeLogBlock(World world, BlockPos pos, IBlockState logBlock, int generateFlag) {

        if (this.isReplaceable(world, pos)) {
            world.setBlockState(pos, logBlock, generateFlag);
        }
    }

    protected void placeLeavesBlock(World world, BlockPos pos, IBlockState leavesBlock, int generateFlag) {

        if (world.isAirBlock(pos)) {
            world.setBlockState(pos, leavesBlock, generateFlag);
        }
    }

    @Override
    public boolean isReplaceable(World world, BlockPos pos) {
        IBlockState bs = world.getBlockState(pos);
        return bs.getMaterial().isReplaceable() || super.canGrowInto(bs.getBlock());
    }

    public IBlockState getTrunkLog(IBlockState defaultLog) {

        if (!this.allowBarkCoveredLogs) {
            return defaultLog;
        }

        IBlockState trunkLog;

        try {
            trunkLog = defaultLog.withProperty(BlockLog.LOG_AXIS, BlockLog.EnumAxis.NONE);
        }
        catch (Exception e) {
            trunkLog = defaultLog;
        }

        return trunkLog;
    }

    public IBlockState getLogBlock() {

        return logBlock;
    }

    public TreeRTG setLogBlock(IBlockState logBlock) {

        this.logBlock = logBlock;
        return this;
    }

    public IBlockState getLeavesBlock() {

        return leavesBlock;
    }

    public TreeRTG setLeavesBlock(IBlockState leavesBlock) {

        this.leavesBlock = leavesBlock;
        return this;
    }

    public int getTrunkSize() {

        return trunkSize;
    }

    public TreeRTG setTrunkSize(int trunkSize) {

        this.trunkSize = trunkSize;
        return this;
    }

    public int getCrownSize() {

        return crownSize;
    }

    public TreeRTG setCrownSize(int crownSize) {

        this.crownSize = crownSize;
        return this;
    }

    public boolean getNoLeaves() {

        return noLeaves;
    }

    public TreeRTG setNoLeaves(boolean noLeaves) {

        this.noLeaves = noLeaves;
        return this;
    }

    public IBlockState getSaplingBlock() {

        return saplingBlock;
    }

    public TreeRTG setSaplingBlock(IBlockState saplingBlock) {

        this.saplingBlock = saplingBlock;
        return this;
    }

    public int getGenerateFlag() {

        return generateFlag;
    }

    public TreeRTG setGenerateFlag(int generateFlag) {

        this.generateFlag = generateFlag;
        return this;
    }

    public int getMinTrunkSize() {

        return minTrunkSize;
    }

    public TreeRTG setMinTrunkSize(int minTrunkSize) {

        this.minTrunkSize = minTrunkSize;
        return this;
    }

    public int getMaxTrunkSize() {

        return maxTrunkSize;
    }

    public TreeRTG setMaxTrunkSize(int maxTrunkSize) {

        this.maxTrunkSize = maxTrunkSize;
        return this;
    }

    public int getMinCrownSize() {

        return minCrownSize;
    }

    public TreeRTG setMinCrownSize(int minCrownSize) {

        this.minCrownSize = minCrownSize;
        return this;
    }

    public int getMaxCrownSize() {

        return maxCrownSize;
    }

    public TreeRTG setMaxCrownSize(int maxCrownSize) {

        this.maxCrownSize = maxCrownSize;
        return this;
    }

    public ArrayList<IBlockState> getValidGroundBlocks() {

        return validGroundBlocks;
    }

    public TreeRTG setValidGroundBlocks(ArrayList<IBlockState> validGroundBlocks) {

        this.validGroundBlocks = validGroundBlocks;
        return this;
    }
}
