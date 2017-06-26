package rtg.api.world.deco.collection;

import net.minecraft.init.Blocks;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.DecoTree.TreeCondition;
import rtg.api.world.deco.DecoTree.TreeType;
import rtg.api.world.deco.helper.DecoHelperRandomSplit;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaPungens;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionMegaTaiga extends DecoCollectionBase {

    public DecoCollectionMegaTaiga(BiomeConfig config) {

        super(config);

        TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
        sitchensisTree.setLogBlock(BlockUtil.getStateLog(1));
        sitchensisTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        sitchensisTree.setMinTrunkSize(4);
        sitchensisTree.setMaxTrunkSize(9);
        sitchensisTree.setMinCrownSize(5);
        sitchensisTree.setMaxCrownSize(14);
        this.addTree(sitchensisTree);

        DecoTree smallPines = new DecoTree(sitchensisTree);
        smallPines.setStrengthNoiseFactorXForLoops(true);
        smallPines.setStrengthFactorForLoops(4f);
        smallPines.setTreeType(TreeType.RTG_TREE);
        smallPines.setTreeCondition(TreeCondition.ALWAYS_GENERATE);
        smallPines.setTreeConditionChance(3);
        smallPines.setMaxY(100);

        TreeRTG pungensTree = new TreeRTGPiceaPungens();
        pungensTree.setLogBlock(BlockUtil.getStateLog(1));
        pungensTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
        pungensTree.setMinTrunkSize(2);
        pungensTree.setMaxTrunkSize(7);
        pungensTree.setMinCrownSize(6);
        pungensTree.setMaxCrownSize(17);
        this.addTree(pungensTree);

        DecoTree spruceTrees = new DecoTree(pungensTree);
        spruceTrees.setStrengthNoiseFactorXForLoops(true);
        spruceTrees.setStrengthFactorForLoops(4f);
        spruceTrees.setTreeType(TreeType.RTG_TREE);
        spruceTrees.setTreeCondition(TreeCondition.ALWAYS_GENERATE);
        spruceTrees.setTreeConditionChance(1);
        spruceTrees.setMaxY(100);

        TreeRTG ponderosaTree = new TreeRTGPinusPonderosa();
        ponderosaTree.setLogBlock(Blocks.LOG.getDefaultState());
        ponderosaTree.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        ponderosaTree.setMinTrunkSize(11);
        ponderosaTree.setMaxTrunkSize(21);
        ponderosaTree.setMinCrownSize(15);
        ponderosaTree.setMaxCrownSize(29);
        this.addTree(ponderosaTree);

        DecoTree oakPines = new DecoTree(ponderosaTree);
        oakPines.setStrengthNoiseFactorXForLoops(true);
        oakPines.setStrengthFactorForLoops(4f);
        oakPines.setTreeType(TreeType.RTG_TREE);
        oakPines.setTreeCondition(TreeCondition.ALWAYS_GENERATE);
        oakPines.setTreeConditionChance(1);
        oakPines.setMaxY(100);

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{spruceTrees, smallPines, oakPines};
        decoHelperRandomSplit.chances = new int[]{8, 2, 2};
        this.addDeco(decoHelperRandomSplit);
    }
}
