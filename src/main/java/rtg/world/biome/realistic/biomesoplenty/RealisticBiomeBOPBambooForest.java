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
import rtg.util.BlockUtil;
import rtg.api.util.noise.CellNoise;
import rtg.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.*;

public class RealisticBiomeBOPBambooForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.bamboo_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBambooForest() {

        super(biome, river);
    }

    @Override
    public void initConfig() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPBambooForest();
    }

    public class TerrainBOPBambooForest extends TerrainBase {

    /* Basic idea: High hilly terrain mixed with ground-noisy flats
     * using a transition that also generates the hills
     */

        private float hillockWavelength = 30;
        private float hillockBoost = 5;
        private float hillockVariance = 10;
        private float hillockSpikeWidth = 8;
        private float hillockSpikeHeight = 4;
        private float terrainBase = 68;

        private JitterEffect biomeHeight;// this includes the base


        public TerrainBOPBambooForest() {
            // bumpy hills on top
            BumpyHillsEffect onTop = new BumpyHillsEffect();
            onTop.hillHeight = hillockVariance;
            onTop.hillWavelength = hillockWavelength;
            onTop.spikeHeight = hillockSpikeHeight;
            onTop.spikeWavelength = hillockSpikeWidth;
            onTop.hillOctave = 1;// same octave as variableRuggedness

            // plus raised a bit
            HeightEffect hillLevel = onTop.plus(new RaiseEffect(hillockBoost + terrainBase));

            // but only
            VariableRuggednessEffect hills = new VariableRuggednessEffect();
            hills.ruggedTerrain = hillLevel;
            hills.smoothTerrain = new RaiseEffect(terrainBase);
            hills.octave = 1;// just to make it clear;
            hills.startTransition = 0.1f;
            hills.transitionWidth = 0.35f;
            hills.wavelength = hillockWavelength;

            HeightEffect unJittered = hills.plus(new GroundEffect(6f));

            // and lets scramble it a bit

            biomeHeight = new JitterEffect();
            biomeHeight.amplitude = 2;
            biomeHeight.wavelength = 5;
            biomeHeight.jittered = unJittered;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            float result = biomeHeight.added(simplex, cell, x, y);
            if (result < 60) {
                throw new RuntimeException();
            }
            return result;
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPBambooForest(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.15f);
    }

    public class SurfaceBOPBambooForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mix;
        private float mixHeight;

        public SurfaceBOPBambooForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                      float stoneHeight, float stoneStrength, float clayCliff, IBlockState mixBlock, float mixSize) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mix = mixBlock;
            mixHeight = mixSize;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            int cliff = 0;
            boolean m = false;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0) {

                        float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                        if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                            cliff = 1;
                        }
                        if (c > cCliff) {
                            cliff = 2;
                        }

                        if (cliff == 1) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, y, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (simplex.noise2(i / 12f, j / 12f) > mixHeight) {
                            primer.setBlockState(x, k, y, mix);
                            m = true;
                        }
                        else {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else {
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
