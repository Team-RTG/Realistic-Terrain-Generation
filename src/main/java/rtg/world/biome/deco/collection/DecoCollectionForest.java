package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.BlockUtil;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionForest extends DecoCollectionBase {

    // Tends to return values between -3f to 5f, with some overflow.
    private DecoTree.Distribution forestDistribution = new DecoTree.Distribution(100f, 6f, 0.8f);

    private float short1Min = -3f;
    private float short1Max = -1f;
    private float tallMin = -1f;
    private float tallMax = 3f;
    private float short2Min = 3f;
    private float short2Max = 5f;

    public DecoCollectionForest(boolean fallenTrees) {

        // Tall trees first.

        TreeRTG ponderosaOakTreeTall = new TreeRTGPinusPonderosa();
        ponderosaOakTreeTall.logBlock = Blocks.LOG.getDefaultState();
        ponderosaOakTreeTall.leavesBlock = Blocks.LEAVES.getDefaultState();
        ponderosaOakTreeTall.minTrunkSize = 11;
        ponderosaOakTreeTall.maxTrunkSize = 21;
        ponderosaOakTreeTall.minCrownSize = 15;
        ponderosaOakTreeTall.maxCrownSize = 29;
        this.addTree(ponderosaOakTreeTall);
        DecoTree oakPines = new DecoTree(ponderosaOakTreeTall);
        oakPines.strengthFactorForLoops = 8f;
        oakPines.treeType = TreeType.RTG_TREE;
        oakPines.distribution = forestDistribution;
        oakPines.treeCondition = TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE;
        oakPines.treeConditionNoise = tallMin;
        oakPines.treeConditionNoise2 = tallMax;
        oakPines.treeConditionChance = 1;
        oakPines.maxY = 85;

        TreeRTG ponderosaSpruceTreeTall = new TreeRTGPinusPonderosa();
        ponderosaSpruceTreeTall.logBlock = BlockUtil.getStateLog(1);
        ponderosaSpruceTreeTall.leavesBlock = BlockUtil.getStateLeaf(1);
        ponderosaSpruceTreeTall.minTrunkSize = 11;
        ponderosaSpruceTreeTall.maxTrunkSize = 21;
        ponderosaSpruceTreeTall.minCrownSize = 15;
        ponderosaSpruceTreeTall.maxCrownSize = 29;
        this.addTree(ponderosaSpruceTreeTall);
        DecoTree sprucePines = new DecoTree(ponderosaSpruceTreeTall);
        sprucePines.strengthFactorForLoops = 8f;
        sprucePines.treeType = TreeType.RTG_TREE;
        sprucePines.distribution = forestDistribution;
        sprucePines.treeCondition = TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE;
        sprucePines.treeConditionNoise = tallMin;
        sprucePines.treeConditionNoise2 = tallMax;
        sprucePines.treeConditionChance = 1;
        sprucePines.maxY = 85;

        DecoHelper5050 decoPines = new DecoHelper5050(oakPines, sprucePines);
        this.addDeco(decoPines);

        // Short trees next.

        TreeRTG ponderosaOakTreeShort1 = new TreeRTGPiceaSitchensis();
        ponderosaOakTreeShort1.logBlock = Blocks.LOG.getDefaultState();
        ponderosaOakTreeShort1.leavesBlock = Blocks.LEAVES.getDefaultState();
        ponderosaOakTreeShort1.minTrunkSize = 4;
        ponderosaOakTreeShort1.maxTrunkSize = 10;
        ponderosaOakTreeShort1.minCrownSize = 6;
        ponderosaOakTreeShort1.maxCrownSize = 14;
        this.addTree(ponderosaOakTreeShort1);
        DecoTree oakPinesShort1 = new DecoTree(ponderosaOakTreeShort1);
        oakPinesShort1.strengthFactorForLoops = 8f;
        oakPinesShort1.treeType = TreeType.RTG_TREE;
        oakPinesShort1.distribution = forestDistribution;
        oakPinesShort1.treeCondition = TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE;
        oakPinesShort1.treeConditionNoise = short1Min;
        oakPinesShort1.treeConditionNoise2 = short1Max;
        oakPinesShort1.treeConditionChance = 1;
        oakPinesShort1.maxY = 85;

        TreeRTG ponderosaSpruceTreeShort1 = new TreeRTGPiceaSitchensis();
        ponderosaSpruceTreeShort1.logBlock = BlockUtil.getStateLog(1);
        ponderosaSpruceTreeShort1.leavesBlock = BlockUtil.getStateLeaf(1);
        ponderosaSpruceTreeShort1.minTrunkSize = 4;
        ponderosaSpruceTreeShort1.maxTrunkSize = 10;
        ponderosaSpruceTreeShort1.minCrownSize = 6;
        ponderosaSpruceTreeShort1.maxCrownSize = 14;
        this.addTree(ponderosaSpruceTreeShort1);
        DecoTree sprucePinesShort1 = new DecoTree(ponderosaSpruceTreeShort1);
        sprucePinesShort1.strengthFactorForLoops = 8f;
        sprucePinesShort1.treeType = TreeType.RTG_TREE;
        sprucePinesShort1.distribution = forestDistribution;
        sprucePinesShort1.treeCondition = TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE;
        sprucePinesShort1.treeConditionNoise = short1Min;
        sprucePinesShort1.treeConditionNoise2 = short1Max;
        sprucePinesShort1.treeConditionChance = 1;
        sprucePinesShort1.maxY = 85;

        DecoHelper5050 decoPinesShort1 = new DecoHelper5050(oakPinesShort1, sprucePinesShort1);
        this.addDeco(decoPinesShort1);

        // Short trees next on the other 'side' of the noise spectrum.

        TreeRTG ponderosaOakTreeShort2 = new TreeRTGPiceaSitchensis();
        ponderosaOakTreeShort2.logBlock = Blocks.LOG.getDefaultState();
        ponderosaOakTreeShort2.leavesBlock = Blocks.LEAVES.getDefaultState();
        ponderosaOakTreeShort2.minTrunkSize = 4;
        ponderosaOakTreeShort2.maxTrunkSize = 10;
        ponderosaOakTreeShort2.minCrownSize = 6;
        ponderosaOakTreeShort2.maxCrownSize = 14;
        this.addTree(ponderosaOakTreeShort2);
        DecoTree oakPinesShort2 = new DecoTree(ponderosaOakTreeShort2);
        oakPinesShort2.strengthFactorForLoops = 8f;
        oakPinesShort2.treeType = TreeType.RTG_TREE;
        oakPinesShort2.distribution = forestDistribution;
        oakPinesShort2.treeCondition = TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE;
        oakPinesShort2.treeConditionNoise = short2Min;
        oakPinesShort2.treeConditionNoise2 = short2Max;
        oakPinesShort2.treeConditionChance = 1;
        oakPinesShort2.maxY = 85;

        TreeRTG ponderosaSpruceTreeShort2 = new TreeRTGPiceaSitchensis();
        ponderosaSpruceTreeShort2.logBlock = BlockUtil.getStateLog(1);
        ponderosaSpruceTreeShort2.leavesBlock = BlockUtil.getStateLeaf(1);
        ponderosaSpruceTreeShort2.minTrunkSize = 4;
        ponderosaSpruceTreeShort2.maxTrunkSize = 10;
        ponderosaSpruceTreeShort2.minCrownSize = 6;
        ponderosaSpruceTreeShort2.maxCrownSize = 14;
        this.addTree(ponderosaSpruceTreeShort2);
        DecoTree sprucePinesShort2 = new DecoTree(ponderosaSpruceTreeShort2);
        sprucePinesShort2.strengthFactorForLoops = 8f;
        sprucePinesShort2.treeType = TreeType.RTG_TREE;
        sprucePinesShort2.distribution = forestDistribution;
        sprucePinesShort2.treeCondition = TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE;
        sprucePinesShort2.treeConditionNoise = short2Min;
        sprucePinesShort2.treeConditionNoise2 = short2Max;
        sprucePinesShort2.treeConditionChance = 1;
        sprucePinesShort2.maxY = 85;

        DecoHelper5050 decoPinesShort2 = new DecoHelper5050(oakPinesShort2, sprucePinesShort2);
        this.addDeco(decoPinesShort2);

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

        WorldGenerator vanillaTreeDefinition = new WorldGenTrees(false);
        DecoTree vanillaTrees = new DecoTree(vanillaTreeDefinition);
        vanillaTrees.strengthFactorForLoops = 3f;
        vanillaTrees.treeType = TreeType.WORLDGEN;
        vanillaTrees.treeCondition = TreeCondition.RANDOM_CHANCE;
        vanillaTrees.treeConditionChance = 3;
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
        decoFallenOak.logBlock = Blocks.LOG.getDefaultState();
        decoFallenOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;

        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 24;
        decoFallenSpruce.maxY = 80;
        decoFallenSpruce.logBlock = BlockUtil.getStateLog(1);
        decoFallenSpruce.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;

        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
        this.addDeco(decoFallenTree, fallenTrees);

        // Shrubs to fill in the blanks.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 140;
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 3;
        this.addDeco(decoShrubOak);

        DecoShrub decoShrubSpruce = new DecoShrub();
        decoShrubSpruce.logBlock = BlockUtil.getStateLog(1);
        decoShrubSpruce.leavesBlock = BlockUtil.getStateLeaf(1);
        decoShrubSpruce.maxY = 140;
        decoShrubSpruce.strengthFactor = 4f;
        decoShrubSpruce.chance = 9;
        this.addDeco(decoShrubSpruce);

        // Only 1-block tall flowers so we can see the trees better.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
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
