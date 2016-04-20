package rtg.world.gen.terrain.tofucraft;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainTOFUTofuForest extends TerrainBase
{

	private float hillStrength = 10f;
	
    public TerrainTOFUTofuForest()
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
