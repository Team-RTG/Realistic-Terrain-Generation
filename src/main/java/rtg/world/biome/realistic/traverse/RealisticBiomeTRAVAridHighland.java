package rtg.world.biome.realistic.traverse;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
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

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainHLAdirondacks(230f, 60f, 88f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceHLAdirondacks(
            getConfig(),
            this.baseBiome().topBlock,
            this.baseBiome().fillerBlock,
            Blocks.GRASS.getDefaultState(),
            Blocks.DIRT.getDefaultState(),
            60f, -0.14f, 14f, 0.25f
        );
    }

    public class TerrainHLAdirondacks extends TerrainBase {

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

    public class SurfaceHLAdirondacks extends SurfaceBase {

        private IBlockState mixBlockTop;
        private IBlockState mixBlockFill;
        private float width;
        private float height;
        private float smallW;
        private float smallS;

        public SurfaceHLAdirondacks(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, float mixWidth,
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
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 2.0f ? true : false;
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
