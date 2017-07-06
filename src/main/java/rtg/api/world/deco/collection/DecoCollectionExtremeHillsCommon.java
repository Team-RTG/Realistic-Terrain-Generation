package rtg.api.world.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.*;
import rtg.api.world.deco.helper.DecoHelper5050;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionExtremeHillsCommon extends DecoCollectionBase {

    public DecoCollectionExtremeHillsCommon(BiomeConfig config) {

        super(config);

        this.addDeco(logDecos(), config.ALLOW_LOGS.get()) // Logs.
            .addDeco(shrubDecos()) // Shrubs.
            .addDeco(boulders()) // Boulders.
            .addDeco(flowers()) // Flowers.
            .addDeco(mushrooms()) // Mushrooms.
            .addDeco(pumpkins()) // Pumpkins.
            .addDeco(doublePlants()) // Ferns & double tall grass.
            .addDeco(grass()); // Grass.
    }

    private DecoFallenTree logs(IBlockState log, IBlockState leaves) {

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(16);
        decoFallenTree.setLogBlock(log);
        decoFallenTree.setLeavesBlock(leaves);
        decoFallenTree.setMinSize(4);
        decoFallenTree.setMaxSize(7);
        decoFallenTree.config().MAX_Y.set(75);

        return decoFallenTree;
    }

    private DecoHelper5050 logDecos() {
        return new DecoHelper5050(
            logs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            logs(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1))
        );
    }

    private DecoShrub shrubs(IBlockState log, IBlockState leaves) {
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setLogBlock(log);
        decoShrub.setLeavesBlock(leaves);
        decoShrub.setStrengthFactor(4f);
        decoShrub.setChance(2);
        decoShrub.config().MAX_Y.set(110);
        return decoShrub;
    }

    private DecoHelper5050 shrubDecos() {
        return new DecoHelper5050(
            shrubs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            shrubs(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1))
        );
    }

    private DecoBoulder boulders() {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState());
        decoBoulder.setChance(12);
        decoBoulder.config().MAX_Y.set(90);
        decoBoulder.setStrengthFactor(2f);
        return decoBoulder;
    }

    private DecoFlowersRTG flowers() {
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.setFlowers(new int[]{9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2});
        decoFlowersRTG.config().MAX_Y.set(80);
        decoFlowersRTG.config().LOOPS.set(3);
        return decoFlowersRTG;
    }

    private DecoMushrooms mushrooms() {
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.config().MAX_Y.set(70);
        decoMushrooms.setRandomType(DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH);
        decoMushrooms.setRandomFloat(3f);
        return decoMushrooms;
    }

    private DecoPumpkin pumpkins() {
        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.config().MAX_Y.set(90);
        decoPumpkin.setRandomType(DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH);
        decoPumpkin.setRandomFloat(30f);
        return decoPumpkin;
    }

    private DecoLargeFernDoubleTallgrass doublePlants() {
        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.config().MAX_Y.set(128);
        decoDoublePlants.fernChance = 3;
        decoDoublePlants.config().LOOPS.set(15);
        return decoDoublePlants;
    }

    private DecoGrass grass() {
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.config().MAX_Y.set(128);
        decoGrass.setStrengthFactor(10f);
        return decoGrass;
    }
}
