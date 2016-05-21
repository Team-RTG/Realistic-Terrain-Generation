
package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 * This creates steep mountains in ranges, leaving most land minimally affected
 * @author Zeno410
 */
public class ScatteredMountainsEffect extends HeightEffect {
    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    // a trio of parameters frequently used together
    public float mountainHeight= Integer.MAX_VALUE;;
    public float mountainWavelength =0 ;
    public float spikeHeight= Integer.MAX_VALUE;;
    public float spikeWavelength =0 ;
    private float adjustedBottom = TerrainBase.blendedHillHeight(0,0f);;


    // octaves are standardized so they don't need to be set
    public int hillOctave = 0;//
    public int spikeOctave = 2;//

    public final float added(OpenSimplexNoise simplex, CellNoise cell,float x, float y) {
        float noise= simplex.octave(hillOctave).noise2(x/mountainWavelength, y/mountainWavelength);
        noise = TerrainBase.blendedHillHeight(noise,0f);
        float spikeNoise = simplex.octave(spikeOctave).noise2(x/spikeWavelength, y/spikeWavelength);
        spikeNoise = Math.abs(noise);
        spikeNoise = TerrainBase.blendedHillHeight(noise, 0f);
        spikeNoise *= spikeNoise;
        spikeNoise = TerrainBase.blendedHillHeight(spikeNoise*noise);
        return noise*mountainHeight+spikeNoise*spikeHeight;
    }
}
