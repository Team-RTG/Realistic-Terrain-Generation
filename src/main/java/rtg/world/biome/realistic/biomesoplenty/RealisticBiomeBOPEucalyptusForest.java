package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.*;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPEucalyptusForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPEucalyptusForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPEucalyptusForest;

public class RealisticBiomeBOPEucalyptusForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.eucalyptus_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPEucalyptusForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPEucalyptusForest(),//(58f, 80f, 36f),
            new SurfaceBOPEucalyptusForest(config,
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

        DecoBoulder decoBoulder1 = new DecoBoulder();
        decoBoulder1.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder1.maxY = 80;
        decoBoulder1.chance = 12;
        decoBoulder1.strengthFactor = 1f;
        DecoBoulder decoBoulder2 = new DecoBoulder();
        decoBoulder2.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder2.maxY = 80;
        decoBoulder2.chance = 12;
        decoBoulder2.strengthFactor = 1f;
        DecoHelper5050 decoHelper5050 = new DecoHelper5050(decoBoulder1, decoBoulder2);
        this.addDeco(decoHelper5050);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE);
        decoFallenTree.minSize = 8;
        decoFallenTree.maxSize = 14;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPEucalyptusForest.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
