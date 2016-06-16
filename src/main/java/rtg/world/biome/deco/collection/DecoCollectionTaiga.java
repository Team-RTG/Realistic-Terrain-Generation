package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCupressusSempervirens;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;


/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionTaiga extends DecoCollectionBase
{

	public DecoCollectionTaiga(boolean fallenTrees, float grassStrengthFactor)
	{
		super();
		
		TreeRTG sempervirensSpruceTree1 = new TreeRTGCupressusSempervirens();
		sempervirensSpruceTree1.logBlock = Blocks.log;
		sempervirensSpruceTree1.logMeta = (byte)1;
		sempervirensSpruceTree1.leavesBlock = Blocks.leaves;
		sempervirensSpruceTree1.leavesMeta = (byte)1;
		sempervirensSpruceTree1.minTrunkSize = 3;
		sempervirensSpruceTree1.maxTrunkSize = 7;
		sempervirensSpruceTree1.minCrownSize = 5;
		sempervirensSpruceTree1.maxCrownSize = 10;
		this.addTree(sempervirensSpruceTree1);

		DecoTree bigSpruceTrees1 = new DecoTree(sempervirensSpruceTree1);
		bigSpruceTrees1.strengthFactorForLoops = 4f;
		bigSpruceTrees1.treeType = TreeType.RTG_TREE;
		bigSpruceTrees1.distribution.noiseDivisor = 100f;
		bigSpruceTrees1.distribution.noiseFactor = 6f;
		bigSpruceTrees1.distribution.noiseAddend = 0.8f;
		bigSpruceTrees1.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceTrees1.treeConditionNoise = 0f;
		bigSpruceTrees1.treeConditionChance = 1;
		bigSpruceTrees1.maxY = 110;
		
		TreeRTG sempervirensSpruceOakTree1 = new TreeRTGCupressusSempervirens();
		sempervirensSpruceOakTree1.logBlock = Blocks.log;
		sempervirensSpruceOakTree1.logMeta = (byte)1;
		sempervirensSpruceOakTree1.leavesBlock = Blocks.leaves;
		sempervirensSpruceOakTree1.leavesMeta = (byte)0;
		sempervirensSpruceOakTree1.minTrunkSize = 3;
		sempervirensSpruceOakTree1.maxTrunkSize = 7;
		sempervirensSpruceOakTree1.minCrownSize = 5;
		sempervirensSpruceOakTree1.maxCrownSize = 10;
		this.addTree(sempervirensSpruceOakTree1);
		
		DecoTree bigSpruceOakTrees1 = new DecoTree(sempervirensSpruceOakTree1);
		bigSpruceOakTrees1.strengthFactorForLoops = 4f;
		bigSpruceOakTrees1.treeType = TreeType.RTG_TREE;
		bigSpruceOakTrees1.distribution.noiseDivisor = 100f;
		bigSpruceOakTrees1.distribution.noiseFactor = 6f;
		bigSpruceOakTrees1.distribution.noiseAddend = 0.8f;
		bigSpruceOakTrees1.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceOakTrees1.treeConditionNoise = 0f;
		bigSpruceOakTrees1.treeConditionChance = 1;
		bigSpruceOakTrees1.maxY = 110;
		
		DecoHelperThisOrThat decoHelperThisOrThat1 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees1, bigSpruceOakTrees1);
		this.addDeco(decoHelperThisOrThat1);
		
		TreeRTG sempervirensSpruceTree2 = new TreeRTGCupressusSempervirens();
		sempervirensSpruceTree2.logBlock = Blocks.log;
		sempervirensSpruceTree2.logMeta = (byte)1;
		sempervirensSpruceTree2.leavesBlock = Blocks.leaves;
		sempervirensSpruceTree2.leavesMeta = (byte)1;
		sempervirensSpruceTree2.minTrunkSize = 3;
		sempervirensSpruceTree2.maxTrunkSize = 7;
		sempervirensSpruceTree2.minCrownSize = 5;
		sempervirensSpruceTree2.maxCrownSize = 10;
		this.addTree(sempervirensSpruceTree2);
		
		DecoTree bigSpruceTrees2 = new DecoTree(sempervirensSpruceTree2);
		bigSpruceTrees2.strengthFactorForLoops = 4f;
		bigSpruceTrees2.treeType = TreeType.RTG_TREE;
		bigSpruceTrees2.distribution.noiseDivisor = 80f;
		bigSpruceTrees2.distribution.noiseFactor = 60f;
		bigSpruceTrees2.distribution.noiseAddend = -15f;
		bigSpruceTrees2.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceTrees2.treeConditionNoise = 0f;
		bigSpruceTrees2.treeConditionChance = 1;
		bigSpruceTrees2.maxY = 110;
		
		TreeRTG sempervirensSpruceOakTree2 = new TreeRTGCupressusSempervirens();
		sempervirensSpruceOakTree2.logBlock = Blocks.log;
		sempervirensSpruceOakTree2.logMeta = (byte)1;
		sempervirensSpruceOakTree2.leavesBlock = Blocks.leaves;
		sempervirensSpruceOakTree2.leavesMeta = (byte)0;
		sempervirensSpruceOakTree2.minTrunkSize = 3;
		sempervirensSpruceOakTree2.maxTrunkSize = 7;
		sempervirensSpruceOakTree2.minCrownSize = 5;
		sempervirensSpruceOakTree2.maxCrownSize = 10;
		this.addTree(sempervirensSpruceOakTree2);
		
		DecoTree bigSpruceOakTrees2 = new DecoTree(sempervirensSpruceOakTree2);
		bigSpruceOakTrees2.strengthFactorForLoops = 4f;
		bigSpruceOakTrees2.treeType = TreeType.RTG_TREE;
		bigSpruceOakTrees2.distribution.noiseDivisor = 80f;
		bigSpruceOakTrees2.distribution.noiseFactor = 60f;
		bigSpruceOakTrees2.distribution.noiseAddend = -15f;
		bigSpruceOakTrees2.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		bigSpruceOakTrees2.treeConditionNoise = 0f;
		bigSpruceOakTrees2.treeConditionChance = 1;
		bigSpruceOakTrees2.maxY = 110;
		
		DecoHelperThisOrThat decoHelperThisOrThat2 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees2, bigSpruceOakTrees2);
		this.addDeco(decoHelperThisOrThat2);
		
		TreeRTG sempervirensSpruceTree3 = new TreeRTGCupressusSempervirens();
		sempervirensSpruceTree3.logBlock = Blocks.log;
		sempervirensSpruceTree3.logMeta = (byte)1;
		sempervirensSpruceTree3.leavesBlock = Blocks.leaves;
		sempervirensSpruceTree3.leavesMeta = (byte)1;
		sempervirensSpruceTree3.minTrunkSize = 3;
		sempervirensSpruceTree3.maxTrunkSize = 7;
		sempervirensSpruceTree3.minCrownSize = 5;
		sempervirensSpruceTree3.maxCrownSize = 10;
		this.addTree(sempervirensSpruceTree3);
		
		DecoTree bigSpruceTrees3 = new DecoTree(sempervirensSpruceTree3);
		bigSpruceTrees3.strengthFactorForLoops = 3f;
		bigSpruceTrees3.treeType = TreeType.RTG_TREE;
		bigSpruceTrees3.distribution.noiseDivisor = 80f;
		bigSpruceTrees3.distribution.noiseFactor = 60f;
		bigSpruceTrees3.distribution.noiseAddend = -15f;
		bigSpruceTrees3.treeCondition = TreeCondition.RANDOM_CHANCE;
		bigSpruceTrees3.treeConditionChance = 2;
		bigSpruceTrees3.maxY = 120;
		
		TreeRTG sempervirensSpruceOakTree3 = new TreeRTGCupressusSempervirens();
		sempervirensSpruceOakTree3.logBlock = Blocks.log;
		sempervirensSpruceOakTree3.logMeta = (byte)1;
		sempervirensSpruceOakTree3.leavesBlock = Blocks.leaves;
		sempervirensSpruceOakTree3.leavesMeta = (byte)0;
		sempervirensSpruceOakTree3.minTrunkSize = 3;
		sempervirensSpruceOakTree3.maxTrunkSize = 7;
		sempervirensSpruceOakTree3.minCrownSize = 5;
		sempervirensSpruceOakTree3.maxCrownSize = 10;
		this.addTree(sempervirensSpruceOakTree3);
		
		DecoTree bigSpruceOakTrees3 = new DecoTree(sempervirensSpruceOakTree3);
		bigSpruceOakTrees3.strengthFactorForLoops = 3f;
		bigSpruceOakTrees3.treeType = TreeType.RTG_TREE;
		bigSpruceOakTrees3.distribution.noiseDivisor = 80f;
		bigSpruceOakTrees3.distribution.noiseFactor = 60f;
		bigSpruceOakTrees3.distribution.noiseAddend = -15f;
		bigSpruceOakTrees3.treeCondition = TreeCondition.RANDOM_CHANCE;
		bigSpruceOakTrees3.treeConditionChance = 2;
		bigSpruceOakTrees3.maxY = 120;
		
		DecoHelperThisOrThat decoHelperThisOrThat3 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees3, bigSpruceOakTrees3);
		this.addDeco(decoHelperThisOrThat3);
		
		TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
		sitchensisTree.logBlock = Blocks.log;
		sitchensisTree.logMeta = (byte)1;
		sitchensisTree.leavesBlock = Blocks.leaves;
		sitchensisTree.leavesMeta = (byte)1;
		sitchensisTree.minTrunkSize = 4;
		sitchensisTree.maxTrunkSize = 9;
		sitchensisTree.minCrownSize = 5;
		sitchensisTree.maxCrownSize = 14;
		this.addTree(sitchensisTree);
		
		DecoTree decoTrees = new DecoTree(sitchensisTree);
		decoTrees.strengthFactorForLoops = 4f;
		decoTrees.treeType = TreeType.RTG_TREE;
		decoTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
		decoTrees.treeConditionChance = 3;
		decoTrees.maxY = 120;
		this.addDeco(decoTrees);

		DecoFallenTree decoFallenTree = new DecoFallenTree();
		decoFallenTree.distribution.noiseDivisor = 100f;
		decoFallenTree.distribution.noiseFactor = 6f;
		decoFallenTree.distribution.noiseAddend = 0.8f;
		decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		decoFallenTree.logConditionNoise = 0f;
		decoFallenTree.logConditionChance = 12;
		decoFallenTree.logBlock = Blocks.log;
		decoFallenTree.logMeta = (byte)1;
		decoFallenTree.leavesBlock = Blocks.leaves;
		decoFallenTree.leavesMeta = (byte)-1;
		decoFallenTree.minSize = 3;
		decoFallenTree.maxSize = 6;
		this.addDeco(decoFallenTree, fallenTrees);
		
		DecoShrub decoShrubSpruce = new DecoShrub();
		decoShrubSpruce.logBlock = Blocks.log;
		decoShrubSpruce.logMeta = 1;
		decoShrubSpruce.leavesBlock = Blocks.leaves;
		decoShrubSpruce.leavesMeta = 1;
		decoShrubSpruce.maxY = 100;
		decoShrubSpruce.strengthFactor = 3f;
		decoShrubSpruce.chance = 6;
		this.addDeco(decoShrubSpruce);

//		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
//		decoBaseBiomeDecorations.equalsZeroChance = 3;
//		this.addDeco(decoBaseBiomeDecorations);
  
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.mossy_cobblestone;
		decoBoulder.chance = 20;
		decoBoulder.maxY = 95;
		decoBoulder.strengthFactor = 2f;
		this.addDeco(decoBoulder);
		
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 32f;
		this.addDeco(decoPumpkin);
		
		DecoMushrooms decoMushrooms = new DecoMushrooms();
		decoMushrooms.maxY = 90;
		decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
		decoMushrooms.randomFloat = 24f;
		this.addDeco(decoMushrooms);
		
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = grassStrengthFactor;
		this.addDeco(decoGrass);
	}
}
