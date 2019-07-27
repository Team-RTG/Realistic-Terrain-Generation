package rtg.api.world.terrain.heighteffect;

import rtg.api.world.RTGWorld;


/**
 * @author Zeno410
 */
public abstract class HeightEffect {

    public abstract float added(RTGWorld rtgWorld, float x, float y);

    public HeightEffect plus(HeightEffect added) {

        return new SummedHeightEffect(this, added);
    }
}
