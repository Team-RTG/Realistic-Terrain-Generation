package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.biomesoplenty.config.BiomeConfigBOPLushDesert;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoFallenTree;
import rtg.world.biome.deco.DecoJungleCacti;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPLushDesert;
import rtg.world.gen.terrain.*;

public class RealisticBiomeBOPLushDesert extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.lush_desert.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPLushDesert(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPLushDesert(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                40f, //float mixWidth, 
                -0.15f, //float mixHeight,
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 80;
        decoBoulder.chance = 16;
        decoBoulder.strengthFactor = 1f;
        this.addDeco(decoBoulder);

        DecoFallenTree decoFallenTree = new DecoFallenTree();
        decoFallenTree.distribution.noiseDivisor = 80f;
        decoFallenTree.distribution.noiseFactor = 60f;
        decoFallenTree.distribution.noiseAddend = -15f;
        decoFallenTree.logCondition = DecoFallenTree.LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
        decoFallenTree.logConditionNoise = 0f;
        decoFallenTree.logConditionChance = 12;
        decoFallenTree.randomLogBlocks = new IBlockState[]{Blocks.LOG2.getStateFromMeta(1), BOPBlocks.log_3.getStateFromMeta(2), Blocks.LOG.getDefaultState()};
        decoFallenTree.minSize = 3;
        decoFallenTree.maxSize = 5;
        this.addDeco(decoFallenTree, this.config._boolean(BiomeConfigBOPLushDesert.decorationLogsId));

        DecoJungleCacti decoJungleCacti = new DecoJungleCacti();
        decoJungleCacti.strengthFactor = 8f;
        decoJungleCacti.maxY = 110;
        this.addDeco(decoJungleCacti);
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
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return riverized(minHeight + groundEffect.added(simplex, cell, x, y), river) + height.added(simplex, cell, x, y);
            //return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 4f);
        }
    }
}
