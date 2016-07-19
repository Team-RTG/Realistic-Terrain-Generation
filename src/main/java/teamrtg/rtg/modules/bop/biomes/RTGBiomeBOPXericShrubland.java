package teamrtg.rtg.modules.bop.biomes;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import teamrtg.rtg.api.tools.terrain.*;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.CliffSelector;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.bop.RTGBiomeBOP;

public class RTGBiomeBOPXericShrubland extends RTGBiomeBOP {

    public RTGBiomeBOPXericShrubland() {
        super(BOPBiomes.alps.get(), Biomes.RIVER);
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {

            private float minHeight;
            private float mesaWavelength;
            private float hillStrength;
            private float topBumpinessHeight=3;
            private float topBumpinessWavelength = 10;
            private HeightEffect height;
            private HeightEffect groundEffect;

            private float jitterAmplitude = 4f;
            private float jitterWavelength = 15f;

            {
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
                height = new VariableRuggednessEffect(new RaiseEffect(0f),topVariation.plus(topBumpiness).plus(new RaiseEffect(hillStrength))
                        ,0.4f,0.3f,mesaWavelength);

                height = new JitterEffect(jitterAmplitude,jitterWavelength,height);

            }

            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float biomeWeight, float border, float river) {
                return riverized(minHeight+groundEffect.added(rtgWorld.simplex, rtgWorld.cell,x, y),river)+height.added(rtgWorld.simplex,rtgWorld.cell, x, y);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = new SurfacePart();
        surface.add(new CliffSelector(1.5f)
            .add(PARTS.STONE_OR_COBBLE));
        surface.add(PARTS.surfaceGeneric());
        return surface;
    }

    @Override
    public void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {

    }
}
