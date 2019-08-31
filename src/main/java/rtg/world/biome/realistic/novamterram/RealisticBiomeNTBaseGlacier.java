package rtg.world.biome.realistic.novamterram;

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

import javax.annotation.Nonnull;
import java.util.Random;


public abstract class RealisticBiomeNTBaseGlacier extends RealisticBiomeNTBase {

    public RealisticBiomeNTBaseGlacier(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {
        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomeNTBaseGlacier(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseGlacier(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseGlacier(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPGlacier(230f, 40f, 68f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPGlacier(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock,
                baseBiome().topBlock, baseBiome().fillerBlock,
                Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(),
                60f, -0.14f, 14f, 0.25f
        );
    }

    public static class TerrainBOPGlacier extends TerrainBase {

        private float width;
        private float strength;

        public TerrainBOPGlacier(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            base = height;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, rtgWorld, river, strength, width, base);
        }
    }

    public static class SurfaceBOPGlacier extends SurfaceBase {

        private IBlockState mixBlockTop;
        private IBlockState mixBlockFill;
        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;
        private float width;
        private float height;
        private float smallW;
        private float smallS;

        public SurfaceBOPGlacier(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, IBlockState cliff1, IBlockState cliff2, float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            mixBlockTop = mixTop;
            mixBlockFill = mixFill;
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
