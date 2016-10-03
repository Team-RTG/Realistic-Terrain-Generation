package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungleEdgeM;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungleEdgeM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungleEdgeM;

public class RealisticBiomeVanillaJungleEdgeM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_JUNGLE_EDGE;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaJungleEdgeM(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaJungleEdgeM(),
            new SurfaceVanillaJungleEdgeM(config, biome.topBlock, biome.fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 6;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaJungleEdgeM.decorationLogsId));
    }
}
