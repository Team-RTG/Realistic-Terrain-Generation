package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import rtg.api.util.BlockUtil;

/**
 * Betula Papyrifera (Paper Birch)
 */
public class TreeRTGBetulaPapyrifera extends TreeRTG {

    /**
     * <b>Betula Papyrifera (Paper Birch)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGBetulaPapyrifera());<br>
     * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
     * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
     * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
     * decoTree.treeConditionNoise = 0f;<br>
     * decoTree.treeConditionChance = 4;<br>
     * decoTree.logBlock = Blocks.LOG;<br>
     * decoTree.logMeta = (byte)2;<br>
     * decoTree.leavesBlock = Blocks.LEAVES;<br>
     * decoTree.leavesMeta = (byte)2;<br>
     * decoTree.minTrunkSize = 6;<br>
     * decoTree.maxTrunkSize = 8;<br>
     * decoTree.minCrownSize = 8;<br>
     * decoTree.maxCrownSize = 24;<br>
     * decoTree.noLeaves = false;<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGBetulaPapyrifera() {

        super();

        this.logBlock = BlockUtil.getStateLog(2);
        this.leavesBlock = BlockUtil.getStateLeaf(2);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (!this.isGroundValid(world, pos)) {
            return false;
        }

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int i;
        for (i = 0; i < this.trunkSize; i++) {
            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag);
            y++;
        }

        int pX = 0;
        int pZ = 0;
        for (i = 0; i < this.crownSize; i++) {
            if (rand.nextInt(2) == 0 && i < this.crownSize - 2) {
                int dX = -1 + rand.nextInt(3);
                int dZ = -1 + rand.nextInt(3);

                if (dX == 0 && dZ == 0) {
                    dX = -1 + rand.nextInt(3);
                    dZ = -1 + rand.nextInt(3);
                }

                if (pX == dX && rand.nextBoolean()) {
                    dX = -dX;
                }
                if (pZ == dZ && rand.nextBoolean()) {
                    dZ = -dZ;
                }

                pX = dX;
                pZ = dZ;

                buildBranch(world, rand, x, y, z, dX, dZ, 1, i < this.crownSize - 2 ? 2 : 1); //i < treeSize - 4 ? 2 : 1
            }
            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag);

            if (i < this.crownSize - 2) {
                if (rand.nextBoolean()) {
                    buildLeaves(world, x, y, z + 1);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x, y, z - 1);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x + 1, y, z);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x - 1, y, z);
                }
            }

            y++;
        }

        buildLeaves(world, x, y - 1, z + 1);
        buildLeaves(world, x, y - 1, z - 1);
        buildLeaves(world, x + 1, y - 1, z);
        buildLeaves(world, x - 1, y - 1, z);
        buildLeaves(world, x, y, z);

        return true;
    }

    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize) {

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

    public void buildLeaves(World world, int x, int y, int z) {

        if (!this.noLeaves) {

            this.placeLeavesBlock(world, new BlockPos(x, y, z), this.leavesBlock, this.generateFlag);
        }
    }
}
