package rtg.world.biome.realistic.morechinesemc;

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
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMCMLoessPlateau extends RealisticBiomeMCMBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeMCMLoessPlateau(Biome biome) {

        super(biome, river);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainMCMLoessPlateau(true, 35f, 160f, 60f, 40f, 69f);
    }

    public class TerrainMCMLoessPlateau extends TerrainBase {

        private boolean booRiver;
        private float[] height;
        private int heightLength;
        private float strength;
        private float cWidth;
        private float cHeigth;
        private float cStrength;
        private float base;

        /*
         * Example parameters:
         *
         * allowed to generate rivers?
         * riverGen = true
         *
         * canyon jump heights
         * heightArray = new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}
         *
         * strength of canyon jump heights
         * heightStrength = 35f
         *
         * canyon width (cliff to cliff)
         * canyonWidth = 160f
         *
         * canyon heigth (total heigth)
         * canyonHeight = 60f
         *
         * canyon strength
         * canyonStrength = 40f
         *
         */
        public TerrainMCMLoessPlateau(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {

            booRiver = riverGen;
            height = new float[]{5.0f, 0.5f, 12.5f, 0.5f, 18.0f, 0.5f};
            strength = heightStrength;
            heightLength = height.length;
            cWidth = canyonWidth;
            cHeigth = canyonHeight;
            cStrength = canyonStrength;
            base = baseHeight;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainCanyon(x, y, simplex, river, height, border, strength, heightLength, booRiver);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceMCMLoessPlateau(config,
            this.baseBiome.topBlock, //Block top
            this.baseBiome.fillerBlock, //Block filler,
            this.baseBiome.topBlock, //IBlockState mixTop,
            this.baseBiome.fillerBlock, //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceMCMLoessPlateau extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        private IBlockState grassBlock = Block.getBlockFromName("morechinesemc:grass_black").getDefaultState();

        public SurfaceMCMLoessPlateau(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
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

                                primer.setBlockState(x, k, y, topBlock);
                            }
                            else {

                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, y, topBlock);
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
        decoFallenTree.logBlock = BlockUtil.getStateLog(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.logBlock = BlockUtil.getStateLog(1);
        decoShrub.leavesBlock = BlockUtil.getStateLeaf(1);
        decoShrub.maxY = 90;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 12;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 30;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
