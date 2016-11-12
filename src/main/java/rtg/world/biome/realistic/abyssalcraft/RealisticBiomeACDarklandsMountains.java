package rtg.world.biome.realistic.abyssalcraft;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import com.shinoow.abyssalcraft.api.biome.ACBiomes;
import com.shinoow.abyssalcraft.api.block.ACBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.*;

public class RealisticBiomeACDarklandsMountains extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands_mountains;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklandsMountains(BiomeConfig config) {

        super(config, biome, river);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACDarklandsMountains(120f, 100f);
    }

    public class TerrainACDarklandsMountains extends FunctionalTerrainBase {

        private float width;
        private float strength;
        private float spikeWidth = 40;
        private float spikeHeight = 60;

        public TerrainACDarklandsMountains(float mountainWidth, float mountainStrength) {

            this(mountainWidth, mountainStrength, 90f);
        }

        public TerrainACDarklandsMountains(float mountainWidth, float mountainStrength, float baseHeight) {

            width = mountainWidth;
            strength = mountainStrength;
            base = baseHeight;
            MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
            mountainEffect.mountainHeight = strength;
            mountainEffect.mountainWavelength = width;
            mountainEffect.spikeHeight = this.spikeHeight;
            mountainEffect.spikeWavelength = this.spikeWidth;

            this.height = new JitterEffect(6f, 10f, mountainEffect);
            height = new JitterEffect(2f, 6f, height);

            HeightVariation passHeight = new HeightVariation();
            passHeight.height = 15;
            passHeight.octave = 4;
            passHeight.wavelength = 70;

            height = height.plus(passHeight);

        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceACDarklandsMountains(config, biome.topBlock, biome.fillerBlock, 0.2f);
    }

    public class SurfaceACDarklandsMountains extends SurfaceACBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float iCliff = 0.3f;
        private float iHeight = 100f;
        private float iStrength = 50f;
        private float cCliff = 1.5f;

        public SurfaceACDarklandsMountains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff) {

            super(config, top, fill);
            min = minCliff;
        }

        public SurfaceACDarklandsMountains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                           float stoneHeight, float stoneStrength, float snowCliff, float snowHeight, float snowStrength, float clayCliff) {

            this(config, top, fill, minCliff);

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            iCliff = snowCliff;
            iHeight = snowHeight;
            iStrength = snowStrength;
            cCliff = clayCliff;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            int cliff = 0;

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
                        if (k > 110 + (p * 4) && c < iCliff + ((k - iHeight) / iStrength) + p) {
                            cliff = 3;
                        }

                        if (cliff == 1 || cliff == 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, y, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else {
                            primer.setBlockState(x, k, y, ACBlocks.darklands_grass.getDefaultState());
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1 || cliff == 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else {
                            primer.setBlockState(x, k, y, Blocks.DIRT.getDefaultState());
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
