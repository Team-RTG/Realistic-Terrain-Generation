package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.RaiseEffect;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainBOPLushDesert extends TerrainBase
{
    private float minHeight;
    private float mesaWavelength;
    private float hillStrength;
    private float topBumpinessHeight=2;
    private float topBumpinessWavelength = 15;
    private HeightEffect height;
    private HeightEffect groundEffect;


    public TerrainBOPLushDesert(float minHeight, float maxHeight, float hillStrength)
    {
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
        height = new VariableRuggednessEffect(new RaiseEffect(0f),topVariation.plus(topBumpiness).plus(new RaiseEffect(hillStrength))
                ,0.3f,0.15f,mesaWavelength);

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(minHeight+groundEffect.added(simplex, cell,x, y),river)+height.added(simplex,cell, x, y);
        //return terrainRollingHills(x, y, simplex, river, hillStrength, maxHeight, groundNoise, groundNoiseAmplitudeHills, 4f);
    }
}
