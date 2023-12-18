package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import rtg.api.util.BlockUtil;


/**
 * Ice Spikes
 */
public class IceSpikeRTG extends TreeRTG {
	
	float taper = 0.15f;
	
	private static int dx(int direction) {
		direction  = direction % 8;
		if ((direction == 0)||(direction == 4)) return 0;
		if (direction < 5) return 1;
		return -1;
	}	
	
	private static int dz(int direction) {
		direction  = (direction + 2)% 8;
		if ((direction == 0)||(direction == 4)) return 0;
		if (direction < 5) return 1;
		return -1;
	}

    public IceSpikeRTG() {

        super();

        this.setLogBlock(Blocks.PACKED_ICE.getDefaultState());
        this.setLeavesBlock(Blocks.AIR.getDefaultState());
        this.trunkSize = 1;
        this.crownSize = 13;
        this.setNoLeaves(true);
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
            if (!this.placeTrunkBlock(world, new BlockPos(x, y, z), this.generateFlag, lightTracker)) return false;
            y++;
        }

        int lastDirection = -1;
        int branchWait = 0;
        for (i = 0; i < this.crownSize; i++) {
        	// place a branch most of the time as soon as possible but not always because that would be boring
            if (rand.nextInt(4) < 3 && i < this.crownSize - 2) { 

                int length = (int)((this.crownSize - i)*taper + 0.9);
                //length = length - 1 + rand.nextInt(3);
                if (length > 0 && branchWait <=0 ) {
                	int direction = rand.nextInt(8); // eight orthogonal and diagonal possible directions
                	if (direction == lastDirection) direction ++;
                   buildBranches(world, rand, x, y, z, direction, length, lightTracker);
                   lastDirection = direction;
                   // branches need to be separated by at least two or their logs can touch diagonally
                   branchWait = 2;
                } else {
                	branchWait  -= 1;
                }
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

        return true;
    }

    private void buildBranches(World world, Random rand, int x, int y, int z, int direction, int logLength, SkylightTracker lightTracker) {
    	// three branches as close as possible to equidistant
        buildBranch(world, rand, x, y, z, dx(direction), dz(direction), logLength, 0, lightTracker);
        direction += 2;
        buildBranch(world, rand, x, y, z, dx(direction), dz(direction), logLength, 0, lightTracker);
        direction += 3;
        buildBranch(world, rand, x, y, z, dx(direction), dz(direction), logLength, 0, lightTracker);
    	
    }
    
    @Override
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize, SkylightTracker lightTracker) {
    	// slanting
    	int dY = 1;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = 0; k < 2; k++) {
                    if (Math.abs(i) + Math.abs(j) + Math.abs(k) < leaveSize + 1) {
                        //buildLeaves(world, x + i + (dX * logLength), y + k, z + j + (dZ * logLength));
                    }
                }
            }
        }

        for (int m = 1; m <= logLength; m++) {
            this.placeLogBlock(world, new BlockPos(x + (dX * m), y + (dY * m), z + (dZ * m)), this.logBlock, this.generateFlag, lightTracker);
        }
    }

    @Override
    public void buildLeaves(World world, int x, int y, int z, SkylightTracker lightTracker) {

        if (!this.noLeaves) {

            this.placeLeavesBlock(world, new BlockPos(x, y, z), this.leavesBlock, this.generateFlag, lightTracker);
        }
    }
}
