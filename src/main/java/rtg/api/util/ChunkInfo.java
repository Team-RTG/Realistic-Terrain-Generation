package rtg.api.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkProviderServer;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBase;

// This class stores expensive to calculate info like noises on a chunk basis to be used for regionally varying decorators.
public class ChunkInfo {

	public  ChunkPos pos;
	public  RTGWorld rtgWorld;

	private final static int TREESIMPLEX  = 8;
	
	private Distribution treeDistribution;
	
	public ChunkInfo(ChunkPos _pos, RTGWorld _rtgWorld) {
		pos = _pos;
		rtgWorld = _rtgWorld;
	}
	
	public float treedensity() {
		BlockPos offsetPos = DecoBase.getOffsetPos(pos);
        float noise = rtgWorld.simplexInstance(TREESIMPLEX)
                .noise2f(offsetPos.getX() / treeDistribution.getNoiseDivisor(), offsetPos.getZ() / treeDistribution.getNoiseDivisor())
                * treeDistribution.getNoiseFactor() + treeDistribution.getNoiseAddend();
        return noise;
	}
	
    private final int TREE_HEIGHT_INDEX = 8;
    private SimplexNoise treeHeightNoise() {return rtgWorld.simplexInstance(TREE_HEIGHT_INDEX);}
    private final float treeHeightNoiseDivisor = 1237;
    
    private Float storedTreeHeight  = null;
    
    public float treeHeightNoiseValue() {
    	if (storedTreeHeight == null) {
    	    BlockPos offsetPos = DecoBase.getOffsetPos(pos);
    	    storedTreeHeight = treeHeightNoise()
                .noise2f(offsetPos.getX() / treeHeightNoiseDivisor, offsetPos.getZ() / treeHeightNoiseDivisor);
    	}
    	return storedTreeHeight;
    }
    
    public World world() {return rtgWorld.world();}
	
}
