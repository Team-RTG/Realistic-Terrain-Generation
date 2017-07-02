package rtg.api.world.terrain.heighteffect;

import rtg.api.world.IRTGWorld;
import rtg.api.world.terrain.TerrainBase;

/**
 * @author Zeno410
 */
public class GroundEffect extends HeightEffect {

    // the standard ground effect
    private final float amplitude;

    public GroundEffect(float amplitude) {

        this.amplitude = amplitude;
    }

    @Override
    public final float added(IRTGWorld rtgWorld, float x, float y) {

        return TerrainBase.groundNoise(x, y, amplitude, rtgWorld.simplex());
    }

}
