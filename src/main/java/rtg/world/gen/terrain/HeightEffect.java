/*
 * Available under the Lesser GPL License 3.0
 */

package rtg.world.gen.terrain;

import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;

/**
 *
 * @author Zeno410
 */
public abstract class HeightEffect {
    public abstract float added(OpenSimplexNoise simplex, CellNoise cell, float x, float y);
    
    public HeightEffect plus(HeightEffect added) {
        return new SummedHeightEffects(this,added);
    }

    private class SummedHeightEffects extends HeightEffect {
        private final HeightEffect one;
        private final HeightEffect two;
        public SummedHeightEffects(HeightEffect one, HeightEffect two){
            this.one = one;
            this.two = two;
        }

        @Override
        public float added(OpenSimplexNoise simplex, CellNoise cell,float x, float y) {
            return one.added(simplex, cell,x, y)+two.added(simplex, cell,x, y);
        }
    }
}
