package rtg.api.world.deco.collection;

import net.minecraft.init.Blocks;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.*;
import rtg.api.world.deco.DecoFallenTree.LogCondition;
import rtg.api.world.deco.DecoTree.TreeCondition;
import rtg.api.world.deco.DecoTree.TreeType;
import rtg.api.world.deco.helper.DecoHelperThisOrThat;
import rtg.api.world.deco.helper.DecoHelperThisOrThat.ChanceType;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCupressusSempervirens;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionTaiga extends DecoCollectionBase {

    public DecoCollectionTaiga(BiomeConfig config, float grassStrengthFactor) {

        super(config);

        TreeRTG sempervirensSpruceTree1 = new TreeRTGCupressusSempervirens();
        sempervirensSpruceTree1.setLogBlock(BlockUtil.getStateLog(1));
        sempervirensSpruceTree1.setLeavesBlock(BlockUtil.getStateLeaf(1));
        sempervirensSpruceTree1.setMinTrunkSize(3);
        sempervirensSpruceTree1.setMaxTrunkSize(7);
        sempervirensSpruceTree1.setMinCrownSize(5);
        sempervirensSpruceTree1.setMaxCrownSize(10);
        this.addTree(sempervirensSpruceTree1);

        DecoTree bigSpruceTrees1 = new DecoTree(sempervirensSpruceTree1);
        bigSpruceTrees1.setStrengthFactorForLoops(4f);
        bigSpruceTrees1.setTreeType(TreeType.RTG_TREE);
        bigSpruceTrees1.getDistribution().setNoiseDivisor(100f);
        bigSpruceTrees1.getDistribution().setNoiseFactor(6f);
        bigSpruceTrees1.getDistribution().setNoiseAddend(0.8f);
        bigSpruceTrees1.setTreeCondition(TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        bigSpruceTrees1.setTreeConditionNoise(0f);
        bigSpruceTrees1.setTreeConditionChance(1);
        bigSpruceTrees1.setMaxY(110);

        TreeRTG sempervirensSpruceOakTree1 = new TreeRTGCupressusSempervirens();
        sempervirensSpruceOakTree1.setLogBlock(BlockUtil.getStateLog(1));
        sempervirensSpruceOakTree1.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        sempervirensSpruceOakTree1.setMinTrunkSize(3);
        sempervirensSpruceOakTree1.setMaxTrunkSize(7);
        sempervirensSpruceOakTree1.setMinCrownSize(5);
        sempervirensSpruceOakTree1.setMaxCrownSize(10);
        this.addTree(sempervirensSpruceOakTree1);

        DecoTree bigSpruceOakTrees1 = new DecoTree(sempervirensSpruceOakTree1);
        bigSpruceOakTrees1.setStrengthFactorForLoops(4f);
        bigSpruceOakTrees1.setTreeType(TreeType.RTG_TREE);
        bigSpruceOakTrees1.getDistribution().setNoiseDivisor(100f);
        bigSpruceOakTrees1.getDistribution().setNoiseFactor(6f);
        bigSpruceOakTrees1.getDistribution().setNoiseAddend(0.8f);
        bigSpruceOakTrees1.setTreeCondition(TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        bigSpruceOakTrees1.setTreeConditionNoise(0f);
        bigSpruceOakTrees1.setTreeConditionChance(1);
        bigSpruceOakTrees1.setMaxY(110);

        DecoHelperThisOrThat decoHelperThisOrThat1 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees1, bigSpruceOakTrees1);
        this.addDeco(decoHelperThisOrThat1);

        TreeRTG sempervirensSpruceTree2 = new TreeRTGCupressusSempervirens();
        sempervirensSpruceTree2.setLogBlock(BlockUtil.getStateLog(1));
        sempervirensSpruceTree2.setLeavesBlock(BlockUtil.getStateLeaf(1));
        sempervirensSpruceTree2.setMinTrunkSize(3);
        sempervirensSpruceTree2.setMaxTrunkSize(7);
        sempervirensSpruceTree2.setMinCrownSize(5);
        sempervirensSpruceTree2.setMaxCrownSize(10);
        this.addTree(sempervirensSpruceTree2);

        DecoTree bigSpruceTrees2 = new DecoTree(sempervirensSpruceTree2);
        bigSpruceTrees2.setStrengthFactorForLoops(4f);
        bigSpruceTrees2.setTreeType(TreeType.RTG_TREE);
        bigSpruceTrees2.getDistribution().setNoiseDivisor(80f);
        bigSpruceTrees2.getDistribution().setNoiseFactor(60f);
        bigSpruceTrees2.getDistribution().setNoiseAddend(-15f);
        bigSpruceTrees2.setTreeCondition(TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        bigSpruceTrees2.setTreeConditionNoise(0f);
        bigSpruceTrees2.setTreeConditionChance(1);
        bigSpruceTrees2.setMaxY(110);

        TreeRTG sempervirensSpruceOakTree2 = new TreeRTGCupressusSempervirens();
        sempervirensSpruceOakTree2.setLogBlock(BlockUtil.getStateLog(1));
        sempervirensSpruceOakTree2.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        sempervirensSpruceOakTree2.setMinTrunkSize(3);
        sempervirensSpruceOakTree2.setMaxTrunkSize(7);
        sempervirensSpruceOakTree2.setMinCrownSize(5);
        sempervirensSpruceOakTree2.setMaxCrownSize(10);
        this.addTree(sempervirensSpruceOakTree2);

        DecoTree bigSpruceOakTrees2 = new DecoTree(sempervirensSpruceOakTree2);
        bigSpruceOakTrees2.setStrengthFactorForLoops(4f);
        bigSpruceOakTrees2.setTreeType(TreeType.RTG_TREE);
        bigSpruceOakTrees2.getDistribution().setNoiseDivisor(80f);
        bigSpruceOakTrees2.getDistribution().setNoiseFactor(60f);
        bigSpruceOakTrees2.getDistribution().setNoiseAddend(-15f);
        bigSpruceOakTrees2.setTreeCondition(TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        bigSpruceOakTrees2.setTreeConditionNoise(0f);
        bigSpruceOakTrees2.setTreeConditionChance(1);
        bigSpruceOakTrees2.setMaxY(110);

        DecoHelperThisOrThat decoHelperThisOrThat2 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees2, bigSpruceOakTrees2);
        this.addDeco(decoHelperThisOrThat2);

        TreeRTG sempervirensSpruceTree3 = new TreeRTGCupressusSempervirens();
        sempervirensSpruceTree3.setLogBlock(BlockUtil.getStateLog(1));
        sempervirensSpruceTree3.setLeavesBlock(BlockUtil.getStateLeaf(1));
        sempervirensSpruceTree3.setMinTrunkSize(3);
        sempervirensSpruceTree3.setMaxTrunkSize(7);
        sempervirensSpruceTree3.setMinCrownSize(5);
        sempervirensSpruceTree3.setMaxCrownSize(10);
        this.addTree(sempervirensSpruceTree3);

        DecoTree bigSpruceTrees3 = new DecoTree(sempervirensSpruceTree3);
        bigSpruceTrees3.setStrengthFactorForLoops(3f);
        bigSpruceTrees3.setTreeType(TreeType.RTG_TREE);
        bigSpruceTrees3.getDistribution().setNoiseDivisor(80f);
        bigSpruceTrees3.getDistribution().setNoiseFactor(60f);
        bigSpruceTrees3.getDistribution().setNoiseAddend(-15f);
        bigSpruceTrees3.setTreeCondition(TreeCondition.RANDOM_CHANCE);
        bigSpruceTrees3.setTreeConditionChance(2);
        bigSpruceTrees3.setMaxY(120);

        TreeRTG sempervirensSpruceOakTree3 = new TreeRTGCupressusSempervirens();
        sempervirensSpruceOakTree3.setLogBlock(BlockUtil.getStateLog(1));
        sempervirensSpruceOakTree3.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        sempervirensSpruceOakTree3.setMinTrunkSize(3);
        sempervirensSpruceOakTree3.setMaxTrunkSize(7);
        sempervirensSpruceOakTree3.setMinCrownSize(5);
        sempervirensSpruceOakTree3.setMaxCrownSize(10);
        this.addTree(sempervirensSpruceOakTree3);

        DecoTree bigSpruceOakTrees3 = new DecoTree(sempervirensSpruceOakTree3);
        bigSpruceOakTrees3.setStrengthFactorForLoops(3f);
        bigSpruceOakTrees3.setTreeType(TreeType.RTG_TREE);
        bigSpruceOakTrees3.getDistribution().setNoiseDivisor(80f);
        bigSpruceOakTrees3.getDistribution().setNoiseFactor(60f);
        bigSpruceOakTrees3.getDistribution().setNoiseAddend(-15f);
        bigSpruceOakTrees3.setTreeCondition(TreeCondition.RANDOM_CHANCE);
        bigSpruceOakTrees3.setTreeConditionChance(2);
        bigSpruceOakTrees3.setMaxY(120);

        DecoHelperThisOrThat decoHelperThisOrThat3 = new DecoHelperThisOrThat(4, ChanceType.NOT_EQUALS_ZERO, bigSpruceTrees3, bigSpruceOakTrees3);
        this.addDeco(decoHelperThisOrThat3);

        TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
        sitchensisTree.setLogBlock(BlockUtil.getStateLog(1));
        sitchensisTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        sitchensisTree.setMinTrunkSize(4);
        sitchensisTree.setMaxTrunkSize(9);
        sitchensisTree.setMinCrownSize(5);
        sitchensisTree.setMaxCrownSize(14);
        this.addTree(sitchensisTree);

        DecoTree decoTrees = new DecoTree(sitchensisTree);
        decoTrees.setStrengthFactorForLoops(4f);
        decoTrees.setTreeType(TreeType.RTG_TREE);
        decoTrees.setTreeCondition(TreeCondition.RANDOM_CHANCE);
        decoTrees.setTreeConditionChance(3);
        decoTrees.setMaxY(120);
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(32);
        decoFallenTree.setLogBlock(BlockUtil.getStateLog(1));
        decoFallenTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        this.addDeco(decoFallenTree, config.ALLOW_LOGS.get());

        DecoShrub decoShrubSpruce = new DecoShrub();
        decoShrubSpruce.setLogBlock(BlockUtil.getStateLog(1));
        decoShrubSpruce.setLeavesBlock(BlockUtil.getStateLeaf(1));
        decoShrubSpruce.setMaxY(100);
        decoShrubSpruce.setStrengthFactor(3f);
        decoShrubSpruce.setChance(6);
        this.addDeco(decoShrubSpruce);

//		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
//		decoBaseBiomeDecorations.setEqualsZeroChance(3);
//		this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState());
        decoBoulder.setChance(20);
        decoBoulder.setMaxY(95);
        decoBoulder.setStrengthFactor(2f);
        this.addDeco(decoBoulder);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.setMaxY(90);
        decoPumpkin.setRandomType(DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH);
        decoPumpkin.setRandomFloat(32f);
        this.addDeco(decoPumpkin);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.setMaxY(90);
        decoMushrooms.setRandomType(DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH);
        decoMushrooms.setRandomFloat(24f);
        this.addDeco(decoMushrooms);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(grassStrengthFactor);
        this.addDeco(decoGrass);
    }
}
