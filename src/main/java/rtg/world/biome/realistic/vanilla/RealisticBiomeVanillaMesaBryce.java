package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.PlateauUtil;
import rtg.api.util.WorldUtil.Terrain;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.deco.collection.DecoCollectionMesa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.VoronoiBasinEffect;

public class RealisticBiomeVanillaMesaBryce extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MUTATED_MESA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesaBryce() {

        super(biome, river);
    }

    @Override
    public void initConfig() {
        this.getConfig().ALLOW_VILLAGES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().ALLOW_PLATEAU_MODIFICATIONS).set(false);
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_BLOCK_LIST).set(PlateauUtil.getMesaPlateauBlocks());
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainRTGMesaBryce(67);
        //return new TerrainVanillaMesaBryce(false, 55f, 120f, 60f, 40f, 69f);
    }
    public static class TerrainRTGMesaBryce extends TerrainBase {

        private static final float stepFinish = 0.9f;
        private static final float stepStart = 0.55f;
        private static final float stepHeight = 50;
        final VoronoiBasinEffect plateau;
        final int groundNoise;
        private float jitterWavelength = 15;
        private float jitterAmplitude = 4;
        private float bumpinessMultiplier = 0.1f;
        private float bumpinessWavelength = 10f;

        public TerrainRTGMesaBryce(float base) {
            plateau = new VoronoiBasinEffect();
            plateau.pointWavelength = 100;
            this.base = base;
            groundNoise = 4;
        }
        

         
        @Override
        public float generateNoise(IRTGWorld rtgWorld, int passedX, int passedY, float border, float river) {
            ISimplexData2D jitterData = SimplexData2D.newDisk();
            rtgWorld.simplexInstance(1).multiEval2D(passedX / jitterWavelength, passedY / jitterWavelength, jitterData);
            float x = (float)(passedX + jitterData.getDeltaX() * jitterAmplitude);
            float y = (float)(passedY + jitterData.getDeltaY() * jitterAmplitude);
            float bumpiness = rtgWorld.simplexInstance(2).noise2f(x / bumpinessWavelength, y / bumpinessWavelength) * bumpinessMultiplier
                            + rtgWorld.simplexInstance(3).noise2f(x / bumpinessWavelength / 2f, y / bumpinessWavelength / 2f) * bumpinessMultiplier / 2f;
            float simplex = plateau.added(rtgWorld, x, y) * river + bumpiness;
            float added = PlateauUtil.stepIncrease(simplex, stepFinish, stepStart, stepHeight);
            return riverized(base + groundNoise(x, y, groundNoise, rtgWorld), river) + added;
        }
        
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaBryce(config, biome.topBlock, BlockUtil.getStateClay(EnumDyeColor.ORANGE), 0);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        this.rReplaceWithRiver(primer, i, j, x, y, depth, rtgWorld, noise, river, base);
    }

    @Override
    public int getExtraGoldGenCount() {
        return 20;
    }

    @Override
    public Biome beachBiome() {
        return this.getBeachBiome(Biomes.BEACH);
    }

    public class SurfaceVanillaMesaBryce extends SurfaceBase {

        private int grassRaise = 0;
        private IBlockState mixBlock;
        private IBlockState mix2Block;

        public SurfaceVanillaMesaBryce(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

            super(config, top, fill);
            grassRaise = grassHeight;

            this.mixBlock  = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(),   BlockUtil.getStateClay(EnumDyeColor.ORANGE));
            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), Blocks.RED_SANDSTONE.getDefaultState());
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = Terrain.calcCliff(x, z, noise);
            boolean cliff = c > 1.3f;
            Block b;

            for(int k = 255; k > -1; k--)
            {
                b = primer.getBlockState(x, k, z).getBlock();
                if(b == Blocks.AIR)
                {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        primer.setBlockState(x, k, z, PlateauUtil.getPlateauBand(rtgWorld, RealisticBiomeVanillaMesaBryce.this, i, k, j));
                    }
                    else {

                        if (k > 74 + grassRaise)
                        {
                            if (depth == 0) {
                                if (rand.nextInt(5) == 0) {
                                    primer.setBlockState(x, k, z, mix2Block);
                                }
                                else {
                                    primer.setBlockState(x, k, z, topBlock);
                                }
                            }
                            else if (depth < 4) {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                        else if (depth == 0 && k > 61) {
                            int r = (int)((k - (62 + grassRaise)) / 2f);
                            if(rand.nextInt(r + 2) == 0)
                            {
                                primer.setBlockState(x, k, z, mixBlock);
                            }
                            else if(rand.nextInt((int)(r / 2f) + 2) == 0)
                            {
                                primer.setBlockState(x, k, z, mix2Block);
                            }
                            else
                            {
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

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionDesertRiver(this.getConfig()));
        this.addDecoCollection(new DecoCollectionMesa(this.getConfig()));
    }

    @Override
    public int waterSurfaceLakeChance() {
        return 0;
    }
}
