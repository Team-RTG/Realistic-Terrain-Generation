package rtg.api.world.deco.collection;

import net.minecraft.init.Blocks;

import rtg.api.config.BiomeConfig;
import rtg.api.world.deco.*;
import rtg.api.world.deco.DecoFallenTree.LogCondition;
import rtg.api.world.deco.DecoTree.TreeCondition;
import rtg.api.world.deco.DecoTree.TreeType;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGAcaciaBucheri;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionSavanna extends DecoCollectionBase {

    public DecoCollectionSavanna(BiomeConfig config) {

        super(config);

        DecoShrub acaciaShrub = new DecoShrub();
        acaciaShrub.setLogBlock(Blocks.LOG2.getDefaultState());
        acaciaShrub.setLeavesBlock(Blocks.LEAVES2.getDefaultState());
        acaciaShrub.config().MAX_Y.set(160);
        acaciaShrub.config().STRENGTH_FACTOR.set(2f);
        acaciaShrub.config().CHANCE.set(12);
        this.addDeco(acaciaShrub);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.config().LOOPS.set(1);
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(36);
        decoFallenTree.setLogBlock(Blocks.LOG2.getDefaultState());
        decoFallenTree.setLeavesBlock(Blocks.LEAVES2.getDefaultState());
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        this.addDeco(decoFallenTree, config.ALLOW_LOGS.get());

        TreeRTG bucheriTree = new TreeRTGAcaciaBucheri();
        bucheriTree.setLogBlock(Blocks.LOG2.getDefaultState());
        bucheriTree.setLeavesBlock(Blocks.LEAVES2.getDefaultState());
        bucheriTree.setMinTrunkSize(4);
        bucheriTree.setMaxTrunkSize(9);
        this.addTree(bucheriTree);

        DecoTree bucheriTrees = new DecoTree(bucheriTree);
        bucheriTrees.config().LOOPS.set(1);
        bucheriTrees.setTreeType(TreeType.RTG_TREE);
        bucheriTrees.getDistribution().setNoiseDivisor(80f);
        bucheriTrees.getDistribution().setNoiseFactor(60f);
        bucheriTrees.getDistribution().setNoiseAddend(-15f);
        bucheriTrees.setTreeCondition(TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        bucheriTrees.setTreeConditionNoise(-0.4f);
        bucheriTrees.setTreeConditionChance(28);
        this.addDeco(bucheriTrees);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.config().BOULDER_BLOCK.set(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.config().CHANCE.set(32);
        decoBoulder.config().MAX_Y.set(95);
        this.addDeco(decoBoulder);

        DecoDoubleGrass decoDoubleGrass = new DecoDoubleGrass();
        decoDoubleGrass.config().MAX_Y.set(128);
        decoDoubleGrass.config().STRENGTH_FACTOR.set(3f);
        this.addDeco(decoDoubleGrass);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.config().MAX_Y.set(128);
        decoGrass.config().STRENGTH_FACTOR.set(10f);
        this.addDeco(decoGrass);
    }
}
