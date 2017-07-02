package rtg.world.gen.terrain.eccentricbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.MountainsWithPassesEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainECCVoid extends TerrainBase
{
    private float width;
    private float strength;
    private float terrainHeight;
    private float spikeWidth = 30;
    private float spikeHeight = 50;
    private HeightEffect heightEffect;

    public TerrainECCVoid(float mountainWidth, float mountainStrength)
    {
        this(mountainWidth, mountainStrength, 90f);
    }

    public TerrainECCVoid(float mountainWidth, float mountainStrength, float height)
    {
        width = mountainWidth;
        strength = mountainStrength;
        terrainHeight = height;
        MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
        mountainEffect.mountainHeight = strength;
        mountainEffect.mountainWavelength = width;
        mountainEffect.spikeHeight = this.spikeHeight;
        mountainEffect.spikeWavelength = this.spikeWidth;


        heightEffect = new JitterEffect(7f,10f, mountainEffect);
        heightEffect = new JitterEffect(3f,6f,heightEffect);

    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(heightEffect.added(simplex, cell, x, y)+terrainHeight,river);
    }
}
