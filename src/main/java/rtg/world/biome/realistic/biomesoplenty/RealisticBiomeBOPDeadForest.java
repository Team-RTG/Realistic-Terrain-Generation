package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPDeadForest;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPDeadForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPDeadForest;

public class RealisticBiomeBOPDeadForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.dead_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPDeadForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPDeadForest(58f, 80f, 30f),
            new SurfaceBOPDeadForest(config,
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
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 1f;
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
        decoFallenTree1.logBlock = BOPBlocks.log_3.getStateFromMeta(2);
        decoFallenTree1.leavesBlock = Blocks.LEAVES.getDefaultState();
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
        decoFallenTree2.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
        decoFallenTree2.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockNewLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 5;

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{decoFallenTree2, decoFallenTree1};
        decoHelperRandomSplit.chances = new int[]{12, 1};
        this.addDeco(decoHelperRandomSplit, this.config._boolean(BiomeConfigBOPDeadForest.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
