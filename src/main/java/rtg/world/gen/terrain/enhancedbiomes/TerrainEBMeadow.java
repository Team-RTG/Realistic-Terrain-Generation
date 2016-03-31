package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HillockEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBMeadow extends TerrainBase
{
    private float hillHeight = 15;
    private float hillWavelength = 70;
    private float baseHeight = 66;
    private HeightEffect height;
    public TerrainEBMeadow()
    {

        HillockEffect hills = new HillockEffect();
        hills.height = hillHeight;
        hills.minimumSimplex = 0.4f;
        hills.wavelength = hillWavelength;

        height = hills.plus(new GroundEffect(2f));
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(height.added(simplex, cell, x, y)+baseHeight,river);
    }
}
