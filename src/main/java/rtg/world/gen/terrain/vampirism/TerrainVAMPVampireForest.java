package rtg.world.gen.terrain.vampirism;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVAMPVampireForest extends TerrainBase
{
	private float hillStrength = 10f;
	
	public TerrainVAMPVampireForest()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        groundNoise = groundNoise(x, y, groundVariation, simplex);

        float m = hills(x, y, hillStrength, simplex, river);

        float floNoise = 65f + groundNoise + m;

        return riverized(floNoise,river);
	}
}
