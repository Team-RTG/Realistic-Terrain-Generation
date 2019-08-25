package rtg.world.biome.realistic.projectvibrantjourneys;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.GroundEffect;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.api.world.terrain.heighteffect.RaiseEffect;
import rtg.api.world.terrain.heighteffect.SpikeEverywhereEffect;
import rtg.api.world.terrain.heighteffect.VoronoiBorderEffect;

import java.util.Random;


public class RealisticBiomePVJRedwoodPeaks extends RealisticBiomePVJBase {

    public RealisticBiomePVJRedwoodPeaks(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
    }

    @Override
    public TerrainBase initTerrain() {
        return new RidgedExtremeHills(150f, 67f, 200f);
        //return new TerrainVanillaExtremeHills(10f, 120f, 10f, 200f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaForest(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f,
                BlockUtil.getStateDirt(BlockDirt.DirtType.PODZOL), 0.4f, BlockUtil.getStateDirt(BlockDirt.DirtType.COARSE_DIRT), -0.5f);
    }

    public static class RidgedExtremeHills extends TerrainBase {

        private final HeightEffect heightIncrease;
        private final HeightEffect multiplier;
        private final HeightEffect groundEffect;
        private float height;
        private float width;
        private float ridgeWidth = 300f;
        private float valleyFloor = -0.2f;

        public RidgedExtremeHills(float landHeight, float baseHeight, float hillWidth) {
            height = landHeight;
            base = baseHeight;
            width = hillWidth;

            SpikeEverywhereEffect baseHills = new SpikeEverywhereEffect();
            baseHills.spiked = new RaiseEffect(height * 2f / 3f);
            baseHills.wavelength = width;
            baseHills.minimumSimplex = -0.2f;
            baseHills.octave = 3;
            baseHills.power = 1.9f;

            SpikeEverywhereEffect additionalHeightSpikes = new SpikeEverywhereEffect();
            additionalHeightSpikes.spiked = new RaiseEffect(height / 3f);
            additionalHeightSpikes.wavelength = width / 3f;
            additionalHeightSpikes.minimumSimplex = -0.2f;
            additionalHeightSpikes.octave = 4;
            additionalHeightSpikes.power = 1.9f;

            JitterEffect additionalHeight = new JitterEffect();
            additionalHeight.amplitude = additionalHeightSpikes.wavelength / 3;
            additionalHeight.wavelength = additionalHeightSpikes.wavelength / 2;
            additionalHeight.jittered = additionalHeightSpikes;

            SpikeEverywhereEffect rougheningSpikes = new SpikeEverywhereEffect();
            rougheningSpikes.spiked = new RaiseEffect(height / 8f);
            rougheningSpikes.wavelength = width / 10f;
            rougheningSpikes.minimumSimplex = -0.2f;
            rougheningSpikes.octave = 5;
            rougheningSpikes.power = 1.9f;

            JitterEffect roughening = new JitterEffect();
            roughening.amplitude = rougheningSpikes.wavelength / 3;
            roughening.wavelength = rougheningSpikes.wavelength / 2;
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
            float groundEffectLevel = groundEffect.added(rtgWorld, (float) x, (float) y);
            float ridging = multiplier.added(rtgWorld, (float) x, (float) y);
            ridging = WorldUtil.Terrain.bayesianAdjustment(ridging, 2);
            float result = base + ridging * (groundEffectLevel + heightIncrease.added(rtgWorld, (float) x, (float) y))
                    + groundEffectLevel;
            return TerrainBase.mountainCap(result);
        }
    }

    public static class TerrainVanillaExtremeHills extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainVanillaExtremeHills(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            return terrainHighland(x, y, rtgWorld, river, start, width, height, base);
        }
    }

    public static class SurfaceVanillaForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;
        private IBlockState mix2Block;
        private float mix2Height;

        public SurfaceVanillaForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                    float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, float mixHeight, IBlockState mix2, float mix2Height) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), mix);
            this.mixHeight = mixHeight;
            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), mix2);
            this.mix2Height = mix2Height;
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
                        else {
                            float mixNoise = simplex.noise2f(i / 12f, j / 12f);

                            if (mixNoise < mix2Height) {
                                primer.setBlockState(x, k, z, mix2Block);
                                m = true;
                            }
                            else if (mixNoise > mixHeight) {
                                primer.setBlockState(x, k, z, mixBlock);
                                m = true;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
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
