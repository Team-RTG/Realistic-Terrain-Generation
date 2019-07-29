package rtg.api.world.gen.feature.tree.rtg;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rtg.api.util.RTGTreeData;

import java.util.Random;


/**
 * Rhizophora Mucronata (Asiatic Mangrove)
 */
public class TreeRTGRhizophoraMucronata extends TreeRTG {

    protected int minBranches;
    protected int maxBranches;
    protected float branchLength;
    protected float verStart;
    protected float verRand;
    protected IBlockState trunkLog;

    /**
     * <b>Rhizophora Mucronata (Asiatic Mangrove)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br>
     * minBranches, maxBranches, branchLength, verStart, verRand<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f));<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)3;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)3;<br>
     * decoTree.setMinTrunkSize(3);<br>
     * decoTree.setMaxTrunkSize(4);<br>
     * decoTree.setMinCrownSize(10);<br>
     * decoTree.setMaxCrownSize(27);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGRhizophoraMucronata(int minBranches, int maxBranches, float branchLength, float verStart, float verRand) {

        this();

        this.minBranches = minBranches;
        this.maxBranches = maxBranches;
        this.branchLength = branchLength;
        this.verStart = verStart;
        this.verRand = verRand;
    }

    public TreeRTGRhizophoraMucronata() {

        super();

        this.minBranches = 3;
        this.maxBranches = 4;
        this.branchLength = 13f;
        this.verStart = 0.32f;
        this.verRand = 0.1f;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (!this.isGroundValid(world, pos)) {
            return false;
        }

        RTGTreeData treeData = new RTGTreeData();

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        this.trunkLog = this.getTrunkLog(this.logBlock);

        int branch = this.minBranches + rand.nextInt(this.maxBranches - this.minBranches + 1);

        if (this.trunkSize > 0) {
            for (int k = 0; k < 3; k++) {
                generateBranch(world, rand, x, y + this.trunkSize, z, (120 * k) - 40 + rand.nextInt(80), 1.6f + rand.nextFloat() * 0.1f, this.trunkSize * 2f, 1f, true, treeData);
            }
        }

        for (int i = y + this.trunkSize; i < y + this.crownSize; i++) {
            this.placeLogBlock(world, new BlockPos(x, i, z), this.logBlock, this.generateFlag, treeData);
        }

        float horDir, verDir;
        int eX, eY, eZ;
        for (int j = 0; j < branch; j++) {
            horDir = (120 * j) - 60 + rand.nextInt(120);
            verDir = verStart + rand.nextFloat() * verRand;
            generateBranch(world, rand, x, y + this.crownSize, z, horDir, verDir, branchLength, 1f, false, treeData);

            eX = x + (int) (Math.cos(horDir * Math.PI / 180D) * verDir * branchLength);
            eZ = z + (int) (Math.sin(horDir * Math.PI / 180D) * verDir * branchLength);
            eY = y + this.crownSize + (int) ((1f - verDir) * branchLength);

            for (int m = 0; m < 1; m++) {
                generateLeaves(world, rand, eX, eY, eZ, 4f, 1.2f, treeData);
            }
        }

        //treeData.dumpTreeData();

        return true;
    }

    /*
     * horDir = number between -180D and 180D
     * verDir = number between 1F (horizontal) and 0F (vertical)
     */
    public void generateBranch(World world, Random rand, float x, float y, float z, double horDir, float verDir, float length, float speed, boolean isTrunk, RTGTreeData treeData) {

        if (verDir < 0f) {
            verDir = -verDir;
        }

        float c = 0f;
        float velY = 1f - verDir;

        if (verDir > 1f) {
            verDir = 1f - (verDir - 1f);
        }

        float velX = (float) Math.cos(horDir * Math.PI / 180D) * verDir;
        float velZ = (float) Math.sin(horDir * Math.PI / 180D) * verDir;

        while (c < length) {

            if (isTrunk) {
                this.placeLogBlock(world, new BlockPos((int) x, (int) y, (int) z), this.trunkLog, this.generateFlag, treeData);
            }
            else {
                this.placeLogBlock(world, new BlockPos((int) x, (int) y, (int) z), this.logBlock, this.generateFlag, treeData);
            }

            x += velX;
            y += velY;
            z += velZ;

            c += speed;
        }
    }

    public void generateLeaves(World world, Random rand, int x, int y, int z, float size, float width, RTGTreeData treeData) {

        float dist;
        int i, j, k, s = (int) (size - 1f), w = (int) ((size - 1f) * width);
        for (i = -w; i <= w; i++) {
            for (j = -s; j <= s; j++) {
                for (k = -w; k <= w; k++) {
                    dist = Math.abs((float) i / width) + (float) Math.abs(j) + Math.abs((float) k / width);
                    if (dist <= size - 0.5f || (dist <= size && rand.nextBoolean())) {
                        if (dist < 0.6f) {
                            this.placeLogBlock(world, new BlockPos(x + i, y + j, z + k), this.logBlock, this.generateFlag, treeData);
                        }

                        if (!this.noLeaves) {
                            this.placeLeavesBlock(world, new BlockPos(x + i, y + j, z + k), this.leavesBlock, this.generateFlag, treeData);
                        }
                    }
                }
            }
        }
    }
}
