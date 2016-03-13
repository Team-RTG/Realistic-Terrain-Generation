package rtg.world.gen.terrain.biomesoplenty;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainBOPArctic extends TerrainBase
{

    private float ruggedness = 0;
    private float ruggednessWavelength = 100f;
    private float heightVariability = 25f;// the ruggedness parameter will multiply this by 0.2
    private float heightWavelength = 60f;

	public TerrainBOPArctic()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        float result = terrainPlains(x, y, simplex, river, ruggednessWavelength, ruggedness, heightVariability, heightWavelength, base);
        // no indentations; cutoff is not noticeable with these low slopes
        return result>base ? result : base;
	}
}
