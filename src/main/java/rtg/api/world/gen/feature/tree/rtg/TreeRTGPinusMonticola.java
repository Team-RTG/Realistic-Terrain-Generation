package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.common.IPlantable;

/**
 * Pinus Monticola (California Mountain Pine)
 */
public class TreeRTGPinusMonticola extends TreeRTG {

    protected IBlockState trunkLog;

    private int height;

    /**
     * <b>Pinus Monticola (California Mountain Pine)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGPinusMonticola());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)0;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)0;<br>
     * decoTree.setMinTrunkSize(2);<br>
     * decoTree.setMaxTrunkSize(3);<br>
     * decoTree.setMinCrownSize(5);<br>
     * decoTree.setMaxCrownSize(12);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGPinusMonticola() {

        super();
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (!this.isGroundValid(world, pos)) {
            return false;
        }

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        this.trunkLog = this.getTrunkLog(this.logBlock);

        this.height = this.trunkSize + this.crownSize;

        int l = rand.nextInt(this.height * 2) + this.height * 2;
        int i1 = this.height + rand.nextInt(this.height);
        int j1 = l - i1;
        int k1 = 2 + rand.nextInt(2);
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256) {
            int i2;
            int l3;

            for (int l1 = y; l1 <= y + 1 + l && flag; ++l1) {
                boolean flag1 = true;

                if (l1 - y < i1) {
                    l3 = 0;
                }
                else {
                    l3 = k1;
                }

                for (i2 = x - l3; i2 <= x + l3 && flag; ++i2) {
                    for (int j2 = z - l3; j2 <= z + l3 && flag; ++j2) {
                        if (l1 >= 0 && l1 < 256) {
                            BlockPos pos2 = new BlockPos(new BlockPos(i2, l1, j2));
                            flag = this.isReplaceable(world, pos2);
                        }
                        else {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag) {
                return false;
            }
            else {
                BlockPos pos3 = new BlockPos(x, y - 1, z);
                IBlockState block1 = world.getBlockState(pos3);

                boolean isSoil = block1.getBlock().canSustainPlant(block1, world, pos3, EnumFacing.UP, (IPlantable) block1);
                if (isSoil && y < 256 - l - 1) {
                    block1.getBlock().onPlantGrow(block1, world, pos3.down(), pos3);
                    l3 = rand.nextInt(2);
                    i2 = 1;
                    byte b0 = 0;
                    int k2;
                    int i4;

                    for (i4 = 0; i4 <= j1; ++i4) {
                        k2 = y + l - i4;

                        for (int l2 = x - l3; l2 <= x + l3; ++l2) {
                            int i3 = l2 - x;

                            for (int j3 = z - l3; j3 <= z + l3; ++j3) {
                                int k3 = j3 - z;
                                BlockPos pos5 = new BlockPos(l2, k2, j3);

                                if ((Math.abs(i3) != l3 || Math.abs(k3) != l3 || l3 <= 0)
                                    && world.getBlockState(pos5).getBlock().canBeReplacedByLeaves(world.getBlockState(pos5), world, pos5)) {
                                    if (!this.noLeaves) {
                                        this.placeLeavesBlock(world, new BlockPos(l2, k2, j3), this.leavesBlock, this.generateFlag);
                                    }
                                }
                            }
                        }

                        if (l3 >= i2) {
                            l3 = b0;
                            b0 = 1;
                            ++i2;

                            if (i2 > k1) {
                                i2 = k1;
                            }
                        }
                        else {
                            ++l3;
                        }
                    }

                    i4 = rand.nextInt(3);

                    for (k2 = 0; k2 < l - i4; ++k2) {
                        this.placeLogBlock(world, new BlockPos(x, y + k2, z), this.logBlock, this.generateFlag);
                    }

                    if (this.height > 4) {
                        buildTrunk(world, rand, x, y, z);
                    }

                    return true;
                }
                else {
                    return false;
                }
            }
        }
        else {
            return false;
        }
    }

    @Override
    public void buildTrunk(World world, Random rand, int x, int y, int z) {

        int[] pos = new int[]{0, 0, 1, 0, 0, 1, -1, 0, 0, -1};
        int sh;
        Block b;
        for (int t = 0; t < (pos.length / 2); t++) {
            sh = rand.nextInt(4) + y - 2;
            while (sh > y - 1) {
                if (world.getBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1])) == Blocks.GRASS.getDefaultState()) {
                    break;
                }

                this.placeLogBlock(world, new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1]), this.trunkLog, this.generateFlag);
                sh--;
            }
        }
    }

    @Override
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize) {

        if (logLength == 3 && Math.abs(dX) + Math.abs(dZ) == 2) {
            logLength--;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = 0; k < 2; k++) {
                    if (Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1) {
                        buildLeaves(world, x + i + (dX * logLength), y + k, z + j + (dZ * logLength));
                    }
                }
            }
        }

        for (int m = 1; m <= logLength; m++) {
            this.placeLogBlock(world, new BlockPos(x + (dX * m), y, z + (dZ * m)), this.logBlock, this.generateFlag);
        }
    }

    @Override
    public void buildLeaves(World world, int x, int y, int z) {

        if (!this.noLeaves) {

            IBlockState b = world.getBlockState(new BlockPos(x, y, z));
            this.placeLeavesBlock(world, new BlockPos(x, y, z), this.leavesBlock, this.generateFlag);
        }
    }
}