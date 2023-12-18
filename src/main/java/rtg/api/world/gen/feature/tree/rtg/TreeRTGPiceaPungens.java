package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Picea Pungens (Colorado Spruce)
 */
public class TreeRTGPiceaPungens extends TreeRTG {

    /**
     * <b>Picea Pungens (Colorado Spruce)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGPiceaPungens());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)1;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)1;<br>
     * decoTree.setMinTrunkSize(2);<br>
     * decoTree.setMaxTrunkSize(7);<br>
     * decoTree.setMinCrownSize(6);<br>
     * decoTree.setMaxCrownSize(17);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGPiceaPungens() {

        super();
    }
    
	public float estimatedSize() {

    	float branchLength= 3f;
    	return branchLength*branchLength/16f;
	}
	
	@Override
    public int furthestLikelyExtension() {
    	float branchLength= 3f;
    	float extension = 1f;
    	return (int)(extension + branchLength);
	}
	
    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        if (!this.isGroundValid(world, pos)) {
            return false;
        }

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        int small = (int) Math.ceil((double) (this.crownSize / 2));
        int large = small;

        SkylightTracker lightTracker = new SkylightTracker(this.furthestLikelyExtension(),pos,world);
        int i, j, k;
        for (i = 0; i < this.trunkSize; i++) {
            this.placeTrunkBlock(world, new BlockPos(x, y, z), this.generateFlag, lightTracker);
            y++;
        }

        for (i = 0; i < large; i++) {
            if (!this.noLeaves) {

                for (j = -2; j <= 2; j++) {
                    for (k = -2; k <= 2; k++) {
                        if (Math.abs(j) + Math.abs(k) != 4 && ((j > -2 && k > -2 && j < 2 && k < 2) || rand.nextInt(4) != 0)) {
                            this.placeLeavesBlock(world, new BlockPos(x + j, y, z + k), this.leavesBlock, this.generateFlag, lightTracker);
                        }
                    }
                }
            }
            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag, lightTracker);
            y++;
        }

        for (i = 0; i < small; i++) {
            if (!this.noLeaves) {

                for (j = -1; j <= 1; j++) {
                    for (k = -1; k <= 1; k++) {
                        if (Math.abs(j) + Math.abs(k) < 2 || (rand.nextInt(4) != 0)) {
                            this.placeLeavesBlock(world, new BlockPos(x + j, y, z + k), this.leavesBlock, this.generateFlag, lightTracker);
                        }
                    }
                }

                if (i == 0) {
                    this.placeLeavesBlock(world, new BlockPos(x + 1, y, z), this.leavesBlock, this.generateFlag, lightTracker);
                    this.placeLeavesBlock(world, new BlockPos(x - 1, y, z), this.leavesBlock, this.generateFlag, lightTracker);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z + 1), this.leavesBlock, this.generateFlag, lightTracker);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z - 1), this.leavesBlock, this.generateFlag, lightTracker);
                    this.placeLeavesBlock(world, new BlockPos(x + 2, y, z), this.leavesBlock, this.generateFlag, lightTracker);
                    this.placeLeavesBlock(world, new BlockPos(x - 2, y, z), this.leavesBlock, this.generateFlag, lightTracker);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z + 2), this.leavesBlock, this.generateFlag, lightTracker);
                    this.placeLeavesBlock(world, new BlockPos(x, y, z - 2), this.leavesBlock, this.generateFlag, lightTracker);
                }
            }

            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag, lightTracker);
            y++;
        }

        this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag, lightTracker);

        if (!this.noLeaves) {

            this.placeLeavesBlock(world, new BlockPos(x + 1, y, z), this.leavesBlock, this.generateFlag, lightTracker);
            this.placeLeavesBlock(world, new BlockPos(x - 1, y, z), this.leavesBlock, this.generateFlag, lightTracker);
            this.placeLeavesBlock(world, new BlockPos(x, y, z + 1), this.leavesBlock, this.generateFlag, lightTracker);
            this.placeLeavesBlock(world, new BlockPos(x, y, z - 1), this.leavesBlock, this.generateFlag, lightTracker);
            this.placeLeavesBlock(world, new BlockPos(x, y + 1, z), this.leavesBlock, this.generateFlag, lightTracker);
            this.placeLeavesBlock(world, new BlockPos(x, y + 2, z), this.leavesBlock, this.generateFlag, lightTracker);
        }

        return true;
    }

    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize, SkylightTracker lightTracker) {

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = 0; k < 2; k++) {
                    if (Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1) {
                        buildLeaves(world, x + i + (dX * logLength), y + k, z + j + (dZ * logLength),lightTracker);
                    }
                }
            }
        }

        for (int m = 1; m <= logLength; m++) {
            this.placeLogBlock(world, new BlockPos(x + (dX * m), y, z + (dZ * m)), this.logBlock, this.generateFlag,lightTracker);
        }
    }

    public void buildLeaves(World world, int x, int y, int z, SkylightTracker lightTracker) {

        if (!this.noLeaves) {

            this.placeLeavesBlock(world, new BlockPos(x, y, z), this.leavesBlock, this.generateFlag, lightTracker);
        }
    }
}
