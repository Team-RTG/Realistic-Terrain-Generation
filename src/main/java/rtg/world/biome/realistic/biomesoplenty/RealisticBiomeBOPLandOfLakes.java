package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.DecoTree;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGBetulaPapyrifera;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


public class RealisticBiomeBOPLandOfLakes extends RealisticBiomeBase {

    public static Biome biome = BOPBiomes.land_of_lakes.get();

    public RealisticBiomeBOPLandOfLakes() {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPLandOfLakes(58f, 76f, 36f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPLandOfLakes(getConfig(), Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, Blocks.STONE.getDefaultState(), 0.10f);
    }

    @Override
    public void initDecos() {

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
        birchTree.setLogBlock(BlockUtil.getStateLog(BlockPlanks.EnumType.BIRCH));
        birchTree.setLeavesBlock(BlockUtil.getStateLeaf(BlockPlanks.EnumType.BIRCH));
        birchTree.setMinTrunkSize(4);
        birchTree.setMaxTrunkSize(10);
        birchTree.setMinCrownSize(8);
        birchTree.setMaxCrownSize(19);
        this.addTree(birchTree);

        DecoTree birchTrees = new DecoTree(birchTree);
        birchTrees.setStrengthFactorForLoops(9f);
        birchTrees.setTreeType(DecoTree.TreeType.RTG_TREE);
        birchTrees.getDistribution().setNoiseDivisor(100f);
        birchTrees.getDistribution().setNoiseFactor(6f);
        birchTrees.getDistribution().setNoiseAddend(0.8f);
        birchTrees.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        birchTrees.setTreeConditionChance(1);
        birchTrees.setTreeConditionNoise(0f);
        birchTrees.setMaxY(120);

        TreeRTG sitchensisTree = new TreeRTGPiceaSitchensis();
        sitchensisTree.setLogBlock(BlockUtil.getStateLog(BlockPlanks.EnumType.SPRUCE));
        sitchensisTree.setLeavesBlock(BlockUtil.getStateLeaf(BlockPlanks.EnumType.SPRUCE));
        sitchensisTree.setMinTrunkSize(4);
        sitchensisTree.setMaxTrunkSize(9);
        sitchensisTree.setMinCrownSize(5);
        sitchensisTree.setMaxCrownSize(14);
        this.addTree(sitchensisTree);

        DecoTree smallPine = new DecoTree(sitchensisTree);
        smallPine.setStrengthFactorForLoops(9f);
        smallPine.setTreeType(DecoTree.TreeType.RTG_TREE);
        smallPine.getDistribution().setNoiseDivisor(100f);
        smallPine.getDistribution().setNoiseFactor(6f);
        smallPine.getDistribution().setNoiseAddend(0.8f);
        smallPine.setTreeCondition(DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE);
        smallPine.setTreeConditionChance(1);
        smallPine.setTreeConditionNoise(0f);
        smallPine.setMaxY(120);

        DecoHelper5050 decoHelper5050 = new DecoHelper5050(birchTrees, smallPine);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(100f);
        decoFallenTree.getDistribution().setNoiseFactor(6f);
        decoFallenTree.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(12);
        decoFallenTree.setRandomLogBlocks(new IBlockState[]{Blocks.LOG.getDefaultState(), BlockUtil.getStateLog(BlockPlanks.EnumType.SPRUCE), BlockUtil.getStateLog(BlockPlanks.EnumType.BIRCH)});
        decoFallenTree.setMinSize(8);
        decoFallenTree.setMaxSize(12);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setMaxY(110);
        decoShrub.setStrengthFactor(3f);
        this.addDeco(decoShrub);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setMaxY(80);
        decoBoulder.setChance(12);
        decoBoulder.setStrengthFactor(1f);
        this.addDeco(decoBoulder);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    public class TerrainBOPLandOfLakes extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;

        public TerrainBOPLandOfLakes(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainRollingHills(x, y, rtgWorld, river, hillStrength, groundNoise, groundNoiseAmplitudeHills, 0f);
        }
    }

    public class SurfaceBOPLandOfLakes extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mix;
        private float mixHeight;

        public SurfaceBOPLandOfLakes(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                     float stoneHeight, float stoneStrength, float clayCliff, IBlockState mixBlock, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mix = mixBlock;
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
                            primer.setBlockState(x, k, z, mix);
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
