package rtg.api.world.gen.feature.tree.rtg;

/**
 * Betula Nigra (River Birch)
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rtg.api.util.Logger;


public class TreeRTGBetulaPendula extends TreeRTG {

    protected IBlockState trunkLog;

    /**
     * <b>Betula Pendula (Silver Birch)</b><br><br>
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

    private float branchLengthening = 0.15f; // increased length of branches with each block down;
    private float shortestBranch  = 1.6f;
    
    private float lowestInterbranch = .3f;// 2/3s of the shortest interbranch distance (there's an additional random multiplier)
    private float interbranchLengthing = .02f; // 2/3s of the lengthening of interbranch for each additional branch;
    private float leafyLogFrequency = .2f;
    public TreeRTGBetulaPendula() {

        super();
        this.crownSize = 20;
        this.trunkSize = 5;
        TreeMaterials.Picker picker  = new TreeMaterials.Picker();
        this.branchBlock = picker.birch.branches;
        this.leavesBlock = picker.birch.leaves;
        this.logBlock = picker.birch.log;
        this.trunkProportionVariability = 0.2f;
        this.lowestVariableTrunkProportion = 0.2f;
        
    }
    
    public TreeRTGBetulaPendula(TreeRTGBetulaPendula model) {

        super(model);
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
        	// if lighting doesn't allow the trunk block, abort
            this.placeTrunkBlock(world, new BlockPos(x, y+i, z),this.generateFlag,lightTracker);
            if (i > 5 && rand.nextInt(7) == 0) {
                int dX = -1 + rand.nextInt(3);
                int dZ = -1 + rand.nextInt(3);

                if (dX == 0 && dZ == 0) {
                    dX = -1 + rand.nextInt(3);
                    dZ = -1 + rand.nextInt(3);
                }

                //buildBranch(world, rand, x, y, z, dX, dZ, 1, 1); no lower branches for now
            }
        }
        
        // make the crown trunk
        for (int crownLocation = 0 ; crownLocation < crownSize;crownLocation++) {
        	if (!this.placeTrunkBlock(world, pos.up(crownLocation+trunkSize), this.generateFlag,lightTracker)) {
        		Logger.info("aborting Tree at {}" , pos);
        		return false;
        	};
        }
        
        buildTrunk(world, rand, x + 1, y, z,lightTracker);
        buildTrunk(world, rand, x - 1, y, z,lightTracker);
        buildTrunk(world, rand, x, y, z + 1,lightTracker);
        buildTrunk(world, rand, x, y, z - 1,lightTracker);
        
        // leaves on top
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize+1), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize).east(), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize).west(), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize).north(), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize).south(), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize+2), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize+1).east(), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize+1).west(), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize+1).north(), this.leavesBlock, this.generateFlag,lightTracker);
        this.placeLeavesBlock(world, pos.up(crownSize+trunkSize+1).south(), this.leavesBlock, this.generateFlag,lightTracker);
        
        

        float height = (float)crownSize-1f;       

        double horizontalDirection = rand.nextFloat()*Math.PI*2.0; // horizontal direction of next branch in radians
        int placedBranches = 0;
        while (height >0) { // top down
        	// goes up more with height plus a random factor;
        	float ascent = 0.5f + height/(float)crownSize*.6f + rand.nextFloat()*0.2f;
        	
        	float branchLengthFromTop= this.shortestBranch + this.branchLengthening*((float)crownSize - height)*1.25f;        	
        	float branchLengthFromBottom= this.shortestBranch + this.branchLengthening*(height)*1.75f;
        	
        	float branchLength = Math.min(branchLengthFromTop, branchLengthFromBottom);
        	
        	BlockPos branchStart = pos.up(trunkSize + (int)height - 1);
        	if (branchLength < 2f) {
            	RTGTreeBranch  branch = new RTGTreeBranch(horizontalDirection,ascent,branchLength,2,branchStart);
        		makeLeafBranch(branch,world,rand,lightTracker);
        	} else if (branchLength <4) {
        		branchLength -= 1.0f;
            	RTGTreeBranch  branch = new RTGTreeBranch(horizontalDirection,ascent,branchLength,2,branchStart);
        	    makeLeafyLogBranch(branch,world,rand,lightTracker);
        	}  else {
        		branchLength -= 1.5f;
        		branchLength *= .8;
            	RTGTreeBranch  branch = new RTGTreeBranch(horizontalDirection,ascent,branchLength,1,branchStart);
        	    makeLeafyLogBranch(branch,world,rand,lightTracker);
        	}
        		
        			
        	// adjust for next branch
        	// rotate about golden mean degrees
        	horizontalDirection += 1.85f + rand.nextFloat();
        	if (horizontalDirection > Math.PI*2.0) horizontalDirection -= Math.PI*2.0;
        	// move the next branch down
        	float branchDrop= this.lowestInterbranch + this.interbranchLengthing*((float)placedBranches);
        	height -= (branchDrop)*(1f + rand.nextFloat());
        	placedBranches ++;
        	
        }


        return true;
    }
	
	public float estimatedSize() {

    	float branchLength= this.shortestBranch + this.branchLengthening*((float)crownSize)/2.5f;
    	return branchLength*branchLength/15f;
	}
	
	@Override
    public int furthestLikelyExtension() {
    	float branchLength= this.shortestBranch + this.branchLengthening*((float)crownSize)/1.5f;
    	int extension = (int)Math.ceil(branchLength);
    	return extension;
	}
    
	public TreeRTG setCrownSize(int size) {
		this.crownSize = size;
		return this;
		
	}
	
	private void makeLeafyLogBranch (RTGTreeBranch branch, World world, Random rand, SkylightTracker lightTracker) {
		RTGTreeBranch leafConnections = new RTGTreeBranch(branch);
		while (branch.notDone()) {
			BlockPos oldLocation = branch.location();
			BlockPos location = branch.moved();
			// abort if blocked by lighting issues
			if (!this.placeLogBlock(world, location, this.branchBlock, this.generateFlag,lightTracker)) {
				return;
			}
            if (oldLocation.getY()!=location.getY()) {//((oldLocation.getX()!=location.getX())&&(oldLocation.getY()!=location.getY())&&(oldLocation.getZ()!=location.getZ())) {
				// add in bridge block for Tinker's Lumber Axe
            	BlockPos bridgeLocation = new BlockPos(location.getX(),oldLocation.getY(),location.getZ());
				this.placeLeavesBlock(world, bridgeLocation, this.leavesBlock, this.generateFlag,lightTracker);            
				if (oldLocation.getX()!=location.getX()) {//((oldLocation.getX()!=location.getX())&&(oldLocation.getY()!=location.getY())&&(oldLocation.getZ()!=location.getZ())) {
					// add in bridge block for Tinker's Lumber Axe
	            	bridgeLocation = new BlockPos(oldLocation.getX(),oldLocation.getY(),location.getZ());
					this.placeLeavesBlock(world, bridgeLocation, this.leavesBlock, this.generateFlag,lightTracker);
					
				}
                if (oldLocation.getZ()!=location.getZ()) {//((oldLocation.getX()!=location.getX())&&(oldLocation.getY()!=location.getY())&&(oldLocation.getZ()!=location.getZ())) {
					// add in bridge block for Tinker's Lumber Axe
	            	bridgeLocation = new BlockPos(location.getX(),oldLocation.getY(),oldLocation.getZ());
					this.placeLeavesBlock(world, bridgeLocation, this.leavesBlock, this.generateFlag,lightTracker);
					
                }
			}
			// top with a leaf block to keep some treefelling mods happy
            this.placeLeavesBlock(world, branch.location().up(), this.leavesBlock, this.generateFlag, lightTracker);
			float branchLeafFrequency = this.leafyLogFrequency * (float) branch.stage;
			if (rand.nextFloat()< branchLeafFrequency) this.placeLeavesBlock(world, location.up(), this.leavesBlock, this.generateFlag,lightTracker);
			if (rand.nextFloat()< branchLeafFrequency) this.placeLeavesBlock(world, location.east(), this.leavesBlock, this.generateFlag,lightTracker);
			if (rand.nextFloat()< branchLeafFrequency) this.placeLeavesBlock(world, location.west(), this.leavesBlock, this.generateFlag,lightTracker);
			if (rand.nextFloat()< branchLeafFrequency) this.placeLeavesBlock(world, location.north(), this.leavesBlock, this.generateFlag,lightTracker);
			if (rand.nextFloat()< branchLeafFrequency) this.placeLeavesBlock(world, location.south(), this.leavesBlock, this.generateFlag,lightTracker);
		}
		while (leafConnections.notDone()) {
		    this.placeLeavesBlock(world, leafConnections.movedOrthogonally(), this.leavesBlock, this.generateFlag,lightTracker);
		}
		
		if (branch.stage == 1) {
			// make and place two successor leafy log branches
			// these are in brackets to prevent misnaming bugs
			{
				double leftDirection = branch.initialHorizontal + 0.5f + rand.nextFloat()*0.3f;
				float leftAscent = branch.initialVertical -0.4f + rand.nextFloat()*0.8f;
				float  leftLength =  branch.initialLength *(.5f + rand.nextFloat()*0.2f);
				RTGTreeBranch leftBranch = new RTGTreeBranch(leftDirection,leftAscent,leftLength,branch.stage+1,branch.location());
				makeLeafyLogBranch(leftBranch,world,rand,lightTracker);
			}
			{
				double rightDirection = branch.initialHorizontal - .8f + rand.nextFloat()*0.3f;
				float rightAscent = branch.initialVertical -0.4f + rand.nextFloat()*0.8f;
				float  rightLength =  branch.initialLength *(.5f + rand.nextFloat()*0.2f);
				RTGTreeBranch rightBranch = new RTGTreeBranch(rightDirection,rightAscent,rightLength,branch.stage+1,branch.location());
				makeLeafyLogBranch(rightBranch,world,rand,lightTracker);
			}

			// make and place downward leaf  branch

			{
				double downDirection = branch.initialHorizontal - .15f + rand.nextFloat()*0.3f;
				float downAscent = -2.4f + rand.nextFloat()*0.8f;
				float  downLength =  1.4f *(.9f + rand.nextFloat()*0.2f);
				RTGTreeBranch rightBranch = new RTGTreeBranch(downDirection,downAscent,downLength,branch.stage+1,branch.location());
				makeLeafBranch(rightBranch,world,rand,lightTracker);
			}
		}
		if (branch.stage == 2) {
			// make and place two successor leaf branches
			// these are in brackets to prevent misnaming bugs
			{
				double leftDirection = branch.initialHorizontal + 0.5f + rand.nextFloat()*0.5f;
				float leftAscent = branch.initialVertical -0.4f + rand.nextFloat()*0.4f;
				float  leftLength = 1.2f *(.9f + rand.nextFloat()*0.2f);
				RTGTreeBranch leftBranch = new RTGTreeBranch(leftDirection,leftAscent,leftLength,branch.stage+1,branch.location());
				makeLeafBranch(leftBranch,world,rand,lightTracker);
			}
			{
				double rightDirection = branch.initialHorizontal - 1f + rand.nextFloat()*0.5f;
				float rightAscent = branch.initialVertical -0.4f + rand.nextFloat()*0.4f;
				float  rightLength =  1.2f *(.9f + rand.nextFloat()*0.2f);
				RTGTreeBranch rightBranch = new RTGTreeBranch(rightDirection,rightAscent,rightLength,branch.stage+1,branch.location());
				makeLeafBranch(rightBranch,world,rand,lightTracker);
			}			// make and place downward leaf  branch

			{
				double downDirection = branch.initialHorizontal - .15f + rand.nextFloat()*0.3f;
				float downAscent = -2.4f + rand.nextFloat()*0.8f;
				float  downLength =  1.4f *(.9f + rand.nextFloat()*0.2f);
				RTGTreeBranch rightBranch = new RTGTreeBranch(downDirection,downAscent,downLength,branch.stage+1,branch.location());
				makeLeafBranch(rightBranch,world,rand,lightTracker);
			}
		}
	}
	
	private void makeLeafBranch (RTGTreeBranch branch, World world, Random rand, SkylightTracker lightTracker) {
		while (branch.notDone()) {
			BlockPos place = branch.movedOrthogonally();
			// abort if blocked by lighting issues
			if (!this.placeLeavesBlock(world, place, this.leavesBlock, this.generateFlag,lightTracker)) return;
			//this.placeLeavesBlock(world, place.up(), this.leavesBlock, this.generateFlag,lightTracker);
			//this.placeLeavesBlock(world, place.east(), this.leavesBlock, this.generateFlag,lightTracker);
			//this.placeLeavesBlock(world, place.west(), this.leavesBlock, this.generateFlag,lightTracker);
			//this.placeLeavesBlock(world, place.north(), this.leavesBlock, this.generateFlag,lightTracker);
			//this.placeLeavesBlock(world, place.south(), this.leavesBlock, this.generateFlag,lightTracker);
			// not down, hoping for a whiff of growing up feel.
		}
	}

    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize, SkylightTracker lightTracker) {

        if (logLength == 3 && Math.abs(dX) + Math.abs(dZ) == 2) {
            logLength--;
        }

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = 0; k < 2; k++) {
                    if (Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1) {
                        buildLeaves(world, x + i + (dX * logLength), y + k, z + j + (dZ * logLength), lightTracker);
                    }
                }
            }
        }

        for (int m = 1; m <= logLength; m++) {
            this.placeLogBlock(world, new BlockPos(x + (dX * m), y, z + (dZ * m)), this.logBlock, this.generateFlag,lightTracker);
        }
    }
    
}