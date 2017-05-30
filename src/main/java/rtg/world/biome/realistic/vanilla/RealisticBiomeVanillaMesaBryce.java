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
import rtg.api.util.CanyonColour;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.SimplexOctave;
import rtg.api.world.RTGWorld;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoCactus;
import rtg.api.world.deco.DecoDeadBush;
import rtg.api.world.deco.DecoShrub;
import rtg.api.world.deco.collection.DecoCollectionDesertRiver;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.util.PlateauStep;
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
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainRTGMesaBryce(67);
        //return new TerrainVanillaMesaBryce(false, 55f, 120f, 60f, 40f, 69f);
    }
    public static class TerrainRTGMesaBryce extends TerrainBase {

        final PlateauStep step;
        final VoronoiBasinEffect plateau;
        final int groundNoise;
        private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
        private float jitterWavelength = 15;
        private float jitterAmplitude = 4;
        private float bumpinessMultiplier = 0.1f;
        private float bumpinessWavelength = 10f;
        private int bumpinessOctave = 2;
        
        public TerrainRTGMesaBryce(float base) {
            plateau = new VoronoiBasinEffect();
            step = new PlateauStep();
            step.finish = 0.9f;
            step.start = 0.55f;
            step.height = 50;
            plateau.pointWavelength = 100;
            this.base = base;
            groundNoise = 4;
        }
        

         
        @Override
        public float generateNoise(RTGWorld rtgWorld, int passedX, int passedY, float border, float river) {
            rtgWorld.simplex.riverJitter().evaluateNoise((float) passedX / jitterWavelength, (float) passedY / jitterWavelength, jitter);
        float x = (float)(passedX + jitter.deltax() * jitterAmplitude);
        float y = (float)(passedY + jitter.deltay() * jitterAmplitude);
            float simplex = plateau.added(rtgWorld, x, y);
            simplex *= river;
            float bumpiness = rtgWorld.simplex.octave(bumpinessOctave).noise2(x / bumpinessWavelength, y / bumpinessWavelength) * bumpinessMultiplier;
            bumpiness += rtgWorld.simplex.octave(bumpinessOctave+1).noise2(x / bumpinessWavelength/2f, y / bumpinessWavelength/2f) * bumpinessMultiplier/2f;
            
            simplex += bumpiness;
            //if (simplex > bordercap) simplex = bordercap;
            float added = step.increase(simplex);
            return riverized(base + TerrainBase.groundNoise(x, y, groundNoise, rtgWorld.simplex),river) + added;
        }
        
    }
    public class TerrainVanillaMesaBryce extends TerrainBase {

        private float height;
        private float density;
        private float base;

        /*
         * Example parameters:
         *
         * allowed to generate rivers?
         * riverGen = true
         *
         * canyon jump heights
         * heightArray = new float[]{2.0f, 0.5f, 6.5f, 0.5f, 14.0f, 0.5f, 19.0f, 0.5f}
         *
         * strength of canyon jump heights
         * heightStrength = 35f
         *
         * canyon width (cliff to cliff)
         * canyonWidth = 160f
         *
         * canyon heigth (total heigth)
         * canyonHeight = 60f
         *
         * canyon strength
         * canyonStrength = 40f
         *
         */
        public TerrainVanillaMesaBryce(boolean riverGen, float heightStrength, float canyonWidth, float canyonHeight, float canyonStrength, float baseHeight) {
            /**
             * Values come in pairs per layer. First is how high to step up.
             * 	Second is a value between 0 and 1, signifying when to step up.
             */
            height = 20f;
            density = 0.7f;
            base = 69f;
        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainBryce(x, y, rtgWorld.simplex, river, height, border);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceVanillaMesaBryce(config, BlockUtil.getStateSand(1), BlockUtil.getStateSand(1), 0);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

        this.rReplaceWithRiver(primer, i, j, x, y, depth, rtgWorld, noise, river, base);
    }

    @Override
    public int getExtraGoldGenCount() {
        return 20;
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }

    public class SurfaceVanillaMesaBryce extends SurfaceBase {

        private int grassRaise = 0;

        public SurfaceVanillaMesaBryce(BiomeConfig config, IBlockState top, IBlockState fill, int grassHeight) {

            super(config, top, fill);
            grassRaise = grassHeight;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand;
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 1.3f;

            for (int k = 255; k > -1; k--) {
                Block b = primer.getBlockState(x, k, z).getBlock();
                if (b == Blocks.AIR) {
                    depth = -1;
                }
                else if (b == Blocks.STONE) {
                    depth++;

                    if (depth > -1 && depth < 12) {
                        if (cliff) {
                                primer.setBlockState(x, k, z, rtgWorld.mesaBiome.getBand(i, k, j));//CanyonColour.MESA.getBlockForHeight(i, k, j));
                        }
                        else {
                            if (depth > 4) {
                                primer.setBlockState(x, k, z, rtgWorld.mesaBiome.getBand(i, k, j));//CanyonColour.MESA.getBlockForHeight(i, k, j));
                            }
                            else if (k > 74 + grassRaise) {
                                if (rand.nextInt(5) == 0) {
                                    primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                                }
                                else {
                                    if (depth == 0) {
                                        primer.setBlockState(x, k, z, topBlock);
                                    }
                                    else {
                                        primer.setBlockState(x, k, z, fillerBlock);
                                    }
                                }
                            }
                            else if (k < 62) {
                                primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                            }
                            else if (k < 62 + grassRaise) {
                                if (depth == 0) {
                                    primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                                }
                                else {
                                    primer.setBlockState(x, k, z, Blocks.DIRT.getDefaultState());
                                }
                            }
                            else if (k < 75 + grassRaise) {
                                if (depth == 0) {
                                    int r = (int) ((k - (62 + grassRaise)) / 2f);
                                    if (rand.nextInt(r + 1) == 0) {
                                        primer.setBlockState(x, k, z, Blocks.GRASS.getDefaultState());
                                    }
                                    else if (rand.nextInt((int) (r / 2f) + 1) == 0) {
                                        primer.setBlockState(x, k, z, BlockUtil.getStateDirt(1));
                                    }
                                    else {
                                        primer.setBlockState(x, k, z, topBlock);
                                    }
                                }
                                else {
                                    primer.setBlockState(x, k, z, fillerBlock);
                                }
                            }
                            else {
                                if (depth == 0) {
                                    primer.setBlockState(x, k, z, topBlock);
                                }
                                else {
                                    primer.setBlockState(x, k, z, fillerBlock);
                                }
                            }
                        }
                    }
                    else if (k > 63) {
                        primer.setBlockState(x, k, z, CanyonColour.MESA_BRYCE.getBlockForHeight(i, k, j));
                    }
                }
            }
        }
    }

    @Override
    public void initDecos() {

        this.addDecoCollection(new DecoCollectionDesertRiver(this.getConfig().ALLOW_CACTUS.get()));

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setMaxY(83);
        this.addDeco(decoBoulder);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.setLoops(3);
        decoShrub.setMaxY(90);
        addDeco(decoShrub);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.setMaxY(100);
        decoDeadBush.setLoops(3);
        this.addDeco(decoDeadBush);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.setSoilBlock(BlockUtil.getStateSand(1));
        decoCactus.setLoops(18);
        decoCactus.setMaxY(100);
        this.addDeco(decoCactus, this.getConfig().ALLOW_CACTUS.get());
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
