package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.util.noise.SimplexOctave;

/**
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
    public float added(OpenSimplexNoise simplex, CellNoise cell, int x, int y) {
        if (running) throw new RuntimeException();
        running = true;
        simplex.riverJitter().evaluateNoise(x / wavelength, y / wavelength, jitter);
        int pX = (int) Math.round(x + jitter.deltax() * amplitude);
        int pY = (int) Math.round(y + jitter.deltay() * amplitude);
        running = false;
        return jittered.added(simplex, cell, pX, pY);
    }

}
