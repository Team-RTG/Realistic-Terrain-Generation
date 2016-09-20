package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYGLushForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGLushForest;
import rtg.world.gen.terrain.biomesyougo.TerrainBYGLushForest;

public class RealisticBiomeBYGLushForest extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGLushForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBYGLushForest(),
            new SurfaceBYGLushForest(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getStateFromMeta(2), 0.15f)
        );

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = Blocks.LOG.getStateFromMeta(2);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBYGLushForest.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 100;
        this.addDeco(decoBaseBiomeDecorations);

        DecoShrub decoShrubBirch = new DecoShrub();
        decoShrubBirch.logBlock = Blocks.LOG.getStateFromMeta(2);
        decoShrubBirch.leavesBlock = Blocks.LEAVES.getStateFromMeta(2);
        decoShrubBirch.maxY = 90;
        decoShrubBirch.strengthFactor = 6f;
        decoShrubBirch.chance = 4;
        this.addDeco(decoShrubBirch);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 100;
        decoGrass.strengthFactor = 6f;
        this.addDeco(decoGrass);
    }
}
