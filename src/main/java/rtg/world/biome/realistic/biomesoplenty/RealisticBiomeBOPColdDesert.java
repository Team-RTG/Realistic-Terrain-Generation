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
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.util.SnowHeightCalculator;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBOPColdDesert extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.cold_desert.get();
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeBOPColdDesert() {

        super(biome, river);
    }

    @Override
    public void initConfig() {}

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPColdDesert();
    }

    public class TerrainBOPColdDesert extends TerrainBase {

        private float ruggedness = 3f;
        private float ruggednessWavelength = 100f;
        private float heightPitch = 35f;// the ruggedness parameter will multiply this by 0.2
        private float heightDivisor = 1f;

        public TerrainBOPColdDesert() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            float result = terrainPlains(x, y, simplex, river, ruggednessWavelength, ruggedness, heightPitch, heightDivisor, base);
            // no indentations; cutoff is not noticeable with these low slopes
            return result > base ? result : base;
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPColdDesert(config,
            Blocks.SNOW.getDefaultState(), //Block top
            biome.fillerBlock, //Block filler,
            Blocks.SNOW.getDefaultState(), //IBlockState mixTop,
            biome.fillerBlock, //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceBOPColdDesert extends SurfaceBase {

        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBOPColdDesert(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                                    float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = mixTop;
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            boolean water = false;
            boolean riverPaint = false;
            boolean grass = false;

            if (river > 0.05f && river + (simplex.noise2(i / 10f, j / 10f) * 0.1f) > 0.86f) {
                riverPaint = true;

                if (simplex.noise2(i / 12f, j / 12f) > 0.25f) {
                    grass = true;
                }
            }

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (riverPaint) {
                        if (grass && depth < 4) {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                        else if (depth == 0) {
                            if (rand.nextInt(2) == 0) {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                        }
                    }
                    else if (depth > -1 && depth < 9) {
                        primer.setBlockState(x, k, y, topBlock);

                        if (depth == 0 && k > 61 && k < 254) {
                            SnowHeightCalculator.calc(x, k, y, primer, noise);
                        }
                    }
                }
                else if (!water && b == Blocks.WATER) {
                    primer.setBlockState(x, k, y, Blocks.ICE.getDefaultState());
                    water = true;
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
