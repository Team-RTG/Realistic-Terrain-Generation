package rtg.world.gen.terrain.enhancedbiomes;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.terrain.HeightEffect;
import rtg.world.gen.terrain.JitterEffect;
import rtg.world.gen.terrain.SpikeEffect;
import rtg.world.gen.terrain.TerrainBase;

public class TerrainEBWastelands extends TerrainBase
{

    private float baseHeight = 66;
    private float spikeHeight = 15;
    private float spikeWavelength = 25;
    private float jitterAmplitude = 4;
    private float jitterWavelength = 15;
    private HeightEffect height;

    public TerrainEBWastelands()
    {
        // spikes
        SpikeEffect spikes = new SpikeEffect();
        spikes.height = spikeHeight;
        spikes.minimumSimplex = 0.3f;
        spikes.wavelength= spikeWavelength;

        // jittered to be messy
        JitterEffect jitter = new JitterEffect();
        jitter.amplitude = jitterAmplitude;
        jitter.wavelength = jitterWavelength;
        jitter.jittered = spikes;

        height = jitter;
    }

    @Override
    public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river)
    {
        return riverized(height.added(simplex, cell, x, y)+baseHeight,river);
    }
}
