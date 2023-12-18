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
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.RealisticBiomeBase;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.deco.collection.DecoCollectionMesa;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.VoronoiPlateauEffect;

import java.util.Random;


public class RealisticBiomeVanillaMesaBryce extends RealisticBiomeBase {

    public static Biome biome = Biomes.MUTATED_MESA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesaBryce() {

        super(biome);
    }

    @Override
    public Biome preferredBeach() {
        return biome;
    }

    @Override
    public void initConfig() {
        this.getConfig().SURFACE_WATER_LAKE_MULT.set(0.0f);
        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().SURFACE_MIX_2_BLOCK).set("");
        this.getConfig().addProperty(this.getConfig().ALLOW_PLATEAU_MODIFICATIONS).set(false);
        this.getConfig().addProperty(this.getConfig().PLATEAU_GRADIENT_BLOCK_LIST).set(PlateauUtil.getMesaPlateauBlocks());
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainRTGBrycePlateau(67f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaBryce(getConfig(), biome.topBlock, BlockUtil.getStateClay(EnumDyeColor.ORANGE), 0);
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

    public class TerrainRTGMesaBryce extends TerrainBase {
        private static final float height = 20f;
        TerrainRTGMesaBryce() { }
        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            return terrainBryce(x, y, rtgWorld, river, height);
        }

    }
    
    public static class TerrainRTGBrycePlateau extends TerrainBase {

        private static final float stepStart = 0.25f;
        private static final float stepFinish = 0.4f;
        private static final float stepHeight = 32;
        private static final float hoodooStepStart = .05f;
        final VoronoiPlateauEffect plateau;
        final int groundNoise;
        private float jitterWavelength = 30;
        private float jitterAmplitude = 10;
        private float bumpinessMultiplier = 0.05f;
        private float bumpinessWavelength = 10f;

        public TerrainRTGBrycePlateau(float base) {
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
            float simplex = plateau.added(rtgWorld, x, y) * bordercap * rivercap + bumpiness;
            float added = PlateauUtil.stepIncrease(simplex, stepStart, stepFinish, stepHeight)/ border;
            float hoodooTop = PlateauUtil.stepIncrease(simplex, hoodooStepStart, stepFinish, stepHeight) / border;
            if (hoodooTop>added) {
            	added += hoodooHeight(x,y,rtgWorld,hoodooTop-added);
            }
            float result = riverized(base + TerrainBase.groundNoise(x, y, groundNoise, rtgWorld), river) + added;
            return result;
        }
        
        public static float hoodooHeight(float x, float y, RTGWorld rtgWorld, float height) {

            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float sn = simplex.noise2f(x / 2f, y / 2f) * 0.5f + 0.5f;
            sn += simplex.noise2f(x, y) * 0.2 + 0.2;
            sn += simplex.noise2f(x / 4f, y / 4f) * 4f + 4f;
            sn += simplex.noise2f(x / 8f, y / 8f) * 2f + 2f;
            sn = sn*2/6.7f;// adjust to [-1,1]
            // extremify
            if (sn < 1) {
            	sn = - bayesianAdjustment(-sn,1);
            } else {
            	sn = bayesianAdjustment(sn,1);
            }
            sn = sn/2 + .5f;
            float n = height * sn;
            //float n = height / sn * 2;
            //n += simplex.noise2f(x / 64f, y / 64f) * 4f;
            //n = (sn < 6) ? n : 0f;
            return n;
        }

    }
    
    public class SurfaceVanillaMesaBryce extends SurfaceBase {

        private int grassRaise = 0;
        private IBlockState mixBlock;
        private IBlockState mix2Block;

        public SurfaceVanillaMesaBryce(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

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
                        primer.setBlockState(x, k, z, PlateauUtil.getPlateauBand(rtgWorld, RealisticBiomeVanillaMesaBryce.this, i, k, j));
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
