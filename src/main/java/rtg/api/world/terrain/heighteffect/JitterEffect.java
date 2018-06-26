package rtg.api.world.terrain.heighteffect;

import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.RTGWorld;

/**
 * This class returns a height effect with a jitter on the position.
 *
 * @author Zeno410
 */
// TODO: [1.12] The functionality of this class should be added as default methods in the interface. When a HeightEffect is created for a terrain then it
//              can apply a jitter effect, with the passed values, if jottering is enabled for that HeightEffect.
public class JitterEffect extends HeightEffect {

    public float amplitude = Integer.MAX_VALUE;
    public float wavelength = 0;
    public HeightEffect jittered;
    private boolean running = false;// this is to check for re-entrancy because this isn't re-entrant

    public JitterEffect() {

    }

    public JitterEffect(float amplitude, float wavelength, HeightEffect toJitter) {

        this.amplitude = amplitude;
        this.wavelength = wavelength;
        this.jittered = toJitter;
    }

    @Override
    public final float added(RTGWorld rtgWorld, float x, float y) {

        if (running) {
// TODO: [1.12] WHY ALWAYS RUNTIME EXCEPTIONS?! WHY?! :SUPERDUPERRAGE:
            throw new RuntimeException();
        }
        running = true;
        ISimplexData2D jitterData = SimplexData2D. newDisk();
        rtgWorld.simplexInstance(1).multiEval2D(x / wavelength, y / wavelength, jitterData);
        int pX = (int) Math.round(x + jitterData.getDeltaX() * amplitude);
        int pY = (int) Math.round(y + jitterData.getDeltaY() * amplitude);
        running = false;
        return jittered.added(rtgWorld, pX, pY);
    }

}
