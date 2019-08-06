package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.HeightVariation;
import rtg.api.world.terrain.heighteffect.JitterEffect;


public class RealisticBiomeBOPDeadSwamp extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.dead_swamp.orNull();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPDeadSwamp() {

        super(biome);
    }

    @Override
    public Biome preferredBeach() {
        return biome;
    }

    @Override
    public void initConfig() {
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPDeadSwamp();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPDeadSwamp(getConfig(), BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState(), BOPBlocks.mud.getDefaultState());
    }

    public static class TerrainBOPDeadSwamp extends TerrainBase {

        private HeightEffect height;

        public TerrainBOPDeadSwamp() {

            HeightVariation waterLand = new HeightVariation();
            waterLand.height = 2f;
            waterLand.wavelength = 40f;
            waterLand.octave = 0;

            height = new JitterEffect(5f, 10f, waterLand);

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return 62f + height.added(rtgWorld, x, y);
        }
    }

    public static class SurfaceBOPDeadSwamp extends SurfaceBase {

        private IBlockState mix;
        private float mixHeight;

        public SurfaceBOPDeadSwamp(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixBlock) {

            super(config, top, filler);
            mix = mixBlock;
            mixHeight = .1f;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(2);
            float c = Terrain.calcCliff(x, z, noise);
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

                            if (simplex.noise2f(i / 12f, j / 12f) > mixHeight + (noise[x * 16 + z] - 63f) / 10f) {
                                primer.setBlockState(x, k, z, mix);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
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
