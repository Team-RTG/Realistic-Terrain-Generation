package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaPungens;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;


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
		
		DecoTree smallPines = new DecoTree(new TreeRTGPiceaSitchensis());
		smallPines.logBlock = Blocks.log;
		smallPines.logMeta = (byte)1;
		smallPines.leavesBlock = Blocks.leaves;
		smallPines.leavesMeta = (byte)1;
		smallPines.minTrunkSize = 4;
		smallPines.maxTrunkSize = 9;
		smallPines.minCrownSize = 5;
		smallPines.maxCrownSize = 14;
		smallPines.strengthNoiseFactorXForLoops = true;
		smallPines.strengthFactorForLoops = 4f;
		smallPines.treeType = TreeType.RTG_TREE;
		smallPines.treeCondition = TreeCondition.ALWAYS_GENERATE;
		smallPines.treeConditionChance = 3;
		smallPines.maxY = 100;
		
		DecoTree spruceTrees = new DecoTree(new TreeRTGPiceaPungens());
		spruceTrees.logBlock = Blocks.log;
		spruceTrees.logMeta = (byte)1;
		spruceTrees.leavesBlock = Blocks.leaves;
		spruceTrees.leavesMeta = (byte)1;
		spruceTrees.minTrunkSize = 2;
		spruceTrees.maxTrunkSize = 7;
		spruceTrees.minCrownSize = 6;
		spruceTrees.maxCrownSize = 17;
		spruceTrees.strengthNoiseFactorXForLoops = true;
		spruceTrees.strengthFactorForLoops = 4f;
		spruceTrees.treeType = TreeType.RTG_TREE;
		spruceTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		spruceTrees.treeConditionChance = 1;
		spruceTrees.maxY = 100;
		
		DecoTree oakPines = new DecoTree(new TreeRTGPinusPonderosa());
		oakPines.logBlock = Blocks.log;
		oakPines.logMeta = (byte)0;
		oakPines.leavesBlock = Blocks.leaves;
		oakPines.leavesMeta = (byte)0;
		oakPines.minTrunkSize = 11;
		oakPines.maxTrunkSize = 21;
		oakPines.minCrownSize = 15;
		oakPines.maxCrownSize = 29;
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
