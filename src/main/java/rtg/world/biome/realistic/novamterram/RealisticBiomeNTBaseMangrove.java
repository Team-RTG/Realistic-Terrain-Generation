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
import rtg.api.world.terrain.heighteffect.BumpyHillsEffect;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.world.biome.realistic.biomesyougo.RealisticBiomeBYGLushDesert;

import javax.annotation.Nonnull;
import java.util.Random;


public abstract class RealisticBiomeNTBaseMangrove extends RealisticBiomeNTBase {

    public RealisticBiomeNTBaseMangrove(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomeNTBaseMangrove(@Nonnull final Biome baseBiome) {

        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseMangrove(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {

        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseMangrove(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {

        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public Biome preferredBeach() {

        return baseBiome();
    }

    @Override
    public void initConfig() {

    }

    @Override
    public double waterLakeMult() {

        return 4d;
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPMangrove();
    }

    @Override
    public SurfaceBase initSurface() {

        return new RealisticBiomeBYGLushDesert.SurfaceBOPLushDesert(getConfig(), baseBiome().topBlock, //Block top
            baseBiome().fillerBlock, //Block filler,
            baseBiome().topBlock, //IBlockState mixTop,
            baseBiome().fillerBlock, //IBlockState mixFill,
            40f, //float mixWidth,
            0.5f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public static class TerrainBOPMangrove extends TerrainBase {

        public TerrainBOPMangrove() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainBeach(x, y, rtgWorld, river, 60f);
        }
    }

    public static class SurfaceBOPLushDesert extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBOPLushDesert(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
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
            float c = TerrainBase.calcCliff(x, z, noise);
            boolean cliff = c > 3.4f;
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
                            if (simplex.noise2f(i / floMixWidth, j / floMixWidth) + simplex.noise2f(i / floSmallWidth, j / floSmallWidth)
                                                                                        * floSmallStrength > floMixHeight) {
                                primer.setBlockState(x, k, z, blockMixTop);

                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, z, blockMixFiller);
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
