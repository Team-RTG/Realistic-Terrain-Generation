package rtg.world.biome.realistic.defiledlands;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

import java.util.Random;


public class RealisticBiomeDLForestVilespine extends RealisticBiomeDLBase {

    public RealisticBiomeDLForestVilespine(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaForest();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaForest(getConfig(), baseBiome().topBlock, baseBiome().fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, baseBiome().topBlock, 0.6f, baseBiome().topBlock, -0.4f);
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

    public static class SurfaceVanillaForest extends SurfaceDLBase {

        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState mixBlock;
        private float mixHeight;
        private IBlockState mix2Block;
        private float mix2Height;

        public SurfaceVanillaForest(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff,
                                    float stoneHeight, float stoneStrength, float clayCliff, IBlockState mix, float mixHeight, IBlockState mix2, float mix2Height) {

            super(config, top, fill);
            min = minCliff;

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), mix);
            this.mixHeight = mixHeight;
            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), mix2);
            this.mix2Height = mix2Height;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
            int cliff = 0;
            boolean m = false;

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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
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
                            float mixNoise = simplex.noise2f(i / 12f, j / 12f);

                            if (mixNoise < mix2Height) {
                                primer.setBlockState(x, k, z, mix2Block);
                                m = true;
                            }
                            else if (mixNoise > mixHeight) {
                                primer.setBlockState(x, k, z, mixBlock);
                                m = true;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
                        }
                        else {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
