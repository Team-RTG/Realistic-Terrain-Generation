package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPBayou;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPBayou extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.bayou.get();
    public static Biome river = Biomes.RIVER;

    private static IBlockState mudBlock = BOPBlocks.mud.getDefaultState();
    private static IBlockState logBlock = BOPBlocks.log_2.getStateFromMeta(5);

    private static IBlockState leavesBlock = BOPBlocks.leaves_4.getStateFromMeta(3)
        .withProperty(BlockLeaves.CHECK_DECAY, false)
        .withProperty(BlockLeaves.DECAYABLE, false);

    public RealisticBiomeBOPBayou(BiomeConfig config) {

        super(config, biome, river);

        this.waterSurfaceLakeChance = 0; // We want RTG ponds, not Mojang lakes.

        DecoPond decoPond = new DecoPond();
        decoPond.chunksPerPond = 1;
        decoPond.maxY = 67;
        decoPond.loops = 8;
        this.addDeco(decoPond);

//        TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
//        myrtilloidesTree.logBlock = logBlock;
//        myrtilloidesTree.leavesBlock = leavesBlock;
//        myrtilloidesTree.validGroundBlocks.add(mudBlock);
//        this.addTree(myrtilloidesTree);
//        DecoTree decoTrees = new DecoTree(myrtilloidesTree);
//        decoTrees.treeType = DecoTree.TreeType.RTG_TREE;
//        decoTrees.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
//        decoTrees.treeConditionChance = 4;
//        decoTrees.logBlock = logBlock;
//        decoTrees.leavesBlock = leavesBlock;
//        decoTrees.maxY = 90;
//        this.addDeco(decoTrees);

        /*
         * STOP! Don't add anymore trees! BOP seems to generate a batch of its trees every time RTG generates a batch
         * of its trees, even though we're not calling the BOP Bayou's decorate() method.
         */

//        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
//        roseaTree.logBlock = logBlock;
//        roseaTree.leavesBlock = leavesBlock;
//        roseaTree.validGroundBlocks.add(mudBlock);
//        roseaTree.minTrunkSize = 2;
//        roseaTree.maxTrunkSize = 3;
//        roseaTree.minCrownSize = 10;
//        roseaTree.maxCrownSize = 18;
//        roseaTree.noLeaves = false;
//        this.addTree(roseaTree);
//        DecoTree ceibaRoseaTree = new DecoTree(roseaTree);
//        ceibaRoseaTree.treeType = DecoTree.TreeType.RTG_TREE;
//        ceibaRoseaTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
//        ceibaRoseaTree.treeConditionChance = 4;
//        ceibaRoseaTree.maxY = 90;
//        ceibaRoseaTree.scatter = new DecoTree.Scatter(16, 0);
//        this.addDeco(ceibaRoseaTree);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 4;
        this.addDeco(decoBaseBiomeDecorations);

        // Shrubs to fill in the blanks.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.maxY = 90;
        decoShrubOak.strengthFactor = 4f;
        decoShrubOak.chance = 3;
        this.addDeco(decoShrubOak);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = -0.2f;
        decoFallenTree.logConditionChance = 4;
        decoFallenTree.logBlock = logBlock;
        decoFallenTree.leavesBlock = leavesBlock;
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPBayou.decorationLogsId));

        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.maxY = 90;
        decoGrassDoubleTallgrass.strengthFactor = 4f;
        decoGrassDoubleTallgrass.doubleGrassChance = 8;
        this.addDeco(decoGrassDoubleTallgrass);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 90;
        decoGrass.strengthFactor = 4f;
        decoGrass.chance = 2;
        this.addDeco(decoGrass);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBayou();
    }

    public class TerrainBOPBayou extends TerrainBase {

        public TerrainBOPBayou() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 80f, 1f, 40f, 20f, 62f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPBayou(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.10f);
    }

    public class SurfaceBOPBayou extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceBOPBayou(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                               float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mixBlock = this.getConfigBlock(config, BiomeConfigBOPBayou.surfaceMixBlockId,
                BiomeConfigBOPBayou.surfaceMixBlockMetaId,
                mix);
            mixHeight = mixSize;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            int cliff = 0;
            boolean m = false;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0) {

                        float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                        if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                            cliff = 1;
                        }
                        if (c > cCliff) {
                            cliff = 2;
                        }

                        if (cliff == 1) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, y, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (simplex.noise2(i / 12f, j / 12f) > mixHeight) {
                            primer.setBlockState(x, k, y, mixBlock);
                            m = true;
                        }
                        else {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
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
