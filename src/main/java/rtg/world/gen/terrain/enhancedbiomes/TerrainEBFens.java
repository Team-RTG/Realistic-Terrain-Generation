package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainEBFens extends TerrainBase
{

    private HeightEffect height;
    public TerrainEBFens()
    {
        base = 61.5f;
        HeightVariation waterLand = new HeightVariation();
        waterLand.height = 3f;
        waterLand.wavelength = 60f;
        waterLand.octave =VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;

        height = new JitterEffect(30f,50f,waterLand);

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(base+ height.added(simplex, cell,x, y),river);
    }
}
