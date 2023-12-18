package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.Logger;
import rtg.api.util.PlateauUtil;
import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.deco.collection.DecoCollectionMesa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.VoronoiPlateauEffect;

import java.util.Random;


public class RealisticBiomeVanillaMesaPlateau extends RealisticBiomeBase {

    public static Biome biome = Biomes.MESA_CLEAR_ROCK;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesaPlateau() {

        super(biome);
    }

    @Override
    public Biome preferredBeach() {
        return biome;
    }

    @Override
    public void initConfig() {
        this.getConfig().SURFACE_WATER_LAKE_MULT.set(0.1f);
        this.getConfig().ALLOW_SCENIC_LAKES.set(false);
        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().ALLOW_PLATEAU_MODIFICATIONS).set(false);
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_BLOCK_LIST).set(PlateauUtil.getMesaPlateauBlocks());
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainRTGMesaPlateau(67);
        //return new TerrainVanillaMesaPlateau(true, 35f, 160f, 60f, 40f, 69f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaPlateau(getConfig(), biome.topBlock, BlockUtil.getStateClay(EnumDyeColor.ORANGE), 0);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        this.rReplaceWithRiver(primer, i, j, x, y, depth, rtgWorld, noise, river, base);
    }

    @Override
    public void initDecos() {
        this.addDecoCollection(new DecoCollectionDesertRiver(this.getConfig()));
        this.addDecoCollection(new DecoCollectionMesa(this.getConfig()));
    }

    @Override
    public void overrideDecorations() {
        baseBiome().decorator.cactiPerChunk = -999;
    }

    public static class TerrainRTGMesaPlateau extends TerrainBase {

        private static final float stepStart = 0.25f;
        private static final float stepFinish = 0.4f;
        private static final float stepHeight = 32;
        final VoronoiPlateauEffect plateau;
        final int groundNoise;
        private float jitterWavelength = 30;
        private float jitterAmplitude = 10;
        private float bumpinessMultiplier = 0.1f;
        private float bumpinessWavelength = 10f;
        private float variabilityMultiplier = 0.15f;
        private float variabilityWavelength = 30f;
        private float variabilityMultiplier2 = 0.075f;
        private float variabilityWavelength2 = 15f;

        public TerrainRTGMesaPlateau(float base) {
            plateau = new VoronoiPlateauEffect();
            plateau.pointWavelength = 200;
            this.base = base;
            groundNoise = 4;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int passedX, int passedY, float border, float river) {
            ISimplexData2D jitterData = SimplexData2D.newDisk();
            rtgWorld.simplexInstance(1).multiEval2D(passedX / jitterWavelength, passedY / jitterWavelength, jitterData);
            float x = (float) (passedX + jitterData.getDeltaX() * jitterAmplitude);
            float y = (float) (passedY + jitterData.getDeltaY() * jitterAmplitude);
            float bordercap = (bordercap = border * 3.5f - 2.5f) > 1 ? 1.0f : bordercap;
            float rivercap = (rivercap = 3f * river) > 1 ? 1.0f : rivercap;
            float bumpiness = rtgWorld.simplexInstance(2).noise2f(x / bumpinessWavelength, y / bumpinessWavelength) * bumpinessMultiplier;
            float variability = rtgWorld.simplexInstance(3).noise2f(x / variabilityWavelength, y / variabilityWavelength) * variabilityMultiplier;
            float variability2 = rtgWorld.simplexInstance(3).noise2f(x / variabilityWavelength2, y / variabilityWavelength2) * variabilityMultiplier2;
            // no holes
            variability += variability2;
            variability= Math.max(variability, 0f);
            float baseSimplex = plateau.added(rtgWorld, x, y);
            baseSimplex += variability ;
            float simplex = baseSimplex * bordercap * rivercap + bumpiness;
            float added = PlateauUtil.stepIncrease(simplex, stepStart, stepFinish, stepHeight) / border;
            float result = riverized(base + TerrainBase.groundNoise(x, y, groundNoise, rtgWorld), river) + added;
            if (border> .1) {
                //Logger.info("Mesa ({},{}) Simplex {} added {} border {} result {}",passedX, passedY,simplex,added,border, result);
                
           } 
            return result;//64f + 100f*simplex;//result;
        }

    }

    public class SurfaceVanillaMesaPlateau extends SurfaceBase {

        private int grassRaise = 0;
        private IBlockState mixBlock;
        private IBlockState mix2Block;

        public SurfaceVanillaMesaPlateau(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

            super(config, top, fill);
            grassRaise = grassHeight;

            this.mixBlock = this.getConfigBlock(config.SURFACE_MIX_BLOCK.get(), BlockUtil.getStateClay(EnumDyeColor.ORANGE));
            this.mix2Block = this.getConfigBlock(config.SURFACE_MIX_2_BLOCK.get(), Blocks.RED_SANDSTONE.getDefaultState());
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            float c = TerrainBase.calcCliff(x, z, noise, river);
            boolean cliff = c > 1.3f;
            Block b;

            for (int k = 255; k > -1; k--) {
                b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (cliff) {
                        primer.setBlockState(x, k, z, PlateauUtil.getPlateauBand(rtgWorld, RealisticBiomeVanillaMesaPlateau.this, i, k, j));
                    }
                    else {

                        if (k > 74 + grassRaise) {
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
                            int r = (int) ((k - (62 + grassRaise)) / 2f);
                            if (rand.nextInt(r + 2) == 0) {
                                primer.setBlockState(x, k, z, mixBlock);
                            }
                            else if (rand.nextInt((int) (r / 2f) + 2) == 0) {
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
                }
            }
        }
    }
}
