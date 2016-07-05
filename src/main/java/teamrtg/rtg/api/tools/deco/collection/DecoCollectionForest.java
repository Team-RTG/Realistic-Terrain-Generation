package teamrtg.rtg.api.tools.deco.collection;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenerator;
import teamrtg.rtg.api.tools.deco.DecoFallenTree;
import teamrtg.rtg.api.tools.deco.DecoFallenTree.LogCondition;
import teamrtg.rtg.api.tools.deco.DecoFlowersRTG;
import teamrtg.rtg.api.tools.deco.DecoGrass;
import teamrtg.rtg.api.tools.deco.DecoShrub;
import teamrtg.rtg.api.tools.deco.DecoTree;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeCondition;
import teamrtg.rtg.api.tools.deco.DecoTree.TreeType;
import teamrtg.rtg.api.tools.deco.helper.DecoHelper5050;
import teamrtg.rtg.api.tools.deco.helper.DecoHelperRandomSplit;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPiceaSitchensis;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTGPinusPonderosa;
import teamrtg.rtg.api.tools.feature.tree.vanilla.WorldGenTreesRTG;
import teamrtg.rtg.api.world.biome.deco.DecoBase;


/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionForest extends DecoCollectionBase
{

	public DecoCollectionForest()
	{

        // Trees first.
		
		TreeRTG ponderosaOakTree = new TreeRTGPinusPonderosa();
		ponderosaOakTree.logBlock = Blocks.LOG.getDefaultState();
		ponderosaOakTree.leavesBlock = Blocks.LEAVES.getDefaultState();
		ponderosaOakTree.minTrunkSize = 11;
		ponderosaOakTree.maxTrunkSize = 21;
		ponderosaOakTree.minCrownSize = 15;
		ponderosaOakTree.maxCrownSize = 29;
		this.addTree(ponderosaOakTree);
		
		DecoTree oakPines = new DecoTree(ponderosaOakTree);
		oakPines.strengthFactorForLoops = 8f;
		oakPines.treeType = TreeType.RTG_TREE;
		oakPines.distribution.noiseDivisor = 100f;
		oakPines.distribution.noiseFactor = 6f;
		oakPines.distribution.noiseAddend = 0.8f;
		oakPines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		oakPines.treeConditionNoise = 0f;
		oakPines.treeConditionChance = 1;
		oakPines.maxY = 85;
		
		TreeRTG ponderosaSpruceTree = new TreeRTGPinusPonderosa();
		ponderosaSpruceTree.logBlock = Blocks.LOG.getStateFromMeta(1);
		ponderosaSpruceTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
		ponderosaSpruceTree.minTrunkSize = 11;
		ponderosaSpruceTree.maxTrunkSize = 21;
		ponderosaSpruceTree.minCrownSize = 15;
		ponderosaSpruceTree.maxCrownSize = 29;
		this.addTree(ponderosaSpruceTree);
		
		DecoTree sprucePines = new DecoTree(ponderosaSpruceTree);
		sprucePines.strengthFactorForLoops = 8f;
		sprucePines.treeType = TreeType.RTG_TREE;
		sprucePines.distribution.noiseDivisor = 100f;
		sprucePines.distribution.noiseFactor = 6f;
		sprucePines.distribution.noiseAddend = 0.8f;
		sprucePines.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		sprucePines.treeConditionNoise = 0f;
		sprucePines.treeConditionChance = 1;
		sprucePines.maxY = 85;
		
		DecoHelper5050 decoPines = new DecoHelper5050(oakPines, sprucePines);
		this.addDeco(decoPines);
		
		// More trees.
		
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
		oakPine.maxY = 100;

        WorldGenerator vanillaTreeDefinition = new WorldGenTreesRTG();
        DecoTree vanillaTrees = new DecoTree(vanillaTreeDefinition);
		vanillaTrees.strengthFactorForLoops = 3f;
        vanillaTrees.treeType = TreeType.WORLDGEN;
		vanillaTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		vanillaTrees.treeConditionChance = 4;
		vanillaTrees.maxY = 120;
		
		DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
		decoHelperRandomSplit.decos = new DecoBase[]{oakPine, vanillaTrees};
		decoHelperRandomSplit.chances = new int[]{8, 4};
		this.addDeco(decoHelperRandomSplit);
		
		// Add some fallen trees of the oak and spruce variety (50/50 distribution).
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 16;
        decoFallenOak.maxY = 80;
        decoFallenOak.logBlock = Blocks.LOG;
        decoFallenOak.logMeta = (byte)0;
        decoFallenOak.leavesBlock = Blocks.LEAVES;
        decoFallenOak.leavesMeta = (byte)-1;
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;
		
        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 24;
        decoFallenSpruce.maxY = 80;
        decoFallenSpruce.logBlock = Blocks.LOG;
        decoFallenSpruce.logMeta = (byte)1;
        decoFallenSpruce.leavesBlock = Blocks.LEAVES;
        decoFallenSpruce.leavesMeta = (byte)-1;
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;
        
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
		this.addDeco(decoFallenTree);
        
        // Shrubs to fill in the blanks.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 140;
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 3;
		this.addDeco(decoShrubOak);
		
        DecoShrub decoShrubSpruce = new DecoShrub();
        decoShrubSpruce.logBlock = Blocks.LOG.getStateFromMeta(1);
        decoShrubSpruce.leavesBlock = Blocks.LEAVES.getStateFromMeta(1);
        decoShrubSpruce.maxY = 140;
        decoShrubSpruce.strengthFactor = 4f;
        decoShrubSpruce.chance = 9;
		this.addDeco(decoShrubSpruce);
        
		// Only 1-block tall flowers so we can see the trees better.
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 6f;
        this.addDeco(decoFlowersRTG);
        
        // Grass filler.
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.minY = 60;
		decoGrass.maxY = 128;
		decoGrass.loops = 8;
        this.addDeco(decoGrass);
	}
}
