package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
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
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.helper.DecoHelperRandomSplit;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.RealisticBiomeBase;

import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;


public class RealisticBiomeBOPDeadForest extends RealisticBiomeBase {

    public static Biome biome = BOPBiomes.dead_forest.get();

    public RealisticBiomeBOPDeadForest() {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPDeadForest(58f, 80f, 30f);
    }

    public class TerrainBOPDeadForest extends TerrainBase {

        private float minHeight = 58f;
        private float maxHeight = 120f;
        private float hillStrength = 30f;
        private float deadForestGroundAmplitude = 10f;

        public TerrainBOPDeadForest() {

        }

        public TerrainBOPDeadForest(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainRollingHills(x, y, rtgWorld, river, hillStrength, groundNoise, deadForestGroundAmplitude, 0f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPDeadForest(getConfig(),
            biome.topBlock, //Block top
            biome.fillerBlock, //Block filler,
            biome.topBlock, //IBlockState mixTop,
            biome.fillerBlock, //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceBOPDeadForest extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBOPDeadForest(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                                    float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = mixTop;
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;
            boolean mix = false;

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
                            if (simplex.noise2f(i / floMixWidth, j / floMixWidth) + simplex.noise2f(i / floSmallWidth, j / floSmallWidth)
                                * floSmallStrength > floMixHeight) {
                                primer.setBlockState(x, k, z, blockMixTop);

                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, z, blockMixFiller);
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

    @Override
    public void initDecos() {

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setChance(16);
        decoBoulder.setMaxY(95);
        decoBoulder.setStrengthFactor(1f);
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.setLoops(1);
        decoFallenTree1.getDistribution().setNoiseDivisor(100f);
        decoFallenTree1.getDistribution().setNoiseFactor(6f);
        decoFallenTree1.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree1.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree1.setLogConditionNoise(0f);
        decoFallenTree1.setLogConditionChance(10);
        decoFallenTree1.setMaxY(100);
        decoFallenTree1.setLogBlock(BOPBlocks.log_3.getStateFromMeta(2));
        decoFallenTree1.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        decoFallenTree1.setMinSize(3);
        decoFallenTree1.setMaxSize(5);

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.setLoops(1);
        decoFallenTree2.getDistribution().setNoiseDivisor(100f);
        decoFallenTree2.getDistribution().setNoiseFactor(6f);
        decoFallenTree2.getDistribution().setNoiseAddend(0.8f);
        decoFallenTree2.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree2.setLogConditionNoise(0f);
        decoFallenTree2.setLogConditionChance(10);
        decoFallenTree2.setMaxY(100);
        decoFallenTree2.setLogBlock(BlockUtil.getStateLog(BlockPlanks.EnumType.SPRUCE));
        decoFallenTree2.setLeavesBlock(BlockUtil.getStateLeaf(BlockPlanks.EnumType.SPRUCE));
        decoFallenTree2.setMinSize(3);
        decoFallenTree2.setMaxSize(5);

        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{decoFallenTree2, decoFallenTree1};
        decoHelperRandomSplit.chances = new int[]{12, 1};
        this.addDeco(decoHelperRandomSplit, this.getConfig().ALLOW_LOGS.get());

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
