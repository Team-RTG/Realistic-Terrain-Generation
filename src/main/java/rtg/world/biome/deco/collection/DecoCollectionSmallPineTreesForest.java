package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.vanilla.WorldGenForestRTG;
import rtg.world.gen.feature.tree.vanilla.WorldGenTreesRTG;


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
    	sitchensisTree.logBlock = Blocks.log;
    	sitchensisTree.logMeta = (byte)0;
    	sitchensisTree.leavesBlock = Blocks.leaves;
    	sitchensisTree.leavesMeta = (byte)0;
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
        
        DecoTree vanillaForest = new DecoTree(new WorldGenForestRTG());
		vanillaForest.strengthFactorForLoops = 3f;
        vanillaForest.treeType = TreeType.WORLDGEN;
		vanillaForest.treeCondition = TreeCondition.RANDOM_CHANCE;
		vanillaForest.treeConditionChance = 4;
		vanillaForest.maxY = 110;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{oakPine, vanillaTrees, vanillaForest};
		decoHelperRandomSplit.chances = new int[]{8, 4, 1};
		this.addDeco(decoHelperRandomSplit);
	}
}
