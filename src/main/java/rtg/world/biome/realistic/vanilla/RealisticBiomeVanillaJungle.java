package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import static rtg.world.biome.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;

public class RealisticBiomeVanillaJungle extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.JUNGLE;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaJungle() {

        super(biome, river);

        this.waterSurfaceLakeChance = 3;
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);

        this.getConfig().ALLOW_VOLCANOES.set(true);
        this.getConfig().VOLCANO_CHANCE.set(rtgConfig.VOLCANO_CHANCE.get() * 2);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaJungle();
    }

    public class TerrainVanillaJungle extends TerrainBase {

        public TerrainVanillaJungle() {

        }

        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, rtgWorld.simplex, river, 3f, 66f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaJungle(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.09f);
    }

    public class SurfaceVanillaJungle extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceVanillaJungle(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
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

        // Blend of the WorldGenMegaJungle collection and some tall RTG Mangrove trees.

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(4, 5, 13f, 0.32f, 0.2f);
        mucronataTree.setLogBlock(BlockUtil.getStateLog(3));
        mucronataTree.setLeavesBlock(BlockUtil.getStateLeaf(3));
        mucronataTree.setMinTrunkSize(3);
        mucronataTree.setMaxTrunkSize(4);
        mucronataTree.setMinCrownSize(10);
        mucronataTree.setMaxCrownSize(27);
        this.addTree(mucronataTree);

        DecoTree mangroves = new DecoTree(new TreeRTGRhizophoraMucronata(4, 5, 13f, 0.32f, 0.2f));
        mangroves.setLoops(3);
        mangroves.setTreeType(DecoTree.TreeType.RTG_TREE);
        mangroves.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        mangroves.setTreeConditionChance(2);
        mangroves.setMaxY(160);

        DecoTree megaJungle = new DecoTree(new WorldGenMegaJungle(false, 10, 27, BlockUtil.getStateLog(3), BlockUtil.getStateLeaf(3)));
        megaJungle.setLogBlock(BlockUtil.getStateLog(3));
        megaJungle.setLeavesBlock(BlockUtil.getStateLeaf(3));
        megaJungle.setMinTrunkSize(3);
        megaJungle.setMaxTrunkSize(4);
        megaJungle.setMinCrownSize(10);
        megaJungle.setMaxCrownSize(27);
        megaJungle.setLoops(3);
        megaJungle.setTreeType(DecoTree.TreeType.WORLDGEN);
        megaJungle.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        megaJungle.setTreeConditionChance(2);
        megaJungle.setMaxY(160);

        DecoHelperThisOrThat decoHelperThisOrThat = new DecoHelperThisOrThat(3, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, megaJungle, mangroves);
        this.addDeco(decoHelperThisOrThat);

        // Add some palm trees for variety.

        TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
        nuciferaTree.setMinTrunkSize(7);
        nuciferaTree.setMaxTrunkSize(9);
        nuciferaTree.setMinCrownSize(6);
        nuciferaTree.setMaxCrownSize(8);
        this.addTree(nuciferaTree);

        DecoTree palmCustom = new DecoTree(nuciferaTree);
        palmCustom.setLoops(1);
        palmCustom.setTreeType(DecoTree.TreeType.RTG_TREE);
        palmCustom.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        palmCustom.setTreeConditionChance(3);
        palmCustom.setMaxY(160);
        this.addDeco(palmCustom);

        // Another pass of the WorldGenMegaJungle collection for extra jungleness.
        this.addDeco(decoHelperThisOrThat);

        // Jungle logs.
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.setLoops(1);
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(5f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(3);
        decoFallenTree.setLogBlock(BlockUtil.getStateLog(3));
        decoFallenTree.setLeavesBlock(BlockUtil.getStateLeaf(3));
        decoFallenTree.setMinSize(4);
        decoFallenTree.setMaxSize(9);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        // At this point, let's hand over some of the decoration to the base biome, but only about 85% of the time.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setNotEqualsZeroChance(6);
        decoBaseBiomeDecorations.setLoops(1);
        this.addDeco(decoBaseBiomeDecorations);

        // A combo-deal of lilypads and vines. (This could probably be pulled out into individual decos.)
        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);

        // A combo-deal of grass and vines. (This could probably be pulled out into individual decos.)
        DecoJungleGrassVines decoJungleGrassVines = new DecoJungleGrassVines();
        this.addDeco(decoJungleGrassVines);

        // Flowers.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.setFlowers(new int[]{5}); // Only orange tulips fit in with the colour scheme.
        decoFlowersRTG.setChance(4);
        decoFlowersRTG.setMaxY(120);
        decoFlowersRTG.setStrengthFactor(2f);
        this.addDeco(decoFlowersRTG);

        // Tall cacti on red sand - matches the colour scheme nicely.
        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.setStrengthFactor(8f);
        decoJungleCacti.setMaxY(120);
        decoJungleCacti.setSandOnly(false);
        decoJungleCacti.setExtraHeight(7);
        decoJungleCacti.setSandMeta((byte) 1);
        this.addDeco(decoJungleCacti, this.getConfig().ALLOW_CACTUS.get());

        // Mossy boulders for the green.
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.MOSSY_COBBLESTONE.getDefaultState());
        decoBoulder.setChance(16);
        decoBoulder.setMaxY(95);
        decoBoulder.setStrengthFactor(2f);
        this.addDeco(decoBoulder);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(128);
        decoGrass.setStrengthFactor(12f);
        this.addDeco(decoGrass);
    }
}
