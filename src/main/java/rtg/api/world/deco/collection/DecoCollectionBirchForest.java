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

        DecoTree decoTree = new DecoTree(birchTree)
            .setStrengthFactorForLoops(3f)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setTreeCondition(DecoTree.TreeCondition.ALWAYS_GENERATE);

        decoTree.config().MAX_Y.set(100);

        return decoTree;
    }

    private DecoTree vanillaTrees() {
        DecoTree decoTree = new DecoTree(new WorldGenTrees(false))
            .setTreeType(DecoTree.TreeType.WORLDGEN)
            .setStrengthFactorForLoops(3f)
            .setTreeCondition(DecoTree.TreeCondition.ALWAYS_GENERATE);

        decoTree.config().MAX_Y.set(100);

        return decoTree;
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
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.config().MAX_Y.set(120);
        decoShrub.config().STRENGTH_FACTOR.set(3f);

        return decoShrub;
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.config().NOT_EQUALS_ZERO_CHANCE.set(3);
        return decoBaseBiomeDecorations;
    }

    private DecoFlowersRTG flowers() {
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG()
            .setFlowers(new int[]{3, 6});

        decoFlowersRTG.config().MAX_Y.set(128);
        decoFlowersRTG.config().STRENGTH_FACTOR.set(12f);

        return decoFlowersRTG;
    }

    private DecoGrass grass() {
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.config().MIN_Y.set(60);
        decoGrass.config().MAX_Y.set(128);
        decoGrass.config().STRENGTH_FACTOR.set(20f);

        return decoGrass;
    }
}
