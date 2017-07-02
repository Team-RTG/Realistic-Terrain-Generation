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
import rtg.api.world.deco.collection.DecoCollectionTaiga;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.TAIGA_HILLS;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaTaigaHills() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_SCENIC_LAKES.set(false);

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaTaigaHills();
    }

    public class TerrainVanillaTaigaHills extends TerrainBase {

        private float hillStrength = 30f;

        public TerrainVanillaTaigaHills() {

            this(72f, 30f);
        }

        public TerrainVanillaTaigaHills(float bh, float hs) {

            base = bh;
            hillStrength = hs;
        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainHighland(x, y, rtgWorld.simplex(), rtgWorld.cell(), river, 10f, 68f, hillStrength, base - 62f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaTaigaHills(config, biome.topBlock, biome.fillerBlock);
    }

    public class SurfaceVanillaTaigaHills extends SurfaceBase {

        private IBlockState mixBlock;

        public SurfaceVanillaTaigaHills(BiomeConfig config, IBlockState top, IBlockState fill) {

            super(config, top, fill);

            mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), BlockUtil.getStateDirt(2));
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float p = simplex.noise2(i / 8f, j / 8f) * 0.5f;
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

                        if (c > 0.45f && c > 1.5f - ((k - 60f) / 65f) + p) {
                            cliff = 1;
                        }
                        if (c > 1.5f) {
                            cliff = 2;
                        }
                        if (k > 110 + (p * 4) && c < 0.3f + ((k - 100f) / 50f) + p) {
                            cliff = 3;
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
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                        }
                        else if (simplex.noise2(i / 50f, j / 50f) + p * 0.6f > 0.24f) {
                            primer.setBlockState(x, k, z, mixBlock);
                        }
                        else {
                            primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                        }
                        else {
                            primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionTaiga(this.getConfig(), 10f));
    }
}
