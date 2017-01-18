package rtg.world.biome.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.util.BlockUtil;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import static rtg.world.biome.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionExtremeHills extends DecoCollectionBase {

    private int treeChance;

    public DecoCollectionExtremeHills(int treeChance, boolean fallenTrees) {

        this.treeChance = treeChance;

        this.addDeco(nigraDecos()) // Trees.
            .addDeco(logDecos(), fallenTrees) // Logs.
            .addDeco(shrubDecos()) // Shrubs.
            .addDeco(boulders()) // Boulders.
            .addDeco(flowers()) // Flowers.
            .addDeco(mushrooms()) // Mushrooms.
            .addDeco(pumpkins()) // Pumpkins.
            .addDeco(doublePlants()) // Ferns & double tall grass.
            .addDeco(grass()); // Grass.
    }

    private DecoTree nigraTrees(IBlockState log, IBlockState leaves) {

        TreeRTG nigraTree = new TreeRTGPinusNigra();
        nigraTree.setLogBlock(log);
        nigraTree.setLeavesBlock(leaves);
        nigraTree.setMinTrunkSize(14);
        nigraTree.setMaxTrunkSize(20);
        nigraTree.setMinCrownSize(10);
        nigraTree.setMaxCrownSize(14);

        this.addTree(nigraTree);

        DecoTree nigraDeco = new DecoTree(nigraTree);
        nigraDeco.setStrengthFactorForLoops(4f);
        nigraDeco.setStrengthNoiseFactorXForLoops(true);
        nigraDeco.getDistribution().setNoiseDivisor(100f);
        nigraDeco.getDistribution().setNoiseFactor(6f);
        nigraDeco.getDistribution().setNoiseAddend(0.8f);
        nigraDeco.setTreeType(DecoTree.TreeType.RTG_TREE);
        nigraDeco.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        nigraDeco.setTreeConditionChance(this.treeChance);
        nigraDeco.setMaxY(85);

        return nigraDeco;
    }

    private DecoHelper5050 nigraDecos() {
        return new DecoHelper5050(
            nigraTrees(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            nigraTrees(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1))
        );
    }

    private DecoFallenTree logs(IBlockState log, IBlockState leaves) {

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(16);
        decoFallenTree.setLogBlock(log);
        decoFallenTree.setLeavesBlock(leaves);
        decoFallenTree.setMinSize(4);
        decoFallenTree.setMaxSize(7);
        decoFallenTree.setMaxY(75);

        return decoFallenTree;
    }

    private DecoHelper5050 logDecos() {
        return new DecoHelper5050(
            logs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            logs(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1))
        );
    }

    private DecoShrub shrubs(IBlockState log, IBlockState leaves) {
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setLogBlock(log);
        decoShrub.setLeavesBlock(leaves);
        decoShrub.setStrengthFactor(4f);
        decoShrub.setChance(2);
        decoShrub.setMaxY(110);
        return decoShrub;
    }

    private DecoHelper5050 shrubDecos() {
        return new DecoHelper5050(
            shrubs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            shrubs(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1))
        );
    }

    private DecoBoulder boulders() {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState());
        decoBoulder.setChance(12);
        decoBoulder.setMaxY(90);
        decoBoulder.setStrengthFactor(2f);
        return decoBoulder;
    }

    private DecoFlowersRTG flowers() {
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.setFlowers(new int[]{9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2});
        decoFlowersRTG.setMaxY(80);
        decoFlowersRTG.setLoops(3);
        return decoFlowersRTG;
    }

    private DecoMushrooms mushrooms() {
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.setMaxY(70);
        decoMushrooms.setRandomType(rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH);
        decoMushrooms.setRandomFloat(3f);
        return decoMushrooms;
    }

    private DecoPumpkin pumpkins() {
        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.setMaxY(90);
        decoPumpkin.setRandomType(rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH);
        decoPumpkin.setRandomFloat(30f);
        return decoPumpkin;
    }

    private DecoLargeFernDoubleTallgrass doublePlants() {
        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.setMaxY(128);
        decoDoublePlants.fernChance = 3;
        decoDoublePlants.setLoops(15);
        return decoDoublePlants;
    }

    private DecoGrass grass() {
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(10f);
        return decoGrass;
    }
}
