package rtg.world.biome.realistic.vanilla;

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
import rtg.api.world.deco.helper.DecoHelperThisOrThat;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCeibaPentandra;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCeibaRosea;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.GroundEffect;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;

public class RealisticBiomeVanillaRoofedForest extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.ROOFED_FOREST;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaRoofedForest() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
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
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f)
            return riverized(65f + groundEffect.added(rtgWorld, x, y), river);
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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float c = CliffCalculator.calc(x, z, noise);
            int cliff = 0;
            boolean m = false;

            // varying clay densities;
            float mixModifier = mixHeight + simplex.noise2(((float) i) / 800f, ((float) j) / 800f);
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
                        else if (simplex.noise2(i / 12f, j / 12f) > mixModifier) {
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

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.config().CHANCE.set(4);
        decoMushrooms.config().MAX_Y.set(90);
        decoMushrooms.setRandomType(DecoMushrooms.RandomType.ALWAYS_GENERATE);
        this.addDeco(decoMushrooms);

        TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
        mucronataTree.setLogBlock(BlockUtil.getStateLog2(1));
        mucronataTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        mucronataTree.setMinTrunkSize(2);
        mucronataTree.setMaxTrunkSize(3);
        mucronataTree.setMinCrownSize(10);
        mucronataTree.setMaxCrownSize(18);
        mucronataTree.setNoLeaves(false);
        this.addTree(mucronataTree);

        DecoTree mangroveTree = new DecoTree(mucronataTree);
        mangroveTree.setTreeType(DecoTree.TreeType.RTG_TREE);
        mangroveTree.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        mangroveTree.setTreeConditionChance(1);
        mangroveTree.setStrengthFactorForLoops(12f);
        mangroveTree.config().MAX_Y.set(110);
        mangroveTree.setScatter(new DecoTree.Scatter(16, 0));
        this.addDeco(mangroveTree);

        TreeRTG pentandraTree = new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f);
        pentandraTree.setLogBlock(BlockUtil.getStateLog2(1));
        pentandraTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        pentandraTree.setMinTrunkSize(2);
        pentandraTree.setMaxTrunkSize(3);
        pentandraTree.setMinCrownSize(10);
        pentandraTree.setMaxCrownSize(18);
        pentandraTree.setNoLeaves(false);
        this.addTree(pentandraTree);

        DecoTree ceibaPentandraTree = new DecoTree(pentandraTree);
        ceibaPentandraTree.setTreeType(DecoTree.TreeType.RTG_TREE);
        ceibaPentandraTree.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        ceibaPentandraTree.setTreeConditionChance(1);
        ceibaPentandraTree.setStrengthFactorForLoops(12f);
        ceibaPentandraTree.config().MAX_Y.set(110);
        ceibaPentandraTree.setScatter(new DecoTree.Scatter(16, 0));
        this.addDeco(ceibaPentandraTree);

        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
        roseaTree.setLogBlock(BlockUtil.getStateLog2(1));
        roseaTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        roseaTree.setMinTrunkSize(2);
        roseaTree.setMaxTrunkSize(3);
        roseaTree.setMinCrownSize(10);
        roseaTree.setMaxCrownSize(18);
        roseaTree.setNoLeaves(false);
        this.addTree(roseaTree);

        DecoTree ceibaRoseaTree = new DecoTree(roseaTree);
        ceibaRoseaTree.setTreeType(DecoTree.TreeType.RTG_TREE);
        ceibaRoseaTree.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
        ceibaRoseaTree.setTreeConditionChance(1);
        ceibaRoseaTree.setStrengthFactorForLoops(12f);
        ceibaRoseaTree.config().MAX_Y.set(110);
        ceibaRoseaTree.setScatter(new DecoTree.Scatter(16, 0));
        this.addDeco(ceibaRoseaTree);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(80f);
        decoFallenTree.getDistribution().setNoiseFactor(60f);
        decoFallenTree.getDistribution().setNoiseAddend(-15f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionChance(16);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogBlock(BlockUtil.getStateLog2(1));
        decoFallenTree.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        decoFallenTree.setMinSize(4);
        decoFallenTree.setMaxSize(9);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoShrub darkOakShrub = new DecoShrub();
        darkOakShrub.setLogBlock(BlockUtil.getStateLog2(1));
        darkOakShrub.setLeavesBlock(BlockUtil.getStateLeaf2(1));
        darkOakShrub.config().MAX_Y.set(100);
        darkOakShrub.config().STRENGTH_FACTOR.set(8f);

        DecoShrub oakShrub = new DecoShrub();
        oakShrub.setLogBlock(Blocks.LOG.getDefaultState());
        oakShrub.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        oakShrub.config().MAX_Y.set(100);
        oakShrub.config().STRENGTH_FACTOR.set(8f);

        this.addDeco(new DecoHelperThisOrThat(4, DecoHelperThisOrThat.ChanceType.NOT_EQUALS_ZERO, darkOakShrub, oakShrub));

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.config().BOULDER_BLOCK.set(Blocks.MOSSY_COBBLESTONE.getDefaultState());
        decoBoulder.config().CHANCE.set(16);
        decoBoulder.config().MAX_Y.set(80);
        decoBoulder.config().STRENGTH_FACTOR.set(2f);
        this.addDeco(decoBoulder);

        DecoCobwebs decoCobwebs = new DecoCobwebs();
        decoCobwebs.config().CHANCE.set(1);
        decoCobwebs.config().MIN_Y.set(63);
        decoCobwebs.config().MAX_Y.set(76);
        decoCobwebs.config().STRENGTH_FACTOR.set(24f);
        decoCobwebs.config().ADJACENT_BLOCK.set(BlockUtil.getStateLog2(1));
        decoCobwebs.config().MIN_ADJACENTS.set(2);
        this.addDeco(decoCobwebs, this.getConfig().ALLOW_COBWEBS.get());

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.config().NOT_EQUALS_ZERO_CHANCE.set(2);
        decoBaseBiomeDecorations.config().MAX_Y.set(100);
        this.addDeco(decoBaseBiomeDecorations);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.config().MAX_Y.set(100);
        decoGrass.config().STRENGTH_FACTOR.set(20f);
        this.addDeco(decoGrass);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.config().MAX_Y.set(100);
        decoDeadBush.config().CHANCE.set(2);
        decoDeadBush.config().STRENGTH_FACTOR.set(2f);
        this.addDeco(decoDeadBush);
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 3;
    }
}
