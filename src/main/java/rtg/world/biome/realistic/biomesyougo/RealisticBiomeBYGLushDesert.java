package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.config.BiomeConfig;
import rtg.api.util.BlockUtil;
import rtg.api.util.noise.SimplexNoise;
import rtg.api.world.RTGWorld;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.GroundEffect;
import rtg.api.world.terrain.heighteffect.HeightEffect;
import rtg.api.world.terrain.heighteffect.HeightVariation;
import rtg.api.world.terrain.heighteffect.RaiseEffect;
import rtg.api.world.terrain.heighteffect.VariableRuggednessEffect;

import java.util.Random;


public class RealisticBiomeBYGLushDesert extends RealisticBiomeBYGBase {

    private static IBlockState bygMixBlock = Blocks.HARDENED_CLAY.getDefaultState();

    public RealisticBiomeBYGLushDesert(Biome biome) {

        super(biome, RiverType.NORMAL, BeachType.NORMAL);
    }

    @Override
    public void initConfig() {
        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
    }

    @Override
    public void initDecos() {
        fallenTrees(new IBlockState[]{
                        BlockUtil.getBlockStateFromCfgString("byg:ironwoodlog", BlockUtil.getStateLog(BlockPlanks.EnumType.BIRCH)),
                        BlockUtil.getBlockStateFromCfgString("byg:ironwoodlog", BlockUtil.getStateLog(BlockPlanks.EnumType.BIRCH))
                },
                new int[]{4, 4}
        );
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPLushDesert(65f, 40f, 10f);
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPLushDesert(getConfig(), baseBiome().topBlock, //Block top
                baseBiome().fillerBlock, //Block filler,
                bygMixBlock, //IBlockState mixTop,
                baseBiome().fillerBlock, //IBlockState mixFill,
                40f, //float mixWidth,
                0.5f, //float mixHeight,
                10f, //float smallWidth,
                0.5f //float smallStrength
        );
    }

    public static class TerrainBOPLushDesert extends TerrainBase {

        private float minHeight;
        private float mesaWavelength;
        private float hillStrength;
        private float topBumpinessHeight = 2;
        private float topBumpinessWavelength = 15;
        private HeightEffect height;
        private HeightEffect groundEffect;


        public TerrainBOPLushDesert(float minHeight, float maxHeight, float hillStrength) {

            this.minHeight = minHeight;
            this.mesaWavelength = maxHeight;
            this.hillStrength = hillStrength;

            groundEffect = new GroundEffect(3f);

            // this is variation in what's added to the top. Set to vary with the "standard" ruggedness
            HeightVariation topVariation = new HeightVariation();
            topVariation.height = hillStrength;
            topVariation.octave = 1;
            topVariation.wavelength = VariableRuggednessEffect.STANDARD_RUGGEDNESS_WAVELENGTH;


            // create some bumpiness to disguise the cliff heights
            HeightVariation topBumpiness = new HeightVariation();
            topBumpiness.height = topBumpinessHeight;
            topBumpiness.wavelength = topBumpinessWavelength;
            topBumpiness.octave = 3;

            // now make the top only show up on mesa
            height = new VariableRuggednessEffect(new RaiseEffect(0f), topVariation.plus(topBumpiness).plus(new RaiseEffect(hillStrength))
                    , 0.3f, 0.15f, mesaWavelength);

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return riverized(minHeight + groundEffect.added(rtgWorld, x, y), river) + height.added(rtgWorld, x, y);
            //return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 4f);
        }
    }

    public static class SurfaceBOPLushDesert extends SurfaceBase {


        private IBlockState blockMixTop;
        private IBlockState blockMixFiller;
        private float floMixWidth;
        private float floMixHeight;
        private float floSmallWidth;
        private float floSmallStrength;

        public SurfaceBOPLushDesert(BiomeConfig config, IBlockState top, IBlockState filler, IBlockState mixTop, IBlockState mixFiller,
                                    float mixWidth, float mixHeight, float smallWidth, float smallStrength) {

            super(config, top, filler);

            blockMixTop = mixTop;
            blockMixFiller = mixFiller;

            floMixWidth = mixWidth;
            floMixHeight = mixHeight;
            floSmallWidth = smallWidth;
            floSmallStrength = smallStrength;
        }

        @Override
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, RTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            SimplexNoise simplex = rtgWorld.simplexInstance(0);
            float c = TerrainBase.calcCliff(x, z, noise);
            boolean cliff = c > 3.4f;
            boolean mix = false;

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

                                primer.setBlockState(x, k, z, hcCobble());
                            }
                            else {

                                primer.setBlockState(x, k, z, hcStone());
                            }
                        }
                        else if (depth < 10) {
                            primer.setBlockState(x, k, z, hcStone());
                        }
                    }
                    else {
                        if (depth == 0 && k > 61) {
                            if (simplex.noise2f(i / floMixWidth, j / floMixWidth) + simplex.noise2f(i / floSmallWidth, j / floSmallWidth)
                                    * floSmallStrength > floMixHeight) {
                                primer.setBlockState(x, k, z, blockMixTop);

                                mix = true;
                            }
                            else {
                                primer.setBlockState(x, k, z, topBlock);
                            }
                        }
                        else if (depth < 4) {
                            if (mix) {
                                primer.setBlockState(x, k, z, blockMixFiller);
                            }
                            else {
                                primer.setBlockState(x, k, z, fillerBlock);
                            }
                        }
                    }
                }
            }
        }
    }
}
