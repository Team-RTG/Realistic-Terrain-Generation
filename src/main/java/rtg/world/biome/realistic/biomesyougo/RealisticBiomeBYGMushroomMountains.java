package rtg.world.biome.realistic.biomesyougo;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.MathUtils;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoBaseBiomeDecorations;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;

public class RealisticBiomeBYGMushroomMountains extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGMushroomMountains(Biome biome) {

        super(biome, river);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_RIVERS.set(false);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGMushroomMountains(230f, 100f, 68f);
    }

    public class TerrainBYGMushroomMountains extends TerrainBase
    {
        private float width;
        private float strength;
        private float terrainHeight;

        private int wavelength = 39;
        private double amplitude = 12;

        public TerrainBYGMushroomMountains(float mountainWidth, float mountainStrength, float height)
        {
            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;

            // experimentation
            terrainHeight = 30;
            width = 120;
        }

        @Override
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            float pX = x;
            float pY = y;
            SimplexNoise simplex0 = rtgWorld.simplexInstance(0);
            ISimplexData2D jitterData = SimplexData2D.newDisk();
            rtgWorld.simplexInstance(1).multiEval2D((float)x / wavelength, (float)y / wavelength, jitterData);

            pX += jitterData.getDeltaX() * amplitude;
            pY += jitterData.getDeltaY() * amplitude;

            float height, height2;
            height  = MathUtils.pow2(simplex0.noise2f(pX / 19f, pY / 19f)) * 2f;
            height2 = MathUtils.pow2(simplex0.noise2f(pX / 13f, pY / 13f)) * 1.3f;
            height  = Math.max(height, height2) + height2;
            height += MathUtils.pow2(simplex0.noise2f(pX / 53f, pY / 53f)) * 5f;

            float m = unsignedPower(simplex0.noise2f(pX / width, pY / width), 1.4f) * strength * river;
            // invert y and x for complexity
            float m2 = unsignedPower(simplex0.noise2f(pY / width * 1.5f, pX / width * 1.5f), 1.4f) * strength * river;
            m = Math.max(m, m2);

            // intensify ruggedness at height
            height = (m > 10) ? height * m / 10 : height2;
            m = above(m, -50f);

            return terrainHeight + height + m;
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBYGMushroomMountains(config, this.baseBiome.topBlock, this.baseBiome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f);
    }

    public class SurfaceBYGMushroomMountains extends SurfaceBase
    {
        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        public SurfaceBYGMushroomMountains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff)
        {
            super(config, top, fill);
            min = minCliff;
        }

        public SurfaceBYGMushroomMountains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff, float stoneHeight, float stoneStrength, float clayCliff)
        {
            this(config, top, fill, minCliff);

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = Terrain.calcCliff(x, z, noise);
            int cliff = 0;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if(depth == 0)
                    {

                        float p = simplex.noise3f(i / 8f, j / 8f, k / 8f) * 0.5f;
                        if(c > min && c > sCliff - ((k - sHeight) / sStrength) + p)
                        {
                            cliff = 1;
                        }
                        if(c > cCliff)
                        {
                            cliff = 2;
                        }

                        if(cliff == 1)
                        {
                            if (rand.nextInt(3) == 0) {

                                primer.setBlockState(x, k, z, hcCobble(rtgWorld, i, j, x, z, k));
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                            }
                        }
                        else if(cliff == 2)
                        {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else if(k < 63)
                        {
                            if(k < 62)
                            {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                            else
                            {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else
                        {
                            primer.setBlockState(x, k, z, topBlock);
                        }
                    }
                    else if(depth < 6)
                    {
                        if(cliff == 1)
                        {
                            primer.setBlockState(x, k, z, hcStone(rtgWorld, i, j, x, z, k));
                        }
                        else if(cliff == 2)
                        {
                            primer.setBlockState(x, k, z, getShadowStoneBlock(rtgWorld, i, j, x, z, k));
                        }
                        else
                        {
                            primer.setBlockState(x, k, z, fillerBlock);
                        }
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
