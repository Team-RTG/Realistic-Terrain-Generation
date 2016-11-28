package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.EXTREME_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaExtremeHills() {

        super(biome, river);

        this.generatesEmeralds = true;
        this.generatesSilverfish = true;
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_FILLER_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_FILLER_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaExtremeHills(10f, 120f, 10f, 200f);
    }

    public class TerrainVanillaExtremeHills extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainVanillaExtremeHills(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, start, width, height, base);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaExtremeHills(config, biome.topBlock, biome.fillerBlock, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 60f, -0.14f, 14f, 0.25f);
    }

    public class SurfaceVanillaExtremeHills extends SurfaceBase {

        private IBlockState mixBlockTop;
        private IBlockState mixBlockFill;
        private float width;
        private float height;
        private float smallW;
        private float smallS;

        public SurfaceVanillaExtremeHills(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, float mixWidth,
                                          float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            mixBlockTop = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), mixTop);
            mixBlockFill = this.getConfigBlock(config.SURFACE_MIX_FILLER_BLOCK.get(), config.SURFACE_MIX_FILLER_BLOCK_META.get(), mixFill);

            width = mixWidth;
            height = mixHeight;
            smallW = smallWidth;
            smallS = smallStrength;
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
                            if (simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height) {
                                primer.setBlockState(x, k, y, mixBlockTop);
                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, y, mixBlockFill);
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

        TreeRTG nigraTree = new TreeRTGPinusNigra();
        nigraTree.logBlock = Blocks.LOG.getDefaultState();
        nigraTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        nigraTree.minTrunkSize = 18;
        nigraTree.maxTrunkSize = 27;
        nigraTree.minCrownSize = 7;
        nigraTree.maxCrownSize = 10;
        this.addTree(nigraTree);

        DecoTree decoTrees = new DecoTree(nigraTree);
        decoTrees.strengthFactorForLoops = 4f;
        decoTrees.strengthNoiseFactorXForLoops = true;
        decoTrees.distribution.noiseDivisor = 100f;
        decoTrees.distribution.noiseFactor = 6f;
        decoTrees.distribution.noiseAddend = 0.8f;
        decoTrees.treeType = DecoTree.TreeType.RTG_TREE;
        decoTrees.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        decoTrees.treeConditionChance = 24;
        decoTrees.maxY = 100;
        this.addDeco(decoTrees);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 7;
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 4;
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder.chance = 12;
        decoBoulder.maxY = 90;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);

        DecoPumpkin decoPumpkin = new DecoPumpkin();
        decoPumpkin.maxY = 90;
        decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
        decoPumpkin.randomFloat = 30f;
        this.addDeco(decoPumpkin);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
