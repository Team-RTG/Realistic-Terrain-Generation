package rtg.api.world.terrain.heighteffect;

import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;

/**
 * @author Zeno410
 */
public class AdjustableSpikeEffect extends HeightEffect {

    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float height = Integer.MAX_VALUE;
    ;
    public float wavelength = 0;
    public float minimumSimplex = Integer.MAX_VALUE;// normal range is -1 to 1;
    //usually numbers above 0 are often preferred to avoid dead basins
    public int octave;
    public float power = 1.6f;// usually a range of 1 to 2

    @Override
    public final float added(RTGWorld rtgWorld, float x, float y) {

        float noise = rtgWorld.simplex.octave(octave).noise2((float) x / wavelength, (float) y / wavelength);
        if (noise < minimumSimplex) {
            noise = minimumSimplex;
        }
        noise = 1f - (1f - noise) / (1f - minimumSimplex);
        noise = TerrainBase.unsignedPower(noise, power);
        return noise * height;
    }
}
