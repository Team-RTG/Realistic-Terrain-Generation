package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.collection.DecoCollectionExtremeHills;
import rtg.api.world.deco.collection.DecoCollectionExtremeHillsCommon;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.GroundEffect;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.api.world.terrain.heighteffect.RaiseEffect;
import rtg.api.world.terrain.heighteffect.SpikeEverywhereEffect;
import rtg.api.world.terrain.heighteffect.VoronoiBorderEffect;
import rtg.api.world.biome.RealisticBiomeBase;


public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeBase {

    public static Biome biome = Biomes.EXTREME_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaExtremeHills() {

        super(biome, BeachType.STONE);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_FILLER_BLOCK).set("");
    }

    @Override
    public TerrainBase initTerrain() {
        return new RidgedExtremeHills(150f, 67f, 200f);
        //return new TerrainVanillaExtremeHills(10f, 120f, 10f, 200f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaExtremeHills(getConfig(), biome.topBlock, biome.fillerBlock, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 60f, -0.14f, 14f, 0.25f);
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionExtremeHills(this.getConfig()));
        this.addDecoCollection(new DecoCollectionExtremeHillsCommon(this.getConfig()));
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
            ridging = Terrain.bayesianAdjustment(ridging, 2);
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

    public static class SurfaceVanillaExtremeHills extends SurfaceBase {

        private IBlockState mixBlockTop;
        private IBlockState mixBlockFill;
        private float width;
        private float height;
        private float smallW;
        private float smallS;

        public SurfaceVanillaExtremeHills(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, float mixWidth,
                                          float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            mixBlockTop = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), mixTop);
            mixBlockFill = this.getConfigBlock(config.SURFACE_MIX_FILLER_BLOCK.get(), mixFill);

            width = mixWidth;
            height = mixHeight;
            smallW = smallWidth;
            smallS = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f;
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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone());
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
    }
}
