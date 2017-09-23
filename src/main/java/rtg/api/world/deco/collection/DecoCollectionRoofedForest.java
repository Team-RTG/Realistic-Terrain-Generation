package rtg.api.world.deco.collection;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenTrees;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.*;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCeibaPentandra;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCeibaRosea;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionRoofedForest extends DecoCollectionBase {

    // Tends to return values between -3f to 5f, with some overflow.
    private DecoTree.Distribution forestDistribution = new DecoTree.Distribution(100f, 6f, 0.8f);

    private float distributionNoiseMin = -3.2f;
    private float distributionNoiseMax = 5.4f;

    private int treesMinY = 63;
    private int treesMaxY = 225;

    public DecoCollectionRoofedForest(BiomeConfig config, int treesMinY, int treesMaxY) {

        super(config);

        this.treesMinY = treesMinY;
        this.treesMaxY = treesMaxY;

        this
            .addDeco(ponds(), config.ALLOW_PONDS_WATER.get())
            .addDeco(mushrooms())
            .addDeco(mucronataTrees(treesMinY, treesMaxY))
            .addDeco(pentandraTrees(treesMinY, treesMaxY))
            .addDeco(roseaTrees(treesMinY, treesMaxY))
            .addDeco(canopyTrees())
            .addDeco(vanillaTrees())
            .addDeco(logs(), config.ALLOW_LOGS.get())
            .addDeco(darkOakShrubs())
            .addDeco(oakShrubs())
            .addDeco(boulders())
            .addDeco(cobwebs(), config.ALLOW_COBWEBS.get())
            //.addDeco(baseBiomeDecorations())
            .addDeco(grass())
            .addDeco(deadBushes())
        ;
    }

    private DecoTree mucronataTrees(int minY, int maxY) {

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
        mucronataTree.setLogBlock(BlockUtil.getStateLog2(1));
        mucronataTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        mucronataTree.setMinTrunkSize(2);
        mucronataTree.setMaxTrunkSize(3);
        mucronataTree.setMinCrownSize(8);
        mucronataTree.setMaxCrownSize(16);
        mucronataTree.setNoLeaves(false);
        this.addTree(mucronataTree);

        return new DecoTree(mucronataTree)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(DecoTree.TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(distributionNoiseMin)
            .setTreeConditionNoise2(distributionNoiseMax)
            .setTreeConditionChance(5)
            .setStrengthFactorForLoops(10f)
            .setMinY(minY)
            .setMaxY(maxY)
            .setScatter(new DecoTree.Scatter(16, 0));
    }

    private DecoTree pentandraTrees(int minY, int maxY) {

        TreeRTG pentandraTree = new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f);
        pentandraTree.setLogBlock(BlockUtil.getStateLog2(1));
        pentandraTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        pentandraTree.setMinTrunkSize(2);
        pentandraTree.setMaxTrunkSize(3);
        pentandraTree.setMinCrownSize(8);
        pentandraTree.setMaxCrownSize(16);
        pentandraTree.setNoLeaves(false);
        this.addTree(pentandraTree);

        return new DecoTree(pentandraTree)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(DecoTree.TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(distributionNoiseMin)
            .setTreeConditionNoise2(distributionNoiseMax)
            .setTreeConditionChance(5)
            .setStrengthFactorForLoops(10f)
            .setMinY(minY)
            .setMaxY(maxY)
            .setScatter(new DecoTree.Scatter(16, 0));
    }

    private DecoTree roseaTrees(int minY, int maxY) {

        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
        roseaTree.setLogBlock(BlockUtil.getStateLog2(1));
        roseaTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        roseaTree.setMinTrunkSize(2);
        roseaTree.setMaxTrunkSize(3);
        roseaTree.setMinCrownSize(8);
        roseaTree.setMaxCrownSize(16);
        roseaTree.setNoLeaves(false);
        this.addTree(roseaTree);

        return new DecoTree(roseaTree)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(DecoTree.TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(distributionNoiseMin)
            .setTreeConditionNoise2(distributionNoiseMax)
            .setTreeConditionChance(5)
            .setStrengthFactorForLoops(10f)
            .setMinY(minY)
            .setMaxY(maxY)
            .setScatter(new DecoTree.Scatter(16, 0));
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        return new DecoBaseBiomeDecorations()
            .setNotEqualsZeroChance(4)
            .setMinY(treesMinY)
            .setMaxY(treesMaxY);
    }

    private DecoTree canopyTrees() {
        return new DecoTree()
            .setWorldGen(new WorldGenCanopyTree(false))
            .setTreeType(DecoTree.TreeType.WORLDGEN)
            .setStrengthFactorForLoops(12f)
            .setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(1)
            .setMinY(treesMinY)
            .setMaxY(treesMaxY);
    }

    private DecoTree vanillaTrees() {
        return new DecoTree()
            .setWorldGen(new WorldGenTrees(false))
            .setTreeType(DecoTree.TreeType.WORLDGEN)
            .setStrengthFactorForLoops(8f)
            .setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(1)
            .setMinY(treesMinY)
            .setMaxY(treesMaxY);
    }

    private DecoPond ponds() {
        return new DecoPond()
            .setChunksPerPond(10)
            .setMaxY(67)
            .setLoops(1);
    }

    private DecoMushrooms mushrooms() {
        return new DecoMushrooms()
            .setChance(6)
            .setMaxY(90)
            .setRandomType(DecoMushrooms.RandomType.ALWAYS_GENERATE);
    }

    private DecoFallenTree logs() {
        return new DecoFallenTree()
            .setDistribution(new DecoFallenTree.Distribution(80f, 60f, -15f))
            .setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE)
            .setLogConditionChance(16)
            .setLogConditionNoise(0f)
            .setLogBlock(BlockUtil.getStateLog2(1))
            .setLeavesBlock(BlockUtil.getStateLeaf2(1))
            .setMinSize(4)
            .setMaxSize(9)
            .setMaxY(72);
    }

    private DecoShrub darkOakShrubs() {
        return new DecoShrub()
            .setLogBlock(BlockUtil.getStateLog2(1))
            .setLeavesBlock(BlockUtil.getStateLeaf2(1))
            .setStrengthFactor(40f)
            .setMinY(treesMinY)
            .setMaxY(treesMaxY);
    }

    private DecoShrub oakShrubs() {
        return new DecoShrub()
            .setLogBlock(Blocks.LOG.getDefaultState())
            .setLeavesBlock(Blocks.LEAVES.getDefaultState())
            .setStrengthFactor(40f)
            .setMinY(treesMinY)
            .setMaxY(treesMaxY);
    }

    private DecoBoulder boulders() {
        return new DecoBoulder()
            .setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState())
            .setChance(12)
            .setMaxY(80)
            .setStrengthFactor(2f);
    }

    private DecoCobwebs cobwebs() {
        return new DecoCobwebs()
            .setChance(1)
            .setMinY(treesMinY)
            .setMaxY(treesMaxY)
            .setStrengthFactor(24f)
            .setAdjacentBlock(BlockUtil.getStateLog2(1))
            .setMinAdjacents(2);
    }

    private DecoGrass grass() {
        return new DecoGrass()
            .setStrengthFactor(32f);
    }

    private DecoDeadBush deadBushes() {
        return new DecoDeadBush()
            .setChance(2)
            .setStrengthFactor(2f);
    }
}
