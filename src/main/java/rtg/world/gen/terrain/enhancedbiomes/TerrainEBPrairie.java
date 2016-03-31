package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainEBPrairie extends TerrainBase
{

    private HeightEffect height;
    private float rollHeight = 4f;
    public TerrainEBPrairie()
    {
        base = 69f;
        HeightVariation roll = new HeightVariation();
        roll.height = rollHeight;
        roll.octave = VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;
        roll.wavelength = VariableRuggednessEffect.STANDARD_RUGGEDNESS_WAVELENGTH;

        height = roll.plus(new GroundEffect(1f));
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(height.added(simplex, cell, x, y)+base,river);
    }
}
