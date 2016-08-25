package rtg.world.biome.realistic.sugiforest;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.sugiforest.config.BiomeConfigSFSugiForest;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.sugiforest.SurfaceSFSugiForest;
import rtg.world.gen.terrain.sugiforest.TerrainSFSugiForest;

public class RealisticBiomeSFSugiForest extends RealisticBiomeSFBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState sugiLogBlock = Block.getBlockFromName("sugiforest:sugi_log").getDefaultState();
    private static IBlockState sugiLeavesBlock = Block.getBlockFromName("sugiforest:sugi_leaves").getDefaultState();

    public RealisticBiomeSFSugiForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainSFSugiForest(),
            new SurfaceSFSugiForest(config,
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

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = sugiLogBlock;
        decoFallenTree.leavesBlock = sugiLeavesBlock;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigSFSugiForest.decorationLogsId));

        DecoShrub decoShrubSugi = new DecoShrub();
        decoShrubSugi.logBlock = sugiLogBlock;
        decoShrubSugi.leavesBlock = sugiLeavesBlock;
        decoShrubSugi.maxY = 90;
        decoShrubSugi.strengthFactor = 4f;
        decoShrubSugi.chance = 8;
        this.addDeco(decoShrubSugi);

        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 90;
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 4;
        this.addDeco(decoShrubOak);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 24;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 105;
        decoBaseBiomeDecorations.notEqualsZeroChance = 8;
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.minY = 63;
        decoGrass.maxY = 100;
        decoGrass.loops = 1;
        this.addDeco(decoGrass);
    }
}
