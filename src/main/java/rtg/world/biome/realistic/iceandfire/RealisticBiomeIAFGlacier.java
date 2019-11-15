package rtg.world.biome.realistic.iceandfire;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.world.biome.RealisticBiomeBase;

import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.*;

public class RealisticBiomeIAFGlacier extends RealisticBiomeBase {

    public RealisticBiomeIAFGlacier(Biome biome) {

        super(biome, RiverType.FROZEN);
    }

    @Override
    public void initConfig() {
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainIAFGlacier(150f, 67f, 200f);
    }

    public static class TerrainIAFGlacier extends TerrainBase {
        private float height;
        private float width;
        private float ridgeWidth = 300f;
        private float valleyFloor = -0.2f;

        private final HeightEffect heightIncrease;
        private final HeightEffect multiplier;
        private final HeightEffect groundEffect;

        public TerrainIAFGlacier(float landHeight, float baseHeight, float hillWidth) {
            height = landHeight;
            base = baseHeight;
            width = hillWidth;

            SpikeEverywhereEffect baseHills = new SpikeEverywhereEffect();
            baseHills.spiked = new RaiseEffect(height*2f/3f);
            baseHills.wavelength = width;
            baseHills.minimumSimplex = -0.2f;
            baseHills.octave = 3;
            baseHills.power = 1.9f;

            SpikeEverywhereEffect additionalHeightSpikes = new SpikeEverywhereEffect();
            additionalHeightSpikes.spiked = new RaiseEffect(height/3f);
            additionalHeightSpikes.wavelength = width/3f;
            additionalHeightSpikes.minimumSimplex = -0.2f;
            additionalHeightSpikes.octave = 4;
            additionalHeightSpikes.power = 1.9f;

            JitterEffect additionalHeight = new JitterEffect();
            additionalHeight.amplitude = additionalHeightSpikes.wavelength/3;
            additionalHeight.wavelength = additionalHeightSpikes.wavelength/2;
            additionalHeight.jittered = additionalHeightSpikes;

            SpikeEverywhereEffect rougheningSpikes = new SpikeEverywhereEffect();
            rougheningSpikes.spiked = new RaiseEffect(height/8f);
            rougheningSpikes.wavelength = width/10f;
            rougheningSpikes.minimumSimplex = -0.2f;
            rougheningSpikes.octave = 5;
            rougheningSpikes.power = 1.9f;

            JitterEffect roughening = new JitterEffect();
            roughening.amplitude = rougheningSpikes.wavelength/3;
            roughening.wavelength = rougheningSpikes.wavelength/2;
            roughening.jittered = rougheningSpikes;

            JitterEffect hillJitter = new JitterEffect();
            hillJitter.amplitude = 15f;
            hillJitter.wavelength = 50f;
            hillJitter.jittered = baseHills.plus(additionalHeight).plus(roughening);
            heightIncrease = hillJitter;


            VoronoiBorderEffect ridging = new VoronoiBorderEffect();
            ridging.pointWavelength = ridgeWidth;
            ridging.floor = valleyFloor;
            ridging.minimumDivisor = .2f;

            JitterEffect ridgeJitter = new JitterEffect();
            ridgeJitter.amplitude = 15f;
            ridgeJitter.wavelength = 50f;
            ridgeJitter.jittered = ridging;

            JitterEffect ridgeJitterrette = new JitterEffect();
            ridgeJitterrette.amplitude = 5f;
            ridgeJitterrette.wavelength = 20f;
            ridgeJitterrette.jittered = ridgeJitter;
            multiplier = ridgeJitterrette;

            groundEffect = new GroundEffect(6);
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            // ground effect is increased by the multiplier
            float groundEffectLevel = groundEffect.added(rtgWorld, (float)x, (float)y);
            float ridging = multiplier.added(rtgWorld, (float)x, (float )y);
            ridging = bayesianAdjustment(ridging, 2);
            float result = base + ridging * (groundEffectLevel + heightIncrease.added(rtgWorld, (float)x, (float )y))
                + groundEffectLevel;
            return TerrainBase.mountainCap(result);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceIAFGlacier(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, Blocks.SNOW.getDefaultState(), Blocks.SNOW.getDefaultState(), Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), 60f,
            -0.14f, 14f, 0.25f);
    }

    public class SurfaceIAFGlacier extends SurfaceBase {

        private IBlockState mixBlockTop;
        private IBlockState mixBlockFill;
        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;
        private float width;
        private float height;
        private float smallW;
        private float smallS;

        public SurfaceIAFGlacier(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, IBlockState cliff1, IBlockState cliff2, float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            mixBlockTop = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), mixTop);
            mixBlockFill = this.getConfigBlock(config.SURFACE_MIX_FILLER_BLOCK.get(), mixFill);

            cliffBlock1 = cliff1;
            cliffBlock2 = cliff2;

            width = mixWidth;
            height = mixHeight;
            smallW = smallWidth;
            smallS = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = TerrainBase.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f;
            boolean mix = false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                    continue;
                }
                if (b != Blocks.STONE) {
                    continue;
                }

                depth++;
                if (cliff) {
                    if (depth > -1 && depth < 2) {
                        primer.setBlockState(x, k, z, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                    }
                    else if (depth < 10) {
                        primer.setBlockState(x, k, z, cliffBlock1);
                    }
                }
                else {
                    if (depth == 0 && k > 61) {
                        if (simplex.noise2f(i / width, j / width) + simplex.noise2f(i / smallW, j / smallW) * smallS > height) {
                            primer.setBlockState(x, k, z, mixBlockTop);
                            mix = true;
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 4) {
                        if (mix) {
                            primer.setBlockState(x, k, z, mixBlockFill);
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
    }
}
