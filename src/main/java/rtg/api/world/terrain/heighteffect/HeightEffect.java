// TODO: [1.12] This whole HeightEffect system should be rewritten eventually. HeightEffects should have private data with setter/getters, or have a builder with method chaining.

// TODO: [1.12] Uhh.. licenced by whom? Where? Will make no difference after conversion to interface.
/*
 * Available under the Lesser GPL License 3.0
 */

package rtg.api.world.terrain.heighteffect;

import rtg.api.world.RTGWorld;


/**
 * @author Zeno410
 */
// TODO: [1.12] This should be an interface.
public abstract class HeightEffect {

    // TODO: [1.12] The naming of this method makes little syntactical sense. Lack of documentation is also unhelpful.
    public abstract float added(RTGWorld rtgWorld, float x, float y);

    //  TODO: [1.12] This functionality should be a member of SummedHeightEffect or a static utility method
    public HeightEffect plus(HeightEffect added) {

        return new SummedHeightEffect(this, added);
    }
}
