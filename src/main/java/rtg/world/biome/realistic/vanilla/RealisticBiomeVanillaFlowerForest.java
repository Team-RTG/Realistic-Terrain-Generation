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
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.collection.DecoCollectionSmallPineTreesForest;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaFlowerForest extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaFlowerForest() {

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

        return new TerrainVanillaFlowerForest();
    }

    public class TerrainVanillaFlowerForest extends TerrainBase {

        public TerrainVanillaFlowerForest() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaFlowerForest(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, Blocks.GRASS.getDefaultState(), 0.05f);
    }

    public class SurfaceVanillaFlowerForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceVanillaFlowerForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                          float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), mix);
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

    @Override
    public void initDecos() {

        // First, let's get a few shrubs in to break things up a bit.
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 4f;
        decoShrub.chance = 3;
        this.addDeco(decoShrub);

        // Flowers are the most aesthetically important feature of this biome, so let's add those next.
        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
        decoFlowers1.flowers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}; //Only colourful 1-block-tall flowers.
        decoFlowers1.strengthFactor = 12f; // Lots and lots of flowers!
        decoFlowers1.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers1);

        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
        decoFlowers2.flowers = new int[]{10, 11, 14, 15}; //Only 2-block-tall flowers.
        decoFlowers2.strengthFactor = 2f; // Not as many of these.
        decoFlowers2.chance = 3;
        decoFlowers2.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE; // We're only bothered about surface flowers here.
        this.addDeco(decoFlowers2);

        // Trees first.

        TreeRTG ponderosaOakTree = new TreeRTGPinusPonderosa();
        ponderosaOakTree.logBlock = Blocks.LOG.getDefaultState();
        ponderosaOakTree.leavesBlock = Blocks.LEAVES.getDefaultState();
        ponderosaOakTree.minTrunkSize = 11;
        ponderosaOakTree.maxTrunkSize = 21;
        ponderosaOakTree.minCrownSize = 15;
        ponderosaOakTree.maxCrownSize = 29;
        this.addTree(ponderosaOakTree);

        DecoTree oakPines = new DecoTree(ponderosaOakTree);
        oakPines.strengthNoiseFactorForLoops = true;
        oakPines.treeType = DecoTree.TreeType.RTG_TREE;
        oakPines.distribution.noiseDivisor = 80f;
        oakPines.distribution.noiseFactor = 60f;
        oakPines.distribution.noiseAddend = -15f;
        oakPines.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        oakPines.treeConditionNoise = 0f;
        oakPines.treeConditionChance = 1;
        oakPines.maxY = 140;

        TreeRTG ponderosaSpruceTree = new TreeRTGPinusPonderosa();
        ponderosaSpruceTree.logBlock = BlockUtil.getStateLog(1);
        ponderosaSpruceTree.leavesBlock = BlockUtil.getStateLeaf(1);
        ponderosaSpruceTree.minTrunkSize = 11;
        ponderosaSpruceTree.maxTrunkSize = 21;
        ponderosaSpruceTree.minCrownSize = 15;
        ponderosaSpruceTree.maxCrownSize = 29;
        this.addTree(ponderosaSpruceTree);

        DecoTree sprucePines = new DecoTree(ponderosaSpruceTree);
        sprucePines.strengthNoiseFactorForLoops = true;
        sprucePines.treeType = DecoTree.TreeType.RTG_TREE;
        sprucePines.distribution.noiseDivisor = 80f;
        sprucePines.distribution.noiseFactor = 60f;
        sprucePines.distribution.noiseAddend = -15f;
        sprucePines.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        sprucePines.treeConditionNoise = 0f;
        sprucePines.treeConditionChance = 1;
        sprucePines.maxY = 140;

        DecoHelper5050 decoPines = new DecoHelper5050(oakPines, sprucePines);
        this.addDeco(decoPines);

        // More trees.
        this.addDecoCollection(new DecoCollectionSmallPineTreesForest());

        // Not much free space left, so let's give some space to the base biome.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 4;
        this.addDeco(decoBaseBiomeDecorations);

        // Add some fallen trees of the oak and spruce variety (50/50 distribution).
        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 8;
        decoFallenOak.maxY = 100;
        decoFallenOak.logBlock = Blocks.LOG.getDefaultState();
        decoFallenOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;
        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 8;
        decoFallenSpruce.maxY = 100;
        decoFallenSpruce.logBlock = BlockUtil.getStateLog(1);
        decoFallenSpruce.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenOak, decoFallenSpruce);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
    }
}
