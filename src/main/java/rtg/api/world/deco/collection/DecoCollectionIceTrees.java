package rtg.api.world.deco.collection;

import java.util.ArrayList;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.config.BiomeConfig;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.DecoTree.TreeCondition;
import rtg.api.world.deco.DecoTree.TreeType;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaPungens;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionIceTrees extends DecoCollectionBase {

    // Tends to return values between -3f to 5f, with some overflow.
    private DecoTree.Distribution forestDistribution = new DecoTree.Distribution(100f, 6f, 0.8f);

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
            tallPineTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), noiseMin, noiseMax),
            tallPineTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), noiseMin, noiseMax)
        );
    }

    private DecoTree tallPineTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

        TreeRTG pinusPonderosa = new TreeRTGPinusPonderosa();
        pinusPonderosa.setLogBlock(log);
        pinusPonderosa.setLeavesBlock(leaves);
        pinusPonderosa.setMinTrunkSize(8);
        pinusPonderosa.setMaxTrunkSize(21);
        pinusPonderosa.setMinCrownSize(13);
        pinusPonderosa.setMaxCrownSize(29);

        ArrayList<IBlockState> validBlocks = pinusPonderosa.getValidGroundBlocks();
        validBlocks.add(Blocks.SNOW.getDefaultState());
        pinusPonderosa.setValidGroundBlocks(validBlocks);

        this.addTree(pinusPonderosa);

        return new DecoTree(pinusPonderosa)
            .setStrengthFactorForLoops(4f)
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
            shortPineTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), noiseMin, noiseMax),
            shortPineTrees(Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), noiseMin, noiseMax)
        );
    }

    private DecoTree shortPineTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

        TreeRTG piceaSitchensis = new TreeRTGPiceaSitchensis()
            .setLogBlock(log)
            .setLeavesBlock(leaves)
            .setMinTrunkSize(4)
            .setMaxTrunkSize(8)
            .setMinCrownSize(4)
            .setMaxCrownSize(14);

        ArrayList<IBlockState> validBlocks = piceaSitchensis.getValidGroundBlocks();
        validBlocks.add(Blocks.SNOW.getDefaultState());
        piceaSitchensis.setValidGroundBlocks(validBlocks);

        this.addTree(piceaSitchensis);

        return new DecoTree(piceaSitchensis)
            .setStrengthFactorForLoops(4f)
            .setTreeType(TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(noiseMin)
            .setTreeConditionNoise2(noiseMax)
            .setTreeConditionChance(2)
            .setMaxY(85);
    }

    private DecoTree randomPungensTrees() {

        TreeRTG piceaPungens = new TreeRTGPiceaPungens()
            .setLogBlock(Blocks.PACKED_ICE.getDefaultState())
            .setLeavesBlock(Blocks.ICE.getDefaultState())
            .setMinTrunkSize(2)
            .setMaxTrunkSize(4)
            .setMinCrownSize(4)
            .setMaxCrownSize(8);

        ArrayList<IBlockState> validBlocks = piceaPungens.getValidGroundBlocks();
        validBlocks.add(Blocks.SNOW.getDefaultState());
        piceaPungens.setValidGroundBlocks(validBlocks);

        this.addTree(piceaPungens);

        return new DecoTree(piceaPungens)
            .setStrengthFactorForLoops(3f)
            .setTreeType(TreeType.RTG_TREE)
            .setTreeCondition(TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(8)
            .setMaxY(100);
    }

    private DecoShrub shrubsIce() {
        return new DecoShrub()
            .setLogBlock(Blocks.PACKED_ICE.getDefaultState())
            .setLeavesBlock(Blocks.ICE.getDefaultState())
            .setMaxY(140)
            .setStrengthFactor(4f)
            .setChance(3);
    }
}
