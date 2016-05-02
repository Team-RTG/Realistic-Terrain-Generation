package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
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

		DecoTree oakPine = new DecoTree(new TreeRTGPiceaSitchensis());
    	oakPine.logBlock = Blocks.log;
    	oakPine.logMeta = (byte)0;
    	oakPine.leavesBlock = Blocks.leaves;
    	oakPine.leavesMeta = (byte)0;
    	oakPine.minTrunkSize = 4;
    	oakPine.maxTrunkSize = 10;
    	oakPine.minCrownSize = 6;
    	oakPine.maxCrownSize = 14;
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
