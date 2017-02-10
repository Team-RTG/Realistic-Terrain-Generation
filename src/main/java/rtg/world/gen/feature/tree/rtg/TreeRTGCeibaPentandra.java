package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Ceiba Pentandra (White Silk-Cotton Tree)
 */
public class TreeRTGCeibaPentandra extends TreeRTG {

    protected float length;
    protected int branch;
    protected float verStart;
    protected float verRand;
    protected IBlockState trunkLog;

    /**
     * <b>Ceiba Pentandra (White Silk-Cotton Tree)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br>
     * length, branch, verStart, verRand<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f));<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)0;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)0;<br>
     * decoTree.setMinTrunkSize(3);<br>
     * decoTree.setMaxTrunkSize(4);<br>
     * decoTree.setMinCrownSize(10);<br>
     * decoTree.setMaxCrownSize(24);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGCeibaPentandra() {

        super();

        this.length = 13f;
        this.branch = 3;
        this.verStart = 0.32f;
        this.verRand = 0.1f;
    }

    public TreeRTGCeibaPentandra(float length, int branch, float verStart, float verRand) {

        this();

        this.length = length;
        this.branch = branch;
        this.verStart = verStart;
        this.verRand = verRand;
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

        if (this.trunkSize > 0) {
            for (int k = 0; k < 3; k++) {
                generateBranch(world, rand, x, y + this.trunkSize, z, (120 * k) - 40 + rand.nextInt(80), 1.6f + rand.nextFloat() * 0.1f, this.trunkSize * 1.7f, 1f, true);
            }
        }

        for (int i = y + this.trunkSize; i < y + this.crownSize; i++) {
            this.placeLogBlock(world, new BlockPos(x, i, z), this.logBlock, this.generateFlag);
        }

        float horDir, verDir;
        int eX, eY, eZ;
        for (int j = 0; j < branch; j++) {
            horDir = (120 * j) - 60 + rand.nextInt(120);
            verDir = verStart + rand.nextFloat() * verRand;
            generateBranch(world, rand, x, y + this.crownSize, z, horDir, verDir, length, 1f, false);

            eX = x + (int) (Math.cos(horDir * Math.PI / 180D) * verDir * length);
            eZ = z + (int) (Math.sin(horDir * Math.PI / 180D) * verDir * length);
            eY = y + this.crownSize + (int) ((1f - verDir) * length);

            for (int m = 0; m < 1; m++) {
                generateLeaves(world, rand, eX, eY, eZ, 4f, 1.5f);
            }
        }

        return true;
    }

    /*
     * horDir = number between -180D and 180D
     * verDir = number between 1F (horizontal) and 0F (vertical)
     */
    public void generateBranch(World world, Random rand, float x, float y, float z, double horDir, float verDir, float length, float speed, boolean isTrunk) {

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
                this.placeLogBlock(world, new BlockPos((int) x, (int) y, (int) z), this.trunkLog, this.generateFlag);
            }
            else {
                this.placeLogBlock(world, new BlockPos((int) x, (int) y, (int) z), this.logBlock, this.generateFlag);
            }

            x += velX;
            y += velY;
            z += velZ;

            c += speed;
        }
    }

    public void generateLeaves(World world, Random rand, int x, int y, int z, float size, float width) {

        float dist;
        int i, j, k, s = (int) (size - 1f), w = (int) ((size - 1f) * width);
        for (i = -w; i <= w; i++) {
            for (j = -s; j <= s; j++) {
                for (k = -w; k <= w; k++) {
                    dist = Math.abs((float) i / width) + (float) Math.abs(j) + Math.abs((float) k / width);
                    if (dist <= size - 0.5f || (dist <= size && rand.nextBoolean())) {
                        if (dist < 0.6f) {
                            this.placeLogBlock(world, new BlockPos(x + i, y + j, z + k), this.logBlock, this.generateFlag);
                        }

                        if (!this.noLeaves) {

                            this.placeLeavesBlock(world, new BlockPos(x + i, y + j, z + k), this.leavesBlock, this.generateFlag);
                        }
                    }
                }
            }
        }
    }

    public TreeRTGCeibaPentandra setLength(float length) {

        this.length = length;
        return this;
    }

    public TreeRTGCeibaPentandra setBranch(int branch) {

        this.branch = branch;
        return this;
    }

    public TreeRTGCeibaPentandra setVerStart(float verStart) {

        this.verStart = verStart;
        return this;
    }

    public TreeRTGCeibaPentandra setVerRand(float verRand) {

        this.verRand = verRand;
        return this;
    }
}
