package rtg.api.world.terrain.heighteffect;

import rtg.api.world.IRTGWorld;

/**
 * @author Zeno410
 */
public class PlateauEffect extends HeightEffect {
    // similar to HillockEffect except that the transition is smooth
    // and it can pass through a subordinate effect after multiply by the BlendedHill noise

    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float height = Integer.MAX_VALUE;
    ;
    public float wavelength = 0;
    public float bottomSimplexValue = Integer.MAX_VALUE;// normal range is -1 to 1;
    //usually numbers above 0 are often preferred to avoid dead basins
    public float topSimplexValue = Integer.MIN_VALUE;
    public int octave;
    public HeightEffect subordinate;

    @Override
    public final float added(IRTGWorld rtgWorld, float x, float y) {

        float noise = rtgWorld.simplex().octave(octave).noise2(x / wavelength, y / wavelength);
        if (noise > topSimplexValue) {
            noise = 1f;
        }
        else if (noise < bottomSimplexValue) {
            noise = 0f;
        }
        else {
            noise = (noise - bottomSimplexValue) / (topSimplexValue - bottomSimplexValue);
        }
        if (subordinate == null) {
            return noise * height;
        }
        float added = subordinate.added(rtgWorld, x, y);
        return noise * (height + added);
    }
}