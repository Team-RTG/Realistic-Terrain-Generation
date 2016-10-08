package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.common.block.BlockBOPLog;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPConiferousForest;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPConiferousForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPConiferousForest;

public class RealisticBiomeBOPConiferousForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.coniferous_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPConiferousForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPConiferousForest(58f, 84f, 24f),
            new SurfaceBOPConiferousForest(config,
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

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = BlockBOPLog.paging.getVariantState(BOPWoods.FIR);
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPConiferousForest.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 12;
        decoBaseBiomeDecorations.loops = 1;
        this.addDeco(decoBaseBiomeDecorations);
    }
}
