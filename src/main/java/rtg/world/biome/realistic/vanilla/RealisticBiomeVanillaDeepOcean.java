package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.world.biome.deco.collection.DecoCollectionOcean;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.*;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.DEEP_OCEAN;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaDeepOcean() {

        super(biome, river);

        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public void initConfig() {

        this.getConfig().ALLOW_VILLAGES.set(false);

        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK_META).set(0);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaSeamounts(false, 1f, 360f, 30f);
        //return new TerrainVanillaDeepOcean();
    }
    public class TerrainVanillaSeamounts extends TerrainBase {

        private float[] height;
        private int heightLength;
        private float strength;
        private boolean riverGen;
        private float canyonWidth;
        private float abyssalVariation = 6f;
        private HeightEffect seamounts;
        public TerrainVanillaSeamounts(boolean riverGen, float heightStrength, float canyonWidth,  float baseHeight) {
            this.canyonWidth = canyonWidth;
            this.base = baseHeight;
            
            // spikes for interest
            SpikeEffect seamountSpikes = new SpikeEffect();
            seamountSpikes.height = 10;
            seamountSpikes.minimumSimplex = -.3f;
            seamountSpikes.octave = 3;
            seamountSpikes.wavelength = 10;
            
            // some variation in height
            HeightVariation seamountTop = new HeightVariation();
            seamountTop.height = 5;
            seamountTop.octave = 4;
            seamountTop.wavelength = 120;
            
            // widely scattered
            PlateauEffect seamountPlacement = new PlateauEffect();
            seamountPlacement.bottomSimplexValue = .85f;
            seamountPlacement.height = 15;
            seamountPlacement.octave = 5;
            seamountPlacement.subordinate = seamountTop.plus(seamountSpikes);
            seamountPlacement.topSimplexValue = .92f;
            seamountPlacement.wavelength = canyonWidth;
            
            seamounts = seamountPlacement;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            float result = seamounts.added(rtgWorld, x, y)+this.oceanNoise(x, y, river, rtgWorld.simplex)+30f;
            if (result > 61) result = 61;// just in case;
            return result;
        }
        
        public float oceanNoise(float x, float y, float amplitude, OpenSimplexNoise simplex) {
            // similar to groundnoise except just uses simplex noise because deadvalleys are not an issue

            float h = simplex.noise2(x / 49f, y / 49f) * amplitude;
            h += simplex.octave(1).noise2(x / 23f, y / 23f) * amplitude / 2f;
            h += simplex.octave(2).noise2(x / 11f, y / 11f) * amplitude / 4f;
            return h;
        }
    }
        
    public class TerrainVanillaDeepOcean extends TerrainBase {

        public TerrainVanillaDeepOcean() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainOcean(x, y, rtgWorld.simplex, river, 40f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaDeepOcean(config, Blocks.SAND.getDefaultState(), Blocks.SAND.getDefaultState(), Blocks.CLAY.getDefaultState(), 20f, 0.44f);
    }

    public class SurfaceVanillaDeepOcean extends SurfaceBase {

        private IBlockState mixBlock;
        private float width;
        private float height;
        private float mixCheck;

        public SurfaceVanillaDeepOcean(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mix, float mixWidth, float mixHeight) {

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

                    if (depth == 0 && k > 0 && k < 63) {
                        mixCheck = simplex.noise2(i / width, j / width);

                        if (mixCheck > height) // > 0.27f, i / 12f
                        {
                            primer.setBlockState(x, k, z, mixBlock);
                        }
                        else {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if (depth < 4 && k < 63) {
                        primer.setBlockState(x, k, z, fillerBlock);
                    }

                    else if (depth == 0 && k < 69) {
                        primer.setBlockState(x, k, z, topBlock);

                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionOcean());
    }
}
