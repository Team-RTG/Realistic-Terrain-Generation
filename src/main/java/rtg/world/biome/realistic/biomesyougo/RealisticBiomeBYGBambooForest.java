package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.BumpyHillsEffect;
import rtg.api.world.terrain.heighteffect.GroundEffect;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.api.world.terrain.heighteffect.RaiseEffect;
import rtg.api.world.terrain.heighteffect.VariableRuggednessEffect;

import java.util.Random;


public class RealisticBiomeBYGBambooForest extends RealisticBiomeBYGBase {

    public RealisticBiomeBYGBambooForest(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGBiome();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBYGBiome(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, baseBiome().topBlock, 0.15f);
    }

    public static class TerrainBYGBiome extends TerrainBase {

    /* Basic idea: High hilly terrain mixed with ground-noisy flats
     * using a transition that also generates the hills
     */

        private float hillockWavelength = 30;
        private float hillockBoost = 5;
        private float hillockVariance = 10;
        private float hillockSpikeWidth = 8;
        private float hillockSpikeHeight = 4;
        private float terrainBase = 68;

        private JitterEffect biomeHeight;// this includes the base


        public TerrainBYGBiome() {
            // bumpy hills on top
            BumpyHillsEffect onTop = new BumpyHillsEffect();
            onTop.hillHeight = hillockVariance;
            onTop.hillWavelength = hillockWavelength;
            onTop.spikeHeight = hillockSpikeHeight;
            onTop.spikeWavelength = hillockSpikeWidth;
            onTop.hillOctave = 1;// same octave as variableRuggedness

            // plus raised a bit
            HeightEffect hillLevel = onTop.plus(new RaiseEffect(hillockBoost + terrainBase));

            // but only
            VariableRuggednessEffect hills = new VariableRuggednessEffect();
            hills.ruggedTerrain = hillLevel;
            hills.smoothTerrain = new RaiseEffect(terrainBase);
            hills.octave = 1;// just to make it clear;
            hills.startTransition = 0.1f;
            hills.transitionWidth = 0.35f;
            hills.wavelength = hillockWavelength;

            HeightEffect unJittered = hills.plus(new GroundEffect(6f));

            // and lets scramble it a bit

            biomeHeight = new JitterEffect();
            biomeHeight.amplitude = 2;
            biomeHeight.wavelength = 5;
            biomeHeight.jittered = unJittered;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            float result = biomeHeight.added(rtgWorld, x, y);
            return riverized(result, river);
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
        }
    }

    public static class SurfaceBYGBiome extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mix;
        private float mixHeight;

        public SurfaceBYGBiome(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
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
