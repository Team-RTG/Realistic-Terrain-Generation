package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLValley extends TerrainBase
{
	public TerrainHLValley()
	{
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(65f + groundEffect.added(simplex, cell, x, y),river);
    }
    private GroundEffect groundEffect = new GroundEffect(4f);
}
