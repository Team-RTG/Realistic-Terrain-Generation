package rtg.api.world.deco.collection;

import net.minecraft.block.state.IBlockState;

import rtg.api.config.BiomeConfig;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusNigra;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionExtremeHillsM extends DecoCollectionExtremeHills {

    public DecoCollectionExtremeHillsM(BiomeConfig config) {

        super(config);

        this.addDeco(nigraDecos(85, 10, 18, 10, 18)); // Taller trees lower down.
        this.addDeco(nigraDecos(95, 10, 12, 10, 12)); // Shorter trees both lower down & higher up.
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
            .setLoops(4)
            .setTreeType(DecoTree.TreeType.RTG_TREE)
            .setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(3)
            .setMaxY(maxY);
    }
}
