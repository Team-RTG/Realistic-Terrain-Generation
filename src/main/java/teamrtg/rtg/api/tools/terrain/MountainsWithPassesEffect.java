package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.world.biome.TerrainBase;

/**
 * @author Zeno410
 */
public class MountainsWithPassesEffect extends HeightEffect {
    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float mountainHeight = Integer.MAX_VALUE;
    ;
    public float mountainWavelength = 0;
    public float spikeHeight = Integer.MAX_VALUE;
    ;
    public float spikeWavelength = 0;
    // octaves are standardized so they don't need to be set
    public int hillOctave = 0;//
    ;
    public int spikeOctave = 2;//
    private float adjustedBottom = TerrainBase.blendedHillHeight(0, 0f);

    public final float added(OpenSimplexNoise simplex, CellNoise cell, int x, int y) {
        float noise = simplex.octave(hillOctave).noise2((float) x / mountainWavelength, (float) y / mountainWavelength);
        noise = Math.abs(noise);
        noise = TerrainBase.blendedHillHeight(noise, 0f);
        noise = 1f - (1f - noise) / (1f - adjustedBottom);
        float spikeNoise = simplex.octave(spikeOctave).noise2((float) x / spikeWavelength, (float) y / spikeWavelength);
        spikeNoise = Math.abs(noise);
        spikeNoise = TerrainBase.blendedHillHeight(noise, 0f);
        spikeNoise *= spikeNoise;
        spikeNoise = TerrainBase.blendedHillHeight(spikeNoise * noise);
        return noise * mountainHeight + spikeNoise * spikeHeight;
    }
}