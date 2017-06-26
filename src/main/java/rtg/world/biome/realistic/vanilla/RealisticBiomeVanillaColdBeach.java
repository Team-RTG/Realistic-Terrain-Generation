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
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaColdBeach extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.COLD_BEACH;
    public static Biome river = Biomes.FROZEN_RIVER;

    public RealisticBiomeVanillaColdBeach() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_VILLAGES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaColdBeach();
    }

    public class TerrainVanillaColdBeach extends TerrainBase {

        public TerrainVanillaColdBeach() {

        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainBeach(x, y, rtgWorld.simplex(), river, 180f, 35f, 63f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaColdBeach(config, biome.topBlock, biome.fillerBlock, biome.topBlock, biome.fillerBlock, (byte) 0, 1);
    }

    public class SurfaceVanillaColdBeach extends SurfaceBase {

        private IBlockState cliffBlock1;
        private IBlockState cliffBlock2;
        private byte sandMetadata;
        private int cliffType;

        public SurfaceVanillaColdBeach(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState cliff1, IBlockState cliff2, byte metadata, int cliff) {

            super(config, top, filler);

            cliffBlock1 = cliff1;
            cliffBlock2 = cliff2;
            sandMetadata = metadata;
            cliffType = cliff;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.3f ? true : false;
            boolean dirt = false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (cliffType == 1) {
                            if (depth < 6) {
                                primer.setBlockState(x, k, z, cliffBlock1.getBlock().getDefaultState());
                            }
                        }
                        else {
                            if (depth > -1 && depth < 2) {
                                primer.setBlockState(x, k, z, rand.nextInt(3) == 0 ? cliffBlock2 : cliffBlock1);
                            }
                            else if (depth < 10) {
                                primer.setBlockState(x, k, z, cliffBlock1);
                            }
                        }
                    }
                    else if (depth < 6) {
                        if (depth == 0 && k > 61) {
                            if (simplex.noise2(i / 12f, j / 12f) > -0.3f + ((k - 61f) / 15f)) {
                                dirt = true;
                                primer.setBlockState(x, k, z, topBlock);
                            }
                            else {
                                if (k < 69) {
                                    primer.setBlockState(x, k, z, BlockUtil.getStateSand(sandMetadata));
                                } // else probably steep shore so leave stone

                            }
                        }
                        else if (depth < 4) {
                            if (dirt) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                            else {
                                if (k < 69) {
                                    primer.setBlockState(x, k, z, BlockUtil.getStateSand(sandMetadata));
                                }
                            }
                        }
                        else if (!dirt) {
                            primer.setBlockState(x, k, z, Blocks.SANDSTONE.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setChance(16);
        decoBoulder.setMaxY(95);
        decoBoulder.setStrengthFactor(3f);
        this.addDeco(decoBoulder);
    }
}
