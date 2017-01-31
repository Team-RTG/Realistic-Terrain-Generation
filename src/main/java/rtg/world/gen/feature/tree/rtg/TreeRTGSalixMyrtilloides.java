package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Salix Myrtilloides (Swamp Willow)
 */
public class TreeRTGSalixMyrtilloides extends TreeRTG {

    /**
     * <b>Salix Myrtilloides (Swamp Willow)</b><br>
     * <i>Only spawns near water</i><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, <s>trunkSize</s>, <s>crownSize</s>, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGSalixMyrtilloides());<br>
     * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
     * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
     * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
     * decoTree.treeConditionNoise = 0f;<br>
     * decoTree.treeConditionChance = 4;<br>
     * decoTree.logBlock = Blocks.log;<br>
     * decoTree.logMeta = (byte)0;<br>
     * decoTree.leavesBlock = Blocks.leaves;<br>
     * decoTree.leavesMeta = (byte)0;<br>
     * decoTree.noLeaves = false;<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGSalixMyrtilloides() {

        super();

        this.setLogBlock(Blocks.log.getStateFromMeta(0)).setLeavesBlock(Blocks.leaves.getStateFromMeta(0));
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        IBlockState cb;
        boolean earth = false;
        boolean water = false;
        for (int c1 = -2; c1 <= 2; c1++) {
            for (int c3 = -2; c3 <= 2; c3++) {
                for (int c2 = -1; c2 <= 1; c2++) {
                    cb = world.getBlockState(new BlockPos(x + c1, y + c2, z + c3));
                    if (cb == Blocks.grass.getDefaultState()) {
                        earth = true;
                    }
                    else if (cb == Blocks.water.getDefaultState()) {
                        water = true;
                    }
                }
            }
        }

        if (!(earth && water)) {
            return false;
        }

        int height = 13;
        int leaveheight = 5;
        int branches = 6;
        int branchLenght = 6;

        for (int i = 0; i < height; i++) {
            world.setBlockState(new BlockPos(x, y + i, z), this.logBlock, this.generateFlag);
        }
        createLeavesAroundBranch(world, rand, x, y + height, z, 3, 2);
        createTrunk(world, rand, x, y, z);

        int dir = rand.nextInt((int) (360f / branches));
        int bl;
        float xd, yd, hd, c;
        boolean m;
        for (int b = 0; b < branches; b++) {
            c = 0;
            hd = height - rand.nextFloat() * leaveheight - 2f;
            dir += (int) (360f / branches);
            xd = (float) Math.cos(dir * Math.PI / 180f);
            yd = (float) Math.sin(dir * Math.PI / 180f);
            m = false;

            while (c < branchLenght) {
                if (c > branchLenght / 2 && !m) {
                    m = true;
                    createLeavesAroundBranch(world, rand, x + (int) (c * xd), y + (int) hd, z + (int) (c * yd), 2, 1);
                }
                c++;
                hd += 0.5f;

                //TODO: this.logMeta + 12
                world.setBlockState(new BlockPos(x + (int) (c * xd), y + (int) hd, z + (int) (c * yd)), this.logBlock, this.generateFlag);
            }
            createLeavesAroundBranch(world, rand, x + (int) (c * xd), y + (int) hd, z + (int) (c * yd), 2, 1);
        }

        return true;
    }

    private void createLeavesAroundBranch(World world, Random rand, int x, int y, int z, int s, int c) {

        int l;
        int t = (int) Math.pow(s, 2);
        for (int i = -s; i <= s; i++) {
            for (int j = -s; j <= s; j++) {
                for (int k = -s; k <= s; k++) {
                    l = i * i + j * j + k * k;
                    if (l <= t) {
                        if (world.isAirBlock(new BlockPos(x + i, y + j, z + k)) && (l < t - c || rand.nextBoolean())) {
                            if (!this.noLeaves) {

                                world.setBlockState(new BlockPos(x + i, y + j, z + k), this.leavesBlock, this.generateFlag);
                                if (j < -(s - 2) && rand.nextInt(3) != 0) {
                                    createVine(world, rand, x + i, y + j, z + k);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void createVine(World world, Random rand, int x, int y, int z) {

        int r = rand.nextInt(3) + 5;
        for (int i = -1; i > -r; i--) {
            if (!world.isAirBlock(new BlockPos(x, y + i, z))) {
                break;
            }
            world.setBlockState(new BlockPos(x, y + i, z), this.leavesBlock, this.generateFlag);
        }
    }

    private void createTrunk(World world, Random rand, int x, int y, int z) {

        int[] pos = new int[]{0, 0, 1, 0, 0, 1, -1, 0, 0, -1};
        int sh;
        for (int t = 0; t < 5; t++) {
            sh = rand.nextInt(3) + y;
            while (sh > y - 3) {
                //TODO: this.logMeta + 12 (meta)
                world.setBlockState(new BlockPos(x + pos[t * 2], sh, z + pos[t * 2 + 1]), this.logBlock, this.generateFlag);
                sh--;
            }
        }
    }
}
