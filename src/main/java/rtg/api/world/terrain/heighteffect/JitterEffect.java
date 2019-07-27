package rtg.api.world.terrain.heighteffect;

import rtg.api.util.noise.ISimplexData2D;
import rtg.api.util.noise.SimplexData2D;
import rtg.api.world.RTGWorld;


/**
 * This class returns a height effect with a jitter on the position.
 *
 * @author Zeno410
 */
public class JitterEffect extends HeightEffect {

    public float amplitude = Integer.MAX_VALUE;
    public float wavelength = 0;
    public HeightEffect jittered;

    public JitterEffect() {

    }

    public JitterEffect(float amplitude, float wavelength, HeightEffect toJitter) {

        this.amplitude = amplitude;
        this.wavelength = wavelength;
        this.jittered = toJitter;
    }

    @Override
    public final float added(RTGWorld rtgWorld, float x, float y) {

        ISimplexData2D jitterData = SimplexData2D.newDisk();
        rtgWorld.simplexInstance(1).multiEval2D(x / wavelength, y / wavelength, jitterData);
        int pX = (int) Math.round(x + jitterData.getDeltaX() * amplitude);
        int pY = (int) Math.round(y + jitterData.getDeltaY() * amplitude);
        return jittered.added(rtgWorld, pX, pY);
    }

}
