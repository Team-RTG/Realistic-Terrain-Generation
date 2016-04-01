package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBSnowyPlateau extends TerrainBase
{

    private float plateauHeight = 30f;

	public TerrainEBSnowyPlateau(float plateauHeight, float baseHeight)
	{

        this.plateauHeight = plateauHeight;
        base = baseHeight;

	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(
                borderAdjusted(plateauHeight, border, 0.7f, 0.5f)
                + this.groundNoise(x, y, 2f, simplex)
                + base
                ,river);
	}
}
