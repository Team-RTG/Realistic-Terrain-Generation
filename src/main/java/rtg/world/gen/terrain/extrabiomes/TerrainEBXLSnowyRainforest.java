package rtg.world.gen.terrain.extrabiomes;

import rtg.world.gen.terrain.FunctionalTerrainBase;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.MountainsWithPassesEffect;

public class TerrainEBXLSnowyRainforest extends FunctionalTerrainBase {
	private float width;
	private float strength;
	private float spikeWidth = 40;
    private float spikeHeight = 50;

	public TerrainEBXLSnowyRainforest(float mountainWidth, float mountainStrength)
	{
		this(mountainWidth, mountainStrength, 80f);
	}

	public TerrainEBXLSnowyRainforest(float mountainWidth, float mountainStrength, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		base = height;
        MountainsWithPassesEffect mountainEffect = new MountainsWithPassesEffect();
        mountainEffect.mountainHeight = strength;
        mountainEffect.mountainWavelength = width;
        mountainEffect.spikeHeight = this.spikeHeight;
        mountainEffect.spikeWavelength = this.spikeWidth;

        super.height = new JitterEffect(5f,10f, mountainEffect);
        super.height = new JitterEffect(2f,6f,super.height);

	}
}
