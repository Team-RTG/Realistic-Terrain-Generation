package rtg.api.world.terrain.heighteffect;

import rtg.api.world.IRTGWorld;

/**
 * // just adds a constant increase
 *
 * @author Zeno410
 */
public class RaiseEffect extends HeightEffect {

    // just adds a number
    public final float height;

    public RaiseEffect(float height) {

        this.height = height;
    }

    @Override
    public final float added(IRTGWorld rtgWorld, float x, float y) {

        return height;
    }
}
