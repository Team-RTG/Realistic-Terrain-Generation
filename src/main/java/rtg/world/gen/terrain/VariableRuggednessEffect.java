
package rtg.world.gen.terrain;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 * This provides a standard "ruggedness switch" between a rugged terrain and a smooth one
 * it has its wavelength standardized to provide cooperation between adjacent terrains
 * @author Zeno410
 */
public class VariableRuggednessEffect extends HeightEffect {
    // not going to bother to set up a creator shell to make sure everything is set
    // set defaults to absurd values to crash if they're not set
    public HeightEffect smoothTerrain;
    public HeightEffect ruggedTerrain;
    public float startTransition = Integer.MAX_VALUE;
    public float transitionWidth = 0;
    public int octave=1;// this is the standard "ruggedness octave"
    public float wavelength ;// standard ruggedness wavelength
    
    public static float STANDARD_RUGGEDNESS_WAVELENGTH = 200f;
    public static int STANDARD_RUGGEDNESS_OCTAVE = 1;

    public VariableRuggednessEffect() {}

    public VariableRuggednessEffect(HeightEffect smoothTerrain, HeightEffect ruggedTerrain,
            float startTransition, float transitionWidth, float wavelength) {
        this.smoothTerrain = smoothTerrain;
        this.ruggedTerrain = ruggedTerrain;
        this.startTransition = startTransition;
        this.transitionWidth = transitionWidth;
        this.wavelength = wavelength;
    }

    public VariableRuggednessEffect(HeightEffect smoothTerrain, HeightEffect ruggedTerrain,
        float startTransition, float transitionWidth) {
        this(smoothTerrain,ruggedTerrain,startTransition,transitionWidth,STANDARD_RUGGEDNESS_WAVELENGTH);

    }

    public final float added(OpenSimplexNoise simplex, CellNoise cell,float x, float y) {
        float choice = simplex.octave(octave).noise2((float)x/wavelength, (float)y/wavelength);
        if (choice<= startTransition) return smoothTerrain.added(simplex, cell,x, y);
        if (choice>= startTransition + transitionWidth) return ruggedTerrain.added(simplex, cell, x, y);
        // otherwise in the transition zone;
        float smooth = smoothTerrain.added(simplex, cell, x, y);
        float rugged = ruggedTerrain.added(simplex, cell, x, y);
        return ((choice - startTransition)*rugged + (startTransition + transitionWidth - choice)*smooth)/
                transitionWidth;
    }

}