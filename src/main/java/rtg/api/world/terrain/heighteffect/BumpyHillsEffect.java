package rtg.api.world.terrain.heighteffect;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


/**
 * This creates an effect of scattered hills with irregular surfaces
 *
 * @author Zeno410
 */
public class BumpyHillsEffect extends HeightEffect {

    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float hillHeight = Integer.MAX_VALUE;
    public float hillWavelength = 0;
    public float spikeHeight = Integer.MAX_VALUE;
    public float spikeWavelength = 0;


    // octaves are standardized so they don't need to be set
    public int hillOctave = 0;//
    public int spikeOctave = 2;//

    @Override
    public final float added(RTGWorld rtgWorld, float x, float y) {
        float noise = rtgWorld.simplexInstance(hillOctave).noise2f(x / hillWavelength, y / hillWavelength);
        noise = TerrainBase.blendedHillHeight(noise);
        float spikeNoise = rtgWorld.simplexInstance(spikeOctave).noise2f(x / spikeWavelength, y / spikeWavelength);
        spikeNoise = TerrainBase.blendedHillHeight(spikeNoise * noise);
        return noise * hillHeight + spikeNoise * spikeHeight;
    }
}
