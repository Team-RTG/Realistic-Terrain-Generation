package rtg.world.biome.realistic.mithwoodforest;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import rtg.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMFMithwoodForest extends RealisticBiomeMFBase {

    public static Biome river = Biomes.RIVER;

    private static IBlockState mithwoodLogBlock = Block.getBlockFromName("mithwoodforest:mithwood_log").getDefaultState();
    private static IBlockState mithwoodLeavesBlock = Block.getBlockFromName("mithwoodforest:mithwood_leaves").getDefaultState();

    public RealisticBiomeMFMithwoodForest(Biome biome) {

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

        return new TerrainMFMithwoodForest();
    }

    public class TerrainMFMithwoodForest extends TerrainBase {

        public TerrainMFMithwoodForest() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex);

            float h = terrainPlains(x, y, rtgWorld.simplex, river, 160f, 10f, 60f, 80f, 65f);

            return riverized(groundNoise + h, river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceMFMithwoodForest(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.10f);
    }

    public class SurfaceMFMithwoodForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceMFMithwoodForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;
            float c = CliffCalculator.calc(x, z, noise);
            int cliff = 0;
            boolean m = false;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
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

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (simplex.noise2(i / 12f, j / 12f) > mixHeight) {
                            primer.setBlockState(x, k, z, mixBlock);
                            m = true;
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        TreeRTG megaMithwood = new TreeRTGPinusNigra();
        megaMithwood.logBlock = mithwoodLogBlock;
        megaMithwood.leavesBlock = mithwoodLeavesBlock;
        megaMithwood.minTrunkSize = 18;
        megaMithwood.maxTrunkSize = 27;
        megaMithwood.minCrownSize = 7;
        megaMithwood.maxCrownSize = 10;
        this.addTree(megaMithwood);

        DecoTree RTGMithwood = new DecoTree(megaMithwood);
        RTGMithwood.strengthFactorForLoops = 1f;
        RTGMithwood.strengthNoiseFactorXForLoops = true;
        RTGMithwood.distribution.noiseDivisor = 100f;
        RTGMithwood.distribution.noiseFactor = 6f;
        RTGMithwood.distribution.noiseAddend = 0.8f;
        RTGMithwood.treeType = DecoTree.TreeType.RTG_TREE;
        RTGMithwood.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGMithwood.treeConditionChance = 12;
        RTGMithwood.maxY = 100;
        this.addDeco(RTGMithwood);

        TreeRTG megaOak = new TreeRTGPinusNigra();
        megaOak.logBlock = Blocks.LOG.getDefaultState();
        megaOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        megaOak.minTrunkSize = 18;
        megaOak.maxTrunkSize = 27;
        megaOak.minCrownSize = 7;
        megaOak.maxCrownSize = 10;
        this.addTree(megaOak);

        DecoTree RTGOak = new DecoTree(megaOak);
        RTGOak.strengthFactorForLoops = 4f;
        RTGOak.strengthNoiseFactorXForLoops = true;
        RTGOak.distribution.noiseDivisor = 100f;
        RTGOak.distribution.noiseFactor = 6f;
        RTGOak.distribution.noiseAddend = 0.8f;
        RTGOak.treeType = DecoTree.TreeType.RTG_TREE;
        RTGOak.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGOak.treeConditionChance = 12;
        RTGOak.maxY = 100;
        this.addDeco(RTGOak);

        TreeRTG megaBirch = new TreeRTGBetulaPapyrifera();
        megaBirch.logBlock = BlockUtil.getStateLog(2);
        megaBirch.leavesBlock = BlockUtil.getStateLeaf(2);
        megaBirch.minTrunkSize = 4;
        megaBirch.maxTrunkSize = 10;
        megaBirch.minCrownSize = 8;
        megaBirch.maxCrownSize = 19;
        this.addTree(megaBirch);

        DecoTree RTGBirch = new DecoTree(megaBirch);
        RTGBirch.strengthFactorForLoops = 2f;
        RTGBirch.treeType = DecoTree.TreeType.RTG_TREE;
        RTGBirch.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGBirch.treeConditionChance = 24;
        RTGBirch.maxY = 100;
        this.addDeco(RTGBirch);

        TreeRTG megaSpruce = new TreeRTGPinusPonderosa();
        megaSpruce.logBlock = BlockUtil.getStateLog(1);
        megaSpruce.leavesBlock = BlockUtil.getStateLeaf(1);
        megaSpruce.minTrunkSize = 11;
        megaSpruce.maxTrunkSize = 21;
        megaSpruce.minCrownSize = 15;
        megaSpruce.maxCrownSize = 29;
        this.addTree(megaSpruce);

        DecoTree RTGSpruce = new DecoTree(megaSpruce);
        RTGSpruce.strengthFactorForLoops = 2f;
        RTGSpruce.strengthNoiseFactorForLoops = true;
        RTGSpruce.treeType = DecoTree.TreeType.RTG_TREE;
        RTGSpruce.distribution.noiseDivisor = 80f;
        RTGSpruce.distribution.noiseFactor = 60f;
        RTGSpruce.distribution.noiseAddend = -15f;
        RTGSpruce.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        RTGSpruce.treeConditionNoise = 0f;
        RTGSpruce.treeConditionChance = 24;
        RTGSpruce.maxY = 140;
        this.addDeco(RTGSpruce);

        DecoShrub decoShrubMithwood = new DecoShrub();
        decoShrubMithwood.logBlock = mithwoodLogBlock;
        decoShrubMithwood.leavesBlock = mithwoodLeavesBlock;
        decoShrubMithwood.maxY = 100;
        decoShrubMithwood.strengthFactor = 1f;
        decoShrubMithwood.chance = 2;
        this.addDeco(decoShrubMithwood);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 100;
        decoShrub.strengthFactor = 2f;
        decoShrub.chance = 4;
        this.addDeco(decoShrub);

        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
        decoFlowers1.flowers = new int[]{1, 2, 3, 6, 7, 8};
        decoFlowers1.strengthFactor = 2f;
        decoFlowers1.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE;
        this.addDeco(decoFlowers1);

        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
        decoFlowers2.flowers = new int[]{11, 12, 13, 14};
        decoFlowers2.strengthFactor = 1f;
        decoFlowers2.chance = 1;
        decoFlowers2.heightType = DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE;
        this.addDeco(decoFlowers2);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.chance = 2;
        decoBoulder.maxY = 100;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.maxY = 100;
        decoBaseBiomeDecorations.notEqualsZeroChance = 8;
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenMithwoodTree = new DecoFallenTree();
        decoFallenMithwoodTree.distribution.noiseDivisor = 100f;
        decoFallenMithwoodTree.distribution.noiseFactor = 6f;
        decoFallenMithwoodTree.distribution.noiseAddend = 0.8f;
        decoFallenMithwoodTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenMithwoodTree.logConditionNoise = 0f;
        decoFallenMithwoodTree.logConditionChance = 24;
        decoFallenMithwoodTree.logBlock = mithwoodLogBlock;
        decoFallenMithwoodTree.leavesBlock = mithwoodLeavesBlock;
        decoFallenMithwoodTree.minSize = 3;
        decoFallenMithwoodTree.maxSize = 6;
        this.addDeco(decoFallenMithwoodTree, this.getConfig().ALLOW_LOGS.get());

        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenOak.logConditionChance = 8;
        decoFallenOak.maxY = 100;
        decoFallenOak.logBlock = Blocks.LOG.getDefaultState();
        decoFallenOak.leavesBlock = Blocks.LEAVES.getDefaultState();
        decoFallenOak.minSize = 3;
        decoFallenOak.maxSize = 6;
        this.addDeco(decoFallenOak, this.getConfig().ALLOW_LOGS.get());

        DecoFallenTree decoFallenBirch = new DecoFallenTree();
        decoFallenBirch.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenBirch.logConditionChance = 8;
        decoFallenBirch.maxY = 100;
        decoFallenBirch.logBlock = BlockUtil.getStateLog(2);
        decoFallenBirch.leavesBlock = BlockUtil.getStateLeaf(2);
        decoFallenBirch.minSize = 3;
        decoFallenBirch.maxSize = 6;

        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenSpruce.logConditionChance = 8;
        decoFallenSpruce.maxY = 100;
        decoFallenSpruce.logBlock = BlockUtil.getStateLog(1);
        decoFallenSpruce.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenSpruce.minSize = 3;
        decoFallenSpruce.maxSize = 6;
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenBirch, decoFallenSpruce);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 24f;
        this.addDeco(decoGrass);
    }
}
