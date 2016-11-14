package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

import rtg.config.BiomeConfig;
import rtg.config.BiomeConfigProperty;
import rtg.config.ConfigRTG;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCocosNucifera;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import static rtg.config.BiomeConfig.allowVolcanoesId;
import static rtg.config.BiomeConfig.volcanoChanceId;

public class RealisticBiomeVanillaJungle extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.JUNGLE;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaJungle() {

        super(biome, river);

        this.waterSurfaceLakeChance = 3;
    }

    @Override
    public void initConfig() {

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, BiomeConfig.decorationLogsName, "", true));
        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.decorationCactusId, BiomeConfigProperty.Type.BOOLEAN, BiomeConfig.decorationCactusName, "", true));

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixBlockId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockName, "", ""));
        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockMetaName, "", "0"));

        this.config.setPropertyValueById(allowVolcanoesId, true);
        this.config.setPropertyValueById(volcanoChanceId, (ConfigRTG.volcanoChance * 2));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaJungle();
    }

    public class TerrainVanillaJungle extends TerrainBase {

        public TerrainVanillaJungle() {

        }

        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, simplex, river, 3f, 66f);
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

            mixBlock = this.getConfigBlock(config, BiomeConfig.surfaceMixBlockId,
                BiomeConfig.surfaceMixBlockMetaId,
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

    @Override
    public void initDecos() {

        // Blend of the WorldGenMegaJungle collection and some tall RTG Mangrove trees.

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(4, 5, 13f, 0.32f, 0.2f);
        mucronataTree.logBlock = BlockUtil.getStateLog(3);
        mucronataTree.leavesBlock = BlockUtil.getStateLeaf(3);
        mucronataTree.minTrunkSize = 3;
        mucronataTree.maxTrunkSize = 4;
        mucronataTree.minCrownSize = 10;
        mucronataTree.maxCrownSize = 27;
        this.addTree(mucronataTree);

        DecoTree mangroves = new DecoTree(new TreeRTGRhizophoraMucronata(4, 5, 13f, 0.32f, 0.2f));
        mangroves.loops = 3;
        mangroves.treeType = DecoTree.TreeType.RTG_TREE;
        mangroves.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        mangroves.treeConditionChance = 2;
        mangroves.maxY = 160;

        DecoTree megaJungle = new DecoTree(new WorldGenMegaJungle(false, 10, 27, BlockUtil.getStateLog(3), BlockUtil.getStateLeaf(3)));
        megaJungle.logBlock = BlockUtil.getStateLog(3);
        megaJungle.leavesBlock = BlockUtil.getStateLeaf(3);
        megaJungle.minTrunkSize = 3;
        megaJungle.maxTrunkSize = 4;
        megaJungle.minCrownSize = 10;
        megaJungle.maxCrownSize = 27;
        megaJungle.loops = 3;
        megaJungle.treeType = DecoTree.TreeType.WORLDGEN;
        megaJungle.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        megaJungle.treeConditionChance = 2;
        megaJungle.maxY = 160;

        DecoHelperThisOrThat decoHelperThisOrThat = new DecoHelperThisOrThat(3, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, megaJungle, mangroves);
        this.addDeco(decoHelperThisOrThat);

        // Add some palm trees for variety.

        TreeRTG nuciferaTree = new TreeRTGCocosNucifera();
        nuciferaTree.minTrunkSize = 7;
        nuciferaTree.maxTrunkSize = 9;
        nuciferaTree.minCrownSize = 6;
        nuciferaTree.maxCrownSize = 8;
        this.addTree(nuciferaTree);

        DecoTree palmCustom = new DecoTree(nuciferaTree);
        palmCustom.loops = 1;
        palmCustom.treeType = DecoTree.TreeType.RTG_TREE;
        palmCustom.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        palmCustom.treeConditionChance = 3;
        palmCustom.maxY = 160;
        this.addDeco(palmCustom);

        // Another pass of the WorldGenMegaJungle collection for extra jungleness.
        this.addDeco(decoHelperThisOrThat);

        // Jungle logs.
        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.loops = 1;
        decoFallenTree.distribution.noiseDivisor = 100f;
        decoFallenTree.distribution.noiseFactor = 5f;
        decoFallenTree.distribution.noiseAddend = 0.8f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 3;
        decoFallenTree.logBlock = BlockUtil.getStateLog(3);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(3);
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 9;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfig.decorationLogsId));

        // At this point, let's hand over some of the decoration to the base biome, but only about 85% of the time.
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 6;
        decoBaseBiomeDecorations.loops = 1;
        this.addDeco(decoBaseBiomeDecorations);

        // A combo-deal of lilypads and vines. (This could probably be pulled out into individual decos.)
        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);

        // A combo-deal of grass and vines. (This could probably be pulled out into individual decos.)
        DecoJungleGrassVines decoJungleGrassVines = new DecoJungleGrassVines();
        this.addDeco(decoJungleGrassVines);

        // Flowers.
        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{5}; // Only orange tulips fit in with the colour scheme.
        decoFlowersRTG.chance = 4;
        decoFlowersRTG.maxY = 120;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        // Tall cacti on red sand - matches the colour scheme nicely.
        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.strengthFactor = 8f;
        decoJungleCacti.maxY = 120;
        decoJungleCacti.sandOnly = false;
        decoJungleCacti.extraHeight = 7;
        decoJungleCacti.sandMeta = (byte) 1;
        this.addDeco(decoJungleCacti, this.config._boolean(BiomeConfig.decorationCactusId));

        // Mossy boulders for the green.
        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 95;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        // Grass filler.
        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
