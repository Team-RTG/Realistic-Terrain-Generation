package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBBorealPlateauM extends TerrainBase
{
    TerrainEBBorealForest baseHeight = new TerrainEBBorealForest();
    private float addedHeight = 30f;

	public TerrainEBBorealPlateauM(float addedHeight)
	{
        this.addedHeight = addedHeight;

	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        float nonPlateauHeight = baseHeight.generateNoise(simplex, cell, x, y, border, river);
        // low off plateau
        if (border < .4) return nonPlateauHeight;
        float added = addedHeight/border;
        // high on plateau
        if (border >= .6 ) return nonPlateauHeight + added;
        // interpolate;
        return nonPlateauHeight + added*(border -0.4f)/(.2f);
	}
}
