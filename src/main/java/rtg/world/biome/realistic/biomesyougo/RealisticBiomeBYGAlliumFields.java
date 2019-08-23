package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import java.util.Random;


public class RealisticBiomeBYGAlliumFields extends RealisticBiomeBYGBase {

    public RealisticBiomeBYGAlliumFields(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGBiome(65f, 80f, 25f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBYGBiome(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock);
    }

    public static class TerrainBYGBiome extends TerrainBase {

        private float minHeight;
        private float maxHeight;
        private float hillStrength;

        // 63f, 80f, 30f

        public TerrainBYGBiome(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.maxHeight = (maxHeight > rollingHillsMaxHeight) ? rollingHillsMaxHeight : ((maxHeight < this.minHeight) ? rollingHillsMaxHeight : maxHeight);
            this.hillStrength = hillStrength;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainPlains(x, y, rtgWorld, river, 200f, 1f, 30f, 1f, maxHeight);
        }
    }

    public static class SurfaceBYGBiome extends SurfaceBase {

        public SurfaceBYGBiome(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
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
