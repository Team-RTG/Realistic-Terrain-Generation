package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.config.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPBog extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.bog.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBog() {

        super(biome, river);
    }

    @Override
    public void initConfig() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBog();
    }

    public class TerrainBOPBog extends TerrainBase {

        private final float bottom = 58f;
        private final HeightVariation bottomVariation;
        private final HillockEffect smallHills;
        private final HillockEffect mediumHills;

        // surprisingly the BoP version is mostly above water and kind of hilly
        public TerrainBOPBog() {

            bottomVariation = new HeightVariation();
            bottomVariation.height = 2;
            bottomVariation.octave = 0;
            bottomVariation.wavelength = 40;

            smallHills = new HillockEffect();
            smallHills.height = 6;
            smallHills.wavelength = 15;
            smallHills.minimumSimplex = 0.2f;
            smallHills.octave = 1;

            mediumHills = new HillockEffect();
            mediumHills.height = 12;
            mediumHills.wavelength = 25;
            mediumHills.minimumSimplex = 0.2f;
            mediumHills.octave = 2;

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            float increment = bottomVariation.added(simplex, cell, x, y) + smallHills.added(simplex, cell, x, y);
            increment += mediumHills.added(simplex, cell, x, y);
            return riverized(bottom + increment, river);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPBog(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceBOPBog extends SurfaceBase {

        public SurfaceBOPBog(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            boolean cliff = c > 1.4f ? true : false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
