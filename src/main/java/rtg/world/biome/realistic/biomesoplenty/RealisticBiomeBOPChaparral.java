package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeBOPChaparral extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.chaparral.orNull();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPChaparral() {

        super(biome);
    }

    @Override
    public void initConfig() {
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPChaparral();
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPChaparral(getConfig(), biome.topBlock, biome.fillerBlock, Blocks.SAND.getDefaultState(), 26f, 0.35f);
    }

    @Override
    public void initDecos() {
        DecoBOPBaseBiomeDecorations decoBOPBaseBiomeDecorations = new DecoBOPBaseBiomeDecorations();
        this.addDeco(decoBOPBaseBiomeDecorations);
    }

    public class TerrainBOPChaparral extends TerrainBase {

        private float baseHeight = 76f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 40f;
        private float smoothHillWavelength = 60f;
        private float smoothHillStrength = 30f;
        private float wavelength = 10f;// of jitter
        private float amplitude = 2f;// of jitter

        public TerrainBOPChaparral() {

        }

        public TerrainBOPChaparral(float bh, float hs) {

            baseHeight = bh;
            peakyHillStrength = hs;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            this.groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld);

            //float m = hills(x, y, peakyHillStrength, simplex, river);

// TODO [1.12] This is the only use of SimplexData2D$Derivative - investigate removing this data type to simplify SimplexData2D
            ISimplexData2D jitterData = SimplexData2D.newDerivative();
            rtgWorld.simplexInstance(1).multiEval2D(x / wavelength, y / wavelength, jitterData);
            int pX = (int) Math.round(x + jitterData.getDeltaX() * amplitude);
            int pY = (int) Math.round(y + jitterData.getDeltaY() * amplitude);

            float h = terrainGrasslandHills(pX, pY, rtgWorld, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);
            return groundNoise * river + h;
        }
    }

    public class SurfaceBOPChaparral extends SurfaceBase {

        private IBlockState mixBlock;
        private float width;
        private float height;

        public SurfaceBOPChaparral(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mix, float mixWidth, float mixHeight) {

            super(config, top, filler);

            mixBlock = mix;

            width = mixWidth;
            height = mixHeight;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 1.4f ? true : false;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        if (depth > -1 && depth < 2) {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            if (simplex.noise2f(i / width, j / width) > height) // > 0.27f, i / 12f
                            {
                                primer.setBlockState(x, k, z, mixBlock);
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
                    }
                }
            }
        }
    }
}
