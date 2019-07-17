package rtg.world.biome.realistic.candyworld;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeCWGummySwamp extends RealisticBiomeCWBase {

    public RealisticBiomeCWGummySwamp(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaForest();
    }

    @Override
    public SurfaceBase initSurface() {

        IBlockState GUMMY_GREEN = BlockUtil.getBlockStateFromCfgString("candymod:gummy_block[type=green]");
        IBlockState GUMMY_ORANGE = BlockUtil.getBlockStateFromCfgString("candymod:gummy_block[type=orange]");
        IBlockState GUMMY_RED = BlockUtil.getBlockStateFromCfgString("candymod:gummy_block[type=red]");
        IBlockState GUMMY_WHITE = BlockUtil.getBlockStateFromCfgString("candymod:gummy_block[type=white]");
        IBlockState GUMMY_YELLOW = BlockUtil.getBlockStateFromCfgString("candymod:gummy_block[type=yellow]");

        IBlockState SOLID_GUMMY_GREEN = BlockUtil.getBlockStateFromCfgString("candymod:hardened_gummy_block[type=green]");
        IBlockState SOLID_GUMMY_ORANGE = BlockUtil.getBlockStateFromCfgString("candymod:hardened_gummy_block[type=orange]");
        IBlockState SOLID_GUMMY_RED = BlockUtil.getBlockStateFromCfgString("candymod:hardened_gummy_block[type=red]");
        IBlockState SOLID_GUMMY_WHITE = BlockUtil.getBlockStateFromCfgString("candymod:hardened_gummy_block[type=white]");
        IBlockState SOLID_GUMMY_YELLOW = BlockUtil.getBlockStateFromCfgString("candymod:hardened_gummy_block[type=yellow]");

        return new SurfaceVanillaForest(getConfig(), this.baseBiome().topBlock, this.baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, GUMMY_WHITE, 0.5f, GUMMY_YELLOW, 0.2f, GUMMY_ORANGE, -0.2f, GUMMY_GREEN, -0.5f, SOLID_GUMMY_WHITE, SOLID_GUMMY_YELLOW, SOLID_GUMMY_ORANGE, SOLID_GUMMY_GREEN);
    }

    public static class TerrainVanillaForest extends TerrainBase {

        private float hillStrength = 10f;// this needs to be linked to the

        public TerrainVanillaForest() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            groundNoise = groundNoise(x, y, groundVariation, rtgWorld);

            float m = hills(x, y, hillStrength, rtgWorld);

            float floNoise = 65f + groundNoise + m;

            return riverized(floNoise, river);
        }
    }

    public static class SurfaceVanillaForest extends SurfaceBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;
        private IBlockState mix2Block;
        private float mix2Height;
        private IBlockState mix3Block;
        private float mix3Height;
        private IBlockState mix4Block;
        private float mix4Height;

        private IBlockState mixFillBlock;
        private IBlockState mixFill2Block;
        private IBlockState mixFill3Block;
        private IBlockState mixFill4Block;

        public SurfaceVanillaForest(BiomeConfig config,
                                    IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                    float stoneHeight, float stoneStrength, float clayCliff,
                                    IBlockState mix, float mixHeight,
                                    IBlockState mix2, float mix2Height,
                                    IBlockState mix3, float mix3Height,
                                    IBlockState mix4, float mix4Height,
                                    IBlockState mixFill, IBlockState mixFill2, IBlockState mixFill3, IBlockState mixFill4) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            this.mixBlock = mix;
            this.mixHeight = mixHeight;
            this.mix2Block = mix2;
            this.mix2Height = mix2Height;
            this.mix3Block = mix3;
            this.mix3Height = mix3Height;
            this.mix4Block = mix4;
            this.mix4Height = mix4Height;

            this.mixFillBlock = mixFill;
            this.mixFill2Block = mixFill2;
            this.mixFill3Block = mixFill3;
            this.mixFill4Block = mixFill4;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
            int cliff = 0;
            IBlockState mix_filler = fillerBlock;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0) {

                        float p = simplex.noise3f(i / 8f, j / 8f, k / 8f) * 0.5f;
                        if (c > min && c > sCliff - ((k - sHeight) / sStrength) + p) {
                            cliff = 1;
                        }
                        if (c > cCliff) {
                            cliff = 2;
                        }

                        if (cliff == 1) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, topBlock);
                            }
                            else {

                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                        else if (k < 63) {
                            if (k < 62) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else {
                            float mixNoise = simplex.noise2f(i / 12f, j / 12f); // Range = -0.8f to 0.8f (approximately)

                            if (mixNoise < mix4Height) {
                                primer.setBlockState(x, k, z, mix4Block);
                                mix_filler = mixFill4Block;
                            }
                            else if (mixNoise < mix3Height) {
                                primer.setBlockState(x, k, z, mix3Block);
                                mix_filler = mixFill3Block;
                            }
                            else if (mixNoise < mix2Height) {
                                primer.setBlockState(x, k, z, mix2Block);
                                mix_filler = mixFill2Block;
                            }
                            else if (mixNoise < mixHeight) {
                                primer.setBlockState(x, k, z, mixBlock);
                                mix_filler = mixFillBlock;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                    }
                    else if (depth < 6) {
//                        if (cliff == 1) {
//                            primer.setBlockState(x, k, z, topBlock);
//                        }
//                        else if (cliff == 2) {
//                            primer.setBlockState(x, k, z, topBlock);
//                        }
//                        else {
                            primer.setBlockState(x, k, z, mix_filler);
//                        }
                    }
                }
            }
        }
    }
}
