package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesyougo.config.BiomeConfigBYGBirchPlains;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.biomesyougo.SurfaceBYGBirchPlains;
import rtg.world.gen.terrain.biomesyougo.TerrainBYGBirchPlains;

public class RealisticBiomeBYGBirchPlains extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGBirchPlains(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBYGBirchPlains(),
            new SurfaceBYGBirchPlains(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 0.15f)
        );

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBYGBirchPlains.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 100;
        this.addDeco(decoBaseBiomeDecorations);

        DecoShrub decoShrubBirch = new DecoShrub();
        decoShrubBirch.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.BIRCH);
        decoShrubBirch.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.BIRCH);
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
