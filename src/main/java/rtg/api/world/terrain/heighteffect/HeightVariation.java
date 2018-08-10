package rtg.api.world.terrain.heighteffect;

import rtg.api.world.RTGWorld;


/**
 * @author Zeno410
 */
public class HeightVariation extends HeightEffect {

    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    public float height = Integer.MAX_VALUE;
    public float wavelength = 0;
    public int octave = -1;

    @Override
    public final float added(RTGWorld rtgWorld, float x, float y) {
        return rtgWorld.simplexInstance(octave).noise2f(x / wavelength, y / wavelength) * height;
    }

}
