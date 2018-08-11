package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.WorldUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.util.noise.SimplexOctave;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.world.biome.realistic.RealisticBiomeBase;


public class RealisticBiomeBOPChaparral extends RealisticBiomeBase {

    public static Biome biome = BOPBiomes.chaparral.get();

    public RealisticBiomeBOPChaparral() {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
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

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }

    public class TerrainBOPChaparral extends TerrainBase {

        private float baseHeight = 76f;
        private float peakyHillWavelength = 40f;
        private float peakyHillStrength = 40f;
        private float smoothHillWavelength = 60f;
        private float smoothHillStrength = 30f;


        private SimplexOctave.Derivative jitter = new SimplexOctave.Derivative();
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

            groundNoise = groundNoise(x, y, groundNoiseAmplitudeHills, rtgWorld);

            //float m = hills(x, y, peakyHillStrength, simplex, river);

            rtgWorld.simplexInstance(0).riverJitter().evaluateNoise((float) x / wavelength, (float) y / wavelength, jitter);
            int pX = (int) Math.round(x + jitter.deltax() * amplitude);
            int pY = (int) Math.round(y + jitter.deltay() * amplitude);
            float h = this.terrainGrasslandHills(pX, pY, rtgWorld, river, peakyHillWavelength, peakyHillStrength, smoothHillWavelength, smoothHillStrength, baseHeight);

            return groundNoise + h;
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
            float c = WorldUtil.Terrain.calcCliff(x, z, noise);
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
