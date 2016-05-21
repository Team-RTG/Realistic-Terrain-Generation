
package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 *
 * @author Zeno410
 */
public class HillockEffect extends HeightEffect {
    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float height= Integer.MAX_VALUE;;
    public float wavelength =0 ;
    public float minimumSimplex = Integer.MAX_VALUE;// normal range is -1 to 1;
                                //usually numbers above 0 are often preferred to avoid dead basins
    public int octave;

    public final float added(OpenSimplexNoise simplex, CellNoise cell,float x, float y) {
        float noise= simplex.octave(octave).noise2(x/wavelength, y/wavelength);
        if (noise < minimumSimplex) {
            noise = 0;
        } else {
            noise = (noise-minimumSimplex)/(1f -minimumSimplex);
        }
        return noise*height;
    }
}
