package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoGrass;
import rtg.api.world.deco.DecoGrassDoubleTallgrass;
import rtg.api.world.deco.DecoJungleLilypadVines;
import rtg.api.world.deco.DecoMushrooms;
import rtg.api.world.deco.DecoPond;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.RealisticBiomeBase;


public class RealisticBiomeBOPBayou extends RealisticBiomeBase {

    public static Biome biome = BOPBiomes.bayou.get();

    private static IBlockState mudBlock = BOPBlocks.mud.getDefaultState();
    private static IBlockState logBlock = BOPBlocks.log_2.getStateFromMeta(5);

    private static IBlockState leavesBlock = BOPBlocks.leaves_4.getStateFromMeta(3)
        .withProperty(BlockLeaves.CHECK_DECAY, false)
        .withProperty(BlockLeaves.DECAYABLE, false);

    public RealisticBiomeBOPBayou() {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBayou();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPBayou(getConfig(), biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, biome.topBlock, 0.10f);
    }

    @Override
    public void initDecos() {

        DecoPond decoPond = new DecoPond();
        decoPond.setChunksPerPond(1);
        decoPond.setMaxY(67);
        decoPond.setLoops(8);
        this.addDeco(decoPond);

//        TreeRTG myrtilloidesTree = new TreeRTGSalixMyrtilloides();
//        myrtilloidesTree.setLogBlock(logBlock);
//        myrtilloidesTree.setLeavesBlock(leavesBlock);
//        myrtilloidesTree.validGroundBlocks.add(mudBlock);
//        this.addTree(myrtilloidesTree);
//        DecoTree decoTrees = new DecoTree(myrtilloidesTree);
//        decoTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
//        decoTrees.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
//        decoTrees.setTreeConditionChance(4);
//        decoTrees.setLogBlock(logBlock);
//        decoTrees.setLeavesBlock(leavesBlock);
//        decoTrees.setMaxY(90);
//        this.addDeco(decoTrees);

        /*
         * STOP! Don't add anymore trees! BOP seems to generate a batch of its trees every time RTG generates a batch
         * of its trees, even though we're not calling the BOP Bayou's decorate() method.
         */

//        TreeRTG roseaTree = new TreeRTGCeibaRosea(16f, 5, 0.32f, 0.1f);
//        roseaTree.setLogBlock(logBlock);
//        roseaTree.setLeavesBlock(leavesBlock);
//        roseaTree.validGroundBlocks.add(mudBlock);
//        roseaTree.setMinTrunkSize(2);
//        roseaTree.setMaxTrunkSize(3);
//        roseaTree.setMinCrownSize(10);
//        roseaTree.setMaxCrownSize(18);
//        roseaTree.setNoLeaves(false);
//        this.addTree(roseaTree);
//        DecoTree ceibaRoseaTree = new DecoTree(roseaTree);
//        ceibaRoseaTree.setTreeType(DecoTree.TreeType.RTG_TREE);
//        ceibaRoseaTree.setTreeCondition(DecoTree.TreeCondition.RANDOM_CHANCE);
//        ceibaRoseaTree.setTreeConditionChance(4);
//        ceibaRoseaTree.setMaxY(90);
//        ceibaRoseaTree.setScatter(new DecoTree.Scatter(16, 0));
//        this.addDeco(ceibaRoseaTree);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        decoBaseBiomeDecorations.setNotEqualsZeroChance(4);
        this.addDeco(decoBaseBiomeDecorations);

        // Shrubs to fill in the blanks.
        DecoShrub decoShrubOak = new DecoShrub();
        decoShrubOak.setMaxY(90);
        decoShrubOak.setStrengthFactor(4f);
        decoShrubOak.setChance(3);
        this.addDeco(decoShrubOak);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(80f);
        decoFallenTree.getDistribution().setNoiseFactor(60f);
        decoFallenTree.getDistribution().setNoiseAddend(-15f);
        decoFallenTree.setLogCondition(DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(-0.2f);
        decoFallenTree.setLogConditionChance(4);
        decoFallenTree.setLogBlock(logBlock);
        decoFallenTree.setLeavesBlock(leavesBlock);
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(6);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoJungleLilypadVines decoJungleLilypadVines = new DecoJungleLilypadVines();
        this.addDeco(decoJungleLilypadVines);

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.setMaxY(90);
        decoGrassDoubleTallgrass.setStrengthFactor(4f);
        decoGrassDoubleTallgrass.setDoubleGrassChance(8);
        this.addDeco(decoGrassDoubleTallgrass);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.setMaxY(90);
        decoGrass.setStrengthFactor(4f);
        decoGrass.setChance(2);
        this.addDeco(decoGrass);

        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.setMaxY(90);
        decoMushrooms.setRandomType(DecoMushrooms.RandomType.ALWAYS_GENERATE);
        this.addDeco(decoMushrooms);
    }

    public class TerrainBOPBayou extends TerrainBase {

        public TerrainBOPBayou() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 80f, 1f, 40f, 20f, 62f);
        }
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

            mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), mix);
            mixHeight = mixSize;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
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

                        float p = simplex.noise3f(i / 8f, j / 8f, k / 8f) * 0.5f;
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
                        else if (simplex.noise2f(i / 12f, j / 12f) > mixHeight) {
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
}
