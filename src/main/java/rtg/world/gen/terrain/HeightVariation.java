
package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 *
 * @author Zeno410
 */
public class HeightVariation extends HeightEffect {
    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    public float height = Integer.MAX_VALUE;
    public float wavelength = 0;
    public int octave=-1;

    public final float added(OpenSimplexNoise simplex, CellNoise cell,float x, float y) {
        return simplex.octave(octave).noise2(x/wavelength, y/wavelength)*height;
    }

}
