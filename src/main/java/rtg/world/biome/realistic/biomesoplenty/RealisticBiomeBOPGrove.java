package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.util.CliffCalculator;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.helper.DecoHelper5050;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPGrove extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.grove.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPGrove() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPGrove();
    }

    public class TerrainBOPGrove extends TerrainBase {

        private float baseHeight = 64f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 5f;
        private float smoothHillWavelength = 20f;
        private float smoothHillStrength = 10f;

        public TerrainBOPGrove() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
            // no ground noise

            float h = this.terrainGrasslandHills(x, y, simplex, cell, river, smoothHillWavelength, smoothHillStrength, peakyHillWavelength, peakyHillStrength, baseHeight);

            return h;
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPGrove(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.15f);
    }

    public class SurfaceBOPGrove extends SurfaceBase {

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

            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;
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

        DecoFallenTree decoFallenTree1 = new DecoFallenTree();
        decoFallenTree1.distribution.noiseDivisor = 80f;
        decoFallenTree1.distribution.noiseFactor = 60f;
        decoFallenTree1.distribution.noiseAddend = -15f;
        decoFallenTree1.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree1.logConditionNoise = 8f;
        decoFallenTree1.logConditionChance = 1;
        decoFallenTree1.maxY = 100;
        decoFallenTree1.logBlock = BlockUtil.getStateLog(2);
        decoFallenTree1.leavesBlock = BlockUtil.getStateLeaf(2);
        decoFallenTree1.minSize = 3;
        decoFallenTree1.maxSize = 6;

        DecoFallenTree decoFallenTree2 = new DecoFallenTree();
        decoFallenTree2.distribution.noiseDivisor = 80f;
        decoFallenTree2.distribution.noiseFactor = 60f;
        decoFallenTree2.distribution.noiseAddend = -15f;
        decoFallenTree2.logCondition = DecoFallenTree.LogCondition.X_DIVIDED_BY_STRENGTH;
        decoFallenTree2.logConditionNoise = 8f;
        decoFallenTree2.logConditionChance = 1;
        decoFallenTree2.maxY = 100;
        decoFallenTree2.logBlock = BlockUtil.getStateLog2(1);
        decoFallenTree2.leavesBlock = BlockUtil.getStateLeaf(1);
        decoFallenTree2.minSize = 3;
        decoFallenTree2.maxSize = 6;

        DecoHelper5050 decoHelperHelper5050 = new DecoHelper5050(decoFallenTree1, decoFallenTree2);
        this.addDeco(decoHelperHelper5050, this.getConfig().ALLOW_LOGS.get());

        DecoShrub decoShrubCustom = new DecoShrub();
        decoShrubCustom.logBlock = BlockUtil.getStateLog(2);
        decoShrubCustom.leavesBlock = BlockUtil.getStateLeaf(2);
        decoShrubCustom.maxY = 110;
        decoShrubCustom.strengthFactor = 2f;
        DecoShrub decoShrubCustom2 = new DecoShrub();
        decoShrubCustom2.logBlock = BlockUtil.getStateLog2(1);
        decoShrubCustom2.leavesBlock = BlockUtil.getStateLeaf2(1);
        decoShrubCustom2.maxY = 110;
        decoShrubCustom2.strengthFactor = 2f;
        DecoHelper5050 decoHelperHelper50502 = new DecoHelper5050(decoShrubCustom, decoShrubCustom2);
        this.addDeco(decoHelperHelper50502);

        DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoFlowersRTG.flowers = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        decoFlowersRTG.maxY = 128;
        decoFlowersRTG.strengthFactor = 2f;
        this.addDeco(decoFlowersRTG);

        DecoGrass decoGrass = new DecoGrass();
        decoGrass.maxY = 128;
        decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
