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

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPChaparral extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.chaparral.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPChaparral(BiomeConfig config) {

        super(config, biome, river);

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPChaparral();
    }

    public class TerrainBOPChaparral extends TerrainBase {

        private float baseHeight = 76f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 40f;
        private float smoothHillWavelength = 60f;
        private float smoothHillStrength = 30f;


        private SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
        private float wavelength = 10f;// of jitter
        private float amplitude = 2f;// of jitter

        public TerrainBOPChaparral() {

        }

        public TerrainBOPChaparral(float bh, float hs) {

            baseHeight = bh;
            peakyHillStrength = hs;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, simplex);

            //float m = hills(x, y, peakyHillStrength, simplex, river);

            simplex.riverJitter().evaluateNoise((float) x / wavelength, (float) y / wavelength, jitter);
            int pX = (int) Math.round(x + jitter.deltax() * amplitude);
            int pY = (int) Math.round(y + jitter.deltay() * amplitude);
            float h = this.terrainGrasslandHills(pX, pY, simplex, cell, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

            return groundNoise + h;
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPChaparral(config, biome.topBlock, biome.fillerBlock, Blocks.SAND.getDefaultState(), 26f, 0.35f);
    }

    public class SurfaceBOPChaparral extends SurfaceBase {

        private IBlockState mixBlock;
        private float width;
        private float height;

        public SurfaceBOPChaparral(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mix, float mixWidth, float mixHeight) {

            super(config, top, filler);

            mixBlock = mix;

            width = mixWidth;
            height = mixHeight;
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
                            if (simplex.noise2(i / width, j / width) > height) // > 0.27f, i / 12f
                            {
                                primer.setBlockState(x, k, y, mixBlock);
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
