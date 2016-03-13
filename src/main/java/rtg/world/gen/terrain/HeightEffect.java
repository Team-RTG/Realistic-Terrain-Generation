/*
 * Available under the Lesser GPL License 3.0
 */

package rtg.world.gen.terrain;

import rtg.util.OpenSimplexNoise;

/**
 *
 * @author Zeno410
 */
public abstract class HeightEffect {
    public abstract float added(OpenSimplexNoise simplex, int x, int y);
}
