package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.HeightVariation;
import rtg.api.world.terrain.heighteffect.JitterEffect;
import rtg.world.biome.realistic.RealisticBiomeBase;


public class RealisticBiomeBOPDeadSwamp extends RealisticBiomeBase {

    public static Biome biome = BOPBiomes.dead_swamp.get();

    public RealisticBiomeBOPDeadSwamp() {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
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

        return new SurfaceBOPDeadSwamp(getConfig(), BOPBlocks.grass.getDefaultState(), BOPBlocks.dirt.getDefaultState());
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    public class TerrainBOPDeadSwamp extends TerrainBase {

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

    public class SurfaceBOPDeadSwamp extends SurfaceBase {

        public SurfaceBOPDeadSwamp(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

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

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
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
