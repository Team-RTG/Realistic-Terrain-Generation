package rtg.api.world.gen.feature.tree.rtg;

import rtg.api.world.gen.feature.tree.rtg.TreeRTG.FractionalBlockPos;

class BranchVector {
	final double dx;
	final double dy;
	final double dz;
    final double length;
	
	
	BranchVector (double horizontalDirection, float verticalShift) {
		double trialDx = Math.cos(horizontalDirection);
		double trialDz = Math.sin(horizontalDirection);
		double trialDy  = verticalShift;
		// adjust lengths so the vector goes to the edge of a unit cube
		double longestDirection =Math.max(Math.abs(trialDx),Math.abs(trialDy));
		longestDirection = Math.max(longestDirection,Math.abs(trialDz));
		dx = trialDx/longestDirection;
		dy = trialDy/longestDirection;
		dz = trialDz/longestDirection;
		length = Math.sqrt(dx*dx+dy*dy+dz*dz);
	}
	
    void move(FractionalBlockPos moved) {
    	moved.x += dx;
    	moved.y += dy;
    	moved.z += dz;
    }
    
    void moveFractionally(FractionalBlockPos moved, double proportion) {
    	moved.x += dx*proportion;
    	moved.y += dy*proportion;
    	moved.z += dz*proportion;
    	
    }
	
}