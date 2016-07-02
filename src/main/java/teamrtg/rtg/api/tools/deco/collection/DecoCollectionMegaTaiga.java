package teamrtg.rtg.api.tools.deco.collection;

import net.minecraft.init.Blocks;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperRandomSplit;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPiceaPungens;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPiceaSitchensis;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPinusPonderosa;
import teamrtg.rtg.api.world.biome.deco.DecoBase;


/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionMegaTaiga extends DecoCollectionBase
{

	public DecoCollectionMegaTaiga()
	{
		super();
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
		sitchensisTree.logBlock = Blocks.LOG.getStateFromMeta(1);
		sitchensisTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
		sitchensisTree.minTrunkSize = 4;
		sitchensisTree.maxTrunkSize = 9;
		sitchensisTree.minCrownSize = 5;
		sitchensisTree.maxCrownSize = 14;
		this.addTree(sitchensisTree);
		
		DecoTree smallPines = new DecoTree(sitchensisTree);
		smallPines.strengthNoiseFactorXForLoops = true;
		smallPines.strengthFactorForLoops = 4f;
		smallPines.treeType = TreeType.RTG_TREE;
		smallPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallPines.treeConditionChance = 3;
		smallPines.maxY = 100;
		
		TreeRTG pungensTree = new TreeRTGPiceaPungens();
		pungensTree.logBlock = Blocks.LOG.getStateFromMeta(1);
		pungensTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
		pungensTree.minTrunkSize = 2;
		pungensTree.maxTrunkSize = 7;
		pungensTree.minCrownSize = 6;
		pungensTree.maxCrownSize = 17;
		this.addTree(pungensTree);
		
		DecoTree spruceTrees = new DecoTree(pungensTree);
		spruceTrees.strengthNoiseFactorXForLoops = true;
		spruceTrees.strengthFactorForLoops = 4f;
		spruceTrees.treeType = TreeType.RTG_TREE;
		spruceTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		spruceTrees.treeConditionChance = 1;
		spruceTrees.maxY = 100;
		
		TreeRTG ponderosaTree = new TreeRTGPinusPonderosa();
		ponderosaTree.logBlock = Blocks.LOG.getDefaultState();
		ponderosaTree.leavesBlock = Blocks.LEAVES.getDefaultState();
		ponderosaTree.minTrunkSize = 11;
		ponderosaTree.maxTrunkSize = 21;
		ponderosaTree.minCrownSize = 15;
		ponderosaTree.maxCrownSize = 29;
		this.addTree(ponderosaTree);
		
		DecoTree oakPines = new DecoTree(ponderosaTree);
		oakPines.strengthNoiseFactorXForLoops = true;
		oakPines.strengthFactorForLoops = 4f;
		oakPines.treeType = TreeType.RTG_TREE;
		oakPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		oakPines.treeConditionChance = 1;
		oakPines.maxY = 100;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{spruceTrees, smallPines, oakPines};
		decoHelperRandomSplit.chances = new int[]{8, 2, 2};
		this.addDeco(decoHelperRandomSplit);
	}
}
