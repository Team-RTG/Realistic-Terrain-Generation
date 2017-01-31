package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import rtg.config.rtg.ConfigRTG;


/**
 * Rhizophora Mucronata (Asiatic Mangrove)
 */
public class TreeRTGRhizophoraMucronata extends TreeRTG {

    protected int minBranches;
    protected int maxBranches;
    protected float branchLength;
    protected float verStart;
    protected float verRand;

    /**
     * <b>Rhizophora Mucronata (Asiatic Mangrove)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br>
     * minBranches, maxBranches, branchLength, verStart, verRand<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f));<br>
     * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
     * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
     * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
     * decoTree.treeConditionNoise = 0f;<br>
     * decoTree.treeConditionChance = 4;<br>
     * decoTree.logBlock = Blocks.log;<br>
     * decoTree.logMeta = (byte)3;<br>
     * decoTree.leavesBlock = Blocks.leaves;<br>
     * decoTree.leavesMeta = (byte)3;<br>
     * decoTree.minTrunkSize = 3;<br>
     * decoTree.maxTrunkSize = 4;<br>
     * decoTree.minCrownSize = 10;<br>
     * decoTree.maxCrownSize = 27;<br>
     * decoTree.noLeaves = false;<br>
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

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        IBlockState b = world.getBlockState(new BlockPos(x, y - 1, z));

        if (b == Blocks.sand.getDefaultState() && !ConfigRTG.allowTreesToGenerateOnSand) {
            return false;
        }

        if (b != Blocks.grass.getDefaultState() && b != Blocks.dirt.getDefaultState() && b != Blocks.sand.getDefaultState()) {
            if (!(b == Blocks.water.getDefaultState() && world.getBlockState(new BlockPos(x, y - 2, z)) == Blocks.sand.getDefaultState() && world.getBlockState(new BlockPos(x, y, z)) == Blocks.air.getDefaultState())) {
                return false;
            }
        }

        int branch = this.minBranches + rand.nextInt(this.maxBranches - this.minBranches + 1);

        if (this.trunkSize > 0) {
            for (int k = 0; k < 3; k++) {
                generateBranch(world, rand, x, y + this.trunkSize, z, (120 * k) - 40 + rand.nextInt(80), 1.6f + rand.nextFloat() * 0.1f, this.trunkSize * 2f, 1f);
            }
        }

        for (int i = y + this.trunkSize; i < y + this.crownSize; i++) {
            world.setBlockState(new BlockPos(x, i, z), this.logBlock, this.generateFlag);
        }

        float horDir, verDir;
        int eX, eY, eZ;
        for (int j = 0; j < branch; j++) {
            horDir = (120 * j) - 60 + rand.nextInt(120);
            verDir = verStart + rand.nextFloat() * verRand;
            generateBranch(world, rand, x, y + this.crownSize, z, horDir, verDir, branchLength, 1f);

            eX = x + (int) (Math.cos(horDir * Math.PI / 180D) * verDir * branchLength);
            eZ = z + (int) (Math.sin(horDir * Math.PI / 180D) * verDir * branchLength);
            eY = y + this.crownSize + (int) ((1f - verDir) * branchLength);

            for (int m = 0; m < 1; m++) {
                generateLeaves(world, rand, eX, eY, eZ, 4f, 1.2f);
            }
        }

        return true;
    }

    /*
     * horDir = number between -180D and 180D
     * verDir = number between 1F (horizontal) and 0F (vertical)
     */
    public void generateBranch(World world, Random rand, float x, float y, float z, double horDir, float verDir, float length, float speed) {

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
            world.setBlockState(new BlockPos((int) x, (int) y, (int) z), this.logBlock, this.generateFlag);

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
                            world.setBlockState(new BlockPos(x + i, y + j, z + k), this.logBlock, this.generateFlag);
                        }

                        if (!this.noLeaves) {

                            if (world.isAirBlock(new BlockPos(x + i, y + j, z + k))) {
                                world.setBlockState(new BlockPos(x + i, y + j, z + k), this.leavesBlock, this.generateFlag);
                            }
                        }
                    }
                }
            }
        }
    }
}
