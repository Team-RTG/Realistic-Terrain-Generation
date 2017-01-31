package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Pinus Nigra (Austrian Pine)
 */
public class TreeRTGPinusNigra extends TreeRTG {

    protected IBlockState trunkLog;

    /**
     * <b>Pinus Nigra (Austrian Pine)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGPinusNigra());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)0;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)0;<br>
     * decoTree.setMinTrunkSize(18);<br>
     * decoTree.setMaxTrunkSize(27);<br>
     * decoTree.setMinCrownSize(7);<br>
     * decoTree.setMaxCrownSize(10);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGPinusNigra() {

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

        int height = this.trunkSize;
        int leafheight = this.crownSize;
        float branchIncrease = 0.25f;

        for (int i = 0; i <= height; i++) {
            this.placeLogBlock(world, new BlockPos(x, y + i, z), this.logBlock, this.generateFlag);
        }
        buildLeaves(world, rand, x, y + height, z, 2);
        buildTrunk(world, rand, x, y, z);

        int dir = 0, b;
        float xd, yd, bl = 1f;
        for (int j = height; j >= height - leafheight; j--) {
            bl += branchIncrease;
            dir += rand.nextInt(180) + 90;
            dir -= dir > 360 ? 360 : 0;
            xd = (float) Math.cos(dir * Math.PI / 180f);
            yd = (float) Math.sin(dir * Math.PI / 180f);

            for (b = 0; b <= bl; b++) {
                this.placeLogBlock(world, new BlockPos(x + (int) (b * xd), y + j, z + (int) (b * yd)), this.trunkLog, this.generateFlag);
            }
            buildLeaves(world, rand, x, y + j, z, 2);
            buildLeaves(world, rand, x + (int) (b * xd), y + j, z + (int) (b * yd), 2);
        }

        return true;
    }

    @Override
    public void buildLeaves(World world, Random rand, int x, int y, int z, int size) {

        if (!this.noLeaves) {

            int l;
            int t = (int) Math.pow(size, 2);
            for (int i = -size; i <= size; i++) {
                for (int j = -size; j <= size; j++) {
                    for (int k = -size; k <= size; k++) {
                        l = i * i + j * j + k * k;
                        if (l <= t) {
                            if ((l < t / 2 || rand.nextBoolean())) {
                                this.placeLeavesBlock(world, new BlockPos(x + i, y + j, z + k), this.leavesBlock, this.generateFlag);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void buildTrunk(World world, Random rand, int x, int y, int z) {

        int[] pos = new int[]{0, 0, 1, 0, 0, 1, -1, 0, 0, -1};
        int sh;
        for (int t = 0; t < 5; t++) {
            sh = rand.nextInt(3) + y;
            while (sh > y - 3) {
                if (world.getBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1])) == Blocks.dirt.getDefaultState()) {
                    break;
                }

                this.placeLogBlock(world, new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1]), this.trunkLog, this.generateFlag);
                sh--;
            }
        }
    }
}
