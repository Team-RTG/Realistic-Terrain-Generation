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
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelperThisOrThat;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaPentandra;
import rtg.world.gen.feature.tree.rtg.TreeRTGCeibaRosea;
import rtg.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.ROOFED_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaRoofedForest() {

        super(biome, river);

        this.waterSurfaceLakeChance = 3;
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().ALLOW_COBWEBS).set(true);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaRoofedForest();
    }

    public class TerrainVanillaRoofedForest extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaRoofedForest() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f)
            return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaRoofedForest(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.08f);
    }

    public class SurfaceVanillaRoofedForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceVanillaRoofedForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
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

            // varying clay densities;
            float mixModifier = mixHeight + simplex.noise2(((float) i) / 800f, ((float) j) / 800f);
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
                        else if (simplex.noise2(i / 12f, j / 12f) > mixModifier) {
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

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.chance = 4;
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.ALWAYS_GENERATE;
        this.addDeco(decoMushrooms);

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
        mucronataTree.logBlock = BlockUtil.getStateLog2(1);
        mucronataTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        mucronataTree.minTrunkSize = 2;
        mucronataTree.maxTrunkSize = 3;
        mucronataTree.minCrownSize = 10;
        mucronataTree.maxCrownSize = 18;
        mucronataTree.noLeaves = false;
        this.addTree(mucronataTree);

        DecoTree mangroveTree = new DecoTree(mucronataTree);
        mangroveTree.treeType = DecoTree.TreeType.RTG_TREE;
        mangroveTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        mangroveTree.treeConditionChance = 1;
        mangroveTree.strengthFactorForLoops = 12f;
        mangroveTree.maxY = 110;
        mangroveTree.scatter = new DecoTree.Scatter(16, 0);
        this.addDeco(mangroveTree);

        TreeRTG pentandraTree = new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f);
        pentandraTree.logBlock = BlockUtil.getStateLog2(1);
        pentandraTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        pentandraTree.minTrunkSize = 2;
        pentandraTree.maxTrunkSize = 3;
        pentandraTree.minCrownSize = 10;
        pentandraTree.maxCrownSize = 18;
        pentandraTree.noLeaves = false;
        this.addTree(pentandraTree);

        DecoTree ceibaPentandraTree = new DecoTree(pentandraTree);
        ceibaPentandraTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaPentandraTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaPentandraTree.treeConditionChance = 1;
        ceibaPentandraTree.strengthFactorForLoops = 12f;
        ceibaPentandraTree.maxY = 110;
        ceibaPentandraTree.scatter = new DecoTree.Scatter(16, 0);
        this.addDeco(ceibaPentandraTree);

        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
        roseaTree.logBlock = BlockUtil.getStateLog2(1);
        roseaTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        roseaTree.minTrunkSize = 2;
        roseaTree.maxTrunkSize = 3;
        roseaTree.minCrownSize = 10;
        roseaTree.maxCrownSize = 18;
        roseaTree.noLeaves = false;
        this.addTree(roseaTree);

        DecoTree ceibaRoseaTree = new DecoTree(roseaTree);
        ceibaRoseaTree.treeType = DecoTree.TreeType.RTG_TREE;
        ceibaRoseaTree.treeCondition = DecoTree.TreeCondition.RANDOM_CHANCE;
        ceibaRoseaTree.treeConditionChance = 1;
        ceibaRoseaTree.strengthFactorForLoops = 12f;
        ceibaRoseaTree.maxY = 110;
        ceibaRoseaTree.scatter = new DecoTree.Scatter(16, 0);
        this.addDeco(ceibaRoseaTree);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionChance = 16;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logBlock = BlockUtil.getStateLog2(1);
        decoFallenTree.leavesBlock = BlockUtil.getStateLeaf2(1);
        decoFallenTree.minSize = 4;
        decoFallenTree.maxSize = 9;
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoShrub darkOakShrub = new DecoShrub();
        darkOakShrub.logBlock = BlockUtil.getStateLog2(1);
        darkOakShrub.leavesBlock = BlockUtil.getStateLeaf2(1);
        darkOakShrub.maxY = 100;
        darkOakShrub.strengthFactor = 8f;

        DecoShrub oakShrub = new DecoShrub();
        oakShrub.logBlock = Blocks.LOG.getDefaultState();
        oakShrub.leavesBlock = Blocks.LEAVES.getDefaultState();
        oakShrub.maxY = 100;
        oakShrub.strengthFactor = 8f;

        this.addDeco(new DecoHelperThisOrThat(4, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, darkOakShrub, oakShrub));

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.MOSSY_COBBLESTONE.getDefaultState();
        decoBoulder.chance = 16;
        decoBoulder.maxY = 80;
        decoBoulder.strengthFactor = 2f;
        this.addDeco(decoBoulder);

        DecoCobwebs decoCobwebs = new DecoCobwebs();
        decoCobwebs.chance = 1;
        decoCobwebs.minY = 63;
        decoCobwebs.maxY = 76;
        decoCobwebs.strengthFactor = 24f;
        decoCobwebs.adjacentBlock = BlockUtil.getStateLog2(1);
        decoCobwebs.minAdjacents = 2;
        this.addDeco(decoCobwebs, this.getConfig().ALLOW_COBWEBS.get());

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.notEqualsZeroChance = 2;
        decoBaseBiomeDecorations.maxY = 100;
        this.addDeco(decoBaseBiomeDecorations);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 100;
        decoGrass.strengthFactor = 20f;
        this.addDeco(decoGrass);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.maxY = 100;
        decoDeadBush.chance = 2;
        decoDeadBush.strengthFactor = 2f;
        this.addDeco(decoDeadBush);
    }
}
