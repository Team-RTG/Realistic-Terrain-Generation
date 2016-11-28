package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenTrees;

import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.util.CliffCalculator;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaBirchForestHillsM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_BIRCH_FOREST_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaBirchForestHillsM() {

        super(biome, river);

        this.noLakes = true;
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaBirchForestHillsM();
    }

    public class TerrainVanillaBirchForestHillsM extends TerrainBase {

        private float hillStrength = 70f;

        public TerrainVanillaBirchForestHillsM() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainHighland(x, y, rtgWorld.simplex, rtgWorld.cell, river, 10f, 68f, hillStrength, 10f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaBirchForestHillsM(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceVanillaBirchForestHillsM extends SurfaceBase {

        public SurfaceVanillaBirchForestHillsM(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

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
                            primer.setBlockState(x, k, z, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
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
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

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
