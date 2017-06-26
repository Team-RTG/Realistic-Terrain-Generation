package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.config.BiomeConfig;
import rtg.api.util.CliffCalculator;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.deco.DecoBoulder;
import rtg.api.world.deco.DecoFallenTree;
import rtg.api.world.deco.DecoJungleCacti;
import rtg.api.world.surface.SurfaceBase;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.*;
import static rtg.api.world.deco.DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;

public class RealisticBiomeBOPLushDesert extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.lush_desert.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPLushDesert() {

        super(biome, river);
    }

    @Override
    public void initConfig() {

        this.getConfig().addProperty(this.getConfig().ALLOW_LOGS).set(true);
        this.getConfig().addProperty(this.getConfig().FALLEN_LOG_DENSITY_MULTIPLIER);
        this.getConfig().addProperty(this.getConfig().ALLOW_CACTUS).set(true);
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPLushDesert(65f, 40f, 10f);
    }

    public class TerrainBOPLushDesert extends TerrainBase {

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
        public float generateNoise(IRTGWorld rtgWorld, int x, int y, float border, float river) {

            return riverized(minHeight + groundEffect.added(rtgWorld, x, y), river) + height.added(rtgWorld, x, y);
            //return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 4f);
        }
    }

    @Override
    public SurfaceBase initSurface() {

        return new SurfaceBOPLushDesert(config,
            biome.topBlock, //Block top
            biome.fillerBlock, //Block filler,
            Blocks.GRASS.getDefaultState(), //IBlockState mixTop,
            biome.fillerBlock, //IBlockState mixFill,
            40f, //float mixWidth,
            0.5f, //float mixHeight,
            10f, //float smallWidth,
            0.5f //float smallStrength
        );
    }

    public class SurfaceBOPLushDesert extends SurfaceBase {


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
        public void paintTerrain(ChunkPrimer primer, int i, int j, int x, int z, int depth, IRTGWorld rtgWorld, float[] noise, float river, Biome[] base) {

            Random rand = rtgWorld.rand();
            OpenSimplexNoise simplex = rtgWorld.simplex();
            float c = CliffCalculator.calc(x, z, noise);
            boolean cliff = c > 3.4f ? true : false;
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
                            if (simplex.noise2(i / floMixWidth, j / floMixWidth) + simplex.noise2(i / floSmallWidth, j / floSmallWidth)
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

    @Override
    public void initDecos() {

        DecoBOPBaseBiomeDecorations decoBOPBaseBiomeDecorations = new DecoBOPBaseBiomeDecorations();
        this.addDeco(decoBOPBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        decoBoulder.setMaxY(80);
        decoBoulder.setChance(16);
        decoBoulder.setStrengthFactor(1f);
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.getDistribution().setNoiseDivisor(80f);
        decoFallenTree.getDistribution().setNoiseFactor(60f);
        decoFallenTree.getDistribution().setNoiseAddend(-15f);
        decoFallenTree.setLogCondition(NOISE_GREATER_AND_RANDOM_CHANCE);
        decoFallenTree.setLogConditionNoise(0f);
        decoFallenTree.setLogConditionChance(12);
        decoFallenTree.setRandomLogBlocks(new IBlockState[]{Blocks.LOG2.getStateFromMeta(1), BOPBlocks.log_3.getStateFromMeta(2), Blocks.LOG.getDefaultState()});
        decoFallenTree.setMinSize(3);
        decoFallenTree.setMaxSize(5);
        this.addDeco(decoFallenTree, this.getConfig().ALLOW_LOGS.get());

        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.setStrengthFactor(8f);
        decoJungleCacti.setMaxY(110);
        this.addDeco(decoJungleCacti, this.getConfig().ALLOW_CACTUS.get());
    }
}
