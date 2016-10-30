package rtg.world.biome.realistic.mineworld;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.mineworld.BiomeConfigMWDeadForest;
import rtg.util.BlockUtil;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.surface.mineworld.SurfaceMWDeadForest;
import rtg.world.gen.terrain.mineworld.TerrainMWDeadForest;

public class RealisticBiomeMWDeadForest extends RealisticBiomeMWBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMWDeadForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainMWDeadForest(58f, 80f, 30f),
            new SurfaceMWDeadForest(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 24;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.loops = 1;
        decoFallenTree1.distribution.noiseDivisor = 100f;
        decoFallenTree1.distribution.noiseFactor = 6f;
        decoFallenTree1.distribution.noiseAddend = 0.8f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree1.logConditionNoise = 0f;
        decoFallenTree1.logConditionChance = 10;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = BlockUtil.getStateLog(1);
        decoFallenTree1.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 5;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.loops = 1;
        decoFallenTree2.distribution.noiseDivisor = 100f;
        decoFallenTree2.distribution.noiseFactor = 6f;
        decoFallenTree2.distribution.noiseAddend = 0.8f;
        decoFallenTree2.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree2.logConditionNoise = 0f;
        decoFallenTree2.logConditionChance = 10;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = BlockUtil.getStateLog(3);
        decoFallenTree2.leavesBlock = BlockUtil.getStateLeaf(3);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 5;

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{decoFallenTree2, decoFallenTree1};
        decoHelperRandomSplit.chances = new int[]{12, 8};
        this.addDeco(decoHelperRandomSplit, this.config._boolean(BiomeConfigMWDeadForest.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
