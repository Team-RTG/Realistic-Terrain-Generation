package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

import rtg.api.util.BlockUtil;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaPungens;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusMonticola;

/**
 * @author WhichOnesPink
 */
public class DecoCollectionTaiga extends DecoCollectionBase {

    public DecoCollectionTaiga(boolean fallenTrees, float grassStrengthFactor) {

        super();

        TreeRTG monticolaTree = new TreeRTGPinusMonticola();
        monticolaTree.setLogBlock(BlockUtil.getStateLog(1));
        monticolaTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        monticolaTree.setMinTrunkSize(2);
        monticolaTree.setMaxTrunkSize(3);
        monticolaTree.setMinCrownSize(3);
        monticolaTree.setMaxCrownSize(4);
        this.addTree(monticolaTree);
        DecoTree monticolaDeco = new DecoTree(monticolaTree);
        monticolaDeco.setStrengthFactorForLoops(3f);
        monticolaDeco.setTreeType(DecoTree.TreeType.RTG_TREE);
        monticolaDeco.getDistribution().setNoiseDivisor(100f);
        monticolaDeco.getDistribution().setNoiseFactor(6f);
        monticolaDeco.getDistribution().setNoiseAddend(0.8f);
        monticolaDeco.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        monticolaDeco.setTreeConditionNoise(0f);
        monticolaDeco.setTreeConditionChance(2);
        monticolaDeco.setMaxY(100);
        this.addDeco(monticolaDeco);

        TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
        sitchensisTree.setLogBlock(BlockUtil.getStateLog(1));
        sitchensisTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        sitchensisTree.setMinTrunkSize(4);
        sitchensisTree.setMaxTrunkSize(9);
        sitchensisTree.setMinCrownSize(5);
        sitchensisTree.setMaxCrownSize(14);
        this.addTree(sitchensisTree);
        DecoTree sitchensisDeco = new DecoTree(sitchensisTree);
        sitchensisDeco.setStrengthFactorForLoops(4f);
        sitchensisDeco.setTreeType(DecoTree.TreeType.RTG_TREE);
        sitchensisDeco.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        sitchensisDeco.setTreeConditionChance(3);
        sitchensisDeco.setMaxY(100);

        TreeRTG pungensTree = new TreeRTGPiceaPungens();
        pungensTree.setLogBlock(BlockUtil.getStateLog(1));
        pungensTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        pungensTree.setMinTrunkSize(2);
        pungensTree.setMaxTrunkSize(7);
        pungensTree.setMinCrownSize(6);
        pungensTree.setMaxCrownSize(17);
        this.addTree(pungensTree);
        DecoTree pungensDeco = new DecoTree(pungensTree);
        pungensDeco.setStrengthFactorForLoops(4f);
        pungensDeco.setTreeType(DecoTree.TreeType.RTG_TREE);
        pungensDeco.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        pungensDeco.setTreeConditionChance(3);
        pungensDeco.setMaxY(100);

        DecoHelper5050 mediumTrees = new DecoHelper5050(sitchensisDeco, pungensDeco);
        this.addDeco(mediumTrees);

        DecoTree vanillaTaigaPine = new DecoTree();
        vanillaTaigaPine.setWorldGen(new WorldGenTaiga1());
        vanillaTaigaPine.setTreeType(DecoTree.TreeType.WORLDGEN);
        vanillaTaigaPine.setLoops(3);
        vanillaTaigaPine.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        vanillaTaigaPine.setTreeConditionChance(1);
        vanillaTaigaPine.setMaxY(110);

        DecoTree vanillaTaigaSpruce = new DecoTree();
        vanillaTaigaSpruce.setWorldGen(new WorldGenTaiga2(false));
        vanillaTaigaSpruce.setTreeType(DecoTree.TreeType.WORLDGEN);
        vanillaTaigaSpruce.setLoops(3);
        vanillaTaigaSpruce.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        vanillaTaigaSpruce.setTreeConditionChance(1);
        vanillaTaigaSpruce.setMaxY(110);

        DecoHelper5050 vanillaTrees = new DecoHelper5050(vanillaTaigaPine, vanillaTaigaSpruce);
        this.addDeco(vanillaTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(24);
        decoFallenTree.setLogBlock(BlockUtil.getStateLog(1));
        decoFallenTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        this.addDeco(decoFallenTree, fallenTrees);

        DecoShrub decoShrubSpruce = new DecoShrub();
        decoShrubSpruce.setLogBlock(BlockUtil.getStateLog(1));
        decoShrubSpruce.setLeavesBlock(BlockUtil.getStateLeaf(1));
        decoShrubSpruce.setMaxY(100);
        decoShrubSpruce.setStrengthFactor(4f);
        decoShrubSpruce.setChance(4);
        this.addDeco(decoShrubSpruce);

//		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
//		this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState());
        decoBoulder.setChance(20);
        decoBoulder.setMaxY(95);
        decoBoulder.setStrengthFactor(2f);
        this.addDeco(decoBoulder);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.setMaxY(90);
        decoPumpkin.setRandomType(rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH);
        decoPumpkin.setRandomFloat(32f);
        this.addDeco(decoPumpkin);

        DecoFlowersRTG flowers = new DecoFlowersRTG()
            .setFlowers(new int[]{3, 6, 8, 13})
            .setMaxY(90)
            .setStrengthFactor(6f);
        this.addDeco(flowers);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.setMaxY(90);
        decoMushrooms.setRandomType(rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH);
        decoMushrooms.setRandomFloat(24f);
        this.addDeco(decoMushrooms);

        DecoGrass decoFern = new DecoGrass(2);
        decoFern.setMaxY(128);
        decoFern.setStrengthFactor(grassStrengthFactor);
        this.addDeco(decoFern);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(grassStrengthFactor);
        this.addDeco(decoGrass);
    }
}
