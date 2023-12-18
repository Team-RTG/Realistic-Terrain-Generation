package rtg.api.world.deco;

import rtg.api.world.gen.feature.tree.rtg.TreeRTGCupressusSempervirens;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;

/**
 * Variable Spruce Trees
 * This class make trees of variable type and height based on a noise parameter from ChunkInfo
 * It gets 
 */

public class DecoVariableSpruce extends DecoVariableTree {
		
	public DecoVariableSpruce() {
		tallTree = new TreeRTGPinusPonderosa();
		mediumTree = new TreeRTGPiceaSitchensis();
		smallTree = new TreeRTGCupressusSempervirens();
		this.materials = this.materialsPicker.spruce;
		this.averageHeightSqrt += 0f;
	}
	
}
