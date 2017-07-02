package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.world.IRTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.HeightVariation;
import rtg.api.world.terrain.heighteffect.HillockEffect;

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

        private final float bottom = 62f;
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
            smallHills.height = 5;
            smallHills.wavelength = 25;
            smallHills.minimumSimplex = 0.2f;
            smallHills.octave = 1;

            mediumHills = new HillockEffect();
            mediumHills.height = 10;
            mediumHills.wavelength = 40;
            mediumHills.minimumSimplex = 0.2f;
            mediumHills.octave = 2;

        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            float increment = bottomVariation.added(rtgWorld, x, y) + smallHills.added(rtgWorld, x, y);
            increment += mediumHills.added(rtgWorld, x, y);
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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

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

    @Override
    public void initDecos() {
        DecoBOPBaseBiomeDecorations decoBOPBaseBiomeDecorations = new DecoBOPBaseBiomeDecorations();
        this.addDeco(decoBOPBaseBiomeDecorations);
    }
}
