package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import rtg.RTG;
import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil;

/**
 * The base class for all RTG trees.
 *
 * @author WhichOnesPink
 * @see <a href="http://imgur.com/a/uoJsU">RTG Tree Gallery</a>
 */
public class TreeRTG extends WorldGenAbstractTree {

    public IBlockState logBlock;
    public IBlockState leavesBlock;
    public int trunkSize;
    public int crownSize;
    public boolean noLeaves;

    public IBlockState saplingBlock;

    public int generateFlag;

    public int minTrunkSize;
    public int maxTrunkSize;
    public int minCrownSize;
    public int maxCrownSize;

    public boolean allowBarkCoveredLogs;

    public ArrayList<IBlockState> validGroundBlocks;

    public TreeRTG(boolean notify) {

        super(notify);
    }

    public TreeRTG() {

        this(false);

        this.logBlock = Blocks.LOG.getDefaultState();
        this.leavesBlock = Blocks.LEAVES.getDefaultState();
        this.trunkSize = 2;
        this.crownSize = 4;
        this.noLeaves = false;

        this.saplingBlock = Blocks.SAPLING.getDefaultState();

        this.generateFlag = 2;

        // These need to default to zero as they're only used when generating trees from saplings.
        this.minTrunkSize = 0;
        this.maxTrunkSize = 0;
        this.minCrownSize = 0;
        this.maxCrownSize = 0;

        // Each tree sub-class is responsible for using (or not using) this list as part of its generation logic.
        this.validGroundBlocks = new ArrayList<IBlockState>(Arrays.asList(
            Blocks.GRASS.getDefaultState(),
            Blocks.DIRT.getDefaultState(),
            BlockUtil.getStateDirt(2),
            Blocks.SAND.getDefaultState(),
            BlockUtil.getStateSand(1)
        ));

        this.allowBarkCoveredLogs = RTG.instance.getConfig().allowBarkCoveredLogs.get();
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

    public TreeRTG setLogBlock(IBlockState logBlock) {

        this.logBlock = logBlock;
        return this;
    }

    public TreeRTG setLeavesBlock(IBlockState leavesBlock) {

        this.leavesBlock = leavesBlock;
        return this;
    }

    public TreeRTG setTrunkSize(int trunkSize) {

        this.trunkSize = trunkSize;
        return this;
    }

    public TreeRTG setCrownSize(int crownSize) {

        this.crownSize = crownSize;
        return this;
    }

    public TreeRTG setNoLeaves(boolean noLeaves) {

        this.noLeaves = noLeaves;
        return this;
    }

    protected boolean isGroundValid(World world, BlockPos trunkPos) {

        return this.isGroundValid(world, trunkPos, RTG.instance.getConfig().allowTreesToGenerateOnSand.get());
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
    public boolean isReplaceable(World world, BlockPos pos)
    {
        IBlockState state = world.getBlockState(pos);

        return state.getBlock().isAir(state, world, pos)
            || state.getBlock().isLeaves(state, world, pos)
            || state.getBlock().isWood(world, pos)
            || canGrowInto(state.getBlock());
    }

    @Override
    protected boolean canGrowInto(Block blockType)
    {
        Material material = blockType.getDefaultState().getMaterial();

        return material == Material.AIR
            || material == Material.LEAVES
            || material == Material.PLANTS
            || material == Material.GRASS
            || material == Material.GROUND
            || material == Material.WOOD
            || material == Material.VINE
            || material == Material.SNOW;
    }
    public boolean hasSpaceToGrow(World world, Random rand, BlockPos pos, int treeHeight) {

        WorldUtil worldUtil = new WorldUtil(world);
        if (!worldUtil.isSurroundedByBlock(
            Blocks.AIR.getDefaultState(),
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
}
