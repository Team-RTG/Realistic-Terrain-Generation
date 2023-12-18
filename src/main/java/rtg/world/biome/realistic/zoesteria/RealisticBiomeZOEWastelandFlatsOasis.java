package rtg.world.biome.realistic.zoesteria;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightVariation;

import java.util.Random;


public class RealisticBiomeZOEWastelandFlatsOasis extends RealisticBiomeZOEBase {

    public RealisticBiomeZOEWastelandFlatsOasis(Biome biome) {
        super(biome);
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

        return new TerrainBOPMarsh();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPMarsh(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock);
    }

    public static class TerrainBOPMarsh extends TerrainBase {

        private float baseHeight = 62f;
        private HeightVariation variation;
        private HeightVariation smallVariation;

        public TerrainBOPMarsh() {

            variation = new HeightVariation();
            variation.height = 1.5f;
            variation.wavelength = 20;
            variation.octave = 0;

            smallVariation = new HeightVariation();
            smallVariation.height = 1.5f;
            smallVariation.wavelength = 10;
            smallVariation.octave = 0;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return baseHeight + variation.added(rtgWorld, x, y) + smallVariation.added(rtgWorld, x, y);
        }
    }

    public static class SurfaceBOPMarsh extends SurfaceBase {

        public SurfaceBOPMarsh(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = TerrainBase.calcCliff(x, z, noise, river);
            boolean cliff = c > 1.4f;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff && k > 64) {
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
