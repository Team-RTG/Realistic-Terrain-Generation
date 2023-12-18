package rtg.world.biome.realistic.betteragriculture;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.*;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import java.util.Random;


public class RealisticBiomeBAFarmlandBiome extends RealisticBiomeBABase {

    public RealisticBiomeBAFarmlandBiome(Biome biome) {

        super(biome);
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

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBAFarmlandBiome(getConfig(), this.baseBiome().topBlock, //Block top
            Blocks.DIRT.getDefaultState(), //Block filler,
            BlockUtil.getStateDirt(DirtType.COARSE_DIRT), //IBlockState mixTop,
            Blocks.DIRT.getDefaultState(), //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    @Override
    public void initDecos() {

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
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
        decoShrubOak.setLoopMultiplier(2f);
        decoShrubOak.setChance(4);
        this.addDeco(decoShrubOak);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(BlockUtil.getStateDirt(DirtType.PODZOL));
        decoBoulder.setChance(24);
        decoBoulder.setMaxY(80);
        decoBoulder.setStrengthFactor(4f);
        this.addDeco(decoBoulder);
    }

    public static class TerrainBAFarmlandBiome extends TerrainBase {

        private float baseHeight = 67f;
        private float peakyHillWavelength = 15f;
        private float peakyHillStrength = 5f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 20f;

        public TerrainBAFarmlandBiome() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld);

            float h = terrainGrasslandHills(x, y, rtgWorld, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

            return riverized(groundNoise + h, river);
        }
    }

    public static class SurfaceBAFarmlandBiome extends SurfaceBase {


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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = TerrainBase.calcCliff(x, z, noise, river);
            boolean cliff = c > 1.4f;
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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            if (simplex.noise2f(i / floMixWidth, j / floMixWidth) + simplex.noise2f(i / floSmallWidth, j / floSmallWidth)
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
}
