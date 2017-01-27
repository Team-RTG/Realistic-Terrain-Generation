package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;

import rtg.api.util.BlockUtil;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaPentandra;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaRosea;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import static rtg.world.biome.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionRoofedForest extends DecoCollectionBase {

    private int lowTreesMinY = 63;
    private int lowTreesMaxY = 80;
    private int highTreesMinY = 81;
    private int highTreesMaxY = 90;

    public DecoCollectionRoofedForest(boolean allowLogs, boolean allowCobwebs, boolean allowPonds) {

        super();

        this.addDeco(ponds(), allowPonds)
            .addDeco(mushrooms())
            .addDeco(mucronataTrees(lowTreesMinY, lowTreesMaxY))
            .addDeco(pentandraTrees(lowTreesMinY, lowTreesMaxY))
            .addDeco(roseaTrees(lowTreesMinY, lowTreesMaxY))
            .addDeco(logs(), allowLogs)
            .addDeco(darkOakShrubs())
            .addDeco(oakShrubs())
            .addDeco(boulders())
            .addDeco(cobwebs(), allowCobwebs)
            .addDeco(baseBiomeDecorations())
            .addDeco(grass())
            .addDeco(deadBushes());
    }

    private DecoTree mucronataTrees(int minY, int maxY) {

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
        mucronataTree.setLogBlock(BlockUtil.getStateLog2(1));
        mucronataTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        mucronataTree.setMinTrunkSize(2);
        mucronataTree.setMaxTrunkSize(3);
        mucronataTree.setMinCrownSize(10);
        mucronataTree.setMaxCrownSize(18);
        mucronataTree.setNoLeaves(false);
        this.addTree(mucronataTree);

        return new DecoTree(mucronataTree)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(1)
            .setStrengthFactorForLoops(8f)
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
        pentandraTree.setMinCrownSize(10);
        pentandraTree.setMaxCrownSize(18);
        pentandraTree.setNoLeaves(false);
        this.addTree(pentandraTree);

        return new DecoTree(pentandraTree)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(1)
            .setStrengthFactorForLoops(8f)
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
        roseaTree.setMinCrownSize(10);
        roseaTree.setMaxCrownSize(18);
        roseaTree.setNoLeaves(false);
        this.addTree(roseaTree);

        return new DecoTree(roseaTree)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(1)
            .setStrengthFactorForLoops(8f)
            .setMinY(minY)
            .setMaxY(maxY)
            .setScatter(new DecoTree.Scatter(16, 0));
    }

    private DecoPond ponds() {
        return new DecoPond()
            .setChunksPerPond(2)
            .setMaxY(67)
            .setLoops(2);
    }

    private DecoMushrooms mushrooms() {
        return new DecoMushrooms()
            .setChance(6)
            .setMaxY(90)
            .setRandomType(rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE);
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
            .setMaxY(76);
    }

    private DecoShrub darkOakShrubs() {
        return new DecoShrub()
            .setLogBlock(BlockUtil.getStateLog2(1))
            .setLeavesBlock(BlockUtil.getStateLeaf2(1))
            .setMaxY(90)
            .setStrengthFactor(32f);
    }

    private DecoShrub oakShrubs() {
        return new DecoShrub()
            .setLogBlock(Blocks.LOG.getDefaultState())
            .setLeavesBlock(Blocks.LEAVES.getDefaultState())
            .setMaxY(90)
            .setStrengthFactor(32f);
    }

    private DecoBoulder boulders() {
        return new DecoBoulder()
            .setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState())
            .setChance(16)
            .setMaxY(80)
            .setStrengthFactor(2f);
    }

    private DecoCobwebs cobwebs() {
        return new DecoCobwebs()
            .setChance(1)
            .setMinY(63)
            .setMaxY(76)
            .setStrengthFactor(24f)
            .setAdjacentBlock(BlockUtil.getStateLog2(1))
            .setMinAdjacents(2);
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        return new DecoBaseBiomeDecorations()
            .setMaxY(100);
    }

    private DecoGrass grass() {
        return new DecoGrass()
            .setMaxY(100)
            .setStrengthFactor(32f);
    }

    private DecoDeadBush deadBushes() {
        return new DecoDeadBush()
            .setMaxY(90)
            .setChance(2)
            .setStrengthFactor(2f);
    }
}
