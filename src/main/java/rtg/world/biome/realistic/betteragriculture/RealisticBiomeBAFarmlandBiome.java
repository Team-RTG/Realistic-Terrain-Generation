package rtg.world.biome.realistic.betteragriculture;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.*;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


class RealisticBiomeBAFarmlandBiome extends rtg.world.biome.realistic.betteragriculture.RealisticBiomeBABase {

    public static Biome river = Biomes.RIVER;

    RealisticBiomeBAFarmlandBiome(Biome biome) {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBAFarmlandBiome();
    }

    public class TerrainBAFarmlandBiome extends TerrainBase {

        private float baseHeight = 67f;
        private float peakyHillWavelength = 15f;
        private float peakyHillStrength = 5f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 20f;

        public TerrainBAFarmlandBiome() {

        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex());

            float h = terrainGrasslandHills(x, y, rtgWorld.simplex(), rtgWorld.cell(), river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

            return riverized(groundNoise + h, river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBAFarmlandBiome(config,
            this.baseBiome.topBlock, //Block top
            Blocks.DIRT.getDefaultState(), //Block filler,
            BlockUtil.getStateDirt(1), //IBlockState mixTop,
            Blocks.DIRT.getDefaultState(), //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceBAFarmlandBiome extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBAFarmlandBiome(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                                      float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = mixTop;
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;
            boolean mix = false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
                                * floSmallStrength > floMixHeight) {
                                primer.setBlockState(x, k, z, blockMixTop);

                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, z, blockMixFiller);
                            }
                            else {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(24);
        decoFallenTree.setLogBlock(Blocks.LOG.getDefaultState());
        decoFallenTree.setLeavesBlock(Blocks.LOG.getDefaultState());
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoCrop decoWheat = new DecoCrop();
        decoWheat.setType(3);
        decoWheat.setChance(80);
        decoWheat.setStrengthFactor(15f);
        decoWheat.setMaxY(255);
        decoWheat.setSize(30);//DO NOT PUT HIGHER THAN 30
        decoWheat.setDensity(600);
        decoWheat.setHeight(5);
        this.addDeco(decoWheat);

        DecoCrop decoBeet = new DecoCrop();
        decoBeet.setType(2);
        decoBeet.setChance(80);
        decoBeet.setStrengthFactor(12f);
        decoBeet.setMaxY(255);
        decoBeet.setSize(30);//DO NOT PUT HIGHER THAN 30
        decoBeet.setDensity(500);
        decoBeet.setHeight(5);
        this.addDeco(decoBeet);

        DecoCrop decoCarrot = new DecoCrop();
        decoCarrot.setType(1);
        decoCarrot.setChance(80);
        decoCarrot.setStrengthFactor(12f);
        decoCarrot.setMaxY(255);
        decoCarrot.setSize(30);//DO NOT PUT HIGHER THAN 30
        decoCarrot.setDensity(500);
        decoCarrot.setHeight(5);
        this.addDeco(decoCarrot);

        DecoCrop decoPotato = new DecoCrop();
        decoPotato.setType(0);
        decoPotato.setChance(80);
        decoPotato.setStrengthFactor(12f);
        decoPotato.setMaxY(255);
        decoPotato.setSize(30);//DO NOT PUT HIGHER THAN 30
        decoPotato.setDensity(500);
        decoPotato.setHeight(5);
        this.addDeco(decoPotato);

        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.setMaxY(90);
        decoShrubOak.setStrengthFactor(2f);
        decoShrubOak.setChance(4);
        this.addDeco(decoShrubOak);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(BlockUtil.getStateDirt(2));
        decoBoulder.setChance(24);
        decoBoulder.setMaxY(80);
        decoBoulder.setStrengthFactor(4f);
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setMaxY(105);
        decoBaseBiomeDecorations.setNotEqualsZeroChance(8);
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMinY(63);
        decoGrass.setMaxY(100);
        decoGrass.setLoops(1);
        this.addDeco(decoGrass);
    }
}
