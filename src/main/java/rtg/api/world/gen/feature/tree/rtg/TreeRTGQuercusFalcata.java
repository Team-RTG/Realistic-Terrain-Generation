package rtg.api.world.gen.feature.tree.rtg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * Quercus Falcata (Spanish Oak)
 * Tree designed to have straight branches with blobs of leaves on the ends.
 * 
 */



import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
//import rtg.api.util.Logger;

public class TreeRTGQuercusFalcata extends TreeRTG {
	
	public TreeRTGQuercusFalcata() {
		this.trunkReserve = 2;
	}

    private float interbranch = .4f;// 2/3s of the typical interbranch distance (there's an additional random multiplier)
    private final float additionalBranchLength = 1.5f;// a little extra so the top doesn't stick out
    
	public TreeRTG setCrownSize(int size) {
		this.crownSize = size -2;
		return this;
		
	}
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		
		ArrayList<BlockPos> leafList = new ArrayList<>(30*crownSize);

        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();


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
            this.placeTrunkBlock(world, new BlockPos(x, y+i, z), this.generateFlag, lightTracker);
        }

        buildTrunk(world, rand, x + 1, y, z, lightTracker);
        buildTrunk(world, rand, x - 1, y, z, lightTracker);
        buildTrunk(world, rand, x, y, z + 1, lightTracker);
        buildTrunk(world, rand, x, y, z - 1, lightTracker);
        
        // make the crown trunk
        for (int crownLocation = 0 ; crownLocation < crownSize;crownLocation++) {
            this.placeLogBlock(world, pos.up(crownLocation+trunkSize-1), this.logBlock, this.generateFlag,lightTracker);
        }
        // leaves on top
        this.placeLeavesBlob(world, pos.up(crownSize+trunkSize-1),leafList,lightTracker);
        
        float height = (float)crownSize;       
        double horizontalDirection = rand.nextFloat()*Math.PI*2.0; // horizontal direction of next branch in radians
        int placedBranches = 0;
        while (height >0) { // top down
        	// goes up plus a random factor;
        	float ascent = 1.5f*height/crownSize - 0.2f + rand.nextFloat()*0.4f;
        	
        	float branchLength= (float)(crownSize-height)/4.0f;
        	branchLength += this.additionalBranchLength;
        	branchLength = branchLength - 0.5f + rand.nextFloat();
        	if (branchLength > 2) {
        	    BlockPos branchStart = pos.up(trunkSize + (int)height - 1);
        	    if (branchLength<3) {
        	         RTGTreeBranch  branch = new RTGTreeBranch(horizontalDirection,ascent,branchLength,0,branchStart);
        	         makeLogBranch(branch,world,rand,leafList,lightTracker);
        	    } else {
       	            RTGTreeBranch  branch = new RTGTreeBranch(horizontalDirection,ascent,branchLength*0.7f,0,branchStart);
       	            makeBifurcatingBranch(branch,world,rand,leafList,lightTracker);
        	    }
        	}
        			
        	// adjust for next branch
        	// rotate somewhat less than 120 degrees
        	horizontalDirection += 1.4f + rand.nextFloat();
        	// move the next branch down
        	float branchDrop= interbranch;
        	height -= (branchDrop)*(1f + rand.nextFloat());
        	placedBranches ++;
        	
        }
		return true;
	}
	

	@Override
	public float estimatedSize() {
    	float branchLength= (float)(crownSize)/4.0f +additionalBranchLength + 1f;
    	return branchLength*branchLength/20f;// seemed sparse
	}
	
	@Override
    public int furthestLikelyExtension() {
    	float branchLength= (float)(crownSize)/4.0f +additionalBranchLength + 4.5f;
    	int extension = (int)Math.ceil(branchLength);
    	return extension;
    }
	
    public void buildTrunk(World world, Random rand, int x, int y, int z, SkylightTracker lightTracker) {

        int h = (int) Math.floor(this.trunkSize / 4f);
        h = h - 3 + rand.nextInt(4);
        if (h <= 0) return;
        for (int i = -1; i < h; i++) {
            this.placeTrunkBlock(world, new BlockPos(x, y + i, z), this.generateFlag, lightTracker);
        }
    }
	
	private void makeLogBranch (RTGTreeBranch branch, World world, Random rand,ArrayList<BlockPos> leafList, SkylightTracker lightTracker) {
		while (branch.notDone()) {
			BlockPos oldLocation = branch.location();
			BlockPos location = branch.moved();
			if (!placeLogBlock(world, location, this.branchBlock, this.generateFlag,lightTracker)) {
				return;
		    }// branch blocked
			
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
		}
		placeLeavesBlob(world,branch.branchLocation.location(), leafList, lightTracker);
    }
	
	private void makeBifurcatingBranch (RTGTreeBranch branch, World world, Random rand,ArrayList<BlockPos> leafList,SkylightTracker lightTracker) {
		while (branch.notDone()) {
			BlockPos oldLocation = branch.location();
			BlockPos location = branch.moved();
			if (!placeLogBlock(world, location, this.branchBlock, this.generateFlag,lightTracker)) {
				return;
		    }// branch blocked
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
		}
		{
			double leftDirection = branch.initialHorizontal + 0.5f + rand.nextFloat()*0.5f;
			float leftAscent = branch.initialVertical -0.2f + rand.nextFloat()*0.4f;
			float  leftLength = branch.initialLength *(.6f + rand.nextFloat()*0.2f);
			RTGTreeBranch leftBranch = new RTGTreeBranch(leftDirection,leftAscent,leftLength,branch.stage+1,branch.location());
			makeLogBranch(leftBranch,world,rand, leafList,lightTracker);
		}
		{
			double rightDirection = branch.initialHorizontal - 1f + rand.nextFloat()*0.5f;
			float rightAscent = branch.initialVertical -0.2f + rand.nextFloat()*0.4f;
			float  rightLength = branch.initialLength *(.6f + rand.nextFloat()*0.2f);
			RTGTreeBranch rightBranch = new RTGTreeBranch(rightDirection,rightAscent,rightLength,branch.stage+1,branch.location());
			makeLogBranch(rightBranch,world,rand, leafList,lightTracker);
		}
    }
    
    private void placeLeavesBlob(World world, BlockPos center, ArrayList<BlockPos> leafList, SkylightTracker lightTracker ) {
    	for (int dy = 0; dy < 2; dy ++) {
	    	for (int dz = -2; dz < 3; dz ++) {
	    		for (int dx = -2; dx < 3; dx ++) {
	    			if (dz*dz + dx*dx == 8) continue;// leaving corners open.
	    			recordLeaf(new BlockPos(center.getX() + dx, center.getY()+dy,center.getZ()+dz),leafList);
	    		}
	    	}
    	}
    	BlockPos topCenter = center.up(2);
    	recordLeaf(topCenter,leafList);
    	recordLeaf(topCenter.east(),leafList);
    	recordLeaf(topCenter.west(),leafList);
    	recordLeaf(topCenter.north(),leafList);
    	recordLeaf(topCenter.south(),leafList);
    	
    	placeLeaves(world, leafList,lightTracker);
    }
    
    private void recordLeaf(BlockPos recorded, ArrayList<BlockPos> leafList) {
    	leafList.add(recorded);
    }
    
    private void placeLeaves(World world, ArrayList<BlockPos> leafList, SkylightTracker lightTracker ) {
    	for (BlockPos pos: leafList) {
    		this.placeLeavesBlock(world, pos, leavesBlock, generateFlag,lightTracker);
    	}
    	leafList.clear();
    }
}
