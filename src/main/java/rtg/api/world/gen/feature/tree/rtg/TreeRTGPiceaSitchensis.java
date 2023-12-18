package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rtg.api.util.BlockUtil;


/**
 * Picea Sitchensis (Sitka Spruce)
 */
public class TreeRTGPiceaSitchensis extends TreeRTG {

    /**
     * <b>Picea Sitchensis (Sitka Spruce)</b><br><br>
     * <u>Relevant variables:</u><br>
     * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, crownSize, noLeaves<br><br>
     * <u>DecoTree example:</u><br>
     * DecoTree decoTree = new DecoTree(new TreeRTGPiceaSitchensis());<br>
     * decoTree.setTreeType(DecoTree.TreeType.RTG_TREE);<br>
     * decoTree.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);<br>
     * decoTree.setDistribution(new DecoTree.Distribution(100f, 6f, 0.8f));<br>
     * decoTree.setTreeConditionNoise(0f);<br>
     * decoTree.setTreeConditionChance(4);<br>
     * decoTree.setLogBlock(Blocks.LOG);<br>
     * decoTree.logMeta = (byte)1;<br>
     * decoTree.setLeavesBlock(Blocks.LEAVES);<br>
     * decoTree.leavesMeta = (byte)1;<br>
     * decoTree.setMinTrunkSize(4);<br>
     * decoTree.setMaxTrunkSize(9);<br>
     * decoTree.setMinCrownSize(5);<br>
     * decoTree.setMaxCrownSize(14);<br>
     * decoTree.setNoLeaves(false);<br>
     * this.addDeco(decoTree);
     */
    public TreeRTGPiceaSitchensis() {

        super();

        this.setLogBlock(BlockUtil.getStateLog(EnumType.SPRUCE));
        this.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.SPRUCE));
        this.setBranchBlock(new TreeMaterials.Picker().spruce.branches);
        this.trunkSize = 8;
        this.crownSize = 10;
        this.setNoLeaves(false);
    }
	public float estimatedSize() {

    	float branchLength= (crownSize/4) + 1;
    	return branchLength*branchLength/30f;
	}
	
	@Override
    public int furthestLikelyExtension() {
    	float branchLength= (crownSize/4) + 1;
    	float extension = 2f;
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

        SkylightTracker lightTracker = new SkylightTracker(this.furthestLikelyExtension(),pos,world);
        
        int i;
        for (i = 0; i < this.trunkSize; i++) {
            this.placeTrunkBlock(world, new BlockPos(x, y, z), this.generateFlag, lightTracker);
            y++;
        }

        int pX = 0;
        int pZ = 0;
        for (i = 0; i < this.crownSize; i++) {
            if (rand.nextInt(2) <= 0 && i < this.crownSize - 2) {
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
                
                int leafSize = i < this.crownSize - 3 ? 2 : 1;//(crownSize - i)/4 + 1
                
                int branchSize = (crownSize +2 - i)/4 ;//i < this.crownSize - 10 ? 2 : 1,
                branchSize = (crownSize - i -3)/3;
                if (branchSize<0) branchSize = 0;

                buildBranch(world, rand, x, y, z, dX, dZ, branchSize, leafSize, lightTracker);
                // other two
                
                dX = -dX;
                dZ = -dZ;
                if ((dX ==0)||(dZ==0)) {
                	if (dX==0) {

                        buildBranch(world, rand, x, y, z, 1, dZ, branchSize, leafSize, lightTracker);
                        buildBranch(world, rand, x, y, z, -1, dZ, branchSize, leafSize, lightTracker);
                	} else {

                        buildBranch(world, rand, x, y, z, dX, -1, branchSize, leafSize, lightTracker);
                        buildBranch(world, rand, x, y, z, dX, 1, branchSize, leafSize, lightTracker);
                	}
                	
                } else {
                    buildBranch(world, rand, x, y, z, 0, dZ, branchSize, leafSize, lightTracker);
                    buildBranch(world, rand, x, y, z, dX, 0, branchSize, leafSize, lightTracker);
                }
                
                i++;// space branches
            }
            this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag, lightTracker);

            if (i < this.crownSize - 2) {
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

    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize, SkylightTracker lightTracker) {

        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                for (int k = 0; k < 1; k++) {
                	int ragged = 0;
                	if (rand.nextInt(4)==0) ragged = 1;
                    if (Math.abs(i) + Math.abs(j) + Math.abs(k) + ragged < leaveSize + 1) {
                        buildLeaves(world, x + i + (dX * logLength), y + k, z + j + (dZ * logLength),lightTracker);
                    }
                }
            }
        }

        // one on top
        
        buildLeaves(world, x + (dX * logLength), y + 1, z + (dZ * logLength),lightTracker);
        
        for (int m = 1; m <= logLength; m++) {
            this.placeLogBlock(world, new BlockPos(x + (dX * m), y, z + (dZ * m)), this.branchBlock, generateFlag, lightTracker);
            if (m>1) {
            	// extra leaves
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        for (int k = 0; k < 1; k++) {
                        	int ragged = 0;
                        	if (rand.nextInt(4)==0) ragged = 1;
                            if (Math.abs(i) + Math.abs(j) + Math.abs(k) + ragged < leaveSize + 1) {
                                buildLeaves(world, x + i + (dX * m), y + k, z + j + (dZ * m), lightTracker);
                            }
                        }
                    }
                }
            }
        }
    }

    public void buildLeaves(World world, int x, int y, int z, SkylightTracker lightTracker) {

        if (!this.noLeaves) {

            this.placeLeavesBlock(world, new BlockPos(x, y, z), this.leavesBlock, this.generateFlag, lightTracker);
        }
    }
}
