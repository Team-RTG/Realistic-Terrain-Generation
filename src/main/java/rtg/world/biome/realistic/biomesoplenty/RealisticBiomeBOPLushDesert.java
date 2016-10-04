package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushDesert;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoJungleCacti;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushDesert;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPLushDesert;

public class RealisticBiomeBOPLushDesert extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.lush_desert.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPLushDesert(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPLushDesert(65f, 40f, 10f),
            new SurfaceBOPLushDesert(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                40f, //float mixWidth, 
                -0.15f, //float mixHeight,
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 12;
        decoFallenTree.randomLogBlocks = new IBlockState[]{Blocks.LOG2.getDefaultState().withProperty(BlockNewLog.VARIANT, BlockPlanks.EnumType.DARK_OAK), BOPBlocks.log_3.getStateFromMeta(2), Blocks.LOG.getDefaultState()};
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPLushDesert.decorationLogsId));

        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.strengthFactor = 8f;
        decoJungleCacti.maxY = 110;
        this.addDeco(decoJungleCacti);
    }
}
