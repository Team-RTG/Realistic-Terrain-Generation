package rtg.api.world.gen.feature.tree.rtg;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.google.common.collect.Lists;

/**
 * Quercus Robur (Pedunculate Oak)
 */
public class TreeRTGQuercusRobur extends TreeRTG {

    int heightLimit;
    int height;
    double heightAttenuation = 0.618D;
    double branchSlope = 0.381D;
    double scaleWidth = 1.0D;
    double leafDensity = 1.0D;
    int trunkSize = 1;
    int heightLimitLimit = 12;
    /**
     * Sets the distance limit for how far away the generator will populate leaves from the base leaf node.
     */
    int leafDistanceLimit = 4;
    List<TreeRTGQuercusRobur.FoliageCoordinates> field_175948_j;
    private Random rand;
    private World world;
    private BlockPos basePos = BlockPos.ORIGIN;

    /**
     * <b>Quercus Robur (Pedunculate Oak)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, <s>trunkSize</s>, <s>crownSize</s>, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGQuercusRobur());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)0;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)0;<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGQuercusRobur() {

        super();

        this.setLogBlock(Blocks.LOG.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        this.trunkSize = 4;
        this.crownSize = 8;
    }

    /**
     * Generates a list of leaf nodes for the tree, to be populated by generateLeaves.
     */
    void generateLeafNodeList() {

        this.heightLimit = this.trunkSize + this.crownSize;

        this.height = (int) ((double) this.heightLimit * this.heightAttenuation);

        if (this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }

        int i = (int) (1.382D + Math.pow(this.leafDensity * (double) this.heightLimit / 13.0D, 2.0D));

        if (i < 1) {
            i = 1;
        }

        int j = this.basePos.getY() + this.height;
        int k = this.heightLimit - this.leafDistanceLimit;
        this.field_175948_j = Lists.<TreeRTGQuercusRobur.FoliageCoordinates>newArrayList();
        this.field_175948_j.add(new TreeRTGQuercusRobur.FoliageCoordinates(this.basePos.up(k), j));

        for (; k >= 0; --k) {
            float f = this.layerSize(k);

            if (f >= 0.0F) {
                for (int l = 0; l < i; ++l) {
                    double d0 = this.scaleWidth * (double) f * ((double) this.rand.nextFloat() + 0.328D);
                    double d1 = (double) (this.rand.nextFloat() * 2.0F) * Math.PI;
                    double d2 = d0 * Math.sin(d1) + 0.5D;
                    double d3 = d0 * Math.cos(d1) + 0.5D;
                    BlockPos blockpos = this.basePos.add(d2, (double) (k - 1), d3);
                    BlockPos blockpos1 = blockpos.up(this.leafDistanceLimit);

                    if (this.checkBlockLine(blockpos, blockpos1) == -1) {
                        int i1 = this.basePos.getX() - blockpos.getX();
                        int j1 = this.basePos.getZ() - blockpos.getZ();
                        double d4 = (double) blockpos.getY() - Math.sqrt((double) (i1 * i1 + j1 * j1)) * this.branchSlope;
                        int k1 = d4 > (double) j ? j : (int) d4;
                        BlockPos blockpos2 = new BlockPos(this.basePos.getX(), k1, this.basePos.getZ());

                        if (this.checkBlockLine(blockpos2, blockpos) == -1) {
                            this.field_175948_j.add(new TreeRTGQuercusRobur.FoliageCoordinates(blockpos, blockpos2.getY()));
                        }
                    }
                }
            }
        }
    }

    void func_181631_a(BlockPos p_181631_1_, float p_181631_2_, IBlockState p_181631_3_) {

        int i = (int) ((double) p_181631_2_ + 0.618D);

        for (int j = -i; j <= i; ++j) {
            for (int k = -i; k <= i; ++k) {
                if (Math.pow((double) Math.abs(j) + 0.5D, 2.0D) + Math.pow((double) Math.abs(k) + 0.5D, 2.0D) <= (double) (p_181631_2_ * p_181631_2_)) {
                    BlockPos blockpos = p_181631_1_.add(j, 0, k);
                    net.minecraft.block.state.IBlockState state = this.world.getBlockState(blockpos);

                    if (state.getBlock().isAir(state, this.world, blockpos) || state.getBlock().isLeaves(state, this.world, blockpos)) {
                        this.setBlockAndNotifyAdequately(this.world, blockpos, p_181631_3_);
                    }
                }
            }
        }
    }

    /**
     * Gets the rough size of a layer of the tree.
     */
    float layerSize(int p_76490_1_) {

        if ((float) p_76490_1_ < (float) this.heightLimit * 0.3F) {
            return -1.0F;
        }
        else {
            float f = (float) this.heightLimit / 2.0F;
            float f1 = f - (float) p_76490_1_;
            float f2 = MathHelper.sqrt(f * f - f1 * f1);

            if (f1 == 0.0F) {
                f2 = f;
            }
            else if (Math.abs(f1) >= f) {
                return 0.0F;
            }

            return f2 * 0.5F;
        }
    }

    float leafSize(int p_76495_1_) {

        return p_76495_1_ >= 0 && p_76495_1_ < this.leafDistanceLimit ? (p_76495_1_ != 0 && p_76495_1_ != this.leafDistanceLimit - 1 ? 3.0F : 2.0F) : -1.0F;
    }

    /**
     * Generates the leaves surrounding an individual entry in the leafNodes list.
     */
    void generateLeafNode(BlockPos pos) {

        for (int i = 0; i < this.leafDistanceLimit; ++i) {
            this.func_181631_a(pos.up(i), this.leafSize(i), this.leavesBlock.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)));
        }
    }

    void func_175937_a(BlockPos p_175937_1_, BlockPos p_175937_2_, IBlockState p_175937_3_) {

        BlockPos blockpos = p_175937_2_.add(-p_175937_1_.getX(), -p_175937_1_.getY(), -p_175937_1_.getZ());
        int i = this.getGreatestDistance(blockpos);
        float f = (float) blockpos.getX() / (float) i;
        float f1 = (float) blockpos.getY() / (float) i;
        float f2 = (float) blockpos.getZ() / (float) i;

        for (int j = 0; j <= i; ++j) {
            BlockPos blockpos1 = p_175937_1_.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1), (double) (0.5F + (float) j * f2));
            BlockLog.EnumAxis blocklog$enumaxis = this.func_175938_b(p_175937_1_, blockpos1);
            this.setBlockAndNotifyAdequately(this.world, blockpos1, p_175937_3_.withProperty(BlockLog.LOG_AXIS, blocklog$enumaxis));
        }
    }

    /**
     * Returns the absolute greatest distance in the BlockPos object.
     */
    private int getGreatestDistance(BlockPos posIn) {

        int i = MathHelper.abs(posIn.getX());
        int j = MathHelper.abs(posIn.getY());
        int k = MathHelper.abs(posIn.getZ());
        return k > i && k > j ? k : (j > i ? j : i);
    }

    private BlockLog.EnumAxis func_175938_b(BlockPos p_175938_1_, BlockPos p_175938_2_) {

        BlockLog.EnumAxis blocklog$enumaxis = BlockLog.EnumAxis.Y;
        int i = Math.abs(p_175938_2_.getX() - p_175938_1_.getX());
        int j = Math.abs(p_175938_2_.getZ() - p_175938_1_.getZ());
        int k = Math.max(i, j);

        if (k > 0) {
            if (i == k) {
                blocklog$enumaxis = BlockLog.EnumAxis.X;
            }
            else if (j == k) {
                blocklog$enumaxis = BlockLog.EnumAxis.Z;
            }
        }

        return blocklog$enumaxis;
    }

    /**
     * Generates the leaf portion of the tree as specified by the leafNodes list.
     */
    void generateLeaves() {

        for (TreeRTGQuercusRobur.FoliageCoordinates worldgenbigtree$foliagecoordinates : this.field_175948_j) {
            this.generateLeafNode(worldgenbigtree$foliagecoordinates);
        }
    }

    /**
     * Indicates whether or not a leaf node requires additional wood to be added to preserve integrity.
     */
    boolean leafNodeNeedsBase(int p_76493_1_) {

        return (double) p_76493_1_ >= (double) this.heightLimit * 0.2D;
    }

    /**
     * Places the trunk for the big tree that is being generated. Able to generate double-sized trunks by changing a
     * field that is always 1 to 2.
     */
    void generateTrunk() {

        BlockPos blockpos = this.basePos;
        BlockPos blockpos1 = this.basePos.up(this.height);
        IBlockState block = this.logBlock;
        this.func_175937_a(blockpos, blockpos1, block);

        if (this.trunkSize == 2) {
            this.func_175937_a(blockpos.east(), blockpos1.east(), block);
            this.func_175937_a(blockpos.east().south(), blockpos1.east().south(), block);
            this.func_175937_a(blockpos.south(), blockpos1.south(), block);
        }
    }

    /**
     * Generates additional wood blocks to fill out the bases of different leaf nodes that would otherwise degrade.
     */
    void generateLeafNodeBases() {

        for (TreeRTGQuercusRobur.FoliageCoordinates worldgenbigtree$foliagecoordinates : this.field_175948_j) {
            int i = worldgenbigtree$foliagecoordinates.func_177999_q();
            BlockPos blockpos = new BlockPos(this.basePos.getX(), i, this.basePos.getZ());

            if (!blockpos.equals(worldgenbigtree$foliagecoordinates) && this.leafNodeNeedsBase(i - this.basePos.getY())) {
                this.func_175937_a(blockpos, worldgenbigtree$foliagecoordinates, this.logBlock);
            }
        }
    }

    /**
     * Checks a line of blocks in the world from the first coordinate to triplet to the second, returning the distance
     * (in blocks) before a non-air, non-leaf block is encountered and/or the end is encountered.
     */
    int checkBlockLine(BlockPos posOne, BlockPos posTwo) {

        BlockPos blockpos = posTwo.add(-posOne.getX(), -posOne.getY(), -posOne.getZ());
        int i = this.getGreatestDistance(blockpos);
        float f = (float) blockpos.getX() / (float) i;
        float f1 = (float) blockpos.getY() / (float) i;
        float f2 = (float) blockpos.getZ() / (float) i;

        if (i == 0) {
            return -1;
        }
        else {
            for (int j = 0; j <= i; ++j) {
                BlockPos blockpos1 = posOne.add((double) (0.5F + (float) j * f), (double) (0.5F + (float) j * f1), (double) (0.5F + (float) j * f2));

                if (!this.isReplaceable(blockpos1)) {

                    String replaceBlock = world.getBlockState(blockpos1).getBlock().getLocalizedName();

                    //Logger.debug("Block at %d %d %d (%s) is not replaceable.", blockpos1.getX(), blockpos1.getY(), blockpos1.getZ(), replaceBlock);

                    return j;
                }
            }

            return -1;
        }
    }

    public void func_175904_e() {

        this.leafDistanceLimit = 5;
    }

    public boolean generate(World worldIn, Random rand, BlockPos position) {

        this.world = worldIn;
        this.basePos = position;
        this.rand = new Random(rand.nextLong());

        int x = position.getX();
        int y = position.getY();
        int z = position.getZ();

        if (!this.isGroundValid(world, position)) {
            return false;
        }

        if (this.heightLimit == 0) {
            this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
        }

        if (!this.validTreeLocation()) {
            this.world = null; //Fix vanilla Mem leak, holds latest world
            return false;
        }
        else {
            this.generateLeafNodeList();
            this.generateLeaves();
            this.generateTrunk();
            this.generateLeafNodeBases();
            this.world = null; //Fix vanilla Mem leak, holds latest world
            return true;
        }
    }

    /**
     * Returns a boolean indicating whether or not the current location for the tree, spanning basePos to to the height
     * limit, is valid.
     */
    private boolean validTreeLocation() {

        BlockPos down = this.basePos.down();
        net.minecraft.block.state.IBlockState state = this.world.getBlockState(down);
        boolean isSoil = state.getBlock().canSustainPlant(state, this.world, down, net.minecraft.util.EnumFacing.UP, ((net.minecraft.block.BlockSapling) Blocks.SAPLING));

        if (!isSoil) {
            //Logger.debug("Invalid tree location! Ground block is not soil.");
            return false;
        }
        else {
            int i = this.checkBlockLine(this.basePos, this.basePos.up(this.heightLimit - 1));

            if (i == -1) {
                return true;
            }
            else if (i < 6) {
                //Logger.debug("Invalid tree location! checkBlockLine() == false");
                return false;
            }
            else {
                this.heightLimit = i;
                return true;
            }
        }
    }

    private boolean isReplaceable(BlockPos pos) {

        IBlockState state = world.getBlockState(pos);
        return state.getBlock().isAir(state, world, pos)
            || state.getBlock().isLeaves(state, world, pos)
            || state.getBlock() == Blocks.SAPLING
            || state.getBlock().isWood(world, pos);
    }

    static class FoliageCoordinates extends BlockPos {

        private final int field_178000_b;

        public FoliageCoordinates(BlockPos p_i45635_1_, int p_i45635_2_) {

            super(p_i45635_1_.getX(), p_i45635_1_.getY(), p_i45635_1_.getZ());
            this.field_178000_b = p_i45635_2_;
        }

        public int func_177999_q() {

            return this.field_178000_b;
        }
    }
}