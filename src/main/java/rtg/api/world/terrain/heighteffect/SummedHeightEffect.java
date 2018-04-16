package rtg.api.world.terrain.heighteffect;

import rtg.api.world.IRTGWorld;

// TODO: [1.12] The functionality of this class could probably be handled by a static utility.
class SummedHeightEffect extends HeightEffect {

    private final HeightEffect one;
    private final HeightEffect two;

    public SummedHeightEffect(HeightEffect one, HeightEffect two) {

        this.one = one;
        this.two = two;
    }

    @Override
    public float added(IRTGWorld rtgWorld, float x, float y) {

        return one.added(rtgWorld, x, y) + two.added(rtgWorld, x, y);
    }
}
