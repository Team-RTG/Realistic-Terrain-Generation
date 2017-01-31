package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import rtg.config.rtg.ConfigRTG;
import rtg.util.BlockUtil;
import rtg.util.WorldUtil;


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

    private boolean allowBarkCoveredLogs;

    public TreeRTG(boolean notify) {

        super(notify);
    }

    public TreeRTG() {

        this(false);

        this.setLogBlock(Blocks.log.getDefaultState());
        this.setLeavesBlock(Blocks.leaves.getDefaultState());
        this.setTrunkSize(2);
        this.setCrownSize(4);
        this.setNoLeaves(false);

        this.setSaplingBlock(Blocks.sapling.getDefaultState());

        this.setGenerateFlag(2);

        // These need to default to zero as they're only used when generating trees from saplings.
        this.setMinTrunkSize(0);
        this.setMaxTrunkSize(0);
        this.setMinCrownSize(0);
        this.setMaxCrownSize(0);

        // Each tree sub-class is responsible for using (or not using) this list as part of its generation logic.
        this.setValidGroundBlocks(new ArrayList<IBlockState>(Arrays.asList(
            Blocks.grass.getDefaultState(),
            Blocks.dirt.getDefaultState(),
            BlockUtil.getStateDirt(2),
            Blocks.sand.getDefaultState(),
            BlockUtil.getStateSand(1)
        )));

        this.allowBarkCoveredLogs = ConfigRTG.allowBarkCoveredLogs;
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

        return this.isGroundValid(world, trunkPos, ConfigRTG.allowTreesToGenerateOnSand);
    }

    protected boolean isGroundValid(World world, BlockPos trunkPos, boolean sandAllowed) {

        IBlockState g = world.getBlockState(new BlockPos(trunkPos.getX(), trunkPos.getY() - 1, trunkPos.getZ()));

        if (g.getBlock() == Blocks.sand && !sandAllowed) {
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

        IBlockState state = world.getBlockState(pos);

        return state.getBlock().isAir(world, pos)
            || state.getBlock().isLeaves(world, pos)
            || state.getBlock().isWood(world, pos)
            || canGrowInto(state.getBlock());
    }

    @Override
    protected boolean func_150523_a(Block blockType) {

        return this.canGrowInto(blockType);
    }

    protected boolean canGrowInto(Block blockType) {

        Material material = blockType.getMaterial();

        return material == Material.air
            || material == Material.leaves
            || material == Material.plants
            || material == Material.grass
            || material == Material.ground
            || material == Material.wood
            || material == Material.vine
            || material == Material.snow;
    }

    public boolean hasSpaceToGrow(World world, Random rand, BlockPos pos, int treeHeight) {

        WorldUtil worldUtil = new WorldUtil(world);
        if (!worldUtil.isSurroundedByBlock(
            Blocks.air.getDefaultState(),
            treeHeight,
            WorldUtil.SurroundCheckType.UP,
            rand,
            pos.getX(),
            pos.getY(),
            pos.getZ()
        )) {

            //Logger.debug("Unable to grow RTG tree with %d height. Something in the way.", treeHeight);

            return false;
        }

        return true;
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
