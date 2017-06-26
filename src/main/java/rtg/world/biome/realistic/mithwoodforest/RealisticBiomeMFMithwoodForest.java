package rtg.world.biome.realistic.mithwoodforest;

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
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusNigra;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPinusPonderosa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.RANDOM_CHANCE;

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
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);

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
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld.simplex());

            float h = terrainPlains(x, y, rtgWorld.simplex(), river, 160f, 10f, 60f, 80f, 65f);

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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
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
        megaMithwood.setLogBlock(mithwoodLogBlock);
        megaMithwood.setLeavesBlock(mithwoodLeavesBlock);
        megaMithwood.setMinTrunkSize(18);
        megaMithwood.setMaxTrunkSize(27);
        megaMithwood.setMinCrownSize(7);
        megaMithwood.setMaxCrownSize(10);
        this.addTree(megaMithwood);

        DecoTree RTGMithwood = new DecoTree(megaMithwood);
        RTGMithwood.setStrengthFactorForLoops(1f);
        RTGMithwood.setStrengthNoiseFactorXForLoops(true);
        RTGMithwood.getDistribution().setNoiseDivisor(100f);
        RTGMithwood.getDistribution().setNoiseFactor(6f);
        RTGMithwood.getDistribution().setNoiseAddend(0.8f);
        RTGMithwood.setTreeType(DecoTree.TreeType.RTG_TREE);
        RTGMithwood.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        RTGMithwood.setTreeConditionChance(12);
        RTGMithwood.setMaxY(100);
        this.addDeco(RTGMithwood);

        TreeRTG megaOak = new TreeRTGPinusNigra();
        megaOak.setLogBlock(Blocks.LOG.getDefaultState());
        megaOak.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        megaOak.setMinTrunkSize(18);
        megaOak.setMaxTrunkSize(27);
        megaOak.setMinCrownSize(7);
        megaOak.setMaxCrownSize(10);
        this.addTree(megaOak);

        DecoTree RTGOak = new DecoTree(megaOak);
        RTGOak.setStrengthFactorForLoops(4f);
        RTGOak.setStrengthNoiseFactorXForLoops(true);
        RTGOak.getDistribution().setNoiseDivisor(100f);
        RTGOak.getDistribution().setNoiseFactor(6f);
        RTGOak.getDistribution().setNoiseAddend(0.8f);
        RTGOak.setTreeType(DecoTree.TreeType.RTG_TREE);
        RTGOak.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        RTGOak.setTreeConditionChance(12);
        RTGOak.setMaxY(100);
        this.addDeco(RTGOak);

        TreeRTG megaBirch = new TreeRTGBetulaPapyrifera();
        megaBirch.setLogBlock(BlockUtil.getStateLog(2));
        megaBirch.setLeavesBlock(BlockUtil.getStateLeaf(2));
        megaBirch.setMinTrunkSize(4);
        megaBirch.setMaxTrunkSize(10);
        megaBirch.setMinCrownSize(8);
        megaBirch.setMaxCrownSize(19);
        this.addTree(megaBirch);

        DecoTree RTGBirch = new DecoTree(megaBirch);
        RTGBirch.setStrengthFactorForLoops(2f);
        RTGBirch.setTreeType(DecoTree.TreeType.RTG_TREE);
        RTGBirch.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        RTGBirch.setTreeConditionChance(24);
        RTGBirch.setMaxY(100);
        this.addDeco(RTGBirch);

        TreeRTG megaSpruce = new TreeRTGPinusPonderosa();
        megaSpruce.setLogBlock(BlockUtil.getStateLog(1));
        megaSpruce.setLeavesBlock(BlockUtil.getStateLeaf(1));
        megaSpruce.setMinTrunkSize(11);
        megaSpruce.setMaxTrunkSize(21);
        megaSpruce.setMinCrownSize(15);
        megaSpruce.setMaxCrownSize(29);
        this.addTree(megaSpruce);

        DecoTree RTGSpruce = new DecoTree(megaSpruce);
        RTGSpruce.setStrengthFactorForLoops(2f);
        RTGSpruce.setStrengthNoiseFactorForLoops(true);
        RTGSpruce.setTreeType(DecoTree.TreeType.RTG_TREE);
        RTGSpruce.getDistribution().setNoiseDivisor(80f);
        RTGSpruce.getDistribution().setNoiseFactor(60f);
        RTGSpruce.getDistribution().setNoiseAddend(-15f);
        RTGSpruce.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        RTGSpruce.setTreeConditionNoise(0f);
        RTGSpruce.setTreeConditionChance(24);
        RTGSpruce.setMaxY(140);
        this.addDeco(RTGSpruce);

        DecoShrub decoShrubMithwood = new DecoShrub();
        decoShrubMithwood.setLogBlock(mithwoodLogBlock);
        decoShrubMithwood.setLeavesBlock(mithwoodLeavesBlock);
        decoShrubMithwood.setMaxY(100);
        decoShrubMithwood.setStrengthFactor(1f);
        decoShrubMithwood.setChance(2);
        this.addDeco(decoShrubMithwood);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setMaxY(100);
        decoShrub.setStrengthFactor(2f);
        decoShrub.setChance(4);
        this.addDeco(decoShrub);

        DecoFlowersRTG decoFlowers1 = new DecoFlowersRTG();
        decoFlowers1.setFlowers(new int[]{1, 2, 3, 6, 7, 8});
        decoFlowers1.setStrengthFactor(2f);
        decoFlowers1.setHeightType(DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE);
        this.addDeco(decoFlowers1);

        DecoFlowersRTG decoFlowers2 = new DecoFlowersRTG();
        decoFlowers2.setFlowers(new int[]{11, 12, 13, 14});
        decoFlowers2.setStrengthFactor(1f);
        decoFlowers2.setChance(1);
        decoFlowers2.setHeightType(DecoFlowersRTG.HeightType.GET_HEIGHT_VALUE);
        this.addDeco(decoFlowers2);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setChance(2);
        decoBoulder.setMaxY(100);
        decoBoulder.setStrengthFactor(2f);
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setMaxY(100);
        decoBaseBiomeDecorations.setNotEqualsZeroChance(8);
        this.addDeco(decoBaseBiomeDecorations);

        DecoFallenTree decoFallenMithwoodTree = new DecoFallenTree();
        decoFallenMithwoodTree.getDistribution().setNoiseDivisor(100f);
        decoFallenMithwoodTree.getDistribution().setNoiseFactor(6f);
        decoFallenMithwoodTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenMithwoodTree.setLogCondition(DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenMithwoodTree.setLogConditionNoise(0f);
        decoFallenMithwoodTree.setLogConditionChance(24);
        decoFallenMithwoodTree.setLogBlock(mithwoodLogBlock);
        decoFallenMithwoodTree.setLeavesBlock(mithwoodLeavesBlock);
        decoFallenMithwoodTree.setMinSize(3);
        decoFallenMithwoodTree.setMaxSize(6);
        this.addDeco(decoFallenMithwoodTree, this.getConfig().ALLOW_LOGS.get());

        DecoFallenTree decoFallenOak = new DecoFallenTree();
        decoFallenOak.setLogCondition(RANDOM_CHANCE);
        decoFallenOak.setLogConditionChance(8);
        decoFallenOak.setMaxY(100);
        decoFallenOak.setLogBlock(Blocks.LOG.getDefaultState());
        decoFallenOak.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        decoFallenOak.setMinSize(3);
        decoFallenOak.setMaxSize(6);
        this.addDeco(decoFallenOak, this.getConfig().ALLOW_LOGS.get());

        DecoFallenTree decoFallenBirch = new DecoFallenTree();
        decoFallenBirch.setLogCondition(RANDOM_CHANCE);
        decoFallenBirch.setLogConditionChance(8);
        decoFallenBirch.setMaxY(100);
        decoFallenBirch.setLogBlock(BlockUtil.getStateLog(2));
        decoFallenBirch.setLeavesBlock(BlockUtil.getStateLeaf(2));
        decoFallenBirch.setMinSize(3);
        decoFallenBirch.setMaxSize(6);

        DecoFallenTree decoFallenSpruce = new DecoFallenTree();
        decoFallenSpruce.setLogCondition(RANDOM_CHANCE);
        decoFallenSpruce.setLogConditionChance(8);
        decoFallenSpruce.setMaxY(100);
        decoFallenSpruce.setLogBlock(BlockUtil.getStateLog(1));
        decoFallenSpruce.setLeavesBlock(BlockUtil.getStateLeaf(1));
        decoFallenSpruce.setMinSize(3);
        decoFallenSpruce.setMaxSize(6);
        DecoHelper5050 decoFallenTree = new DecoHelper5050(decoFallenBirch, decoFallenSpruce);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(24f);
        this.addDeco(decoGrass);
    }
}
