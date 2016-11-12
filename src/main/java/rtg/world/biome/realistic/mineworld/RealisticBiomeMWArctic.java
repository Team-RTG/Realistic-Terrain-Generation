package rtg.world.biome.realistic.mineworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.mineworld.BiomeConfigMWArctic;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeMWArctic extends RealisticBiomeMWBase {

    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeMWArctic(Biome biome, BiomeConfig config) {

        super(config, biome, river);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainMWArctic();
    }

    public class TerrainMWArctic extends TerrainBase {

        private float ruggedness = 3f;
        private float ruggednessWavelength = 100f;
        private float heightPitch = 35f;// the ruggedness parameter will multiply this by 0.2
        private float heightDivisor = 1f;

        public TerrainMWArctic() {

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

        return new SurfaceMWArctic(config,
            this.baseBiome.topBlock, //Block top
            this.baseBiome.fillerBlock, //Block filler,
            Blocks.SNOW.getDefaultState(), //IBlockState mixTop,
            this.baseBiome.fillerBlock, //IBlockState mixFill,
            80f, //float mixWidth,
            -0.15f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceMWArctic extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceMWArctic(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                               float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = this.getConfigBlock(config, BiomeConfigMWArctic.surfaceMixBlockId,
                BiomeConfigMWArctic.surfaceMixBlockMetaId, mixTop);
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            boolean cliff = c > 1.4f ? true : false;
            boolean mix = false;

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
                            if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
                                * floSmallStrength > floMixHeight) {
                                primer.setBlockState(x, k, y, blockMixTop);

                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, y, blockMixFiller);
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
        protected IBlockState hcStone(World world, int i, int j, int x, int y, int k) {

            return topBlock;
        }

        @Override
        protected IBlockState hcCobble(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {

            return topBlock;
        }
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
