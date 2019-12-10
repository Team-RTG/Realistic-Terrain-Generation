package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import static rtg.api.world.deco.DecoFallenTree.LogCondition.RANDOM_CHANCE;


public class RealisticBiomeBOPShield extends RealisticBiomeBase {

    private double lakeWaterLevel = 0.04;// the lakeStrength below which things should be below water
    private double lakeDepressionLevel = 0.3;// the lakeStrength below which land should start to be lowered

    public RealisticBiomeBOPShield(final Biome biome) { super(biome); }

    @Override
    public Biome preferredBeach() {
        return BOPBiomes.gravel_beach.orNull();
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPShield();
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceBOPShield(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.STONE.getDefaultState(), 0.10f);
    }

    @Override
    public float lakePressure(RTGWorld rtgWorld, int x, int y, float border, float lakeInterval, float largeBendSize, float mediumBendSize, float smallBendSize) {

        double pX = x;
        double pY = y;
        ISimplexData2D jitterData = SimplexData2D.newDisk();
        // rather than lakes, we have a bayou network
        rtgWorld.simplexInstance(1).multiEval2D(x / 40.0d, y / 40.0d, jitterData);
        pX += jitterData.getDeltaX() * 35d;
        pY += jitterData.getDeltaY() * 35d;
        return TerrainBase.bayesianAdjustment((float) rtgWorld.cellularInstance(0).eval2D(pX / 150.0, pY / 150.0).interiorValue(), 0.25f);
    }

    @Override
    public float erodedNoise(RTGWorld rtgWorld, int x, int y, float river, float border, float biomeHeight) {
        // Bayou has a higher river bottom
        float r;
        // river of actualRiverProportions now maps to 1; TODO
        float riverFlattening = 1f - river;
        riverFlattening = riverFlattening - (1 - RTGWorld.ACTUAL_RIVER_PROPORTION);
        // return biomeHeight if no river effect
        if (riverFlattening < 0) {
            return biomeHeight;
        }
        // what was 1 set back to 1;
        riverFlattening /= (RTGWorld.ACTUAL_RIVER_PROPORTION);

        // back to usual meanings: 1 = no river 0 = river
        r = 1f - riverFlattening;
        // flat spot in middle;
        riverFlattening = riverFlattening * 1.4f - 0.4f;
        if (riverFlattening < 0) {
            riverFlattening = 0;
        }

        if ((r < 1f && biomeHeight > 57f)) {
            float irregularity = rtgWorld.simplexInstance(0).noise2f(x / 12f, y / 12f) * 2f + rtgWorld.simplexInstance(0).noise2f(x / 8f, y / 8f);
            // less on the bottom and more on the sides
            irregularity = irregularity * (1 + r);
            return (biomeHeight * (r)) + ((57f + irregularity) * 1.0f) * (1f - r);
        }
        else {
            return biomeHeight;
        }
    }

    @Override
    public float lakeFlattening(float pressure, float bottomLevel, float topLevel) {

        // these are rivers so not necessary to fake the lake values as river
        return pressure;
        // this number indicates a multiplier to height
        /*if (pressure > lakeDepressionLevel) return 1;
        if (pressure<lakeWaterLevel) return 0;
        return (float)((pressure-lakeWaterLevel)/(lakeDepressionLevel-lakeWaterLevel));*/
    }

    @Override
    public void initDecos() {

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setMaxY(80);
        decoBoulder.setChance(16);
        decoBoulder.setStrengthFactor(1f);
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.getDistribution().setNoiseDivisor(80f);
        decoFallenTree1.getDistribution().setNoiseFactor(60f);
        decoFallenTree1.getDistribution().setNoiseAddend(-15f);
        decoFallenTree1.setLogCondition(RANDOM_CHANCE);
        decoFallenTree1.setLogConditionChance(6);
        decoFallenTree1.setMaxY(100);
        decoFallenTree1.setLogBlock(BOPBlocks.log_2.getStateFromMeta(6));
        decoFallenTree1.setLeavesBlock(Blocks.LEAVES.getDefaultState());
        decoFallenTree1.setMinSize(3);
        decoFallenTree1.setMaxSize(4);

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.getDistribution().setNoiseDivisor(80f);
        decoFallenTree2.getDistribution().setNoiseFactor(60f);
        decoFallenTree2.getDistribution().setNoiseAddend(-15f);
        decoFallenTree2.setLogCondition(RANDOM_CHANCE);
        decoFallenTree2.setLogConditionChance(6);
        decoFallenTree2.setMaxY(100);
        decoFallenTree2.setLogBlock(BlockUtil.getStateLog(EnumType.SPRUCE));
        decoFallenTree2.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.SPRUCE));
        decoFallenTree2.setMinSize(3);
        decoFallenTree2.setMaxSize(4);

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050, this.getConfig().ALLOW_LOGS.get());
    }

    // this biome also changes the lake generation in RealisticBiomeBase
    public static class TerrainBOPShield extends TerrainBase {

        public TerrainBOPShield() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 160f, 10f, 60f, 200f, 64f);
        }
    }

    public static class SurfaceBOPShield extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;

        public SurfaceBOPShield(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
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
            float c = TerrainBase.calcCliff(x, z, noise);
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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
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
                            primer.setBlockState(x, k, z, hcStone());
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
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
