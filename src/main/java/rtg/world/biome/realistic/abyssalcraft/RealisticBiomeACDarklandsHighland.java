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

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHills;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeACDarklandsHighland extends RealisticBiomeACBase {

    public static Biome biome = ACBiomes.darklands_hills;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeACDarklandsHighland(BiomeConfig config) {

        super(config, biome, river);

        this.noLakes = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainACDarklandsHighland(10f, 120f, 10f, 200f);
    }

    public class TerrainACDarklandsHighland extends TerrainBase {

        private float start;
        private float height;
        private float width;

        public TerrainACDarklandsHighland(float hillStart, float landHeight, float baseHeight, float hillWidth) {

            start = hillStart;
            height = landHeight;
            base = baseHeight;
            width = hillWidth;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainHighland(x, y, simplex, cell, river, start, width, height, base);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceACDarklandsHighland(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, 60f, -0.14f, 14f, 0.25f);
    }

    public class SurfaceACDarklandsHighland extends SurfaceACBase {

        private IBlockState mixBlockTop;
        private byte mixBlockTopMeta;
        private IBlockState mixBlockFill;
        private byte mixBlockFillMeta;
        private float width;
        private float height;
        private float smallW;
        private float smallS;

        public SurfaceACDarklandsHighland(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, float mixWidth,
                                          float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            mixBlockTop = this.getConfigBlock(config, BiomeConfigVanillaExtremeHills.surfaceMixBlockId, BiomeConfigVanillaExtremeHills.surfaceMixBlockMetaId, mixTop);

            mixBlockFill = this.getConfigBlock(config, BiomeConfigVanillaExtremeHills.surfaceMixFillerBlockId, BiomeConfigVanillaExtremeHills.surfaceMixFillerBlockMetaId, mixFill);

            width = mixWidth;
            height = mixHeight;
            smallW = smallWidth;
            smallS = smallStrength;
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
                            if (simplex.noise2(i / width, j / width) + simplex.noise2(i / smallW, j / smallW) * smallS > height) {
                                primer.setBlockState(x, k, y, mixBlockTop);
                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, y, mixBlockFill);
                            }
                            else {
                                primer.setBlockState(x, k, y, fillerBlock);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

    }
}
