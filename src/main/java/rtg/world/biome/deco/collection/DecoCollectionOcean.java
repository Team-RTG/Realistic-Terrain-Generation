package rtg.world.biome.deco.collection;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.util.BlockUtil;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelper5050;
import static rtg.world.biome.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionOcean extends DecoCollectionBase {

    private int maxY = 60;

    public DecoCollectionOcean(boolean fallenTrees) {

        this.addDeco(logDecos(), fallenTrees) // Logs.
            .addDeco(shrubDecos()) // Shrubs.
            .addDeco(boulderDecos()) // Mossy & non-mossy boulders.
            .addDeco(sponge()) // Rare, wet sponge (only in deeper waters).
            .addDeco(flowers()) // Flowers.
            .addDeco(doublePlants()) // Ferns & double tall grass.
            .addDeco(grass()) // Grass.
            .addDeco(baseBiomeDecorations()); // Base biome decorations.
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
        decoFallenTree.setMaxY(maxY);

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
        decoShrub.setMaxY(maxY);
        return decoShrub;
    }

    private DecoHelper5050 shrubDecos() {
        return new DecoHelper5050(
            shrubs(Blocks.LOG.getDefaultState(), Blocks.LEAVES.getDefaultState()),
            shrubs(BlockUtil.getStateLog(1), BlockUtil.getStateLeaf(1))
        );
    }

    private DecoHelper5050 boulderDecos() {
        return new DecoHelper5050(
            boulders(Blocks.COBBLESTONE.getDefaultState(), 4),
            boulders(Blocks.MOSSY_COBBLESTONE.getDefaultState(), 4)
        );
    }

    private DecoBoulder boulders(IBlockState boulderBlock, int chance) {
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(boulderBlock);
        decoBoulder.setChance(chance);
        decoBoulder.setMinY(22);
        decoBoulder.setMaxY(58);
        decoBoulder.setHeightType(DecoBoulder.HeightType.NEXT_INT);
        decoBoulder.setStrengthFactor(4f);
        return decoBoulder;
    }

    private DecoFlowersRTG flowers() {
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.setFlowers(new int[]{12, 12, 12, 12});
        decoFlowersRTG.setMaxY(maxY);
        decoFlowersRTG.setLoops(3);
        return decoFlowersRTG;
    }

    private DecoLargeFernDoubleTallgrass doublePlants() {
        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.setMaxY(maxY);
        decoDoublePlants.fernChance = 3;
        decoDoublePlants.setLoops(15);
        return decoDoublePlants;
    }

    private DecoGrass grass() {
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(maxY);
        decoGrass.setStrengthFactor(4f);
        return decoGrass;
    }

    private DecoBaseBiomeDecorations baseBiomeDecorations() {
        return new DecoBaseBiomeDecorations();
    }

    private DecoBoulder sponge() {
        DecoBoulder decoBoulder = new DecoBoulder();
        //decoBoulder.setBoulderBlock(BlockUtil.getSponge(1));
        decoBoulder.setBoulderBlock(Blocks.REEDS.getDefaultState());
        decoBoulder.setChance(4);
        decoBoulder.setMinY(22);
        decoBoulder.setMaxY(32);
        decoBoulder.setHeightType(DecoBoulder.HeightType.NEXT_INT);
        decoBoulder.setStrengthFactor(4f);
        return decoBoulder;
    }
}
