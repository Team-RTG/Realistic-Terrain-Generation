package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
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
import rtg.api.world.terrain.heighteffect.HeightVariation;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;

public class RealisticBiomeBOPLandOfLakes extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.land_of_lakes.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPLandOfLakes() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPLandOfLakes();//(58f, 76f, 36f);
    }

    public class TerrainBOPLandOfLakes extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;
        private HeightVariation small;
        private HeightVariation large;
        private JitterEffect largeJitter;
        private JitterEffect smallJitter;
        

        public TerrainBOPLandOfLakes() {
            super(63f);
            small = new HeightVariation();
            small.height = 2.5f;
            small.octave = 1;
            small.wavelength = 10;
            
            large = new HeightVariation();
            large.height = 5;
            large.octave = 2;
            large.wavelength = 20;
            
            smallJitter = new JitterEffect();
            smallJitter.amplitude= 2;
            smallJitter.wavelength = 9;
            smallJitter.jittered = large.plus(small);
            
            largeJitter = new JitterEffect();
            largeJitter.amplitude = 4;
            largeJitter.wavelength = 18;
            largeJitter.jittered = smallJitter;

        }
        public TerrainBOPLandOfLakes(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {
            return riverized(largeJitter.added(rtgWorld, x, y)+ this.base,river);
            //return terrainRollingHills(x, y, rtgWorld.simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 0f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPLandOfLakes(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 0f, 1.5f, 60f, 65f, 1.5f, Blocks.GRASS.getDefaultState(), 0.10f);
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

    @Override
    public void initDecos() {

        TreeRTG birchTree = new TreeRTGBetulaPapyrifera();
        birchTree.setLogBlock(BlockUtil.getStateLog(2));
        birchTree.setLeavesBlock(BlockUtil.getStateLeaf(2));
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
        sitchensisTree.setLogBlock(BlockUtil.getStateLog(1));
        sitchensisTree.setLeavesBlock(BlockUtil.getStateLeaf(1));
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
        decoFallenTree.setRandomLogBlocks(new IBlockState[]{Blocks.LOG.getDefaultState(), BlockUtil.getStateLog(1), BlockUtil.getStateLog(2)});
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

        DecoBOPBaseBiomeDecorations decoBOPBaseBiomeDecorations = new DecoBOPBaseBiomeDecorations();
        this.addDeco(decoBOPBaseBiomeDecorations);
    }
}
