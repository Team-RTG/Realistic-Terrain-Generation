package rtg.world.gen.terrain.vanilla;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.MountainsWithPassesEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainVanillaExtremeHillsPlus extends TerrainBase
{

    private float width;
    private float strength;
    private float spikeWidth = 40;
    private float spikeHeight = 70;
    private HeightEffect heightEffect;

    public TerrainVanillaExtremeHillsPlus(float mountainWidth, float mountainStrength, float height)
    {
		width = mountainWidth;
		strength = mountainStrength;
		base = height;
        MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
        mountainEffect.mountainHeight = strength;
        mountainEffect.mountainWavelength = width;
        mountainEffect.spikeHeight = this.spikeHeight;
        mountainEffect.spikeWavelength = this.spikeWidth;

        heightEffect = new JitterEffect(7f,10f, mountainEffect);
        heightEffect = new JitterEffect(3f,6f,heightEffect);
        //this(mountainWidth, mountainStrength, depthLake, 260f, 68f);
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(heightEffect.added(simplex, cell, x, y)+base,river);
    }
}
