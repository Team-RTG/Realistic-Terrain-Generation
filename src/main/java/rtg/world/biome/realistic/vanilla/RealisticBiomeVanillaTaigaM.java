package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt.DirtType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.collection.DecoCollectionTaiga;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.biome.RealisticBiomeBase;


public class RealisticBiomeVanillaTaigaM extends RealisticBiomeBase {

    public static Biome biome = Biomes.MUTATED_TAIGA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaTaigaM() {

        super(biome, BeachType.STONE);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaTaigaM(70f, 180f, 7f, 100f, 38f, 160f, 68f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaTaigaM(getConfig(), biome.topBlock, biome.fillerBlock);
    }

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionTaiga(this.getConfig(), 10f));
    }

    public boolean allowVanillaTrees() {return false;}
    
    public static class TerrainVanillaTaigaM extends TerrainBase {

        private float hHeight;
        private float hWidth;
        private float vHeight;
        private float vWidth;
        private float lHeight;
        private float lWidth;
        private float bHeight;

        public TerrainVanillaTaigaM(float hillHeight, float hillWidth, float varHeight, float varWidth, float lakeHeight, float lakeWidth, float baseHeight) {

            hHeight = hillHeight;
            hWidth = hillWidth;

            vHeight = varHeight;
            vWidth = varWidth;

            lHeight = lakeHeight;
            lWidth = lakeWidth;

            bHeight = baseHeight;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainGrasslandHills(x, y, rtgWorld, river, vWidth, vHeight, hWidth, hHeight, bHeight);
        }
    }

    public static class SurfaceVanillaTaigaM extends SurfaceBase {

        private IBlockState mixBlock;

        public SurfaceVanillaTaigaM(BiomeConfig config, IBlockState top, IBlockState fill) {

            super(config, top, fill);

            mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), BlockUtil.getStateDirt(DirtType.PODZOL));
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float p = simplex.noise2f(i / 8f, j / 8f) * 0.5f;
            float c = TerrainBase.calcCliff(x, z, noise, river);
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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
                        }
                        else if (cliff == 3) {
                            primer.setBlockState(x, k, z, Blocks.SNOW.getDefaultState());
                        }
                        else if (simplex.noise2f(i / 50f, j / 50f) + p * 0.6f > 0.24f) {
                            primer.setBlockState(x, k, z, mixBlock);
                        }
                        else {
                            primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                        }
                    }
                    else if (depth < 6) {
                        if (cliff == 1) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                        else if (cliff == 2) {
                            primer.setBlockState(x, k, z, getShadowStoneBlock());
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
}
