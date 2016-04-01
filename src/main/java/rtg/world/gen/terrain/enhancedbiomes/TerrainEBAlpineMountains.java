package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.LonelyMountainEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBAlpineMountains extends TerrainBase
{
    private HeightEffect height;
    private float baseHeight = 95;
	public TerrainEBAlpineMountains()
	{
        LonelyMountainEffect mountain = new LonelyMountainEffect();
        mountain.mountainHeight = 60;
        mountain.mountainWavelength = 70;
        mountain.spikeHeight = 15;
        mountain.spikeWavelength = 30;

        JitterEffect jitteredMountain = new JitterEffect(7f,10f,mountain);
        height = jitteredMountain.plus(new GroundEffect(8f));
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return height.added(simplex, cell, x, y) + baseHeight;
        //return terrainMountainRiver(x, y, simplex, cell, river, 300f, 67f);
	}
}
