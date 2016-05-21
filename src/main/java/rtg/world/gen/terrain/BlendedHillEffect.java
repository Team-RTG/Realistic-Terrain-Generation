
package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 *
 * @author Zeno410
 */
public class BlendedHillEffect extends HeightEffect {
    // similar to HillockEffect except that the transition is smooth
    // and it can pass through a subordinate effect after multiply by the BlendedHill noise

    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float height= Integer.MAX_VALUE;;
    public float wavelength =0 ;
    public float hillBottomSimplexValue = Integer.MAX_VALUE;// normal range is -1 to 1;
                                //usually numbers above 0 are often preferred to avoid dead basins
    public int octave;
    public HeightEffect subordinate;

    public final float added(OpenSimplexNoise simplex, CellNoise cell,float x, float y) {
        float noise= simplex.octave(octave).noise2(x/wavelength, y/wavelength);
        noise = TerrainBase.blendedHillHeight(noise, hillBottomSimplexValue);
        if (subordinate == null) return noise*height;
        return noise*(height+subordinate.added(simplex, cell, x, y));
    }

}