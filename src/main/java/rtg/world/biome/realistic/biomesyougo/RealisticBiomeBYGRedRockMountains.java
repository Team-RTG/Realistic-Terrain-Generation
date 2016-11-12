package rtg.world.biome.realistic.biomesyougo;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.*;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeBYGRedRockMountains extends RealisticBiomeBYGBase {

    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBYGRedRockMountains(Biome biome, BiomeConfig config) {

        super(config, biome, river);

        this.noLakes = true;
        this.noWaterFeatures = true;
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBYGRedRockMountains(230f, 100f, 68f);
    }

    public class TerrainBYGRedRockMountains extends TerrainBase
    {
        private float width;
        private float strength;
        private float terrainHeight;

        private int wavelength = 39;
        private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        private double amplitude = 12;

        public TerrainBYGRedRockMountains(float mountainWidth, float mountainStrength, float height)
        {
            width = mountainWidth;
            strength = mountainStrength;
            terrainHeight = height;

            // experimentation
            terrainHeight = 30;
            width = 120;
        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
        {
            simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
            float pX = (float)((float)x + jitter.deltax() * amplitude);
            float pY = (float)((float)y + jitter.deltay() * amplitude);

            float h = simplex.noise2(pX / 19f, pY / 19f);
            h = h*h*2f;
            float h2 = simplex.noise2(pX / 13f, pY / 13f);
            h2 = h2 * h2 * 1.3f;
            h = Math.max(h, h2);
            h += h2;
            float h3 = simplex.noise2( pX / 53f, pY /53f);
            h3= h3*h3*5f;
            h+= h3;

            float m = unsignedPower(simplex.noise2(pX / width, pY / width),1.4f) * strength * river;
            // invert y and x for complexity
            float m2 = unsignedPower(simplex.noise2(pY / (width*1.5f), pX / (width*1.5f)),1.4f) * strength * river;

            m = Math.max(m, m2);

            // intensify ruggedness at height
            h = m>10? h * m/10: h;

            m = above(m,-50f);


            return terrainHeight + h + m;


        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBYGRedRockMountains(config, this.baseBiome.topBlock, this.baseBiome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f);
    }

    public class SurfaceBYGRedRockMountains extends SurfaceBase
    {
        private float min;

        private float sCliff = 1.5f;
        private float sHeight = 60f;
        private float sStrength = 65f;
        private float cCliff = 1.5f;

        private IBlockState redRockStone = Block.getBlockFromName("BiomesYouGo:RedRock").getDefaultState();
        private IBlockState redRockCobble = Block.getBlockFromName("BiomesYouGo:RedRockCobblestone").getDefaultState();
        private IBlockState redClay = BlockUtil.getStateClay(14);

        public SurfaceBYGRedRockMountains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff)
        {
            super(config, top, fill);
            min = minCliff;
        }

        public SurfaceBYGRedRockMountains(BiomeConfig config, IBlockState top, IBlockState fill, float minCliff, float stoneCliff, float stoneHeight, float stoneStrength, float clayCliff)
        {
            this(config, top, fill, minCliff);

            sCliff = stoneCliff;
            sHeight = stoneHeight;
            sStrength = stoneStrength;
            cCliff = clayCliff;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                                 OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

            float c = CliffCalculator.calc(x, y, noise);
            int cliff = 0;

            Block b;
            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, y).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if(depth == 0)
                    {

                        float p = simplex.noise3(i / 8f, j / 8f, k / 8f) * 0.5f;
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

                                primer.setBlockState(x, k, y, hcCobble(world, i, j, x, y, k));
                            }
                            else {

                                primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                            }
                        }
                        else if(cliff == 2)
                        {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else if(k < 63)
                        {
                            if(k < 62)
                            {
                                primer.setBlockState(x, k, y, fillerBlock);
                            }
                            else
                            {
                                primer.setBlockState(x, k, y, topBlock);
                            }
                        }
                        else
                        {
                            primer.setBlockState(x, k, y, topBlock);
                        }
                    }
                    else if(depth < 6)
                    {
                        if(cliff == 1)
                        {
                            primer.setBlockState(x, k, y, hcStone(world, i, j, x, y, k));
                        }
                        else if(cliff == 2)
                        {
                            primer.setBlockState(x, k, y, getShadowStoneBlock(world, i, j, x, y, k));
                        }
                        else
                        {
                            primer.setBlockState(x, k, y, fillerBlock);
                        }
                    }
                }
            }
        }

        @Override
        protected IBlockState hcStone(World world, int i, int j, int x, int y, int k) {
            //return redRockStone;
            return Blocks.STONE.getDefaultState();
        }

        @Override
        protected IBlockState hcCobble(World world, int worldX, int worldZ, int chunkX, int chunkZ, int worldY) {
            //return redRockCobble;
            return Blocks.COBBLESTONE.getDefaultState();
        }

        @Override
        protected IBlockState getShadowStoneBlock(World world, int i, int j, int x, int y, int k) {
            //return redClay;
            return redRockStone;
        }
    }

    @Override
    public void initDecos() {

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
