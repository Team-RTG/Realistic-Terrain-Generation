package rtg.world.biome.realistic.defiledlands;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import java.util.Random;


public class RealisticBiomeDLIcePlainsDefiled extends RealisticBiomeDLBase {

    public RealisticBiomeDLIcePlainsDefiled(Biome biome) {

        super(biome, RiverType.FROZEN, BeachType.COLD);
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().USE_ARCTIC_SURFACE).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaIcePlains();
    }

    @Override
    public SurfaceBase initSurface() {

        if (this.getConfig().USE_ARCTIC_SURFACE.get()) {

            return new SurfacePolar(getConfig(), baseBiome().topBlock, //Block top
                    baseBiome().fillerBlock, //Block filler,
                    baseBiome().topBlock, //IBlockState mixTop,
                    baseBiome().fillerBlock, //IBlockState mixFill,
                    80f, //float mixWidth,
                    -0.15f, //float mixHeight,
                    10f, //float smallWidth,
                    0.5f //float smallStrength
            );
        }
        else {

            return new SurfaceVanillaIcePlains(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, baseBiome().topBlock, baseBiome().topBlock);
        }

    }

    public static class TerrainVanillaIcePlains extends TerrainBase {

        public TerrainVanillaIcePlains() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 160f, 10f, 60f, 200f, 65f);
        }
    }

    public static class SurfaceVanillaIcePlains extends SurfaceDLBase {

        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;

        public SurfaceVanillaIcePlains(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2) {
            super(config, top, filler);

            cliffBlock1 = cliff1;
            cliffBlock2 = cliff2;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
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
                            primer.setBlockState(x, k, z, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    public static class SurfacePolar extends SurfaceDLBase {

        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfacePolar(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                            float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = mixTop;
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            boolean water = false;
            boolean riverPaint = false;
            boolean grass = false;

            if (river > 0.05f && river + (simplex.noise2f(i / 10f, j / 10f) * 0.1f) > 0.86f) {
                riverPaint = true;

                if (simplex.noise2f(i / 12f, j / 12f) > 0.25f) {
                    grass = true;
                }
            }

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (riverPaint) {
                        if (grass && depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                        else if (depth == 0) {
                            if (rand.nextInt(2) == 0) {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                        }
                    }
                    else if (depth > -1 && depth < 9) {
                        primer.setBlockState(x, k, z, topBlock);

                        if (depth == 0 && k > 61 && k < 254) {
                            WorldUtil.Terrain.calcSnowHeight(x, k, z, primer, noise);
                        }
                    }
                }
                else if (!water && b == Blocks.WATER) {
                    primer.setBlockState(x, k, z, Blocks.ICE.getDefaultState());
                    water = true;
                }
            }
        }
    }
}
