package rtg.api.world.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusNigra;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionExtremeHillsPlusM extends DecoCollectionExtremeHillsPlus {

    public DecoCollectionExtremeHillsPlusM(BiomeConfig config) {

        super(config);

        this.addDeco(nigraDecos(85, 14, 20, 10, 14)) // Lower, taller trees.
            .addDeco(nigraDecos(95, 10, 14, 8, 10)); // Higher, shorter trees.
    }

    @Override
    protected DecoTree nigraTrees(IBlockState log, IBlockState leaves, int maxY, int minTrunkSize, int maxTrunkSize, int minCrownSize, int maxCrownSize) {

        TreeRTG nigraTree = new TreeRTGPinusNigra();
        nigraTree.setLogBlock(log);
        nigraTree.setLeavesBlock(leaves);
        nigraTree.setMinTrunkSize(minTrunkSize);
        nigraTree.setMaxTrunkSize(maxTrunkSize);
        nigraTree.setMinCrownSize(minCrownSize);
        nigraTree.setMaxCrownSize(maxCrownSize);

        this.addTree(nigraTree);

        return new DecoTree(nigraTree)
            .setLoops(3)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(4)
            .setMaxY(maxY);
    }

    @Override
    protected DecoHelper5050 nigraDecos(int maxY, int minTrunkSize, int maxTrunkSize, int minCrownSize, int maxCrownSize) {
        return new DecoHelper5050(
            nigraTrees(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState(), maxY, minTrunkSize, maxTrunkSize, minCrownSize, maxCrownSize),
            nigraTrees(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1), maxY, minTrunkSize, maxTrunkSize, minCrownSize, maxCrownSize)
        );
    }
}
