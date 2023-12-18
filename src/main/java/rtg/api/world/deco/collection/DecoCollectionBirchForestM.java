package rtg.api.world.deco.collection;

import rtg.api.config.BiomeConfig;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.DecoVariableMaterialTree;
import rtg.api.world.gen.feature.tree.rtg.TreeMaterials;

public class DecoCollectionBirchForestM extends DecoCollectionBirchForest {
	
	public DecoCollectionBirchForestM(BiomeConfig config) {

        super(config);
        
	}
	
    protected DecoTree tallVariableTrees(float noiseMin, float noiseMax) {
    	
    	DecoVariableMaterialTree result =  new DecoVariableMaterialTree(TreeMaterials.inBirchForest);
                result.setStrengthFactorForLoops(6f)
                .setTreeType(DecoTree.TreeType.RTG_TREE)
                .setDistribution(treeFrequencyDistribution)
                .setTreeCondition(DecoTree.TreeCondition.ALWAYS_GENERATE)
                .setTreeConditionNoise(noiseMin)
                .setTreeConditionNoise2(noiseMax)
                .setTreeConditionChance(1)
                .setStrengthNoiseFactorForLoops(true);
                
                result.changeAverageHeightSqrt(2f);
                result.changeHeightNoiseVariability(-1f);
        
        return result;
    }
}
