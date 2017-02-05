package rtg.world.biome.deco.collection;

import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTrees;

import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionSmallPineTreesForest extends DecoCollectionBase {

    public DecoCollectionSmallPineTreesForest() {

        TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
        sitchensisTree.setLogBlock(Blocks.LOG.getDefaultState());
        sitchensisTree.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        sitchensisTree.setMinTrunkSize(4);
        sitchensisTree.setMaxTrunkSize(10);
        sitchensisTree.setMinCrownSize(6);
        sitchensisTree.setMaxCrownSize(14);
        this.addTree(sitchensisTree);

        DecoTree oakPine = new DecoTree(sitchensisTree);
        oakPine.setStrengthFactorForLoops(3f);
        oakPine.setTreeType(TreeType.RTG_TREE);
        oakPine.setTreeCondition(TreeCondition.RANDOM_CHANCE);
        oakPine.setTreeConditionChance(4);
        oakPine.setMaxY(110);

        DecoTree vanillaTrees = new DecoTree(new WorldGenTrees(false));
        vanillaTrees.setStrengthFactorForLoops(3f);
        vanillaTrees.setTreeType(TreeType.WORLDGEN);
        vanillaTrees.setTreeCondition(TreeCondition.RANDOM_CHANCE);
        vanillaTrees.setTreeConditionChance(4);
        vanillaTrees.setMaxY(110);

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{oakPine, vanillaTrees};
        decoHelperRandomSplit.chances = new int[]{8, 4};
        this.addDeco(decoHelperRandomSplit);
    }
}
