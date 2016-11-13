package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPEucalyptusForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.eucalyptus_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPEucalyptusForest() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, BiomeConfig.decorationLogsName, "", true));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPEucalyptusForest(); //(58f, 80f, 36f)
    }

    public class TerrainBOPEucalyptusForest extends TerrainBase {

        private float baseHeight = 76f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 20f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 10f;

        public TerrainBOPEucalyptusForest() {

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

        return new SurfaceBOPEucalyptusForest(config,
            biome.topBlock, //Block top
            biome.fillerBlock, //Block filler,
            biome.topBlock, //IBlockState mixTop,
            biome.fillerBlock, //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceBOPEucalyptusForest extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBOPEucalyptusForest(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
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
        decoFallenTree.logBlock = BlockUtil.getStateLog(3);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(3);
        decoFallenTree.minSize = 8;
        decoFallenTree.maxSize = 14;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfig.decorationLogsId));

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
