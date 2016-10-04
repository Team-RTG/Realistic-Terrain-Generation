package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaMegaSpruceTaiga;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMegaSpruceTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMegaSpruceTaiga;

public class RealisticBiomeVanillaMegaSpruceTaiga extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_REDWOOD_TAIGA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMegaSpruceTaiga(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaMegaSpruceTaiga(),
            new SurfaceVanillaMegaSpruceTaiga(config, biome.topBlock, biome.fillerBlock)
        );

        this.noLakes = true;

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
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigVanillaMegaSpruceTaiga.decorationLogsId));
    }
}
