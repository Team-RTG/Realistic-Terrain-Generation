package rtg.world.biome.realistic.biomesoplenty;

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


public class RealisticBiomeBOPColdDesert extends RealisticBiomeBase {

    public RealisticBiomeBOPColdDesert(final Biome biome) { super(biome, RiverType.FROZEN, BeachType.COLD); }

    @Override
    public void initDecos() {}

    @Override
    public void initConfig() {
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPColdDesert();
    }

    @Override
    public SurfaceBase initSurface() {
        return new SurfaceBOPColdDesert(getConfig(), Blocks.SNOW.getDefaultState(), baseBiome().fillerBlock, Blocks.SNOW.getDefaultState(), baseBiome().fillerBlock, 80f, -0.15f, 10f, 0.5f );
    }

    public static class TerrainBOPColdDesert extends TerrainBase {

        private float ruggedness = 3f;
        private float ruggednessWavelength = 100f;
        private float heightPitch = 35f;// the ruggedness parameter will multiply this by 0.2
        private float heightDivisor = 1f;

        public TerrainBOPColdDesert() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            float result = terrainPlains(x, y, rtgWorld, river, ruggednessWavelength, ruggedness, heightPitch, heightDivisor, base);
            // no indentations; cutoff is not noticeable with these low slopes
            return result > base ? result : base;
        }
    }

    public static class SurfaceBOPColdDesert extends SurfaceBase {

        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBOPColdDesert(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
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
                            TerrainBase.calcSnowHeight(x, k, z, primer, noise);
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
