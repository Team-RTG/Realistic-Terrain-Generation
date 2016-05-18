package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.world.biome.TerrainBase;

/**
 * @author Zeno410
 */
public class BlendedHillEffect extends HeightEffect {
    // similar to HillockEffect except that the transition is smooth

    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float height = Integer.MAX_VALUE;
    public float wavelength = 0;
    public float hillBottomSimplexValue = Integer.MAX_VALUE;// normal range is -1 to 1;
    //usually numbers above 0 are often preferred to avoid dead basins
    public int octave;

    public final float added(OpenSimplexNoise simplex, CellNoise cell, int x, int y) {
        float noise = simplex.octave(octave).noise2((float) x / wavelength, (float) y / wavelength);
        noise = TerrainBase.blendedHillHeight(noise, hillBottomSimplexValue);
        return noise * height;
    }

}