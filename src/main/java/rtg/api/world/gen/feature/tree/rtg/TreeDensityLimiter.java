package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import rtg.api.util.Logger;

// A class to limit the number of trees from a chunk. 
// Mostly just a way to pass a float variable
public class TreeDensityLimiter {
	
	private float treesRemaining;
	public int treesSoFar;
	
	public TreeDensityLimiter(float treeLimit) {
		treesRemaining = treeLimit;
	}
	
	public boolean allowed(float needed, Random rand) {
		//Logger.info("remaining {} needed {}", treesRemaining,needed);
		if (treesRemaining <= 0) return false;
		if (treesRemaining >= needed) {
			treesSoFar ++;
			treesRemaining -= needed;
			return true;
		}
		// conditional, chance equal to the ratio remaining/needed
		boolean allowed = rand.nextFloat()*needed < treesRemaining;
		if (allowed) treesSoFar++;
		treesRemaining = 0;
		return allowed;
	}
	
	public boolean notDone() {return treesRemaining > 0;}
	
	public void occupy(float occupied) {
		treesSoFar++;
		treesRemaining -= occupied;
	}
	
	public float remaining() {return treesRemaining;}

}
