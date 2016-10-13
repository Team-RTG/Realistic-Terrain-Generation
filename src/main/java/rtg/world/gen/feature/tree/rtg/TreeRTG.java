package rtg.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

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
            Blocks.SAND.getDefaultState()
        ));
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
}
