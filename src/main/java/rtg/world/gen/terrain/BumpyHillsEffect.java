
package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 * This creates an effect of scattered hills with irregular surfaces
 * @author Zeno410
 */
public class BumpyHillsEffect extends HeightEffect {
    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float hillHeight= Integer.MAX_VALUE;;
    public float hillWavelength =0 ;
    public float spikeHeight= Integer.MAX_VALUE;;
    public float spikeWavelength =0 ;


    // octaves are standardized so they don't need to be set
    public int hillOctave = 0;//
    public int spikeOctave = 2;//

    public final float added(OpenSimplexNoise simplex, CellNoise cell,int x, int y) {
        float noise= simplex.octave(hillOctave).noise2((float)x/hillWavelength, (float)y/hillWavelength);
        noise = TerrainBase.blendedHillHeight(noise);
        float spikeNoise = simplex.octave(spikeOctave).noise2((float)x/spikeWavelength, (float)y/spikeWavelength);
        spikeNoise = TerrainBase.blendedHillHeight(spikeNoise*noise);
        return noise*hillHeight+spikeNoise*spikeHeight;
    }
}
