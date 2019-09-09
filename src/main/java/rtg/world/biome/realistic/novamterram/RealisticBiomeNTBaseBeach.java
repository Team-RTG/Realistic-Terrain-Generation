package rtg.world.biome.realistic.novamterram;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import javax.annotation.Nonnull;
import java.util.Random;


public abstract class RealisticBiomeNTBaseBeach extends RealisticBiomeNTBase {

    public RealisticBiomeNTBaseBeach(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {

        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomeNTBaseBeach(@Nonnull final Biome baseBiome) {

        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseBeach(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {

        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeNTBaseBeach(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {

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
    public TerrainBase initTerrain() {

        return new TerrainVanillaBeach();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaBeach(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock);
    }

    public static class TerrainVanillaBeach extends TerrainBase {

        public TerrainVanillaBeach() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainBeach(x, y, rtgWorld, river, 63f);
        }
    }

    public static class SurfaceVanillaBeach extends SurfaceBase {

        private IBlockState mixBlockFill;

        public SurfaceVanillaBeach(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);

            mixBlockFill = this.getConfigBlock(config.SURFACE_MIX_FILLER_BLOCK.get(), top);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();

                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0 && k > 61) {
                        primer.setBlockState(x, k, z, topBlock);
                    }
                    else if (k > 63 && depth > 3 && depth < 6) {
                        primer.setBlockState(x, k, z, mixBlockFill);
                    }
                    else if (depth < 4) {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }
                }
            }
        }
    }
}
