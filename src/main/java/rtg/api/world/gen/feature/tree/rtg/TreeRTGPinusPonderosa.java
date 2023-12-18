package rtg.api.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


/**
 * Pinus Ponderosa (Ponderosa Pine)
 */
public class TreeRTGPinusPonderosa extends TreeRTG {

    protected IBlockState trunkLog;

    /**
     * <b>Pinus Ponderosa (Ponderosa Pine)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGPinusPonderosa());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)0;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)0;<br>
     * decoTree.setMinTrunkSize(11);<br>
     * decoTree.setMaxTrunkSize(21);<br>
     * decoTree.setMinCrownSize(15);<br>
     * decoTree.setMaxCrownSize(29);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGPinusPonderosa() {

        super();
    }
    
    public float estimatedSize() {
    	//return estimated size of current tree in area. 1f is roughly an 8 diameter circle.
    	float branchLength = crownSize/6 +1;
    	return branchLength*branchLength/30f;
    }
    
    public int furthestLikelyExtension() { 
    	return 9;
    }
    
    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        this.trunkLog = this.getTrunkLog(this.logBlock);

        if (!this.isGroundValid(world, new ArrayList<BlockPos>(Arrays.asList(
            new BlockPos(x + 1, y, z),
            new BlockPos(x - 1, y, z),
            new BlockPos(x, y, z + 1),
            new BlockPos(x, y, z - 1)
        )))) {
            return false;
        }

        SkylightTracker lightTracker = new SkylightTracker(this.furthestLikelyExtension(),pos,world);

        int i;
        for (i = 0; i < trunkSize; i++) {
            if (!this.placeTrunkBlock(world, new BlockPos(x, y, z), this.generateFlag, lightTracker)) return false;
            if (i > 5 && rand.nextInt(7) == 0) {
                int dX = -1 + rand.nextInt(3);
                int dZ = -1 + rand.nextInt(3);

                if (dX == 0 && dZ == 0) {
                    dX = -1 + rand.nextInt(3);
                    dZ = -1 + rand.nextInt(3);
                }                
                buildBranch(world, rand, x, y, z, dX, dZ, 1, 1, lightTracker);
            }

            y++;
        }
        
        y = pos.getY();
        
        buildTrunk(world, rand, x + 1, y, z, lightTracker);
        buildTrunk(world, rand, x - 1, y, z, lightTracker);
        buildTrunk(world, rand, x, y, z + 1, lightTracker);
        buildTrunk(world, rand, x, y, z - 1, lightTracker);
        
        y+= trunkSize;
        
        int pX = 0;
        int pZ = 0;
        int j;
        for (i = 0; i < crownSize; i++) {
            if (rand.nextInt(i < crownSize - 12 && i > 2 ? 3 : 2) <= 1 && i < crownSize - 2) {
                int dX = -1 + rand.nextInt(3);
                int dZ = -1 + rand.nextInt(3);

                if (dX == 0 && dZ == 0) {
                    dX = -1 + rand.nextInt(3);
                    dZ = -1 + rand.nextInt(3);
                }

                if (pX != 0 && pX == dX && rand.nextInt(3)<2) {
                    dX = -dX;
                }
                if (pZ != 0 && pZ == dZ && rand.nextInt(3)<2) {
                    dZ = -dZ;
                }

                pX = dX;
                pZ = dZ;

                int logSize = i < crownSize - 12 && i > 3 ? 3 : i < crownSize - 8 ? 2 : 1;
                
                int leafSize = i < crownSize - 4 ? 2 : 1;
                //leafSize = (crownSize - i)/5 + 1;
                
                buildBranch(world, rand, x, y, z, dX, dZ,
                    logSize,
                    leafSize,
                     lightTracker
                );
            }

            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag, lightTracker);

            if (i < crownSize - 2) {
                if (rand.nextBoolean()) {
                    buildLeaves(world, x, y, z + 1, lightTracker);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x, y, z - 1, lightTracker);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x + 1, y, z, lightTracker);
                }
                if (rand.nextBoolean()) {
                    buildLeaves(world, x - 1, y, z, lightTracker);
                }
            }

            y++;
        }

        buildLeaves(world, x, y - 1, z + 1, lightTracker);
        buildLeaves(world, x, y - 1, z - 1, lightTracker);
        buildLeaves(world, x + 1, y - 1, z, lightTracker);
        buildLeaves(world, x - 1, y - 1, z, lightTracker);
        buildLeaves(world, x, y, z, lightTracker);

        lightTracker.checkLighting(world);
        return true;
    }

    @Override
    public void buildTrunk(World world, Random rand, int x, int y, int z, SkylightTracker lightTracker) {

        int h = (int) Math.ceil(this.trunkSize / 4f);
        h = h + rand.nextInt(h * 2);
        for (int i = -1; i < h; i++) {
            this.placeLogBlock(world, new BlockPos(x, y + i, z), this.trunkLog, this.generateFlag, lightTracker);
        }
    }

    @Override
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize, SkylightTracker lightTracker) {

        if (logLength == 3 && Math.abs(dX) + Math.abs(dZ) == 2) {
            //logLength--;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = 0; k < 1; k++) {
                    if (Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1) {
                        buildLeaves(world, x + i + (dX * logLength), y + k, z + j + (dZ * logLength), lightTracker);
                    }
                }
            }
        }

        buildLeaves(world, x + (dX * logLength), y + 1, z + (dZ * logLength), lightTracker);
        
        for (int m = 1; m <= logLength; m++) {
        	if (m < logLength) {
                 this.placeLogBlock(world, new BlockPos(x + (dX * m), y, z + (dZ * m)), this.logBlock, this.generateFlag, lightTracker);
        	} else {

                this.placeLeavesBlock(world, new BlockPos(x + (dX * m), y, z + (dZ * m)), this.logBlock, this.generateFlag, lightTracker);
        	}
            if (m>1) {
            	// extra leaves
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        for (int k = 0; k < 1; k++) {
                            if (Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1) {
                                buildLeaves(world, x + i + (dX * m), y + k, z + j + (dZ * m), lightTracker);
                            }
                        }
                    }
                }
                buildLeaves(world, x + (dX * m), y + 1, z + (dZ * m), lightTracker);
            }
        }
    }

    @Override
    public void buildLeaves(World world, int x, int y, int z, SkylightTracker lightTracker) {

        if (!this.noLeaves) {
            this.placeLeavesBlock(world, new BlockPos(x, y, z), this.leavesBlock, this.generateFlag, lightTracker);
        }
    }
}
