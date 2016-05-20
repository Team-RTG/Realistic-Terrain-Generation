
package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.SimplexOctave;

/**
 *
 * This class returns a height effect with a jitter on the position.
 * @author Zeno410
 */
public class JitterEffect extends HeightEffect {

    public float amplitude = Integer.MAX_VALUE;
    public float wavelength = 0;
    public HeightEffect jittered;
    private SimplexOctave.Disk jitter = new SimplexOctave.Disk();
    private boolean running = false;// this is to check for re-entrancy because this isn't re-entrant

    public JitterEffect() {}
    
    public JitterEffect(float amplitude, float wavelength, HeightEffect toJitter) {
        this.amplitude = amplitude;
        this.wavelength = wavelength;
        this.jittered = toJitter;
    }
    
    @Override
    public float added(OpenSimplexNoise simplex, CellNoise cell, float x, float y) {
        if (running) throw new RuntimeException();
        running = true;
        simplex.riverJitter().evaluateNoise((float)x / wavelength, (float)y / wavelength, jitter);
        int pX = (int)Math.round(x + jitter.deltax() * amplitude);
        int pY = (int)Math.round(y + jitter.deltay() * amplitude);
        running = false;
        return jittered.added(simplex, cell, pX, pY);
    }

}
