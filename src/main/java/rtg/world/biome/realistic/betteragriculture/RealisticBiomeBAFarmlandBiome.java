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
        decoWheat.config().CROP_TYPE.set(3);
        decoWheat.config().CHANCE.set(80);
        decoWheat.config().STRENGTH_FACTOR.set(15f);
        decoWheat.config().MAX_Y.set(255);
        decoWheat.config().CROP_SIZE.set(30);//DO NOT PUT HIGHER THAN 30
        decoWheat.config().CROP_DENSITY.set(600);
        decoWheat.config().CROP_HEIGHT.set(5);
        this.addDeco(decoWheat);

        DecoCrop decoBeet = new DecoCrop();
        decoBeet.config().CROP_TYPE.set(2);
        decoBeet.config().CHANCE.set(80);
        decoBeet.config().STRENGTH_FACTOR.set(12f);
        decoBeet.config().MAX_Y.set(255);
        decoBeet.config().CROP_SIZE.set(30);//DO NOT PUT HIGHER THAN 30
        decoBeet.config().CROP_DENSITY.set(500);
        decoBeet.config().CROP_HEIGHT.set(5);
        this.addDeco(decoBeet);

        DecoCrop decoCarrot = new DecoCrop();
        decoCarrot.config().CROP_TYPE.set(1);
        decoCarrot.config().CHANCE.set(80);
        decoCarrot.config().STRENGTH_FACTOR.set(12f);
        decoCarrot.config().MAX_Y.set(255);
        decoCarrot.config().CROP_SIZE.set(30);//DO NOT PUT HIGHER THAN 30
        decoCarrot.config().CROP_DENSITY.set(500);
        decoCarrot.config().CROP_HEIGHT.set(5);
        this.addDeco(decoCarrot);

        DecoCrop decoPotato = new DecoCrop();
        decoPotato.config().CROP_TYPE.set(0);
        decoPotato.config().CHANCE.set(80);
        decoPotato.config().STRENGTH_FACTOR.set(12f);
        decoPotato.config().MAX_Y.set(255);
        decoPotato.config().CROP_SIZE.set(30);//DO NOT PUT HIGHER THAN 30
        decoPotato.config().CROP_DENSITY.set(500);
        decoPotato.config().CROP_HEIGHT.set(5);
        this.addDeco(decoPotato);

        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.config().MAX_Y.set(90);
        decoShrubOak.config().STRENGTH_FACTOR.set(2f);
        decoShrubOak.config().CHANCE.set(4);
        this.addDeco(decoShrubOak);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.config().BOULDER_BLOCK.set(BlockUtil.getStateDirt(2));
        decoBoulder.config().CHANCE.set(24);
        decoBoulder.config().MAX_Y.set(80);
        decoBoulder.config().STRENGTH_FACTOR.set(4f);
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.config().MAX_Y.set(105);
        decoBaseBiomeDecorations.config().NOT_EQUALS_ZERO_CHANCE.set(8);
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.config().MIN_Y.set(63);
        decoGrass.config().MAX_Y.set(100);
        decoGrass.config().LOOPS.set(1);
        this.addDeco(decoGrass);
    }
}
