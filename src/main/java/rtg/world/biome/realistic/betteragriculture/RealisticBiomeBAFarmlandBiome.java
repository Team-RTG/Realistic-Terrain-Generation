package rtg.world.biome.realistic.betteragriculture;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;


class RealisticBiomeBAFarmlandBiome extends rtg.world.biome.realistic.betteragriculture.RealisticBiomeBABase {

    public static Biome river = Biomes.RIVER;

    RealisticBiomeBAFarmlandBiome(Biome biome) {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
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
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

            float h = terrainGrasslandHills(x, y, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            boolean cliff = c > 1.4f ? true : false;
            boolean mix = false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
                                * floSmallStrength > floMixHeight) {
                                primer.setBlockState(x, k, y, blockMixTop);

                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, y, blockMixFiller);
                            }
                            else {
                                primer.setBlockState(x, k, y, fillerBlock);
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
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

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
