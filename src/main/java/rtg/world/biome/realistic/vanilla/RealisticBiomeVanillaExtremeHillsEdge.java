package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHillsEdge;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsEdge;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsEdge;

public class RealisticBiomeVanillaExtremeHillsEdge extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = BiomeGenBase.extremeHillsEdge.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.extremeHillsEdge.fillerBlock;

    public RealisticBiomeVanillaExtremeHillsEdge(BiomeConfig config) {

        super(config,
            BiomeGenBase.extremeHillsEdge,
            BiomeGenBase.river,
            new TerrainVanillaExtremeHillsEdge(10f, 60f, 68f, 200f),
            new SurfaceVanillaExtremeHillsEdge(config, topBlock, fillerBlock, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), 60f, -0.14f, 14f, 0.25f)
        );

        this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;

        TreeRTG nigraTree = new TreeRTGPinusNigra();
        nigraTree.setLogBlock(Blocks.log.getDefaultState());
        nigraTree.setLeavesBlock(Blocks.leaves.getDefaultState());
        nigraTree.setMinTrunkSize(18);
        nigraTree.setMaxTrunkSize(27);
        nigraTree.setMinCrownSize(7);
        nigraTree.setMaxCrownSize(10);
        this.addTree(nigraTree);

        DecoTree decoTrees = new DecoTree(nigraTree);
        decoTrees.strengthFactorForLoops = 4f;
        decoTrees.strengthNoiseFactorXForLoops = true;
        decoTrees.distribution.noiseDivisor = 100f;
        decoTrees.distribution.noiseFactor = 6f;
        decoTrees.distribution.noiseAddend = 0.8f;
        decoTrees.treeType = DecoTree.TreeType.RTG_TREE;
        decoTrees.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        decoTrees.treeConditionChance = 24;
        decoTrees.maxY = 100;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.logBlock = Blocks.log.getStateFromMeta(1);
        decoFallenTree.leavesBlock = Blocks.leaves.getStateFromMeta(1);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaExtremeHillsEdge.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.mossy_cobblestone.getDefaultState();
        decoBoulder.chance = 12;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.maxY = 90;
        decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
        decoPumpkin.randomFloat = 20f;
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
