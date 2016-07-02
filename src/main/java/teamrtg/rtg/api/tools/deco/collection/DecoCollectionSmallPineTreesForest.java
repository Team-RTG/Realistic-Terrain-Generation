package teamrtg.rtg.api.tools.deco.collection;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperRandomSplit;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPiceaSitchensis;
import teamrtg.rtg.api.tools.feature.tree.vanilla.WorldGenTreesRTG;
import teamrtg.rtg.api.world.biome.deco.DecoBase;


/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionSmallPineTreesForest extends DecoCollectionBase
{

	public DecoCollectionSmallPineTreesForest()
	{

		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
    	sitchensisTree.logBlock = Blocks.LOG.getDefaultState();
    	sitchensisTree.leavesBlock = Blocks.LEAVES.getDefaultState();
    	sitchensisTree.minTrunkSize = 4;
    	sitchensisTree.maxTrunkSize = 10;
    	sitchensisTree.minCrownSize = 6;
    	sitchensisTree.maxCrownSize = 14;
		this.addTree(sitchensisTree);
		
		DecoTree oakPine = new DecoTree(sitchensisTree);
		oakPine.strengthFactorForLoops = 3f;
    	oakPine.treeType = TreeType.RTG_TREE;
		oakPine.treeCondition = TreeCondition.RANDOM_CHANCE;
		oakPine.treeConditionChance = 4;
		oakPine.maxY = 110;
        
        DecoTree vanillaTrees = new DecoTree(new WorldGenTreesRTG());
		vanillaTrees.strengthFactorForLoops = 3f;
        vanillaTrees.treeType = TreeType.WORLDGEN;
		vanillaTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		vanillaTrees.treeConditionChance = 4;
		vanillaTrees.maxY = 110;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{oakPine, vanillaTrees};
		decoHelperRandomSplit.chances = new int[]{8, 4};
		this.addDeco(decoHelperRandomSplit);
	}
}
