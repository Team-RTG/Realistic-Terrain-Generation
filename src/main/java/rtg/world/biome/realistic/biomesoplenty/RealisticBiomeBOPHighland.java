package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.config.BiomeConfig;
import rtg.util.CliffCalculator;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.BumpyHillsEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPHighland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.highland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPHighland() {

        super(biome, river);

        this.generatesEmeralds = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPHighland();
    }

    public class TerrainBOPHighland extends TerrainBase {

        private float baseHeight = 90f;
        private BumpyHillsEffect onTop = new BumpyHillsEffect();
        private JitterEffect withJitter;

        public TerrainBOPHighland() {

            onTop.hillHeight = 30;
            onTop.hillWavelength = 60;
            onTop.spikeHeight = 20;
            onTop.spikeWavelength = 10;

            withJitter = new JitterEffect();
            withJitter.amplitude = 2;
            withJitter.wavelength = 5;
            withJitter.jittered = onTop;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return riverized(baseHeight + withJitter.added(simplex, cell, x, y) + this.groundNoise(x, y, 6, simplex), river);
            //return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPHighland(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceBOPHighland extends SurfaceBase {

        public SurfaceBOPHighland(BiomeConfig config, IBlockState top, IBlockState filler) {

            super(config, top, filler);
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
