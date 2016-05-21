package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaJungleEdge extends TerrainBase
{

    public TerrainVanillaJungleEdge()
    {

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {

        return riverized(65f + groundEffect.added(simplex, cell, x, y),river);
    }
    private GroundEffect groundEffect = new GroundEffect(4f);
}
