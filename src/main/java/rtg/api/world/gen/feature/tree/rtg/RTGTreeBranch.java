package rtg.api.world.gen.feature.tree.rtg;

import net.minecraft.util.math.BlockPos;
import rtg.api.util.Logger;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG.FractionalBlockPos;

class RTGTreeBranch {
	static int reports = 0;
	final BranchVector direction;
	final float initialLength;
	private float remainingLength;
	final double initialHorizontal;
	final float initialVertical;
	final int stage;
	final FractionalBlockPos branchLocation;
	
	RTGTreeBranch (double horizontal, float vertical, float length, int stage, BlockPos branchStart) {
		initialLength = length;
		remainingLength = length;
		initialHorizontal = horizontal;
		initialVertical = vertical;
		this.stage = stage;
		direction = new BranchVector(horizontal,vertical);
		branchLocation = new FractionalBlockPos(branchStart);
	}
	
	RTGTreeBranch(RTGTreeBranch copied) {
		initialLength = copied.initialLength;
		remainingLength = copied.initialLength;
		initialHorizontal = copied.initialHorizontal;
		initialVertical= copied.initialVertical;
		stage = copied.stage;
		direction  = copied.direction;// this will be OK as long as nothing changes existing branch directions
		branchLocation = new FractionalBlockPos(copied.branchLocation);
	}
	
	BlockPos moved() {
		direction.move(branchLocation);
		remainingLength -= direction.length;
		return branchLocation.location();
	}
	
	BlockPos movedOrthogonally() {
		BlockPos oldPos = branchLocation.location();
		double oldX = branchLocation.x;
		double oldY = branchLocation.y;
		double oldZ = branchLocation.z;
		double multiplier = multiplierToNextBlock() + .0001; // fudge factor for rounding errors
		direction.moveFractionally(branchLocation,multiplierToNextBlock());
		remainingLength -= Math.abs(multiplier)/direction.length;
		BlockPos newPos = branchLocation.location();
		int differences = 0;
		if (oldPos.getX() != newPos.getX()) differences ++;
		if (oldPos.getY() != newPos.getY()) differences ++;
		if (oldPos.getZ() != newPos.getZ()) differences ++;
		if (differences != 1&&differences == 1) {// debugging code, currently off but not dead issues

			Logger.info("old {},{},{}",oldX, oldY,oldZ);
			Logger.info("pos {},{},{}",oldPos.getX(), oldPos.getY(),oldPos.getZ());

			Logger.info("vector {},{},{}",direction.dx, direction.dy,direction.dz);
			Logger.info("new {},{},{}",branchLocation.x, branchLocation.y,branchLocation.z);
			Logger.info("pos {},{},{}",newPos.getX(), newPos.getY(),newPos.getZ());
			Logger.info("multiplier: {}", multiplier);
			
		}
		return branchLocation.location();
	}
	
	private double multiplierToNextBlock() {
		double xMult = multiplierToNextInteger(branchLocation.x, direction.dx);
		double yMult = multiplierToNextInteger(branchLocation.y, direction.dy);
		double zMult = multiplierToNextInteger(branchLocation.z, direction.dz);
	   
		double result = Math.min(Math.min(yMult, zMult),xMult);
		if (result <0) throw new RuntimeException();
		return result;
	}
	
	private double multiplierToNextInteger(double number, double change) {
		double toGo;
		if (Math.abs(change) <.00001) return 100000;// something has to be big and this isn't it.
		if (change < 0) {
			toGo = Math.floor(number) - (number) - .000001; //make both toGo and change negative		}
			if (toGo > -.00001) toGo = -1.0;
		} else {
			toGo = Math.ceil(number) - (number) + .000001;
			if (toGo < .00001) toGo = 1.0;
		}
		return toGo/change;
		
	}
	
	final BlockPos location() {return branchLocation.location();}
	
	boolean notDone() {return remainingLength > 0;}

}
