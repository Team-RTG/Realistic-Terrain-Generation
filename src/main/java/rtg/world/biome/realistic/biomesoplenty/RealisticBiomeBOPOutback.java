package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPOutback;
import rtg.world.gen.terrain.*;

public class RealisticBiomeBOPOutback extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.outback.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPOutback(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPOutback(config,
                Blocks.GRASS.getDefaultState(), //Block top
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
    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainBOPOutback(65f, 50f, 10f);
    }

    public class TerrainBOPOutback extends TerrainBase {

        private float minHeight;
        private float mesaWavelength;
        private float hillStrength;
        private float topBumpinessHeight = 4;
        private float topBumpinessWavelength = 25;
        private HeightEffect height;
        private HeightEffect groundEffect;


        public TerrainBOPOutback(float minHeight, float wavelength, float hillStrength) {

            this.minHeight = minHeight;
            this.mesaWavelength = wavelength;
            this.hillStrength = hillStrength;

            groundEffect = new GroundEffect(4f);

            // this is variation in what's added to the top. Set to vary with the "standard" ruggedness
            HeightVariation topVariation = new HeightVariation();
            topVariation.height = hillStrength / 2;
            topVariation.octave = 1;
            topVariation.wavelength = VariableRuggednessEffect.STANDARD_RUGGEDNESS_WAVELENGTH;


            // create some bumpiness to disguise the cliff heights
            HeightVariation topBumpiness = new HeightVariation();
            topBumpiness.height = topBumpinessHeight;
            topBumpiness.wavelength = topBumpinessWavelength;
            topBumpiness.octave = 3;


            // now make the top only show up on mesa
            HeightEffect mesaTops = new VariableRuggednessEffect(new RaiseEffect(0f), topVariation.plus(new RaiseEffect(hillStrength)).plus(topBumpiness)
                , 0.3f, 0.15f, mesaWavelength);

            // and make the mesa Tops only show up part of the time, but most of the time,
            // using the standard ruggedness wavelength
            height = new VariableRuggednessEffect(new RaiseEffect(0f), mesaTops, -0.3f, .06f);

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return riverized(minHeight + groundEffect.added(simplex, cell, x, y), river) + height.added(simplex, cell, x, y);
            //return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 4f);
        }
    }
}
