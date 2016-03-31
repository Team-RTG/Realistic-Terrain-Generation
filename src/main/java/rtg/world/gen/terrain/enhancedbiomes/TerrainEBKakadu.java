package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.RaiseEffect;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainEBKakadu extends TerrainBase
{
    private float minHeight;
    private float mesaWavelength;
    private float hillStrength;
    private float topBumpinessHeight=3;
    private float topBumpinessWavelength = 15;
    private HeightEffect height;
    private HeightEffect groundEffect;

    private float jitterAmplitude = 6f;
    private float jitterWavelength = 15f;
	public TerrainEBKakadu()
    {
        this.minHeight = 65f;
        this.mesaWavelength = 80;
        this.hillStrength = 10f;

        groundEffect = new GroundEffect(5f);

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
                ,0.2f,0.2f,mesaWavelength);

        height = new JitterEffect(jitterAmplitude,jitterWavelength,height);

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(minHeight+groundEffect.added(simplex, cell,x, y),river)+height.added(simplex,cell, x, y);
    }
}
