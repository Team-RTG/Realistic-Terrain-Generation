package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.BiomeConfigProperty;
import rtg.util.CellNoise;
import rtg.util.CliffCalculator;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaIceMountains extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.ICE_MOUNTAINS;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaIceMountains() {

        super(biome, river);

        this.noLakes = true;
    }

    @Override
    public void initConfig() {

        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixBlockId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockName, "", ""));
        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixBlockMetaId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixBlockMetaName, "", ""));
        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixFillerBlockId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixFillerBlockName, "", ""));
        this.config.addProperty(new BiomeConfigProperty(BiomeConfig.surfaceMixFillerBlockMetaId, BiomeConfigProperty.Type.STRING, BiomeConfig.surfaceMixFillerBlockMetaName, "", ""));
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaIceMountains(230f, 60f, 68f);
    }

    public class TerrainVanillaIceMountains extends TerrainBase {

        private float width;
        private float strength;
        private float terrainHeight;

        public TerrainVanillaIceMountains(float mountainWidth, float mountainStrength, float height) {

            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainLonelyMountain(x, y, simplex, cell, river, strength, width, terrainHeight);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaIceMountains(config, biome.topBlock, biome.fillerBlock, Blocks.SNOW.getDefaultState(), Blocks.SNOW.getDefaultState(), Blocks.PACKED_ICE.getDefaultState(), Blocks.ICE.getDefaultState(), 60f,
            -0.14f, 14f, 0.25f);
    }

    public class SurfaceVanillaIceMountains extends SurfaceBase {

        private IBlockState mixBlockTop;
        private IBlockState mixBlockFill;
        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;
        private float width;
        private float height;
        private float smallW;
        private float smallS;

        public SurfaceVanillaIceMountains(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFill, IBlockState cliff1, IBlockState cliff2, float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            mixBlockTop = this.getConfigBlock(config, BiomeConfig.surfaceMixBlockId,
                BiomeConfig.surfaceMixBlockMetaId,
                mixTop);

            mixBlockFill = this.getConfigBlock(config, BiomeConfig.surfaceMixFillerBlockId,
                BiomeConfig.surfaceMixFillerBlockMetaId,
                mixFill);

            cliffBlock1 = cliff1;
            cliffBlock2 = cliff2;

            width = mixWidth;
            height = mixHeight;
            smallW = smallWidth;
            smallS = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand, OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

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
                            primer.setBlockState(x, k, y, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, y, cliffBlock1);
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
