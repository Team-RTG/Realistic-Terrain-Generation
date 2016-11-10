package rtg.world.biome.realistic.biomesoplenty;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

import biomesoplenty.api.biome.BOPBiomes;

import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPXericShrubland;
import rtg.world.gen.terrain.*;

public class RealisticBiomeBOPXericShrubland extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.xeric_shrubland.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPXericShrubland(BiomeConfig config) {

        super(config, biome, river,
            new SurfaceBOPXericShrubland(config,
                biome.topBlock, //Block top
                biome.fillerBlock, //Block filler,
                biome.topBlock, //IBlockState mixTop,
                biome.fillerBlock, //IBlockState mixFill,
                80f, //float mixWidth, 
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

        return new TerrainBOPXericShrubland();
    }

    public class TerrainBOPXericShrubland extends TerrainBase {

        private float minHeight;
        private float mesaWavelength;
        private float hillStrength;
        private float topBumpinessHeight = 3;
        private float topBumpinessWavelength = 10;
        private HeightEffect height;
        private HeightEffect groundEffect;

        private float jitterAmplitude = 4f;
        private float jitterWavelength = 15f;

        public TerrainBOPXericShrubland() {

            this.minHeight = 65f;
            this.mesaWavelength = 24f;
            this.hillStrength = 5f;

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
                , 0.4f, 0.3f, mesaWavelength);

            height = new JitterEffect(jitterAmplitude, jitterWavelength, height);

        }

        @Override
        public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {

            return riverized(minHeight + groundEffect.added(simplex, cell, x, y), river) + height.added(simplex, cell, x, y);
        }
    }
}
