package rtg.world.biome.realistic.defiledlands;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.gen.RTGChunkGenSettings;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import java.util.Random;


public class RealisticBiomeDLDesertDefiled extends RealisticBiomeDLBase {

    public RealisticBiomeDLDesertDefiled(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {
        this.getConfig().SURFACE_WATER_LAKE_MULT.set(0.0f);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaDesert();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaDesert(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock);
    }

    public static class TerrainVanillaDesert extends TerrainBase {

        public TerrainVanillaDesert() {

            super(64);
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            RTGChunkGenSettings settings = rtgWorld.getGeneratorSettings();
            float duneHeight = (minDuneHeight + settings.sandDuneHeight);

            duneHeight *= (1f + rtgWorld.simplexInstance(2).noise2f(x / 330f, y / 330f)) / 2f;

            float stPitch = 200f;    // The higher this is, the more smoothly dunes blend with the terrain
            float stFactor = duneHeight;
            float hPitch = 70;    // Dune scale
            float hDivisor = 40;

            return terrainPolar(x, y, rtgWorld, river, stPitch, stFactor, hPitch, hDivisor, base) + groundNoise(x, y, 1f, rtgWorld);
        }
    }

    public static class SurfaceVanillaDesert extends SurfaceDLBase {

        public SurfaceVanillaDesert(BiomeConfig config, IBlockState top, IBlockState fill) {

            super(config, top, fill);
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
                            //primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                        else if (depth == 0) {
                            primer.setBlockState(x, k, z, rand.nextInt(2) == 0 ? topBlock : fillerBlock);
                        }
                    }
                    else if (depth > -1 && depth < 5) {
                        primer.setBlockState(x, k, z, topBlock);
                    }
                    else if (depth < 8) {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }
                }
            }
        }
    }
}
