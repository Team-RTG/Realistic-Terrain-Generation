package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Cupressus Sempervirens (Italian Cypress)
 */
public class TreeRTGCupressusSempervirens extends TreeRTG {

    /**
     * <b>Cupressus Sempervirens (Italian Cypress)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGCupressusSempervirens());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)1;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)1;<br>
     * decoTree.setMinTrunkSize(3);<br>
     * decoTree.setMaxTrunkSize(6);<br>
     * decoTree.setMinCrownSize(5);<br>
     * decoTree.setMaxCrownSize(10);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGCupressusSempervirens() {

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

        int i, j, k;
        for (i = 0; i < this.trunkSize; i++) {
            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag);
            y++;
        }

        int small = (int) Math.ceil((double) (this.crownSize / 2));
        int large = small;

        for (i = 0; i < large; i++) {
            if (!this.noLeaves) {

                for (j = -2; j <= 2; j++) {
                    for (k = -2; k <= 2; k++) {
                        if (Math.abs(j) + Math.abs(k) != 4 && ((j > -2 && k > -2 && j < 2 && k < 2) || rand.nextInt(4) != 0)) {
                            this.placeLeavesBlock(world, new BlockPos(x + j, y, z + k), this.leavesBlock, this.generateFlag);
                        }
                    }
                }
            }
            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag);
            y++;
        }

        for (i = 0; i < small; i++) {
            if (!this.noLeaves) {

                for (j = -1; j <= 1; j++) {
                    for (k = -1; k <= 1; k++) {
                        if (Math.abs(j) + Math.abs(k) < 2 || (rand.nextInt(4) != 0)) {
                            this.placeLeavesBlock(world, new BlockPos(x + j, y, z + k), this.leavesBlock, this.generateFlag);
                        }
                    }
                }

                if (i == 0) {
                    this.placeLeavesBlock(world, new BlockPos(x + 1, y, z), this.leavesBlock, this.generateFlag);
                    this.placeLeavesBlock(world, new BlockPos(x - 1, y, z), this.leavesBlock, this.generateFlag);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z + 1), this.leavesBlock, this.generateFlag);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z - 1), this.leavesBlock, this.generateFlag);
                    this.placeLeavesBlock(world, new BlockPos(x + 2, y, z), this.leavesBlock, this.generateFlag);
                    this.placeLeavesBlock(world, new BlockPos(x - 2, y, z), this.leavesBlock, this.generateFlag);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z + 2), this.leavesBlock, this.generateFlag);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z - 2), this.leavesBlock, this.generateFlag);
                }
            }

            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag);
            y++;
        }

        this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag);

        if (!this.noLeaves) {
            this.placeLeavesBlock(world, new BlockPos(x + 1, y, z), this.leavesBlock, this.generateFlag);
            this.placeLeavesBlock(world, new BlockPos(x - 1, y, z), this.leavesBlock, this.generateFlag);
            this.placeLeavesBlock(world, new BlockPos(x, y, z + 1), this.leavesBlock, this.generateFlag);
            this.placeLeavesBlock(world, new BlockPos(x, y, z - 1), this.leavesBlock, this.generateFlag);
            this.placeLeavesBlock(world, new BlockPos(x, y + 1, z), this.leavesBlock, this.generateFlag);
            this.placeLeavesBlock(world, new BlockPos(x, y + 2, z), this.leavesBlock, this.generateFlag);
        }

        return true;
    }
}
