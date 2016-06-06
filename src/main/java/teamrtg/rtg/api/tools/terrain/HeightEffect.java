/*
 * Available under the Lesser GPL License 3.0
 */

package teamrtg.rtg.api.tools.terrain;

import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;

/**
 * @author Zeno410
 */
public abstract class HeightEffect {
    public abstract float added(OpenSimplexNoise simplex, CellNoise cell, int x, int y);

    public HeightEffect plus(HeightEffect added) {
        return new SummedHeightEffects(this, added);
    }

    private class SummedHeightEffects extends HeightEffect {
        private final HeightEffect one;
        private final HeightEffect two;

        public SummedHeightEffects(HeightEffect one, HeightEffect two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public float added(OpenSimplexNoise simplex, CellNoise cell, int x, int y) {
            return one.added(simplex, cell, x, y) + two.added(simplex, cell, x, y);
        }
    }
}
