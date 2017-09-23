package rtg.api.world.deco.collection;

import net.minecraft.world.gen.feature.WorldGenTrees;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.*;
import rtg.api.world.deco.helper.DecoHelperRandomSplit;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.RANDOM_CHANCE;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionBirchForest extends DecoCollectionBase {

    private DecoTree.Distribution forestDistribution = new DecoTree.Distribution(80f, 60f, -15f);

    public DecoCollectionBirchForest(BiomeConfig config) {

        super(config);

        this
            .addDeco(tallBirchTrees())
            .addDeco(randomTrees())
            .addDeco(logs(), config.ALLOW_LOGS.get()) // Add some fallen birch trees.
            .addDeco(shrubsOak()) // Oak shrubs to fill in the blanks.
            .addDeco(baseBiomeDecorations()) // Let the biome partially-decorate itself.
            .addDeco(flowers()) // Only 1-block tall flowers so we can see the trees better.
            .addDeco(grass()) // Grass filler.
        ;
    }

    private DecoHelperRandomSplit randomTrees() {
        return new DecoHelperRandomSplit()
            .setDecos(new DecoBase[]{tallBirchTrees(), vanillaTrees()})
            .setChances(new int[]{10, 4});
    }

    private DecoTree tallBirchTrees() {

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera()
            .setLogBlock(BlockUtil.getStateLog(2))
            .setLeavesBlock(BlockUtil.getStateLeaf(2))
            .setMinTrunkSize(4)
            .setMaxTrunkSize(10)
            .setMinCrownSize(8)
            .setMaxCrownSize(19);

        this.addTree(birchTree);

        return new DecoTree(birchTree)
            .setStrengthFactorForLoops(3f)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setTreeCondition(DecoTree.TreeCondition.ALWAYS_GENERATE)
            .setMaxY(100);
    }

    private DecoTree vanillaTrees() {
        return new DecoTree(new WorldGenTrees(false))
            .setTreeType(DecoTree.TreeType.WORLDGEN)
            .setStrengthFactorForLoops(3f)
            .setTreeCondition(DecoTree.TreeCondition.ALWAYS_GENERATE)
            .setMaxY(100);
    }

    private DecoFallenTree logs() {
        return new DecoFallenTree()
            .setLogCondition(RANDOM_CHANCE)
            .setLogConditionChance(8)
            .setLogBlock(BlockUtil.getStateLog(2))
            .setLeavesBlock(BlockUtil.getStateLeaf(2))
            .setMinSize(3)
            .setMaxSize(6);
    }

    private DecoShrub shrubsOak() {
        return new DecoShrub()
            .setMaxY(120)
            .setStrengthFactor(3f);
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        return new DecoBaseBiomeDecorations()
            .setNotEqualsZeroChance(3);
    }

    private DecoFlowersRTG flowers() {
        return new DecoFlowersRTG()
            .setFlowers(new int[]{3, 6})
            .setMaxY(128)
            .setStrengthFactor(12f);
    }

    private DecoGrass grass() {
        return new DecoGrass()
            .setMinY(60)
            .setMaxY(128)
            .setStrengthFactor(20f);
    }
}
