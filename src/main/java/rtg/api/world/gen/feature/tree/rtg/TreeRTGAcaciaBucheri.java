package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Acacia Bucheri (Bucher Acacia)
 */
public class TreeRTGAcaciaBucheri extends TreeRTG {

    /**
     * <b>Acacia Bucheri (Bucher Acacia)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, <s>crownSize</s>, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGAcaciaBucheri());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG2);<br>
     * decoTree.logMeta = (byte)0;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES2);<br>
     * decoTree.leavesMeta = (byte)0;<br>
     * decoTree.setMinTrunkSize(6);<br>
     * decoTree.setMaxTrunkSize(16);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGAcaciaBucheri() {

        super();

        this.setLogBlock(Blocks.LOG2.getDefaultState());
        this.setLeavesBlock(Blocks.LEAVES2.getDefaultState());
        this.trunkSize = 10;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (!this.isGroundValid(world, pos)) {
            return false;
        }

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int h = this.trunkSize;
        int bh = h - 3;

        for (int i = 0; i < h; i++) {
            this.placeLogBlock(world, new BlockPos(x, y + i, z), this.logBlock, this.generateFlag);
        }
        genLeaves(world, rand, x, y + h, z);

        int sh, eh, dir;
        float xd, yd, c;

        for (int a = 1 + rand.nextInt(2); a > -1; a--) {
            sh = bh + rand.nextInt(2);
            eh = h - (int) ((h - sh) * 0.25f);
            dir = rand.nextInt(360);
            xd = (float) Math.cos(dir * Math.PI / 180f) * 2f;
            yd = (float) Math.sin(dir * Math.PI / 180f) * 2f;
            c = 1;

            while (sh < h) {
                this.placeLogBlock(world, new BlockPos(x + (int) (xd * c), y + sh, z + (int) (yd * c)), this.logBlock, this.generateFlag);
                sh++;
                c += 0.5f;
            }
            genLeaves(world, rand, x + (int) (xd * c), y + sh, z + (int) (yd * c));
        }

        return true;
    }

    public void genLeaves(World world, Random rand, int x, int y, int z) {

        if (!this.noLeaves) {

            int i;
            int j;
            for (i = -1; i <= 1; i++) {
                for (j = -1; j <= 1; j++) {
                    this.placeLeavesBlock(world, new BlockPos(x + i, y + 1, z + j), this.leavesBlock, this.generateFlag);
                }
            }

            for (i = -2; i <= 2; i++) {
                for (j = -2; j <= 2; j++) {
                    if (Math.abs(i) + Math.abs(j) < 4) {
                        this.placeLeavesBlock(world, new BlockPos(x + i, y, z + j), this.leavesBlock, this.generateFlag);
                    }
                }
            }
        }

        this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag);
    }
}
