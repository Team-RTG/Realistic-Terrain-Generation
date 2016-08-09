package rtg.world.biome.realistic.abyssalcraft;

import net.minecraft.world.biome.BiomeGenBase;

import com.shinoow.abyssalcraft.api.block.ACBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.abyssalcraft.config.BiomeConfigACDarklandsForest;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.abyssalcraft.SurfaceACDarklandsForest;
import rtg.world.gen.terrain.abyssalcraft.TerrainACDarklandsForest;

public class RealisticBiomeACDarklandsForest extends RealisticBiomeACBase {

    public RealisticBiomeACDarklandsForest(BiomeGenBase acBiome, BiomeConfig config) {

        super(config,
            acBiome,
            BiomeGenBase.river,
            new TerrainACDarklandsForest(),
            new SurfaceACDarklandsForest(config, acBiome.topBlock, acBiome.fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f, acBiome.topBlock, 0.10f)
        );

        DecoAbyssalCraftTree decoTrees = new DecoAbyssalCraftTree();
        decoTrees.strengthNoiseFactorXForLoops = true;
        decoTrees.distribution.noiseDivisor = 80f;
        decoTrees.distribution.noiseFactor = 60f;
        decoTrees.distribution.noiseAddend = -15f;
        decoTrees.treeType = DecoAbyssalCraftTree.TreeType.DARKWOOD;
        decoTrees.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        decoTrees.treeConditionChance = 3;
        decoTrees.maxY = 110;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 12;
        decoFallenTree.logBlock = ACBlocks.darklands_oak_wood.getDefaultState();
        decoFallenTree.leavesBlock = ACBlocks.darklands_oak_leaves.getDefaultState();
        decoFallenTree.minSize = 2;
        decoFallenTree.maxSize = 3;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigACDarklandsForest.decorationLogsId));

        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.logBlock = ACBlocks.darklands_oak_wood.getDefaultState();
        decoShrubCustom.leavesBlock = ACBlocks.darklands_oak_leaves.getDefaultState();
        decoShrubCustom.maxY = 110;
        decoShrubCustom.notEqualsZerochance = 3;
        decoShrubCustom.strengthFactor = 3f;
        this.addDeco(decoShrubCustom);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 8f;
        this.addDeco(decoGrass);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
