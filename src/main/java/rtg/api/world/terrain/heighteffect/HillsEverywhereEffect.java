package rtg.api.world.terrain.heighteffect;

import rtg.api.world.IRTGWorld;
import rtg.api.world.terrain.TerrainBase;

/**
 * @author Zeno410
 */
public class HillsEverywhereEffect extends HeightEffect {
    // this multiplies an existing heightEffect by the absolute value of a blended simplex call
    // and adds a height of its own

    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float height = Integer.MAX_VALUE;
    ;
    public float wavelength = 3;
    public float hillBottomSimplexValue = 0.2f;// normal range is -1 to 1;
    //usually numbers above 0 are often preferred to avoid dead basins
    public int octave;
    public HeightEffect modified;

    @Override
    public final float added(IRTGWorld rtgWorld, float x, float y) {

        float noise = rtgWorld.simplex().octave(octave).noise2(x / wavelength, y / wavelength);
        noise = Math.abs(noise);
        noise = TerrainBase.blendedHillHeight(noise, hillBottomSimplexValue);
        if (modified == null) {
            return noise * height;
        }
        return noise * (height + modified.added(rtgWorld, x, y));
    }

}
