package rtg.world.biome.realistic.betteragriculture;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.betteragriculture.config.BiomeConfigBAFarmlandBiome;
import rtg.util.BlockUtil;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.betteragriculture.SurfaceBAFarmlandBiome;
import rtg.world.gen.terrain.betteragriculture.TerrainBAFarmlandBiome;


class RealisticBiomeBAFarmlandBiome extends rtg.world.biome.realistic.betteragriculture.RealisticBiomeBABase {

    public static Biome river = Biomes.RIVER;

    RealisticBiomeBAFarmlandBiome(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new TerrainBAFarmlandBiome(),
            new SurfaceBAFarmlandBiome(config,
                biome.topBlock, //Block top
                Blocks.DIRT.getDefaultState(), //Block filler,
                BlockUtil.getStateDirt(1), //IBlockState mixTop,
                Blocks.DIRT.getDefaultState(), //IBlockState mixFill,
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
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBAFarmlandBiome.decorationLogsId));

        DecoCrop decoWheat = new DecoCrop();
        decoWheat.type = 3;
        decoWheat.chance = 80;
        decoWheat.strengthFactor = 15f;
        decoWheat.maxY = 255;
        decoWheat.size = 30;//DO NOT PUT HIGHER THAN 30
        decoWheat.density = 600;
        decoWheat.height = 5;
        this.addDeco(decoWheat);

        DecoCrop decoBeet = new DecoCrop();
        decoBeet.type = 2;
        decoBeet.chance = 80;
        decoBeet.strengthFactor = 12f;
        decoBeet.maxY = 255;
        decoBeet.size = 30;//DO NOT PUT HIGHER THAN 30
        decoBeet.density = 500;
        decoBeet.height = 5;
        this.addDeco(decoBeet);

        DecoCrop decoCarrot = new DecoCrop();
        decoCarrot.type = 1;
        decoCarrot.chance = 80;
        decoCarrot.strengthFactor = 12f;
        decoCarrot.maxY = 255;
        decoCarrot.size = 30;//DO NOT PUT HIGHER THAN 30
        decoCarrot.density = 500;
        decoCarrot.height = 5;
        this.addDeco(decoCarrot);

        DecoCrop decoPotato = new DecoCrop();
        decoPotato.type = 0;
        decoPotato.chance = 80;
        decoPotato.strengthFactor = 12f;
        decoPotato.maxY = 255;
        decoPotato.size = 30;//DO NOT PUT HIGHER THAN 30
        decoPotato.density = 500;
        decoPotato.height = 5;
        this.addDeco(decoPotato);

        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 90;
        decoShrubOak.strengthFactor = 2f;
        decoShrubOak.chance = 4;
        this.addDeco(decoShrubOak);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = BlockUtil.getStateDirt(2);
        decoBoulder.chance = 24;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 4f;
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
