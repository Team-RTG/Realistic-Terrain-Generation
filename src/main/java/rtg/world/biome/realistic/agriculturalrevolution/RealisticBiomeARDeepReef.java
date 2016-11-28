package rtg.world.biome.realistic.agriculturalrevolution;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.config.BiomeConfig;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeARDeepReef extends RealisticBiomeARBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeARDeepReef(Biome biome) {

        super(biome, river);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_VILLAGES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainARDeepReef();
    }

    public class TerrainARDeepReef extends TerrainBase {

        public TerrainARDeepReef() {

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return terrainOcean(x, y, simplex, river, 40f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceARDeepReef(config, Blocks.GRAVEL.getDefaultState(), Blocks.GRAVEL.getDefaultState(), Blocks.CLAY.getDefaultState(), 20f, 0.1f);
    }

    public class SurfaceARDeepReef extends SurfaceBase {

        private IBlockState mixBlock;
        private float width;
        private float height;
        private float mixCheck;

        public SurfaceARDeepReef(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mix, float mixWidth, float mixHeight) {

            super(config, top, filler);

            mixBlock = mix;

            width = mixWidth;
            height = mixHeight;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {


            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth == 0 && k > 0 && k < 63) {
                        mixCheck = simplex.noise2(i / width, j / width);

                        if (mixCheck > height) {
                            primer.setBlockState(x, k, y, mixBlock);
                        }
                        else {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if (depth < 4 && k < 63) {
                        primer.setBlockState(x, k, y, fillerBlock);
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
