package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.world.biome.deco.collection.DecoCollectionExtremeHillsCommon;
import rtg.world.biome.deco.collection.DecoCollectionExtremeHillsPlusM;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaExtremeHillsPlusM extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_EXTREME_HILLS_WITH_TREES;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaExtremeHillsPlusM() {

        super(biome, river);

        this.generatesEmeralds = true;
        this.generatesSilverfish = true;
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

       return new RealisticBiomeVanillaExtremeHills.RidgedExtremeHills(190f, 67f, 200f);
    }

    public class TerrainVanillaExtremeHillsPlusM extends TerrainBase {

        private float width;
        private float strength;

        public TerrainVanillaExtremeHillsPlusM(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            base = height;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, rtgWorld.simplex, rtgWorld.cell, river, strength, width, base);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaExtremeHillsPlusM(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), -0.8f, 1.5f, 60f, 65f, 1.5f, Blocks.GRAVEL.getDefaultState(), BlockUtil.getStateDirt(1), -0.5f, -0.7f);
    }

    public class SurfaceVanillaExtremeHillsPlusM extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private IBlockState mix2Block;
        private float mixHeight;
        private float mix2Height;

        public SurfaceVanillaExtremeHillsPlusM(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                           float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, IBlockState mix2, float mixSize, float mix2Size) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), mix);
            mix2Block = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_2_BLOCK_META.get(), mix2);
            mixHeight = mixSize;
            mix2Height = mix2Size;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;
            float c = CliffCalculator.calc(x, z, noise);
            int cliff = 0;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0) {

                        float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
                        float mixNoise = simplex.noise2(i / 12f, j / 12f);

                        //Logger.debug("%f", mixNoise);

                        if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                            cliff = 1;
                        }
                        if (c > cCliff) {
                            cliff = 2;
                        }

                        if (cliff == 1) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (mixNoise > mixHeight) {
                            primer.setBlockState(x, k, z, mixBlock);
                        }
                        else {
                            if (mixNoise > mix2Height) {
                                primer.setBlockState(x, k, z, mix2Block);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionExtremeHillsPlusM());
        this.addDecoCollection(new DecoCollectionExtremeHillsCommon(this.getConfig().ALLOW_LOGS.get()));
    }
}
