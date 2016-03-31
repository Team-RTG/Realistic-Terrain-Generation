package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBScrub extends TerrainBase
{

    private HeightEffect height;
    public TerrainEBScrub()
    {
        base = 66f;
        HillockEffect hills = new HillockEffect();
        hills.height = 5;
        hills.minimumSimplex = 0.4f;
        hills.wavelength = 30;

        height = hills.plus(new GroundEffect(1.5f));

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(height.added(simplex, cell, x, y)+ base,river);
    }
}
