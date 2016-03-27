package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.GroundEffect;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.HeightVariation;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.MountainsWithPassesEffect;
import rtg.world.gen.terrain.ScatteredMountainsEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBSandstoneRanges extends TerrainBase
{
	private float width;
	private float strength;
	private float terrainHeight;
    private float spikeWidth = 15;
    private float spikeHeight = 30;
    private HeightEffect heightEffect;

	public TerrainEBSandstoneRanges(float mountainWidth, float mountainStrength)
	{
		this(mountainWidth, mountainStrength, 80f);
	}

	public TerrainEBSandstoneRanges(float mountainWidth, float mountainStrength, float height)
	{
		width = mountainWidth;
		strength = mountainStrength;
		terrainHeight = height;
        ScatteredMountainsEffect mountainEffect = new ScatteredMountainsEffect();
        mountainEffect.mountainHeight = strength;
        mountainEffect.mountainWavelength = width;
        mountainEffect.spikeHeight = this.spikeHeight;
        mountainEffect.spikeWavelength = this.spikeWidth;

        heightEffect = new JitterEffect(5f,10f, mountainEffect);
        heightEffect = new JitterEffect(2f,6f,heightEffect);

        HeightVariation hilliness = new HeightVariation();
        hilliness.octave = 2;
        hilliness.wavelength = 40;
        hilliness.height = 6;

        heightEffect = heightEffect.plus(hilliness);

        GroundEffect ground = new GroundEffect(3f);

        heightEffect = heightEffect.plus(ground);
	}

	@Override
	public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
	{
        return riverized(heightEffect.added(simplex, cell, x, y)+terrainHeight,river);
    }
}
