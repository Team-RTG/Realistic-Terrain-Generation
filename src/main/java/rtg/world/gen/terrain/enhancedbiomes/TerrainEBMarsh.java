package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.BlendedHillEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainEBMarsh extends TerrainBase
{
    private HeightEffect height;
    public TerrainEBMarsh()
	{        base = 61;
        HeightVariation waterLand = new HeightVariation();
        waterLand.height = 3f;
        waterLand.wavelength = 40;
        waterLand.octave =VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;

        height = new JitterEffect(20f,30f,waterLand);
        height = new JitterEffect(13f,20f,height);
        height = new JitterEffect(7f,10f,height);


    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(base+ height.added(simplex, cell,x, y),river);
    }
}
