package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBBorealPlateau extends TerrainBase
{
    TerrainEBBorealForest baseHeight = new TerrainEBBorealForest();
    private float addedHeight = 20f;
	
	public TerrainEBBorealPlateau(float addedHeight)
	{
        this.addedHeight = addedHeight;

	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        float nonPlateauHeight = baseHeight.generateNoise(simplex, cell, x, y, border, river);
        // low off plateau
        if (border < .60) return nonPlateauHeight;
        float added = addedHeight/border;
        // high on plateau
        if (border >= .70 ) return nonPlateauHeight + added;
        // interpolate;
        return nonPlateauHeight + added*(border -0.6f)/(.1f);
    }
}
