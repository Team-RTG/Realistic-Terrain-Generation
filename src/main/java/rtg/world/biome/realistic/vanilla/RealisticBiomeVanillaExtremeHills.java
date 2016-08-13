package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHills;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHills;

public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.EXTREME_HILLS.topBlock;
    public static IBlockState fillerBlock = Biomes.EXTREME_HILLS.fillerBlock;

    public RealisticBiomeVanillaExtremeHills(BiomeConfig config) {

        super(config,
            Biomes.EXTREME_HILLS,
            Biomes.RIVER,
            new TerrainVanillaExtremeHills(10f, 120f, 10f, 200f),
            new SurfaceVanillaExtremeHills(config, topBlock, fillerBlock, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 60f, -0.14f, 14f, 0.25f)
        );

        this.generatesEmeralds = true;
        this.noLakes = true;
        this.noWaterFeatures = true;

        TreeRTG nigraTree = new TreeRTGPinusNigra();
        nigraTree.logBlock = Blocks.LOG.getDefaultState();
        nigraTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        nigraTree.minTrunkSize = 18;
        nigraTree.maxTrunkSize = 27;
        nigraTree.minCrownSize = 7;
        nigraTree.maxCrownSize = 10;
        this.addTree(nigraTree);

        DecoTree decoTrees = new DecoTree(nigraTree);
        decoTrees.strengthFactorForLoops = 4f;
        decoTrees.strengthNoiseFactorXForLoops = true;
        decoTrees.distribution.noiseDivisor = 100f;
        decoTrees.distribution.noiseFactor = 6f;
        decoTrees.distribution.noiseAddend = 0.8f;
        decoTrees.treeType = TreeType.RTG_TREE;
        decoTrees.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        decoTrees.treeConditionChance = 24;
        decoTrees.maxY = 100;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 7;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaExtremeHills.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 4;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder.chance = 12;
        decoBoulder.maxY = 90;
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
        decoPumpkin.randomFloat = 30f;
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
