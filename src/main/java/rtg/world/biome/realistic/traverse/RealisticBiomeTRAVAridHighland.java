package rtg.world.biome.realistic.traverse;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVAridHighland extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVAridHighland(Biome biome) {

        super(biome);
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

        return new TerrainHLAdirondacks(230f, 60f, 88f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaForest(getConfig(), this.baseBiome().topBlock, this.baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, this.baseBiome().topBlock, 0.6f, Blocks.GRASS.getDefaultState(), -0.4f);
    }

    public static class TerrainHLAdirondacks extends TerrainBase {

        private float width;
        private float strength;
        private float terrainHeight;
        private int wavelength = 40;
        private ISimplexData2D jitter = SimplexData2D.newDisk();
        private double amplitude = 10;

        public TerrainHLAdirondacks(float mountainWidth, float mountainStrength, float height) {
            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            rtgWorld.simplexInstance(1).multiEval2D((float) x / wavelength, (float) y / wavelength, jitter);
            int pX = (int) Math.round(x + jitter.getDeltaX() * amplitude);
            int pY = (int) Math.round(y + jitter.getDeltaY() * amplitude);
            x = pX;
            y = pY;

            return terrainLonelyMountain(x, y, rtgWorld, river, strength, width, terrainHeight);
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
                            primer.setBlockState(x, k, z, mix2Block);
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, topBlock);
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
                            primer.setBlockState(x, k, z, mix2Block);
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, topBlock);
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
