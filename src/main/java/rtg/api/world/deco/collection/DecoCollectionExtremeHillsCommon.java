package rtg.api.world.deco.collection;

import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoFlowersRTG;
import rtg.api.world.deco.DecoGrass;
import rtg.api.world.deco.DecoLargeFernDoubleTallgrass;
import rtg.api.world.deco.DecoMushrooms;
import rtg.api.world.deco.DecoPumpkin;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.helper.DecoHelper5050;

import static net.minecraft.block.BlockFlower.EnumFlowerType.ALLIUM;
import static net.minecraft.block.BlockFlower.EnumFlowerType.DANDELION;
import static net.minecraft.block.BlockFlower.EnumFlowerType.HOUSTONIA;
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
        decoFallenTree.setMaxY(75);

        return decoFallenTree;
    }

    private DecoHelper5050 logDecos() {
        return new DecoHelper5050(
            logs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            logs(BlockUtil.getStateLog(EnumType.SPRUCE), BlockUtil.getStateLeaf(EnumType.SPRUCE))
        );
    }

    private DecoShrub shrubs(IBlockState log, IBlockState leaves) {
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setLogBlock(log);
        decoShrub.setLeavesBlock(leaves);
        decoShrub.setStrengthFactor(4f);
        decoShrub.setChance(2);
        decoShrub.setMaxY(110);
        return decoShrub;
    }

    private DecoHelper5050 shrubDecos() {
        return new DecoHelper5050(
            shrubs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            shrubs(BlockUtil.getStateLog(EnumType.SPRUCE), BlockUtil.getStateLeaf(EnumType.SPRUCE))
        );
    }

    private DecoBoulder boulders() {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState());
        decoBoulder.setChance(12);
        decoBoulder.setMaxY(90);
        decoBoulder.setStrengthFactor(2f);
        return decoBoulder;
    }

    private DecoFlowersRTG flowers() {
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
// TODO: [1.12] Original flowers list had dupes.. may need to increase flower generation here.
        decoFlowersRTG.addFlowers(ALLIUM, HOUSTONIA, DANDELION);
        decoFlowersRTG.setMaxY(80);
        decoFlowersRTG.setLoops(3);
        return decoFlowersRTG;
    }

    private DecoMushrooms mushrooms() {
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.setMaxY(70);
        decoMushrooms.setRandomType(DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH);
        decoMushrooms.setRandomFloat(3f);
        return decoMushrooms;
    }

    private DecoPumpkin pumpkins() {
        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.setMaxY(90);
        decoPumpkin.setRandomType(DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH);
        decoPumpkin.setRandomFloat(30f);
        return decoPumpkin;
    }

    private DecoLargeFernDoubleTallgrass doublePlants() {
        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.setMaxY(128);
        decoDoublePlants.fernChance = 3;
        decoDoublePlants.setLoops(15);
        return decoDoublePlants;
    }

    private DecoGrass grass() {
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(10f);
        return decoGrass;
    }
}
