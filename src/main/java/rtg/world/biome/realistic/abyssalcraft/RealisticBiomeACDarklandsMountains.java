package rtg.world.biome.realistic.abyssalcraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.HeightVariation;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.api.world.terrain.heighteffect.MountainsWithPassesEffect;


public class RealisticBiomeACDarklandsMountains extends RealisticBiomeBase {

    public RealisticBiomeACDarklandsMountains(final Biome biome) { super(biome); }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().SURFACE_CLIFF_STONE_BLOCK.set("abyssalcraft:stone");
        this.getConfig().SURFACE_CLIFF_COBBLE_BLOCK.set("abyssalcraft:cobblestone");
    }

    @Override
    public void initDecos() { }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACDarklandsMountains(120f, 100f);
    }

    public static class TerrainACDarklandsMountains extends TerrainBase {

        private float width;
        private float strength;
        private float spikeWidth = 40;
        private float spikeHeight = 60;
        protected HeightEffect height;

        public TerrainACDarklandsMountains(float mountainWidth, float mountainStrength) {

            this(mountainWidth, mountainStrength, 90f);
        }

        public TerrainACDarklandsMountains(float mountainWidth, float mountainStrength, float baseHeight) {

            width = mountainWidth;
            strength = mountainStrength;
            base = baseHeight;
            MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
            mountainEffect.mountainHeight = strength;
            mountainEffect.mountainWavelength = width;
            mountainEffect.spikeHeight = this.spikeHeight;
            mountainEffect.spikeWavelength = this.spikeWidth;

            this.height = new JitterEffect(6f, 10f, mountainEffect);
            height = new JitterEffect(2f, 6f, height);

            HeightVariation passHeight = new HeightVariation();
            passHeight.height = 15;
            passHeight.octave = 4;
            passHeight.wavelength = 70;

            height = height.plus(passHeight);
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return riverized(height.added(rtgWorld, x, y) + base, river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceACDarklandsMountains(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, 0.2f);
    }

    public static class SurfaceACDarklandsMountains extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float iCliff = 0.3f;
        private float iHeight = 100f;
        private float iStrength = 50f;
        private float cCliff = 1.5f;

        public SurfaceACDarklandsMountains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff) {

            super(config, top, fill);
            min = minCliff;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = TerrainBase.calcCliff(x, z, noise);
            int cliff = 0;

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
                        if (k > 110 + (p * 4) && c < iCliff + ((k - iHeight) / iStrength) + p) {
                            cliff = 3;
                        }

                        if (cliff == 1 || cliff == 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, z, hcStone());
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
                            primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1 || cliff == 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                        else {
                            primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                        }
                    }
                }
            }
        }
    }
}
