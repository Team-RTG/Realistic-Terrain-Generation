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
        return borderAdjusted(addedHeight, border, .6f, 0.4f)
                + baseHeight.generateNoise(simplex, cell, x, y, border, river);
    }
}
