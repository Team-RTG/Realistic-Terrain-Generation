package rtg.world.biome.realistic.biomesyougo;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.util.CliffCalculator;
import rtg.world.biome.deco.*;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGQuercusRobur;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGAutumnForest extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState cikaLogBlock = Block.getBlockFromName("BiomesYouGo:CikaLog").getDefaultState();
    private static IBlockState cikaLeavesBlock = Block.getBlockFromName("BiomesYouGo:CikaLeaves").getDefaultState();

    public RealisticBiomeBYGAutumnForest(Biome biome) {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGAutumnForest();
    }

    public class TerrainBYGAutumnForest extends TerrainBase {

        private float baseHeight = 72f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 10f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 20f;

        public TerrainBYGAutumnForest() {

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

        return new SurfaceBYGAutumnForest(config,
            this.baseBiome.topBlock, //Block top
            this.baseBiome.fillerBlock, //Block filler,
            BlockUtil.getStateDirt(2), //IBlockState mixTop,
            this.baseBiome.fillerBlock, //IBlockState mixFill,
            80f, //float mixWidth,
            0.35f, //float mixHeight,
            10f, //float smallWidth,
            0.65f //float smallStrength
        );
    }

    public class SurfaceBYGAutumnForest extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBYGAutumnForest(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                                      float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), mixTop);
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;
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
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 6f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 24;
        decoFallenTree.logBlock = Blocks.LOG.getDefaultState();
        decoFallenTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoShrub decoShrubCika = new DecoShrub();
        decoShrubCika.logBlock = cikaLogBlock;
        decoShrubCika.leavesBlock = cikaLeavesBlock;
        decoShrubCika.maxY = 90;
        decoShrubCika.strengthFactor = 4f;
        decoShrubCika.chance = 8;
        this.addDeco(decoShrubCika);

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

        TreeRTG quercusRoburOakTree = new TreeRTGQuercusRobur();
        quercusRoburOakTree.logBlock = Blocks.LOG.getDefaultState();
        quercusRoburOakTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        quercusRoburOakTree.minTrunkSize = 3;
        quercusRoburOakTree.maxTrunkSize = 6;
        quercusRoburOakTree.minCrownSize = 5;
        quercusRoburOakTree.maxCrownSize = 9;
        quercusRoburOakTree.validGroundBlocks.clear();
        quercusRoburOakTree.validGroundBlocks.add(BlockUtil.getStateDirt(2));
        this.addTree(quercusRoburOakTree);

        DecoTree bigOakTrees = new DecoTree(quercusRoburOakTree);
        bigOakTrees.strengthFactorForLoops = 2f;
        bigOakTrees.treeType = DecoTree.TreeType.RTG_TREE;
        bigOakTrees.distribution.noiseDivisor = 100f;
        bigOakTrees.distribution.noiseFactor = 6f;
        bigOakTrees.distribution.noiseAddend = 0.8f;
        bigOakTrees.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        bigOakTrees.treeConditionNoise = 0f;
        bigOakTrees.treeConditionChance = 6;
        bigOakTrees.maxY = 105;
        this.addDeco(bigOakTrees);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 105;
        decoBaseBiomeDecorations.notEqualsZeroChance = 8;
        this.addDeco(decoBaseBiomeDecorations);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.minY = 63;
        decoGrass.maxY = 105;
        decoGrass.loops = 1;
        this.addDeco(decoGrass);
    }
}
