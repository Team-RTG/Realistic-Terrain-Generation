package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.helper.DecoHelper5050;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeBOPGrove extends RealisticBiomeBase {

    public RealisticBiomeBOPGrove(final Biome biome) { super(biome); }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPGrove();
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceBOPGrove(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(DirtType.PODZOL), 0.15f);
    }

    @Override
    public void initDecos() {

        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.setLogBlock(BlockUtil.getStateLog(EnumType.BIRCH));
        decoShrubCustom.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.BIRCH));
        decoShrubCustom.setMaxY(110);
        decoShrubCustom.setLoopMultiplier(2f);
        DecoShrub decoShrubCustom2 = new DecoShrub();
        decoShrubCustom2.setLogBlock(BlockUtil.getStateLog(EnumType.DARK_OAK));
        decoShrubCustom2.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.DARK_OAK));
        decoShrubCustom2.setMaxY(110);
        decoShrubCustom2.setLoopMultiplier(2f);
        DecoHelper5050 decoHelperHelper50502 = new DecoHelper5050(decoShrubCustom, decoShrubCustom2);
        this.addDeco(decoHelperHelper50502);

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.getDistribution().setNoiseDivisor(80f);
        decoFallenTree1.getDistribution().setNoiseFactor(60f);
        decoFallenTree1.getDistribution().setNoiseAddend(-15f);
        decoFallenTree1.setLogConditionChance(3);
        decoFallenTree1.setMaxY(100);
        decoFallenTree1.setLogBlock(BlockUtil.getStateLog(EnumType.BIRCH));
        decoFallenTree1.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.BIRCH));
        decoFallenTree1.setMinSize(3);
        decoFallenTree1.setMaxSize(6);

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.getDistribution().setNoiseDivisor(80f);
        decoFallenTree2.getDistribution().setNoiseFactor(60f);
        decoFallenTree2.getDistribution().setNoiseAddend(-15f);
        decoFallenTree2.setLogConditionChance(3);
        decoFallenTree2.setMaxY(100);
        decoFallenTree2.setLogBlock(BlockUtil.getStateLog(EnumType.DARK_OAK));
        decoFallenTree2.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.SPRUCE));
        decoFallenTree2.setMinSize(3);
        decoFallenTree2.setMaxSize(6);

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050, this.getConfig().ALLOW_LOGS.get());
    }

    public static class TerrainBOPGrove extends TerrainBase {

        private float baseHeight = 64f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 5f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 10f;

        public TerrainBOPGrove() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            // no ground noise

            return terrainGrasslandHills(x, y, rtgWorld, river,
                smoothHillWavelength, smoothHillStrength, peakyHillWavelength, peakyHillStrength, baseHeight);
        }
    }

    public static class SurfaceBOPGrove extends SurfaceBase {

        public byte mixByte = (byte) 0;
        private float min;
        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;
        private IBlockState mix;
        private float mixHeight;

        public SurfaceBOPGrove(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
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
            float c = TerrainBase.calcCliff(x, z, noise, river);
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
                            primer.setBlockState(x, k, z, mix);
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
