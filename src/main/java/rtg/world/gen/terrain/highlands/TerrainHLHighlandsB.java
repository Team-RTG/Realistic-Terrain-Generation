package rtg.world.gen.terrain.highlands;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.BumpyHillsEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainHLHighlandsB extends TerrainBase
{

    private float baseHeight = 90f;
    private BumpyHillsEffect onTop = new BumpyHillsEffect();
    private JitterEffect withJitter;
	public TerrainHLHighlandsB()
	{
        onTop.hillHeight = 50;
        onTop.hillWavelength = 60;
        onTop.spikeHeight = 20;
        onTop.spikeWavelength = 10;

        JitterEffect bigJitter = new JitterEffect();
        bigJitter.amplitude = 15;
        bigJitter.wavelength = 60;
        bigJitter.jittered = onTop;

        withJitter = new JitterEffect();
        withJitter.amplitude=2;
        withJitter.wavelength=5;
        withJitter.jittered = bigJitter;
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(baseHeight + withJitter.added(simplex, cell,x, y)+ this.groundNoise(x, y, 6, simplex),river);
        //return terrainGrasslandMountains(x, y, simplex, cell, river, 4f, 80f, 68f);
	}
}
