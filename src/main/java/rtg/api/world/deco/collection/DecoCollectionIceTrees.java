package rtg.api.world.deco.collection;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import rtg.api.config.BiomeConfig;
import rtg.api.util.Distribution;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.DecoTree.TreeCondition;
import rtg.api.world.deco.DecoTree.TreeType;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.gen.feature.tree.rtg.IceSpikeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionIceTrees extends DecoCollectionBase {

    // Tends to return values between -3f to 5f, with some overflow.
    private Distribution forestDistribution = new Distribution(100f, 6f, 0.8f);

    private float short1Min = -3f;
    private float short1Max = -1f;
    private float tallMin = -1f;
    private float tallMax = 3f;
    private float short2Min = 3f;
    private float short2Max = 5f;

    public DecoCollectionIceTrees(BiomeConfig config) {

        super(config);

        this
            .addDeco(tallTrees(tallMin, tallMax)) // Tall trees first.
            .addDeco(shortTrees(short1Min, short1Max)) // Short trees next.
            .addDeco(shortTrees(short2Min, short2Max)) // More short trees (on the other 'side' of the noise spectrum).
            .addDeco(randomPungensTrees()) // More trees.
            .addDeco(shrubsIce()) // Shrubs to fill in the blanks.
        ;
    }

    private DecoHelper5050 tallTrees(float noiseMin, float noiseMax) {
        return new DecoHelper5050(
            tallIceTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.AIR.getDefaultState(), noiseMin, noiseMax),
            tallIceTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.AIR.getDefaultState(), noiseMin, noiseMax)
        );
    }

    private DecoTree tallIceTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

        TreeRTG iceSpike = new IceSpikeRTG();
        iceSpike.setLogBlock(log);
        iceSpike.setLeavesBlock(leaves);
        iceSpike.setMinTrunkSize(1);
        iceSpike.setMaxTrunkSize(1);
        iceSpike.setMinCrownSize(10);
        iceSpike.setMaxCrownSize(24);

        ArrayList<IBlockState> validBlocks = iceSpike.getValidGroundBlocks();
        validBlocks.add(Blocks.SNOW.getDefaultState());
        iceSpike.setValidGroundBlocks(validBlocks);

        this.addTree(iceSpike);

        return new DecoTree(iceSpike)
            .setStrengthFactorForLoops(1f)
            .setTreeType(TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(noiseMin)
            .setTreeConditionNoise2(noiseMax)
            .setTreeConditionChance(2)
            .setMaxY(85);
    }

    private DecoHelper5050 shortTrees(float noiseMin, float noiseMax) {
        return new DecoHelper5050(
            shortIceTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.AIR.getDefaultState(), noiseMin, noiseMax),
            shortIceTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.AIR.getDefaultState(), noiseMin, noiseMax)
        );
    }

    private DecoTree shortIceTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

        TreeRTG shortIceSpike = new IceSpikeRTG()
            .setLogBlock(log)
            .setLeavesBlock(leaves)
            .setMinTrunkSize(1)
            .setMaxTrunkSize(1)
            .setMinCrownSize(5)
            .setMaxCrownSize(13);

        ArrayList<IBlockState> validBlocks = shortIceSpike.getValidGroundBlocks();
        validBlocks.add(Blocks.SNOW.getDefaultState());
        shortIceSpike.setValidGroundBlocks(validBlocks);

        this.addTree(shortIceSpike);

        return new DecoTree(shortIceSpike)
            .setStrengthFactorForLoops(2f)
            .setTreeType(TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(noiseMin)
            .setTreeConditionNoise2(noiseMax)
            .setTreeConditionChance(2)
            .setMaxY(85);
    }

    private DecoTree randomPungensTrees() {

        TreeRTG iceBush = new IceSpikeRTG()
            .setLogBlock(Blocks.PACKED_ICE.getDefaultState())
            .setLeavesBlock(Blocks.AIR.getDefaultState())
            .setMinTrunkSize(1)
            .setMaxTrunkSize(1)
            .setMinCrownSize(5)
            .setMaxCrownSize(11);

        ArrayList<IBlockState> validBlocks = iceBush.getValidGroundBlocks();
        validBlocks.add(Blocks.SNOW.getDefaultState());
        iceBush.setValidGroundBlocks(validBlocks);

        this.addTree(iceBush);

        return new DecoTree(iceBush)
            .setStrengthFactorForLoops(1f)
            .setTreeType(TreeType.RTG_TREE)
            .setTreeCondition(TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(8)
            .setMaxY(100);
    }

    private DecoShrub shrubsIce() {
        return new DecoShrub()
            .setLogBlock(Blocks.PACKED_ICE.getDefaultState())
            .setLeavesBlock(Blocks.AIR.getDefaultState())
            .setMaxY(140)
            .setLoopMultiplier(4f)
            .setChance(3);
    }
}
