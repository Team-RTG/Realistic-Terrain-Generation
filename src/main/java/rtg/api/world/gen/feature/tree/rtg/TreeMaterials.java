package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import rtg.api.world.RTGWorld;

public class TreeMaterials {
	public final String name;
	public final IBlockState log;
	public final IBlockState leaves;
	public final IBlockState branches;
	public TreeMaterials(String name, IBlockState log, IBlockState leaves, IBlockState branches) {
		this.name = name;
		this.log = log;
		this.leaves = leaves;
		this.branches = branches;
	}
	
	public boolean equals(Object compared) {
		if (!(compared instanceof TreeMaterials)) return false;
		TreeMaterials comparedMaterials = (TreeMaterials)compared;
		if (!this.log.equals(comparedMaterials.log)) return false;
		if (!this.leaves.equals(comparedMaterials.leaves)) return false;
		if (!this.branches.equals(comparedMaterials.branches)) return false;
		return true;
	}
	
	public static class Picker {
		public static final TreeMaterials spruce = new TreeMaterials(
				"Spruce",
				Blocks.LOG.getStateFromMeta(1),
				Blocks.LEAVES.getStateFromMeta(1),
				Blocks.LOG.getStateFromMeta(13));
		public static final TreeMaterials oak = new TreeMaterials(
				"Oak",
				Blocks.LOG.getStateFromMeta(0),
				Blocks.LEAVES.getStateFromMeta(0),
				Blocks.LOG.getStateFromMeta(12));
		public static final TreeMaterials birch = new TreeMaterials(
				"Birch",
				Blocks.LOG.getStateFromMeta(2),
				Blocks.LEAVES.getStateFromMeta(2),
				Blocks.LOG.getStateFromMeta(14));
		public static final TreeMaterials acacia = new TreeMaterials(
				"Acacia",
				Blocks.LOG2.getStateFromMeta(0),
				Blocks.LEAVES2.getStateFromMeta(0),
				Blocks.LOG2.getStateFromMeta(12));
		
		public final TreeMaterials forNoise(float noise) {
			if (noise <= -1) return spruce;
			if (noise < 1) return oak;
			return birch;
			
		}
	}
	
	public static abstract class Chooser {
		protected TreeMaterials.Picker picker = new Picker();
		public abstract TreeMaterials materialFor(BlockPos pos, RTGWorld world, Random rand);
		
	}
	
	public static TreeMaterials.Chooser inBirchForest  = new Chooser() {
		public TreeMaterials materialFor(BlockPos pos, RTGWorld world, Random rand) {
        	float divisor = RTGWorld.getTreeMaterialsNoiseDivisor();
        	float rawNoise = world.treeMaterialsNoise()
        			.noise2f(((float)pos.getX())/divisor, (float)pos.getZ()/divisor);
        	return this.picker.forNoise(-rawNoise + rand.nextFloat()*1f + 1f);
    	}
	};
	
	public static TreeMaterials.Chooser inOakForest = new Chooser() {
	    public TreeMaterials materialFor(BlockPos pos, RTGWorld world, Random rand) {
        	float divisor = RTGWorld.getTreeMaterialsNoiseDivisor();
        	float rawNoise = world.treeMaterialsNoise()
        			.noise2f(((float)pos.getX())/divisor, (float)pos.getZ()/divisor);
        	return this.picker.forNoise(rawNoise + rand.nextFloat()*1.4f -0.7f);
    	    }
	    };
	public static TreeMaterials.Chooser inSpruceForest = new Chooser() {
	    public TreeMaterials materialFor(BlockPos pos, RTGWorld world, Random rand) {
        	float divisor = RTGWorld.getTreeMaterialsNoiseDivisor();
        	float rawNoise = world.treeMaterialsNoise()
        			.noise2f(((float)pos.getX())/divisor, (float)pos.getZ()/divisor);
        	return this.picker.forNoise(- rawNoise - rand.nextFloat()*1f -1.0f);
    	    }
	    };
}