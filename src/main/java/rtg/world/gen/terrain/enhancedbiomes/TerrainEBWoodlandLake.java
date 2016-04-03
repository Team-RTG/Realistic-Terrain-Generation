package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;
import rtg.world.gen.terrain.VariableRuggednessEffect;

public class TerrainEBWoodlandLake extends TerrainBase
{
    private HeightEffect height;
    public TerrainEBWoodlandLake()
    {
        base = 61;
        HeightVariation waterLand = new HeightVariation();
        waterLand.height = 4f;
        waterLand.wavelength = 80;
        waterLand.octave =VariableRuggednessEffect.STANDARD_RUGGEDNESS_OCTAVE;

        height = new JitterEffect(20f,40f,waterLand);

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(base+ height.added(simplex, cell,x, y),river);
    }
}
