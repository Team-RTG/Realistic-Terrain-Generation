package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenTrees;

import rtg.config.BiomeConfig;
import rtg.config.BiomeConfigProperty;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBirchForest extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.BIRCH_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaBirchForest() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.decorationLogsId, BiomeConfigProperty.Type.BOOLEAN, BiomeConfig.decorationLogsName, "", true));

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixBlockId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockName, "", ""));
        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockMetaName, "", ""));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaBirchForest();
    }

    public class TerrainVanillaBirchForest extends TerrainBase {


        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaBirchForest() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
            return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaBirchForest(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.15f);
    }

    public class SurfaceVanillaBirchForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceVanillaBirchForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
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

        TreeRTG birchSmall = new TreeRTGBetulaPapyrifera();
        birchSmall.logBlock = BlockUtil.getStateLog(2);
        birchSmall.leavesBlock = BlockUtil.getStateLeaf(2);
        birchSmall.minTrunkSize = 4;
        birchSmall.maxTrunkSize = 10;
        birchSmall.minCrownSize = 8;
        birchSmall.maxCrownSize = 19;
        this.addTree(birchSmall);

        DecoTree smallBirch = new DecoTree(birchSmall);
        smallBirch.strengthNoiseFactorForLoops = true;
        smallBirch.treeType = DecoTree.TreeType.RTG_TREE;
        smallBirch.distribution.noiseDivisor = 80f;
        smallBirch.distribution.noiseFactor = 60f;
        smallBirch.distribution.noiseAddend = -15f;
        smallBirch.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        smallBirch.maxY = 120;
        this.addDeco(smallBirch);

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
        birchTree.logBlock = BlockUtil.getStateLog(2);
        birchTree.leavesBlock = BlockUtil.getStateLeaf(2);
        birchTree.minTrunkSize = 4;
        birchTree.maxTrunkSize = 10;
        birchTree.minCrownSize = 8;
        birchTree.maxCrownSize = 19;
        this.addTree(birchTree);

        DecoTree birchTrees = new DecoTree(birchTree);
        birchTrees.strengthFactorForLoops = 3f;
        birchTrees.treeType = DecoTree.TreeType.RTG_TREE;
        birchTrees.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        birchTrees.maxY = 100;

        DecoTree rtgTrees = new DecoTree(new WorldGenTrees(false));
        rtgTrees.treeType = DecoTree.TreeType.WORLDGEN;
        rtgTrees.strengthFactorForLoops = 3f;
        rtgTrees.treeCondition = DecoTree.TreeCondition.ALWAYS_GENERATE;
        rtgTrees.maxY = 100;

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{birchTrees, rtgTrees};
        decoHelperRandomSplit.chances = new int[]{10, 4};
        this.addDeco(decoHelperRandomSplit);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 8;
        decoFallenTree.logBlock = BlockUtil.getStateLog(2);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf(2);
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 6;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfig.decorationLogsId));

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 120;
        decoShrub.strengthFactor = 3f;
        this.addDeco(decoShrub);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 3;
        this.addDeco(decoBaseBiomeDecorations);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{3, 6};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 12f;
        this.addDeco(decoFlowersRTG);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);
    }
}
