package rtg.api.world.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.*;
import rtg.api.world.deco.DecoFallenTree.LogCondition;
import rtg.api.world.deco.DecoTree.TreeCondition;
import rtg.api.world.deco.DecoTree.TreeType;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.deco.helper.DecoHelperRandomSplit;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaPungens;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionForest extends DecoCollectionBase {

    // Tends to return values between -3f to 5f, with some overflow.
    private DecoTree.Distribution forestDistribution = new DecoTree.Distribution(100f, 6f, 0.8f);

    private float short1Min = -3f;
    private float short1Max = -1f;
    private float tallMin = -1f;
    private float tallMax = 3f;
    private float short2Min = 3f;
    private float short2Max = 5f;

    public DecoCollectionForest(BiomeConfig config) {

        super(config);

        this
            .addDeco(tallTrees(tallMin, tallMax)) // Tall trees first.
            .addDeco(shortTrees(short1Min, short1Max)) // Short trees next.
            .addDeco(shortTrees(short2Min, short2Max)) // More short trees (on the other 'side' of the noise spectrum).
            .addDeco(randomTrees()) // More trees.
            .addDeco(logs(), config.ALLOW_LOGS.get()) // Add some fallen trees of the oak and spruce variety (50/50 distribution).
            .addDeco(shrubsOak()) // Shrubs to fill in the blanks.
            .addDeco(shrubsSpruce()) // Fewer spruce shrubs than oak.
            .addDeco(flowers()) // Only 1-block tall flowers so we can see the trees better.
            .addDeco(grass()) // Grass filler.
        ;
    }

    private DecoHelper5050 tallTrees(float noiseMin, float noiseMax) {
        return new DecoHelper5050(
            tallPineTrees(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState(), noiseMin, noiseMax),
            tallPineTrees(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1), noiseMin, noiseMax)
        );
    }

    private DecoTree tallPineTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

        TreeRTG pinusPonderosa = new TreeRTGPinusPonderosa();
        pinusPonderosa.setLogBlock(log);
        pinusPonderosa.setLeavesBlock(leaves);
        pinusPonderosa.setMinTrunkSize(11);
        pinusPonderosa.setMaxTrunkSize(21);
        pinusPonderosa.setMinCrownSize(15);
        pinusPonderosa.setMaxCrownSize(29);

        this.addTree(pinusPonderosa);

        return new DecoTree(pinusPonderosa)
            .setStrengthFactorForLoops(8f)
            .setTreeType(TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(noiseMin)
            .setTreeConditionNoise2(noiseMax)
            .setTreeConditionChance(1)
            .setMaxY(85);
    }

    private DecoHelper5050 shortTrees(float noiseMin, float noiseMax) {
        return new DecoHelper5050(
            shortPineTrees(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState(), noiseMin, noiseMax),
            shortPineTrees(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1), noiseMin, noiseMax)
        );
    }

    private DecoTree shortPineTrees(IBlockState log, IBlockState leaves, float noiseMin, float noiseMax) {

        TreeRTG piceaSitchensis = new TreeRTGPiceaSitchensis()
            .setLogBlock(log)
            .setLeavesBlock(leaves)
            .setMinTrunkSize(4)
            .setMaxTrunkSize(10)
            .setMinCrownSize(6)
            .setMaxCrownSize(14);

        this.addTree(piceaSitchensis);

        return new DecoTree(piceaSitchensis)
            .setStrengthFactorForLoops(6f)
            .setTreeType(TreeType.RTG_TREE)
            .setDistribution(forestDistribution)
            .setTreeCondition(TreeCondition.NOISE_BETWEEN_AND_RANDOM_CHANCE)
            .setTreeConditionNoise(noiseMin)
            .setTreeConditionNoise2(noiseMax)
            .setTreeConditionChance(1)
            .setMaxY(85);
    }

    private DecoHelperRandomSplit randomTrees() {
        return new DecoHelperRandomSplit()
            .setDecos(new DecoBase[]{randomPungensTrees(), randomVanillaTrees()})
            .setChances(new int[]{8, 4});
    }

    private DecoTree randomPungensTrees() {

        TreeRTG piceaPungens = new TreeRTGPiceaPungens()
            .setLogBlock(Blocks.LOG.getDefaultState())
            .setLeavesBlock(Blocks.LEAVES.getDefaultState())
            .setMinTrunkSize(2)
            .setMaxTrunkSize(4)
            .setMinCrownSize(5)
            .setMaxCrownSize(8);

        this.addTree(piceaPungens);

        return new DecoTree(piceaPungens)
            .setStrengthFactorForLoops(3f)
            .setTreeType(TreeType.RTG_TREE)
            .setTreeCondition(TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(5)
            .setMaxY(100);
    }

    private DecoTree randomVanillaTrees() {

        WorldGenerator worldGenTrees = new WorldGenTrees(false);

        return new DecoTree(worldGenTrees)
            .setStrengthFactorForLoops(3f)
            .setTreeType(TreeType.WORLDGEN)
            .setTreeCondition(TreeCondition.RANDOM_CHANCE)
            .setTreeConditionChance(3)
            .setMaxY(120);
    }

    private DecoHelper5050 logs() {
        return new DecoHelper5050(oakLogs(), spruceLogs());
    }

    private DecoFallenTree oakLogs() {
        return new DecoFallenTree()
            .setLogCondition(LogCondition.RANDOM_CHANCE)
            .setLogConditionChance(16)
            .setMaxY(80)
            .setLogBlock(Blocks.LOG.getDefaultState())
            .setLeavesBlock(Blocks.LEAVES.getDefaultState())
            .setMinSize(3)
            .setMaxSize(6);
    }

    private DecoFallenTree spruceLogs() {
        return new DecoFallenTree()
            .setLogCondition(LogCondition.RANDOM_CHANCE)
            .setLogConditionChance(24)
            .setMaxY(80)
            .setLogBlock(BlockUtil.getStateLog(1))
            .setLeavesBlock(BlockUtil.getStateLeaf(1))
            .setMinSize(3)
            .setMaxSize(6);
    }

    private DecoShrub shrubsOak() {
        return new DecoShrub()
            .setMaxY(140)
            .setStrengthFactor(4f)
            .setChance(3);
    }

    private DecoShrub shrubsSpruce() {
        return new DecoShrub()
            .setLogBlock(BlockUtil.getStateLog(1))
            .setLeavesBlock(BlockUtil.getStateLeaf(1))
            .setMaxY(140)
            .setStrengthFactor(4f)
            .setChance(9);
    }

    private DecoFlowersRTG flowers() {
        return new DecoFlowersRTG()
            .setFlowers(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
            .setMaxY(128)
            .setStrengthFactor(6f);
    }

    private DecoGrass grass() {
        return new DecoGrass()
            .setMinY(60)
            .setMaxY(128)
            .setLoops(8);
    }
}
