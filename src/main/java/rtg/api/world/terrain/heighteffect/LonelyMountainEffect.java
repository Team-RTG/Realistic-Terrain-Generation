package rtg.api.world.terrain.heighteffect;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

/**
 * @author Zeno410
 */
public class LonelyMountainEffect extends HeightEffect {

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

    @Override
    public final float added(RTGWorld rtgWorld, float x, float y) {

        float noise = rtgWorld.simplex.octave(hillOctave).noise2(x / mountainWavelength, y / mountainWavelength);
        noise = Math.abs(noise);
        noise = TerrainBase.blendedHillHeight(noise, 0f);
        //transform to be more mountainous
        noise = TerrainBase.unsignedPower(noise, 1.7f);
        noise = 1f - (1f - noise) / (1f - adjustedBottom);
        float spikeNoise = rtgWorld.simplex.octave(spikeOctave).noise2((float) x / spikeWavelength, (float) y / spikeWavelength);
        spikeNoise = Math.abs(noise);
        spikeNoise = TerrainBase.blendedHillHeight(noise, 0f);
        spikeNoise *= noise;
        return noise * mountainHeight + spikeNoise * spikeHeight;
    }
}