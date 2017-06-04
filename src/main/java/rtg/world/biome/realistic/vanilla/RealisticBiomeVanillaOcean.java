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
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.OCEAN;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaOcean() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().ALLOW_VILLAGES.set(false);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaOcean();
    }

    public class TerrainVanillaOcean extends TerrainBase {

        public TerrainVanillaOcean() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainOcean(x, y, rtgWorld.simplex, river, rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get() - 13f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaOcean(config, Blocks.SAND.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.GRAVEL.getDefaultState(), 20f, 0.2f);
    }

    public class SurfaceVanillaOcean extends SurfaceBase {

        private final int sandMetadata = 0;
        private IBlockState mixBlock;
        private float width;
        private float height;
        private float mixCheck;

        public SurfaceVanillaOcean(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mix, float mixWidth, float mixHeight) {

            super(config, top, filler);

            mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), config.SURFACE_MIX_BLOCK_META.get(), mix);

            width = mixWidth;
            height = mixHeight;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            OpenSimplexNoise simplex = rtgWorld.simplex;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0 && k > 0 && k < rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get()) {
                        mixCheck = simplex.noise2(i / width, j / width);

                        if (mixCheck > height) // > 0.27f, i / 12f
                        {
                            primer.setBlockState(x, k, z, mixBlock);
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 4 && k < rtg.api.RTGAPI.config().SEA_LVL_MODIFIER.get()) {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }

                    else if (depth == 0 && k < 69) {
                        primer.setBlockState(x, k, z, BlockUtil.getStateSand(sandMetadata));

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

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }

    @Override
    public int lavaSurfaceLakeChance() {
        return 0;
    }
}
