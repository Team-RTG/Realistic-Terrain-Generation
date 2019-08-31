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
import rtg.api.world.terrain.heighteffect.GroundEffect;

import javax.annotation.Nonnull;
import java.util.Random;


public abstract class RealisticBiomeNTBasePlains extends RealisticBiomeNTBase {

    public RealisticBiomeNTBasePlains(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType, @Nonnull final BeachType beachType) {
        super(baseBiome, riverType, beachType);
    }

    public RealisticBiomeNTBasePlains(@Nonnull final Biome baseBiome) {
        this(baseBiome, RiverType.NORMAL, BeachType.NORMAL);
    }

    public RealisticBiomeNTBasePlains(@Nonnull final Biome baseBiome, @Nonnull final RiverType riverType) {
        this(baseBiome, riverType, BeachType.NORMAL);
    }

    public RealisticBiomeNTBasePlains(@Nonnull final Biome baseBiome, @Nonnull final BeachType beachType) {
        this(baseBiome, RiverType.NORMAL, beachType);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaPlains();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaPlains(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock);
    }

    public static class TerrainVanillaPlains extends TerrainBase {

        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaPlains() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 66f);
            return riverized(65f + groundEffect.added(rtgWorld, x, y), river);
        }
    }

    public static class SurfaceVanillaPlains extends SurfaceBase {

        public SurfaceVanillaPlains(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = TerrainBase.calcCliff(x, z, noise);
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
}
