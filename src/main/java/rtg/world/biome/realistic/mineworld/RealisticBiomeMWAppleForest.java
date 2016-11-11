package rtg.world.biome.realistic.mineworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.mineworld.BiomeConfigMWAppleForest;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.mineworld.SurfaceMWAppleForest;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMWAppleForest extends RealisticBiomeMWBase {

    public static Biome river = Biomes.RIVER;
    private static IBlockState mwLogBlock = BlockUtil.getBlock("mw:logs").getDefaultState();

    public RealisticBiomeMWAppleForest(Biome biome, BiomeConfig config) {

        super(config, biome, river,
            new SurfaceMWAppleForest(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                BlockUtil.getStateDirt(2), //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth,
                -0.15f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
            )
        );

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.strengthFactor = 2f;
        decoBoulder.chance = 24;
        decoBoulder.maxY = 95;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.distribution.noiseDivisor = 100f;
        decoFallenTree1.distribution.noiseFactor = 6f;
        decoFallenTree1.distribution.noiseAddend = 0.8f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree1.logConditionNoise = 0f;
        decoFallenTree1.logConditionChance = 16;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = mwLogBlock;
        decoFallenTree1.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 5;
        this.addDeco(decoFallenTree1, this.config._boolean(BiomeConfigMWAppleForest.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 120;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 4;
        this.addDeco(decoBaseBiomeDecorations);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainMWAppleForest(58f, 76f, 18f);
    }

    public class TerrainMWAppleForest extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;

        public TerrainMWAppleForest(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 0f);
        }
    }
}
