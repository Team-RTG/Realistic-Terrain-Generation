package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainEBCarr extends TerrainBase
{

    private HeightEffect height;
    public TerrainEBCarr()
    {
        base = 62.5f;
        HeightVariation waterLand = new HeightVariation();
        waterLand.height = 3f;
        waterLand.wavelength = 30;
        waterLand.octave =VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;

        height = new JitterEffect(15f,30f,waterLand);
        height = new JitterEffect(5f,10f,height);

        // add in some occasional hills
        BlendedHillEffect intermittentHills = new BlendedHillEffect();
        intermittentHills.height = 10f;
        intermittentHills.hillBottomSimplexValue = 0.5f;// rarish
        intermittentHills.wavelength = 80f;

        JitterEffect jitteredHills = new JitterEffect(5f,15f,intermittentHills);

        height = height.plus(jitteredHills);


    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(base+ height.added(simplex, cell,x, y),river);
    }
}
